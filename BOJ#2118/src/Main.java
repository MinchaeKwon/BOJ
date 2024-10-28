/**
 * 2118 두개의 탑
 * https://www.acmicpc.net/problem/2118
 * 
 * @author minchae
 * @date 2024. 10. 28.
 * 
 * 투 포인터 사용 - 183 ms
 * */

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int[] dist = new int[N];
		int sum = 0;
		
		for (int i = 0; i < N; i++) {
			dist[i] = Integer.parseInt(br.readLine());
			sum += dist[i];
		}
		
		int start = 0;
		int end = 1;
		
		int right = dist[start]; // 시계 방향 거리
		int left = sum - right; // 반시계 방향 거리
		
		int answer = 0;
		
		while (start < N) {
			answer = Math.max(answer, Math.min(right, left)); // 최솟값 중에 최댓값 찾기
			
			// 시계 방향 거리가 더 짧은 경우 end 증가
			if (right < left) {
				right += dist[end];
				left -= dist[end];
				
				end++;
				end %= N;
			} else {
				right -= dist[start];
				left += dist[start];
				
				start++;
			}
		}
		
		System.out.println(answer);
		
		// 누적합으로 이용한 풀이 -> 1300 ms 정도 나옴
//		int answer = 0;
//		
//		for (int i = 0; i < N; i++) {
//			for (int j = i + 1; j < N; j++) {
//				int right = dist[j] - dist[i]; // 시계 방향 거리
//				int left = dist[N - 1] - right; // 반시계 방향 거리
//				
//				int min = Math.min(right, left); // 둘 중에서 작은 값 구하기
//				
//				answer = Math.max(answer, min); // 작은 값들 중에서 최댓값 갱신
//			}
//		}
//		
//		System.out.println(answer);
	}

}