/**
 * 3085 사탕 게임
 * https://www.acmicpc.net/problem/3085
 * 
 * @author minchae
 * @date 2023. 7. 24.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static char[][] map; // 빨간색은 C, 파란색은 P, 초록색은 Z, 노란색은 Y

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        changeColor();

        System.out.println(result);
    }

    // 색 교환
    private static void changeColor() {
        // 가로로 인접한 두 칸의 색 교환
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                swap(i, j, i, j + 1);
                checkMaxCandy();
                swap(i, j, i, j + 1); // 교환한 색 원복

            }
        }

        // 세로로 인접한 두 칸의 색 교환
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                swap(j, i, j + 1, i);
                checkMaxCandy();
                swap(j, i, j + 1, i); // 교환한 색 원복
            }
        }
    }

    // 행과 열에 연속된 사탕이 최대 몇 개 연속되는지 확인
    private static void checkMaxCandy() {
        // 행에 연속된 사탕 개수 확인
        for (int i = 0; i < N; i++) {
            int cnt = 1;

            for (int j = 0; j < N - 1; j++) {
                if (map[i][j] == map[i][j + 1]) {
                    cnt++;
                    result = Math.max(result, cnt);
                } else {
                    cnt = 1;
                }
            }
        }

        // 열에 연속된 사탕 개수 확인
        for (int i = 0; i < N; i++) {
            int cnt = 1;

            for (int j = 0; j < N - 1; j++) {
                if (map[j][i] == map[j + 1][i]) {
                    cnt++;
                    result = Math.max(result, cnt);
                } else {
                    cnt = 1;
                }
            }
        }
    }

    // 값 교환
    private static void swap(int x1, int y1, int x2, int y2) {
        char tmp = map[x1][y1];
        map[x1][y1] = map[x2][y2];
        map[x2][y2] = tmp;
    }
}