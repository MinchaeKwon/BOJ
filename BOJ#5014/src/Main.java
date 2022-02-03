/**
 * 5014 스타트링크
 * https://www.acmicpc.net/problem/5014
 * 
 * @author minchae
 * @date 2022. 2. 4.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] move = new int[2];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int F = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		
		move[0] = Integer.parseInt(st.nextToken());
		move[1] = -Integer.parseInt(st.nextToken());
		
		int result = bfs(F, S, G);
		System.out.println(result == -1 ? "use the stairs" : result);
	}
	
	public static int bfs(int first, int start, int goal) {
		Queue<Integer> q = new LinkedList<>();
		int[] visited = new int[first + 1];
		
		q.add(start);
		visited[start] = 1;
		
		while (!q.isEmpty()) {
			int current = q.poll();
			
			// 목표층에 도달한 경우
			if (current == goal) {
				return visited[goal] - 1; // 출발점 S를 제일 처음 방문체크 하기 때문에 -1 해주는 것
			}
			
			// 엘리베이터 층 이동하기
			for (int i = 0; i < 2; i++) {
				int next = current + move[i];
				
				// 범위를 벗어나지 않고, 방문 하지 않은 경우
				if (next > 0 && next <= first && visited[next] == 0) {
					q.add(next);
					visited[next] = visited[current] + 1; // 해당 층에 가기 위해 누르는 버튼의 수를 증가시킴
				}	
			}
		}
		
		return -1;
	}

}
