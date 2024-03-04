/**
 * 16929 Two Dots
 * https://www.acmicpc.net/problem/16929
 * 
 * @author minchae
 * @date 2024. 3. 4.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M;
	static char[][] map;
	
	static int curX, curY;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited = new boolean[N][M];
				
				curX = i;
				curY = j;
				
				dfs(i, j, 1);
			}
		}

		System.out.println("No");
	}
	
	private static void dfs(int x, int y, int cnt) {
		visited[x][y] = true;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (isRange(nx, ny) && map[nx][ny] == map[x][y]) {
				if (!visited[nx][ny]) {
					dfs(nx, ny, cnt + 1);
				} else {
					// 사이클인 경우 바로 종료
					if (cnt >= 4 && nx == curX && ny == curY) {
						System.out.println("Yes");
						System.exit(0);
					}
				}
			}
		}
		
		return;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
