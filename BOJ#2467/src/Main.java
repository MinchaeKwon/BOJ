/**
 * 2467 용액
 * https://www.acmicpc.net/problem/2467
 * 
 * @author minchae
 * @date 2024. 4. 9.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static long[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new long[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int left = 0;
		int right = arr.length - 1;
		
		long diff = Long.MAX_VALUE;
		
		long[] answer = new long[2];
		
		while (left < right) {
			long sum = arr[left] + arr[right];
			long cur = Math.abs(sum);
			
			if (cur < diff) {
				diff = cur;
				
				answer[0] = arr[left];
				answer[1] = arr[right];
			}
			
			if (sum > 0) {
				right--;
			} else {
				left++;
			}
		}

		for (long n : answer) {
			System.out.print(n + " ");
		}
	}

}
