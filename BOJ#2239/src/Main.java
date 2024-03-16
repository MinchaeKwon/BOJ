/**
 * 2239 스도쿠
 * https://www.acmicpc.net/problem/2239
 * 
 * @author minchae
 * @date 2024. 3. 16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int[][] map = new int[9][9];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 9; i++) {
			String input = br.readLine();
			
			for (int j = 0; j < 9; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		backtracking(0, 0);
	}
	
	private static void backtracking(int x, int y) {
		if (y == 9) {
			backtracking(x + 1, 0);
			return;
		}
		
		if (x == 9) {
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			
			System.out.println(sb.toString());
			System.exit(0);
		}
		
		if (map[x][y] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (check(x, y, i)) {
					map[x][y] = i;
					backtracking(x, y + 1);
				}
			}
			
			map[x][y] = 0;
			return;
		}
		
		backtracking(x, y + 1);
	}
	
	private static boolean check(int x, int y, int num) {
		for (int i = 0; i < 9; i++) {
			if (map[x][i] == num) {
				return false;
			}
			
			if (map[i][y] == num) {
				return false;
			}
		}
		
		int sx = (x / 3) * 3;
		int sy = (y / 3) * 3;
		
		for (int i = sx; i < sx + 3; i++) {
			for (int j = sy; j < sy + 3; j++) {
				if (map[i][j] == num) {
					return false;
				}
			}
		}
		
		return true;
	}

}
