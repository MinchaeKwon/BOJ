/**
 * 16236 아기 상어
 * https://www.acmicpc.net/problem/16236
 * 
 * @author minchae
 * @date 2024. 2. 14.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236 {
	
	static class Pair {
		int x;
		int y;
		int d;
		
		public Pair(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static int N;
	static int[][] map;
	
	// 아기 상어 위치
	static int sx;
	static int sy;
	
	static int shark = 2; // 아기 상어의 크기
	static int fish = 0; // 먹은 물고기 수
	static int time = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 9) {
					sx = i;
					sy = j;
					
					map[i][j] = 0; // 아기 상어가 있는 곳도 지나갈 수 있는 곳으로 만들어줌
				}
			}
		}
		
		boolean flag = true;
		
		while (flag) {
			flag = eat(sx, sy);
			
			// 자신의 크기만큼 물고기를 먹은 경우
			if (shark == fish) {
				shark++;
				fish = 0;
			}
		}

		System.out.println(time);
	}
	
	private static boolean eat(int x, int y) {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		q.add(new Pair(x, y, 0));
		visited[x][y] = true;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			// 빈 칸이 아니고 상어의 크기보다 작은 경우 물고기를 먹음
			if (map[cur.x][cur.y] > 0 && map[cur.x][cur.y] < shark) {
				map[cur.x][cur.y] = 0;
				
				fish++;
				time += cur.d;
				
				sx = cur.x;
				sy = cur.y;
				
				return true;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (isRange(nx, ny) && !visited[nx][ny] && map[nx][ny] <= shark) {
					q.add(new Pair(nx, ny, cur.d + 1));
					visited[nx][ny] = true;
				}
			}
		}
		
		return false;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
