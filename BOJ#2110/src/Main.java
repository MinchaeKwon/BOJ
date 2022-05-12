/**
 * 2110 공유기 설치
 * https://www.acmicpc.net/problem/2110
 * 
 * @author minchae
 * @date 2022. 5. 12.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] house = new int[N];
		
		for (int i = 0; i < N; i++) {
			house[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(house);
		
		int start = 1;
		int end = house[N - 1] - house[0];
		
		while (start <= end) {
			int mid = (start + end) / 2;
			
			// 첫 번째 집은 무조건 설치한다고 가정
			int cnt = 1;
			int locate = house[0];
			
			for(int i = 1; i < house.length; i++) {
				int distance = house[i] - locate; // 현재 설치하려는 집과 이전에 설치했던 집 간의 거리
				
				// 현재 설치하려는 집과 이전에 설치했던 집 간의 거리가 mid(최소 거리)보다 크거나 같은 경우 공유기 설치 후 마지막 설치 위치 갱신
				if (distance >= mid) {
					cnt++;
					locate = house[i];
				}
			}
			
			if (cnt >= C) {
				// 설치 가능한 공유기 개수가 C(설치해야 하는 공유기 개수)보다 많거나 같으면 최소 거리를 늘림
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		
		System.out.println(start - 1);
	}

}
