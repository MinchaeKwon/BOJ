/**
 * 22352 항체 인식
 * https://www.acmicpc.net/problem/22352
 * 
 * @author minchae
 * @date 2024. 3. 7.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
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
	
	static int[][] map;
	static int[][] compare;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		compare = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				compare[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		simulation();
		
		System.out.println(check() ? "YES" : "NO");
	}
	
	private static void simulation() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != compare[i][j]) {
					bfs(i, j, compare[i][j]);
					return;
				}
			}
		}
	}
	
	private static void bfs(int x, int y, int num) {
		int before = map[x][y];
		
		Queue<Pair> q = new LinkedList<>();
		
		q.add(new Pair(x, y));
		map[x][y] = num;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!isRange(nx, ny) || map[nx][ny] != before) {
					continue;
				}
				
				q.add(new Pair(nx, ny));
				map[nx][ny] = num;
			}
		}
	}
	
	private static boolean check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != compare[i][j]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
