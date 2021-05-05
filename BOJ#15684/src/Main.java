/**
 * 15684 사다리 조작
 * https://www.acmicpc.net/problem/15684
 * 
 * @author minchae
 * @date 2021. 5. 6
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, H;
	static int[][] map;
	static int result;
	static boolean finish = false;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H + 1][N + 1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			map[a][b] = 1;
			map[a][b + 1] = 2;
		}
		
		for (int i = 0; i <= 3; i++) {
			result = i; // 추가할 가로선 개수를 정해놓음
			backtracking(1, 0);
			
			// finish를 사용한다면 추가해야할 코드
//			if (check()) {
//				break;
//			}
		}
		
		System.out.println(-1);
		
//		System.out.println(finish ? result : -1);
		
	}
	
	public static void backtracking(int n, int cnt) { 
//		if (finish) {
//			return;
//		}
		
		// 추가할 가로선 개수와 똑같아졌다면 i번 세로선 결과가 i가 되는지 확인함
		if (result == cnt) { 
			if (check()) {
//				finish = true;
				
				System.out.println(result);
				System.exit(0);
			}
			return; 
		} 
		
		for (int i = n; i <= H; i++) { 
			for (int j = 1; j < N; j++) { 
				if (map[i][j] == 0 && map[i][j + 1] == 0) { 
					map[i][j] = 1; 
					map[i][j + 1] = 2; 
					backtracking(i, cnt + 1); 
					map[i][j] = 0;
					map[i][j + 1] = 0; 
				}
			} 
		}
	}
	
	// i번 세로선의 결과가 i번인지 확인
	public static boolean check() {
		for (int i = 1; i <= N; i++) {
			int ny = i;
			
			for (int j = 1; j <= H; j++) {
				if (map[j][ny] == 1) {
					ny++;
				}
				else if (map[j][ny] == 2) {
					ny--;
				}
			}
			
			if (ny != i) {
				return false;
			}
		}
		
		return true;
	}

}
