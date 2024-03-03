/**
 * 1240 노드사이의 거리
 * https://www.acmicpc.net/problem/1240
 * 
 * @author minchae
 * @date 2024. 3. 3.
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
		int next;
		int dist;
		
		public Node(int next, int dist) {
			this.next = next;
			this.dist = dist;
		}
	}
	
	static int N, M;
	static ArrayList<Node>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			list[a].add(new Node(b, c));
			list[b].add(new Node(a, c));
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			int result = bfs(start, end);
			sb.append(result + "\n");
		}

		System.out.println(sb.toString());
	}
	
	private static int bfs(int start, int end) {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		
		q.add(new Node(start, 0));
		visited[start] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (cur.next == end) {
				return cur.dist;
			}
			
			for (Node n : list[cur.next]) {
				if (!visited[n.next]) {
					q.add(new Node(n.next, cur.dist + n.dist));
					visited[n.next] = true;
				}
			}
		}
		
		return 0;
	}

}
