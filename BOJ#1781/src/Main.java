/**
 * 1781 컵라면
 * https://www.acmicpc.net/problem/1781
 * 
 * @author minchae
 * @date 2024. 4. 25.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node> {
		int d;
		int cnt;
		
		public Node(int d, int cnt) {
			this.d = d;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			if (this.d == o.d) {
				return o.cnt - this.cnt;
			}
			
			return this.d - o.d;
		}
	}
	
	static int N;
	static Node[] arr;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		arr = new Node[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int p = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			arr[i] = new Node(p, d);
		}
		
		Arrays.sort(arr);
		
		for (Node cur : arr) {
			pq.add(cur.cnt);
			
			if (cur.d < pq.size()) {
				pq.poll();
			}
		}
		
		int answer = 0;
		
		while (!pq.isEmpty()) {
			answer += pq.poll();
		}
		
		System.out.println(answer);
		
	}

}
