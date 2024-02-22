/**
 * 20056 마법사 상어와 파이어볼
 * https://www.acmicpc.net/problem/20056
 * 
 * @author minchae
 * @date 2024. 2. 22.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_20056_2 {
	
	static class Fireball {
		int x;
		int y;
		int m; // 질량
		int s; // 속력
		int d; // 방향
		
		public Fireball(int x, int y, int m, int s, int d) {
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
	
	static ArrayList<Fireball> fireballs = new ArrayList<>();
	static ArrayList<Fireball>[][] map;

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
			
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			fireballs.add(new Fireball(x, y, m, s, d));
		}
		
		while (K-- > 0) {
			move();
			calculate();
		}

		// 남아있는 파이어볼 질량의 합 출력
		int answer = 0;
		
		for (Fireball b : fireballs) {
			answer += b.m;
		}
		
		System.out.println(answer);
	}
	
	private static void move() {
		for (Fireball b : fireballs) {
			b.x = (N + b.x + dx[b.d] * (b.s % N)) % N;
			b.y = (N + b.y + dy[b.d] * (b.s % N)) % N;
			
			map[b.x][b.y].add(b);
		}
	}
	
	private static void calculate() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = map[i][j].size();
				
				// 2개 미만인 경우 다음으로 넘어감
				if (cnt >= 2) {
					int massSum = 0;
					int speedSum = 0;
					boolean odd = true; // 홀수만 있는지
					boolean even = true; // 짝수만 있는지
					
					for (Fireball b : map[i][j]) {
						massSum += b.m;
						speedSum += b.s;
						
						if (b.d % 2 == 0) {
							odd = false;
						} else {
							even = false;
						}
						
						fireballs.remove(b);
					}
					
					int mass = massSum / 5;
					
					if (mass > 0) {
						int speed = speedSum / cnt;
						
						int start = odd || even ? 0 : 1;
						
						for (int d = start; d < 8; d += 2) {
							fireballs.add(new Fireball(i, j, mass, speed, d));
						}
					}
				}
				
				map[i][j].clear();
			}
		}
	}

}
