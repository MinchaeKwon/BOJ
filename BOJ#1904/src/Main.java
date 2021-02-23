/**
 * 1904 - 01타일
 * https://www.acmicpc.net/problem/1904
 * 
 * @author Minchae Gwon
 * @date 2021.2.23
 * 
 * 입력
 * 첫 번째 줄에 자연수 N이 주어진다. (1 ≤ N ≤ 1,000,000)
 * 
 * 출력
 * 첫 번째 줄에 지원이가 만들 수 있는 길이가 N인 모든 2진 수열의 개수를 15746으로 나눈 나머지를 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[n + 1];
		
		//n = 1일때부터 5일때까지의 수열 개수를 계산해보면 1, 2, 3, 5, 8 이런 식으로 피보나치 수열처럼 증가하고 있음 -> 피보나치 수열 구하는 방법 이용
		dp[1] = 1; //n이 1일 때는 1만 만들 수 있기 때문에 1을 넣어줌
		
		//n이 1일수도 있기때문에 예외 처리 -> 안해주면 채첨할때 런타임에러(ArrayIndexOutOfBounds) 발생
		if (n > 1) {
			dp[2] = 2;	
		}
		
		for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i - 1] + dp[i - 2]) % 15746;
		}
		
		System.out.println(dp[n]);
		
	}

}
