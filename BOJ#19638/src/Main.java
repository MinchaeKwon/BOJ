/**
 * 19638 센티와 마법의 뿅망치
 * https://www.acmicpc.net/problem/19638
 * 
 * @author minchae
 * @date 2024. 9. 4.
 */

import java.util.*;
import java.io.*;

public class Main {
	
	static int N, H, T;
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(br.readLine());
			
			// 자신보다 키가 크거나 같은 거인만 추가
			if (height >= H) {
				pq.add(height);	
			}
		}

		int cnt = 0;
		
		while (T-- > 0 && !pq.isEmpty()) {
			int cur = pq.poll();
			
			// 키가 1인 경우에는 영향 받지 않음
			if (cur > 1) {
				cur /= 2;
			}
			
			// 줄인 키가 센티보다 크거나 같은 경우 다시 큐에 삽입
			if (cur >= H) {
				pq.add(cur);
			}
			
			cnt++; // 뿅망치 사용 횟수 증가
		}
		
		if (!pq.isEmpty()) {
			// 큐에 값이 있는 경우 모든 거인이 센티보다 작지 않은 것
			System.out.println("NO");
			System.out.println(pq.poll());
		} else {
			System.out.println("YES");
			System.out.println(cnt);
		}
	}

}
