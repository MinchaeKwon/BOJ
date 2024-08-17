/**
 * 12904 A와 B
 * https://www.acmicpc.net/problem/12904
 * 
 * @author minchae
 * @date 2024. 8. 17.
 */

import java.io.*;

// S에서 T로 만드는 것은 두 가지 경우를 다 살펴봐야 함 (시간 많이 소요)
// 반대로 T에서 S로 만드는 것은 한 가지 연산만 하면 됨 (마지막이 A인 경우 A를 제거, B인 경우 B를 빼고 뒤집으면 됨)
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder S = new StringBuilder(br.readLine());
		StringBuilder T = new StringBuilder(br.readLine());
		
		while (S.length() < T.length()) {
			if (T.charAt(T.length() - 1) == 'A') {
				// A 제거
				T.deleteCharAt(T.length() - 1);
			} else if (T.charAt(T.length() - 1) == 'B') {
				// B 제거 후 문자열 뒤집기
				T.deleteCharAt(T.length() - 1).reverse();
			}
		}
		
		System.out.println(S.toString().equals(T.toString()) ? 1 : 0);
	}

}
