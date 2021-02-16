/**
 * 2225 - 합분해
 * https://www.acmicpc.net/problem/2225
 * 
 * @author Minchae Gwon
 * @date 2021.2.17
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[n + 1][k + 1];
		
		//k가 1일때 1로 초기화 -> k가 1인 경우에는 n이 되는 경우의 수는 무조건 1개이기 때문에 1로 초기화
		for (int i = 1; i <= n; i++) {
			dp[i][1] = 1;
		}
		
		//n이 1일때  i로 초기화 - ex) n이 1인 경우 k개로 합이 n이 되는 경우의 수는 k개이므로 i로 초기화 해야함
		for (int i = 1; i <= k; i++) {
			dp[1][i] = i;
		}
		
		for (int i = 2; i <= n; i++) {
			for (int j = 2; j <= k; j++) {
				dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 1000000000;
			}
		}
		
		System.out.println(dp[n][k]);
	}

}
