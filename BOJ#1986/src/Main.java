/**
 * 1986 체스
 * https://www.acmicpc.net/problem/1986
 * 
 * @author minchae
 * @date 2023. 7. 18.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    // 퀸이 움직일 수 있는 위치, 상하좌우, 대각선
    static int[] qdx = { -1, 1, 0, 0, -1, 1, -1, 1 };
    static int[] qdy = { 0, 0, -1, 1, 1, -1, -1, 1 };

    // 나이트가 움직일 수 있는 위치
    static int[] kdx = { -2, -2, -1, -1, 1, 1, 2, 2 };
    static int[] kdy = { -1, 1, -2, 2, -2, 2, -1, 1 };

    static int n, m;
    static int[][] map; // 퀸 1, 나이트 2, 폰 3

    static int qCnt, kCnt, pCnt;
    static Point[] queen, knight, pawn;

    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        st = new StringTokenizer(br.readLine());

        qCnt = Integer.parseInt(st.nextToken());
        queen = new Point[qCnt];

        for (int i = 0; i < qCnt; i++) {
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            map[x][y] = 1;
            queen[i] = new Point(x, y);
            visited[x][y] = true;
        }

        st = new StringTokenizer(br.readLine());

        kCnt = Integer.parseInt(st.nextToken());
        knight = new Point[kCnt];

        for (int i = 0; i < kCnt; i++) {
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            map[x][y] = 1;
            knight[i] = new Point(x, y);
            visited[x][y] = true;
        }

        st = new StringTokenizer(br.readLine());

        pCnt = Integer.parseInt(st.nextToken());
        pawn = new Point[pCnt];

        for (int i = 0; i < pCnt; i++) {
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            map[x][y] = 1;
            pawn[i] = new Point(x, y);
            visited[x][y] = true;
        }

        System.out.println(solve());
    }

    private static int solve() {

        for (int i = 0; i < qCnt; i++) {
            for (int d = 0; d < 8; d++) {
                int nx = queen[i].x + qdx[d];
                int ny = queen[i].y + qdy[d];

                // 퀸은 가로, 세로, 대각선으로 갈 수 있는 만큼 최대한 많이 이동할 수 있기 때문에 무한루프 사용
                while (true) {
                    if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] != 1) { // 장애물이 있을 때까지 이동
                        visited[nx][ny] = true;

                        nx += qdx[d];
                        ny += qdy[d];
                    } else {
                        break; // 범위를 벗어나거나 중간에 장애물이 있는 경우 루프 탈출 (나이트나 폰이 있는 경우)
                    }
                }
            }
        }

        for (int i = 0; i < kCnt; i++) {
            for (int d = 0; d < 8; d++) {
                int nx = knight[i].x + kdx[d];
                int ny = knight[i].y + kdy[d];

                // 범위를 벗어나지 않고 아직 방문하지 않은 경우
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                }
            }
        }

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

}