/**
 * 1197 최소 스패닝 트리
 * https://www.acmicpc.net/problem/1197
 * 
 * @author minchae
 * @date 2021. 6. 28.
 * 
 * 크루스칼 알고리즘 이용
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int s;
	int e;
	int weight;
	
	public Edge(int s, int e, int weight) {
		this.s = s;
		this.e = e;
		this.weight = weight;
	}
	
	@Override
	public int compareTo(Edge n) { // 가중치를 기준으로 오름차순 정렬
		return this.weight - n.weight;
	}
}

public class Main {
	
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken()); // 정점의 개수
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수

		
//		ArrayList<Edge> nodeList = new ArrayList<>();
		
		// 우선순위 큐를 사용한다면 Collections.sort를 하지 않아도 가중치를 기준으로 오름차순 정렬이 됨
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
//			nodeList.add(new Edge(a, b, c));
			pq.add(new Edge(a, b, c));
		}
		
		// 가중치가 작은 순서대로 정렬
//		Collections.sort(nodeList);
		
		parent = new int[V + 1];
		
		// 부모를 자기 자신으로 초기화
		for (int i = 1; i < V + 1; i++) {
			parent[i] = i;
		}

		int result = 0;
		for (int i = 0; i < E; i++) {
//			Edge edge = nodeList.get(i);
			Edge edge = pq.poll();
			
			// 특정 간선의 시작점과 끝점의 최상위 노드를 찾음
			int rootS = find(edge.s);
			int rootE = find(edge.e);
			
			// 최상위 노드가 같지 않을 경우(간선의 양끝 정점이 같은 집합에 속해있지 않은 경우, 연결되어있지 않은 경우) union
			if (rootS != rootE) {
//				union(node.s, node.e);
				union(rootS, rootE);
				
				result += edge.weight; // 가중치를 더함
			}
		}
		
		System.out.println(result);
		
	}
	
	// 노드 x가 속하는 부모 노드(최상위 노드)를 찾음
	public static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		else {
			return parent[x] = find(parent[x]);
		}
	}
	
	// 두 개의 노드가 속한 집합을 합침(연결함)
	public static void union(int x, int y) {
//		int rootX = find(x);
//		int rootY = find(y);
//		
//		if (rootX != rootY) {
//			parent[rootX] = rootY;
//		}
		
		parent[x] = y;

	}

}
