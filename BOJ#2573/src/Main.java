/**
 * 2573 빙산
 * https://www.acmicpc.net/problem/2573
 * 
 * @author minchae
 * @date 2022. 3. 7.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class IceBerg {
	int x;
	int y;
	
	public IceBerg(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int N, M;
	static int[][] map;
	static boolean[][] visited;

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
		
		int year = 0;
		while (true) {
			int result = countIsland();
			
			// 빙하 덩어리가 0개(다 녹은 경우)이거나, 2개 이상일 경우 break
			if (result >= 2) {
				break;
			} else if (result == 0) {
				year = 0; // 빙하가 다 녹을 때까지 두 덩어리 이상 분리되지 않는 경우이므로 0을 넣어줌
				break;
			}
			
			bfs();
			year++;
		}

		System.out.println(year);
	}
	
	// 빙하 덩어리 개수를 세는 메소드
	public static int countIsland() {
		boolean[][] visited = new boolean[N][M];
		
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    dfs(i, j, visited);
                    cnt++;
                }
            }
        }
		
		return cnt;
	}
	
	// dfs를 통해 빙하 덩어리를 계산
	public static void dfs(int x, int y, boolean[][] visited) {
		visited[x][y] = true;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
				if (!visited[nx][ny] && map[nx][ny] > 0) {
					dfs(nx, ny, visited);
				}
			}
		}
	}
	
	// 빙하 녹이는 메소드
	public static void bfs() {
		Queue<IceBerg> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    q.add(new IceBerg(i, j));
                    visited[i][j] = true;
                }
            }
        }
		
		while (!q.isEmpty()) {
			IceBerg ice = q.poll();
			
			int sea = 0; // 빙하 상하좌우에 존재하는 바닷물 영역의 개수
			
			for (int i = 0; i < 4; i++) {
				int nx = ice.x + dx[i];
				int ny = ice.y + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if (!visited[nx][ny] && map[nx][ny] == 0) {
						sea++;
					}
				}
			}
			
			if (map[ice.x][ice.y] - sea < 0) {
				map[ice.x][ice.y] = 0; // 각 칸에 저장된 높이는 0보다 더 줄어들지 않기 때문에 0보다 작아지는 경우 0을 저장
			} else {
				map[ice.x][ice.y] -= sea;
			}
		}
	}

}
