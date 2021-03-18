/**
 * 1504 특정한 최단 경로
 * https://www.acmicpc.net/problem/1504
 * 
 * @author minchae
 * @date 2021.3.18
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int end;
	int weight;
	
	public Node(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
}

public class Main {
	
	static int N, E; // 정점 개수, 간선 개수 
	static int INF = 100000000;
	
	static LinkedList<Node>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		list = new LinkedList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			// 무방향 그래프이므로 둘 다 추가 
			list[a].add(new Node(b, c));
			list[b].add(new Node(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		int v1 = Integer.parseInt(st.nextToken());
		int v2 = Integer.parseInt(st.nextToken());
		
		// start -> v1 -> v2 -> end
		int route1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
		
		// start -> v2 -> v1 -> end
		int route2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);
		
		// 둘 중에서 최단 경로를 선택 -> 경로가 없는 경우에는 -1을 넣어줌
		int result = (route1 < INF && route2 < INF) ? Math.min(route1, route2) : -1;
		
		System.out.println(result);

	}
	
	public static int dijkstra(int start, int end) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		boolean[] visited = new boolean[N + 1];
		
		Arrays.fill(dist, INF);
		
		dist[start] = 0;
		q.offer(new Node(start, 0));
		
		while (!q.isEmpty()) {
			int cur = q.poll().end;
			
			if (!visited[cur]) {
				visited[cur] = true;
				
				for (Node node : list[cur]) {
					if (dist[node.end] > dist[cur] + node.weight) {
						dist[node.end] = dist[cur] + node.weight;
						q.add(new Node(node.end, dist[node.end]));
					}
				}
			}
			
		}
		
		return dist[end];
	}

}
