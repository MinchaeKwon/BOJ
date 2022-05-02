/**
 * 2146 다리 만들기
 * https://www.acmicpc.net/problem/2146
 * 
 * @author minchae
 * @date 2022. 3. 23.
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
	static int result = Integer.MAX_VALUE;
	
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
		
		boolean[][] visited = new boolean[N][N];
		int num = 1;
		
		// 섬에 번호를 새김
		for (int i = 0; i < N; i++) {
           for (int j = 0; j < N; j++) {
        	   if (!visited[i][j] && map[i][j] == 1) {
        		   dfs(i, j, visited, num);
        		   num++;
	            }
	        }
	    }
		
		// 다리를 놓음
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 섬인 경우 -> 다리를 놓아야 하기 때문에 bfs 실행
				// 최소 다리 길이를 찾아야 하기 때문에 섬을 발견하면 bfs를 실행해야하기 때문에 !visited[i][j]를 확인하지 않음
				if (map[i][j] > 0) {
					bfs(i, j);
				}
			}
		}
		
		System.out.println(result);
	}
	
	// dfs를 통해 섬마다 번호를 새기는 메소드
	private static void dfs(int x, int y, boolean[][] visited, int num) {
		visited[x][y] = true;
		map[x][y] = num;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
				// 아직 방문하지 않았고, 섬인 경우
				if (!visited[nx][ny] && map[nx][ny] == 1) {
					dfs(nx, ny, visited, num);
				}
			}
		}
	}
	
	// bfs를 통해 다리를 놓는 메소드
	private static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		q.add(new Point(x, y, 0));
		visited[x][y] = true;
		
		int currentIsland = map[x][y]; // 현재 섬의 번호
		
		while (!q.isEmpty()) {
			Point point = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = point.x + dx[i];
				int ny = point.y + dy[i];
				
				// 범위를 벗어나지 않으면서 아직 방문하지 않았고, 바다이거나 다른 섬인 경우
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] != currentIsland) {
					visited[nx][ny] = true;
					
					if (map[nx][ny] == 0) {
						// 바다인 경우 큐에 해당 위치와 다리 길이 큐에 삽입
						q.add(new Point(nx, ny, point.count + 1));
					} else {
						// 다른 섬일 경우 다리를 놓을 수 있는 경우이므로 result와 point.count 중에서 최소값을 찾음
						result = Math.min(result, point.count);
					}
				}
			}
		}
	}

}
