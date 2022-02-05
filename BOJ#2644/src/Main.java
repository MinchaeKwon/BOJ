/**
 * 2644 촌수계산
 * https://www.acmicpc.net/problem/2644
 * 
 * @author minchae
 * @date 2022. 2. 5.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] map;
	static int[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[n + 1][n + 1];
		visited = new int[n + 1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int p1 = Integer.parseInt(st.nextToken());
		int p2 = Integer.parseInt(st.nextToken());
		
		int m = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken()); // 부모
			int y = Integer.parseInt(st.nextToken()); // 자식
			
			// 부모 자식간의 관계이므로 1을 넣어줌
			map[x][y] = 1;
			map[y][x] = 1;
		}
		
		System.out.println(bfs(p1, p2));
	}
	
	public static int bfs(int start, int end) {
		Queue<Integer> q = new LinkedList<>();
		
		q.add(start);
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			if (cur == end) {
				return visited[cur];
			}
			
			// 전체 사람 수만큼 돌면서 촌수 계산
			for (int i = 1; i <= n; i++) {
				// 관계되어 있고 아직 방문하지 않은 경우
				if (map[cur][i] == 1 && visited[i] == 0) {
					q.add(i);
					visited[i] = visited[cur] + 1;
				}
			}
		}
		
		return -1;
	}

}
