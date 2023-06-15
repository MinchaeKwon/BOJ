/**
 * 17142 연구소 3
 * https://www.acmicpc.net/problem/17142
 * 
 * @author minchae
 * @date 2023. 6. 15.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Virus {
    int x;
    int y;
    int time;

    public Virus(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}

public class Main {

    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int N, M;
    static int[][] map;
    static ArrayList<Virus> virusList = new ArrayList<>();

    static int empty = 0;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 0은 빈 칸, 1은 벽, 2는 바이러스

                if (map[i][j] == 2) {
                    virusList.add(new Virus(i, j, 0));
                }

                if (map[i][j] == 0) {
                    empty++;
                }
            }
        }

        // 빈 칸이 없을 경우 바이러스가 이미 다 퍼져있는 것이기 때문에 0 출력
        if (empty == 0) {
            System.out.println(0);
        } else {
            selectActiveVirus(0, 0);
            System.out.println(answer == Integer.MAX_VALUE ? -1 : answer); // 모든 빈 칸에 바이러스를 퍼뜨릴 수 없는 경우에는 -1 출력
        }

    }

    // 백트래킹으로 활성 바이러스가 위치할 수 있는 곳 찾음
    private static void selectActiveVirus(int start, int depth) {
        // M개의 활성 바이러스를 다 선택한 경우 bfs 실행
        if (depth == M) {
            bfs(empty);
            return;
        }

        for (int i = start; i < virusList.size(); i++) {
            Virus v = virusList.get(i);

            map[v.x][v.y] = 3; // 활성 바이러스는 3으로 표시
            selectActiveVirus(i + 1, depth + 1);
            map[v.x][v.y] = 2;
        }
    }

    // bfs를 통해 바이러스 퍼뜨림
    private static void bfs(int cnt) {
        Queue<Virus> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N]; // 바이러스 확산 여부 저장

        // 활성 바이러스 위치 큐에 넣어줌
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 3) {
                    q.add(new Virus(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            Virus v = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = v.x + dx[i];
                int ny = v.y + dy[i];

                // 범위 벗어나지 않은 경우
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    // 바이러스가 퍼지지 않았고, 벽이 아닌 경우에만 퍼뜨릴 수 있음
                    if (!visited[nx][ny] && map[nx][ny] != 1) {
                        q.add(new Virus(nx, ny, v.time + 1));
                        visited[nx][ny] = true;

                        // 빈 칸일 경우 바이러스를 퍼뜨리기 때문에 cnt 감소시킴
                        if (map[nx][ny] == 0) {
                            cnt--;
                        }

                        // 빈 칸이 다 없어질 경우에 최단 시간 갱신
                        if (cnt == 0) {
                            answer = Math.min(answer, v.time + 1);
                            return;
                        }
                    }
                }

            }
        }

    }

}