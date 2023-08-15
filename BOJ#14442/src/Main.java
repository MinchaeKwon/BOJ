/**
 * 14442 벽 부수고 이동하기 2
 * https://www.acmicpc.net/problem/14442
 * 
 * @author minchae
 * @date 2023. 8. 15.
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

    public Position(int x, int y, int wall, int dist) {
        this.x = x;
        this.y = y;
        this.wall = wall;
        this.dist = dist;
    }
}

public class Main {

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

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
        boolean[][][] visited = new boolean[N][M][K + 1]; // 벽을 몇번 부쉈을 때 방문했는지 확인하기 위해 3차원 배열 사용

        q.add(new Position(0, 0, 0, 1)); // 경로에 시작하는 칸과 끝나는 칸도 포함하기 때문에 1부터 시작
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Position cur = q.poll();

            int x = cur.x;
            int y = cur.y;
            int wall = cur.wall;
            int dist = cur.dist;

            if (x == N - 1 && y == M - 1) {
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (checkRange(nx, ny)) {
                    if (map[nx][ny] == 1 && wall < K && !visited[nx][ny][wall + 1]) { // 벽이 있는 곳, 현재 벽을 부순 횟수가 K미만, 다음 칸에 있는 벽을 부순 경우를 방문하지 않은 경우
                        q.add(new Position(nx, ny, wall + 1, dist + 1));
                        visited[nx][ny][wall + 1] = true;
                    } else if (map[nx][ny] == 0 && !visited[nx][ny][wall]) { // 이동할 수 있는 곳, 다음 칸을 방문하지 않은 경우
                        q.add(new Position(nx, ny, wall, dist + 1));
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