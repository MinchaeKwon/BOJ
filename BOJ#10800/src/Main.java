/**
 * 10800 컬러볼
 * https://www.acmicpc.net/problem/10800
 * 
 * @author minchae
 * @date 2024. 3. 13.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static class Ball implements Comparable<Ball> {
		int num;
		int color;
		int size;
		
		public Ball(int num, int color, int size) {
			this.num = num;
			this.color = color;
			this.size = size;
		}

		@Override
		public int compareTo(Ball o) {
			return this.size - o.size;
		}
	}
	
	static int N;
	static ArrayList<Ball> list = new ArrayList<>();
	
	static int[] color; // 같은 색을 가진 공의 크기 합 저장
	static int[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		color = new int[N + 1];
		answer = new int[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());	
			
			int color = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			
			list.add(new Ball(i, color, size));
		}

		Collections.sort(list);
		
		catchBall();
		
		StringBuilder sb = new StringBuilder();
		
		for (int n : answer) {
			sb.append(n + "\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void catchBall() {
		int idx = 0;
		int sum = 0;
		
		for (Ball b : list) {
			while (b.size > list.get(idx).size) {
				sum += list.get(idx).size;
				color[list.get(idx).color] += list.get(idx).size;
				
				idx++;
			}
			
			answer[b.num] = sum - color[b.color]; // 누적 합에서 같은 색을 가진 공의 크기를 빼줌
		}
	}

}
