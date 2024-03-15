/**
 * 16940 BFS 스페셜 저지
 * https://www.acmicpc.net/problem/16940
 * 
 * @author minchae
 * @date 2024. 3. 15.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	
	static int[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		list = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		answer = new int[N];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			answer[i] = Integer.parseInt(st.nextToken());
		}
		
		// 시작 정점은 무조건 0이기 때문에 정답 순서가 1로 시작하지 않는 경우 0 출력
		if (answer[0] != 1) {
			System.out.println(0);
			return;
		}
		
		System.out.println(bfs() ? 1 : 0);
	}
	
	private static boolean bfs() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		
		q.add(1);
		visited[1] = true;
		
		int idx = 1;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			int cnt = 0;
			
			for (int next : list[cur]) {
				if (!visited[next]) {
					visited[next] = true; // 자식 노드 방문처리
					cnt++;
				}
			}
			
			// cur노드의 자식들을 정답 순서에서 확인
			for (int i = idx; i < idx + cnt; i++) {
				if (!visited[answer[i]]) {
					// 방문되어 있지 않은 경우 올바른 방문 순서가 아님
					return false;
				} else {
					// 올바른 방문 순서일 경우 큐에 추가 -> 자식 노드부터 탐색 시작하게 함
					q.add(answer[i]);
				}
			}
			
			idx += cnt;
		}
		
		return true;
	}

}
