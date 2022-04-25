/**
 * 1654 랜선 자르기
 * https://www.acmicpc.net/problem/1654
 * 
 * @author minchae
 * @date 2022. 4. 25.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] lan = new int[K];
		
		long end = 0;
		
		for (int i = 0; i < K; i++) {
			lan[i] = Integer.parseInt(br.readLine());
			end = Math.max(end, lan[i]); // 랜선 길이의 최대값을 구함
		}
		
		long start = 1; // N, K가 1일 경우에 mid로 나누면 0이 나올 수 있기 때문에 최소값을 1로 설정
		
		while (start <= end) {
			long mid = (start + end) / 2; // mid는 랜선을 자르는 높이
			long cnt = 0;
			
			for (int n : lan) {
				cnt += n / mid; // 랜선을 잘라서 몇 개가 나오는지 확인
			}
			
			if (N <= cnt) {
				// N보다 크거나 같을 경우에 더 크게 자르기 위해 시작점을 증가 시킴
				start = mid + 1;
			} else {
				// N보다 작을 경우에 더 작게 자르기 위해 종료 지점을 감소 시킴
				end = mid - 1;
			}
		}
		
		System.out.println(end);
	}

}
