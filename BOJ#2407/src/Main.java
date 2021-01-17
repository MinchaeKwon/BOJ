/*
 * 2407 - 조합
 * 2021.1.5
 * 
 * 문제
 * nCm을 출력한다.
 * 
 * 입력
 * n과 m이 주어진다. (5 ≤ n ≤ 100, 5 ≤ m ≤ 100, m ≤ n)
 * 
 * 출력
 * nCm을 출력한다.
 */
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

	public static BigInteger[][] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		dp = new BigInteger[n + 1][m + 1];
		
		System.out.println(comb(n, m));
		
		sc.close();
	}
	
	public static BigInteger comb(int n, int m) {
		//큰 문제에 답이 있는지 확인 -> 없으면 재귀호출
		if (n == m || m == 0) {
			return BigInteger.ONE;
		}
		if (dp[n][m] != null) {
			return dp[n][m];
		}
		
		dp[n][m] = comb(n - 1, m - 1).add(comb(n - 1, m));
		return dp[n][m];
	}

}
