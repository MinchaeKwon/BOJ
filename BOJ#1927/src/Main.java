/**
 * 1927 최소 힙
 * https://www.acmicpc.net/problem/1927
 * 
 * @author minchae
 * @date 2021.3.18
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
			int x = Integer.parseInt(br.readLine());
			
			if (x == 0) {
				if (!pq.isEmpty()) {
					System.out.println(pq.poll());
				} else {
					System.out.println(0);
				}
			} else {
				pq.add(x);
			}
		}

	}

}
