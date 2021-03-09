/**
 * 17086 아기 상어 2
 * https://www.acmicpc.net/problem/17086
 * 
 * @author Minchae Gwon
 * @date 2021.3.10
 * 
 * 0은 빈 칸, 1은 아기 상어의 위치
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
	int d;
	
	public Point (int x, int y, int d) {
		this.x = x;
		this.y = y;
		this.d = d;
	}
}

public class Main {
	
	// 8방향
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	
	static int N, M;
	static int[][] map;
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int max = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					max = Math.max(max, bfs(i, j));
				}
			}
		}
		
		System.out.println(max);
		
	}
	
	public static int bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		q.add(new Point(x, y, 0));
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			x = p.x;
			y = p.y;
			int d = p.d;
			
			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
					// 상어를 발견하면 (지금까지 이동한 거리 + 1)을 반환
					if (map[nx][ny] == 1) {
						return d + 1;
					}
					
					visited[nx][ny] = true;
					q.add(new Point(nx, ny, d + 1));
				}
			}
		}
		
		return 0;
	}

}
