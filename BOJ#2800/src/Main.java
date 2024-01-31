/**
 * 2800 괄호 제거하기
 * https://www.acmicpc.net/problem/2800
 * 
 * @author minchae
 * @date 2024. 1. 31.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Stack;

public class Main {
	
	static class Pair {
		int l;
		int r;
		
		public Pair(int l, int r) {
			this.l = l;
			this.r = r;
		}
	}
	
	static String input;
	
	static Stack<Integer> stack = new Stack<>();
	static ArrayList<Pair> bracket = new ArrayList<>(); // 쌍이 맞는 괄호의 인덱스를 저장
	
	static TreeSet<String> answer = new TreeSet<>(); // 중복 제거와 사전순 정렬을 위해 Set을 사용

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		input = br.readLine();
		
		for (int i = 0; i < input.length(); i++) {
			Character c = input.charAt(i);
			
			if (c == '(') {
				stack.push(i);
			} else if (c == ')') {
				bracket.add(new Pair(stack.pop(), i));
			}
		}
		
		comb(0, input);
		
		for (String str : answer) {
			System.out.println(str);
		}
	}
	
	// 괄호의 쌍들을 구해서 조합을 통해 괄호를 제거하고 만들 수 있는 식을 구함
	private static void comb(int depth, String str) {
		if (depth == bracket.size()) {
			// 입력값이 아닌 경우에만 추가
			if (!str.equals(input)) {
				answer.add(str.replaceAll(" ", ""));	
			}
			
			return;
		}
		
		// 괄호 제거
		Pair cur = bracket.get(depth);
		StringBuilder sb = new StringBuilder(str);
		
		sb.setCharAt(cur.l, ' ');
		sb.setCharAt(cur.r, ' ');
		
		comb(depth + 1, sb.toString());
		
		// 괄호 제거 X
		comb(depth + 1, str);
	}

}
