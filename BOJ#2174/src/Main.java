/**
 * 2174 로봇 시뮬레이션
 * https://www.acmicpc.net/problem/2174
 * 
 * @author Minchae Gwon
 * @date 2021.3.2
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Robot {
	int x;
	int y;
	int dir;
	
	public Robot(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}

public class Main {
	
	//북서남동
	static int[] dx = {-1, 0, 1, 0}; //북남
	static int[] dy = {0, -1, 0, 1}; //서동
	
	static int A, B;
	static int N, M;
	
	static int[][] map;
	static ArrayList<Robot> robot = new ArrayList<>();
	
	static boolean flag = true;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken()); //가로 -> 열
		B = Integer.parseInt(st.nextToken()); //세로 -> 행
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //로봇 개수
		M = Integer.parseInt(st.nextToken()); //명령 개수
		
		map = new int[B][A];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken()) - 1; //왼쪽부터 -> 열
			int y = Integer.parseInt(st.nextToken()) - 1; //아래쪽부터 -> 행
			
			String s = st.nextToken();
			int dir = 0;
			
			switch (s) {
			case "N":
				dir = 0;
				break;
			case "W":
				dir = 1;
				break;
			case "S":
				dir = 2;
				break;
			case "E":
				dir = 3;
				break;
			}
			
			robot.add(new Robot(B - y - 1, x, dir));
			map[B - y - 1][x] = i + 1; //이렇게 해야 로봇의 위치가 제대로 들어감, 로봇의 번호를 저장함
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int robotN = Integer.parseInt(st.nextToken());
			String command = st.nextToken();
			int cnt = Integer.parseInt(st.nextToken());
			
			simulation(robotN, command, cnt);
			
			if (!flag) break;

		}
		
		if (flag) System.out.println("OK");

	}
	
	public static void simulation(int num, String cmd, int cnt) {
		for (int i = 0; i < cnt; i++) {
			Robot r = robot.get(num - 1);
			
			switch (cmd) {
			case "L":
				r.dir = (r.dir + 1) % 4;
				break;
			case "R":
				r.dir = (r.dir + 3) % 4;
				break;
			case "F":
				int nx = r.x + dx[r.dir];
				int ny = r.y + dy[r.dir];
				
				//로봇이 벽에 충돌하는 경우
				if (nx < 0 || nx >= B || ny < 0 || ny >= A) {
					System.out.println("Robot " + num + " crashes into the wall");
					flag = false;
					
					return;
				}
				
				//로봇끼리 충돌하는 경우
				if (map[nx][ny] != 0) {
					System.out.println("Robot " + num + " crashes into robot " + map[nx][ny]);
					flag = false;
					
					return;
				}
				
				map[r.x][r.y] = 0;
				map[nx][ny] = num;
				r.x = nx;
				r.y = ny;
				
				break;
			}
		}

	}

}
