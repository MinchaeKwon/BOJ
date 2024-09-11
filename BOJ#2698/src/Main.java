/**
 * 2698 인접한 비트의 개수
 * https://www.acmicpc.net/problem/2698
 * 
 * @author minchae
 * @date 2024. 9. 11.
 * */

import java.util.*;
import java.io.*;

// 맨 뒤 비트가 0인지 1인지에 따라 개수 다름
// 0이면 개수 증가 X, 1이면 개수 1 증가
// 수열 크기, 인접 비트 개수, 0 or 1 비트 값 -> 3차원 배열 사용
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		int[][][] dp = new int[101][101][2];
		
		// 인접한 비트 개수 k개, n번째 비트가 0 or 1
		// dp[n][k][0] = dp[n - 1][k][0] + dp[n - 1][k][1]
		// dp[n][k][1] = dp[n - 1][k - 1][1] + dp[n - 1][k][0]
		
		dp[1][0][0] = 1; // 수열 길이 1, 아직 인접 비트 없음, 뒤에 0인 경우 -> 개수 0
		dp[1][0][1] = 1; // 수열 길이 1, 아직 인접 비트 없음, 뒤에 1인 경우 -> 개수 1
		
		for (int k = 0; k < 101; k++) {
			for (int n = 2; n < 101; n++) {
				// 마지막에 1이 오는 경우
				dp[n][k][1] += dp[n - 1][k][0]; // 인접 비트 개수 그대로 가져감, n-1번째 인접비트가 0인 경우를 더함
				
				// 인접 비트 개수를 1 증가시킴
				if (k > 0) {
					dp[n][k][1] += dp[n - 1][k - 1][1];
				} 
				
				// 마지막에 0이 오는 경우 : n-1번째 비트가 0인 경우와 1인 경우를 더함
				dp[n][k][0] += dp[n - 1][k][0] + dp[n - 1][k][1];
			}
		}
		
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			// 마지막에 0이 오는 경우와 1이 오는 경우를 더함
			System.out.println(dp[n][k][0] + dp[n][k][1]);
		}
	}

}
