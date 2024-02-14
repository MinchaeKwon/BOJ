/**
 * 1715 카드 정렬하기
 * https://www.acmicpc.net/problem/1715
 * 
 * @author minchae
 * @date 2024. 2. 14.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		long answer = 0;
		
		while (pq.size() > 1) {
			int n1 = pq.poll();
			int n2 = pq.poll();
			
			answer += (n1 + n2);
			
			pq.add(n1 + n2);
		}
		
		System.out.println(answer);
	}

}
