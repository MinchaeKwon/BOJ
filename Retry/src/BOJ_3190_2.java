/**
 * 3190 뱀
 * https://www.acmicpc.net/problem/3190
 * 
 * @author minchae
 * @date 2024. 4. 13.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190_2 {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Dir {
		int x;
		char c;
		
		public Dir(int x, char c) {
			this.x = x;
			this.c = c;
		}
	}
	
	// 상우하좌
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int N, K, L;
	
	static int[][] map; // 사과 1, 뱀 2
	static int sd; // 뱀의 현재 방향
	static Deque<Pair> dq = new ArrayDeque<>();
	
	static Queue<Dir> q = new LinkedList<>();
	
	static int time;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			map[x][y] = 1;
		}
		
		L = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			
			q.add(new Dir(X, C));
		}
		
		map[0][0] = 2;
		sd = 1;
		dq.add(new Pair(0, 0));
		
		play();
		
		System.out.println(time);
	}
	
	private static void play() {
		while (true) {
			time++;
			
			Pair head = dq.peekFirst();
			
			int nx = head.x + dx[sd];
			int ny = head.y + dy[sd];
			
			if (!isRange(nx, ny) || map[nx][ny] == 2) {
				return;
			}
			
			if (map[nx][ny] == 0) {
				Pair tail = dq.pollLast();
				map[tail.x][tail.y] = 0;
			}
			
			map[nx][ny] = 2;
			dq.addFirst(new Pair(nx, ny));
			
			if (!q.isEmpty() && time == q.peek().x) {
				sd = q.poll().c == 'L' ? (sd + 3) % 4 : (sd + 1) % 4;
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
