/**
 * 17135 캐슬 디펜스
 * https://www.acmicpc.net/problem/17135
 * 
 * @author minchae
 * @date 2024. 9. 4.
 * */

import java.util.*;
import java.io.*;

public class Main {
	
	static class Node implements Comparable<Node> {
		int x;
		int y;
		int d;
		
		public Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			if (this.d == o.d) {
				return Integer.compare(this.y, o.y);
			}
			
			return Integer.compare(this.d, o.d);
		}
	}
	
	static int N, M, D;
	
	static int[][] map;
	static int[][] copy;
	
	static int[] pos; // 궁수의 열 위치 저장
	
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		pos = new int[3];
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comb(0, 0);
		
		System.out.println(answer);
	}
	
	private static void comb(int depth, int start) {
		if (depth == 3) {
			play();
			return;
		}
		
		// 성은 (N + 1) 위치에 있기 때문에 열만 선택함
		for (int i = start; i < M; i++) {
			pos[depth] = i;
			comb(depth + 1, i + 1);
		}
	}
	
	private static void play() {
		copy = new int[N][M]; // 맵을 복사해서 사용
		
		for (int i = 0; i < N; i++) {
			copy[i] = Arrays.copyOf(map[i], M);
		}
		
		int cnt = attack();
		
		answer = Math.max(answer, cnt); // 최댓값 갱신
	}
	
	private static int attack() {
		int cnt = 0;
		
		// 한 턴마다 적은 한 칸 아래로 내려가기 때문에 n번 진행하면 모든 적이 사라지게 됨
		for (int n = 0; n < N; n++) {
			ArrayList<Node> target = new ArrayList<>();
			
			for (int y : pos) {
				ArrayList<Node> list = new ArrayList<>();
				
				// 마지막 행부터 시작 -> 이미 진행된 턴의 행 위로는 보지 않음
				for (int i = N - 1; i >= n; i--) {
					for (int j = 0; j < M; j++) {
						if (copy[i][j] == 0) {
							continue;
						}
						
						// 적과 현재 궁수 사이의 거리 구함
						int dist = getDist(i, j, N, y);
						
						// 궁수와의 거리가 D이하인 적만 선택
						if (dist <= D) {
							list.add(new Node(i, j, dist));
						}
					}
				}
				
				if (list.isEmpty()) {
					continue;
				}
				
				// 그 중에서 가장 가깝거나 가장 왼쪽에 있는 적 선택
				Collections.sort(list);
				target.add(list.get(0));
			}
			
			for (Node cur : target) {
				// 궁수는 같은 적을 동시에 공격할 수 있기 때문에 적이 있을 경우에만 개수 증가
				if (copy[cur.x][cur.y] == 1) {
					copy[cur.x][cur.y] = 0;
					cnt++;
				}
			}
			
			down(); // 공격 끝나고 한 칸 아래로 이동
		}
		
		return cnt;
	}
	
	// 거리 구하기
	private static int getDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	
	// 모든 적이 아래로 한 칸 이동
	private static void down() {
		// 맨 아래 행부터 시작
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = i == 0 ? 0 : copy[i - 1][j];
			}
		}
	}

}