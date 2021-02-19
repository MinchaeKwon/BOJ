/**
 * 3067 - coins
 * https://www.acmicpc.net/problem/3067
 * 
 * @author Minchae Gwon
 * @date 2021.2.20
 * 
 * 9084 동전과 똑같은 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(br.readLine());
			
			int[] coin = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				coin[j] = Integer.parseInt(st.nextToken());
			}
			
			int M = Integer.parseInt(br.readLine());
			
			//1원부터 M원까지이기 때문에 M + 1 크기로 함
			int[] dp = new int[M + 1];
			//만드려는 금액과 동전의 금액이 같을 경우에 dp[k - coin[j]]을 했을 때 dp[0]이 나올 수 있는데 이때는 무조건 M원을 만들 수 있으므로 0번째 인덱스가 1이여야함
			dp[0] = 1;
			
			for (int j = 0; j < N; j++) {
				//금액이 작은 동전부터 dp배열을 채워넣음, 특정 동전 금액보다 작은 경우는 고려할 필요가 없기 때문에 coin[j]를 시작으로 함
				for (int k = coin[j]; k <= M; k++) {
					dp[k] += dp[k - coin[j]];
				}
			}
			
			System.out.println(dp[M]);
		}
		

	}

}
