/**
 * 2473 세 용액
 * https://www.acmicpc.net/problem/2473
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
		
		long diff = Long.MAX_VALUE;
		long[] answer = new long[3];
		
		for (int i = 0; i < N; i++) {
			int left = i + 1;
			int right = N - 1;
			
			while (left < right) {
				long sum = arr[i] + arr[left] + arr[right];
				
				long cur = Math.abs(sum);
				
				if (cur < diff) {
					diff = cur;
					
					answer[0] = arr[i];
					answer[1] = arr[left];
					answer[2] = arr[right];
				}
				
				if (sum > 0) {
					right--;
				} else {
					left++;
				}
			}
		}

		for (long n : answer) {
			System.out.print(n + " ");
		}
	}

}
