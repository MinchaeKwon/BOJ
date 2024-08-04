/**
 * 2502 떡 먹는 호랑이
 * https://www.acmicpc.net/problem/2502
 * 
 * @author minchae
 * @date 2024. 8. 2.
 */

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[D];
		
		// 모든 경우를 확인
		for (int i = 1; i <= K / 2; i++) {
			for (int j = i + 1; j < K; j++) {
				dp[0] = i;
				dp[1] = j;
				
				// 피보나치 수열 저장
				for (int k = 2; k < D; k++) {
					dp[k] = dp[k - 1] + dp[k - 2];
				}
				
				// 할머니가 넘어온 날의 떡의 개수가 A가 되는 경우
				if (dp[D - 1] == K) {
					System.out.println(dp[0]);
					System.out.println(dp[1]);
					
					System.exit(0);
				}
			}
		}

	}

}
