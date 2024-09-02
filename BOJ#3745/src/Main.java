/**
 * 3745 오름세
 * https://www.acmicpc.net/problem/3745
 * 
 * @author minchae
 * @date 2024. 9. 2
 * 
 * 참고 : https://loosie.tistory.com/376?category=972195
 * */

import java.util.*;
import java.io.*;

// 최장 증가 부분 수열 -> 오름차순으로 정렬된 가장 긴 수열 구하기
public class Main {
	
	static int N;
	static int[] arr;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		
		while ((input = br.readLine()) != null) {
			N = Integer.parseInt(input.trim());
			
			arr = new int[N];
			dp = new int[N + 1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int len = 0;
			
			for (int i = 0; i < N; i++) {
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
}
