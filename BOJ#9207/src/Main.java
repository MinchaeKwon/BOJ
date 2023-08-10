/**
 * 9207 페그 솔리테어
 * https://www.acmicpc.net/problem/9207
 * 
 * @author minchae
 * @date 2023. 8. 11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static char[][] map; // '.'는 빈 칸, 'o'는 핀이 꽂혀있는 칸, '#'는 구멍이 없는 칸

    static int remain; // 남아있는 핀의 개수
    static int move; // 이동횟수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            map = new char[5][9];
            int pin = 0;

            for (int i = 0; i < 5; i++) {
                map[i] = br.readLine().toCharArray();

                for (int j = 0; j < 9; j++) {
                    if (map[i][j] == 'o') {
                        pin++;
                    }
                }
            }

            remain = pin;

            // 브루트포스
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 9; j++) {
                    if (map[i][j] == 'o') {
                        backtracking(i, j, pin, 0);
                    }
                }
            }

            System.out.println(remain + " " + move);

            br.readLine();
        }
    }

    private static void backtracking(int x, int y, int pin, int depth) {
        // 게임판에 남아있는 핀의 개수를 최소로 하기 위해 최솟값 갱신
        if (pin <= remain) {
            remain = pin;
            move = depth;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 범위 벗어나지 않고, 인접한 칸에 핀이 있는 경우
            if (checkRange(nx, ny) && map[nx][ny] == 'o') {
                // 인접한 핀의 다음 칸이 비어있는지 확인 -> 핀 이동 시키고 인접한 핀 제거
                int nnx = nx + dx[d];
                int nny = ny + dy[d];

                if (checkRange(nnx, nny) && map[nnx][nny] == '.') {
                    map[x][y] = '.';
                    map[nx][ny] = '.';
                    map[nnx][nny] = 'o';

                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 9; j++) {
                            if (map[i][j] == 'o') {
                                backtracking(i, j, pin - 1, depth + 1);
                            }
                        }
                    }

                    // 원복
                    map[x][y] = 'o';
                    map[nx][ny] = 'o';
                    map[nnx][nny] = '.';
                }
            }
        }
    }

    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < 5 && y >= 0 && y < 9;
    }
}