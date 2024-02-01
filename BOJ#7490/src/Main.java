/**
 * 7490 0 만들기
 * https://www.acmicpc.net/problem/7490
 * 
 * @author minchae
 * @date 2024. 2. 1.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static String[] op = {"+", "-", " "};
	
	static ArrayList<String> answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			N = Integer.parseInt(br.readLine());
			
			answer = new ArrayList<>();
			
			comb(1, "1");
			
			Collections.sort(answer);
			
			for (String str : answer) {
				System.out.println(str);
			}
			System.out.println();
		}
	}
	
	// 조합을 통해 만들 수 있는 식 구함
	private static void comb(int depth, String expression) {
		if (depth == N) {
			// 숫자를 이어 붙이기 위해 공백은 제거함
			if (calculate(expression.replaceAll(" ", ""))) {
				answer.add(expression);
			}
			
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			comb(depth + 1, expression + op[i] + (depth + 1));
		}
	}
	
	// 완성된 식 계산 -> 0을 만들 수 있는지 확인
	private static boolean calculate(String expression) {
		// 처음에 split을 사용해서 문자열을 잘랐는데 구분자는 포함이 안돼서 StringTokenizer로 변경
		StringTokenizer st = new StringTokenizer(expression, "+|-", true); // 구분자 포함해서 문자열 자르기
		
		int result = Integer.parseInt(st.nextToken());
		
		while (st.hasMoreElements()) {
			String s = st.nextToken();
			
			if (s.equals("+")) {
				result += Integer.parseInt(st.nextToken());
			} else if (s.equals("-")) {
				result -= Integer.parseInt(st.nextToken());
			}
		}
		
		return result == 0;
	}

}
