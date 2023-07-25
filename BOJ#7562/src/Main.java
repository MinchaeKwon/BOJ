/**
 * 7562 나이트의 이동
 * https://www.acmicpc.net/problem/7562
 * 
 * @author minchae
 * @date 2023. 7. 26.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Knight {
    int x;
    int y;
    int cnt;

    public Knight(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

public class Main {
    
    // 나이트가 이동할 수 있는 위치
    static int[] dx = { -2, -2, -1, -1, 1, 1, 2, 2 };
    static int[] dy = { -1, 1, -2, 2, -2, 2, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int l = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());

            int ex = Integer.parseInt(st.nextToken());
            int ey = Integer.parseInt(st.nextToken());

            System.out.println(bfs(l, sx, sy, ex, ey));
        }
        
    }

    private static int bfs(int l, int sx, int sy, int ex, int ey) {
        Queue<Knight> q = new LinkedList<>();
        boolean[][] visited = new boolean[l][l];

        // 나이트의 시작위치 삽입
        q.add(new Knight(sx, sy, 0));
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            Knight cur = q.poll();

            // 나이트가 이동하려고 하는 칸에 도착한 경우
            if (cur.x == ex && cur.y == ey) {
                return cur.cnt;
            }

            for (int i = 0; i < 8; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                // 범위 벗어나지 않고 아직 방문하지 않은 경우
                if (nx >= 0 && nx < l && ny >= 0 && ny < l && !visited[nx][ny]) {
                    q.add(new Knight(nx, ny, cur.cnt + 1)); // 갈 수 있는 곳이기 때문에 cnt 증가시킴
                    visited[nx][ny] = true;
                }
            }
        }

        return 0;
    }
}