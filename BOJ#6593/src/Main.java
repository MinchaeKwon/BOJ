/**
 * 6593 상범 빌딩
 * https://www.acmicpc.net/problem/6593
 * 
 * @author minchae
 * @date 2024. 1. 27.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Pos {
	int z;
	int x;
	int y;
	int cnt;
	
	public Pos(int z, int x, int y, int cnt) {
		this.z = z;
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}

public class Main {
	
	// 동서남북상하
	static int[] dz = {0, 0, 0, 0, -1, 1};
	static int[] dx = {0, 0, 1, -1, 0, 0};
	static int[] dy = {-1, 1, 0, 0, 0, 0};
	
	static int L, R, C;
	
	static char[][][] map;
	static boolean[][][] visited;
	
	static int sz, sx, sy;
	
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			L = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			// 입력 종료
			if (L == 0 && R == 0 && C == 0) {
				break;
			}
			
			map = new char[L][R][C];
			
			for (int z = 0; z < L; z++) {
				for (int i = 0; i < R; i++) {
					map[z][i] = br.readLine().toCharArray();
					
					for (int j = 0; j < C; j++) {
						if (map[z][i][j] == 'S') {
							sz = z;
							sx = i;
							sy = j;
						}
					}
				}
				
				br.readLine();
			}
			
			answer = bfs();
			
			System.out.println(answer == -1 ? "Trapped!" : "Escaped in " + answer + " minute(s).");
			
		}
		
	}
	
	private static int bfs() {
		Queue<Pos> q = new LinkedList<>();
		boolean[][][] visited = new boolean[L][R][C];
		
		q.add(new Pos(sz, sx, sy, 0));
		visited[sz][sx][sy] = true;
		
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			
			if (map[cur.z][cur.x][cur.y] == 'E') {
				return cur.cnt;
			}
			
			for (int i = 0; i < 6; i++) {
				int nz = cur.z + dz[i];
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (isRange(nz, nx, ny) && !visited[nz][nx][ny] && map[nz][nx][ny] != '#') {
					q.add(new Pos(nz, nx, ny, cur.cnt + 1));
					visited[nz][nx][ny] = true;
				}
			}
		}
		
		return -1;
	}
	
	private static boolean isRange(int z, int x, int y) {
		return z >= 0 && z < L && x >= 0 && x < R && y >=0 && y < C;
	}

}
