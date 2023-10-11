/**
 * 백준 19236 청소년 상어
 * https://www.acmicpc.net/problem/19236
 * 
 * @author minchae
 * @date 2023. 10. 11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ19236 {
	
	static class Fish {
		int x;
		int y;
		int n;
		int d;
		boolean alive;
		
		public Fish (int x, int y, int n, int d, boolean alive) {
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
	
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] map = new int[4][4];
		Fish[] fishes = new Fish[17];
		
		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 4; j++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken()) - 1;
				
				fishes[a] = new Fish(i, j, a, b, true);
				map[i][j] = a;
			}
		}
		
		// 맨 처음 (0, 0)에 있는 물고기를 먹음
		Fish fish = fishes[map[0][0]];
		fish.alive = false;
		
		map[0][0] = -1; // 죽었다는 표시로 1
		
		Shark shark = new Shark(0, 0, fish.d, fish.n);
		dfs(shark, map, fishes);
		
		System.out.println(result);
	}
	
	// 백트래킹을 통해 먹을 수 있는 물고기의 최대 합을 찾음
	private static void dfs(Shark shark, int[][] map, Fish[] fishes) {
		result = Math.max(result, shark.sum);
		
		moveFish(map, fishes);
		moveShark(shark, map, fishes);
	}
	
	// 상어 움직이기
	private static void moveShark(Shark shark, int[][] map, Fish[] fishes) {
		for (int cnt = 1; cnt <= 3; cnt++) {
			int nx = shark.x + dx[shark.d] * cnt;
			int ny = shark.y + dy[shark.d] * cnt;
			
			// 범위를 벗어나지 않고 물고기가 있는 경우
			if (checkRange(nx, ny) && map[nx][ny] > 0) {
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
				
				dfs(newShark, copyMap, copyFish);
			}
		}
	}
	
	// 물고기 움직이기
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
				
				if (checkRange(nx, ny) && map[nx][ny] != -1) {
					if (map[nx][ny] == 0) { // 빈칸인 경우
						map[fish.x][fish.y] = 0;
					} else { // 다른 물고기가 있는 경우
						Fish change = fishes[map[nx][ny]];
						
						change.x = fish.x;
						change.y = fish.y;
						
						map[fish.x][fish.y] = change.n;
					}
					
					map[nx][ny] = fish.n;
					
					fish.d = nd;
					fish.x = nx;
					fish.y = ny;
					
					break;
				}
			}
		}
	}
	
	private static boolean checkRange(int x, int y) {
		return x >= 0 && x < 4 && y >= 0 && y < 4;
	}

}
