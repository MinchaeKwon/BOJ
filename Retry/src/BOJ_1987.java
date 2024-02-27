/**
 * 1987 알파벳
 * https://www.acmicpc.net/problem/1987
 * 
 * @author minchae
 * @date 2024. 2. 27.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1987 {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int R, C;
	static char[][] map;
	
	static boolean[] visited = new boolean[26];
	
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		backtracking(0, 0, 1);
		
		System.out.println(answer);
	}
	
	private static void backtracking(int x, int y, int cnt) {
		visited[map[x][y] - 'A'] = true;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (isRange(nx, ny) && !visited[map[nx][ny] - 'A']) {
				backtracking(nx, ny, cnt + 1);
			}
		}
		
		visited[map[x][y] - 'A'] = false;
		
		answer = Math.max(answer, cnt);
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}

}
