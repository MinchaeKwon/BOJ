/**
 * 14938 서강그라운드
 * https://www.acmicpc.net/problem/14938
 * 
 * @author minchae
 * @date 2022. 4. 8.
 * 
 * 다익스트라 이용
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node implements Comparable<Node>{
	int num;
	int weight;
	
	public Node(int num, int weight) {
		this.num = num;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) {
		return this.weight - o.weight;
	}
}

public class Main {
	
	static int n, m, r;
	
	static int[] item; // 각 구역에 있는 아이템
	static ArrayList<Node>[] list;
	
	static int[] dist;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken()); // 지역의 개수
		m = Integer.parseInt(st.nextToken()); // 예은이의 수색 범위
		r = Integer.parseInt(st.nextToken()); // 길의 개수
		
		item = new int[n + 1];
		list = new ArrayList[n + 1];
		
		dist = new int[n + 1];
		visited = new boolean[n + 1];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= n; i++) {
			item[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			// 양방향 통행이 가능하기 때문에 각각 추가해줌
			list[a].add(new Node(b, l));
			list[b].add(new Node(a, l));
		}
		
		int result = 0;
		
		for (int i = 1; i <= n; i++) {
			result = Math.max(result, dijkstra(i));
		}
		
		System.out.println(result);
	}
	
	public static int dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		// 초기화
		Arrays.fill(visited, false);
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start] = 0;
		pq.offer(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			int cur = pq.poll().num;
			
			if (!visited[cur]) {
				visited[cur] = true;
				
				for (Node node : list[cur]) {
					if (dist[node.num] > dist[cur] + node.weight) {
						dist[node.num] = dist[cur] + node.weight;
						pq.add(new Node(node.num, dist[node.num]));
					}
				}
			}
		}
		
		int sum = 0;
		
		for (int i = 1; i <= n; i++) {
			if (dist[i] <= m) {
				sum += item[i];
			}
		}
		
		return sum;
	}

}
