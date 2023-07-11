/**
 * 1600 말이 되고픈 원숭이
 * https://www.acmicpc.net/problem/1600
 * 
 * @author minchae
 * @date 2023. 7. 12.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

class Point {
    int x;
    int y;
    int cnt; // 움직인 횟수
    int hCnt; // 말 이동으로 움직인 횟수

    public Point(int x, int y, int cnt, int hCnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.hCnt = hCnt;
    }
}

public class Main {

    // 말이 이동할 수 있는 위치
    static int[] hdx = { -2, -2, -1, -1, 1, 1, 2, 2 };
    static int[] hdy = { -1, 1, -2, 2, -2, 2, -1, 1 };

    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int K, W, H;
    static int[][] map; // 0은 평지, 1은 장애물

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        boolean[][][] visited = new boolean[H][W][K + 1]; // 원숭이가 K번만큼만 말의 이동위치로 이동할 수 있으므로 3차원 배열 사용

        q.add(new Point(0, 0, 0, 0)); // 시작점부터 탐색
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            // 목적지에 도착
            if (cur.x == H - 1 && cur.y == W - 1) {
                return cur.cnt;
            }

            // 원숭이가 인접한 네 방향으로 이동
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx >= 0 && nx < H && ny >= 0 && ny < W) {
                    // 평지이고, 방문하지 않은 경우 (원숭이 이동방향으로 이동하니까 말 이동으로 움직인 횟수는 그대로 둠)
                    if (map[nx][ny] == 0 && !visited[nx][ny][cur.hCnt]) {
                        q.add(new Point(nx, ny, cur.cnt + 1, cur.hCnt));
                        visited[nx][ny][cur.hCnt] = true;
                    }
                }
            }

            // 말 이동으로 움직인 횟수가 K보다 작은 경우 말의 이동 위치로 이동
            if (cur.hCnt < K) {
                for (int i = 0; i < 8; i++) {
                    int nx = cur.x + hdx[i];
                    int ny = cur.y + hdy[i];

                    if (nx >= 0 && nx < H && ny >= 0 && ny < W) {
                        // 평지이고, 말의 이동방향으로 가는 곳을 처음 가는 경우 (말 이동위치로 가니까 1 증가시킨 곳을 확인)
                        if (map[nx][ny] == 0 && !visited[nx][ny][cur.hCnt + 1]) {
                            q.add(new Point(nx, ny, cur.cnt + 1, cur.hCnt + 1));
                            visited[nx][ny][cur.hCnt + 1] = true;
                        }
                    }
                }
            }

        }

        return -1;
    }

}