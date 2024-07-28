/**
 * 17255 N으로 만들기
 * https://www.acmicpc.net/problem/17255
 * 
 * @author minchae
 * @date 2024. 7. 28.
 */

import java.io.*;
import java.util.*;

public class Main {
	
	static char[] N;
	static HashSet<String> hs = new HashSet<>(); // N을 만드는 경로 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = br.readLine().toCharArray();
		
		for (int i = 0; i < N.length; i++) {
			dfs(i, i, "" + N[i], "" + N[i]);
		}

		System.out.println(hs.size());
	}
	
	// 특정 숫자 기준으로 왼쪽, 오른쪽 확인하면서 N을 만듦
	private static void dfs(int left, int right, String s, String result) {
		// 입력 받은 숫자 길이만큼 다 돌아본 경우 종료
		if (left == 0 && right == N.length - 1) {
			hs.add(result);
			return;
		}
		
		if (left - 1 >= 0) {
			String tmp = N[left - 1] + s;
			dfs(left - 1, right, tmp, result + " " + tmp);
		}
		
		if (right + 1 < N.length) {
			String tmp = s + N[right + 1];
			dfs(left, right + 1, tmp, result + " " + tmp);
		}
	}

}
