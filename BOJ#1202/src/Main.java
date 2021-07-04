/**
 * 1202 보석 도둑
 * https://www.acmicpc.net/problem/1202
 * 
 * @author minchae
 * @date 2021. 7. 4.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Jewelry implements Comparable<Jewelry> {
	int weight;
	int price;
	
	public Jewelry(int weight, int price) {
		this.weight = weight;
		this.price = price;
	}

	// 보석 무게를 기준으로 오름차순 정렬(무게가 같은 경우에는 가격을 기준으로 내림차순 정렬)
	@Override
	public int compareTo(Jewelry o) {
		if (this.weight == o.weight) {
			return o.price - this.price;
		}
		else {
			return this.weight - o.weight;
		}
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		// ArrayList도 사용 가능
		Jewelry[] jewelry = new Jewelry[N]; // 보석의 무게와 가격 저장
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int M = Integer.parseInt(st.nextToken());
			int V = Integer.parseInt(st.nextToken());
			
			jewelry[i] = new Jewelry(M, V);
		}
		
		Arrays.sort(jewelry);
		
		int[] bag = new int[K]; // 가방에 담을 수 있는 최대 무게 저장
		
		for (int i = 0; i < K; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
		
		// 오름차순 정렬
		Arrays.sort(bag);
		
		// 보석 가격을 기준으로 내림차순 정렬되도록 Comparator.reverseOrder()를 추가
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		
		// N, K는 최대 300,000이고, 보석 가격이 모두 1,000,000이면 결과의 최댓값은 300,000,000,000이 됨
		// int 범위 2,147,483,647을 넘기 때문에 result를 int로 선언하면 틀림
		long result = 0;
		int idx = 0;
		
		for (int cur : bag) {
			// 현재 보석의 인덱스가 총 보석 개수보다 작을 경우 && 현재 가방 무게보다 보석 무게가 작거나 같을 경우
			while (idx < N && jewelry[idx].weight <= cur) {
				// 큐에 보석 가격을 추가
				pq.add(jewelry[idx].price);
				
				idx++;
			}
			
			// 가방에 보석을 넣음
			// 큐가 비어있지 않을 경우 큐 맨 앞에 있는 보석 가격을 빼서 더함
			// 내림차순 정렬이 되어있으므로 맨 앞에 있는 보석 가격이 제일 큰 가격
			if (!pq.isEmpty()) {
	 			result += pq.poll();
	 		}
		}

		System.out.println(result);
		
	}

}
