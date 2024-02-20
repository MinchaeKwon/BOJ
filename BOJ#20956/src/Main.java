/**
 * 20956 아이스크림 도둑 지호
 * https://www.acmicpc.net/problem/20956
 * 
 * @author minchae
 * @date 2024. 2. 20.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	static class Icecream implements Comparable<Icecream> {
		int idx;
		int size;
		
		public Icecream(int idx, int size) {
			this.idx = idx;
			this.size = size;
		}

		@Override
		public int compareTo(Icecream o) {
			if (this.size == o.size) {
				return this.idx - o.idx;
			}
			
			return o.size - this.size;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		
		Icecream[] input = new Icecream[N];
		HashMap<Integer, Integer> hm = new HashMap<>(); // 아이스크림 양이 같은 것의 개수
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			int size = Integer.parseInt(st.nextToken());
			
			input[i] = new Icecream(i + 1, size);
			hm.put(size, hm.getOrDefault(size, 0) + 1);
		}
		
		Arrays.sort(input);
		
		StringBuilder sb = new StringBuilder();
		
		int next = 0;
		boolean reverse = false; // true인 경우 순서를 바꿈
		
		while (M > 0) {
			int left = next; // 같은 양의 아이스크림 중 인덱스가 가장 작은 것
			int right = left + hm.get(input[left].size) - 1; // 같은 양의 아이스크림 중 인덱스가 가장 큰 것
			
			next = right + 1; // 현재 아이스크림 양 다음으로 많은 양을 가진 아이스크림 인덱스
			
			boolean mint = input[left].size % 7 == 0;
			
			while (left <= right && M-- > 0) {
				// 이전에 먹었던 아이스크림이 민초인 경우 인덱스가 가장 큰 것을 추가, 아닌 경우 작은 것을 추가
				sb.append(reverse ? input[right--].idx : input[left++].idx);
				sb.append("\n");
				
				if (mint) {
					reverse = !reverse;
				}
			}
		}

		System.out.println(sb.toString());
	}

}
