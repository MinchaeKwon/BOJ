/**
 * 13904 과제
 * https://www.acmicpc.net/problem/13904
 * 
 * @author minchae
 * @date 2024. 2. 17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] list = new ArrayList[1001];
		
		for (int i = 0; i < 1001; i++) {
			list[i] = new ArrayList<>();
		}
		
		int maxDay = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int d = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			list[d].add(w);
			
			maxDay = Math.max(maxDay, d);
		}
		
		int answer = 0;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder()); // 특정 날짜에 끝낼 수 있는 과제 후보 저장
		
		for (int day = maxDay; day > 0; day--) {
			// 해당 날짜에 끝낼 수 있는 과제 추가
			for (int weight : list[day]) {
				pq.add(weight);
			}
			
			if (!pq.isEmpty()) {
				answer += pq.poll();
			}
		}
		
		System.out.println(answer);
	}

}
