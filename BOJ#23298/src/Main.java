/**
 * 23289 온풍기 안녕!
 * https://www.acmicpc.net/problem/23289
 * 
 * @author minchae
 * @date 2023. 7. 2.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Point {
        int x;
        int y;
        int dir;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.dir = -1;
        }

        public Point(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    // 우좌상하
    static int[] dx = { 0, 0, 0, -1, 1 };
    static int[] dy = { 0, 1, -1, 0, 0 };

    static int[][] spreadX = { {0, 0, 0}, { 0, -1, 1 }, { 0, -1, 1 }, { -1, -1, -1 }, { 1, 1, 1 } };
    static int[][] spreadY = { {0, 0, 0}, { 1, 1, 1 }, { -1, -1, -1 }, { 0, -1, 1 }, { 0, -1, 1 } };

    static int R, C, K;

    static int[][] map; // 온도 저장
    static int[][] plus;
    static boolean[][][][] wall; // 벽 정보 저장

    static ArrayList<Point> checkList = new ArrayList<>(); // 온도를 조사해야 하는 칸 저장
    static ArrayList<Point> heaterList = new ArrayList<>(); // 온풍기 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        wall = new boolean[R][C][R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < C; j++) {
                int info = Integer.parseInt(st.nextToken());

                /*
                 * 0: 빈 칸
                 * 1: 방향이 오른쪽인 온풍기가 있음
                 * 2: 방향이 왼쪽인 온풍기가 있음
                 * 3: 방향이 위인 온풍기가 있음
                 * 4: 방향이 아래인 온풍기가 있음
                 * 5: 온도를 조사해야 하는 칸
                 */

                if (info == 5) {
                    checkList.add(new Point(i, j));
                } else if (info > 0) {
                    heaterList.add(new Point(i, j, info));
                }
            }
        }

        int W = Integer.parseInt(br.readLine());

        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            // t가 0인 경우 (x, y)와 (x-1, y) 사이에 벽이 있는 것이고, 1인 경우에는 (x, y)와 (x, y+1) 사이에 벽이 있는 것
            if (t == 0) {
                wall[x][y][x - 1][y] = true;
                wall[x - 1][y][x][y] = true;
            } else {
                wall[x][y][x][y + 1] = true;
                wall[x][y + 1][x][y] = true;
            }
        }

        int answer = 0; // 구사과가 먹은 초콜릿 개수

        // 먹는 초콜릿의 개수가 100개를 넘어가거나 모든 칸의 온도가 K이상인 경우 테스트 종료
        while (answer < 101 && !checkMap()) {
            wind();
            control();
            answer++; // 4. 초콜릿을 하나 먹음
        }

        System.out.println(answer);

    }

    /*
     * 1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
     * 2. 온도가 조절됨 -> 맵 복사해서 사용
     * 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
     * 4. 초콜릿을 하나 먹는다.
     * 5. 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사. 모든 칸의 온도가 K이상이면 테스트를 중단하고, 아니면 1부터 다시 시작
     */

    // 1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
    private static void wind() {
        plus = new int[R][C];

        for (Point point : heaterList) {
            workHeater(point.x, point.y, point.dir);
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += plus[i][j];
            }
        }
    }

    // 하나의 온풍기 작동
    private static void workHeater(int x, int y, int dir) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        int t = 5;
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        q.add(new Point(nx, ny, 2));
        visited[nx][ny] = true;
        plus[nx][ny] += 5;

        while (!q.isEmpty()) {
            Point point = q.poll();

            x = point.x;
            y = point.y;
            dir = point.dir;

            if (dir > 5) {
                continue;
            }

            // 방향 3개만 확인
            for (int i = 0; i < 3; i++) {
                nx = x + spreadX[dir][i];
                ny = y + spreadY[dir][i];

                // 범위, 방문 확인
                if (nx < 0 || nx >= R || ny < 0 || ny >= C || visited[nx][ny]) {
                    continue;
                }

                // 현재 칸과 다음 이동할 칸 사이에 벽이 있는지 확인
                if (isWall(x, y, nx, ny, dir)) {
                    continue;
                }

                q.add(new Point(nx, ny, dir + 1));
                visited[nx][ny] = true;
                plus[nx][ny] += t - dir + 1;
            }
        }
    }

    // 온풍기가 가는 방향에 벽이 있는지 확인
    private static boolean isWall(int x, int y, int nx, int ny, int dir) {
        if (x == nx || y == ny) {
            // 직선 방향일 경우
            // return wall[x][y][nx][ny]; // 벽 한개만 보면됨

            if (wall[x][y][nx][ny]) {
                return true;
            }
        } else {
            // 대각선 방향일 경우
            if (dir == 0 || dir == 1) { // 온풍기 방향이 우좌
                // return wall[x][y][nx][y] || wall[nx][y][nx][ny];

                if (wall[x][y][nx][y] || wall[nx][y][nx][ny]) {
                    return true;
                }
            } else { // 온풍기 방향이 상하
                // return wall[x][y][x][ny] || wall[x][ny][nx][ny];

                if (wall[x][y][x][ny] || wall[x][ny][nx][ny]) {
                    return true;
                }
            }
        }

        return false;
    }

    /*
     * 모든 인접한 칸에 대해서, 온도가 높은 칸에서 낮은 칸으로 ⌊(두 칸의 온도의 차이)/4⌋만큼 온도가 조절된다.
     * 온도가 높은 칸은 이 값만큼 온도가 감소하고, 낮은 칸은 온도가 상승
     * 인접한 두 칸 사이에 벽이 있는 경우에는 온도가 조절되지 않는다. 이 과정은 모든 칸에 대해서 동시에 발생
     */

    // 2. 모든 칸 동시에 온도 조절
    private static void control() {
        plus = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    tempControl(i, j);
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] += plus[i][j];

                // 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
                if (i == 0 || i == R - 1 || j == 0 || j == C - 1) {
                    if (map[i][j] > 0) {
                        map[i][j] -= 1;
                    }
                }
            }
        }
    }

    // 특정 칸과 인접한 칸에 대해서 온도 조절
    private static void tempControl(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                continue;
            }

            // 인접한 칸 사이에 벽이 있는 경우
            if (wall[x][y][nx][ny]) {
                continue;
            }

            if (map[x][y] > map[nx][ny]) {
                int num = (map[x][y] - map[nx][ny]) / 4;
                plus[x][y] -= num;
                plus[nx][ny] += num;
            }
        }
    }

    // 5. 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사
    private static boolean checkMap() {
        for (Point point : checkList) {
            if (map[point.x][point.y] < K) {
                return false;
            }
        }

        return true;
    }

}