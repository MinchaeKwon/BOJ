/**
 * 2342 Dance Dance Revolution
 * https://www.acmicpc.net/problem/2342
 * 
 * @author minchae
 * @date 2024. 9. 20.
 * */

import java.util.*;
import java.io.*;

// dp 3차원 배열 사용 - 왼발, 오른발, 현재 진행 횟수
public class Main {
	
	static ArrayList<Integer> move;
	static int[][][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		move = new ArrayList<>();
		
		while (true) {
			int n = Integer.parseInt(st.nextToken());
			
			if (n == 0) {
				break;
			}
			
			move.add(n);
		}
		
		dp = new int[5][5][move.size() + 1];
		
		System.out.println(solve(0, 0, 0));
	}
	
	private static int solve(int cnt, int left, int right) {
		if (cnt == move.size()) {
			return 0;
		}
		
		if (dp[left][right][cnt] > 0) {
			return dp[left][right][cnt];
		}
		
		// 왼발 움직였을 때랑 오른발 움직였을 때 둘 중에서 최솟값 찾기
		dp[left][right][cnt] = Math.min(
				solve(cnt + 1, move.get(cnt), right) + getCost(left, move.get(cnt)), 
				solve(cnt + 1, left, move.get(cnt)) + getCost(right, move.get(cnt)));
		
		return dp[left][right][cnt];
	}
	
	// 이동했을 때의 비용 구하기
	private static int getCost(int prev, int cur) {
		if (prev == 0) {
			return 2;
		} else if (prev == cur) {
			return 1;
		} else if ((prev + cur) % 2 == 0) {
			return 4; // 반대 방향
		} else {
			return 3; // 인접한 방향
		}
	}

}
