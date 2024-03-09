/**
 * 1303 전쟁 - 전투
 * https://www.acmicpc.net/problem/1303
 * 
 * @author minchae
 * @date 2024. 3. 9.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int M, N;
	static char[][] map;
	
	static boolean[][] visited;
	
	static int cnt;
	static int white, blue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[M][N];
		
		for (int i = 0; i < M; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		visited = new boolean[M][N];

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					cnt = 0;
					
					bfs(i, j);
//					dfs(i, j);
					
					if (map[i][j] == 'W') {
						white += Math.pow(cnt, 2);
					} else {
						blue += Math.pow(cnt, 2);
					}
				}
			}
		}
		
		System.out.println(white + " " + blue);
	}
	
	private static void bfs(int x, int y) {
		Queue<Pair> q = new LinkedList<>();
		
		q.add(new Pair(x, y));
		visited[x][y] = true;
		
		cnt++;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (isRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == map[x][y]) {
					q.add(new Pair(nx, ny));
					visited[nx][ny] = true;
					
					cnt++;
				}
			}
		}
	}
	
	private static void dfs(int x, int y) {
		visited[x][y] = true;
		cnt++;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (isRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == map[x][y]) {
				dfs(nx, ny);
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < M && y >= 0 && y < N;
	}

}
