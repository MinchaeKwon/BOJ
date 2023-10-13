/**
 * 백준 23290 마법사 상어와 복제
 * https://www.acmicpc.net/problem/23290
 * 
 * @author minchae
 * @date 2023. 10. 11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ23290 {
	
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
	
	// 물고기 이동방향 ←, ↖, ↑, ↗, →, ↘, ↓, ↙ (시계방향순)
	static int[] fdx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] fdy = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	// 상어 이동방향 상좌하우
	static int[] sdx = {-1, 0, 1, 0};
	static int[] sdy = {0, -1, 0, 1};
	
	static int M, S;
	static int sx, sy; // 상어의 위치
	static ArrayList<Integer>[][] map = new ArrayList[4][4]; // 물고기의 방향 저장
	
	static int[][] smell = new int[4][4]; // 물고기의 냄새 저장
	
	static boolean[][] visited; // 상어가 이미 이동한 칸인지 확인
	static int[] sharkDir;
	static int max; // 상어가 지나가면서 제외되는 물고기의 최댓값

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
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			
			map[x][y].add(d);
		}
		
		st = new StringTokenizer(br.readLine());
		
		sx = Integer.parseInt(st.nextToken()) - 1;
		sy = Integer.parseInt(st.nextToken()) - 1;
		
		// S번의 연습
		while (S-- > 0) {
			ArrayList<Fish> copy = copyFish();
			moveFish();
			moveShark();
			removeSmell();
			completeCopy(copy);
		}

		int result = 0;
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				result += map[i][j].size();
			}
		}
		
		System.out.println(result);
	}
	
	// 상어가 모든 물고기에게 복제마법 시전
	private static ArrayList<Fish> copyFish() {
		ArrayList<Fish> copy = new ArrayList<>();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int d : map[i][j]) {
					copy.add(new Fish(i, j, d));
				}
			}
		}
		
		return copy;
	}
	
	// 모든 물고기가 한 칸 이동함
	private static void moveFish() {
		// 모든 물고기가 동시에 이동하기 때문에 새로운 배열 만들어서 사용
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
						int nd = (d - k + 8) % 8; // 반시계 방향으로 회전시키기 위해 해당 식 사용
						int nx = i + fdx[nd];
						int ny = j + fdy[nd];
						
						// 범위를 벗어나지 않고 상어가 있는 곳이 아니면서 물고기의 냄새가 없는 곳인 경우
						if (checkRange(nx, ny) && !(nx == sx && ny == sy) && smell[nx][ny] == 0) {
							newMap[nx][ny].add(nd);
							move = true;
							
							break;
						}
					}
					
					// 8방향 다 이동하지 못하는 경우에는 제자리
					if (!move) {
						newMap[i][j].add(d);
					}
				}
			}
		}
		
		map = newMap;
	}
	
	// 상어가 연속해서 3칸 이동함
	private static void moveShark() {
		visited = new boolean[4][4];
		max = -1;
		
		findDir(0, sx, sy, 0, new int[3]);
		
		for (int i = 0; i < 3; i++) {
			int d = sharkDir[i];
			
			sx += sdx[d];
			sy += sdy[d];
			
			// 해당 칸에 물고기가 있는 경우
			if (map[sx][sy].size() > 0) {
				map[sx][sy].clear(); // 물고기 제거
				smell[sx][sy] = 3; // 물고기의 냄새를 남김
			}
		}
	}
	
	// 제외되는 물고기가 가장 많을 때의 방향을 구함 (순열 사용)
	private static void findDir(int depth, int x, int y, int cnt, int[] arr) {
		// 상어가 3칸 연속 이동한 경우
		if (depth == 3) {
			if (cnt > max) {
				max = cnt;
				sharkDir = Arrays.copyOf(arr, 3);
			}
			
			return;
		}
		
		// 상어를 상하좌우로 이동시켜봄
		for (int i = 0; i < 4; i++) {
			int nx = x + sdx[i];
			int ny = y + sdy[i];
			
			if (checkRange(nx, ny)) {
				arr[depth] = i; // depth가 인덱스 역할을 함
				
				// 상어가 방문한 칸이랑 방문하지 않은 칸을 나눠서 봄
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					findDir(depth + 1, nx, ny, cnt + map[nx][ny].size(), arr);
					visited[nx][ny] = false;
				} else {
					findDir(depth + 1, nx, ny, cnt, arr); // 이미 방문한 칸인 경우에는 개수 세지 않음
				}
			}
		}
	}
	
	// 물고기의 냄새가 사라짐
	private static void removeSmell() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (smell[i][j] > 0) {
					smell[i][j]--;
				}
			}
		}
	}
	
	// 복제 마법 완료
	private static void completeCopy(ArrayList<Fish> copy) {
		for (Fish fish : copy) {
			map[fish.x][fish.y].add(fish.d);
		}
	}
	
	private static boolean checkRange(int x, int y) {
		return x >= 0 && x < 4 && y >= 0 && y < 4;
	}

}
