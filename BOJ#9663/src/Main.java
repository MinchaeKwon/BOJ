/**
 * 9663 N-Queen
 * https://www.acmicpc.net/problem/9663
 * 
 * @author minchae
 * @date 2021. 3. 31.
 * 
 * queen의 인덱스는 행(row)을 나타내고 인덱스에 들어있는 값은 열(column)을 나타냄
 * 대각선: 열의 값 차이와 행의 값 차이의 절댓값이 일치하면 대각선에서 만나는 것
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static int[] queen;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		queen = new int[N];
		
		dfs(0);
		System.out.println(result);

	}
	
	public static void dfs(int row) {
		if (row == N) {
			result++;
			
			System.out.println(result + "번째");
			for (int i : queen) {
				System.out.print(i + " ");
			}
			System.out.println("\n");
			
			return;
		}
		
		// row행 i열에 퀸을 놓을 수 있는지 확인
		for (int i = 0; i < N; i++) {
			queen[row] = i;
			
			if (check(row)) {
				dfs(row + 1);
			}
		}
	}
	
	public static boolean check(int row) {
		// 0 ~ (row - 1)행까지 같은 열이나 대각선에 퀸이 위치하는지 확인
		for (int i = 0; i < row; i++) {
			// i행과 row행의 column값이 같으면 퀸을 놓을 수 없음
			if (queen[i] == queen[row]) {
				return false;
			}
			
			// 열의 값 차이와 행의 값 차이의 절댓값이 일치하면 대각선에서 만나기 때문에 퀸을 놓을 수 없음
			if (Math.abs(i - row) == Math.abs(queen[i] - queen[row])) {
				return false;
			}
		}
		
		return true;
	}

}
