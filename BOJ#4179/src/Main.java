/**
 * 4179 불!
 * https://www.acmicpc.net/problem/4179
 * 
 * @author minchae
 * @date 2023. 8. 4.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Maze {
    int x;
    int y;
    int time;

    public Maze(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}

public class Main {

    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int R, C;
    static char[][] map; // #: 벽, .: 지나갈 수 있는 공간, J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간), F: 불이 난 공간

    static int sx, sy;
    static Queue<Maze> fq = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'J') {
                    sx = i;
                    sy = j;

										map[i][j] = '.';
                } else if (map[i][j] == 'F') {
                    fq.add(new Maze(i, j, 0));
                }
            }
        }

        int result = bfs();
        System.out.println(result == -1 ? "IMPOSSIBLE" : result);
    }

    private static int bfs() {
        Queue<Maze> q = new LinkedList<>(); // 지훈이가 이동할 수 있는 위치 저장
        boolean[][] visited = new boolean[R][C];

        q.add(new Maze(sx, sy, 0));
        visited[sx][sy] = true;

        while (!q.isEmpty()) {

            spreadFire(); // 불을 먼저 확산시킴 -> 지훈이를 먼저 이동시키고 불을 확산시키면 미로 탈출 불가능

            /*
             * 현재 큐에는 지훈이가 한 번 이동하는 위치의 후보들이 들어있기 때문에 반복문을 쓰지 않으면 후보들을 큐에서 한번씩 꺼낼 때마다 
             * 불이 확산되기 때문에 올바른 답을 얻을 수 없음 큐의 사이즈만큼 반복해야함
             */
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Maze cur = q.poll();

                int x = cur.x;
                int y = cur.y;
                int time = cur.time;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    // 범위를 벗어난 경우 -> 미로를 탈출 했다는 것
                    if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                        return time + 1; // 가장 빠른 탈출시간 반환
                    } else {
                        // 방문하지 않았고, 지나갈 수 있는 공간일 경우 ('J'가 있는 위치는 어차피 방문처리가 되어서 다시 방문될 일이 없기 때문에 고려 X)
                        if (!visited[nx][ny] && map[nx][ny] == '.') {
                            q.add(new Maze(nx, ny, time + 1));
                            visited[nx][ny] = true;
                        }
                    }
                }
            }

        }

        return -1;
    }

    // 불 확장시키는 함수
    private static void spreadFire() {
        int size = fq.size();
        for (int i = 0; i < size; i++) {
            Maze fire = fq.poll();

            for (int d = 0; d < 4; d++) {
                int nx = fire.x + dx[d];
                int ny = fire.y + dy[d];

                if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] == '.') {
                    map[nx][ny] = 'F';
                    fq.add(new Maze(nx, ny, 0));
                }
            }
        }
    }
}