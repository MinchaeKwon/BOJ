/**
 * 19237 어른 상어
 * https://www.acmicpc.net/problem/19237
 * 
 * @author minchae
 * @date 2024. 2. 19.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_19237_2 {
	
	static class Shark {
		int x;
		int y;
		int d; // 상어 방향
		int n; // 상어 번호
		int[][] priority = new int[4][4]; // 상어 방향 우선순위
		
		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M, k;
	
	static int[][] map; // 각 상어의 위치
	static int[][] owner; // 냄새의 주인
	static int[][] smell; // 남아있는 냄새
	
	static HashMap<Integer, Shark> hm = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		owner = new int[N][N];
		smell = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] > 0) {
					hm.put(map[i][j], new Shark(i, j));
					
					// 맨 처음 상어는 자신의 위치에 냄새를 뿌림
					owner[i][j] = map[i][j];
					smell[i][j] = k;
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= M; i++) {
			hm.get(i).d = Integer.parseInt(st.nextToken()) - 1;
			hm.get(i).n = i;
		}
		
		for (int s = 1; s <= M; s++) {
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < 4; j++) {
					hm.get(s).priority[i][j] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
		
		int time = 0;
		
		while (time < 1000) {
			time++;
			
			move();
			removeSmell(); // move()에서 owner를 통해 이동 방향을 결정하기 때문에 이동 후에 냄새를 삭제함
			createSmell();
			
			if (hm.size() == 1) {
				System.out.println(time);
				return;
			}
		}
			
		System.out.println(-1);
	}
	
	// 모든 상어가 한 칸씩 이동
	private static void move() {
		ArrayList<Integer> removeShark = new ArrayList<>(); // 격자 밖으 쫓겨나는 상어의 번호 저장
		
		for (Shark s : hm.values()) {
			ArrayList<Integer> empty = new ArrayList<>(); // 인접한 칸 중 아무 냄새가 없는 칸의 방향
			ArrayList<Integer> my = new ArrayList<>(); // 인접한 칸 중 자신의 냄새가 있는 칸의 방향
			
			for (int i = 0; i < 4; i++) {
				int nx = s.x + dx[i];
				int ny = s.y + dy[i];
				
				if (!isRange(nx, ny)) {
					continue;
				}
				
				if (owner[nx][ny] == 0) {
					empty.add(i);
				} else if (owner[nx][ny] == s.n) {
					my.add(i);
				}
			}
			
			int nd = findDir(s, empty);
			
			// 아무 냄새가 없는 칸이 없는 경우 자신의 냄새가 있는 방향으로 감
			if (nd == -1) {
				nd = findDir(s, my);
			}
			
			map[s.x][s.y] = 0; // 원래 상어가 있던 자리는 빈 칸으로 만듦
			
			// 위치 갱신
			s.x += dx[nd];
			s.y += dy[nd];
			
			// 이동하려는 칸이 빈 칸이거나 숫자가 작아서 쫓겨나지 않는 경우
			if (map[s.x][s.y] == 0 || s.n < map[s.x][s.y]) {
				map[s.x][s.y] = s.n;
				s.d = nd;
			} else {
				removeShark.add(s.n);
			}
		}
		
		for (int n : removeShark) {
			hm.remove(n); // key를 통해 상어 삭제
		}
	}
	
	private static int findDir(Shark s, ArrayList<Integer> dirList) {
		for (int i = 0; i < 4; i++) {
			if (dirList.contains(s.priority[s.d][i])) {
				return s.priority[s.d][i];
			}
		}
		
		return -1;
	}
	
	// k번 이후의 냄새 삭제
	private static void removeSmell() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (smell[i][j] > 0) {
					smell[i][j]--;
				}
				
				// 냄새가 없는 경우 주인도 삭제
				if (smell[i][j] == 0) {
					owner[i][j] = 0;
				}
			}
		}
	}
	
	// 상어가 이동한 칸에 냄새를 남김
	private static void createSmell() {
		for (Shark s : hm.values()) {
			owner[s.x][s.y] = s.n;
			smell[s.x][s.y] = k;
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
