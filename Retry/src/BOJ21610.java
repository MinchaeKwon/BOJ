/**
 * 백준 21610 마법사 상어와 비바라기
 * https://www.acmicpc.net/problem/21610
 * 
 * @author minchae
 * @date 2023. 10. 11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21610 {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// ←, ↖, ↑, ↗, →, ↘, ↓, ↙
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	static int N, M;
	
	static int[][] map;
	
	static Queue<Pair> q = new LinkedList<>();
	static boolean[][] visited; // 사라진 구름을 체크

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 구름 초기 위치 추가
		q.add(new Pair(N - 1, 0));
		q.add(new Pair(N - 1, 1));
		q.add(new Pair(N - 2, 0));
		q.add(new Pair(N - 2, 1));
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int d = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N][N];
			
			move(d, s);
			magic();
			makeCloud();
		}
		
		int result = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result += map[i][j];
			}
		}
		
		System.out.println(result);
	}
	
	// 구름 이동시키고 바구니의 물을 증가시킴
	private static void move(int d, int s) {
		for (Pair cloud : q) {
//			cloud.x = (N + cloud.x + dx[d] * (s % N)) % N;
//			cloud.y = (N + cloud.y + dy[d] * (s % N)) % N;

			cloud.x = (cloud.x + dx[d] * s + s * N) % N;
			cloud.y = (cloud.y + dy[d] * s + s * N) % N;
			
			map[cloud.x][cloud.y]++; // 물의 양 증가시킴
		}
	}
	
	// 구름이 모두 없애고 물복사버그 마법 시전
	private static void magic() {
		// 구름이 모두 없어질 때까지 반복
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			visited[cur.x][cur.y] = true; // 구름이 사라졌다는 표시를 해줌
			
			int cnt = 0;
			
			// 대각선 방향에 물이 있는 바구니가 있는지 확인
			for (int i = 1; i <= 7; i += 2) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				// 범위를 벗어나지 않고 물이 있는 바구니인 경우
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] > 0) {
					cnt++;
				}
			}
			
			map[cur.x][cur.y] += cnt; // 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가
		}
	}
	
	// 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어듬 (구름이 생기는 칸은 구름이 사라진 칸이 아니어야 함)
	private static void makeCloud() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 물의 양이 2 이상이고 구름이 사라진 칸이 아닌 경우
				if (map[i][j] >= 2 && !visited[i][j]) {
					q.add(new Pair(i, j)); // 구름 생김
					map[i][j] -= 2; // 물의 양 2 감소
				}
			}
		}
	}

}
