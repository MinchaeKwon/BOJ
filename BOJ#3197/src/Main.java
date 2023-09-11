/**
 * 3197 백조의 호수
 * https://www.acmicpc.net/problem/3197
 * 
 * @author minchae
 * @date 2023. 8. 21.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Lake {
    int x;
    int y;

    public Lake(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int R, C;
    static char[][] map; // '.'은 물 공간, 'X'는 빙판 공간, 'L'은 백조가 있는 공간

    static ArrayList<Lake> swan = new ArrayList<>(); // 백조 위치 저장
    static Queue<Lake> wq = new LinkedList<>(); // 물 위치 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'L') {
                    swan.add(new Lake(i, j));
                }

                if (map[i][j] != 'X') { // 백조가 있는 곳도 물이기 때문에 빙판이 아닌 경우 물로 봐야함
                    wq.add(new Lake(i, j));
                }
            }
        }

        System.out.println(bfs());
    }

    // 백조가 며칠 지나면 만날 수 있는지 계산
    private static int bfs() {
        Queue<Lake> sq = new LinkedList<>(); // 백조가 이동할 수 있는 칸
        boolean[][] visited = new boolean[R][C];

        // 첫 번째 백조가 있는 칸을 넣어줌
        sq.add(new Lake(swan.get(0).x, swan.get(0).y));
        visited[swan.get(0).x][swan.get(0).y] = true;

        int day = 0;

        while (true) {
            Queue<Lake> nq = new LinkedList<>(); // 다음 날 백조가 탐색할 칸

            while (!sq.isEmpty()) {
                Lake cur = sq.poll();

                // 백조가 서로 만나면 종료
                if (cur.x == swan.get(1).x && cur.y == swan.get(1).y) {
                    return day;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + dx[i];
                    int ny = cur.y + dy[i];

                    if (checkRange(nx, ny) && !visited[nx][ny]) {
                        visited[nx][ny] = true;

                        if (map[nx][ny] == 'X') { // 물에 인접한 빙판이기 때문에 녹게 되기 때문에 탐색할 칸에 넣어줌
                            nq.add(new Lake(nx, ny));
                        } else {
                            sq.add(new Lake(nx, ny)); // 물이어서 이동할 수 있는 경우 백조가 이동할 수 있는 칸에 넣어줌
                        }
                    }
                }
            }

            sq = nq; // 다음 날 탐색할 큐를 넣어줌

            meltingIce();

            day++; // 날짜 증가
        }

    }

    // 빙판 녹이기
    private static void meltingIce() {
        int size = wq.size();

        for (int i = 0; i < size; i++) {
            Lake cur = wq.poll();

            // 상하좌우로 인접한 빙판 녹임
            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (checkRange(nx, ny) && map[nx][ny] == 'X') {
                    map[nx][ny] = '.';
                    wq.add(new Lake(nx, ny));
                }
            }
        }
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}