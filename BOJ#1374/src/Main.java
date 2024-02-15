/**
 * 1374 강의실
 * https://www.acmicpc.net/problem/1374
 * 
 * @author minchae
 * @date 2024. 2. 15.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Pair implements Comparable<Pair> {
		int num;
		int start;
		int end;
		
		public Pair(int num, int start, int end) {
			this.num = num;
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Main.Pair o) {
			if (this.start == o.start) {
				return this.end - o.end;
			}
			
			return this.start - o.start;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Pair> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			list.add(new Pair(num, start, end));
		}
		
		Collections.sort(list);
		
		PriorityQueue<Integer> time = new PriorityQueue<>(); // 진행 중인 강의 저장
		
		int answer = 1;
		
		for (Pair cur : list) {
			// 진행 중인 강의의 끝나는 시간이 다음 강의 시작 시간보다 작거나 같다면 강의실을 비움
			while (!time.isEmpty() && time.peek() <= cur.start) {
				time.poll();
			}
			
			time.add(cur.end);
			answer = Math.max(answer, time.size());
		}
		
		System.out.println(answer);
	}

}
