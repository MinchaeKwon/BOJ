/**
 * 1443 망가진 계산기
 * https://www.acmicpc.net/problem/1443
 * 
 * @author minchae
 * @date 2024. 7. 29.
 */

import java.io.*;
import java.util.*;

public class Main {
	
	static int D, P;
	static long answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		D = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		dfs(0, 9, 1); // 곱할 수 있는 제일 큰 숫자인 9부터 시작
		
		System.out.println(answer == 0 ? -1 : answer);
	}
	
	private static void dfs(int cnt, int start, int num) {
		// 계산된 숫자의 자릿수가 D를 초과할 경우 바로 종료
		if (String.valueOf(num).length() > D) {
			return;
		}
		
		// 연산 횟수를 다 사용한 경우 최댓값 갱신
		if (cnt == P) {
			answer = Math.max(answer, num);
			return;
		}
		
		// 제일 큰 숫자를 얻는 것이기 때문에 큰 숫자부터 반복문 시작
		for (int i = start; i >= 2; i--) {
			dfs(cnt + 1, i, num * i);
		}
	}
	
}	
