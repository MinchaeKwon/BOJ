/**
 * 21610 마법사 상어와 비바라기
 * https://www.acmicpc.net/problem/21610
 * 
 * @author minchae
 * @date 2024. 3. 5.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21610_2 {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	static int N, M;
	static int[][] map;
	
	static Queue<Pair> q = new LinkedList<>();
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 구름 초기 위치
		q.add(new Pair(N - 1, 0));
		q.add(new Pair(N - 1, 1));
		q.add(new Pair(N - 2, 0));
		q.add(new Pair(N - 2, 1));
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N][N];
			
			move(d, s);
			magic();
			make();
		}
		
		System.out.println(getWater());
	}
	
	private static void move(int d, int s) {
		for (Pair p : q) {
			p.x = (N + p.x + dx[d] * (s % N)) % N;
			p.y = (N + p.y + dy[d] * (s % N)) % N;
			
			map[p.x][p.y]++;
		}
	}
	
	private static void magic() {
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			visited[cur.x][cur.y] = true;
			
			int cnt = 0;
			
			for (int i = 1; i <= 7; i += 2) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (isRange(nx, ny) && map[nx][ny] > 0) {
					cnt++;
				}
			}
			
			map[cur.x][cur.y] += cnt;
		}
	}
	
	private static void make() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 2 && !visited[i][j]) {
					q.add(new Pair(i, j));
					map[i][j] -= 2;
				}
			}
		}
	}
	
	private static int getWater() {
		int result = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0) {
					result += map[i][j];
				}
			}
		}
		
		return result;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
