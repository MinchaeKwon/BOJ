/**
 * 3649 로봇 프로젝트
 * https://www.acmicpc.net/problem/3649
 * 
 * @author minchae
 * @date 2024. 3. 24.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			String s = br.readLine();
			
			if (s == null) {
				break;
			}
			
			int x = Integer.parseInt(s) * 10000000;
			
			int n = Integer.parseInt(br.readLine());
			
			int[] arr = new int[n];
			
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(br.readLine());
			}
			
			Arrays.sort(arr);
			
			int left = 0;
			int right = n - 1;
			
			boolean flag = false;
			
			while (left < right) {
				int sum = arr[left] + arr[right];
				
				if (sum == x) {
					flag = true;
					break;
				} else if (sum < x) {
					left++;
				} else {
					right--;
				}
			}
			
			System.out.println(flag ? ("yes " + arr[left] + " " + arr[right]) : "danger");
		}

	}

}
