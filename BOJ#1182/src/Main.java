/**
 * 1182 부분수열의 합
 * https://www.acmicpc.net/problem/1182
 * 
 * @author minchae
 * @date 2023. 12. 28.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, S;
	static int[] arr;
	
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	
		dfs(0, 0);
		
		System.out.println(S == 0 ? answer - 1 : answer); // S가 0일 경우 공집합이 포함되기 때문에 -1을 해줌
	}
	
	private static void dfs(int depth, int sum) {
		if (depth == N) {
			if (sum == S) {
				answer++;
			}
			
			return;
		}
		
		dfs(depth + 1, sum + arr[depth]); // 해당 숫자를 더했을 경우
		dfs(depth + 1, sum); // 더하지 않았을 경우
	}

}
