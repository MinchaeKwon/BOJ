/**
 * 14503 - 로봇 청소기
 * https://www.acmicpc.net/problem/14503
 * 
 * @author Minchae Gwon
 * @date 2021.2.8
 * 
 * 문제
 * 로봇 청소기가 주어졌을 때, 청소하는 영역의 개수를 구하는 프로그램을 작성하시오.
 * 로봇 청소기가 있는 장소는 N×M 크기의 직사각형으로 나타낼 수 있으며, 1×1크기의 정사각형 칸으로 나누어져 있다. 각각의 칸은 벽 또는 빈 칸이다. 
 * 청소기는 바라보는 방향이 있으며, 이 방향은 동, 서, 남, 북중 하나이다. 
 * 지도의 각 칸은 (r, c)로 나타낼 수 있고, r은 북쪽으로부터 떨어진 칸의 개수, c는 서쪽으로 부터 떨어진 칸의 개수이다.
 * 
 * 로봇 청소기는 다음과 같이 작동한다.
 * 1. 현재 위치를 청소한다.
 * 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
 * 	a. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
 * 	b. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
 * 	c. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
 * 	d. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
 * 로봇 청소기는 이미 청소되어있는 칸을 또 청소하지 않으며, 벽을 통과할 수 없다.
 * 
 * 입력
 * 첫째 줄에 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 50)
 * 둘째 줄에 로봇 청소기가 있는 칸의 좌표 (r, c)와 바라보는 방향 d가 주어진다. d가 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽을 바라보고 있는 것이다.
 * 셋째 줄부터 N개의 줄에 장소의 상태가 북쪽부터 남쪽 순서대로, 각 줄은 서쪽부터 동쪽 순서대로 주어진다. 빈 칸은 0, 벽은 1로 주어진다. 지도의 첫 행, 마지막 행, 첫 열, 마지막 열에 있는 모든 칸은 벽이다.
 * 로봇 청소기가 있는 칸의 상태는 항상 빈 칸이다.
 * 
 * 출력
 * 로봇 청소기가 청소하는 칸의 개수를 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1, 0, 1, 0}; //북남(상하)
	static int[] dy = {0, -1, 0, 1}; //동서(우좌)
	
	static int[][] map;
	static int result = 1; //처음에 로봇 청소기가 있는 위치는 무조건 청소를 하기 때문에 1부터 시작함

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()); //북쪽으로부터 떨어진 칸의 개수 -> 행
		int c = Integer.parseInt(st.nextToken()); //서쪽으로 부터 떨어진 칸의 개수 -> 열
		int d = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//d가 0이면 북쪽, 1이면 동쪽, 2이면 남쪽, 3이면 서쪽인데, dx, dy에서 북서남동을 0123으로 설정했기 때문에 아래 코드처럼 d를 바꿔줌
		if (d == 3)
			d = 1;
		else if (d == 1)
			d = 3;
		
		/*애초에 
		 * int[] dx = {-1, 0, 1, 0};
		 * int[] dy = {0, 1, 0, -1};
		 * 이렇게 하면 굳이 바꾸지 않아도 되고 clean for문 안에서 d = (d + 3) % 4;으로만 바꿔주면 됨
		 * */
		
		clean(r, c, d);
		System.out.println(result);
		
	}
	
	//dfs 사용
	public static void clean(int r, int c, int d) {
		map[r][c] = 2; //청소한 구역은 2를 넣어줌
		
		//왼쪽 방향에 청소하지 않은 곳이 있다면 청소 -> 북 서 남 동 순서로 감
		for (int i = 0; i < 4; i++) {
			//다음에 청소할 구역 위치 설정
			d = (d + 1) % 4;
			int tx = r + dx[d];
			int ty = c + dy[d];
			
			//왼쪽 방향에 청소가 가능한 경우(아직 청소가 되지 않은 경우)
			if (map[tx][ty] == 0) {
				result++; //청소 했으므로 ++해줌
				clean(tx, ty, d); //다른 곳을 청소하기 위해 재귀 호출
				
				//return을 해주지 않으면 청소하다가 후진 할 수 없어서 청소가 종료 되어도 위의 for문으로 돌아가게 되기 때문에 return을 해줘야 함
				return;
			}
		}
		
		//후진하기 위한 위치 설정(현재 바라보는 방향을 유지한 채로 후진) -> 네 방향 모두 청소가 되어있거나 벽인 경우에만 이 부분을 만남(이 경우가 아니면 위의 for문에서 재귀 호출 하기 때문)
		int back = (d + 2) % 4; // ex)방향이 북쪽이면 남쪽으로 이동해야하므로 (d + 2) % 4를 통해서 이동할 곳을 정함
		int bx = r + dx[back];
		int by = c + dy[back];
	
		//후진하려는 곳이 벽이 아닐 경우 후진 가능하므로 재귀 호출
		if (map[bx][by] != 1) {
			clean(bx, by, d); //후진은 방향을 바꾸는 것이 아니기 때문에 d는 그대로 넣어줌
		}
		
	}

}
