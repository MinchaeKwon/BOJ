/**
 * 17136 색종이 붙이기
 * https://www.acmicpc.net/problem/17136
 * 
 * @author minchae
 * @date 2023. 7. 5.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] paper = {5, 5, 5, 5, 5};
	static int[][] map = new int[10][10];
	
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0, 0, 0);
        
        // 1을 모두 덮을 수 없는 경우에는 -1 출력
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

	}
	
	// dfs 사용 (백트래킹 이용)
	private static void dfs(int x, int y, int cnt) {
		
	}
	
	// 색종이를 덮을 수 있는지 확인
	private static boolean check(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				// 범위를 벗어나는 경우 false 반환
				if (i < 0 || i >= 10 || j < 0 || j >= 10) {
					return false;
				}
				
				// 1이 아닌 경우 (색종이를 붙일 수 없음) false 반환
				if (map[i][j] != 1) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	// 색종이를 덮음
	private static void cover(int x, int y, int size) {
		for (int i = 0; i < x + size; i++) {
			for (int j = 0; j < y + size; j++) {
				
			}
		}
	}

}
