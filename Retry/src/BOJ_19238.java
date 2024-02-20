/**
 * 19238 스타트 택시
 * https://www.acmicpc.net/problem/19238
 * 
 * @author minchae
 * @date 2024. 2. 20.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_19238 {
	
	static class Node implements Comparable<Node> {
		int x;
		int y;
		int d; // 거리
		
		public Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public int compareTo(BOJ_19238.Node o) {
			if (this.d == o.d) {
				if (this.x == o.x) {
					return this.y - o.y;
				}
				
				return this.x - o.x;
			}
			
			return this.d - o.d;
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M, fuel;
	
	static int[][] map; // 빈 칸 0, 벽 -1, 승객 번호
	static int sx, sy; // 택시 위치
	
	static Node[] passenger;
	static Node[] destination;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		passenger = new Node[M + 1];
		destination = new Node[M + 1];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 1) {
					map[i][j] = -1;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		sx = Integer.parseInt(st.nextToken()) - 1;
		sy = Integer.parseInt(st.nextToken()) - 1;
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int px = Integer.parseInt(st.nextToken()) - 1;
			int py = Integer.parseInt(st.nextToken()) - 1;
			
			passenger[i] = new Node(px, py, 0);
			map[px][py] = i;
			
			int ex = Integer.parseInt(st.nextToken()) - 1;
			int ey = Integer.parseInt(st.nextToken()) - 1;
			
			destination[i] = new Node(ex, ey, 0);
		}
		
		// 승객 수만큼 반복
		for (int i = 0; i < M; i++) {
            int idx = find();

            if (idx == -1) {
                System.out.println(-1);
                return;
            }

            if (!move(destination[idx])) {
            	System.out.println(-1);
                return;
            }
        }
		
		System.out.println(fuel);
	}
	
	// 태울 승객 찾고 번호 반환
	private static int find() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][N];
		
		pq.add(new Node(sx, sy, 0));
		visited[sx][sy] = true;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// 승객을 발견한 경우
			if (map[cur.x][cur.y] > 0) {
				fuel -= cur.d;
				
				int num = map[cur.x][cur.y];
				map[cur.x][cur.y] = 0;
				
				// 택시 위치 갱신
				sx = cur.x;
				sy = cur.y;
				
				return fuel >= 0 ? num : -1; 
				
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == -1) {
					continue;
				}
				
				pq.add(new Node(nx, ny, cur.d + 1));
				visited[nx][ny] = true;
			}
		}
		
		return -1;
	}
	
	// 승객 태우고 목적지까지 이동
	private static boolean move(Node end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[][] visited = new boolean[N][N];
		
		pq.add(new Node(sx, sy, 0));
		visited[sx][sy] = true;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// 목적지에 도착한 경우
			if (cur.x == end.x && cur.y == end.y) {
				fuel -= cur.d;
				
				if (fuel < 0) {
					return false;
				}
				
				// 택시 위치 갱신
				sx = end.x;
				sy = end.y;
				
				fuel += cur.d * 2; // 연료 충전
				
				return true;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == -1) {
					continue;
				}
				
				pq.add(new Node(nx, ny, cur.d + 1));
				visited[nx][ny] = true;
			}
		}
		
		return false;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
