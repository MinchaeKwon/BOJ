/**
 * 1018 체스판 다시 칠하기
 * https://www.acmicpc.net/problem/1018
 * 
 * @author minchae
 * @date 2023. 7. 4.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean[][] board; // 'W'(흰색) true, 'B'(검은색) false

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < M; j++) {
                board[i][j] = input.charAt(j) == 'W' ? true : false;
            }
        }

        // 8 x 8 크기로 자름
        for (int i = 0; i < N - 7; i++) {
            for (int j = 0; j < M - 7; j++) {
                solution(i, j);
            }
        }

        System.out.println(answer);
    }

    private static void solution(int x, int y) {
        int cnt = 0;

        boolean color = board[x][y]; // 첫 번째 칸의 색

        for (int i = x; i < x + 8; i++) {
            for (int j = y; j < y + 8; j++) {
                // 올바른 색이 아닐 경우 (이전 칸이 흰색이었을 때 또 흰색이 나온 경우) -> 체스판을 색칠해야 하므로 cnt 증가
                if (board[i][j] != color) {
                    cnt++;
                }

                color = !color; // 다음 칸은 현재 칸과 색이 반대이므로 반대값을 넣어줌
            }

            color = !color; // 행이 바뀌면서 다음 행의 첫 번째 칸은 색이 다르므로 반대값을 넣어줌
        }

        // 첫 번째 칸의 색을 기준으로 할 때 색칠하는 개수, 그 반대의 색으로 색칠하는 개수 중에서 더 작은 값 찾음
        cnt = Math.min(cnt, 64 - cnt);

        answer = Math.min(answer, cnt); // 최솟값 갱신
    }

}