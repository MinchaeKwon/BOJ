/**
 * 20183 골목 대장 호석 - 효율성 2
 * https://www.acmicpc.net/problem/20183
 * 
 * @author minchae
 * @date 2024. 10. 21.
 * */

import java.io.*;
import java.util.*;

public class Main {
	
	static class Node implements Comparable<Node> {
		int e;
		long cost;
		
		public Node(int e, long cost) {
			this.e = e;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.cost, o.cost);
		}
	}
	
	static int N, M, A, B;
	static long C;
	
	static ArrayList<Node>[] list;
	static long[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Long.parseLong(st.nextToken());
		
		list = new ArrayList[N + 1];
		dist = new long[N + 1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		long end = 0;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			long cost = Long.parseLong(st.nextToken());
			
			list[n1].add(new Node(n2, cost));
			list[n2].add(new Node(n1, cost));
			
			end = Math.max(end, cost);
		}

		long answer = -1;
		long start = 0;
		
		while (start <= end) {
			long mid = (start + end) / 2; // 기준이 되는 수금액
			
			if (dijkstra(mid)) {
				answer = mid;
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		
		System.out.println(answer);
	}
	
	// 최대 수금액을 받음
	private static boolean dijkstra(long limit) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(dist, Long.MAX_VALUE);
		
		pq.add(new Node(A, 0));
		dist[A] = 0;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (dist[cur.e] < cur.cost) {
				continue;
			}
			
			for (Node next : list[cur.e]) {
				// 현재 최대 수금액 미만일 경우에만 큐에 삽입
				if (next.cost <= limit && dist[next.e] > dist[cur.e] + next.cost) {
					dist[next.e] = dist[cur.e] + next.cost;
					pq.add(new Node(next.e, dist[next.e]));
				}
			}
		}
		
		// 도착지까지 가는데 총 비용이 C이하인 경우 true 반환
		return dist[B] <= C;
	}

}
