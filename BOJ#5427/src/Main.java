/**
 * 5427 불
 * https://www.acmicpc.net/problem/5427
 * 
 * @author minchae
 * @date 2023. 8. 9.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
    int x;
    int y;
    int time;

    public Pos(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int w, h;
    static char[][] map; // '.': 빈 공간, '#': 벽, '@': 상근이의 시작 위치, '*': 불
    static Queue<Pos> fq; // 불이 있는 위치 저장
    static Pos start;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            w = Integer.parseInt(st.nextToken()); // 열의 크기
            h = Integer.parseInt(st.nextToken()); // 행의 크기

            map = new char[h][w]; 
            fq = new LinkedList<>(); 

            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();

                for (int j = 0; j < w; j++) {
                    if (map[i][j] == '@') {
                        start = new Pos(i, j, 0);
                        map[i][j] = '.';
                    } else if (map[i][j] == '*') {
                        fq.add(new Pos(i, j, 0));
                    }
                }
            }

            int result = bfs();
            System.out.println(result == -1 ? "IMPOSSIBLE" : result);
        }
    }

    private static int bfs() {
        Queue<Pos> q = new LinkedList<>(); // 상근이 위치 저장
        boolean[][] visited = new boolean[h][w];

        q.add(start);
        visited[start.x][start.y] = true;

        while (!q.isEmpty()) {
            spreadFire(); // 불이 옮겨진 칸 또는 이제 불이 붙으려는 칸으로는 이동할 수 없으므로 불을 먼저 퍼뜨림

            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pos cur = q.poll();

                int time = cur.time;

                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    // 범위 안에 있는 경우
                    if (checkRange(nx, ny)) {
                        // 아직 방문하지 않았고, 빈 공간인 경우
                        if (!visited[nx][ny] && map[nx][ny] == '.') {
                            q.add(new Pos(nx, ny, time + 1));
                            visited[nx][ny] = true;
                        }
                    } else {
                        return time + 1; // 범위를 벗어난 것은 빌딩을 탈출한 것 -> 탈출하는데 가장 빠른 시간 반환
                    }
                }
            }
        }

        return -1;
    }

    // 불 확산시킴
    private static void spreadFire() {
        int size = fq.size();
        for (int i = 0; i < size; i++) {
            Pos cur = fq.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                // 범위를 벗어나지 않고 빈 공간일 경우
                if (checkRange(nx, ny) && map[nx][ny] == '.') {
                    map[nx][ny] = '*';
                    fq.add(new Pos(nx, ny, 0));
                }
            }
        }
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < h && y >= 0 && y < w;
    }
}