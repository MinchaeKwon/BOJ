/**
 * 1197 최소 스패닝 트리
 * https://www.acmicpc.net/problem/1197
 * 
 * @author minchae
 * @date 2024. 8. 19.
 * 
 * 크루스칼 알고리즘 이용
 */

import java.util.*;
import java.io.*;

public class Kruskal {
	
	static class Edge implements Comparable<Edge> {
		int s;
		int e;
		int w;
		
		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}

		// 가중치를 기준으로 오름차순
		@Override
		public int compareTo(Kruskal.Edge o) {
			return this.w - o.w;
		}
		
	}
	
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken()); // 정점 개수
		int E = Integer.parseInt(st.nextToken()); // 간선 개수
		
		PriorityQueue<Edge> pq = new PriorityQueue<>(); // 1. 가중치를 기준으로 오름차순 정렬
//		ArrayList<Edge> list = new ArrayList<>(); // 리스트에 저장하고 sort 해도 됨
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			pq.add(new Edge(A, B, C));
//			list.add(new Edge(A, B, C));
		}

//		Collections.sort(list);
		
		parent = new int[V + 1]; // 노드 번호가 1부터 시작하기 때문에 +1 해줌
		
		// 먼저 부모 배열을 자기 자신으로 초기화
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		
		int answer = 0;
		
		while (!pq.isEmpty()) {
			// 2. 가중치가 가장 작은 간선부터 선택
			Edge cur = pq.poll();
			
			// 3. 시작점과 끝점의 최상위 노드가 같지 않을 경우 (사이클이 형성되지 않는 것)
			// (간선의 양끝 정점이 같은 집합에 속해있지 않은 경우, 연결되어있지 않은 경우) union
			if (find(cur.s) != find(cur.e)) {
				union(cur.s, cur.e);
				answer += cur.w; // 가중치 더함
			}
		}
		
		System.out.println(answer);
	}
	
	// 노드 x가 속하는 부모 노드(최상위 노드)를 찾음
	private static int find(int x) {
		if (parent[x] == x) {
			return x;
		}
		
		return parent[x] = find(parent[x]);
	}
	
	// 두 개의 노드가 속한 집합을 합침(연결함)
	private static void union(int x, int y) {
//		parent[x] = y; // 이 문제에서는 이렇게 해도 상관 없음, 하지만 밑에 코드로 작성하는 것이 올바른 방법
		
		int rootX = find(x);
		int rootY = find(y);
		
		// 노드 번호가 작은 것을 부모로 설정
		if (rootX < rootY) {
			parent[rootY] = rootX;
		} else {
			parent[rootX] = rootY;
		}
	}

}
