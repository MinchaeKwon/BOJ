/**
 * 1987 알파벳
 * https://www.acmicpc.net/problem/1987
 * 
 * @author minchae
 * @date 2021. 7. 10.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// 상하좌우
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int R, C;
	static char[][] map;
	
	// 알파벳 대문자 개수만큼 배열 선언
	static boolean[] visited = new boolean[26];
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		dfs(0, 0, 1);
		System.out.println(result);

	}
	
	public static void dfs(int x, int y, int cnt) {
		visited[map[x][y] - 'A'] = true; // 배열 크기가 26이므로 'A'를 빼서 배열 크기에 맞게 저장
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[map[nx][ny] - 'A']) {
				dfs(nx, ny, cnt + 1);
			}
		}
		
		visited[map[x][y] - 'A'] = false;
		result = Math.max(result, cnt);
	}

}
