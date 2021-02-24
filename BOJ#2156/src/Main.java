/**
 * 2156 - 포도주 시식
 * https://www.acmicpc.net/problem/2156
 * 
 * @author Minchae Gwon
 * @date 2021.2.24
 * 
 * 입력
 * 첫째 줄에 포도주 잔의 개수 n이 주어진다. (1≤n≤10,000) 둘째 줄부터 n+1번째 줄까지 포도주 잔에 들어있는 포도주의 양이 순서대로 주어진다. 포도주의 양은 1,000 이하의 음이 아닌 정수이다.
 * 
 * 출력
 * 첫째 줄에 최대로 마실 수 있는 포도주의 양을 출력한다.
 * 
 * 2579 계단 오르기 문제와 비슷함
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] wine = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[n + 1];

		dp[1] = wine[1];
		
		//n이 1일수도 있기때문에 예외 처리
		if (n > 1) {
			dp[2] = wine[1] + wine[2];
		}
		
		//dp[2]까지 구해놓았기 때문에 i는 3부터 시작
		for (int i = 3; i <= n; i++) {
			/*
			 * 1. i번째 포도주는 마시지 않는 경우(계단 오르기 문제처럼 마지막 계단을 무조건 밟아야 한다는 조건이 없으므로 이 경우를 고려함) -> dp[i - 1]
			 * 2. i번째 포도잔을 1번 연속으로 마시는 경우에는 (i - 1)번째 포도주는 마시면 안됨 -> dp[i - 2] + wine[i]
			 * 3. i번째 포도잔을 2번 연속으로 마시는 경우에는 (i - 2)번째 포도주는 마시면 안됨
			 * (만약 마셨는데 i번째를 마셔버리면 3번 연속 마시는 것이기 때문에 조건 충족 X) -> dp[i - 3] + wine[i - 1] + wine[i]
			 */
			dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2], dp[i - 3] + wine[i - 1]) + wine[i]);
		}
		
		System.out.println(dp[n]);
	}

}
