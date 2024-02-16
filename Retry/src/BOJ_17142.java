/**
 * 17142 연구소 3
 * https://www.acmicpc.net/problem/17142
 * 
 * @author minchae
 * @date 2024. 2. 16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17142 {
	
	static class Pair {
		int x;
		int y;
		int time;
		
		public Pair(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M;
	static int[][] map;
	
	static ArrayList<Pair> virus = new ArrayList<>();
	static int empty = 0;
	static Pair[] selected;
	
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		selected = new Pair[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 0) {
					empty++;
				}
				
				if (map[i][j] == 2) {
					virus.add(new Pair(i, j, 0));
				}
			}
		}
		
		if (empty == 0) {
			System.out.println(0);
		} else {
			selectVirus(0, 0);
			System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);	
		}
	}
	
	// M개의 활성 바이러스 선택
	private static void selectVirus(int depth, int start) {
		if (depth == M) {
			spread(empty);
			return;
		}
		
		for (int i = start; i < virus.size(); i++) {
			selected[depth] = virus.get(i);
			selectVirus(depth + 1, i + 1);
		}
	}
	
	// 바이러스 퍼뜨리기
	private static void spread(int cnt) {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		// 활성 바이러스가 있는 곳은 방문처리 하고 큐에 넣어줌
		for (Pair p : selected) {
			q.add(p);
			visited[p.x][p.y] = true;
		}
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				// 범위를 벗어나거나 이미 바이러스를 퍼뜨렸거나 벽인 경우 다음으로 넘어감
				if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 1) {
					continue;
				}
				
				q.add(new Pair(nx, ny, cur.time + 1));
				visited[nx][ny] = true;
				
				// 빈 칸 개수 감소
				if (map[nx][ny] == 0) {
					cnt--;
				}
				
				// 빈 칸이 남아있지 않은 경우 최소 시간 갱신 후 종료
				if (cnt == 0) {
					answer = Math.min(answer, cur.time + 1);
					return;
				}
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
