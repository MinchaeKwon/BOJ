/**
 * 10597 순열장난
 * https://www.acmicpc.net/problem/10597
 * 
 * @author minchae
 * @date 2024. 7. 25.
 */

import java.io.*;

public class Main {

	static String input;
	static int len;
	static boolean[] visited = new boolean[51]; // N의 최대 범위가 1~50
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
		input = br.readLine();
		len = input.length();
		
		dfs(0, 0, "");
	}
	
	// 현재 인덱스, 순열의 최대 숫자, 현재 결과
	private static void dfs(int depth, int max, String result) {
		if (depth == len) {
			// 만들어진 순열의 범위까지 확인하면서 아직 방문처리가 되지 않은 경우 종료
			for (int i = 1; i <= max; i++) {
				if (!visited[i]) {
					return;
				}
			}
			
			// 조건을 만족하는 순열을 발견한 경우 해당 순열 출력 후 시스템 종료
			System.out.println(result);
			System.exit(0);
		}
		
		// 순열은 최소 1개, 최대 50개의 수로 이루어져 있기 때문이 한 자리 또는 두 자리로 나누면 됨
		
		// 한 자리로 나누는 경우
		int n = Integer.parseInt(input.substring(depth, depth + 1));
		
		if (!visited[n]) {
			visited[n] = true;
			dfs(depth + 1, Math.max(max, n), result + " " + n);
			visited[n] = false;
		}
		
		// 두 자리로 나누는 경우 (2개를 봐야하기 때문에 len -1보다 작은지 확인해야 함)
		if (depth < len - 1) {
			n = Integer.parseInt(input.substring(depth, depth + 2));
			
			// 숫자가 50보다 작고 아직 방문하지 않은 경우
			if (n <= 50 && !visited[n]) {
				visited[n] = true;
				dfs(depth + 2, Math.max(max, n), result + " " + n);
				visited[n] = false;
			}
		}
	}

}
