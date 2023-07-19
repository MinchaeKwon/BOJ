/**
 * 2589 보물섬
 * https://www.acmicpc.net/problem/2589
 * 
 * @author minchae
 * @date 2023. 7. 20.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

class Land {
    int x;
    int y;
    int time;

    public Land(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}

public class Main {
    
    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N, M;
    static char[][] map; // 육지(L), 바다(W)

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 모든 칸을 다 돌아보면서 가장 긴 시간이 걸리는 육지 찾음
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    answer = Math.max(answer, bfs(i, j));
                }
            }
        }

        System.out.println(answer);
    }

    private static int bfs(int x, int y) {
        Queue<Land> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        
        q.add(new Land(x, y, 0));
        visited[x][y] = true;

        int result = 0;

        while (!q.isEmpty()) {
            Land land = q.poll();

            x = land.x;
            y = land.y;
            int time = land.time;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    // 방문하지 않았고, 육지인 경우
                    if (!visited[nx][ny] && map[nx][ny] == 'L') {
                        q.add(new Land(nx, ny, time + 1));
                        visited[nx][ny] = true;

                        result = Math.max(result, time + 1); // 시간 갱신 -> 특정 육지에서 가장 많은 시간이 걸리는 육지 찾음
                    }
                }
            } 
        }

        return result;
    }
}