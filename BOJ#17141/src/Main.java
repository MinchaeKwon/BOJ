/**
 * 17141 연구소 2
 * https://www.acmicpc.net/problem/17141
 * 
 * @author minchae
 * @date 2024. 1. 29.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Virus {
	int x;
	int y;
	int time;
	
	public Virus(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}
}

public class Main {
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M;
	
	static int[][] map; // 빈 칸 0, 벽 1, 바이러스를 놓을 수 있는 곳 2
	
	static ArrayList<Virus> candidate = new ArrayList<>(); // 바이러스를 놓을 수 있는 위치 후보
	static Virus[] virus; // 바이러스를 놓은 위치 저장
	
	static int spread; // 바이러스를 퍼뜨릴 수 있는 곳의 개수 (빈 칸 + M개를 제외한 바이러스 후보 위치)
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		virus = new Virus[M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 0) {
					spread++;
				}
				
				// 바이러스를 놓을 수 있는 곳인 경우
				if (map[i][j] == 2) {
					spread++; // 바이러스를 놓은 곳을 제외한 후보 위치에도 바이러스를 퍼뜨려야 하기 때문에 증가시킴
					candidate.add(new Virus(i, j, 0));
				}
			}
		}
		
		spread -= M; // 바이러스를 놓은 곳은 제외함
		
		if (spread == 0) {
			System.out.println(0);
		} else {
			select(0, 0);
			System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
		}
		
	}
	
	private static void select(int start, int depth) {
		// 바이러스를 M군데 놓은 경우
		if (depth == M) {
			bfs(spread);
			return;
		}
		
		for (int i = start; i < candidate.size(); i++) {
			virus[depth] = candidate.get(i);
			select(i + 1, depth + 1);
		}
	}
	
	// 바이러스 퍼뜨림, 매개변수 : 바이러스를 퍼뜨릴 수 있는 곳의 개수, 바이러스를 놓은 곳의 위치를 저장한 리스트
	private static void bfs(int cnt) {
		Queue<Virus> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		// 바이러스 놓은 위치를 큐에 넣고 방문처리 해줌
		for (Virus v : virus) {
			q.add(v);
			visited[v.x][v.y] = true;
		}
		
		while (!q.isEmpty()) {
			Virus cur  = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				// 범위를 벗어나거나 이미 방문한 곳이거나 벽인 경우 다음으로 넘어감
				if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 1) {
					continue;
				}
				
				// 바이러스를 퍼뜨릴 수 있는 곳인 경우
				q.add(new Virus(nx, ny, cur.time + 1));
				visited[nx][ny] = true;

				cnt--;
				
				// 바이러스를 다 퍼뜨린 경우 최단 시간 갱신 후 종료
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
