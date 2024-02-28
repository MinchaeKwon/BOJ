/**
 * 21608 상어 초등학교
 * https://www.acmicpc.net/problem/21608
 * 
 * @author minchae
 * @date 2024. 2. 28.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_21608_2 {
	
	static class Shark implements Comparable<Shark> {
		int x;
		int y;
		int like;
		int empty;
		
		public Shark(int x, int y, int like, int empty) {
			this.x = x;
			this.y = y;
			this.like = like;
			this.empty = empty;
		}

		@Override
		public int compareTo(Shark o) {
			if (this.like == o.like) {
				if (this.empty == o.empty) {
					if (this.x == o.x) {
						return this.y - o.y;
					}
					
					return this.x - o.x;
				}
				
				return o.empty - this.empty;
			}
			
			return o.like - this.like;
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N;
	
	static int[][] map;
	static int[] order;
	static int[][] students;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		order = new int[N * N + 1];
		students = new int[N * N + 1][4];
		
		for (int i = 1; i <= N * N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			
			order[i] = num;
			
			for (int j = 0; j < 4; j++) {
				students[num][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		
		for (int i = 1; i <= N * N; i++) {
			placement(order[i]);
		}
		

		System.out.println(getScore());
	}
	
	private static void placement(int num) {
		PriorityQueue<Shark> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0) {
					continue;
				}
				
				int like = 0;
				int empty = 0;
				
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if (!isRange(nx, ny)) {
						continue;
					}
					
					for (int n : students[num]) {
						if (n == map[nx][ny]) {
							like++;
						}
					}
					
					if (map[nx][ny] == 0) {
						empty++;
					}
				}
				
				pq.add(new Shark(i, j, like, empty));
			}
		}
		
		Shark shark = pq.poll();
		map[shark.x][shark.y] = num;
	}
	
	private static int getScore() {
		int result = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					continue;
				}
				
				int like = 0;
				
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if (!isRange(nx, ny)) {
						continue;
					}
					
					for (int n : students[map[i][j]]) {
						if (n == map[nx][ny]) {
							like++;
						}
					}
				}
				
				switch (like) {
				case 1:
					result += 1;
					break;
				case 2:
					result += 10;
					break;
				case 3:
					result += 100;
					break;
				case 4:
					result += 1000;
					break;
				}
			}
		}
		
		return result;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
