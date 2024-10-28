/**
 * 1484 다이어트
 * https://www.acmicpc.net/problem/1484
 * 
 * @author minchae
 * @date 2024. 10. 28.
 * */

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int G = Integer.parseInt(br.readLine()); // 현재 몸무게에서 기억하고 있던 몸무게의 제곱을 뺀 것
		
		int start = 1;
		int end = 2;
		
		boolean flag = false;
		
		while (end <= 100000) {
			int n1 = start * start;
			int n2 = end * end;
			
			// 원하는 값이 나온 경우
			if (n2 - n1 == G) {
				System.out.println(end);
				flag = true;
			}
			
			if (n2 - n1 > G) {
				start++;
			} else {
				end++;
			}
		}
		
		if (!flag) {
			System.out.println(-1);
		}
	}

}