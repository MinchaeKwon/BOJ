/**
 * 2661 좋은 수열
 * https://www.acmicpc.net/problem/2661
 * 
 * @author minchae
 * @date 2024. 7. 30.
 */

import java.io.*;

public class Main {
	
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		dfs("");
	}
	
	private static void dfs(String result) {
		if (result.length() == N) {
			System.out.println(result);
			System.exit(0);
		}
		
		for (int i = 1; i <= 3; i++) {
			if (check(result + i)) {
				dfs(result + i);
			}
		}
	}
	
	private static boolean check(String s) {
		int len = s.length() / 2;
		
		for (int i = 1; i <= len; i++) {
			// 마지막에서 i만큼의 문자열과 그 이전의 문자열과 같은 경우
			if (s.substring(s.length() - i).equals(s.substring(s.length() - 2 * i, s.length() - i))) {
                return false;
            }
		}
		
		return true;
	}

}
