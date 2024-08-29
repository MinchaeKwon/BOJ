/**
 * 3151 합이 0
 * https://www.acmicpc.net/problem/3151
 * 
 * @author minchae
 * @date 2024. 8. 30.
 * */

import java.util.*;
import java.io.*;

public class Main {
	
	static int N;
	static int[] A;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		
		long answer = 0;

		for (int i = 0; i < N - 2; i++) {
			for (int j = i + 1; j < N - 1; j++) {
				int sum = A[i] + A[j]; // 일단 두 수의 합을 구함
				
				int lower = lowerBound(j + 1, N, -sum);
				int upper = upperBound(j + 1, N, -sum);
				
				// 중복값이 있기 때문에 upper에서 lower를 빼주면 개수를 구할 수 있음
				answer += upper - lower;
			}
		}
		
		System.out.println(answer);
	}
	
	// key가 나타나는 첫 인덱스
	private static int lowerBound(int left, int right, int key) {
		while (left < right) {
			int mid = (left + right) / 2;
			
			if (A[mid] >= key) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return right;
	}
	
	// key보다 큰 숫자가 나타나는 첫 인덱스
	private static int upperBound(int left, int right, int key) {
		while (left < right) {
			int mid = (left + right) / 2;
			
			if (A[mid] > key) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return right;
	}

}
