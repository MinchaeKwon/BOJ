/**
 * 11054 가장 긴 바이토닉 부분 수열
 * https://www.acmicpc.net/problem/11054
 * 
 * @author minchae
 * @date 2022. 4. 2.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N];
		int[] dpR = new int[N]; // 왼쪽에서 오른쪽으로 LIS(Longes Increasing Subsequence) 구한 값 저장
		int[] dpL = new int[N]; // 오른쪽에서 왼쪽으로 LIS 구한 값 저장
		
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		for (int i = 0; i < N; i++) {			
			A[i] = Integer.parseInt(st.nextToken());
			
			dpR[i] = 1;
			dpL[i] = 1;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i; j++) {
				if (A[j] < A[i] && dpR[i] < dpR[j] + 1) {
					dpR[i] = dpR[j] + 1;
				}
			}
		}
		
		for (int i = N -1; i >= 0; i--) {
			for (int j = N - 1; j > i; j--) {
				if (A[j] < A[i] && dpL[i] < dpL[j] + 1) {
					dpL[i] = dpL[j] + 1;
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, dpR[i] + dpL[i]);
		}
		
		System.out.println(max - 1); // 중복되는 부분 빼줌
	}

}
