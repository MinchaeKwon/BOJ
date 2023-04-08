/**
 * 마법사 상어와 복제
 * https://www.acmicpc.net/problem/23290
 * 
 * @author minchae
 * @date 2023. 4. 8.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Fish {
	int x;
	int y;
	int dir;
	
	public Fish(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}

public class Main {

	// 물고기 이동방향 ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	static int[] fdx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] fdy = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	// 상어 이동방향 상좌하우
	static int[] sdx = {-1, 0, 1, 0};
	static int[] sdy = {0, -1, 0, 1};
	
	static int M;
	static ArrayList<Integer>[][] map = new ArrayList[4][4]; // 물고기 방향 저장
	static int sx, sy; // 상어 위치
	
	static int[][] smell = new int[4][4]; // 물고기 냄새 저장
	static int[] sharkDir = new int[3]; // 상어가 이동하는 방향 저장
	
	static int max = 0; // 상어가 먹을 수 있는 물고기의 개수 저장
	static boolean[][] visited = new boolean[4][4];
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
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
		
		while (S-- > 0) {
			
			// 1. 복제 마법 시전
			ArrayList<Fish> copyFishList = copyFish();
			
			// 2. 물고기 한 칸 이동
			map = moveFish();
			
			// 3. 상어 이동
			moveShark();
			
			// 4. 두 번 전에 연습에서 생긴 물고기의 냄새 삭제
			removeSmell();
			
			// 5. 1에서 사용한 복제 마법 완료 -> 맵에 물고기 복제 처리
			setCopyMap(copyFishList);
		}
		
		// 격자에 있는 물고기 수 출력
		int answer = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				answer += map[i][j].size();
			}
		}
		
		System.out.println(answer);
	}
	
	// 물고기 복제하기
	private static ArrayList<Fish> copyFish() {
		ArrayList<Fish> list = new ArrayList<>();
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int dir : map[i][j]) {
					list.add(new Fish(i, j, dir));
				}
			}
		}
		
		return list;
	}
	
	// 물고기 이동시키기
	private static ArrayList<Integer>[][] moveFish() {
		ArrayList<Integer>[][] copyMap = new ArrayList[4][4];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				copyMap[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int dir : map[i][j]) {
					boolean move = false;
					
					for (int k = 0; k < 8; k++) {
						int nd = (dir - k + 8) % 8;
						int nx = i + fdx[nd];
						int ny = j + fdy[nd];
						
//						int nd = (dir - 1 < 0) ? 7 : dir - 1;
						
						if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
							// 냄새가 없고 상어가 없는 경우 물고기 이동 -> 이동했으면 루프 탈출
							if (smell[nx][ny] == 0 && !(nx == sx && ny == sy)) {
								copyMap[nx][ny].add(nd);
								move = true;
								break;
							}
						}
					}
					
					// 8방향 다 이동할 수 없으면 제자리
					if (!move) {
						copyMap[i][j].add(dir);
					}
				}
			}
		}
		
		return copyMap;
	}
	
	// 상어 이동시키기
	private static void moveShark() {
		max = -1;
		visited = new boolean[4][4];
		findSharkDir(0, 0, sx, sy, new int[3]);
		
		for (int i = 0; i < 3; i++) {
			sx += sdx[sharkDir[i]];
			sy += sdy[sharkDir[i]];
			
			if (map[sx][sy].size() > 0) {
				map[sx][sy].clear();
				smell[sx][sy] = 3;
			}
		}
	}
	
	// 상어가 물고기를 가장 많이 먹을 수 있는 방향 탐색
	private static void findSharkDir(int depth, int cnt, int x, int y, int[] arr) {
		if (depth == 3) {
			if (cnt > max) {
				for(int i = 0 ; i < 3; i++) {
					sharkDir[i] = arr[i];
					max = cnt;
				}
			}
			return;
		}
		
		for(int i = 0 ; i < 4; i++) {
			int nx = x + sdx[i];
			int ny = y + sdy[i];
			
			if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
				arr[depth] = i;
				
				if (!visited[nx][ny]) {
					visited[nx][ny] = true;
					findSharkDir(depth + 1, cnt + map[nx][ny].size(), nx, ny, arr);
					visited[nx][ny] = false;
				} else {
					findSharkDir(depth + 1, cnt, nx, ny, arr); // 이미 방문한 칸이면 물고기 개수 세지않음
				}
			}
		}
	}
	
	// 물고기 냄새 삭제하기
	private static void removeSmell() {
		for(int i = 0 ; i < 4;  i++) {
			for(int j = 0 ; j < 4; j++) {
				if(smell[i][j] > 0) {
					smell[i][j]--;
				}
			}
		}
	}
	
	// 복제된 물고기 맵에 배치
	private static void setCopyMap(ArrayList<Fish> fishList) {
		for (Fish fish : fishList) {
			map[fish.x][fish.y].add(fish.dir);
		}
	}
	
}
