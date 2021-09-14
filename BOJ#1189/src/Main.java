/**
 * 1189 컴백홈
 * https://www.acmicpc.net/problem/1189
 * 
 * @author minchae
 * @date 2021. 9. 14.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = {-1, 0 , 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int R, C, K;
	static char[][] map;
	static boolean[][] visited;
	
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visited = new boolean[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().toCharArray();
		}
		
		// 시작점 방문 처리 -> 거리는 1부터 시작
		visited[R - 1][0] = true;
		dfs(R - 1, 0, 1);
		
		System.out.println(result);
	}
	
	public static void dfs(int x, int y, int dist) {
		if (x == 0 && y == C - 1 && dist == K) {
			result++;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
				if (!visited[nx][ny] && map[nx][ny] != 'T') {
					visited[nx][ny] = true;
					dfs(nx, ny, dist + 1);
					visited[nx][ny] = false;
				}	
			}
			
		}
	}

}
