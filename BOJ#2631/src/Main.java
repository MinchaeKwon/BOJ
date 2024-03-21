/**
 * 2631 줄 세우기
 * https://www.acmicpc.net/problem/2631
 * 
 * @author minchae
 * @date 2024. 3. 21.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static int[] arr;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		dp = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			dp[i] = 1;
		}
		
		int max = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(N - max);
	}

}
