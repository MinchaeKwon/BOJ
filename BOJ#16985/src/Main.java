/**
 * 16985 Maaaaaaaaaze
 * https://www.acmicpc.net/problem/16985
 * 
 * @author minchae
 * @date 2024. 3. 11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int x;
		int y;
		int z;
		int dist;
		
		public Node(int x, int y, int z, int dist) {
			this.x = x;
			this.y = y;
			this.z = z;
			this.dist = dist;
		}
	}
	
	static int[] dx = {-1, 1, 0, 0, 0, 0};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int[] dz = {0, 0, 0, 0, 1, -1};
	
	static int[][][] map = new int[5][5][5];
	static int[][][] copy;
	
	static int[] order = new int[5];
	static boolean[] used = new boolean[5];
	
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int k = 0; k < 5; k++) {
					map[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}

		rotateOrder(0);
		
		System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
	}
	
	// 각 판마다 얼마나 회전할지 정함
	private static void rotateOrder(int depth) {
		// 모든 판을 회전시킨 경우
		if (depth == 5) {
			stackOrder(0);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			rotate(depth);
			rotateOrder(depth + 1);
		}
	}
	
	// 90도 회전
	private static void rotate(int idx) {
		int[][] temp = new int[5][5];
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				temp[i][j] = map[idx][5 - j - 1][i]; // 특정 판 회전시킴
			}
		}
		
		map[idx] = Arrays.copyOf(temp, 5);
	}
	
	// 판 쌓는 순서 정하기
	private static void stackOrder(int depth) {
		// 판 쌓는 순서를 다 정한 경우 (입구 - 출구) 거리 구하기
		if (depth == 5) {
			bfs();
			return;
		}

		for (int i = 0; i < 5; i++) {
			if (!used[i]) {
				used[i] = true;
				order[depth] = i;

				stackOrder(depth + 1);

				used[i] = false;
			}
		}
	}
	
	// 미로 입구에서 출구까지의 거리 구함
	private static void bfs() {
		int[][][] copy = new int[5][5][5];
		
		// 순서에 따라 판 쌓기
		for (int i = 0; i < 5; i++) {
			copy[i] = Arrays.copyOf(map[order[i]], 5);
		}
		
		// 입구나 출구에 들어갈 수 없는 경우 바로 종료
		if (copy[0][0][0] == 0 || copy[4][4][4] == 0) {
			return;
		}
		
		Queue<Node> q = new LinkedList<>();
		boolean[][][] visited = new boolean[5][5][5];
		
		q.add(new Node(0, 0, 0, 0));
		visited[0][0][0] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			// 출구 발견한 경우
			if (cur.x == 4 && cur.y == 4 && cur.z == 4) {
				answer = Math.min(answer, cur.dist);
				continue;
			}
			
			for (int i = 0; i < 6; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				int nz = cur.z + dz[i];
				
				if (!isRange(nx, ny, nz) || visited[nx][ny][nz] || copy[nx][ny][nz] == 0) {
					continue;
				}
				
				q.add(new Node(nx, ny, nz, cur.dist + 1));
				visited[nx][ny][nz] = true;
			}
		}
	}
	
	private static boolean isRange(int x, int y, int z) {
		return x >= 0 && x < 5 && y >= 0 && y < 5 && z >= 0 && z < 5;
	}

}
