/**
 * 2751 수 정렬하기2
 * https://www.acmicpc.net/problem/2751
 * 
 * @author Minchae Gwon
 * @date 2021.3.2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		// 1. Collections.sort를 이용
//		ArrayList<Integer> list = new ArrayList<>();
//		
//		for (int i = 0; i < n; i++) {
//			list.add(Integer.parseInt(br.readLine()));
//		}
//		
//		Collections.sort(list);
//		
//		for (int v : list) {
//			sb.append(v + "\n");
//		}
//		
//		System.out.println(sb);
		
		// 2. 우선순위 큐 사용
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for (int i = 0; i < n; i++) {
			pq.add(Integer.parseInt(br.readLine()));
		}
		
		while (!pq.isEmpty()) {
			sb.append(pq.poll() + "\n");
		}
		
		System.out.println(sb);
		
	}

}