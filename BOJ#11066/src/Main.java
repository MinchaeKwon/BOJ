/**
 * 11066 파일 합치기
 * https://www.acmicpc.net/problem/11066
 * 
 * @author minchae
 * @date 2021. 7. 2.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			
			int[] file = new int[K + 1];
			int[] sum = new int[K + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {			
				file[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i - 1] + file[i];
			}
			
			// K + 1의 크기로 하면 밑의 for문에서 dp[k + 1][i]에서 예외가 발생하기 때문에 K + 1로 함
			int[][] dp = new int[K + 2][K + 2];
			
			for (int i = 2; i <= K; i++) {
				for (int j = i - 1; j > 0; j--) {
					dp[j][i] = Integer.MAX_VALUE;
					
					for (int k = j; k <= i; k++) {
						// dp[j][i]는 j번째부터 i번째까지 합치는데 드는 최소 비용
						dp[j][i] = Math.min(dp[j][i], dp[j][k] + dp[k + 1][i]);
					}
					
					// sum[i] - sum[j - 1]는 파일 i번째부터 j까지의 부분 합
					dp[j][i] += sum[i] - sum[j - 1];
				}
			}
			
			System.out.println(dp[1][K]);
		}

	}

}
