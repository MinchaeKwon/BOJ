/**
 * N과 M (4)
 * https://www.acmicpc.net/problem/15652
 * 
 * @author minchae
 * @date 2022. 3. 27.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] arr;
	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];

		dfs(0, 0);
		System.out.println(sb);
	}
	
	private static void dfs(int start, int depth) {
		// depth와 M이 같아지는 경우 arr에 담았던 수를 출력
		if (depth == M) {
			for (int num : arr) {
				sb.append(num + " ");
			}
			sb.append("\n");
			
			return;
		}
		
		// for문을 돌 때 초기 값을 지정해주기 위해 start(어디서 시작되는지 알려주는 변수)를 사용 
		// -> 같은 수를 여러 번 사용(중복 허용)하기 때문에 (i + 1)이 아닌 i를 넘겨줌
		for (int i = start; i < N; i++) {
			arr[depth] = i + 1;
			dfs(i, depth + 1);
		}
	}

}