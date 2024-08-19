/**
 * 1197 최소 스패닝 트리
 * https://www.acmicpc.net/problem/1197
 * 
 * @author minchae
 * @date 2024. 8. 19.
 * 
 * 프림 알고리즘 이용
 */

import java.util.*;
import java.io.*;

public class Prim {
	
	// 정점을 기반으로 하기 때문에 연결된 끝점과 가중치만 저장
	static class Node implements Comparable<Node> {
		int e;
		int w;
		
		public Node(int e, int w) {
			this.e = e;
			this.w = w;
		}

		// 가중치를 기준으로 오름차순
		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
		
	}
	
	static int V;
	static ArrayList<Node>[] list; // 정점 정보 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken()); // 정점 개수
		int E = Integer.parseInt(st.nextToken()); // 간선 개수
		
		list = new ArrayList[V + 1];
		
		for (int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			list[A].add(new Node(B, C));
			list[B].add(new Node(A, C));
		}
		
		System.out.println(prim());
	}
	
	private static int prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];
		
		// 1번 정점부터 시작 -> 1번에 연결되어 있는 곳 탐색
		pq.add(new Node(1, 0));
		
		int sum = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// 이미 방문한 경우 (사이클이 형성되는 경우) 넘어감
			if (visited[cur.e]) {
				continue;
			}
			
			// 방문 처리 하고 가중치 더함
			visited[cur.e] = true;
			sum += cur.w;
			
			// 해당 노드에 연결된 다른 노드 탐색하기 위해 큐에 삽입
			for (Node next : list[cur.e]) {
				if (!visited[next.e]) {
					pq.add(next);
				}
			}
		}
		
		return sum;
	}

}
