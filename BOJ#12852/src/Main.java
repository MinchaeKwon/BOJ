/**
 * 1로 만들기 2
 * https://www.acmicpc.net/problem/12852
 * 
 * @author minchae
 * @date 2022. 4. 19.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N + 1];
		int[] result = new int[N + 1];
		
		// n-1 또는 n/2 또는 n/3 를 했을 때 가장 작은 값이 답 -> (dp[n - 1] + 1), (dp[n / 2] + 1), (dp[n / 3] + 1) 중 최소값을 찾는 것 -> dp[2]부터 하면 됨
		// 0과 1은 어차피 연산 횟수가 0이기 때문에 2부터 시작함
		for(int i = 2; i <= N; i++) {
			
			// 먼저 n-1한 것을 dp[i]에 넣어놓고 밑에 if문에서 최소값 비교하면 됨
			dp[i] = dp[i - 1] + 1; // 연산 한 번 실행했으니까 +1 해줌
			result[i] = i - 1;
			
			// 2로 나누어 떨어지고 (n - 1) 한 것보다 작은 경우
			if(i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
				dp[i] = dp[i / 2] + 1;
				result[i] = i / 2;
			}
			
			// 3으로 나누어 떨어지고 (n - 1) 한 것보다 작은 경우
			if(i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
				dp[i] = dp[i / 3] + 1;
				result[i] = i / 3;
			}
		}
		
		System.out.println(dp[N]);
		
		while (N > 0) {
			System.out.print(N + " ");
			N = result[N];
		}
	}

}
