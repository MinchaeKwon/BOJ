/**
 * 백준 21609 상어 중학교
 * https://www.acmicpc.net/problem/21609
 * 
 * @author minchae
 * @date 2023. 10. 11.
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
	
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
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

public class BOJ21609 {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M;
	static int[][] map; // -1 검은 블록, 0 무지개 블록, 나머지 일반 블록
	
	static PriorityQueue<Block> pq;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		
		// 블록 그룹이 존재하는 동안 계속해서 반복
		while (true) {
			pq = new PriorityQueue<>();
			visited = new boolean[N][N];
			
			// 1. 가장 큰 블록 그룹 찾기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 아직 방문하지 않았고, 일반 블록인 경우
					if (!visited[i][j] && map[i][j] > 0) {
						findBlock(i, j);
					}
				}
			}
			
			// 블록 그룹이 없는 경우 종료
			if (pq.isEmpty()) {
				break;
			}
			
			// 2. 블록 그룹 제거
			Block remove = pq.poll();
			removeBlock(remove.x, remove.y, map[remove.x][remove.y]);
			
			result += Math.pow(remove.size, 2);
			
			// 3. 중력 적용
			gravity();
			
			// 4. 반시계 방향으로 90도 회전
			rotate();
			
			// 5. 중력 적용
			gravity();
			
		}

		System.out.println(result);
	}
	
	// 블록 그룹 찾기
	private static void findBlock(int x, int y) {
		Queue<Block> q = new LinkedList<>();
		
		q.add(new Block(x, y));
		visited[x][y] = true;
		
		int size = 1;
		int rainbow = 0;
		
		while (!q.isEmpty()) {
			Block cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				// 범위 안, 방문하지 않음
				if (checkRange(nx, ny) && !visited[nx][ny]) {
					// 블록 그룹의 색이 일치하거나 무지개 블록인 경우
					if (map[nx][ny] == map[x][y] || map[nx][ny] == 0) {
						if (map[nx][ny] == 0) {
							rainbow++;
						}
						
						size++;
						
						q.add(new Block(nx, ny));
						visited[nx][ny] = true;	
					}
				}
			}
		}
		
		// 크기가 2이상인 경우에만 블록 그룹에 추가
		if (size >= 2) {
			pq.add(new Block(x, y, size, rainbow));
		}
		
		// 무지개 블록은 방문처리 초기화 해줌 (블록 그룹에 언제든지 포함 가능하기 때문)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0 && visited[i][j]) {
					visited[i][j] = false;
				}
			}
		}
		
	}
	
	// 블록 그룹의 모든 블록 제거
	private static void removeBlock(int x, int y, int color) {
		Queue<Block> q = new LinkedList<>();
		
		q.add(new Block(x, y));
		map[x][y] = -2; // 삭제된 블록은 -2로 표시
		
		while (!q.isEmpty()) {
			Block cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				// 범위를 벗어나지 않는 경우
				if (checkRange(nx, ny)) {
					// 현재 지울 블록 색과 같거나 무지개 블록인 경우
					if (map[nx][ny] == color || map[nx][ny] == 0) {
						q.add(new Block(nx, ny));
						map[nx][ny] = -2;
					}
				}
			}
		}
	}
	
	// 맵에 중력 작용
	private static void gravity() {
		// 열을 확인하면서 맨 밑의 행부터 값을 바꿔줌
		for (int j = 0; j < N; j++) {
			for (int i = N - 1; i >= 0; i--) {
				// 해당 칸이 빈칸이 아니면 블록을 내릴수가 없기 때문에 다음으로 넘어감
				if (map[i][j] != -2) {
					continue;
				}
				
				int x = i;
				
				while (true) {
					x -= 1;
					
					// 격자를 벗어나거나 검은색 블록을 마주친 경우 종료
					if (x < 0 || map[x][j] == -1) {
						break;
					}
					
					if (map[x][j] != -2) {
						map[i][j] = map[x][j];
						map[x][j] = -2;
						
						break;
					}
				}
			}
		}
	}
	
	// 반시계 방향으로 90도 회전
	private static void rotate() {
		int[][] rotateMap = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				rotateMap[N - 1 - j][i] = map[i][j];
			}
		}
		
		map = rotateMap;
	}
	
	private static boolean checkRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
