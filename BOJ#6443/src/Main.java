/**
 * 6443 애너그램
 * https://www.acmicpc.net/problem/6443
 * 
 * @author minchae
 * @date 2024. 2. 4.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;

public class Main {
	
	static char[] arr;
	static char[] result;
	static boolean[] visited;
	
	static LinkedHashSet<String> output = new LinkedHashSet<>();
	
	static int[] alphaCnt; 
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		while (N-- > 0) {
			arr = br.readLine().toCharArray();
			
			alphaCnt = new int[26]; // input 문자열에서 각 알파벳 개수를 구함
			
			for (char c : arr) {
				alphaCnt[c - 'a']++;
			}
			
			sb = new StringBuilder();
			
			select(0);
			
			// 메모리 초과 발생하는 코드
//			result = new char[arr.length];
//			visited = new boolean[arr.length];
//			
//			Arrays.sort(arr);
//			
//			select(0);
//			
//			output.forEach(System.out::println);
		}
	}
	
	// 순열
	private static void select(int depth) {
		if (depth == arr.length) {
			System.out.println(sb.toString());
			return;
		}
		
		for (int i = 0; i < 26; i++) {
			// 해당 알파벳이 있는 경우
			if (alphaCnt[i] > 0) {
				// 개수 감소시키고 문자 추가
				alphaCnt[i]--;
				sb.append((char) (i + 'a'));
				
				select(depth + 1);
				
				// 원복
				alphaCnt[i]++;
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		
		// 메모리 초과 발생
//		if (depth == arr.length) {
//			StringBuilder sb = new StringBuilder();
//			
//			for (char c : result) {
//				sb.append(c);
//			}
//			
//			output.add(sb.toString());
//			
//			return;
//		}
//		
//		for (int i = 0; i < arr.length; i++) {
//			if (!visited[i]) {
//				result[depth] = arr[i];
//				visited[i] = true;
//				
//				select(depth + 1);
//				
//				visited[i] = false;
//			}
//		}
	}

}
