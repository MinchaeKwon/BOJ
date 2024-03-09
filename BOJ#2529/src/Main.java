/**
 * 2529 부등호
 * https://www.acmicpc.net/problem/2529
 * 
 * @author minchae
 * @date 2024. 3. 9.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int K;
	static char[] arr;
	
	static boolean[] visited = new boolean[10];
	
	static ArrayList<String> answer = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		K = Integer.parseInt(br.readLine());
		
		arr = new char[K];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < K; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		backtracking(0, "");
		
		System.out.println(answer.get(answer.size() - 1));
		System.out.println(answer.get(0));
	}
	
	private static void backtracking(int depth, String num) {
		if (depth == K + 1) {
			answer.add(num);
			return;
		}

		for (int i = 0; i < 10; i++) {
			if (visited[i]) {
				continue;

			}
			
			if (depth == 0 || ckeck(Character.getNumericValue(num.charAt(depth - 1)), i, arr[depth - 1])) {
				visited[i] = true;
				backtracking(depth + 1, num + i);
				visited[i] = false;
			}
		}
	}
	
	private static boolean ckeck(int a, int b, char c) {
		if (c == '>') {
			if (a < b) {
				return false;
			}
		} else {
			if (a > b) {
				return false;	
			}
		}
		
		return true;
	}

}
