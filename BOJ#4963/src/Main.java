/**
 * 4963 섬의 개수
 * https://www.acmicpc.net/problem/4963
 * 
 * @author minchae
 * @date 2022. 3. 22.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Island {
	int x;
	int y;
	
	public Island(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	// 상하좌우, 대각선
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	
	static int w, h;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if (w == 0 && h == 0) {
				break;
			}
			
			map = new int[h][w];
			visited = new boolean[h][w];
			
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = 0;
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					// 섬을 발견한 경우 bfs 시작
					if (!visited[i][j] && map[i][j] == 1) {
						bfs(i, j);
						result++;
					}
				}
			}
			
			System.out.println(result);
		}

	}
	
	// 섬 찾기
	private static void bfs(int x, int y) {
		Queue<Island> q = new LinkedList<>();
		
		q.add(new Island(x, y));
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			Island island = q.poll();
			
			for (int i = 0; i < 8; i++) {
				int nx = island.x + dx[i];
				int ny = island.y + dy[i];
				
				if (nx >= 0 && nx < h && ny >= 0 && ny < w) {
					// 아직 방문하지 않았고 땅인 경우
					if (!visited[nx][ny] && map[nx][ny] == 1) {
						q.add(new Island(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
		}
	}

}
