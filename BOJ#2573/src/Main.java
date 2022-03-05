/**
 * 2573 빙산
 * https://www.acmicpc.net/problem/2573
 * 
 * @author minchae
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class IceBerg {
	int x;
	int y;
	
	public IceBerg(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int N, M;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

	}
	
	public static void bfs() {
		Queue<IceBerg> q = new LinkedList<>();
		
		while(!q.isEmpty()) {
			IceBerg ice = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = ice.x + dx[i];
				int ny = ice.y + dy[i];
				
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					
				}
			}
		}
	}

}
