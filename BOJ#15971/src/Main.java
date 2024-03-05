/**
 * 15971 두 로봇
 * https://www.acmicpc.net/problem/15971
 * 
 * @author minchae
 * @date 2024. 3. 5.
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
		int maxWeight;
		
		public Node(int next, int dist) {
			this.next = next;
			this.dist = dist;
		}
		
		public Node(int next, int dist, int maxWeight) {
			this.next = next;
			this.dist = dist;
			this.maxWeight = maxWeight;
		}
	}
	
	static int N;
	static ArrayList<Node>[] list;
	
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[a].add(new Node(b, w));
			list[b].add(new Node(a, w));
		}
		
//		bfs(start, end);
		
		visited = new boolean[N + 1];
		dfs(start, end, 0, 0);
	}
	
	private static void bfs(int start, int end) {
		Queue<Node> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		
		q.add(new Node(start, 0, 0));
		visited[start] = true;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (cur.next == end) {
				System.out.println(cur.dist - cur.maxWeight);
				return;
			}
			
			for (Node node : list[cur.next]) {
				if (!visited[node.next]) {
					q.add(new Node(node.next, cur.dist + node.dist, Math.max(cur.maxWeight, node.dist)));
					visited[node.next] = true;
				}
			}
		}
	}
	
	private static void dfs(int start, int end, int dist, int maxWeight) {
		visited[start] = true;
		
		if (start == end) {
			System.out.println(dist - maxWeight);
			return;
		}
		
		for (Node node : list[start]) {
			if (!visited[node.next]) {
				dfs(node.next, end, dist + node.dist, Math.max(maxWeight, node.dist));
			}
		}
	}

}
