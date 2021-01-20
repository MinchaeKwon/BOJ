/**
 * 10026 - 적록색약
 * https://www.acmicpc.net/problem/10026
 * 
 * @author Minchae Gwon
 * @date 2021.1.21
 * 
 * 입력
 * 첫째 줄에 N이 주어진다. (1 ≤ N ≤ 100)
 * 둘째 줄부터 N개 줄에는 그림이 주어진다.
 * 
 * 출력
 * 적록색약이 아닌 사람이 봤을 때의 구역의 개수와 적록색약인 사람이 봤을 때의 구역의 수를 공백으로 구분해 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static String[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		map = new String[N][N];
		
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.substring(j, j + 1);
			}
		}
		
		visited = new boolean[N][N];
		
		//적록색약이 아닌 사람이 봤을 때의 구역의 개수를 구함
		int normal = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
//					dfs(i, j, N);
					bfs(i, j, N);
					normal++;
				}
			}
		}
		
		for (boolean[] v : visited) {
			Arrays.fill(v, false);	
		}
		
		//적록색약은 빨강=초록이므로 초록색을 발견하면 빨간색으로 바꿔준 후에 탐색을 함
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].equals(String.valueOf('G'))) {
					map[i][j] = String.valueOf('R');
				}
			}
		}
		
		int rgb = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
//					dfs(i, j, N);
					bfs(i, j, N);
					rgb++;
				}
			}
		}
		
		System.out.println(normal + " " + rgb);

	}
	
	//dfs로 탐색
	public static void dfs(int x, int y, int n) {
		visited[x][y] = true;
		String color = map[x][y]; //현재 색깔
		
		for (int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			
			if (tx >= 0 && tx < n && ty >= 0 && ty < n) {
				// 상하좌우를 확인하면서 같은 색이 있는지 보고 방문하지 않았을 경우
				if (map[tx][ty].equals(color) && !visited[tx][ty]) {
					dfs(tx, ty, n);
				}
			}
		}
	}
	
	//bfs로 탐색
	public static void bfs(int x, int y, int n) {
		Queue<int[]> q = new LinkedList<>();
		
		visited[x][y] = true;
		String color = map[x][y]; //현재 색깔
		q.add(new int[] {x, y});
		
		while (!q.isEmpty()) {
			int[] point = q.poll();
			x = point[0];
			y = point[1];
			
			for (int i = 0; i < 4; i++) {
				int tx = x + dx[i];
				int ty = y + dy[i];
				
				if (tx >= 0 && tx < n && ty >= 0 && ty < n) {
					// 상하좌우를 확인하면서 같은 색이 있는지 보고 방문하지 않았을 경우
					if (map[tx][ty].equals(color) && !visited[tx][ty]) {
						q.add(new int[] {tx, ty});
						visited[tx][ty] = true;
					}
				}
			}
		}
	}

}
