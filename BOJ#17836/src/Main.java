/**
 * 17836 공주님을 구해라!
 * https://www.acmicpc.net/problem/17836
 * 
 * @author minchae
 * @date 2023. 8. 20.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Position {
    int x;
    int y;
    int time;
    int sword; // 검을 없는 경우 0, 있는 경우 1

    public Position(int x, int y, int time, int sword) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.sword = sword;
    }
}

public class Main {

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int N, M, T;
    static int[][] map; // 0은 빈 공간, 1은 마법의 벽, 2는 그람

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = getTime();
        System.out.println(result == -1 ? "Fail" : result);
    }

    private static int getTime() {
        Queue<Position> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2]; // 검을 구했는지 안구했는지를 구분 (구하지 못한 경우 0, 구한 경우 1)

        q.add(new Position(0, 0, 0, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Position cur = q.poll();

            // 목적지에 도착한 경우
            if (cur.x == N - 1 && cur.y == M - 1) {
                return cur.time;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                // 범위를 벗어나지 않고 제한 시간 미만인 경우
                if (checkRange(nx, ny) && cur.time < T) {
                    /*
                     * 다음 칸이
                     * 1. 빈 공간, 방문하지 않았을 경우
                     * 2. 마법의 벽, 검이 있음, 검이 있는 채로 방문하지 않았을 경우 (벽이 있을 경우 검이 없으면 지나가지 못하기 때문에 해당 경우는 고려
                     * X)
                     * 3. 검 발견, 방문하지 않았을 경우
                     */

                    if (map[nx][ny] == 0 && !visited[nx][ny][cur.sword]) {
                        q.add(new Position(nx, ny, cur.time + 1, cur.sword));
                        visited[nx][ny][cur.sword] = true;
                    } else if (map[nx][ny] == 1 && cur.sword == 1 && !visited[nx][ny][cur.sword]) {
                        q.add(new Position(nx, ny, cur.time + 1, cur.sword));
                        visited[nx][ny][cur.sword] = true;
                    } else if (map[nx][ny] == 2 && !visited[nx][ny][1]) {
                        q.add(new Position(nx, ny, cur.time + 1, 1));
                        visited[nx][ny][1] = true;
                    }
                }
            }
        }

        return -1;
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}