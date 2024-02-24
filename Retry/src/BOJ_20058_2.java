/**
 * 20058 마법사 상어와 파이어스톰
 * https://www.acmicpc.net/problem/20058
 * 
 * @author minchae
 * @date 2024. 2. 24.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20058_2 {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// 우하상좌
	static int[] dx = {0, 1, -1, 0};
	static int[] dy = {1, 0, 0, -1};
	
	static int N, Q;
	
	static int[][] map;
	
	static int iceSum = 0; // 남아있는 얼음의 합
	static int iceSize = 0; // 남아있는 얼음 중 가장 큰 덩어리가 차지하는 칸의 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		N = (int) Math.pow(2, N);
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		while (Q-- > 0) {
			int L = Integer.parseInt(st.nextToken());
			
			if (L > 0) {
				rotate(L);
			}
			
			decrease();
		}
		
		bfs();

		System.out.println(iceSum);
		System.out.println(iceSize);
	}
	
	// 회전시키기
	private static void rotate(int l) {
		int[][] newMap = new int[N][N];

		l = (int) Math.pow(2, l);

		// l 크기만큼 격자 나누기
		for (int x = 0; x < N; x += l) {
			for (int y = 0; y < N; y += l) {

				// 나눠진 격자 안의 숫자들을 90도 회전시키기
				for (int i = 0; i < l; i++) {
					for (int j = 0; j < l; j++) {
						newMap[x + i][y + j] = map[x + l - 1 - j][y + i];
					}
				}
			}
		}

		map = newMap;
	}
	
	// 얼음 양 줄이기
	private static void decrease() {
		int[][] copy = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			copy[i] = Arrays.copyOf(map[i], N);
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					continue;
				}
				
				int cnt = 0;
				
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if (!isRange(nx, ny) || map[nx][ny] == 0) {
						continue;
					}
					
					cnt++;
				}
				
				if (cnt < 3) {
					copy[i][j] = map[i][j] - 1;
				}
			}
		}
		
		map = copy;
	}
	
	// bfs를 통해 남아있는 얼음 구하기
	private static void bfs() {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				iceSum += map[i][j];
				
				if (visited[i][j] || map[i][j] == 0) {
					continue;
				}
				
				int cnt = 1;
				
				q.add(new Pair(i, j));
				visited[i][j] = true;
				
				while (!q.isEmpty()) {
					Pair cur = q.poll();
					
					for (int d = 0; d < 4; d++) {
						int nx = cur.x + dx[d];
						int ny = cur.y + dy[d];
						
						if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 0) {
							continue;
						}
						
						q.add(new Pair(nx, ny));
						visited[nx][ny] = true;
						
						cnt++;
					}
				}
				
				iceSize = Math.max(iceSize, cnt);
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
