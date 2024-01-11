/**
 * 10773 제로
 * https://www.acmicpc.net/problem/10773
 * 
 * @author minchae
 * @date 2024. 1. 11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		
		for (int i = 0; i < K; i++) {
			int n = Integer.parseInt(br.readLine());
			
			if (n == 0) {
				stack.pop(); // 0인 경우 가장 최근에 쓴 수를 지움
			} else {
				stack.add(n);
			}
		}
		
		int answer = 0;
		
		for (int n : stack) {
			answer += n;
		}
		
		System.out.println(answer);

	}

}
