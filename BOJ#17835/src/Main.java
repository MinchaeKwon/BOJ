/**
 * 17835 면접보는 승범이네
 * https://www.acmicpc.net/problem/17835
 * 
 * @author minchae
 * @date 2024. 10. 21.
 * */

import java.io.*;
import java.util.*;

public class Main {
	
	static class Node implements Comparable<Node> {
		int e;
		long d;
		
		public Node(int e, long d) {
			this.e = e;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.d, o.d);
		}
	}
	
	static int N, M, K;
	
	static ArrayList<Node>[] list;
	static long[] dist;
	
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N + 1];
		dist = new long[N + 1];
		pq = new PriorityQueue<>();
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		Arrays.fill(dist, Long.MAX_VALUE);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int U = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			long C = Integer.parseInt(st.nextToken());
			
			list[V].add(new Node(U, C)); // 면접장에서 도시까지의 거리니까 반대로 넣어주기
		}
		
		st = new StringTokenizer(br.readLine());
		
		// 면접장을 먼저 큐에 삽입
		for (int i = 0; i < K; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			pq.add(new Node(num, 0));
			dist[num] = 0;
		}
		
		int answer = dijkstra();
		
		System.out.println(answer);
		System.out.println(dist[answer]);
	}

	private static int dijkstra() {
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// 이미 최솟값이 저장된 경우 넘어감
			if (dist[cur.e] < cur.d) {
				continue;
			}
			
			// 최단 거리 찾기
			for (Node next : list[cur.e]) {
				if (dist[next.e] > dist[cur.e] + next.d) {
					dist[next.e] = dist[cur.e] + next.d;
					pq.add(new Node(next.e, dist[next.e]));
				}
			}
		}
		
		long max = 0;
		int result = 0;
		
		// 가장 먼 도시 구하기
		for (int i = 1; i <= N; i++) {
			if (dist[i] == Long.MAX_VALUE) {
				continue;
			}
			
			if (max < dist[i]) {
				max = dist[i];
				result = i;
			}
		}
		
		return result;
	}
	
}