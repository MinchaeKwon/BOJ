/**
 * 15688 수 정렬하기 5
 * https://www.acmicpc.net/problem/15688
 * 
 * @author Minchae Gwon
 * @date 2021.3.2
 * 
 * 언어 java 8로 할 경우 통과
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());
		
		// Collections.sort 사용
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
		
		
		// 계수 정렬 사용
		int[] negative = new int[1000001];
		int[] positive = new int[1000001];
		
		for (int i = 0; i < n; i++) {
			int input = Integer.parseInt(br.readLine());
			
			if (input < 0) {
				negative[Math.abs(input)]++;
			} else {
				positive[input]++;
			}
			
		}
		
		for (int i = 1000000; i >= 0 ; i--) {
			while (negative[i] > 0) { //숫자 i의 개수가 0이 아닐 때까지 출력
				sb.append(-i + "\n");
				negative[i]--;
			}
		}
		
		for (int i = 0; i < 1000001; i++) {
			while (positive[i] > 0) { //숫자 i의 개수가 0이 아닐 때까지 출력
				sb.append(i + "\n");
				positive[i]--;
			}
		}
		
		System.out.println(sb);
		
	}
}
