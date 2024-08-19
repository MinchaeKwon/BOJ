/**
 * 1753 최단경로
 * https://www.acmicpc.net/problem/1753
 * 
 * @author minchae
 * @date 2024. 8. 20.
 * 
 * 다익스트라 이용
 */

import java.io.*;
import java.util.*;

public class Dijkstra {
	
	static class Node implements Comparable<Node> {
		int e;
		int w;
		
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}
		
		@Override
		public int compareTo(Dijkstra.Node o) {
			return this.w - o.w;
		}
	}
	
	static final int INF = Integer.MAX_VALUE;
	
	static int V, E;
	
	static int[] dist;
	static ArrayList<Node>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		int K = Integer.parseInt(br.readLine());
		
		list = new ArrayList[V + 1];
		dist = new int[V + 1];
		
		for (int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
			dist[i] = INF;
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[u].add(new Node(v, w)); // 방향 그래프이기 때문에 하나만 추가
		}

		dijkstra(K);
		
		for (int i = 1; i <= V; i++) {
			System.out.println(dist[i] == INF ? "INF" : dist[i]);
		}
	}
	
	private static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];
		
		// 시작점과 연결된 노드 탐색
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// 이미 방문한 곳이면 넘어감
			if (visited[cur.e]) {
				continue;
			}
			
			visited[cur.e] = true;
			
			for (Node next : list[cur.e]) {
				// 최솟값 갱신
				if (dist[next.e] > dist[cur.e] + cur.w) {
					dist[next.e] = dist[cur.e] + cur.w;
					pq.add(new Node(next.e, dist[next.e]));
				}
			}
		}
	}

}
