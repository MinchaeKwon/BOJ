/**
 * 11000 강의실 배정
 * https://www.acmicpc.net/problem/11000
 * 
 * @author minchae
 * @date 2024. 2. 17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Pair implements Comparable<Pair> {
		int start;
		int end;
		
		public Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}

		// 시작 시간을 기준으로 오름차순 정렬, 시작 시간이 같다면 끝나는 시간을 기준으로 오름차순 정렬
		@Override
		public int compareTo(Main.Pair o) {
			if (this.start != o.start) {
				return this.start - o.start;
			}
			
			return this.end - o.end;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		PriorityQueue<Pair> times = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int S = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			
			times.add(new Pair(S, T));
		}
		
		PriorityQueue<Integer> result = new PriorityQueue<>();
		
		result.add(times.poll().end);
		
		while (!times.isEmpty()) {
			Pair cur = times.poll();
			
			// 다음 수업을 시작할 수 있는 경우
			if (result.peek() <= cur.start) {
				result.poll();
			}
			
			result.add(cur.end);
		}

		System.out.println(result.size());
	}

}
