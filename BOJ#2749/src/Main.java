/**
 * 2749 피보나치 수 3
 * https://www.acmicpc.net/problem/2749
 * 
 * @author minchae
 * @date 2022. 3. 25.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	final static int MOD = 1000000;
	static long[][] origin = {{1, 1} , {1, 0}}; // 초기값을 가지고 있는 행렬

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long n = Long.parseLong(br.readLine());
		
		System.out.println(pow(n - 1)[0][0]);
	}
	
	// 분할정복 메소드
	private static long[][] pow(long exp) {
		// 지수가 0 또는 1일 경우에는 초기값 행렬을 반환
		if (exp == 0 || exp == 1) {
			return origin;
		}
		
		long[][] result = pow(exp / 2); // 지수를 반으로 나누고 재귀호출
		
		result = matrixMultiple(result, result); // 재귀를 통해 얻은 행렬을 제곱해줌
		
		// 지수가 홀수일 경우 초기값 행렬을 곱해줌
		if (exp % 2 == 1) {
			result = matrixMultiple(result, origin);
		}
		
		return result;	
	}
	
	// 행렬 곱셈 메소드
	private static long[][] matrixMultiple(long[][] o1, long[][] o2) {
		long[][] matrix = new long[2][2];
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {							
				for (int k = 0; k < 2; k++) {
					matrix[j][k] += o1[j][i] * o2[i][k];
					matrix[j][k] %= MOD;
				}
			}
		}
		
		return matrix;
	}

}
