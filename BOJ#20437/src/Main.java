/**
 * 20437 문자열 게임 2
 * https://www.acmicpc.net/problem/20437
 * 
 * @author minchae
 * @date 2024. 2. 3.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			char[] arr = br.readLine().toCharArray();
			int K = Integer.parseInt(br.readLine());
			
			// K가 1인 경우 가장 짧고 긴 문자열의 길이는 1
			if (K == 1) {
				System.out.println("1 1");
				continue;
			}
			
			int[] alpha = new int[26]; // 알파벳 개수 저장
			
			// 문자열에 특정 알파벳이 몇 개 있는지 저장
			for (char c : arr) {
				alpha[c - 'a']++;
			}
			
			int min = Integer.MAX_VALUE; // K개를 포함하는 가장 짧은 연속 문자열의 길이
			int max = 0; // K개를 포함하고, 문자열의 첫 번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이
			
			for (int i = 0; i < arr.length; i++) {
				// 해당 알파벳 개수가 K개 미만인 경우 다음으로 넘어감 (K개만큼 있어야 하기 때문)
				if (alpha[arr[i] - 'a'] < K) {
					continue;
				}
				
				int cnt = 1;
				
				for (int j = i + 1; j < arr.length; j++) {
					if (arr[i] == arr[j]) {
						cnt++;
					}
					
					// arr[i]를 K개 포함하는 문자열을 발견한 경우 해당 for문 종료
					if (cnt == K) {
						min = Math.min(min, j - i + 1);
						max = Math.max(max, j - i + 1);
						
						break;
					}
				}
			}
			
			System.out.println(min == Integer.MAX_VALUE ? -1 : (min + " " + max));
		}
	}

}
