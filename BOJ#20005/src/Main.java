/**
 * 20005 보스몬스터 전리품
 * https://www.acmicpc.net/problem/20005
 * 
 * @author minchae
 * @date 2024. 2. 3.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Pair {
		int id;
		int x;
		int y;
		
		public Pair(int id, int x, int y) {
			this.id = id;
			this.x = x;
			this.y = y;
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int M, N, P;
	
	static char[][] map; // ‘.’은 이동할 수 있는 길, ‘X’는 이동할 수 없는길, ‘B’는 보스몬스터의 위치
	static HashMap<Integer, Integer> players = new HashMap<>();
	static int HP; // 보스몬의 체력
	
	static Queue<Pair> q = new LinkedList<>();
	static boolean[][][] visited; // 각 플레이어가 특정 칸에 방문했는지 확인하기 위해 3차원 배열 사용

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		map = new char[M][N];
		visited = new boolean[P][M][N];
		
		for (int i = 0; i < M; i++) {
			map[i] = br.readLine().toCharArray();
			
			for (int j = 0; j < N; j++) {
				if (map[i][j] >= 'a' && map[i][j] <= 'z') {
					q.add(new Pair(map[i][j] - 'a', i, j));
					visited[map[i][j] - 'a'][i][j] = true;
				}
			}
		}
		
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());

			players.put(st.nextToken().charAt(0) - 'a', Integer.parseInt(st.nextToken()));
		}
		
		HP = Integer.parseInt(br.readLine());
		
		// 전리품을 가져갈 수 있는 플레이어의 수의 최댓값을 출력
		System.out.println(bfs());
	}
	
	private static int bfs() {
		ArrayList<Integer> list = new ArrayList<>(); // 공격 가능한 플레이어의 id를 저장
		
		// 보스몬을 물리칠 때까지 진행
		while (HP > 0) {
			// 각 플레이어마다 위치 이동 시킴
			int size = q.size();
			
			for (int i = 0; i < size; i++) {
				Pair cur = q.poll();
				
				// 이미 보스몬 위치에 도달해서 공격 가능한 경우 넘어감
				if (list.contains(cur.id)) {
					continue;
				}
				
				// 보스몬 만난 경우 플레이어 수 증가, 플레이어 id 저장
				if (map[cur.x][cur.y] == 'B') {
					list.add(cur.id);
				}
				
				for (int d = 0; d < 4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					
					// 범위 벗어나지 않음, 방문 X, 이동할 수 있는 길인 경우
					if (isRange(nx, ny) && !visited[cur.id][nx][ny] && map[nx][ny] != 'X') {
						q.add(new Pair(cur.id, nx, ny));
						visited[cur.id][nx][ny] = true;
					}
				}
			}
			
			// 보스몬 동시에 공격
			for (int id : list) {
				HP -= players.get(id);
			}
		}
		
		return list.size();
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < M && y >= 0 && y < N;
	}

}
