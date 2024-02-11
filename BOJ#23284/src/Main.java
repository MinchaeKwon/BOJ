/**
 * 23284 모든 스택 수열
 * https://www.acmicpc.net/problem/23284
 * 
 * @author minchae
 * @date 2024. 2. 11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int n;
	static int[] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		result = new int[n];
		
		backtracking(0, 0);
	}
	
	private static void backtracking(int depth, int next) {
		if (depth == n) {
			for (int num : result) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		
		for (int i = 1; i <= n; i++) {
			result[depth] = i;
			
			// 중복 확인
			boolean flag = true;
			
			for (int j = 0; j < depth; j++) {
				// 해당 숫자가 이미 수열에 포함되어 있는 경우
				if (result[j] == result[depth]) {
					flag = false;
				}
			}
			
			// 새로운 숫자인 경우
			if (flag) {
				// 이전 숫자 보다 현재 숫자가 크고 다음에 넣을 숫자 보다 현재 숫자가 작은 경우 잘못된 수열
				if (depth > 0 && result[depth - 1] < result[depth] && result[depth] < next) {
					break; // 바로 종료
				}
				
				if (next <= result[depth]) {
					backtracking(depth + 1, result[depth] + 1); // 새로운 수를 넣어줌
				} else {
					backtracking(depth + 1, next); // 기존 숫자 그대로 넣음
				}
			}
		}
	}
}
