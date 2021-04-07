/**
 * 2805 나무 자르기
 * https://www.acmicpc.net/problem/2805
 * 
 * @author minchae
 * @date 2021. 4. 7.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] tree = new int[N];
		
		long start = 0;
		long end = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
			end = Math.max(end, tree[i]);
		}
		
		while (start <= end) {
			long mid = (start + end) / 2; // mid는 나무를 자르는 높이
			long heightSum = 0;
			
			for (int t : tree) {
				// 나무의 높이가 나무를 자른 높이보다 클 경우 남은 부분을 더해줌
				if (t > mid) {
					heightSum += t - mid;
				}
			}
			
			// M보다 클 경우에 M에 가까워지기 위해서는 시작점을 증가시켜야함
			if (M <= heightSum) {
				start = mid + 1;
			}
			else { // M보다 작을 경우에 M에 가까워지기 위해서는 종료지점을 감소시켜야함
				end = mid - 1;
			}
			
		}
		
		System.out.println(end);
		
	}

}
