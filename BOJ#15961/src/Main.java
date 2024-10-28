/**
 * 15961 회전 초밥
 * https://www.acmicpc.net/problem/15961
 * 
 * @author minchae
 * @date 2024. 10. 28.
 * */

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, d, k, c;
	static int[] food;
	static int[] check;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		food = new int[N];
		check = new int[d + 1];
		
		for (int i = 0; i < N; i++) {
			food[i] = Integer.parseInt(br.readLine());
		}
		
		int result = 1; // 쿠폰이 있기 때문에 최솟값은 1
		check[c]++;
		
		// 일단 k개까지 확인
		for (int i = 0; i < k; i++) {
			// 새로운 초밥일 경우
			if (check[food[i]] == 0) {
				result++;
			}
			
			check[food[i]]++;
		}
		
		int cnt = result; // 초밥 가짓수
		
		// 슬라이딩 윈도우
		for (int i = 1; i < N; i++) {
			int prev = food[i - 1]; // 이전 초밥 종류
			
			check[prev]--; // 해당 초밥 개수 감소
			
			// 0이 되면 초밥 가짓수 감소
			if (check[prev] == 0) {
				cnt--;
			}
			
			int next = food[(i + k - 1) % N]; // 다음에 추가할 초밥 종류
			
			// 새로운 초밥 종류인 경우
			if (check[next] == 0) {
				cnt++;
			}
			
			check[next]++;
			
			result = Math.max(result, cnt); // 최댓값 갱신
		}

		System.out.println(result);
	}

}
