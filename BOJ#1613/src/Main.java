/**
 * 1613 역사
 * https://www.acmicpc.net/problem/1613
 * 
 * @author minchae
 * @date 2024. 3. 14.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		map = new int[n + 1][n + 1];
		
		for (int i = 1; i <= n; i++) {
			map[i][i] = 1;
		}
		
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			map[n1][n2] = -1;
			map[n2][n1] = 1;
		}
		
		floyd();
		
		int s = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < s; i++) {
			st = new StringTokenizer(br.readLine());
			
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			System.out.println(map[n1][n2]);
		}
	}
	
	private static void floyd() {
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (map[i][k] == -1 && map[k][j] == -1) {
						map[i][j] = -1;
					}
					
					if (map[i][k] == 1 && map[k][j] == 1) {
						map[i][j] = 1;
					}
				}
			}
		}
	}

}
