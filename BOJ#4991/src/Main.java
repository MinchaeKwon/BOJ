/**
 * 4991 로봇 청소기
 * https://www.acmicpc.net/problem/4991
 * 
 * @author minchae
 * @date 2024. 3. 28.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int x;
		int y;
		int move;
		
		public Node (int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Node (int x, int y, int move) {
			this.x = x;
			this.y = y;
			this.move = move;
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int w, h;
	static char[][] map;
	
	static ArrayList<Node> list;
	static int dist[][];
	
	static int dirty;
	
	static int answer;
	static boolean[] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if (w == 0 && h == 0) {
				break;
			}
			
			map = new char[h][w];
			list = new ArrayList<>();
			
			for (int i = 0; i < h; i++) {
				map[i] = br.readLine().toCharArray();
				
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 'o') {
						list.add(0, new Node(i, j)); // 첫 번째는 무조건 로봇 청소기의 위치
					} else if (map[i][j] == '*') {
						list.add(new Node(i, j));
					}
				}
			}
			
			dirty = 0;
			dist = new int[list.size()][list.size()];
			
			for (int i = 0; i < list.size(); i++) {
				bfs(list.get(i), i);
			}
			
			if (dirty == list.size() - 1) { // 로봇 청소기의 시작 위치는 빼야 하기 때문에 -1 해줌
				answer = Integer.MAX_VALUE;
				check = new boolean[list.size()];
				
				check[0] = true; // 로봇 시작 위치 방문 체크
				dfs(0, 1, 0);
				
				System.out.println(answer);
			} else {
				// 로봇 위치에서 갈 수 있는 더러운 칸의 개수가 (list.size() - 1)보다 작은 경우 방문할 수 없는 더러운 칸이 존재하는 것
				System.out.println(-1);
			}
		}

	}
	
	// 더러운 칸 사이의 거리 구함
	private static void bfs(Node start, int idx) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[h][w];
		
		q.add(start);
		visited[start.x][start.y] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (map[cur.x][cur.y] == '*') {
				// 로봇 시작 위치에서 출발하는 경우에만 치울 수 있는 먼지 개수 증가시킴
				if (idx == 0) {
					dirty++;
				}
				
				// 현재 위치에서 더러운 칸까지의 거리 구함
				for (int i = 0; i < list.size(); i++) {
					Node node = list.get(i);
					
					if (node.x == cur.x && node.y == cur.y) {
						dist[idx][i] = cur.move;
					}
				}
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!isRange(nx, ny) || visited[nx][ny] || map[nx][ny] == 'x') {
					continue;
				}
				
				q.add(new Node(nx, ny, cur.move + 1));
				visited[nx][ny] = true;
			}
		}
	}
	
	// 순열을 통해 최소 거리 찾음
	private static void dfs(int start, int depth, int sum) {
		if (depth == list.size()) {
			answer = Math.min(answer, sum);
			return;
		}
		
		// 로봇부터 시작하기 때문에 첫 번째 더러운 칸부터 시작
		for (int i = 1; i < list.size(); i++) {
			if (!check[i]) {
				check[i] = true;
				dfs(i, depth + 1, sum + dist[start][i]);
				check[i] = false;
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < h && y >= 0 && y < w;
	}

}
