/**
 * 2512 예산
 * https://www.acmicpc.net/problem/2512
 * 
 * @author minchae
 * @date 2022. 4. 27.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		int[] budget = new int[N];
		int end = 0; // 예산 최댓값
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			budget[i] = Integer.parseInt(st.nextToken());
			end = Math.max(end, budget[i]);
		}
		
		int M = Integer.parseInt(br.readLine());
		
		int start = 0;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			int sum = 0;
			
			for (int n : budget) {
				if (n >= mid) {
					// 배정된 예산이 현재 지방의 요청 예산보다 작으면 배정된 예산(mid)을 더함
					sum += mid;
				} else {
					// 배정된 예산이 현재 지방의 요청 예산보다 많으면 요청 예산(n)을 더함
					sum += n;
				}
			}
			
			if (sum <= M) {
				// 배정된 금액의 합이 총 예산(M)보다 작거나 같을 경우 시작점을 증가 시킴 (예산 배정액을 늘림)
				start = mid + 1;
			} else {
				// 배정된 금액의 합이 총 예산(M)보다 큰 경우 종료 지점을 감소 시킴
				end = mid - 1;
			}
		}
		
		System.out.println(end);
	}

}
