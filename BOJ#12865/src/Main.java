/**
 * 12865 - 평범한 배낭
 * https://www.acmicpc.net/problem/12865
 * 
 * @author Minchae Gwon
 * @date 2021.2.15
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
		
		int[] w = new int[n + 1]; //무게 배열
		int[] v = new int[n + 1]; //가치 배열
		int[][] dp = new int[n + 1][k + 1];
		
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			
			w[i] = Integer.parseInt(st.nextToken());
			v[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= k; j++) {
				//가방에 i번째 물건이 들어갈 수 있는 경우 i번째 물건을 안넣을 경우(i - 1까지의 물건을 넣었을 때)와 i번째 물건을 넣었을 경우 둘중에서 큰 값이 최대 가치합
				if (w[i] <= j) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
				}
				else { //가방에 i번째 물건이 들어갈 수 없는 경우 i - 1까지의 물건을 넣었을 때의 가치가 최대 가치합
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		
		System.out.println(dp[n][k]);

	}

}
