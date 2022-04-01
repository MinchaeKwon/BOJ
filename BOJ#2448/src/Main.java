/**
 * 2448 별 찍기 11
 * https://www.acmicpc.net/problem/2448
 * 
 * @author minchae
 * @date 2022. 4. 1.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	static char[][] star;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());

		star = new char[N][2 * N - 1];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(star[i], ' '); // 공백으로 초기화
		}
		
		drawStar(0, N - 1, N);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 2 * N - 1; j++) {
				System.out.print(star[i][j]);
			}
			System.out.println();
		}
	}
	
	private static void drawStar(int x, int y, int n) {
		// 별 찍기 패턴의 가장 작은 단위일 경우
		if (n == 3) {
			star[x][y] = '*'; // 꼭대기 부분
			star[x + 1][y - 1] = star[x + 1][y + 1] = '*'; // 두 번째
			star[x + 2][y - 2] = star[x + 2][y - 1] = star[x + 2][y] = star[x + 2][y + 1] = star[x + 2][y + 2] = '*'; // 세 번째
            return;
		}
		
		// 가장 작은 단위가 아닐 경우 더 작은 단위로 나눔
		drawStar(x, y, n / 2);
        drawStar(x + n / 2, y - n / 2, n / 2);
        drawStar(x + n / 2, y + n / 2, n / 2);
	}

}
