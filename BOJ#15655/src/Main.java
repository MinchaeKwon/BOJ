/**
 * 15655 N과 M (6)
 * https://www.acmicpc.net/problem/15655
 * 
 * @author minchae
 * @date 2022. 3. 28.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] arr;
	
	static int[] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		result = new int[M];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		dfs(0, 0);
	}
	
	private static void dfs(int start, int depth) {
		if (depth == M) {
			for (int num : result) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		
		// for문을 돌 때 초기 값을 지정해주기 위해 start(어디서 시작되는지 알려주는 변수)를 사용
		for (int i = start; i < N; i++) {
			result[depth] = arr[i];
			dfs(i + 1, depth + 1);
		}
	}

}
