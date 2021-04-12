/**
 * 1238 파티
 * https://www.acmicpc.net/problem/1238
 * 
 * @author minchae
 * @date 2021. 4. 12
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int end; // 도착
	int time; // 소요시간
	
	public Node(int end, int time) {
		this.end = end;
		this.time = time;
	}

	@Override
	public int compareTo(Node o) {
		return this.time - o.time;
	}
}

public class Main {
	
	static int INF = Integer.MAX_VALUE;
	
	static int N, M, X;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		LinkedList<Node>[] road = new LinkedList[N + 1]; // X에서 모든 정점까지의 거리를 구하기 위함
		LinkedList<Node>[] reverseRoad = new LinkedList[N + 1]; // 모든 정점에서 X까지의 거리를 구하기 위함
		
		int[] dist = new int[N + 1];
		int[] reverseDist = new int[N + 1];
		
//		Arrays.fill(dist, INF);
//		Arrays.fill(reverseDist, INF);
		
		for (int i = 1; i <= N; i++) {
			road[i] = new LinkedList<>();
			reverseRoad[i] = new LinkedList<>();
			
			dist[i] = INF;
			reverseDist[i] = INF;
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			
			road[s].add(new Node(e, t));
			reverseRoad[e].add(new Node(s, t)); // 시작점과 끝점을 반대로 넣어야 모든 정점에서 X까지의 거리를 편하게 구할 수 있음
		}
		
		dijkstra(X, road, dist);
		dijkstra(X, reverseRoad, reverseDist);
		
		int result = 0;
		for (int i = 1; i <= N; i++) {
			result = Math.max(result, dist[i] + reverseDist[i]);
		}
		
		System.out.println(result);
		
	}
	
	public static void dijkstra(int start, LinkedList<Node>[] nodeList, int[] dist) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		
		dist[start] = 0;
		q.offer(new Node(start, 0));
		
		while (!q.isEmpty()) {
			int cur = q.poll().end;
			
			if (!visited[cur]) {
				visited[cur] = true;
				
				for (Node node : nodeList[cur]) {
					if (dist[node.end] > dist[cur] + node.time) {
						dist[node.end] = dist[cur] + node.time;
						q.add(new Node(node.end, dist[node.end]));
					}
				}
			}
			
		}
	}

}
