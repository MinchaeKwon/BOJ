/**
 * 15650 N과 M (2)
 * https://www.acmicpc.net/problem/15650
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];

		dfs(0, 0);
	}
	
	private static void dfs(int start, int depth) {
		// depth와 M이 같아지는 경우 arr에 담았던 수를 출력
		if (depth == M) {
			for (int num : arr) {
				System.out.print(num + " ");
			}
			System.out.println();
			
			return;
		}
		
		// for문을 돌 때 초기 값을 지정해주기 위해 start(어디서 시작되는지 알려주는 변수)를 사용 
		// -> 다음 재귀는 (start + 1)부터 시작하기 때문에 오름차순으로 탐색 가능함 (이전 값보다 큰 수부터 탐색 가능, visited 배열도 필요 없음)
		for (int i = start; i < N; i++) {
			arr[depth] = i + 1;
			dfs(i + 1, depth + 1);
		}
	}

}
