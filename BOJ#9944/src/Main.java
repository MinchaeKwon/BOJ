/**
 * 9944 NxM 보드 완주하기
 * https://www.acmicpc.net/problem/9944
 * 
 * @author minchae
 * @date 2023. 8. 13.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int N, M;
    static char[][] map; // 장애물 '*', 빈 칸 '.'

    static boolean[][] visited;
    static int emptyCnt;

    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 1;
        String input = null;

        while ((input = br.readLine()) != null && !input.isEmpty()) {
            StringTokenizer st = new StringTokenizer(input);

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[N][M];
            visited = new boolean[N][M];

            emptyCnt = 0;
            result = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();

                for (int j = 0; j < M; j++) {
                    if (map[i][j] == '.') {
                        emptyCnt++;
                    }
                }
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == '.') {
                        visited[i][j] = true;
                        dfs(i, j, 1, 0);
                        visited[i][j] = false;
                    }
                }
            }

            System.out.println("Case " + T + ": " + (result == Integer.MAX_VALUE ? -1 : result));
            T++;
        }
    }

    // 백트래킹 이용
    private static void dfs(int x, int y, int empty, int move) {
        // 빈 칸을 다 지나간 경우
        if (empty == emptyCnt) {
            result = Math.min(result, move);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x;
            int ny = y;
            
            int cnt = 0; // 특정 방향으로 몇 칸 이동했는지 저장
            
            // 위, 아래, 오른쪽, 왼쪽 중 방향 하나를 고른 다음, 그 방향으로 공을 계속해서 이동
            while (true) {
                nx += dx[d];
                ny += dy[d];

                // 공은 장애물, 보드의 경계, 이미 공이 지나갔던 칸을 만나기 전까지 계속해서 이동
                if (checkRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == '.') {
                    visited[nx][ny] = true;
                    cnt++;
                } else {
                    // 조건에 만족하지 않는 경우에는 원래대로 돌려줌
                    nx -= dx[d];
                    ny -= dy[d];

                    break;
                }
            }

            // 한 칸도 움직이지 못한 경우 방향 바꾸기 위해 continue
            if (cnt == 0) {
                continue;
            } else {
                dfs(nx, ny, empty + cnt, move + 1);

                // 원복 (백트래킹 이용하기 때문에 해주는 것)
                for (int i = 1; i <= cnt; i++) {
                    nx = x + dx[d] * i;
                    ny = y + dy[d] * i;

                    visited[nx][ny] = false;
                }
            }
        }

    }

    // 범위 확인
    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}