//행렬 곱셈 순서 문제(동적 프로그래밍)

import java.util.Scanner;
public class Main {
	
	private static int[] p;
	private static int[][] m;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N  = sc.nextInt();
		p = new int[N + 1];
		int[][] matrix = new int[N][2];
		
		//i번째 행렬의 열의 개수 == i+1번째 행렬의 행의 개수
		for (int i = 0; i < N; i++) {
			matrix[i][0] = sc.nextInt();
			matrix[i][1] = sc.nextInt();
		}
		
		for (int i = 0; i <= N; i++) {
			if (i == N)
				p[i] = matrix[i - 1][1];
			else
				p[i] = matrix[i][0];
		}
		
		int n = p.length - 1;
		System.out.println("최소 곱셈 횟수: " + matrixChain(n));

		sc.close();
	}
	
	public static int matrixChain(int n) {
		m = new int[n + 1][n + 1];
		
		for (int i = 1; i <= n; i++)
			m[i][i] = 0;
		
		for (int r = 1; r <= n - 1; r++) {
			for (int i = 1; i <= n - r; i++) {
				int j = i + r;
				m[i][j] = Integer.MAX_VALUE;
				
				for (int k = i; k <= j - 1; k++)
					m[i][j] = Math.min(m[i][j], m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j]);
			}
		}
		
		return m[1][n];
	}
}
