/**
 * 16236 아기 상어
 * https://www.acmicpc.net/problem/16236
 * 
 * @author Minchae Gwon
 * @date 2021.3.3
 * 
 * 0 빈 칸
 * 1, 2, 3, 4, 5, 6 칸에 있는 물고기의 크기
 * 9 아기 상어의 위치
 * 아기 상어 초기 상태는 2
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Shark {
	int x;
	int y;

	Shark(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	// 상하좌우
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int N;
	static int[][] map;
	static int[][] dist; // 아기 상어의 이동 거리 확인
	
	static int size = 2; // 아기 상어의 초기 크기
	static int sx, sy; // 아기 상어의 초기 좌표 
	static int fish = 0; // 아기 상어가 먹은 물고기 개수
	static int minD, minX, minY; // 먹을 수 있는 물고기까지의 최소 서기, 최소 x값, 최소 y값
	static int result = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());

				// 아기 상어 초기 위치 저장
				if (map[i][j] == 9) {
					sx = i;
					sy = j;
					map[i][j] = 0;
				}
				
			}
		}

		while (true) {
			dist = new int[N][N];
			
			minX = Integer.MAX_VALUE;
			minY = Integer.MAX_VALUE;
			minD = Integer.MAX_VALUE;

			bfs(sx, sy);

			if (minX != Integer.MAX_VALUE) { // 물고기를 발견한 경우
				result += minD; // 물고기까지의 이동 거리를 더해줌(이동거리가 이동 시간임

				fish++;
				
				// 먹은 물고기의 개수가 아기 상어 크기와 같은 경우
				if (fish == size) {
					// 아기 상어 크기 증가, 먹은 물고기를 개수 초기화
					size++;
					fish = 0;
				}

				// 아기 상어 이동
				map[minX][minY] = 0;
				sx = minX;
				sy = minY;
			}
			else {
				break;
			}
		}

		System.out.println(result);
	}

	// 아기 상어를 이동시키면서 먹을 수 있는 물고기 탐색
	public static void bfs(int x, int y) {
		Queue<Shark> q = new LinkedList<Shark>();

		q.add(new Shark(x, y));

		while (!q.isEmpty()) {
			Shark p = q.poll();
			x = p.x;
			y = p.y;

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				// 범위를 벗어나지 않고 방문하지 않은 곳이고 아기 상어의 크기보다 같거나 작을 경우 -> 이동을 하거나 물고기를 먹을 수 있음
				if (nx >= 0 && nx < N && ny >= 0 & ny < N && dist[nx][ny] == 0 && map[nx][ny] <= size) {
					dist[nx][ny] = dist[x][y] + 1; // 해당 위치까지의 이동 거리 1 증가

					// 해당 위치에 물고기가 있고 아기 상어 크기보다 작은 경우 -> 물고기를 먹을 수 있음
					if (map[nx][ny] != 0 && map[nx][ny] < size) {
						// 해당 위치의 물고기까지의 이동 거리가 더 작은 경우
						if (dist[nx][ny] < minD) {
							minX = nx;
							minY = ny;
							minD = dist[nx][ny];
						}
						else if (dist[nx][ny] == minD) { // 거리가 가까운 물고기가 많은 경우
							if (nx < minX) { // 가장 위에 있는 물고기를 먹음
								minX = nx;
								minY = ny;
							}
							else if (nx == minX) { // 가장 위에 있는 물고기가 많은 경우
								if (ny < minY) { // 그중에서 가장 왼쪽에 있는 물고기를 먹음
									minX = nx;
									minY = ny;
								}
							}
						}
					}
					
					q.add(new Shark(nx, ny));
				}
			}
		}

	}
}