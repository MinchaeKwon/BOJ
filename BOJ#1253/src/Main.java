/**
 * 1253 좋다
 * https://www.acmicpc.net/problem/1253
 * 
 * @author minchae
 * @date 2024. 3. 20.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int answer = 0;
		
		for (int i = 0; i < N; i++) {
			int left = 0;
			int right = N - 1;
			
			while (left < right) {
				int sum = arr[left] + arr[right];
				
				if (sum == arr[i]) {
					if (i == left) {
						left++;
					} else if (i == right) {
						right--;
					} else {
						answer++;
						break;
					}
				} else if (sum > arr[i]) {
					right--;
				} else if (sum < arr[i]) {
					left++;
				}
			}
		}
		
		System.out.println(answer);
	}

}
