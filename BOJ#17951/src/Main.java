/**
 * 17951 흩날리는 시험지 속에서 내 평점이 느껴진거야
 * https://www.acmicpc.net/problem/17951
 * 
 * @author minchae
 * @date 2024. 8. 28.
 * */

import java.util.*;
import java.io.*;

public class Main {
	
	static int N, K;
	static int[] score;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		score = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		int end = 0;
		
		for (int i = 0; i < N; i++) {
			score[i] = Integer.parseInt(st.nextToken());
			end += score[i];
		}
		
		int start = 0;
		
		System.out.println(getMax(start, end));
	}

	private static int getMax(int start, int end) {
		while (start <= end) {
			int mid = (start + end) / 2;
			
			int cnt = 0;
			int sum = 0;
			
			// sum에 현재 시험지에서 맞은 문제 개수를 더함
			for (int num : score) {
				sum += num;
				
				// mid보다 sum이 크거나 같은 경우, 그룹을 만드는 조건에 부합
				// sum을 0으로 초기화, 그룹 수 증가
				if (sum >= mid) {
					sum = 0;
					cnt++;
				}
			}
			
			if (K <= cnt) {
				start = mid + 1; // 그룹별로 맞은 문제 개수 중 최소값이 현재보다 커야함
			} else {
				end = mid - 1; // // 그룹별로 맞은 문제 개수 중 최소값이 현재보다 작아야함
			}
		}
		
		return end;
	}
}