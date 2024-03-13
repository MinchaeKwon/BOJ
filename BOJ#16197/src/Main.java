/**
 * 16197 두 동전
 * https://www.acmicpc.net/problem/16197
 * 
 * @author minchae
 * @date 2024. 3. 13.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Coin {
		int x1;
		int y1;
		int x2;
		int y2;
		int cnt;
		
		public Coin(int x1, int y1, int x2, int y2, int cnt) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.cnt = cnt;
		}
	}
	
	// 좌우상하
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	static int N, M;
	static char[][] map;
	
	static ArrayList<int[]> coins = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'o') {
					coins.add(new int[] {i, j});
				}
			}
		}
		
		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Coin> q = new LinkedList<>();
		boolean[][][][] visited = new boolean[N][M][N][M];
		
		int[] c1 = coins.get(0);
		int[] c2 = coins.get(1);
		
		q.add(new Coin(c1[0], c1[1], c2[0], c2[1], 0));
		visited[c1[0]][c1[1]][c2[0]][c2[1]] = true;
		
		while (!q.isEmpty()) {
			Coin cur = q.poll();
			
			if (cur.cnt >= 10) {
				return -1;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx1 = cur.x1 + dx[i];
				int ny1 = cur.y1 + dy[i];
				
				int nx2 = cur.x2 + dx[i];
				int ny2 = cur.y2 + dy[i];
				
				// 다음 칸이 벽인 경우 제자리
				if (isRange(nx1, ny1) && map[nx1][ny1] == '#') {
					nx1 -= dx[i];
					ny1 -= dy[i];
				}
				
				if (isRange(nx2, ny2) && map[nx2][ny2] == '#') {
					nx2 -= dx[i];
					ny2 -= dy[i];
				}
				
				int out = 0;
				
				if (!isRange(nx1, ny1)) {
					out++;
				}
				
				if (!isRange(nx2, ny2)) {
					out++;
				}
				
				if (out == 1) {
					return cur.cnt + 1; // 두 동전 중 하나가 떨어진 경우 종료
				} else if (out == 0 && !visited[nx1][ny1][nx2][ny2]) {
					// 하나도 떨어지지 않았고 아직 방문하지 않은 경우
					q.add(new Coin(nx1, ny1, nx2, ny2, cur.cnt + 1));
					visited[nx1][ny1][nx2][ny2] = true;
				}
			}
		}
		
		return -1;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
