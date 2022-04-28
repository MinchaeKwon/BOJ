/**
 * 11047 동전 0
 * https://www.acmicpc.net/problem/11047
 * 
 * @author minchae
 * @date 2022. 4. 28.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] coin = new int[N];
		
		for (int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		
		int result = 0;
		
		// 최솟값을 구하는 것이기 때문에 동전 가치가 큰 것부터 시작
		for (int i = N - 1; i >= 0; i--) {
			if (coin[i] <= K) {
				result += K / coin[i]; // 현재 가치의 동전이 몇 개의 동전이 쓰이는지 확인
				K = K % coin[i]; // 나머지 값을 K에 저장
			}
		}
		
		System.out.println(result);
	}

}
