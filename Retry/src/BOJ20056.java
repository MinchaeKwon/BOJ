/**
 * 백준 20056 마법사 상어와 파이어볼
 * https://www.acmicpc.net/problem/20056
 * 
 * @author minchae
 * @date 2023. 10. 11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20056 {
	
	static class FireBall {
		int x;
		int y;
		int m; // 질량
		int s; // 속력
		int d; // 방향
		
		public FireBall(int x, int y, int m, int s, int d) {
			this.x = x;
			this.y = y;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	// ↑, ↗, →, ↘, ↓, ↙, ←, ↖
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	
	static int N, M, K;
	
	static ArrayList<FireBall> fireballs = new ArrayList<>();
	static ArrayList<FireBall>[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			fireballs.add(new FireBall(r, c, m, s, d));
		}
		
		while (K-- > 0) {
			move();
			calculate();
		}
		
		int result = 0;
		
		for (FireBall ball : fireballs) {
			result += ball.m;
		}
		
		System.out.println(result);
	}
	
	// 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동
	private static void move() {
		for (FireBall ball : fireballs) {
//			ball.x = (N + ball.x + dx[ball.d] * (ball.s % N)) % N;
//			ball.y = (N + ball.y + dy[ball.d] * (ball.s % N)) % N;
			
			ball.x = (ball.x + dx[ball.d] * ball.s + ball.s * N) % N;
			ball.y = (ball.y + dy[ball.d] * ball.s + ball.s * N) % N;
			
			map[ball.x][ball.y].add(ball);
		}
	}
	
	// 파이어볼 합치고 나누기
	private static void calculate() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = map[i][j].size(); // 해당 칸에 있는 파이어볼 개수
				
				if (cnt >= 2) {
					int massSum = 0;
					int speedSum = 0;
					boolean odd = true; // 홀수만 있는지
					boolean even = true; // 짝수만 있는지
					
					for (FireBall ball : map[i][j]) {
						massSum += ball.m;
						speedSum += ball.s;
						
						if (ball.d % 2 == 0) {
							odd = false;
						} else {
							even = false;
						}
						
						fireballs.remove(ball);
					}
					
					int mass = massSum / 5;
					
					// 질량이 0보다 클 경우에만 파이어볼을 나눔 (질량이 0인 파이어볼은 소멸됨)
					if (mass > 0) {
						int speed = speedSum / cnt;
						
						int start = odd || even ? 0 : 1;
						
						for (int d = start; d < 8; d += 2) {
							fireballs.add(new FireBall(i, j, mass, speed, d));
						}
					}
					
				}
				
				map[i][j].clear();
			}
		}
		
	}

}
