/**
 * 11722 가장 긴 감소하는 부분 수열
 * https://www.acmicpc.net/problem/11722
 * 
 * @author minchae
 * @date 2022. 4. 5.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N];
		int[] dp = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		for (int i = 0; i < N; i++) {			
			A[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}
		
		for (int i = N -1; i >= 0; i--) {
			for (int j = N - 1; j > i; j--) {
				if (A[j] < A[i] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}

}
