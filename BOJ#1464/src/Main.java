/**
 * 1464 뒤집기 3
 * https://www.acmicpc.net/problem/1464
 * 
 * @author minchae
 * @date 2024. 2. 21.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] input = br.readLine().toCharArray();
		
		ArrayDeque<Character> dq = new ArrayDeque<>();
		
		dq.add(input[0]);
		
		for (int i = 1; i < input.length; i++) {
			//dq의 맨 마지막에는 가장 작은 문자가 들어있음 -> 해당 문자를 기준으로 함
			if (dq.peekLast() < input[i]) {
				dq.addFirst(input[i]); // 현재 문자가 이전 문자보다 큰 경우 앞에 추가
			} else {
				dq.addLast(input[i]); // 현재 문자가 이전 문자보다 작은 경우 뒤에 추가 (가장 작은 문자 갱신)
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		while (!dq.isEmpty()) {
			sb.append(dq.pollLast()); // 맨 뒤에 저장된 것부터 출력하면 사전순으로 출력됨
		}
		
		System.out.println(sb.toString());
	}

}
