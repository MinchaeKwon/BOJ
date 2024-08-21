/**
 * 1992 쿼드트리
 * https://www.acmicpc.net/problem/1992
 * 
 * @author minchae
 * @date 2024. 8. 22.
 */

import java.io.*;

public class Main {

	static int N;
	static int[][] map;
	
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			
			for (int j = 0; j < N; j++) {
				map[i][j] = input.charAt(j) - '0';
			}
		}
		
		sb = new StringBuilder();
		
		cut(0, 0, N);
		
		System.out.println(sb.toString());
	}
	
	private static void cut(int x, int y, int size) {
		if (check(x, y, size)) {
			sb.append(map[x][y]);
			
			return;
		}
		
		int half = size / 2; // 반으로 자름
		
		sb.append("(");
		
		for (int i = x; i < x + size; i += half) {
			for (int j = y; j < y + size; j += half) {
				cut(i, j, half);
			}
		}
		
		sb.append(")");
	}
	
	// 같은 영상인지 확인
	private static boolean check(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (map[i][j] != map[x][y]) {
					return false;
				}
			}
		}
		
		return true;
	}

}
