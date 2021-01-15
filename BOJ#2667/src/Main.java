/**
 * 2668 - 단지번호붙이기
 * https://www.acmicpc.net/problem/2667
 * 
 * @author Minchae Gwon
 * @date 2021.1.16
 * 
 * 입력
 * 첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.
 * 
 * 출력
 * 첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
 *
 */

import java.util.Scanner;

public class Main {

	//상하좌우의 단지를 확인하기 위한 배열
	static int[] dx = {-1, 1, 0, 0}; //상하
	static int[] dy = {0, 0, -1, 1}; //좌우
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		int[][] map = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			String m = sc.next();
			
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(m.substring(j, j + 1));
			}
		}
		
		
		
		sc.close();

	}

}
