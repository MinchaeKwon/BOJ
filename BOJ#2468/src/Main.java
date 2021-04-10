/**
 * 2468 안전 영역
 * https://www.acmicpc.net/problem/2468
 * 
 * @author minchae
 * @date 2021. 4. 10.
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
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int N;
	static int[][] map;
	static int result;
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int high = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				high = Math.max(high, map[i][j]);
			}
		}
		
		for (int i = 0; i < high; i++) {
			bfs(i);
		}
		
		System.out.println(result);
	}
	
	public static void bfs(int height) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		int safe = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > height && !visited[i][j]) {
					safe++; // 안전 영역 개수 추가
					
					q.add(new Point(i, j));
					visited[i][j] = true;
					
					while (!q.isEmpty()) {
						Point p = q.poll();
						
						for (int k = 0; k < 4; k++) {
							int nx = p.x + dx[k];
							int ny = p.y + dy[k];
							
							if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] > height && !visited[nx][ny]) {
								q.add(new Point(nx, ny));
								visited[nx][ny] = true;
							}
							
						}
						
					}
				}
				
			}
		}
		
		result = Math.max(result, safe); // 최댓값 찾음
		
	}
	
}
