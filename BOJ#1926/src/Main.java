/**
 * 1926 그림
 * https://www.acmicpc.net/problem/1926
 * 
 * @author minchae
 * @date 2024. 1. 26.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int n, m;
	
	static int[][] map;
	static boolean[][] visited;
	
	static int drawingCnt;
	static int maxSize;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 1 && !visited[i][j]) {
					bfs(i, j);
					drawingCnt++;
				}
			}
		}
		
		System.out.println(drawingCnt);
		System.out.println(maxSize);
	}
	
	// 각 그림의 크기를 구함
	private static void bfs(int x, int y) {
		Queue<int[]> q = new LinkedList<>();
		
		q.add(new int[] {x, y});
		visited[x][y] = true;
		
		int cnt = 1;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				// 범위 벗어나지 않음, 그림인 경우, 아직 방문하지 않은 경우
				if (isRange(nx, ny) && map[nx][ny] == 1 && !visited[nx][ny]) {
					q.add(new int[] {nx, ny});
					visited[nx][ny] = true;
					
					cnt++;
				}
			}
		}
		
		maxSize = Math.max(maxSize, cnt); // 최댓값 갱신
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}

}
