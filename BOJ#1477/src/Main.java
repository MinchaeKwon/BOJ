/**
 * 1477 휴게소 세우기
 * https://www.acmicpc.net/problem/1477
 * 
 * @author minchae
 * @date 2024. 3. 22.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, L;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		arr = new int[N + 2]; // 고속도로 시작과 끝을 고려
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		arr[0] = 0;
		arr[N + 1] = L;
		
		Arrays.sort(arr);
		
		int left = 1;
		int right = L - 1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			int cnt = 0;
			
			for (int i = 1; i < arr.length; i++) {
				cnt += (arr[i] - arr[i - 1] - 1) / mid;
			}
			
			if (cnt > M) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		System.out.println(left);
	}

}
