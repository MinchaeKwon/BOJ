/**
 * 15685 드래곤 커브
 * https://www.acmicpc.net/problem/15685
 * 
 * @author minchae
 * @date 2022. 1. 31.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	/*
	 * 0: x좌표가 증가하는 방향 (→) 우
	 * 1: y좌표가 감소하는 방향 (↑) 상
	 * 2: x좌표가 감소하는 방향 (←) 좌
	 * 3: y좌표가 증가하는 방향 (↓) 하
	 * 
	 * 일반적으로 사용하는 i(행), j(열) 기준이 아닌 진짜 그래프의 x축(가로), y축(세로)을 기준으로 방향 설정
	 * i(행), j(열)을 사용하는 경우
	 * static int[] dx = {0, -1, 0, 1};
	 * static int[] dy = {1, 0, -1, 0}; 이렇게 쓰고 입력받는 부분에서 x, y를 바꿔서 저장해야함
	 */
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static boolean[][] map = new boolean[101][101]; // 드래곤 커브가 포함되는 경우 true를 넣음

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			drawDragonCurve(x, y, d, g);
		}

		System.out.println(getSquare());
	}
	
	// 드래곤 커브 그리기
	private static void drawDragonCurve(int x, int y, int d, int g) {
		ArrayList<Integer> dirList = new ArrayList<>();
		dirList.add(d); // 방향 추가
		
		// 세대만큼 반복
		while (g-- > 0) {
			// 전 세대의 영향을 받기 때문에 마지막부터 시작
			for (int i = dirList.size() - 1; i >= 0; i--) {
				int dir = dirList.get(i);
				dirList.add((dir + 1) % 4); // 새로운 방향 추가 : 반시계 방향으로 90도씩 돌린 방향 추가
			}
		}
		
		// 추가된 방향들을 true로 바꿔줌 : 드래곤 커브의 일부로 바꿔주는 것
		map[x][y] = true;
		
		for (int dir : dirList) {
			x += dx[dir];
			y += dy[dir];
			
			map[x][y] = true;
		}
	}
	
	// 크기가 1×1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형의 개수를 구함
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
