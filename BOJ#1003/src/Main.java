/**
 * 1003 피보나치 함수
 * https://www.acmicpc.net/problem/1003
 * 
 * @author minchae
 * @date 2021.3.18
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[41][2];
		
		dp[0][0] = 1; // N이 0일때 0이 출력되는 개수 
		dp[0][1] = 0; // N이 0일때 1이 출력되는 개수 
		dp[1][0] = 0; // N이 1일때 0이 출력되는 개수 
		dp[1][1] = 1; // N이 1일때 1이 출력되는 개수 
		
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			
			for (int j = 2; j <= N; j++) {
				dp[j][0] = dp[j - 1][0] + dp[j - 2][0];
				dp[j][1] = dp[j - 1][1] + dp[j - 2][1];
			}
			
			System.out.println(dp[N][0] + " " + dp[N][1]);
		}
	}

}
