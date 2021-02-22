/**
 * 2579 - 계단 오르기
 * https://www.acmicpc.net/problem/2579
 * 
 * @author Minchae Gwon
 * @date 2021.2.23
 * 
 * 입력
 * 입력의 첫째 줄에 계단의 개수가 주어진다.
 * 둘째 줄부터 한 줄에 하나씩 제일 아래에 놓인 계단부터 순서대로 각 계단에 쓰여 있는 점수가 주어진다.
 * 계단의 개수는 300이하의 자연수이고, 계단에 쓰여 있는 점수는 10,000이하의 자연수이다.
 * 
 * 출력
 * 첫째 줄에 계단 오르기 게임에서 얻을 수 있는 총 점수의 최댓값을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] stairs = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			stairs[i] = Integer.parseInt(br.readLine());
		}
		
		int[] dp = new int[n + 1];
		
		dp[1] = stairs[1]; //dp[1]을 첫번째 계단의 점수로 초기화
		
		//n이 1이라면 dp[2]는 구해줄 필요가 없으므로 2이상일때부터 계산함
		if (n > 1) {
			dp[2] = Math.max(dp[1] + stairs[2], stairs[2]); //두칸을 건너뛸 수 있어서 점수를 계산할 때 n - 3까지 고려하므로 dp[2]까지는 미리 계산해놓음	
		}
		
		//위에서 dp[2]까지 계산했기 때문에 i는 3부터 시작함
		for (int i = 3; i <= n; i++) {
			/* 계단을 밟을 수 있는 경우의 수는 두가지임
			 * dp[i - 3] + stairs[i - 1]는 이전에 한칸을 밟은 경우로  i - 2를 밟아버리면 세칸을 연속해서 밟는 것이므로  i - 3과 i를 무조건 더해야 함
			 * dp[i - 2]는 두칸을 밟은 경우이므로 i - 1은 밟을 수 없고 세칸을 건너뛸 수 없으므로 i번째는 무조건 밟아야함
			 */
			dp[i] = Math.max(dp[i - 3] + stairs[i - 1], dp[i - 2]) + stairs[i];
		}
		
		System.out.println(dp[n]);
	}

}
