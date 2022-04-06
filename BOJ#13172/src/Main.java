/**
 * 13172 Σ
 * https://www.acmicpc.net/problem/13172
 * 
 * @author minchae
 * @date 2022. 4. 6.
 * 
 * b의 모듈러 곱셈에 대한 역원 (b^ - 1)을 구해야 함 -> 그 값은 (b^x - 2) % x 를 통해 얻을 수 있음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static final int MOD = 1000000007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int M = Integer.parseInt(br.readLine());
		
		int result = 0;
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int S = Integer.parseInt(st.nextToken());

			// 기약분수 만들기
			int gcd = gcd(N, S);
			N /= gcd;
			S /= gcd;
			
			result += S * pow(N, MOD - 2) % MOD;
			result %= MOD;
		}

		System.out.println(result);
	}
	
	// 최대 공약수 구하는 메소드
	public static int gcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		
		return gcd(b, a % b);
	}
	
	public static long pow(int n, int exp) {
		if (exp == 1) {
			return n;
		}
		
		if (exp % 2 == 0) {
			long tmp = pow(n, exp / 2);
			return tmp * tmp % MOD;
		} else {
			return n * pow(n, exp - 1) % MOD;
		}
	}

}
