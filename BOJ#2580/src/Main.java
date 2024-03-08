/**
 * 2580 스도쿠
 * https://www.acmicpc.net/problem/2580
 * 
 * @author minchae
 * @date 2024. 3. 8.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map = new int[9][9];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		backtracking(0, 0);
	}
	
	private static void backtracking(int x, int y) {
		// 특정 행의 칸을 다 채운 경우 다음 행으로 넘어감
		if (y == 9) {
			backtracking(x + 1, 0);
			return;
		}
		
		// 모든 칸을 다 채운 경우
		if (x == 9) {
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(map[i][j] + " ");
				}
				sb.append("\n");
			}
			
			System.out.println(sb.toString());
			System.exit(0);
		}
		
		if (map[x][y] == 0) {
			for(int i = 1 ; i <= 9 ; i++) {
				if (check(x, y, i)) {
					map[x][y] = i;
					backtracking(x, y + 1);
				}
			}
			
			map[x][y] = 0; // 들어갈 수 있는 값이 없는 경우 종료
			return;
		}
		
		backtracking(x, y + 1); // 빈 칸이 아닌 경우에도 열 이동
	}
	
	private static boolean check(int x, int y, int num) {
		// 행 확인
		for (int i = 0; i < 9; i++) {
			if (map[x][i] == num) {
				return false;
			}
		}
		
		// 열 확인
		for (int i = 0; i < 9; i++) {
			if (map[i][y] == num) {
				return false;
			}
		}
		
		// 3 x 3 확인
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
