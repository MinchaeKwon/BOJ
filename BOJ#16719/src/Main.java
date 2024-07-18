/**
 * 16719 ZOAC
 * https://www.acmicpc.net/problem/16719
 * 
 * @author minchae
 * @date 2024. 7. 17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static String input;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		input = br.readLine();
		
		visited = new boolean[input.length()];
		
		solve(0, input.length() - 1);
	}
	
	private static void solve(int left, int right) {
		// 왼쪽 인덱스가 더 커질 경우 종료
		if (left > right) {
			return;
		}
		
		int idx = left; // 시작은 왼쪽 인덱스
		
		for (int i = left; i <= right; i++) {
			// 사전순으로 앞에 오는 문자를 발견하는 경우
			if (input.charAt(i) < input.charAt(idx)) {
				idx = i; // 인덱스 위치 갱신
			}
		}
		
		// 사전순으로 앞서는 문자 방문 처리
		visited[idx] = true;
		
		// 사전순으로 문자 출력
		for (int i = 0; i < visited.length; i++) {
			if (visited[i]) {
				System.out.print(input.charAt(i));
			}
		}
		System.out.println();
		
		// 범위 좁혀가면서 확인
		solve(idx + 1, right);
		solve(left, idx - 1);
	}

}
