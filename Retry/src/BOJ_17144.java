/**
 * 17144 미세먼지 안녕!
 * https://www.acmicpc.net/problem/17144
 * 
 * @author minchae
 * @date 2024. 2. 15.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17144 {
	
	static class Pair {
		int x;
		int y;
		int size;
		
		public Pair(int x, int y, int size) {
			this.x = x;
			this.y = y;
			this.size = size;
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int R, C, T;
	static int[][] map; // 공기 청정기 1, 나머지 값은 미세먼지의 양
	
	static int[] pos = new int[2]; // 공기청정기 위치
	static Queue<Pair> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		
		int idx = 0;
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == -1) {
					pos[idx++] = i;
				}
			}
		}
		
		while (T-- > 0) {
			checkDust();
			spread();
			operate();
		}
		
		System.out.println(getDustCnt());
	}
	
	// 먼지 체크
	private static void checkDust() {
		q = new LinkedList<>();
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					q.add(new Pair(i, j, map[i][j])); // 먼지 위치 저장
				}
			}
		}
	}
	
	// 미세먼지 확산
	private static void spread() {
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			int cnt = 0;
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (isRange(nx, ny) && map[nx][ny] != -1) {
					map[nx][ny] += cur.size / 5;
					cnt++;
				}
			}
			
			map[cur.x][cur.y] -= (cur.size / 5) * cnt;
		}
	}
	
	// 공기청정기 작동
	private static void operate() {
		int top = pos[0]; // 공기청정기 위쪽
        int down = pos[1]; // 공기청정기 아래쪽

        // 바람이 불면 먼지가 바람의 방향대로 모두 한 칸씩 이동
        // 위쪽은 반시계 방향으로 바람을 일으킴

        // 1) 아래로 이동
        for (int i = top - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }

        // 2) 왼쪽으로 이동
        for (int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }

        // 3) 위로 이동
        for (int i = 0; i < top; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }

        // 4) 오른쪽으로 이동
        for (int i = C - 1; i > 1; i--) {
            map[top][i] = map[top][i - 1];
        }

        map[top][1] = 0; // 돌풍에서 나오는 바람은 먼지가 없는 바람이기 때문에 바로 옆에는 0을 넣어줌

        // 아래쪽은 시계 방향으로 바람을 일으킴

        // 1) 위로 이동
        for (int i = down + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }

        // 2) 왼쪽으로 이동
        for (int i = 0; i < C - 1; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }

        // 3) 아래로 이동
        for (int i = R - 1; i > down; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }

        // 4) 오른쪽로 이동
        for (int i = C - 1; i > 1; i--) {
            map[down][i] = map[down][i - 1];
        }

        map[down][1] = 0; // 공기청정기에서 나오는 바람은 먼지가 없는 바람이기 때문에 바로 옆에는 0을 넣어줌
	}
	
	// 남아있는 먼지의 양
	private static int getDustCnt() {
		int cnt = 0;
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					cnt += map[i][j];	
				}
			}
		}
		
		return cnt;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}

}
