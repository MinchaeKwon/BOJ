/**
 * 16234 인구 이동
 * https://www.acmicpc.net/problem/16234
 * 
 * @author minchae
 * @date 2024. 2. 8.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234 {

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
	
	static int N, L, R;
	static int[][] map;
	
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;		
		boolean flag = true;

		// 인구 이동이 없을 때까지 진행
		while (flag) {
			visited = new boolean[N][N];
			flag = false;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && move(i, j)) {
						flag = true;
					}
				}
			}
			
			if (flag) {
				answer++;
			}
		}
		
		System.out.println(answer);

	}
	
	private static boolean move(int x, int y) {
		Queue<Pair> q = new LinkedList<>();
		
		q.add(new Pair(x, y));
		visited[x][y] = true;
		
		ArrayList<Pair> list = new ArrayList<>(); // 연합 국가 저장
		list.add(new Pair(x, y));
		
		int total = map[x][y]; // 연합 국가의 총 인구 수 저장
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!isRange(nx, ny) || visited[nx][ny]) {
					continue;
				}
				
				int diff = Math.abs(map[cur.x][cur.y] - map[nx][ny]);
				
				if (diff >= L && diff <= R) {
					q.add(new Pair(nx, ny));
					visited[nx][ny] = true;
					
					list.add(new Pair(nx, ny));
					total += map[nx][ny];
				}
			}
		}
		
		// 연합 국가가 있는 경우
		if (list.size() > 1) {
			int people = total / list.size();
			
			// 인구 이동
			for (Pair p : list) {
				map[p.x][p.y] = people;
			}
			
			return true;
		}
		
		return false;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
