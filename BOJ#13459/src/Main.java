
/**
 * 13459 구슬 탈출
 * https://www.acmicpc.net/problem/13459
 * 
 * @author minchae
 * @date 2024. 1. 30.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Ball {
		int rx, ry;
		int bx, by;
		int cnt;

		public Ball(int rx, int ry, int bx, int by, int cnt) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
		}
	}

	// 상하좌우
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int N, M;

	static char[][] map; // '.'은 빈 칸, '#'은 공이 이동할 수 없는 장애물 또는 벽, 'O'는 구멍의 위치, 'R'은 빨간 구슬의 위치, 'B'는 파란 구슬의
							// 위치

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];

		int rx = 0, ry = 0, bx = 0, by = 0;

		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();

			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'R') {
					rx = i;
					ry = j;
				} else if (map[i][j] == 'B') {
					bx = i;
					by = j;
				}
			}
		}

		System.out.println(bfs(new Ball(rx, ry, bx, by, 0)));
	}

	private static int bfs(Ball start) {
		Queue<Ball> q = new LinkedList<>();
		boolean[][][][] visited = new boolean[N][M][N][M];

		q.add(start);
		visited[start.rx][start.ry][start.bx][start.by] = true;

		while (!q.isEmpty()) {
			Ball cur = q.poll();

			if (cur.cnt > 10) {
				return 0;
			}

			// 파란 구슬이 구멍으로 빠지면 실패이기 때문에 다음으로 넘어감
			if (map[cur.bx][cur.by] == 'O') {
				continue;
			}

			// 빨간 구슬이 구멍으로 빠지면 종료
			if (map[cur.rx][cur.ry] == 'O') {
				return 1;
			}

			// 상하좌우로 기울이기
			for (int i = 0; i < 4; i++) {
				int nbx = cur.bx;
				int nby = cur.by;

				// 장애물을 만나기 전까지 진행
				while (map[nbx + dx[i]][nby + dy[i]] != '#') {
					nbx += dx[i];
					nby += dy[i];

					// 출구를 만난 경우 while문 종료
					if (map[nbx][nby] == 'O') {
						break;
					}
				}

				int nrx = cur.rx;
				int nry = cur.ry;

				// 장애물을 만나기 전까지 진행
				while (map[nrx + dx[i]][nry + dy[i]] != '#') {
					nrx += dx[i];
					nry += dy[i];

					// 출구를 만난 경우 while문 종료
					if (map[nrx][nry] == 'O') {
						break;
					}
				}

				// 빨간 구슬과 파란 구슬이 이동한 위치가 같고 빨간 구슬이 출구를 만나지 않은 경우
				if (nrx == nbx && nry == nby && map[nrx][nry] != 'O') {
					int red = Math.abs(nrx - cur.rx) + Math.abs(nry - cur.ry); // 빨간 구슬 이동거리
					int blue = Math.abs(nbx - cur.bx) + Math.abs(nby - cur.by); // 파란 구슬 이동거리

					if (red > blue) {
						// 빨간 구슬 이동거리가 더 길 경우 이전 위치로 되돌림
						nrx -= dx[i];
						nry -= dy[i];
					} else {
						nbx -= dx[i];
						nby -= dy[i];
					}
				}

				if (!visited[nrx][nry][nbx][nby]) {
					q.add(new Ball(nrx, nry, nbx, nby, cur.cnt + 1));
					visited[nrx][nry][nbx][nby] = true;
				}
			}
		}

		return 0;
	}

}
