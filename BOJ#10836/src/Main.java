/**
 * 10836 여왕벌
 * https://www.acmicpc.net/problem/10836
 * 
 * @author Minchae Gwon
 * @date 2021.3.3
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
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[M][M];
		
		for (int i = 0; i < M; i++) {
			Arrays.fill(map[i], 1);
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			// 제일 왼쪽 열의 애벌레 키우기 (제일 왼쪽 아래 칸부터 시작)
			for (int j = M - 1; j >= 0; j--) {
				if (zero > 0) {
					zero--;
				}
				else if (one > 0) {
					map[j][0] += 1;
					one--;
				}
				else if (two > 0) {
					map[j][0] += 2;
					two--;
				}
			}
			
			// 제일 위쪽 행의 애벌레 키우기 
			// -> 왼쪽에서 올라오면서 제일 위쪽에 도착하면 오른쪽으로 이동하기 때문에(0, 0)은 이미 자란 상태이므로 j는 1부터 시작함
			for (int j = 1; j < M; j++) {
				if (zero > 0) {
					zero--;
				}
				else if (one > 0) {
					map[0][j] += 1;
					one--;
				}
				else if (two > 0) {
					map[0][j] += 2;
					two--;
				}
			}
			
		}
		
		// 나머지 애벌레 키우기
		for (int i = 1; i < M; i++) {
			for (int j = 1; j < M; j++) {
				//왼쪽, 왼쪽 위, 위쪽 중에 큰 값을 넣음
				map[i][j] = Math.max(map[i - 1][j], Math.max(map[i - 1][j - 1], map[i][j - 1]));
			}
		}
		
		//출력
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j] + " ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());

	}

}
