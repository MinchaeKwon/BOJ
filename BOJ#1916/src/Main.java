/**
 * 1916 최소비용 구하기
 * https://www.acmicpc.net/problem/1916
 * 
 * @author Minchae Gwon
 * @date 2021.3.6
 * 
 * 출력
 * 첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int INF = Integer.MAX_VALUE;
	
	static int[] dist;
	static LinkedList<Node>[] bus;
	static boolean[] visited;
	
	static class Node implements Comparable<Node>{
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		bus = new LinkedList[N + 1];
		dist = new int[N + 1];
		visited = new boolean[N + 1];
		
		for (int i = 1; i <= N; i++) {
			dist[i] = INF;
			bus[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			bus[s].add(new Node(e, c));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
		dijkstra(start);
		System.out.println(dist[end]);

	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		
		dist[start] = 0;
		q.offer(new Node(start, 0));
		
		while (!q.isEmpty()) {
			int cur = q.poll().end;
			
			if (!visited[cur]) {
				visited[cur] = true;
				
				for (Node node : bus[cur]) {
					if (dist[node.end] > dist[cur] + node.weight) {
						dist[node.end] = dist[cur] + node.weight;
						q.add(new Node(node.end, dist[node.end]));
					}
				}
			}
			
		}
	}

}
