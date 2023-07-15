/**
 * 1938 통나무 옮기기
 * https://www.acmicpc.net/problem/1938
 * 
 * @author minchae
 * @date 2023. 7. 16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Point {
    int x;
    int y;
    int widthYn; // 1이면 가로, 0이면 세로
    int cnt;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y, int widthYn, int cnt) {
        this.x = x;
        this.y = y;
        this.widthYn = widthYn;
        this.cnt = cnt;
    }
}

public class Main {

    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int N;
    static char[][] map; // 1은 아직 잘려지지 않은 나무, 0은 아무 것도 없음

    // 통나무의 중간값 저장
    static Point start;
    static Point end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        boolean sFlag = true;
        boolean eFlag = true;

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'B') {
                    if (start == null) { // 통나무의 처음을 만난 경우
                        start = new Point(i, j);
                    } else if (sFlag) {
                        start.widthYn = start.x == i ? 1 : 0; // 첫 번째 행의 값이 두 번째 행의 값과 같다면 가로 방향, 아니면 세로 방향
                        start.x = i;
                        start.y = j;

                        sFlag = false; // 통나무 중간값 저장하면 탐색 그만하도록 false 넣어줌
                    }
                } else if (map[i][j] == 'E') {
                    if (end == null) { // 통나무의 처음을 만난 경우
                        end = new Point(i, j);
                    } else if (eFlag) {
                        end.widthYn = end.x == i ? 1 : 0; // 첫 번째 행의 값이 두 번째 행의 값과 같다면 가로 방향, 아니면 세로 방향
                        end.x = i;
                        end.y = j;

                        eFlag = false; // 통나무 중간값 저장하면 탐색 그만하도록 false 넣어줌
                    }
                }
            }
        }

        System.out.println(bfs());
    }

    // 최소 동작 횟수 구함
    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][N][2]; // 통나무가 가로/세로일 경우를 봐야하기 때문에 3차원 배열 사용

        q.add(start);
        visited[start.x][start.y][start.widthYn] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            int x = cur.x;
            int y = cur.y;
            int widthYn = cur.widthYn;
            int cnt = cur.cnt;

            // 통나무 중간이 목적지 중간에 도착하고 방향이 같은 경우
            if (x == end.x && y == end.y && widthYn == end.widthYn) {
                return cnt;
            }

            // 상하좌우 탐색 방법 1
            // for (int i = 0; i < 4; i++) {
            //     int nx = x + dx[i];
            //     int ny = y + dy[i];

            //     // 통나무가 이동 가능하고, 방문하지 않은 경우
            //     if (checkMove(nx, ny, widthYn, i) && !visited[nx][ny][widthYn]) {
            //         q.add(new Point(nx, ny, widthYn, cnt + 1));
            //         visited[nx][ny][widthYn] = true;
            //     }
            // }

            // 상하좌우 탐색 방법 2 -> checkMove 함수에서 공통되는 부분을 묶은 것
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 통나무가 범위 안에 있고, 이동 가능하고, 방문하지 않은 경우
                if (isRange(nx, ny, widthYn) && isMove(nx, ny, widthYn) &&
                        !visited[nx][ny][widthYn]) {
                    q.add(new Point(nx, ny, widthYn, cnt + 1));
                    visited[nx][ny][widthYn] = true;
                }
            }

            // 방향 회전 가능한지 확인
            if (checkRotate(x, y)) {
                int change = widthYn == 1 ? 0 : 1;

                // 바꾼 방향으로 방문하지 않았을 경우
                if (!visited[x][y][change]) {
                    q.add(new Point(x, y, change, cnt + 1));
                    visited[x][y][change] = true;
                }
            }
        }

        return 0;
    }

    // 통나무가 상하좌우로 이동할 수 있는지 확인
    private static boolean checkMove(int x, int y, int widthYn, int dir) {
        if (widthYn == 1) { // 가로방향
            if (dir < 2) { // 상하
                // 상하로 움직이니까 행만 확인
                if (x < 0 || x >= N || map[x][y] == '1' || map[x][y - 1] == '1' || map[x][y + 1] == '1') {
                    return false;
                }
            } else { // 좌우
                // 좌우로 움직이니까 열만 확인 -> 양끝 좌우가 범위를 벗어나는지 확인
                if (y - 1 < 0 || y + 1 >= N || map[x][y] == '1' || map[x][y - 1] == '1' || map[x][y + 1] == '1') {
                    return false;
                }
            }
        } else { // 세로방향
            if (dir < 2) { // 상하
                // 상하로 움직이니까 행만 확인 -> 위아래가 범위를 벗어나는지 확인
                if (x - 1 < 0 || x + 1 >= N || map[x][y] == '1' || map[x - 1][y] == '1' || map[x + 1][y] == '1') {
                    return false;
                }
            } else { // 좌우
                // 좌우로 움직이니까 열만 확인
                if (y < 0 || y >= N || map[x][y] == '1' || map[x - 1][y] == '1' || map[x + 1][y] == '1') {
                    return false;
                }
            }
        }

        return true;
    }

    // // 통나무가 범위 안에 있는지 확인
    private static boolean isRange(int x, int y, int widthYn) {
        if (widthYn == 1) {
            return x >= 0 && x < N && y - 1 >= 0 && y + 1 < N;
        } else {
            return x - 1 >= 0 && x + 1 < N && y >= 0 && y < N;
        }
    }

    // 통나무가 이동가능한지 확인
    private static boolean isMove(int x, int y, int widthYn) {
        // 통나무가 3개의 칸을 쓰니까 -1, 0, 1 확인
        for (int i = -1; i <= 1; i++) {
            if (widthYn == 1 && map[x][y + i] == '1') {
                return false;
            } else if (widthYn == 0 && map[x + i][y] == '1') {
                return false;
            }
        }

        return true;
    }

    // // 통나무를 90도 회전시킬 수 있는지 확인
    private static boolean checkRotate(int x, int y) {
        // 통나무 중간을 중심으로 주변 8방향 탐색
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                // 범위를 벗어나거나, 나무가 있는 경우 회전시킬 수 없음
                if (i < 0 || i >= N || j < 0 || j >= N || map[i][j] == '1') {
                    return false;
                }
            }
        }

        return true;
    }

}