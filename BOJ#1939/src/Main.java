/**
 * 1939 중량제한
 * https://www.acmicpc.net/problem/1939
 * 
 * @author minchae
 * @date 2024. 4. 6.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int n;
		int w;
		
		public Node(int n, int w) {
			this.n = n;
			this.w = w;
		}
	}
	
	static int N, M;
	static ArrayList<Node>[] list;
	
	static int start, end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		int low = Integer.MAX_VALUE;
		int high = 0;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			list[A].add(new Node(B, C));
			list[B].add(new Node(A, C));
			
			low = Math.min(low, C);
			high = Math.max(high, C);
		}
		
		st = new StringTokenizer(br.readLine());
		
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		int answer = 0;

		while (low <= high) {
			int mid = (low + high) / 2;
			
			if (bfs(mid)) {
				// mid 중량을 옮길 수 있는 경우 low를 증가시켜 최댓값 확인
				low = mid + 1;
				answer = mid;
			} else {
				// 옮길 수 없는 경우 mid를 감소시켜 옮길 수 있는 값의 범위를 줄임
				high = mid - 1;
			}
		}
		
		System.out.println(answer);
	}
	
	private static boolean bfs(int mid) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		
		q.add(start);
		visited[start] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			// 도착지인 경우 종료
			if (cur == end) {
				return true;
			}
			
			for (Node node : list[cur]) {
				// 다음 섬으로 이동할 때의 중량이 mid보다 크거나 같은 경우 건너갈 수 있는 것
				if (!visited[node.n] && node.w >= mid) {
					q.add(node.n);
					visited[node.n] = true;
				}
			}
		}
		
		return false;
	}

}
