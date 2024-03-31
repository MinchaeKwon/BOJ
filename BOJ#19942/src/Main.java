/**
 * 19942 다이어트
 * https://www.acmicpc.net/problem/19942
 * 
 * @author minchae
 * @date 2024. 3. 31.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	
	static int[][] nutrients;
	static int[] minNutrients;
	static int[] selected;
	
	static int answer = Integer.MAX_VALUE;
	static ArrayList<String> nums = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		nutrients = new int[N][5];
		minNutrients = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 4; i++) {
			minNutrients[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 5; j++) {
				nutrients[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= N; i++) {
			selected = new int[N];
			simulation(0, i, 0);
		}

		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			Collections.sort(nums); // 사전 순으로 빠른 식재료 얻음
			
			System.out.println(answer);
			System.out.println(nums.get(0));
		}
	}
	
	private static void simulation(int depth, int cnt, int start) {
		if (depth == cnt) {
			checkPrice(cnt);
			return;
		}
		
		for (int i = start; i < N; i++) {
			selected[depth] = i;
			simulation(depth + 1, cnt, i + 1);
		}
	}
	
	private static void checkPrice(int cnt) {
		int[] sum = new int[4];
		int price = 0;
		
		for (int i = 0; i < cnt; i++) {
			for (int j = 0; j < 4; j++) {
				sum[j] += nutrients[selected[i]][j];
			}
			
			price += nutrients[selected[i]][4];
		}
		
		for (int i = 0; i < 4; i++) {
			if (minNutrients[i] > sum[i]) {
				return;
			}
		}
		
		// 똑같은 비용인데 서로 조합이 다른 식재료일 수 있기 때문에 =을 붙임
		if (answer >= price) {
			// 비용이 더 작으면 리스트 초기화
			if (answer > price) {
				nums.clear();
			}
			
			answer = price;
			
			StringBuilder sb = new StringBuilder();
			
			for (int i = 0; i < cnt; i++) {
				sb.append((selected[i] + 1) + (i != cnt - 1 ? " " : ""));
			}
			
			nums.add(sb.toString());
		}
	}

}
