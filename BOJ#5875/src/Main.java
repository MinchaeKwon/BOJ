/**
 * 5875 오타
 * https://www.acmicpc.net/problem/5875
 * 
 * @author minchae
 * @date 2024. 8. 12.
 */

import java.io.*;

public class Main {
	
	static char[] input;
	static int total, start, end;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = br.readLine().toCharArray();
		
		System.out.println(solve());
	}
	
	private static int solve() {
		for (int i = 0; i < input.length; i++) {
			if (input[i] == '(') {
				start++;
				total++;
			} else {
				end++;
				total--;
			}
			
			// 닫는 괄호가 더 많아서 올바른 괄호가 되지 못하는 것
			if (total == -1) {
				// 이전까지 쌓아온 닫는 괄호를 수정하면 올바른 괄호가 되기 때문에 right 반환
				// '()(()))) ' : 2, 5, 6, 7번째 문자 수정 가능
				return end;
			}
			
			// 여는 괄호가 더 많은 경우
			if (total == 1) {
				// 1이되는 여는 괄호를 제외하고 뒤에 오는 여는 괄호를 수정하면 올바른 괄호가 됨
				// left를 0으로 초기화 해야 올바른 답을 구할 수 있음
				// '((()' : 2 또는 3번째 문자 수정 가능
				start = 0;
			}
		}
		
		return start;
	}

}
