/**
 * 14502 연구소
 * https://www.acmicpc.net/problem/14502
 * 
 * @author minchae
 * @date 2026. 3. 19
 **/

import java.io.*;
import java.util.*;

public class BOJ_14502_2 {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M;
	static int[][] map;
	
	static int answer;

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

		installWall(0);
		
		System.out.println(answer);
	}
	
	// 벽 3개 세우기
	private static void installWall(int depth) {
		if (depth == 3) {
			spread();
			return;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					installWall(depth + 1);
					map[i][j] = 0;
				}
			}
		}
	}
	
	private static void spread() {
		Queue<Pair> q = new LinkedList<>();
		
		int[][] copy = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
				
				// 바이러스가 퍼져야 하니까 큐에 바이러스 다 추가하기
				if (map[i][j] == 2) {
					q.add(new Pair(i, j));
				}
			}
		}
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				
				if (isRange(nx, ny) && copy[nx][ny] == 0) {
					q.add(new Pair(nx, ny));
					
					copy[nx][ny] = 2;
				}
			}
		}
		
		answer = Math.max(answer, countVirus(copy));
	}
	
	private static int countVirus(int[][] spreadMap) {
		int result = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (spreadMap[i][j] == 0) {
					result++;
				}
			}
		}
		
		return result;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
