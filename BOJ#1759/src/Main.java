/**
 * 1759 암호 만들기
 * https://www.acmicpc.net/problem/1759
 * 
 * @author minchae
 * @date 2021. 7. 16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int L, C;
	static boolean[] visited;
	static char[] alphabet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		alphabet = new char[C];
		visited = new boolean[C];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alphabet[i] = st.nextToken().charAt(0);
		}
		
		// 암호를 이루는 알파벳이 암호에서 증가하는 순서로 배열되기 때문에 알파벳순으로 정렬
		Arrays.sort(alphabet);

		dfs(0, 0);
	}
	
	public static void dfs(int start, int depth) {
		if (depth == L) { // L개를 뽑은 경우
			String result = "";
			int vowel = 0;
			int consonant = 0;
			
			// L개를 뽑은 경우에 반복문 안에서 최소 한 개의 모음과 최소 두 개의 자음이 포함되었는지 확인해야 함
			for (int i = 0; i < C; i++) {
				if (visited[i]) { // 방문된 문자 번호일 경우
					char cur = alphabet[i];
					
					result += cur; // 결과 문자열에 추가
					
					// 모음, 자음 개수를 셈
					if (cur == 'a' || cur == 'e' || cur == 'i' || cur == 'o' || cur == 'u') {
						vowel++;
					}
					else {
						consonant++;
					}
				}
			}
			
			// 모음 한 개 이상, 자음 두 개 이상이 있을 경우에만 암호 출력
			if (vowel >= 1 && consonant >= 2) {
				System.out.println(result);
			}
			
			return;
		}
		
		// i번째 문자를 선택한 경우와 선택하지 않은 경우로 나누어서 확인하는 것
		// start를 기준으로 start 보다 작으면 선택하지 않게 되고, start  보다 크면 선택하게 함
		for (int i = start; i < C; i++) {
			visited[i] = true;
			dfs(i + 1, depth + 1);
			visited[i] = false;
		}
	}

}
