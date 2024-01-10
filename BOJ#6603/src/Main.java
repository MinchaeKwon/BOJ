/**
 * 6603 로또
 * https://www.acmicpc.net/problem/6603
 * 
 * @author minchae
 * @date 2024. 1. 10.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int k;
	static int[] S;
	
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			k = Integer.parseInt(st.nextToken());
			
			if (k == 0) {
				break;
			}
			
			S = new int[k];
			
			for (int i = 0; i < k; i++) {
				S[i] = Integer.parseInt(st.nextToken());
			}
			
			arr = new int[6];
			
			simulation(0, 0);
			System.out.println();
		}

	}
	
	private static void simulation(int depth, int start) {
		if (depth == 6) {
			for (int n : arr) {
				System.out.print(n + " ");
			}
			System.out.println();
			
			return;
		}
		
		for (int i = start; i < k; i++) {
			arr[depth] = S[i];
			simulation(depth + 1, i + 1); // 중복을 허용하지 않기 때문에 i + 1을 해줌
		}
	}

}
