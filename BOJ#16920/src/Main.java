/**
 * 16920 확장 게임
 * https://www.acmicpc.net/problem/16920
 * 
 * @author minchae
 * @date 2024. 1. 28.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pair {
	int x;
	int y;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	// 상좌우하
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};
	
	static int N, M, P;
	
	static char[][] map;
	static int[] s; // 플레이어가 이동할 수 있는 거리
	
	static Queue<Pair>[] players; // 각 플레이어의 성의 위치
	static int[] castle; // 플레이어가 가진 성의 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		s = new int[P + 1];
		
		players = new LinkedList[P + 1];
		castle = new int[P + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= P; i++) {
			s[i] = Integer.parseInt(st.nextToken());
			players[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			
			for (int j = 0; j < M; j++) {
				// 플레이어의 성이 있는 곳인 경우
				if (map[i][j] >= '1' && map[i][j] <= '9') {
					int num = map[i][j] - '0';
					
					players[num].add(new Pair(i, j));
					castle[num]++; // 성 개수 증가
				}
			}
		}
		
		// 더이상 확장할 수 없을 때까지 진행
		while (true) {
			for (int i = 1; i <= P; i++) {
				bfs(players[i], s[i], i);
			}
			
			boolean flag = true;
			
			// 1. 플레이어의 수만큼 반복하면서 bfs 진행 (성 확장)
			for (int i = 1; i <= P; i++) {
				// 한 명이라도 확장 가능한 경우에는 계속 진행해야 하기 때문에 false로 바꿔줌
				if (!players[i].isEmpty()) {
					flag = false;
					break;
				}
			}
			
			// 모든 플레이어가 성을 확장할 수 없는 경우 종료
			if (flag) {
				break;
			}
		}
		
		// 플레이어가 가진 성의 수 출력
		for (int i = 1; i <= P; i++) {
			System.out.print(castle[i] + " ");
		}
		System.out.println();
		
	}
	
	private static void bfs(Queue<Pair> q, int move, int num) {
		// 2. 특정 플레이어가 이동 가능한 거리만큼 반복
		for (int m = 0; m < move; m++) {
			int size = q.size();
			
			// 3. 각 성마다 크기를 확장시켜야 하기 때문에 size를 따로 구해서 반복문을 사용함
			for (int i = 0; i < size; i++) {
				Pair cur = q.poll();
				
				// 4. 각 방향으로 성 확장시킴
				for (int d = 0; d < 4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					
					// 범위를 벗어나지 않고, 빈칸일 경우
					if (isRange(nx, ny) && map[nx][ny] == '.') {
						q.add(new Pair(nx, ny));
						
						map[nx][ny] = (char) (num + '0');
						castle[num]++; // 확장한 성의 개수 증가 시킴
					}
				}
			}
			
			// 5. 특정 플레이어의 성을 더이상 확장시킬 수 없는 경우 종료
			if (q.isEmpty()) {
				return;
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
