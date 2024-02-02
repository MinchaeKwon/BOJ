/**
 * 14502 연구소
 * https://www.acmicpc.net/problem/14502
 * 
 * @author minchae
 * @date 2024. 2. 2.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502 {
	
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
	
	static int answer = 0; // 안전영역 최대 크기

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
	
	// 벽 3개 설치하기
	private static void installWall(int depth) {
		if (depth == 3) {
			spreadVirus();
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
	
	// 바이러스 퍼뜨리기
	private static void spreadVirus() {
		Queue<Pair> q = new LinkedList<>();
		
		int[][] copy = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = map[i][j];
				
				if (copy[i][j] == 2) {
					q.add(new Pair(i, j));
				}
			}
		}
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				// 범위를 벗어나지 않고 빈 칸인 경우
				if (isRange(nx, ny) && copy[nx][ny] == 0) {
					q.add(new Pair(nx, ny));
					copy[nx][ny] = 2;
				}
			}
		}
		
		getSafeArea(copy);
	}
	
	// 안전영역 크기 구하기
	private static void getSafeArea(int[][] copy) {
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 0) {
					cnt++;
				}
			}
		}
		
		answer = Math.max(answer, cnt);
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
