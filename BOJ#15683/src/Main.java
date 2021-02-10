/**
 * 15683 - 감시
 * https://www.acmicpc.net/problem/15683
 * 
 * @author Minchae Gwon
 * @date 2021.2.10
 * 
 * 입력
 * 첫째 줄에 사무실의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 8)
 * 둘째 줄부터 N개의 줄에는 사무실 각 칸의 정보가 주어진다. 0은 빈 칸, 6은 벽, 1~5는 CCTV를 나타내고, 문제에서 설명한 CCTV의 종류이다. 
 * CCTV의 최대 개수는 8개를 넘지 않는다.
 * 
 * 출력
 * 첫째 줄에 사각 지대의 최소 크기를 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Camera {
	int x;
	int y;
	int num; //cctv의 번호
	
	public Camera(int x, int y, int num) {
		this.x = x;
		this.y = y;
		this.num = num;
	}
}

public class Main {
	
	//이 순서로 해야 방향 계산하기 편함
	static int[] dx = {-1, 0, 1, 0}; //북남
	static int[] dy = {0, 1, 0, -1}; //서동
	
	static int[][] map;
	static ArrayList<Camera> camera;
	static int n;
	static int m;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		camera = new ArrayList<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				//cctv일 경우(빈 칸이 아니고 벽이 아닐 경우) 리스트에 넣어줌
				if (map[i][j] != 0 && map[i][j] != 6) {
					camera.add(new Camera(i, j, map[i][j]));
				}
			}
		}
		
		//첫 번째 cctv부터 탐색 시작
		findBlindSpot(0, map);
		System.out.println(result);
		
	}
	
	public static void findBlindSpot(int cNum, int[][] prev) {
		int[][] copy;
		
		//cctv의 모든 방향을 탐색한 경우
		if (cNum == camera.size()) {
			//사각지대의 최소 크기 알아내기
			int tmp = 0;
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (prev[i][j] == 0) {
						tmp++;
					}
				}
			}
			
			result = Math.min(result, tmp);
			
		} else {
			Camera c = camera.get(cNum);
			int cx = c.x;
			int cy = c.y;
			int num = c.num;
			
			switch (num) {
			case 1: //1번은 상, 하, 좌, 우 4가지를 탐색해야함
				for (int i = 0; i < 4; i++) {
					copy = copyArray(prev); //이전 감시 현황을 저장한 배열을 복사
					
					//cctv 감시 구역 구함
					detect(copy, i, cx, cy); //1번 한 방향만 감시
					findBlindSpot(cNum + 1, copy); //다음 cctv의 감시 구역을 구하기 위해 재귀 호출
				}
				
				break;
			case 2: //2번은 (상, 하), (좌, 우) 2가지를 탐색해야함
				for (int i = 0; i < 2; i++) {
					copy = copyArray(prev);
					
					//2번은 특정 방향과 그 방향의 반대 방향을 감시 -> 두 방향 감시
					detect(copy, i, cx, cy);
					detect(copy, i + 2, cx, cy);
					findBlindSpot(cNum + 1, copy);
				}
				
				break;
			case 3: //3번은 (상, 좌), (좌, 하), (하, 우), (우, 상) 4가지를 탐색해야함
				for (int i = 0; i < 4; i++) {
					copy = copyArray(prev);
					
					//3번은 특정 방향과 그 방향의 직각 방향(오른쪽 직각)을 감시 -> 두 방향 감시
					detect(copy, i, cx, cy);
					detect(copy, (i + 1) % 4, cx, cy);
					findBlindSpot(cNum + 1, copy);
				}
				
				break;
				
			case 4: //4번은 (상, 좌, 하), (좌, 하, 우), (하, 우, 상), (우, 상, 좌) 4가지를 탐색해야함
				for (int i = 0; i < 4; i++) {
					copy = copyArray(prev);
					
					//4번은 세 방향을 감시
					detect(copy, i, cx, cy);
					detect(copy, (i + 1) % 4, cx, cy);
					detect(copy, (i + 2) % 4, cx, cy);
					findBlindSpot(cNum + 1, copy);
				}
				
				break;
			case 5: //5번은 (상, 하 , 좌, 우) 1가지만 탐색해야함
				copy = copyArray(prev);
				
				//5번은 네 방향을 다 감시
				for (int i = 0; i < 4; i++) {	
					detect(copy, i, cx, cy);
				}
				
				findBlindSpot(cNum + 1, copy);
				
				break;
			}
			
		}
	}
	
	//특정 번호의 cctv로 감시할 수 있는 영역 구함
	public static void detect(int[][] backup, int dir, int x, int y) {
		x += dx[dir];
		y += dy[dir];
		
		while (x >= 0 && x < n && y >= 0 && y < m && backup[x][y] != 6) {
			//빈 구역일 경우 감시 가능
			if (backup[x][y] == 0) {
				backup[x][y] = 7; //감시할 수 있는 영역은 7로 표시
			}
			
			//cctv는 cctv를 통과할 수 있으므로 특정 조건 없어도 됨
			x += dx[dir];
			y += dy[dir];
		}
		
	}
	
	public static int[][] copyArray(int[][] prev) {
		int[][] cArray = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				cArray[i][j] = prev[i][j];
			}
		}
		
		return cArray;
	}

}
