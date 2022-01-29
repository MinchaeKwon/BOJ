/**
 * 15685 드래곤 커브
 * https://www.acmicpc.net/problem/15685
 * 
 * @author minchae
 * @Date 2022. 1. 28
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Dragon {
	int x;
	int y;
	int d;
	int g;
	
	public Dragon(int x, int y, int d, int g) {
		this.x = x;
		this.y = y;
		this.d = d;
		this.g = g;
	}
}

public class Main {
	
	/*
	 * 0: x좌표가 증가하는 방향 (→) 우
	 * 1: y좌표가 감소하는 방향 (↑) 상
	 * 2: x좌표가 감소하는 방향 (←) 좌
	 * 3: y좌표가 증가하는 방향 (↓) 하
	 */
	static int[] dx = {0, -1 , 0, 1};
	static int[] dy = {1, 0, -1, 1};
	
	static boolean[][] map = new boolean[101][101]; // 드래곤 커브가 포함되는 경우 true
	static Dragon[] dragon;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		dragon = new Dragon[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());	
			
			// x, y를 바꿔서 입력받는 이유 : (i, j)가 좌표일 때 i를 행, j를 열로 두고 하기 때문에 편하게 사용하기 위해 바꿔주는 것
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			dragon[i] = new Dragon(x, y, d, g);
		}

		System.out.println(getSquare());
	}
	
	private static void drawDragonCurve() {
		
	}
	
	private static int getSquare() {
		int cnt = 0;
		
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}

}
