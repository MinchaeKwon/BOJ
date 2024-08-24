/**
 * 5904 Moo 게임
 * https://www.acmicpc.net/problem/5904
 * 
 * @author minchae
 * @date 2024. 8. 24.
 */

import java.io.*;

public class Main {
	
	static int N;
	static char answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		solve(N);

		System.out.println(answer);
	}
	
	private static void solve(int num) {
		int size = 3; // 문자열 길이
		int idx = 0; // 문자열 반복 깊이 계산
		
		if (num == 1) {
			answer = 'm'; // 첫 번째는 무조건 m
		} else if (num <= 3) {
			answer = 'o'; // 2, 3번째는 o
		} else {
			// num이 포함된 문자열 크기 확장
			while (size < num) {
				size = size * 2 + idx + 4; // (m과 k+2개의 o) 길이만큼 늘어남
				idx++;
			}
			
			// 추가되는 중앙 패턴의 길이를 제외하고 남은 앞, 뒤 S(k-1) 길이
			int len = (size - idx - 3) / 2;
			
			// num이 어디에 위치하는지 판단
			if (size - len + 1 <= num) { // 중앙 패턴 뒤에 위치하는 경우
				solve(num - size + len); // 뒤쪽 계산
			} else if (num == len + 1) {
				answer = 'm'; // 중앙 패턴 처음에 위치하면 m
			} else {
				answer = 'o';
			}
		}
	}

}
