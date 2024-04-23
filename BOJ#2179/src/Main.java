/**
 * 2179 비슷한 단어
 * https://www.acmicpc.net/problem/2179
 * 
 * @author minchae
 * @date 2024. 4. 23.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N;
	static String[] words;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		
		words = new String[N];
		
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine();
		}
		
		int max = 0;
		int x = 0;
		int y = 0;
		
		for (int i = 0; i < N - 1; i++) {
			for (int j = i  + 1; j < N; j++) {
				int cnt = getPrefix(words[i], words[j]);
				
				if (cnt > max) {
					max = cnt;
					x = i;
					y = j;
				}
			}
		}
		
		System.out.println(words[x]);
		System.out.println(words[y]);
	}
	
	private static int getPrefix(String s1, String s2) {
		int len = Math.min(s1.length(), s2.length());
		int cnt = 0;
		
		for (int i = 0; i < len; i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				break;
			}
			
			cnt++;
		}
		
		return cnt;
	}

}
