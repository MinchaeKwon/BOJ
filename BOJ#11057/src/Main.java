/**
 * 11057 - 오르막 수
 * acmicpc.net/problem/11057
 * 
 * @author Minchae Gwon
 * @date 2021.2.22
 * 
 * 문제
 * 오르막 수는 수의 자리가 오름차순을 이루는 수를 말한다. 이때, 인접한 수가 같아도 오름차순으로 친다.
 * 예를 들어, 2234와 3678, 11119는 오르막 수이지만, 2232, 3676, 91111은 오르막 수가 아니다.
 * 수의 길이 N이 주어졌을 때, 오르막 수의 개수를 구하는 프로그램을 작성하시오. 수는 0으로 시작할 수 있다.
 * 
 * 입력
 * 첫째 줄에 N (1 ≤ N ≤ 1,000)이 주어진다.
 * 
 * 출력
 * 첫째 줄에 길이가 N인 오르막 수의 개수를 10,007로 나눈 나머지를 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		int[][] dp = new int[n + 1][10]; //[n개의 자릿수][맨 끝에 올 수 있는 숫자의 개수(0 ~ 9)]
		
		//n이 1인 경우에는 오르막 수를 만들 수 있는 경우의 수는 한가지밖에 없으므로 1로 초기화
		for (int i = 0; i < 10; i++) {
			dp[1][i] = 1;
		}
		
		//dp[1][0 ~ 9]에는 이미 값이 들어있으므로 i는 2부터 시작함
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < 10; j++) {
				if (i == 1) {
					dp[1][i] = 1;
				} else {
					//3중 포문을 사용할 경우 - 표를 그려보면 dp[i - 1][k]를 더하면 dp[i][j]가 되는 것을 알 수 있음
					/* 3중 포문으로 k를 추가하는 이유: 예를 들어 i번째 자리수에 8이라는 숫자가 있다면 i-1번째 자리수에는 0~8까지의 숫자만 올 수 있기 때문
					 * dp[i][j]에 dp[i - 1][0 ~ 8]을 더하면 길이가 i인 오르막 수를 구할 수 있음 */
					for (int k = 0; k <= j; k++) {
						//위의 이유로 dp[i][j]에 dp[i - 1][k]를 더하는 것임
						dp[i][j] += dp[i - 1][k];
						dp[i][j] %= 10007;
					}
					
					//3중 포문을 사용하지 않을 경우 - 표를 그려보면 dp[i - 1][j] + dp[i][j - 1]가 dp[i][j]가 되는 것을 확인 할 수 있음
//					if (j == 0) {
//						//j가 0이라는 것을 끝자리가 0인 것인데 이때는 오르막 수가 무조건 한개임 -> ex) 0, 00, 000 등등
//						dp[i][j] = dp[i - 1][j];
//					} else {
//						dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
//					}
//					dp[i][j] %= 10007;
				}
				
			}
		}
		
		int result = 0;
		for (int i = 0; i < 10; i++) {
			result += dp[n][i]; //n번째 행에 들어있는 오르막 수를 다 더해야 길이가 n인 오르막 수의 개수를 구할 수 있음
		}
		
		System.out.println(result % 10007);
		
	}

}
