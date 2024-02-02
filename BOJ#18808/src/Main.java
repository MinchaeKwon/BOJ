/**
 * 18808 스티커 붙이기
 * https://www.acmicpc.net/problem/18808
 * 
 * @author minchae
 * @date 2024. 2. 2.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, K;
	
	static int[][] map;
	static int[][] sticker;
	
	static int R, C;
	
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			sticker = new int[R][C];
			
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < C; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			findPosition();
		}
		
		System.out.println(answer);
	}
	
	// 스티커를 붙일 위치 찾기
	private static void findPosition() {
		// 0, 90, 180, 270도 회전
		for (int d = 0; d < 4; d++) {
			for (int i = 0; i < N - R + 1; i++) {
				for (int j = 0; j < M - C + 1; j++) {
					// 특정 칸에 스티커를 붙일 수 있는 경우에만 붙임
					if (check(i, j)) {
						attach(i, j); // 스티커를 붙인 경우 종료
						return;
					}
				}
			}
			
			sticker = rotate(); // 못붙이면 90도 회전 시킴
			
			// 가로, 세로 크기가 변경됐기 때문에 수정해줌
			R = sticker.length;
			C = sticker[0].length;
		}
	}
	
	// 특정 칸을 기점으로 스티커를 붙일 수 있는지 확인
	private static boolean check(int x, int y) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 스티커가 있어서 붙이려고 하는 위치에 이미 다른 스티커가 있는 경우 false 반환
				if (sticker[i][j] == 1 && map[i + x][j + y] == 1) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	// 스티커 붙이기
	private static void attach(int x, int y) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				// 스티커를 붙임
				if (sticker[i][j] == 1) {
					map[i + x][j + y] = 1;
					answer++;
				}
			}
		}
	}
	
	// 시계 방향으로 90도 회전
	private static int[][] rotate() {
		int[][] result = new int[C][R]; // 정사각형이 아니기 때문에 회전시키면 가로, 세로 크기가 변함
		
		// 기준이 (0, 0)인 사각형을 회전시키는 것이기 때문에 값을 바로 넣어도 됨 (아닌 경우에는 좌표를 (0, 0)으로 맞추고 회전시켜야함)
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				result[j][R - i - 1] = sticker[i][j];
			}
		}
		
		return result;
	}

}
