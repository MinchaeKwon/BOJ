/**
 * 1890 점프
 * https://www.acmicpc.net/problem/1890
 * 
 * @author minchae
 * @date 2022. 5. 24.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] board = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		long[][] dp = new long[N][N];
		
		dp[0][0] = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int next = board[i][j];
				
				// 가장 오른쪽 아래 칸에 도착한 경우
				if (next == 0) {
					break;
				}
				
				// 아래쪽으로 이동하는 경우
				if (i + next < N) {
					dp[i + next][j] += dp[i][j];
				}
				
				// 오른쪽으로 이동하는 경우
				if (j + next < N) {
					dp[i][j + next] += dp[i][j];
				}
			}
		}

		System.out.println(dp[N - 1][N - 1]);
	}

}
