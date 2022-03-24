/**
 * 1932 정수 삼각형
 * https://www.acmicpc.net/problem/1932
 * 
 * @author minchae
 * @date 2022. 3. 24.
 * 
 * 아래에서 위로 올라가면서 최대값을 구함
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		int[][] triangle = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < i + 1; j++) {
				triangle[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = n -1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				// 위층의 값 += 현재 위치의 값과 오른쪽에 있는 값 중에서 더 큰 값
				triangle[i - 1][j] += Math.max(triangle[i][j], triangle[i][j + 1]);
			}
		}

		System.out.println(triangle[0][0]);
	}

}
