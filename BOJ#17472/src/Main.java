/**
 * 17472 다리 만들기 2
 * https://www.acmicpc.net/problem/17472
 * 
 * @author minchae
 * @date 2022. 4. 30.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	int count;
	
	public Point(int x, int y, int count) {
		this.x = x;
		this.y = y;
		this.count = count;
	}
}

public class Main {

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int N;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
	}
	
	public static void dfs(int x, int y, boolean[][] visited) {
		visited[x][y] = true;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				if (!visited[nx][ny] && map[nx][ny] == 1) {
					dfs(nx, ny, visited);	
				}
			}
		}
	}
	
	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		q.add(new Point(x, y, 0));
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					
				}
			}
		}
	}

}
