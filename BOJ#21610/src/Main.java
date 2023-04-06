/**
 * 마법사 상어와 비바라기
 * https://www.acmicpc.net/problem/21610
 * 
 * @author minchae
 * @date 2023. 4. 6.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Cloud {
	int x;
	int y;
	
	public Cloud(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	// 좌, 왼쪽 위, 상, 오른쪽 위, 우, 오른쪽 아래, 하, 왼쪽 아래
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	static int N;
	static int[][] map;
	
	static Queue<Cloud> q = new LinkedList<>();
	static boolean[][] visited; // 사라진 구름의 위치를 저장하기 위해 방문체크 함

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 구름 기존 위치 리스트에 넣기 -> (N, 1), (N, 2), (N-1, 1), (N-1, 2)
		q.add(new Cloud(N -1, 0));
		q.add(new Cloud(N -1, 1));
		q.add(new Cloud(N -2, 0));
		q.add(new Cloud(N -2, 1));
		
		// M번만큼 반복
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			
			moveCloud(d, s);
			magic();
			makeCloud();
		}
		
		// M번 이동이 끝난 후에 남아 있는 물의 양의 합 구하기
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer += map[i][j];
			}
		}
		
		System.out.println(answer);
	}
	
	// 구름 이동하고 구름이 이동한 칸에 물의 양 1 증가시킴
	private static void moveCloud(int d, int s) {
		for (Cloud cloud : q) {
			// d방향으로 s칸 이동
			cloud.x = (N + cloud.x + dx[d] * (s % N)) % N;
			cloud.y = (N + cloud.y + dy[d] * (s % N)) % N;
						
			map[cloud.x][cloud.y]++; // 1 증가시킴
			
			// 여기서 방문체크 할 필요가 없는 이유 -> 큐에 들어있는 구름 위치를 바로 변화시켜주기 때문에 큐의 크기만큼 물복사버그 마법 시전하면됨
		}
	}
	
	// 구름 사라지고 물복사버그 마법 시전 (위에서 물의 양이 증가한 칸에 해당 마법 시전)
	private static void magic() {
		while (!q.isEmpty()) {
			Cloud cloud = q.poll(); // 구름 없앰
			int x = cloud.x;
			int y = cloud.y;
			
			int cnt = 0;
			
			visited[x][y] = true;
			
			// 대각선 방향 확인
			for (int i = 1; i <= 7; i += 2) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					// 대각선 칸에 물이 있는 경우에 바구니의 개수 증가
					if (map[nx][ny] > 0) {
						cnt++;
					}
				}
			}
			
			map[x][y] += cnt; // 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 현재 칸의 바구니의 물이 양이 증가
		}
	}
	
	
	// 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 함
	private static void makeCloud() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 이전에 구름이 사라지지 않았고(방문체크 되지 않은 것이 구름이 사라지지 않은 칸) 물의 양이 2 이상인 경우
				if (!visited[i][j] && map[i][j] >= 2) {
					q.add(new Cloud(i, j));
					map[i][j] -= 2;
				}
			}
		}
		
		visited = new boolean[N][N]; // M번만큼 반복하기 때문에 과정이 한번 끝나면 방문배열 초기화 해줌
	}

}
