/**
 * 2705 팰린드롬 파티션
 * https://www.acmicpc.net/problem/2705
 * 
 * @author minchae
 * @date 2024. 7. 18.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	// 1 <= N <= 1000
	static int[] dp = new int[1001]; // 합이 n이 되는 팰린드롬 개수 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		dp[1] = 1;
		
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			
			System.out.println(getCnt(N));
		}
	}

	// 가운데 숫자를 기준으로 양쪽으로 갈라지는 수에 대해 재귀 호출해서 개수 구함
	// 1부터 시작해서 n/2까지의 개수 구함
	// dp[n] => 합이 n인 재귀적인 팰린드롬 개수
	private static int getCnt(int num) {
		// 팰린드롬 개수를 이미 구한 경우 바로 개수 반환
		if (dp[num] != 0) {
			return dp[num];
		}
		
		int cnt = 0;
		
		for (int i = 1; i <= num / 2; i++) {
			cnt += getCnt(i);
		}
		
		dp[num] = cnt + 1;
		
		return cnt + 1;
	}
	
}
