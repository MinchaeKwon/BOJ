/**
 * 1799 비숍
 * https://www.acmicpc.net/problem/1799
 * 
 * @author minchae
 * @date 2024. 4. 8.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// 대각선
	static int[] dx = { -1, -1, 1, 1 };
	static int[] dy = { -1, 1, -1, 1 };
	
	static int N;
	static int[][] map; // 비숍을 놓을 수 있는 곳에는 1, 비숍을 놓을 수 없는 곳에는 0
	
	static int[] answer = new int[2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		ArrayList<Pair> black = new ArrayList<>();
		ArrayList<Pair> white = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				// 비숍을 놓을 수 있는 경우에만 리스트에 추가
				if (map[i][j] == 1) {
					if (isBlack(i, j)) {
						black.add(new Pair(i, j));
					} else {
						white.add(new Pair(i, j));
					}
				}
			}
		}
		
		backtracking(0, 0, black, 0); // 검은색 칸 백트래킹
		backtracking(0, 0, white, 1); // 흰색 칸 백트래킹

		System.out.println(answer[0] + answer[1]);
	}
	
	private static void backtracking(int start, int cnt, ArrayList<Pair> list, int color) {
		for (int i = start; i < list.size(); i++) {
			Pair cur = list.get(i);
			
			// 해당 칸에 비숍을 놓을 수 있는 경우
			if (check(cur.x, cur.y)) {
				map[cur.x][cur.y] = 2;
				backtracking(i + 1, cnt + 1, list, color); // 개수 증가 시키고 다음 칸 탐색
				map[cur.x][cur.y] = 1;
			}
		}
		
		// 모든 칸 탐색 다 했을 경우 최댓값 갱신
		answer[color] = Math.max(answer[color], cnt);
	}
	
	private static boolean isBlack(int x, int y) {
		// (x, y) 둘 다 짝수이거나 홀수이면 검은색 칸
		if ((x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1)) {
			return true;
		}
		
		return false;
	}
	
	// 대각선 방향에 다른 비숍이 있는지 확인
	private static boolean check(int x, int y) {
		// 위쪽만 확인 (위에서부터 비숍을 놓기 때문)
		for (int d = 0; d < 2; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			for (int cnt = 0; cnt < x; cnt++) {
				if (isRange(nx, ny) && map[nx][ny] == 2) {
					return false;
				}
				
				nx += dx[d];
				ny += dy[d];
			}
		}
		
//		for (int cnt = 1; cnt <= x; cnt++) {
//			// 위쪽만 확인
//			for (int d = 0; d < 2; d++) {
//				int nx = x + dx[d] * cnt;
//				int ny = y + dy[d] * cnt;
//				
//				// 범위 안이고 이미 비숍이 놓여있는 경우
//				if (isRange(nx, ny) && map[nx][ny] == 2) {
//					return false;
//				}
//			}
//		}
		
		return true;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
