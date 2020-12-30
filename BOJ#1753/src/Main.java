//1753 - 최단경로
//알고리즘 과제 - 다익스트라 알고리즘(그리디 알고리즘으로 최적해를 구할 수 있는 최단 경로 알고리즘)

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

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

	private static int INF = Integer.MAX_VALUE;
	private static int[] dist;
	private static ArrayList<Node>[] list;
	private static boolean[] visited;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//System.out.print("정점 개수: ");
		int v = sc.nextInt(); //정점에는 1부터 v까지 번호가 매겨져 있다고 가정
		
		//System.out.print("간선 개수: ");
		int e = sc.nextInt();
		
		//System.out.print("시작 정점 번호: ");
		int start = sc.nextInt();
		
		list = new ArrayList[v + 1];
		dist = new int[v + 1];
		visited = new boolean[v + 1];
		
		for (int i = 1; i <= v; i++) {
			dist[i] = INF;
			list[i] = new ArrayList<>();
		}
		
		//System.out.println("출발 노드, 도착 노드, 간선 가중치 입력");
		for (int i = 0; i < e; i++) {
			int u = sc.nextInt();
			int d = sc.nextInt();
			int w = sc.nextInt();
			list[u].add(new Node(d, w));
		}
		
		dijkstra(start);
		
		//시작 정점에서 i번 정점으로의 최단 경로 출력, 경로가 존재하지 않는 경우에는 INF 출력
		print_distance(v);
		
		sc.close();

	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		
		dist[start] = 0;
		q.offer(new Node(start, 0));
		
		while (!q.isEmpty()) {
			int current = q.poll().end;
			
			if (visited[current])
				continue;
			visited[current] = true;
			
			for (Node node : list[current]) {
				if (dist[node.end] > dist[current] + node.weight) {
					dist[node.end] = dist[current] + node.weight;
					q.add(new Node(node.end, dist[node.end]));
				}
			}
		}
	}
	
	public static void print_distance(int v) {
		for (int i = 1; i <= v; i++) {
			if (dist[i] == INF)
				System.out.println("INF");
			else
				System.out.println(dist[i]);
		}
	}
	

}
