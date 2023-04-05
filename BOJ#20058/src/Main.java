/**
 * 마법사 상어와 파이어스톰
 * https://www.acmicpc.net/problem/20058
 * 
 * @author minchae
 * @date 2023. 4. 5.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, Q;
	static int[][] map;
	static int[] step;
	
	static int iceSum = 0; // 남아있는 얼음의 양
	static int iceSize = 0; // 가장 큰 얼음 덩어리의 크기
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		
		N = (int) Math.pow(2, N);
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			map = divide(Integer.parseInt(st.nextToken()));
			map = meltingIce();
		}
		
		bfs();
		
		System.out.println(iceSum);
		System.out.println(iceSize);

	}
	
	// 격자 나누는 함수
	private static int[][] divide(int L) {
		// 배열 복사해서 사용 -> 원본 배열에서 바로 90도 회전시키면 제대로 된 답이 나오지 않음
		int[][] copyMap = new int[N][N];
		
		L = (int) Math.pow(2, L);
		
		for (int i = 0; i < N; i += L) {
			for (int j = 0; j < N; j += L) {
				rotate(i, j, L, copyMap);
			}
		}
		
		return copyMap;
	}
	
	// 90도 회전 시키기
	private static void rotate(int x, int y, int L, int[][] copyMap) {
		// 나눠진 격자 크기만큼 돌면서 90도 회전시킴
		for (int i = 0; i < L; i++) {
			for (int j = 0; j < L; j++) {
				copyMap[x + i][y + j] = map[x + L - 1 - j][y + i];
			}
		}
	}
	
	// 얼음 녹이기
	private static int[][] meltingIce() {
		// 배열 복사해서 사용 -> 얼음을 동시에 녹이는 것이기 때문에 원본 배열의 얼음을 바로 녹여버리면 제대로 된 답을 얻을 수 없음
		int[][] copyMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 0;
				
				// 해당 칸에 얼음이 있는 경우에만 인접한 칸 탐색 (녹일 얼음이 없으면 탐색할 필요 없음)
				if (map[i][j] > 0) {
					// 상하좌우 탐색하면서 인접한 칸에 얼음이 있는지 확인
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						// 범위를 벗어나지 않는 경우
						if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
							// 인접한 칸에 얼음이 있는 경우
							if (map[nx][ny] > 0) {
								cnt++;
							}	
						}
					}
					
					// 인접한 칸 중에 얼음이 있는 칸 개수가 3개 미만인 경우 해당 칸의 얼음 1 감소시킴
					if (cnt < 3) {
						copyMap[i][j] = map[i][j] - 1;
					}
				}
			}
		}
		
		return copyMap;
	}
	
	// bfs 사용해서 가장 큰 얼음 덩어리 찾기
	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean visited[][] = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				iceSum += map[i][j];
				
				if (map[i][j] > 0 && !visited[i][j]) {
					q.add(new int[]{i, j});
					visited[i][j] = true;
					
					int cnt = 1;
					
					while (!q.isEmpty()) {
						int[] cur = q.poll();
						int cx = cur[0];
						int cy = cur[1];
						
						for (int k = 0; k < 4; k++) {
							int nx = cx + dx[k];
							int ny = cy + dy[k];
							
							// 범위를 벗어나지 않는 경우
							if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
								// 얼음이 있고 방문하지 않은 경우
								if (map[nx][ny] > 0 && !visited[nx][ny]) {
									q.add(new int[]{nx, ny});
									visited[nx][ny] = true;
									
									cnt++;
								}
							}
						}
						
					}
					
					iceSize = Math.max(iceSize, cnt);
				}
				
			}
		}
	}

}
