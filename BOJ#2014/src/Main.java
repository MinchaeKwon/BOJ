/**
 * 2014 소수의 곱
 * https://www.acmicpc.net/problem/2014
 * 
 * @author minchae
 * @date 2024. 9. 4.
 * */

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Long> pq = new PriorityQueue<>();
		long[] arr = new long[K];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			pq.add(arr[i]);
		}
		
		// N번째 숫자를 출력해야 돼서 1까지 진행
		while (N-- > 1) {
			long num = pq.poll();
			
			// 큐에서 뺀 숫자에 현재 배열에 있는 소수들을 곱해서 넣어줌
			for (long i : arr) {
				pq.add(num * i);
				
				// 해당 소수로 나누어 떨어지는 경우 소인수분해를 하면 해당 소수가 이미 존재하는 것임
				// 해당 소수보다 큰 소수는 곱하면 안됨 -> 중복 없앱
				if (num % i == 0) {
					break;
				}
			}
		}

		System.out.println(pq.poll());
	}

}
