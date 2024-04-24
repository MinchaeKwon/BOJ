/**
 * 2109 순회강연
 * https://www.acmicpc.net/problem/2109
 * 
 * @author minchae
 * @date 2024. 4. 24.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node> {
		int p;
		int d;
		
		public Node(int p, int d) {
			this.p = p;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			if (this.p == o.p) {
				return o.d - this.d;
			}
			
			return o.p - this.p;
		}
	}
	
	static int N;
	static PriorityQueue<Node> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		int max = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			pq.add(new Node(p, d));
			max = Math.max(max, p);
		}
		
		int answer = 0;
		boolean[] check = new boolean[max + 1];
		
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			
			for (int i = cur.d; i >= 1; i--) {
				if (!check[i]) {
					check[i] = true;
					answer += cur.p;
					
					break;
				}
			}
		}
		
		System.out.println(answer);
		
	}

}
