/**
 * 3190 뱀
 * https://www.acmicpc.net/problem/3190
 * 
 * @author minchae
 * @date 2024. 2. 5.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3190 {
	
	static class Direction {
		int time;
		char info; // 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향
		
		public Direction(int time, char info) {
			this.time = time;
			this.info = info;
		}
	}
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// 상좌하우 (회전시키기 편하게 설정)
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int N;
	static int[][] map; // 사과가 있는 곳은 1, 뱀이 있는 곳은 2
	
	static Queue<Direction> direction = new LinkedList<>(); // 방향 전환 정보 저장
	static Deque<Pair> snake = new ArrayDeque<>(); // 뱀의 머리와 꼬리가 따로 움직이기 때문에 Deque 사용

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		
		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			
			map[x][y] = 1;
		}
		
		int L = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int X = Integer.parseInt(st.nextToken());
			char C = st.nextToken().charAt(0);
			
			direction.add(new Direction(X, C));
		}
		
		// 뱀 초기 위치 저장
		map[0][0] = 2;
		snake.add(new Pair(0, 0));
		
		int dir = 3; // 뱀의 초기 방향은 오른쪽

		System.out.println(moveSnake(dir));
	}
	
	private static int moveSnake(int dir) {
		int time = 0;
		
		// 벽 또는 자기자신의 몸과 부딪힐 때까지 진행
		while (true) {
			time++;
			
			// 머리가 이동할 다음 위치 구함
			int nx = snake.peekFirst().x + dx[dir];
			int ny = snake.peekFirst().y + dy[dir];
			
			// 벽에 부딫히거나 자기자신의 몸에 부딫히는 경우
			if (!isRange(nx, ny) || map[nx][ny] == 2) {
				return time;
			}
			
			// 이동한 칸에 사과가 없는 경우 꼬리가 위치한 칸을 비워줌
			if (map[nx][ny] == 0) {
				Pair tail = snake.pollLast();
				map[tail.x][tail.y] = 0;
			}
			
			// 사과가 있는 경우 해당 칸에 사과가 없어지고 뱀의 머리를 위치 시킴
			map[nx][ny] = 2;
			snake.addFirst(new Pair(nx, ny));
			
			// 방향 전환 정보가 있고 해당 방향 전환 정보에 있는 시간만큼 흘렀을 경우
			if (!direction.isEmpty() && direction.peek().time == time) {
				char info = direction.poll().info;
				
				dir = (dir + (info == 'L' ? 1 : 3)) % 4; // L, R에 따라 반시계/시계 방향으로 90도 회전
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
