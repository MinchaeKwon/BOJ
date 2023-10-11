/**
 * 백준 20058 마법사 상어와 파이어스톰
 * https://www.acmicpc.net/problem/20058
 * 
 * @author minchae
 * @date 2023. 10. 11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20058 {
	
	// 우하상좌
	static int[] dx = {0, 1, -1, 0};
	static int[] dy = {1, 0, 0, -1};
	
	static int N, Q;
	static int[][] map;
	
	static int iceTotal;
	static int iceCnt;

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
			int l = Integer.parseInt(st.nextToken());
			
			if (l > 0) {
				rotate(l);
			}
			
			meltingIce();
		}
		
		bfs();

		System.out.println(iceTotal);
		System.out.println(iceCnt);
	}
	
	// 백준에서 맞는 코드
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
	
	// 코드트리에서 맞는 코드
//	private static void rotate(int l) {
//		int[][] newMap = new int[N][N];
//		
//		int size = (int) Math.pow(2, l - 1);
//		l = (int) Math.pow(2, l);
//		
//		for (int i = 0; i < N; i += l) {
//			for (int j = 0; j < N; j+= l) {
//				move(i, j, size, 0, newMap);
//				move(i, j + size, size, 1, newMap);
//				move(i + size, j, size, 2, newMap);
//				move(i + size, j + size, size, 3, newMap);
//			}
//		}
//		
//		map = newMap;
//	}
//	
//	private static void move(int sx, int sy, int size, int dir, int[][] newMap) {
//		for (int i = sx; i < sx + size; i++) {
//			for (int j = sy; j < sy + size; j++) {
//				int nx = i + dx[dir] * size;
//				int ny = j + dy[dir] * size;
//				
//				newMap[nx][ny] = map[i][j];
//			}
//		}
//	}
	
	// 얼음 녹이기
	private static void meltingIce() {
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
					
					if (checkRange(nx, ny) && map[nx][ny] > 0) {
						cnt++;
					}
				}
				
				if (cnt < 3) {
					copy[i][j] = map[i][j] - 1;
				}
			}
		}
		
		map = copy;
	}
	
	// 남아있는 얼음의 양과 얼음 중 가장 큰 덩어리가 차지하는 개수를 구함
	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				iceTotal += map[i][j];
				
				if (!visited[i][j] && map[i][j] > 0) {
					q.add(new int[] {i, j});
					visited[i][j] = true;
					
					int cnt = 1;
					
					while (!q.isEmpty()) {
						int[] cur = q.poll();
						
						for (int d = 0; d < 4; d++) {
							int nx = cur[0] + dx[d];
							int ny = cur[1] + dy[d];
							
							if (checkRange(nx, ny) && !visited[nx][ny] && map[nx][ny] > 0) {
								q.add(new int[] {nx, ny});
								visited[nx][ny] = true;
								
								cnt++;
							}
						}
					}
					
					iceCnt = Math.max(iceCnt, cnt);
				}
			}
		}
	}
	
	private static boolean checkRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
