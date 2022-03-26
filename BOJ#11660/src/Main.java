/**
 * 11660 구간 합 구하기 5
 * https://www.acmicpc.net/problem/11660
 * 
 * @author minchae
 * @date 2022. 3. 26.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] dp = new int[N + 1][N + 1];
		
		// dp[i][j] = (1, 1)에서 (i, j)까지의 합
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 현재 위치의 위쪽 행까지의  합 + 현재 위치의 왼쪽 열까지의 합 - 현재 위치의 행과 열을 제외한 부분의 합(중복되는 값) + 현재 위치 값
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + map[i][j];
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			// (1, 1)부터 (x2, y2)까지의 합에서 해당하지 않는 부분 빼기
			// (dp[x1 - 1][y1 - 1]를 해주는 이유는 (-dp[x2][y1 - 1] -dp[x1 - 1][y2])를 통해 dp[x1 - 1][y1 - 1]이 두 번 빠졌기 때문)
			System.out.println(dp[x2][y2] - dp[x2][y1 - 1] - dp[x1 - 1][y2] + dp[x1 - 1][y1 - 1]);
		}
	}

}
