/**
 * 14499 - 주사위 굴리기
 * https://www.acmicpc.net/problem/14499
 * 
 * @author Minchae Gwon
 * @date 2021.2.10
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	//동서남북 순서
	static int[] dx = {0, 0, -1, 1}; //북남
	static int[] dy = {1, -1, 0, 0}; //동서

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dice = new int[6]; //처음 주사위의 숫자는 0임
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			int dir = Integer.parseInt(st.nextToken());
			
			//주사위가 이동할 위치 - 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
			int tx = x + dx[dir - 1];
			int ty = y + dy[dir - 1];
			
			//위치가 바깥이 아닐 경우에만 해당 명령을 수행
			if (tx >= 0 && tx < n && ty >= 0 && ty < m) {
				x = tx;
				y = ty;
				
				int[] temp = dice.clone();
				
				//주사위 굴리기 - 윗면은 1, 뒷면은 2, 오른쪽면은 3, 왼쪽면은 4, 앞면은 5, 아랫면은 6
				switch (dir) {
				case 1: //동쪽
					dice[0] = temp[3];
					dice[2] = temp[0];
					dice[3] = temp[5];
					dice[5] = temp[2];
					
					break;
				case 2: //서쪽
					dice[0] = temp[2];
					dice[2] = temp[5];
					dice[3] = temp[0];
					dice[5] = temp[3];
					
					break;
				case 3: //북쪽
					dice[0] = temp[4];
					dice[1] = temp[0];
					dice[4] = temp[5];
					dice[5] = temp[1];
					
					break;
				case 4: //남쪽
					dice[0] = temp[1];
					dice[1] = temp[5];
					dice[4] = temp[0];
					dice[5] = temp[4];
					
					break;
				}
				
				//주사위를 굴렸을 때, 이동한 칸에 쓰여있는 수가 0일 경우
				if (map[x][y] == 0) {
					map[x][y] = dice[5]; //주사위의 바닥면에 쓰여 있는 수가 칸에 복사됨
				} else {
					dice[5] = map[x][y]; //칸에 쓰여 있는 수가 주사위의 바닥면으로 복사됨
					map[x][y] = 0; //칸에 쓰여 있는 수는 0이 됨
				}
				
				//주사위 윗 면에 쓰인 수 출력
				System.out.println(dice[0]);
				
			}
			
		}
		
	}

}
