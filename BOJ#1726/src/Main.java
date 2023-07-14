/**
 * 1726 로봇
 * https://www.acmicpc.net/problem/1726
 * 
 * @author minchae
 * @date 2023. 7. 15.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;
    int dir;
    int cnt;

    public Point(int x, int y, int dir, int cnt) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.cnt = cnt;
    }
}

public class Main {

    // 동서남북
    static int[] dx = { 0, 0, 1, -1 };
    static int[] dy = { 1, -1, 0, 0 };

    static int M, N;
    static int[][] map; // 0은 궤도가 깔려 있어 로봇이 갈 수 있는 지점, 1은 궤도가 없어 로봇이 갈 수 없는 지점

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 로봇의 출발 지점 (방향은 동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4)
        st = new StringTokenizer(br.readLine());
        Point start = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1, 0);

        // 로봇의 도착 지점
        st = new StringTokenizer(br.readLine());
        Point end = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
                Integer.parseInt(st.nextToken()) - 1, 0);

        bfs(start, end);
    }

    private static void bfs(Point start, Point end) {
        Queue<Point> q = new LinkedList<>();
        boolean[][][] visited = new boolean[M][N][4]; // 왼쪽, 오른쪽으로 회전시켜보기 때문에 3차원 배열 사용해서 각각의 방향도 같이 확인

        q.add(start);
        visited[start.x][start.y][start.dir] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            int x = cur.x;
            int y = cur.y;
            int dir = cur.dir;
            int cnt = cur.cnt;

            // 도착 지점에 도착하면 종료
            if (x == end.x && y == end.y && dir == end.dir) {
                System.out.println(cnt);
                return;
            }

            // 명령 1. Go k: k는 1, 2 또는 3일 수 있다. 현재 향하고 있는 방향으로 k칸 만큼 움직임
            for (int i = 1; i <= 3; i++) {
                int nx = x + dx[dir] * i;
                int ny = y + dy[dir] * i;

                // 범위를 벗어나지 않고, 궤도가 깔려있는 경우
                if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[nx][ny] == 0) {
                    // 방문하지 않았을 경우
                    if (!visited[nx][ny][dir]) {
                        q.add(new Point(nx, ny, dir, cnt + 1));
                        visited[nx][ny][dir] = true;
                    }
                } else {
                    break; // k칸을 지나가지 못하는 경우 다른 칸도 이동하지 못하기 때문에 break를 통해 for문 탈출
                }
            }

            // 명령 2. Turn dir: dir은 left 또는 right 이며, 각각 왼쪽 또는 오른쪽으로 90° 회전
            for (int i = 0; i < 4; i++) {
                // dir : 현재 방향, i : 회전시키려는 방향
                if (dir != i && !visited[x][y][i]) {
                    // 동 -> 서, 서 -> 동, 남 -> 북, 북 -> 남 (270도 회전하는 경우는 반대로 90도만 회전시키는 것과 같으므로 180도 회전하는 경우만 확인)
                    if ((dir == 0 && i == 1) || (dir == 1 && i == 0) || (dir == 2 && i == 3) || (dir == 3 && i == 2)) {
                        q.add(new Point(x, y, i, cnt + 2)); // 180도 회전하는 경우에는 명령 횟수 2 증가시킴
                    } else {
                        q.add(new Point(x, y, i, cnt + 1)); // 90도 회전만 하는 경우에는 1 증가
                    }

                    visited[x][y][i] = true;
                }
            }
        }
    }

}