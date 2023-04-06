/**
 * 상어 중학교
 * https://www.acmicpc.net/problem/21609
 * 
 * @author minchae
 * @date 2023. 4. 7.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Block implements Comparable<Block> {
	int x;
	int y;
	int size;
	int rainbow;
	
	public Block(int x, int y, int size, int rainbow) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.rainbow = rainbow;
	}
	
	@Override
	public int compareTo(Block o) {
		if (this.size == o.size) {
			if (this.rainbow == o.rainbow) {
				if (this.x == o.x) {
					return o.y - this.y;
				}
				
				return o.x - this.x;
			}
			
			return o.rainbow - this.rainbow;
		}
		
		return o.size - this.size;
	}
}

public class Main {

	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M;
	static int[][] map;
	
	static boolean[][] visited;
	static PriorityQueue<Block> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); // 색상의 개수
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		
		// 오토 플레이는 다음과 같은 과정이 블록 그룹이 존재하는 동안 계속해서 반복
		while (true) {
			visited = new boolean[N][N];
			
			// 1. 가장 큰 블록 그룹 찾음
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 아직 방문하지 않았고 일반 블록인 경우
					if (!visited[i][j] && map[i][j] > 0) {
						findBlock(i, j);
					}
				}
			}
			
			// 더이상 블록 그룹이 없다면 종료
			if (pq.isEmpty()) {
				break;
			}
			
			// 2. 가장 큰 블록 그룹 삭제
			Block remove = pq.poll();
			removeBlock(remove.x, remove.y);
			
			answer += (int) Math.pow(remove.size, 2); // 점수 획득
			
			gravity(); // 3. 중력 적용
			map = rotate(); // 4. 반시계 회전
			gravity(); // 5. 중력 적용
			
			pq.clear();
		}
		
		System.out.println(answer);
	}
	
	// 크기가 가장 큰 블록 그룹 찾기
	private static void findBlock(int x, int y) {
		Queue<Block> q = new LinkedList<>();
		
		q.add(new Block(x, y, 0, 0));
		visited[x][y] = true;
		
		int size = 1;
		int rainbow = 0;
		
		while (!q.isEmpty()) {
			Block block = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = block.x + dx[i];
				int ny = block.y + dy[i];
				
				// 경계를 넘지 않고 아직 방문하지 않은 경우
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
					// 이전 칸의 블록 색과 똑같거나 무지개 블록인 경우
					if (map[nx][ny] == map[x][y] || map[nx][ny] == 0) {
						if (map[nx][ny] == 0) {
							rainbow++;
						}
						
						size++;
						
						q.add(new Block(nx, ny, 0, 0));
						visited[nx][ny] = true;
					}
				}
			}
		}
		
		// 블록 그룹 안의 블록 개수가 2개 이상인 경우
		if (size >= 2) {
			pq.add(new Block(x, y, size, rainbow));
		}
		
		// 무지개 블록은 어느 그룹에나 포함될 수 있기 때문에 다시 false 넣어줌
		for (int i = 0; i < N ; i++) {
			for (int j = 0 ; j < N ; j++) {
				if (map[i][j] == 0) {
					visited[i][j] = false;
				}
			}
		}
		
	}
	
	// 가장 큰 블록 그룹의 블록 삭제하기
	private static void removeBlock(int x, int y) {
		int cur = map[x][y];
		
		Queue<Block> q = new LinkedList<>();
		
		q.add(new Block(x, y, 0, 0));
		map[x][y] = -2;
		
		while (!q.isEmpty()) {
			Block block = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = block.x + dx[i];
				int ny = block.y + dy[i];
				
				// 경계를 넘지 않고 아직 방문하지 않은 경우
				if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
					// 이전 칸의 블록 색과 똑같거나 무지개 블록인 경우
					if (map[nx][ny] == cur || map[nx][ny] == 0) {
						q.add(new Block(nx, ny, 0, 0));
						map[nx][ny] = -2;
					}
				}
			}
		}
	}
	
	// 중력 적용시키기
	private static void gravity() {
		for (int j = 0 ; j < N ; j++) {
			for (int i = N - 1 ; i >= 0 ; i--) {
				for (int k = i ; k < N - 1 ; k ++) {
					// 검은색 블록을 제외한 모든 블록이 움직이기 때문에 -1이 아닌 경우만 봄
					if (map[k][j] != -1) {
						if (map[k][j] != -2 && map[k + 1][j] == -2) {
							int temp = map[k][j];
							map[k][j] = -2;
							map[k + 1][j] = temp;
						}	
					}
				}
			}
		}
			
//		for (int j = 0; j < N; j++) {
//			for (int i = N - 1;i >= 1; i--) {
//				if (map[i][j] != -2) continue;
//				int nx = i;
//					
//				while(true) {
//					nx -= 1;
//					if (nx < 0) break;
//					if (map[nx][j] == -1) break;
//						
//					if (map[nx][j] != -2) {
//						map[i][j] = map[nx][j];
//						map[nx][j] = -2;
//						break;
//					}
//				}
//			}
//		}
			
	}
	
	// 반시계 방향으로 이동
	private static int[][] rotate() {
		int[][] copyMap = new int[N][N];
		
		// 반시계 방향으로 회전
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[N - 1 - j][i] = map[i][j];
			}
		}
		
		return copyMap;
	}

}
