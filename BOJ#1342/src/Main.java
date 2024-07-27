/**
 * 행운의 문자열
 * https://www.acmicpc.net/problem/1342
 * 
 * @author minchae
 * @date 2024. 7. 26.
 */

import java.io.*;

public class Main {

	static String input;
	static int[] alpha = new int[27];
	
	static int answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = br.readLine();
		
		// 주어진 문자열에 나타나는 알파벳의 개수를 증가시킴
		for (int i = 0; i < input.length(); i++) {
			alpha[input.charAt(i) - 'a']++;
		}

		dfs(0, ' ');
		
		System.out.println(answer);
	}
	
	// 현재 깊이, 이전 문자열
	private static void dfs(int depth, char pre) {
		if (depth == input.length()) {
			answer++;
			return;
		}
		
		for (int i = 0; i < alpha.length; i++) {
			if (alpha[i] == 0) {
				continue;
			}
			
			// 이전에 등장한 문자열과 다른 경우
			if (pre != (char) (i + 'a')) {
				alpha[i]--;
				dfs(depth + 1, (char) (i + 'a'));
				alpha[i]++;
			}
		}
	}

}
