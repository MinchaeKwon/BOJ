/**
 * 25682 체스판 다시 칠하기 2
 * https://www.acmicpc.net/problem/25682
 * 
 * @author minchae
 * @date 2024. 8. 10.
 */

import java.io.*;
import java.util.*;

public class Main {

	static int N, M, K;
	static char[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		board = new char[N][M];

		for (int i = 0; i < N; i++) {
			board[i] = br.readLine().toCharArray();
		}

		int[][] black = getSumBoard('B');
		int[][] white = getSumBoard('W');
		
		System.out.println(Math.min(cut(black), cut(white)));
	}

	// 특정 색으로 시작할 때의 색을 변경해야 하는 누적합 구하기
	private static int[][] getSumBoard(char color) {
		int[][] tmp = new int[N + 1][M + 1];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int cur = 0; // 색을 바꾸는 경우 1, 아니면 0 저장

				// 색이 번갈아 가면서 칠해져야 하기 때문에 (i + j) % 2를 확인
				if ((i + j) % 2 == 0) {
					cur = board[i][j] == color ? 0 : 1;
				} else {
					cur = board[i][j] != color ? 0 : 1;
				}

				// 이전 행의 누적합 + 이전 열의 누적합 - 중복 값 + 현재 칸의 값
				tmp[i + 1][j + 1] = tmp[i + 1][j] + tmp[i][j + 1] - tmp[i][j] + cur;
			}
		}
		return tmp;
	}
	
	// K * K로 잘랐을 때의 최솟값 구하기
	private static int cut(int[][] sum) {
		int result = Integer.MAX_VALUE;
		
		for (int i = K; i <= N; i++) {
			for (int j = K; j <= M; j++) {
				// K * K로 잘랐을 때 [i][j] 위치 누적합 - (이전 행의 누적합 + 이전 열의 누적합 - 중복값)
				int num = sum[i][j] - (sum[i - K][j] + sum[i][j - K] - sum[i - K][j - K]);
				result = Math.min(result, num);
			}
		}
		
		return result;
	}

}
