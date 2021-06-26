/**
 * 1766 문제집
 * https://www.acmicpc.net/problem/1766
 * 
 * @author minchae
 * @date 2021. 6. 26.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 문제의 수
		int M = Integer.parseInt(st.nextToken()); // 좋은 문제에 대한 정보의 개수
		
		ArrayList<Integer>[] list = new ArrayList[N + 1];
		int[] indegree = new int[N + 1]; // 진입 차수
		
		for (int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			indegree[b]++;
		}
		
		// 가능하면 쉬운 문제(번호가 작은 것)부터 풀어야하기 때문에 우선순위 큐를 사용함
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 1; i < N + 1; i++) {
			if (indegree[i] == 0) {
				pq.add(i);
			}
		}
		
		while(!pq.isEmpty()) {
			int first = pq.poll();
			
			// 순서 출력
			System.out.print(first + " ");
			
			for (int next : list[first]) {
				indegree[next]--;
				
				if (indegree[next] == 0) {
					pq.add(next);
				}
			}
			
		}
		
	}

}
