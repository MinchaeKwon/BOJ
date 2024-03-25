/**
 * 1194 달이 차오른다, 가자.
 * https://www.acmicpc.net/problem/1194
 * 
 * @author minchae
 * @date 2024. 3. 25.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int x;
		int y;
		int move;
		int key;
		
		public Node(int x, int y, int move, int key) {
			this.x = x;
			this.y = y;
			this.move = move;
			this.key = key;
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M;
	static char[][] map;
	
	static int sx, sy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {
					sx = i;
					sy = j;
					map[i][j] = '.';
				}
			}
		}

		System.out.println(bfs());
	}
	
	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[N][M][64];
		
		q.add(new Node(sx, sy, 0, 0));
		visited[sx][sy][0] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (map[cur.x][cur.y] == '1') {
				return cur.move;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!isRange(nx, ny) || visited[nx][ny][cur.key] || map[nx][ny] == '#') {
					continue;
				}
				
				if (map[nx][ny] >= 'a' && map[nx][ny] <= 'z') { // 열쇠
					int key = cur.key | (1 << (map[nx][ny] - 'a'));
					
					q.add(new Node(nx, ny, cur.move + 1, key));
					visited[nx][ny][key] = true;
				} else if (map[nx][ny] >= 'A' && map[nx][ny] < 'Z') { // 문
					if ((cur.key & (1 << (map[nx][ny] - 'A'))) > 0) { // 열쇠가 있는 경우
						q.add(new Node(nx, ny, cur.move + 1, cur.key));
						visited[nx][ny][cur.key] = true;
					}
				} else {
					q.add(new Node(nx, ny, cur.move + 1, cur.key));
					visited[nx][ny][cur.key] = true;
				}
			}
		}
		
		return -1;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
