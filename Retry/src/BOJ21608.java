/**
 * 백준 21608 상어 초등학교
 * https://www.acmicpc.net/problem/21608
 * 
 * @author minchae
 * @date 2023. 10. 11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ21608 {
	
	static class Shark implements Comparable<Shark> {
		int x;
		int y;
		int like; // 인접한 칸 중에 좋아하는 학생이 있는 칸의 개수
		int empty; // 인접한 칸 중에 비어있는 칸의 개수
		
		public Shark(int x, int y, int like, int empty) {
			this.x = x;
			this.y = y;
			this.like = like;
			this.empty = empty;
		}

		@Override
		public int compareTo(BOJ21608.Shark o) {
			if (this.like == o.like) {
				if (this.empty == o.empty) {
					if (this.x == o.x) {
						return this.y - o.y;
					}
					
					return this.x - o.x;
				}
				
				return o.empty - this.empty;
			}
			
			return o.like - this.like;
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N;
	
	static ArrayList<Integer>[] students; // 각 학생이 좋아하는 학생의 번호를 저장
	static int[] order; // 배치할 학생의 순서
	
	static int[][] map; // 배치된 학생의 위치 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		students = new ArrayList[N * N + 1];
		order = new int[N * N + 1];
		map = new int[N][N];
		
		for (int i = 1; i <= N * N; i++) {
			students[i] = new ArrayList<>();
		}
		
		for (int i = 1; i <= N * N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			
			order[i] = num;
			
			for (int j = 0; j < 4; j++) {
				students[num].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		for (int i = 1; i <= N * N; i++) {
			placement(order[i]);
		}

		System.out.println(getScore());
	}
	
	// 자리배치
	private static void placement(int num) {
		PriorityQueue<Shark> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 빈칸이 아닌 경우 다음으로 넘어감
				if (map[i][j] > 0) {
					continue;
				}
				
				int like = 0;
				int empty = 0;
				
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if (checkRange(nx, ny)) {
						// 좋아하는 학생이 있는지 확인
						for (int n : students[num]) {
							if (n == map[nx][ny]) {
								like++;
							}
						}
						
						// 비어있는 칸이 있는지 확인
						if (map[nx][ny] == 0) {
							empty++;
						}
					}
				}
				
				pq.add(new Shark(i, j, like, empty));
			}
		}
		
		Shark shark = pq.poll();
		map[shark.x][shark.y] = num;
	}
	
	// 만족도 구하기
	private static int getScore() {
		int result = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int like = 0;
				
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if (checkRange(nx, ny)) {
						// 특정 학생의 좋아하는 학생 리스트에 다음 칸 학생의 번호가 있는 경우 cnt 증가
						for (int num : students[map[i][j]]) {
							if (num == map[nx][ny]) {
								like++;
							}
						}
					}
				}
				
				switch (like) {
				case 1 : 
					result += 1;
					break;
				case 2 : 
					result += 10;
					break;
				case 3 : 
					result += 100;
					break;
				case 4 : 
					result += 1000;
					break;
				}
			}
		}
		
		return result;
	}
	
	private static boolean checkRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
