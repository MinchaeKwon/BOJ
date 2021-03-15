/**
 * 2206 벽 부수고 이동하기
 * https://www.acmicpc.net/problem/2206
 * 
 * @author Minchae Gwon
 * @date 2021.3.16
 * 
 * 0은 이동할 수 있는 곳, 1은 이동할 수 없는 벽이 있는 곳
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
	int wall; // 부순 벽의 개수
	int dist; // 이동 거리
	
	public Point(int x, int y, int wall, int dist) {
		this.x = x;
		this.y = y;
		this.wall = wall;
		this.dist = dist;
	}
}

public class Main {
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int N, M;
	static int[][] map;
	static boolean[][][] visited; // 벽을 부순 경우와 부수지 않은 경우를 나눠서 방문 여부를 확인함

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M ];
		visited = new boolean[N][M ][2];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			
			for (int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
	
		System.out.println(bfs(0, 0));
		
	}
	
	public static int bfs(int x, int y) {		
		Queue<Point> q = new LinkedList<>();
		
		q.add(new Point(x, y, 0, 1));
		visited[x][y][0] = true; // 벽을 부수지 않은 경우
		visited[x][y][1] = true; // 벽을 부순 경우
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			x = p.x;
			y = p.y;
			
			// 도착점에 온 경우
			if (x == N - 1 && y == M - 1) {
				return p.dist;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if (map[nx][ny] == 1) { // 벽인 경우
						// 벽을 부순 적이 없고 방문한 곳이 아닐 경우에만 큐에 추가함
						if (p.wall == 0 && !visited[nx][ny][1]) {
							visited[nx][ny][1] = true;
							q.add(new Point(nx, ny, p.wall + 1, p.dist + 1)); // 벽 개수와 이동 거리를 증가시킴
						}
					}
					else { // 벽이 아닌 경우
						if (!visited[nx][ny][p.wall]) { // 방문한적이 없는 경우
							visited[nx][ny][p.wall] = true;
							q.add(new Point(nx, ny, p.wall, p.dist + 1));
						}
					}
					
				}
			}
		}
		
		return -1;
		
	}

}
