/**
 * 백준 19237 어른 상어
 * https://www.acmicpc.net/problem/19237
 * 
 * @author minchae
 * @date 2023. 10. 11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ19237 {
	
	static class Shark {
		int x;
		int y;
		int n;
		int d;
		int[][] priority = new int[4][4];
		
		public Shark(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M, k;
	
	static int[][] map; // 상어 위치
	static int[][] owner; // 냄새의 주인
	static int[][] smell; // 상어 냄새
	
	static HashMap<Integer, Shark> sharkMap = new HashMap<>();

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
				
				// 상어가 있을 경우
				if (map[i][j] > 0) {
					// 맨 처음에는 모든 상어가 자신의 위치에 자신의 냄새를 뿌림
					owner[i][j] = map[i][j];
					smell[i][j] = k;
					
					sharkMap.put(map[i][j], new Shark(i, j));
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= M; i++) {
			sharkMap.get(i).n = i;
			sharkMap.get(i).d = Integer.parseInt(st.nextToken()) - 1;
		}
		
		for (int p = 1; p <= M; p++) {
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < 4; j++) {
					sharkMap.get(p).priority[i][j] = Integer.parseInt(st.nextToken()) - 1;
				}
			}
		}
		
		int cnt = 0;
		
		while (cnt < 1000) {
			cnt++;
			
			move();
			removeSmell();
			createSmell();
			
			if (sharkMap.size() == 1) {
				System.out.println(cnt);
				System.exit(0);
			}
		}
		
		System.out.println(-1);
	}
	
	// 상어 이동
	private static void move() {
		ArrayList<Integer> remove = new ArrayList<>();
		
		for (Shark shark : sharkMap.values()) {
			ArrayList<Integer> empty = new ArrayList<>(); // 인접한 칸 중에 빈칸
			ArrayList<Integer> my = new ArrayList<>(); // 인접한 칸 중에 자신의 냄새가 있는 칸
			
			for (int i = 0; i < 4; i++) {
				int nx = shark.x + dx[i];
				int ny = shark.y + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					if (owner[nx][ny] == 0) { // 빈칸인 경우
						empty.add(i);
					} else if (owner[nx][ny] == shark.n) { // 자신의 냄새가 있는 칸인 경우
						my.add(i);
					}
				}
			}
			
			int nd = findDir(shark, empty); // 빈칸 중에서 갈 수 있는 곳을 찾음
			
			if (nd == -1) {
				nd = findDir(shark, my); // 자신의 냄새가 있는 칸 중에서 갈 수 있는 곳을 찾음
			}
			
			map[shark.x][shark.y] = 0; // 원래 상어가 있던 자리는 빈칸으로 만들어줌

			switch (nd) {
			case 0 : // 상
				shark.x--;
				break;
			case 1 : // 하
				shark.x++;
				break;
			case 2 : // 좌
				shark.y--;
				break;
			case 3 : // 우
				shark.y++;
				break;
			}
			
			if (map[shark.x][shark.y] == 0 || shark.n < map[shark.x][shark.y]) {
				shark.d = nd;
				map[shark.x][shark.y] = shark.n;
			} else {
				remove.add(shark.n);
			}
		}
		
		// 상어 사라짐
		for (int n : remove) {
			sharkMap.remove(n);
		}
	}
	
	// 상어가 이동할 다음 방향 찾기
	private static int findDir(Shark shark, ArrayList<Integer> list) {
		for (int i = 0; i < 4; i++) {
			if (list.contains(shark.priority[shark.d][i])) {
				return shark.priority[shark.d][i];
			}
		}
		
		return -1;
	}
	
	// 상어 냄새 감소시킴
	private static void removeSmell() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (smell[i][j] > 0) {
					smell[i][j]--;
				}
				
				// 냄새가 사라지면 냄새의 주인을 저장해놓은 맵도 0으로 변경
				if (smell[i][j] == 0) {
					owner[i][j] = 0;
				}
			}
		}
	}
	
	// 상어 냄새 뿌림
	private static void createSmell() {
		for (Shark shark : sharkMap.values()) {
			owner[shark.x][shark.y] = shark.n;
			smell[shark.x][shark.y] = k;
		}
	}

}
