/**
 * 1062 가르침
 * https://www.acmicpc.net/problem/1062
 * 
 * @author minchae
 * @date 2024. 3. 27.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K;
	
	static String[] words;
	static boolean[] visited;
	
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		words = new String[N];
		visited = new boolean[26];
		
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}
		
		visited['a' - 'a'] = true;
		visited['n' - 'a'] = true;
		visited['t' - 'a'] = true;
		visited['i' - 'a'] = true;
		visited['c' - 'a'] = true;

		if (K < 5) {
			System.out.println(0);
		} else {
			backtracking(0, 0);
			System.out.println(max);
		}
	}
	
	private static void backtracking(int start, int depth) {
		if (depth + 5 == K) {
			max = Math.max(max, countWord());
			return;
		}
		
		for (int i = start; i < 26; i++) {
			if (visited[i]) {
				continue;
			}
			
			visited[i] = true;
			backtracking(i + 1, depth + 1);
			visited[i] = false;
		}
	}
	
	private static int countWord() {
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			char[] arr = words[i].toCharArray();
			boolean flag = true;
			
			for (int c : arr) {
				if (!visited[c - 'a']) {
					flag = false;
					break;
				}
			}
			
			if (flag) {
				cnt++;
			}
		}
		
		return cnt;
	}

}
