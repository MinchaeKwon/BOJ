/**
 * 14923 미로 탈출
 * https://www.acmicpc.net/problem/14923
 * 
 * @author minchae
 * @date 2023. 8. 17.
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
    int d;
    int wall;

    public Position(int x, int y, int d, int wall) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.wall = wall;
    }
}

public class Main {

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int N, M;
    static int[][] map; // 0이 빈 칸, 1이 벽

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int hx = Integer.parseInt(st.nextToken()) - 1;
        int hy = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        int ex = Integer.parseInt(st.nextToken()) - 1;
        int ey = Integer.parseInt(st.nextToken()) - 1;

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(hx, hy, ex, ey));
    }

    private static int bfs(int hx, int hy, int ex, int ey) {
        Queue<Position> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2]; // 벽을 한개 부쉈을 때와 부수지 않았을 때를 구분하기 위해 3차원 배열 사용

        q.add(new Position(hx, hy, 0, 0));
        visited[hx][hy][0] = true;

        while (!q.isEmpty()) {
            Position cur = q.poll();

            int x = cur.x;
            int y = cur.y;
            int d = cur.d;
            int wall = cur.wall;

            if (x == ex && y == ey) {
                return d;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (checkRange(nx, ny)) {
                    if (map[nx][ny] == 1 && wall == 0 && !visited[nx][ny][1]) { // 벽이고, 마법 지팡이 사용 안했고, 벽을 부쉈을 때의 경우를
                                                                                // 방문하지 않은 경우
                        q.add(new Position(nx, ny, d + 1, wall + 1));
                        visited[nx][ny][1] = true;
                    } else if (map[nx][ny] == 0 && !visited[nx][ny][wall]) { // 이동할 수 있는 칸이고 아직 방문하지 않은 경우
                        q.add(new Position(nx, ny, d + 1, wall));
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