/**
 * 17472 다리 만들기 2
 * https://www.acmicpc.net/problem/17472
 * 
 * @author minchae
 * @date 2024. 8. 20.
 * 
 * DFS, BFS, 크루스칼 사용
 */

import java.util.*;
import java.io.*;

public class Main2 {
	
	static class Node {
		int x;
		int y;
		int cnt;
		
		public Node (int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int s;
		int e;
		int w;
		
		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Edge o) {
			return this.w - o.w;
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M;
	static int[][] map; // 0은 바다, 1은 땅
	
	static int[] parent;
	static PriorityQueue<Edge> pq; // 건설된 다리 (간선) 정보 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int num = 1;
		boolean[][] visited = new boolean[N][M];
		
		// 섬을 그룹으로 묶음
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] || map[i][j] == 0) {
					continue;
				}
				
				dfs(i, j, num++, visited);
			}
		}
		
		// 섬마다 다리를 놓음
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					bfs(i, j);
				}
			}
		}
		
		parent = new int[num];
		
		for (int i = 1; i < num; i++) {
			parent[i] = i; // 자기 자신으로 초기화
		}
		
		System.out.println(kruskal(num));
	}
	
	// 섬에 번호를 새김
	private static void dfs(int x, int y, int num, boolean[][] visited) {
		visited[x][y] = true;
		map[x][y] = num;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 0) {
				continue;
			}
			
			dfs(nx, ny, num, visited);
		}
	}
	
	// 특정 섬에서 다른 섬까지 다리를 놓음
	private static void bfs(int x, int y) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		// 4방향으로 다리를 건설함
		for (int i = 0; i < 4; i++) {
			q.add(new Node(x, y, 0));
			visited[x][y] = true;
			
			while (!q.isEmpty()) {
				Node cur = q.poll();
				
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == map[x][y]) {
					continue;
				}
				
				if (map[nx][ny] == 0) { // 바다인 경우
					q.add(new Node(nx, ny, cur.cnt + 1)); // 다리 길이 증가
					visited[nx][ny] = true;
				} else { // 다른 섬을 만난 경우
					// 다리 길이가 2이상인 경우 간선 정보 큐에 추가
					if (cur.cnt >= 2) {
						pq.add(new Edge(map[x][y], map[nx][ny], cur.cnt));
						break;
					}
				}
			}
		}
	}
	
	// 모든 섬을 연결하는 다리 길이의 최솟값 구함 (크루스칼)
	private static int kruskal(int num) {
		int result = 0;
		
		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			
			// 사이클이 형성되지 않는 경우
			if (find(cur.s) != find(cur.e)) {
				union(cur.s, cur.e);
				result += cur.w;
			}
		}
		
		// 모든 섬이 연결되어 있는지 확인
		int root = parent[1];
		
		// 섬 번호가 작은 것을 부모로 저장했기 때문에 1번 섬이 최상위 노드
		for (int i = 2; i < num; i++) {
			if (find(i) != root) {
				return -1;
			}
		}
		
		return result;
	}
	
	private static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
	
	private static void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		
		if (rootX < rootY) {
			parent[rootY] = rootX;
		} else {
			parent[rootX] = rootY;
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
