/**
 * 14500 - 테트로미노
 * https://www.acmicpc.net/problem/14500
 * 
 * @author Minchae Gwon
 * @date 2021.2.13
 * 
 * 입력
 * 첫째 줄에 종이의 세로 크기 N과 가로 크기 M이 주어진다. (4 ≤ N, M ≤ 500)
 * 둘째 줄부터 N개의 줄에 종이에 쓰여 있는 수가 주어진다. i번째 줄의 j번째 수는 위에서부터 i번째 칸, 왼쪽에서부터 j번째 칸에 쓰여 있는 수이다. 입력으로 주어지는 수는 1,000을 넘지 않는 자연수이다.
 * 
 * 출력
 * 첫째 줄에 테트로미노가 놓인 칸에 쓰인 수들의 합의 최댓값을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static int n, m;
	static int[][] map;
	static boolean[][] visited;
	
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visited = new boolean[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				getScore(i, j, 0, 0);
			}
		}
		
		System.out.println(max);

	}
	
	//백트랙킹 사용
	public static void getScore(int x, int y, int depth, int sum) {
		//깊이가 4이면 정사각형 네개를 탐색했다는 의미이므로 점수를 구해주고 종료
		if(depth == 4) {
			max = Math.max(sum, max);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			
			//정사각형 범위를 벗어나지 않고 방문하지 않은 칸일 경우
			if (tx >= 0 && tx < n && tx >= 0 && ty < m) {
				if (!visited[tx][ty]) {
					visited[tx][ty] = true;
					getScore(tx, ty, depth + 1, sum + map[tx][ty]);
					visited[tx][ty] = false;	
				}
			}
			
		}
	}

}
