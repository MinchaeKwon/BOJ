/**
 * 2075 - N번째 큰 수
 * https://www.acmicpc.net/problem/2075
 * 
 * @author Minchae Gwon
 * @date 2021.1.26
 * 
 * 입력
 * 첫째 줄에 N(1 ≤ N ≤ 1,500)이 주어진다. 다음 N개의 줄에는 각 줄마다 N개의 수가 주어진다. 표에 적힌 수는 -10억보다 크거나 같고, 10억보다 작거나 같은 정수이다.
 * 
 * 출력
 * 첫째 줄에 N번째 큰 수를 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		//int형 priorityQueue 선언 (우선순위가 높은 숫자 순)
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < n; j++) {
				pq.add(Integer.parseInt(st.nextToken()));
			}
		}
		
		//n번째 큰 수를 찾는 것이기 때문에 우선순위큐에서 n-1까지 값을 삭제함
		for (int i = 0; i < n -1; i++) {
			pq.poll();
		}
		
		//n번쩨 큰 수 출력
		System.out.println(pq.poll());
	}

}
