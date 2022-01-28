/**
 * 13458 시험 감독
 * https://www.acmicpc.net/problem/13458
 * 
 * @author minchae
 * @Date 2022. 1. 28.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] A = new int[N];
		
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		long result = N; // 총감독은 무조건 한 명 있어야 하므로 N으로 초기화
		
		for (int i = 0; i < N; i++) {
			A[i] -= B; // 총감독이 감시할 수 있는 응시자 수를 뺌
			
			// 응시생이 남아있는 경우
			if (A[i] > 0) {
				result += A[i] / C; // 부감독이 감시할 수 있는 응시자 수를 나눈 몫을 더해줌
				
				// 나머지가 0보다 클 경우 부감독 한 명을 추가함
				if (A[i] % C > 0) {
					result++;
				}
			}
		}
		
		System.out.println(result);
	}

}
