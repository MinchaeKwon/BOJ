/**
 * 3055 탈출
 * https://www.acmicpc.net/problem/3055
 * 
 * @author minchae
 * @date 2023. 7. 22.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

class Pos {
    int x;
    int y;
    int time;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Pos(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}

public class Main {

    // 상하좌우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int R, C;
    static char[][] map; // 비어있는 곳은 '.', 물이 차있는 지역은 '*', 돌은 'X', 비버의 굴은 'D', 고슴도치의 위치는 'S'

    static Pos start; // 고슴도치 위치

    static Queue<Pos> hq = new LinkedList<>(); // 고슴도치 저장
    static Queue<Pos> wq = new LinkedList<>(); // 물 저장

    static int result = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'S') {
                    hq.add(new Pos(i, j, 0));
                } else if (map[i][j] == '*') {
                    wq.add(new Pos(i, j));
                }
            }
        }

        move();
        System.out.println(result == Integer.MAX_VALUE ? "KAKTUS" : result);
    }
    

    // 고슴도치 이동시키는 함수
    private static void move() {
        // 고슴도치가 더 이상 이동할 수 없을 때까지 반복
        while (!hq.isEmpty()) {
            flowWater(); // 물 먼저 확장 (고슴도치 먼저 이동하면 고슴도치가 이동한 칸에 물이 확장될 수 있기 때문)

            // 고슴도치 위치 후보 개수만큼 반복
            int len = hq.size(); // 사이즈 미리 저장하지 않으면 반복문 범위가 이상해짐

            for (int i = 0; i < len; i++) {
                Pos cur = hq.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                        if (map[nx][ny] == '.') {
                            map[nx][ny] = 'S';
                            hq.add(new Pos(nx, ny, cur.time + 1));
                        } else if (map[nx][ny] == 'D') {
                            result = Math.min(result, cur.time + 1);
                            return;
                        }
                    }
                }
            }
        }
    }

    // 물 확장시키는 함수
    private static void flowWater() {
        // 물이 있는 개수만큼 반복
        int len = wq.size();
        for (int i = 0; i < len; i++) {
            Pos cur = wq.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                // 범위 벗어나지 않고 빈 칸인 경우
                if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] == '.') {
                    map[nx][ny] = '*';
                    wq.add(new Pos(nx, ny));
                }
            }
        }

    }
}