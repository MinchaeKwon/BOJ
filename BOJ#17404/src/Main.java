/**
 * 17404 RGB 거리 2
 * https://www.acmicpc.net/problem/17404
 * 
 * @author minchae
 * @date 2021.3.18
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] cost = new int[N][3];
		int[][] dp = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken()); // 0 빨강, 1 초록, 2 파랑 
			}
			
		}
		
		int result = Integer.MAX_VALUE;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// 첫 번째 집을 특정 색깔로 고정시킴 
				if (j == i) {
					dp[0][j] = cost[0][j];
				} else { // 나머지 집은 최소값을 구할 수 없도록 큰 값을 넣어줌 -> 집을 칠하는 비용의 범위가 0~999이므로 1000을 넣어줌 
					dp[0][j] = 1000;
				}
			}
			
			for (int j = 1; j < N; j++) {
				dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + cost[j][0];
				dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + cost[j][1];
				dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + cost[j][2];
			}
			
			for (int j = 0; j < 3; j++) {
				// 첫 번째 집과 마지막 집은 색이 달라야하므로 특정 색깔과 다른 나머지 색깔 중에서 더 작은 값을 구함
				if (j != i) {
					result = Math.min(result, dp[N - 1][j]);
				}
			}
			
		}
		
		System.out.println(result);

	}

}
