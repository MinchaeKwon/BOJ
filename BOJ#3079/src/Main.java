/**
 * 3079 입국심사
 * https://www.acmicpc.net/problem/3079
 * 
 * @author minchae
 * @date 2022. 3. 8.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		ArrayList<Long> times = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			times.add(Long.parseLong(br.readLine()));
		}
		
		System.out.println(getTotalTime(M, times));
	}
	
	public static long getTotalTime(int M, ArrayList<Long> times) {
		Collections.sort(times);
		
		long low = times.get(0); // 첫 번째 인덱스를 최소값으로 잡음
		long high = times.get(times.size() - 1) * M; // 맨 뒤에 있는 값에 인원 수를 곱한 값이 가장 많이 걸리는 시간임
		
		// while (low <= high)
		while (low < high) {
			long mid = (low + high) / 2;
			
			long people = 0;
			
			// mid 시간동안 심사한 인원 구하기
			for (long time : times) {
				people += mid / time;
			}
			
			if (people < M) {
				// M명보다 적을 경우 더 많은 시간이 소요된다는 의미이므로 mid + 1을 해줌
				low = mid + 1;
			} else {
				// mid 시간동안 심사한 인원이 M명 이상일 경우 high = mid을 통해 탐색 범위를 줄여줌
				high = mid; // high = mid - 1;
			}
		}
		
		return low;
	}

}
