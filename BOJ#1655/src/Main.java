/**
 * 1655 가운데를 말해요
 * https://www.acmicpc.net/problem/1655
 * 
 * @author minchae
 * @date 2021. 6. 29.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 오름차순으로 정렬
		PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder()); // 내림차순으로 정렬
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			// 최소 힙과 최대 힙에 번갈아 가면서 숫자를 넣어줌
			// 최소 힙과 최대 힙의 크기가 같을 경우 최대 힙에 숫자를 넣고, 아닐 경우 최소 힙에 넣음
			if (minHeap.size() == maxHeap.size()) {
				maxHeap.add(num);
			}
			else {
				minHeap.add(num);
			}
			
			if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
				// 최대힙의 최대값이 최소힙의 최소값보다 큰 경우
				if (maxHeap.peek() > minHeap.peek()) {
					// 두 숫자의 위치를 바꿔줌
					int tmp = minHeap.poll();
					
					minHeap.add(maxHeap.poll());
					maxHeap.add(tmp);
				}
			}
			
			// maxHeap의 최대값이 가운데 숫자가 됨
//			System.out.println(maxHeap.peek());
			bw.write(maxHeap.peek() + "\n");
		}
		
		bw.flush();
		bw.close();
		
	}

}
