/**
 * 14719 빗물
 * https://www.acmicpc.net/problem/14719
 * 
 * @author minchae
 * @date 2024. 1. 31.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int H, W;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] height = new int[W];
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < W; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		
		// 처음, 마지막 위치에는 빗물이 고일 수 없기 때문에 1부터 W - 1까지 확인
		for (int i = 1; i < W - 1; i++) {
			int left = 0;
			
			for (int j = 0; j < i; j++) {
				left = Math.max(left, height[j]); // 왼쪽에서 최고 높이 구함
			}
			
			int right = 0;
			
			for (int j = i; j < W; j++) {
				right = Math.max(right, height[j]); // 오른쪽에서 최고 높이 구함
			}
			
			// 빗물이 고이기 위해서는 현재 인덱스의 블록 높이보다 왼쪽, 오른쪽 최고 높이가 낮아야함
			if (height[i] < left && height[i] < right) {
				// 낮은 경우 둘 중에서 더 작은 값에서 현재 인덱스 블록 높이를 빼준 값을 더함
				// 둘 중에서 최소 높이만큼만 빗물이 참
				answer += Math.min(left, right) - height[i];
			}
		}
		
		System.out.println(answer);
	}

}
