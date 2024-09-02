/**
 * 2352 반도체 설계
 * https://www.acmicpc.net/problem/2352
 * 
 * @author minchae
 * @date 2024. 9. 2
 * */

import java.util.*;
import java.io.*;

public class Main {
	
	static int n;
	static int[] arr;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
	
		arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[n + 1];
		
		int len = 0;
		
		for (int i = 0; i < n; i++) {
			if (arr[i] > dp[len]) {
				dp[++len] = arr[i];
			} else {
				int start = 0;
				int end = len;
				int mid = 0;
				
				while (start < end) {
					mid = (start + end) / 2;
					
					if (dp[mid] < arr[i]) {
						start = mid + 1;
					} else {
						end = mid;
					}
				}
				
				dp[end] = arr[i];
			}
		}
		
		System.out.println(len);
	}

}
