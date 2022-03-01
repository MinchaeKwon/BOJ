/**
 * 7569 토마토
 * https://www.acmicpc.net/problem/7569
 * 
 * @author minchae
 * @date 2022. 3. 1.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Tomato {
	int x;
	int y;
	int z;
	int day;
	
	public Tomato(int x, int y, int z, int day) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.day = day;
	}
}

public class Main {

	// 위, 아래, 왼쪽, 오른쪽, 앞, 뒤 여섯 방향 확인하기 위한 배열
	static int[] dx = {-1, 0, 1, 0, 0, 0};
	static int[] dy = {0, 1, 0, -1, 0, 0};
	static int[] dz = {0, 0, 0, 0, -1, 1};
	
	static int[][][] map;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][][] map = new int[H][N][M];
		Queue<Tomato> q = new LinkedList<>();
		
		int cnt = 0;
		int empty = 0;
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				
				for (int k = 0; k < M; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
					
					if (map[i][j][k] == 1) {
						q.add(new Tomato(i, j, k, 0));
						cnt++;
					} else if (map[i][j][k] == -1) {
						empty++;
					}
				}	
			}
		}
		
		if (cnt == (H * N * M) - empty) { // 저장될 때부터 모든 토마토가 익어있는 상태인 경우 0 출력
			System.out.println(0);
		} else {
			System.out.println(bfs(H, N, M, map, q));
		}

	}
	
	public static int bfs(int H, int N, int M, int[][][] map, Queue<Tomato> q) {
		int day = 0;
		
		while(!q.isEmpty()) {
			Tomato tomato = q.poll();
			day = Math.max(day, tomato.day);
			
			for (int i = 0; i < 6; i++) {
				int nx = tomato.x + dx[i];
				int ny = tomato.y + dy[i];
				int nz = tomato.z + dz[i];
				
				if (nx >= 0 && nx < H && ny >= 0 && ny < N && nz >= 0 && nz < M) {
					if (map[nx][ny][nz] == 0) {
						map[nx][ny][nz] = 1;
						q.add(new Tomato(nx, ny, nz, tomato.day + 1));
					}
				}
			}
		}
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					// bfs를 했는데 0인 경우는 토마토가 모두 익지 못하는 경우이기 때문에 return -1
					if (map[i][j][k] == 0) {
						return -1;
					}
				}	
			}
		}
		
		return day;
	}

}
