/**
 * 16933 벽 부수고 이동하기 3
 * https://www.acmicpc.net/problem/16933
 * 
 * @author minchae
 * @date 2023. 8. 16.
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
    int wall;
    int dist;
    int state; // 0 낮, 1 밤

    public Position(int x, int y, int wall, int dist, int state) {
        this.x = x;
        this.y = y;
        this.wall = wall;
        this.dist = dist;
        this.state = state;
    }
}

public class Main {

    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int N, M, K;
    static int[][] map; // 0 이동할 수 있는 곳, 1 벽이 있는 곳

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Position> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][K + 1];

        q.add(new Position(0, 0, 0, 1, 0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Position cur = q.poll();

            int x = cur.x;
            int y = cur.y;
            int wall = cur.wall;
            int dist = cur.dist;
            int state = cur.state;

            if (x == N - 1 && y == M - 1) {
                return dist;
            }

            // 이동하는 경우
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (checkRange(nx, ny)) {
                    // 한 번 이동할 때마다 낮과 밤이 바뀌게 되므로 다음 칸의 visited 값은 낮과 밤을 반대로 넣어줌

                    if (map[nx][ny] == 1 && wall < K && !visited[nx][ny][wall + 1]) {
                        // 밤에 제자리에 있는 경우를 고려 (벽을 부수지 못해서 가지 못하는 경우이기 때문)
                        if (state == 0) {
                            q.add(new Position(nx, ny, wall + 1, dist + 1, 1));
                            visited[nx][ny][wall + 1] = true;
                        } else {
                            q.add(new Position(x, y, wall, dist + 1, 0));
                            visited[x][y][wall] = true;
                        }
                    } else if (map[nx][ny] == 0 && !visited[nx][ny][wall]) {
                        q.add(new Position(nx, ny, wall, dist + 1, state == 0 ? 1 : 0));
                        visited[nx][ny][wall] = true;
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