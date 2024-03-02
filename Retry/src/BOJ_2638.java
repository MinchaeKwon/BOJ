/**
 * 2638 치즈
 * https://www.acmicpc.net/problem/2638
 * 
 * @author minchae
 * @date 2024. 3. 1.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2638 {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M;
	static int[][] map; // 공기 0, 치즈 1, 외부 공기 -1, 녹은 치즈 2
	
	static int cheese;

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
				
				if (map[i][j] == 1) {
					cheese++;
				}
			}
		}

		int answer = 0;
		
		while (cheese > 0) {
			bfs();
			melting();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == -1 || map[i][j] == 2) {
						map[i][j] = 0;
					}
				}
			}
			
			answer++;
		}
		
		System.out.println(answer);
	}
	
	// 외부 공기 구분
	private static void bfs() {
		Queue<Pair> q = new LinkedList<>();
		
		q.add(new Pair(0, 0));
		map[0][0] = -1;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (isRange(nx, ny) && map[nx][ny] == 0) {
					q.add(new Pair(nx, ny));
					map[nx][ny] = -1;
				}
			}
		}
	}
	
	// 치즈 녹이기
	private static void melting() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					int cnt = 0;
					
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						
						if (isRange(nx, ny) && map[nx][ny] == -1) {
							cnt++;
						}
					}
					
					if (cnt >= 2) {
						cheese--;
						map[i][j] = 2;
					}
				}
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
