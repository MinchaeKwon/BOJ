/**
 * 행렬 제곱
 * https://www.acmicpc.net/problem/10830
 * 
 * @author Minchae Gwon
 * @date 2021.2.12
 * 
 * 문제
 * 크기가 N*N인 행렬 A가 주어진다. 이때, A의 B제곱을 구하는 프로그램을 작성하시오. 수가 매우 커질 수 있으니, A^B의 각 원소를 1,000으로 나눈 나머지를 출력한다.
 * 
 * 입력
 * 첫째 줄에 행렬의 크기 N과 B가 주어진다. (2 ≤ N ≤  5, 1 ≤ B ≤ 100,000,000,000)
 * 둘째 줄부터 N개의 줄에 행렬의 각 원소가 주어진다. 행렬의 각 원소는 1,000보다 작거나 같은 자연수 또는 0이다.
 * 
 * 출력
 * 첫째 줄부터 N개의 줄에 걸쳐 행렬 A를 B제곱한 결과를 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] matrix;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		matrix = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] answer = matrixPow(b);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(answer[i][j] % 1000 + " ");
			}
			System.out.println();
		}
		
	}
	
	public static int[][] matrixPow(long b) {
		int result[][] = new int[n][n];
		
		//b가 1일 경우 제곱하지 않고 그대로 반환
		if (b == 1) {
			return matrix;
		}
		else if (b % 2 == 0) { //b가 짝수인 경우 (행렬의 b/2제곱)*(행렬의 b/2제곱)
			int[][] prev = matrixPow(b / 2);
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (int k = 0; k < n; k++) {
						result[i][j] += prev[i][k] * prev[k][j];
					}
					result[i][j] %= 1000;
				}
			}
			
			return result;
		}
		else { //b가 홀수인 경우(행렬의 b-1제곱) * 원래 행렬
			int[][] prev = matrixPow(b - 1);
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {		
					for (int k = 0; k < n; k++) {
						result[i][j] += prev[i][k] * matrix[k][j];
					}
					result[i][j] %= 1000;
				}
			}
			
			return result;
		}
		
	}

}
