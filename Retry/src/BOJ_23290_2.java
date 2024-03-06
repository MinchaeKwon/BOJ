/**
 * 23290 마법사 상어와 복제
 * https://www.acmicpc.net/problem/23290
 * 
 * @author minchae
 * @date 2024. 3. 6.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_23290_2 {
	
	static class Fish {
		int x;
		int y;
		int d;
		
		public Fish(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	
	// ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	static int[] fdx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] fdy = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	// 상좌하우
	static int[] sdx = {-1, 0, 1, 0};
	static int[] sdy = {0, -1, 0, 1};
	
	static int M, S;
	
	static ArrayList<Integer>[][] map = new ArrayList[4][4]; // 물고기 방향 저장
	static int sx, sy;
	
	static int[][] smell = new int[4][4]; // 물고기 냄새 저장
	
	static boolean[][] visited; // 상어 방문 표시
	static int[] dir; // 상어 이동방향 저장
	static int max; // 상어가 먹을 수 있는 물고기의 최대 개수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			
			map[x][y].add(d);
		}
		
		st = new StringTokenizer(br.readLine());
		
		sx = Integer.parseInt(st.nextToken()) - 1;
		sy = Integer.parseInt(st.nextToken()) - 1;
		
		while (S-- > 0) {
			ArrayList<Fish> copy = magic();
			
			moveFish();
			
			moveShark();
			
			removeSmell();
			
			copyFish(copy);
		}
		
		int answer = 0;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				answer += map[i][j].size();
			}
		}
		
		System.out.println(answer);
	}
	
	private static ArrayList<Fish> magic() {
		ArrayList<Fish> result = new ArrayList<>();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int d : map[i][j]) {
					result.add(new Fish(i, j, d));
				}
			}
		}
		
		return result;
	}
	
	private static void moveFish() {
		ArrayList<Integer>[][] newMap = new ArrayList[4][4];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				newMap[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int d : map[i][j]) {
					boolean move = false;
					
					for (int k = 0; k < 8; k++) {
						int nd = (d - k + 8) % 8;
						int nx = i + fdx[nd];
						int ny = j + fdy[nd];
						
						if (!isRange(nx, ny)) {
							continue;
						}
						
						if (smell[nx][ny] == 0 && !(nx == sx && ny == sy)) {
							move = true;
							newMap[nx][ny].add(nd);
							
							break;
						}
					}
					
					if (!move) {
						newMap[i][j].add(d);
					}
				}
			}
		}
		
		map = newMap;
	}
	
	private static void moveShark() {
		visited = new boolean[4][4];
		max = -1;
		
		dfs(0, sx, sy, 0, new int[3]);
		
		for (int d : dir) {
			sx += sdx[d];
			sy += sdy[d];
			
			if (map[sx][sy].size() > 0) {
				map[sx][sy].clear();
				smell[sx][sy] = 3;
			}
		}
	}
	
	private static void dfs(int depth, int x, int y, int cnt, int[] arr) {
		if (depth == 3) {
			if (cnt > max) {
				max = cnt;
				dir = Arrays.copyOf(arr, 3);
			}
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x + sdx[i];
			int ny = y + sdy[i];
			
			if (!isRange(nx, ny)) {
				continue;
			}
			
			arr[depth] = i;
			
			if (!visited[nx][ny]) {
				visited[nx][ny] = true;
				dfs(depth + 1, nx, ny, cnt + map[nx][ny].size(),arr);
				visited[nx][ny] = false;
			} else {
				dfs(depth + 1, nx, ny, cnt, arr);
			}
		}
	}
	
	private static void removeSmell() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (smell[i][j] > 0) {
					smell[i][j]--;
				}
			}
		}
	}
	
	private static void copyFish(ArrayList<Fish> copy) {
		for (Fish f : copy) {
			map[f.x][f.y].add(f.d);
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < 4 && y >= 0 && y < 4;
	}

}
