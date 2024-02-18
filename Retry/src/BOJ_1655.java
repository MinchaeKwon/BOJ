/**
 * 1655 가운데를 말해요
 * https://www.acmicpc.net/problem/1655
 * 
 * @author minchae
 * @date 2024. 2. 18.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ_1655 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 오름차순 정렬
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순 정렬
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if (minHeap.size() == maxHeap.size()) {
				maxHeap.add(num);
			} else {
				minHeap.add(num);
			}
			
			if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
				// 최대힙의 최댓값이 최소힙의 최솟값보다 클 경우 최댓값과 최솟값 위치를 바꿈
				if (maxHeap.peek() > minHeap.peek()) {
					int tmp = minHeap.poll();
					
					minHeap.add(maxHeap.poll());
					maxHeap.add(tmp);
				}
			}
			
			sb.append(maxHeap.peek() + "\n");
		}

		System.out.println(sb.toString());
	}

}
