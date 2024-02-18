/**
 * 19236 청소년 상어
 * https://www.acmicpc.net/problem/19236
 * 
 * @author minchae
 * @date 2024. 2. 18.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_19236_2 {
	
	static class Fish {
		int x;
		int y;
		int n;
		int d;
		boolean alive;
		
		public Fish(int x, int y, int n, int d, boolean alive) {
			this.x = x;
			this.y = y;
			this.n = n;
			this.d = d;
			this.alive = alive;
		}
	}
	
	static class Shark {
		int x;
		int y;
		int d;
		int sum;
		
		public Shark(int x, int y, int d, int sum) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.sum = sum;
		}
	}
	
	// ↑, ↖, ←, ↙, ↓, ↘, →, ↗
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
	
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] map = new int[4][4];
		Fish[] fishes = new Fish[17];

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken()); // 물고기 번호
				int b = Integer.parseInt(st.nextToken()) - 1; // 물고기 방향
				
				fishes[a] = new Fish(i, j, a, b, true);
				map[i][j] = a;
			}
		}
		
		// (0, 0)에 있는 물고기를 먹음
		Fish fish = fishes[map[0][0]];
		
		fish.alive = false;
		map[0][0] = -1; // 상어 위치 표시
		
		Shark shark = new Shark(0, 0, fish.d, fish.n);
		
		simulation(map, fishes, shark);
		
		System.out.println(result);
	}
	
	private static void simulation(int[][] map, Fish[] fishes, Shark shark) {
		result = Math.max(result, shark.sum);
		
		moveFish(map, fishes);
		moveShark(map, fishes, shark);
	}
	
	// 물고기 이동시킴
	private static void moveFish(int[][] map, Fish[] fishes) {
		for (int i = 1; i <= 16; i++) {
			Fish fish = fishes[i];
			
			if (!fish.alive) {
				continue;
			}
			
			for (int d = 0; d < 8; d++) {
				int nd = (fish.d + d) % 8;
				int nx = fish.x + dx[nd];
				int ny = fish.y + dy[nd];
				
				if (!isRange(nx, ny) || map[nx][ny] == -1) {
					continue;
				}
				
				if (map[nx][ny] == 0) { // 빈칸인 경우
					map[fish.x][fish.y] = 0;
				} else { // 다른 물고기가 있는 경우
					Fish change = fishes[map[nx][ny]];
					
					change.x = fish.x;
					change.y = fish.y;
					
					map[fish.x][fish.y] = change.n; // 현재 물고기가 있는 자리에 다른 물고기를 넣음
				}
				
				// 현재 물고기 위치 갱신
				map[nx][ny] = fish.n;
				
				fish.d = nd;
				fish.x = nx;
				fish.y = ny;
				
				break;
			}
		}
	}
	
	// 상어 이동시킴
	private static void moveShark(int[][] map, Fish[] fishes, Shark shark) {
		for (int cnt = 1; cnt <= 3; cnt++) {
			int nx = shark.x + dx[shark.d] * cnt;
			int ny = shark.y + dy[shark.d] * cnt;
			
			if (!isRange(nx, ny) || map[nx][ny] <= 0) {
				continue;
			}
			
			int[][] copyMap = new int[4][4];
			
			for (int i = 0; i < 4; i++) {
				copyMap[i] = Arrays.copyOf(map[i], 4);
			}
			
			Fish[] copyFish = new Fish[17];
			
			for (int i = 1; i <= 16; i++) {
				Fish fish = fishes[i];
				
				copyFish[i] = new Fish(fish.x, fish.y, fish.n, fish.d, fish.alive);
			}
			
			// 상어 위치 이동
			copyMap[shark.x][shark.y] = 0;
			copyMap[nx][ny] = -1;
			
			// 물고기 먹음
			Fish fish = copyFish[map[nx][ny]];
			fish.alive = false;
			
			Shark newShark = new Shark(nx, ny, fish.d, shark.sum + fish.n);
			
			simulation(copyMap, copyFish, newShark);
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < 4 && y >= 0 && y < 4;
	}

}
