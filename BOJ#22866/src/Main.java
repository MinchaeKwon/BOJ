/**
 * 22866 탑 보기
 * https://www.acmicpc.net/problem/22866
 * 
 * @author minchae
 * @date 2024. 10. 5.
 * 
 * 좌 -> 우, 우 -> 좌 두 번 확인하면서 볼 수 있는 건물을 확인
 * */

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] h = new int[N + 1];
		int[] cnt = new int[N + 1]; // 보이는 건물 개수
		int[] near = new int[N + 1]; // 가까운 건물 번호
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			h[i] = Integer.parseInt(st.nextToken());
			near[i] = -100001;
		}
		
		// 좌 -> 우, 자신의 왼쪽으로 보이는 건물 개수 계산
		Stack<Integer> stack = new Stack<>(); // 건물 번호 저장
		
		for (int i = 1; i <= N; i++) {
			// 현재 건물 높이보다 작은 건물은 pop
			while (!stack.isEmpty() && h[stack.peek()] <= h[i]) {
				stack.pop();
			}
			
			cnt[i] = stack.size(); // 남은 크기가 볼 수 있는 건물의 개수
			
			if (cnt[i] > 0) {
				near[i] = stack.peek();
			}
			
			stack.push(i);
		}
		
		// 우 -> 좌, 자신의 오른쪽에 보이는 건물 계산
		stack = new Stack<>();
		
		for (int i = N; i >= 1; i--) {
			// 현재 건물 높이보다 작은 건물은 pop
			while (!stack.isEmpty() && h[stack.peek()] <= h[i]) {
				stack.pop();
			}
			
			cnt[i] += stack.size();
			
			// 오른쪽에 있는 건물이 더 가까운 경우 가까운 건물 번호 갱신
			if (stack.size() > 0 && stack.peek() - i < i - near[i]) {
				near[i] = stack.peek();
			}
			
			stack.push(i);
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.print(cnt[i] + " ");
			System.out.println(cnt[i] > 0 ? near[i] : "");
		}
	}

}
