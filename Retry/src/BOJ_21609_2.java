/**
 * 21609 상어 중학교
 * https://www.acmicpc.net/problem/21609
 * 
 * @author minchae
 * @date 2024. 3. 4.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21609_2 {
	
	static class Block implements Comparable<Block> {
		int x;
		int y;
		int size;
		int rainbow;
		
		public Block(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Block(int x, int y, int size, int rainbow) {
			this.x = x;
			this.y = y;
			this.size = size;
			this.rainbow = rainbow;
		}

		@Override
		public int compareTo(BOJ_21609_2.Block o) {
			if (this.size == o.size) {
				if (this.rainbow == o.rainbow) {
					if (this.x == o.x) {
						return o.y - this.y;
					}
					
					return o.x - this.x;
				}
				
				return o.rainbow - this.rainbow;
			}
			
			return o.size - this.size;
		}
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M;
	static int[][] map;
	
	static PriorityQueue<Block> pq;
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
		
		int answer = 0;
		
		while (true) {
			pq = new PriorityQueue<>();
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && map[i][j] > 0) {
						find(i, j);
					}
				}
			}
			
			if (pq.isEmpty()) {
				break;
			}
			
			Block r = pq.poll();
			remove(r.x, r.y, map[r.x][r.y]);
			
			answer += Math.pow(r.size, 2);
			
			gravity();
			
			rotate();
			
			gravity();
		}
		
		System.out.println(answer);
	}
	
	// 크기가 가장 큰 블록 그룹 찾음
	private static void find(int x, int y) {
		Queue<Block> q = new LinkedList<>();
		
		q.add(new Block(x, y));
		visited[x][y] = true;
		
		int size = 1;
		int rainbow = 0;
		
		while (!q.isEmpty()) {
			Block cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!isRange(nx, ny) || visited[nx][ny]) {
					continue;
				}
				
				if (map[nx][ny] == map[x][y] || map[nx][ny] == 0) {
					size++;
					
					if (map[nx][ny] == 0) {
						rainbow++;
					}
					
					q.add(new Block(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
		
		if (size >= 2) {
			pq.add(new Block(x, y, size, rainbow));
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0 && visited[i][j]) {
					visited[i][j] = false;
				}
			}
		}
	}
	
	// 블록 그룹 삭제
	private static void remove(int x, int y, int color) {
		Queue<Block> q = new LinkedList<>();
		
		q.add(new Block(x, y));
		map[x][y] = -2;
		
		while (!q.isEmpty()) {
			Block cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!isRange(nx, ny)) {
					continue;
				}
				
				if (map[nx][ny] == color || map[nx][ny] == 0) {
					q.add(new Block(nx, ny));
					map[nx][ny] = -2;
				}
			}
		}
	}
	
	// 중력 작용
	private static void gravity() {
		for (int j = 0; j < N; j++) {
			for (int i = N - 1; i >= 0 ; i--) {
				if (map[i][j] != -2) {
					continue;
				}
				
				int x = i;
				
				while (true) {
					x -= 1;
					
					if (x < 0 || map[x][j] == -1) {
						break;
					}
					
					// 블록을 만난 경우
					if (map[x][j] >= 0) {
						map[i][j] = map[x][j];
						map[x][j] = -2;
						
						break;
					}
				}
			}
		}
	}
	
	// 반시계 방향으로 90도 회전
	private static void rotate() {
		int[][] newMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[N - 1 - j][i] = map[i][j];
			}
		}
		
		map = newMap;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
