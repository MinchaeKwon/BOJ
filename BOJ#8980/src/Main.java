/**
 * 8980 택배
 * https://www.acmicpc.net/problem/8980
 * 
 * @author minchae
 * @date 2022. 4. 24.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Delivery implements Comparable<Delivery> {
	int start;
	int end;
	int num;
	
	public Delivery(int start, int end, int num) {
		this.start = start;
		this.end = end;
		this.num = num;
	}

	// 받는 마을을 기준으로 오름차순 정렬 (받는 마을이 같다면 보내는 마을을 기준으로 오름차순 정렬)
	@Override
	public int compareTo(Delivery o) {
		if (this.end == o.end) {
			return this.start - o.start;
		}
		
		return this.end - o.end;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(br.readLine());
		
		ArrayList<Delivery> deliveries = new ArrayList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			deliveries.add(new Delivery(start, end, num));
		}
		
		Collections.sort(deliveries);
		
		int[] box = new int[N + 1];
		int boxCnt = 0;
		
		for (Delivery delivery : deliveries) {
			int start = delivery.start;
			int end = delivery.end;
			int num = delivery.num;
			
			int max = 0;
			
			// 이미 실린 박스 중에서 최댓값을 구함
			for (int i = start; i < end; i++) {
				max = Math.max(max, box[i]);
			}
			
			int load = Math.min(C - max, num); // 실을 수 있는 박스의 수
			boxCnt += load;
			
			for (int i = start; i < end; i++) {
				box[i] += load;
			}
		}
		
		System.out.println(boxCnt);
	}

}
