/**
 * 14621 나만 안되는 연애
 * https://www.acmicpc.net/problem/14621
 * 
 * @author minchae
 * @date 2024. 10. 10.
 * */

import java.util.*;
import java.io.*;

public class Main {
	
	static class Node implements Comparable<Node> {
		int e;
		int d;
		
		public Node(int e, int d) {
			this.e = e;
			this.d = d;
		}

		@Override
		public int compareTo(Main.Node o) {
			return Integer.compare(this.d, o.d);
		}
	}
	
	static int N, M;
	
	static char[] type;
	static ArrayList<Node>[] list; // 남자인지 여자인지 구분해야 함

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		type = new char[N + 1];
		list = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		type = br.readLine().toCharArray();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			// 서로 타입이 다를 경우에만 추가
			if (type[u] != type[v]) {
				list[u].add(new Node(v, d));
				list[v].add(new Node(u, d));	
			}
		}

		int answer = prim();
		
		System.out.println(answer);
	}

	private static int prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];
		
		pq.add(new Node(1, 0));
		
		int result = 0;
		int cnt = 1;
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if (visited[cur.e]) {
				continue;
			}
			
			visited[cur.e] = true;
			
			result += cur.d;
			cnt++;
			
			if (cnt == N - 1) {
				break;
			}
			
			for (Node next : list[cur.e]) {
				if (!visited[next.e]) {
					pq.add(next);
				}
			}
		}
		
		return result;
	}
}
