/**
 * 2294 동전2
 * https://www.acmicpc.net/problem/2294
 * 
 * @author minchae
 * @date 2022. 4. 29.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static final int INF = 10001;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] coin = new int[n];
		
		for (int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[k + 1];
		
		Arrays.fill(dp, INF);
		
		dp[0] = 0;
		for (int i = 0; i < n; i++) {
			for (int j = coin[i]; j <= k; j++) {
				dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1);
			}
		}
		
		System.out.println(dp[k] == INF ? -1 : dp[k]);
	}

}
