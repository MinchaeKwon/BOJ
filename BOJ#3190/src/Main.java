/**
 * 3190 - 뱀
 * https://www.acmicpc.net/problem/3190
 * 
 * @author Minchae Gwon
 * @date 2021.2.9
 * 
 * 입력
 * 첫째 줄에 보드의 크기 N이 주어진다. (2 ≤ N ≤ 100) 다음 줄에 사과의 개수 K가 주어진다. (0 ≤ K ≤ 100)
 * 다음 K개의 줄에는 사과의 위치가 주어지는데, 첫 번째 정수는 행, 두 번째 정수는 열 위치를 의미한다. 사과의 위치는 모두 다르며, 맨 위 맨 좌측 (1행 1열) 에는 사과가 없다.
 * 다음 줄에는 뱀의 방향 변환 횟수 L 이 주어진다. (1 ≤ L ≤ 100)
 * 다음 L개의 줄에는 뱀의 방향 변환 정보가 주어지는데,  정수 X와 문자 C로 이루어져 있으며. 
 * 게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻이다. X는 10,000 이하의 양의 정수이며, 방향 전환 정보는 X가 증가하는 순으로 주어진다.
 * 
 * 출력
 * 첫째 줄에 게임이 몇 초에 끝나는지 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//방향 전환 정보를 담을 클래스
class DirInfo {
	int time;
	String dir;
	
	public DirInfo(int time, String dir) {
		this.time = time;
		this.dir = dir;
	}
}

//뱀의 위치를 담을 클래스
class Snake {
	int x;
	int y;
	
	public Snake(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int[][] map;
	static Queue<DirInfo> dirInfo; //뱀의 방향 변환 정보를 담음
	static Deque<Snake> snake; //뱀의 위치를 담음 -> 머리와 꼬리를 각각 움직여줘야하기 때문에 Deque 사용
	
	static int[] dx = {-1, 0, 1, 0}; //북남(상하)
	static int[] dy = {0, -1, 0, 1}; //서동(좌우)
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());
		
		//뱀, 사과 둘 다 없는 경우 0, 사과가 있는 경우 1, 뱀이 있는 경우 2를 넣음
		map = new int[n + 1][n + 1];
		
		for (int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = 1; //사과가 있는 곳은 1로 표시
		}
		
		int l = Integer.parseInt(br.readLine());
		dirInfo = new LinkedList<>();
		
		//방향 전환 정보 넣기
		for (int i = 0; i < l; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			String d = st.nextToken();
			dirInfo.add(new DirInfo(t, d));
		}
		
		snake = new ArrayDeque<>();
		snake.add(new Snake(1, 1)); //뱀의 처음 위치 넣어줌
		map[1][1] = 2; //뱀이 있는 곳은 2
		
		int curDir = 3; //뱀의 현재 방향(오른쪽으로 이동하므로 동쪽을 바라보고 있음) - 0은 북(상), 1는 서(좌), 2은 남(하), 3는 동(우)
		
		System.out.println(gameOver(curDir, n));
		
	}
	
	public static int gameOver(int cur, int n) {
		int result = 0;
		while (true) {
			result++;
			
			//뱀이 이동할 위치(몸길이를 늘려 머리를 위치할 곳)
			int tx = snake.peekFirst().x + dx[cur];
			int ty = snake.peekFirst().y + dy[cur];
			
			//벽 또는 자기 자신의 몸과 부딪힌 경우 게임 종료
			if (tx <= 0 || tx > n || ty <= 0 || ty > n || map[tx][ty] == 2) {
				return result;
			}
			
			//이동한 칸에 사과가 있는 경우
			if (map[tx][ty] == 1) {
				map[tx][ty] = 2; //사과를 없애고 뱀으로 표시
				snake.offerFirst(new Snake(tx, ty)); //머리를 해당 좌표로 이동시킴
			}
			else { //사과가 없는 경우
				map[tx][ty] = 2; //뱀의 위치 표시
				snake.offerFirst(new Snake(tx, ty)); //머리를 해당 좌표로 이동시킴
				
				Snake tail = snake.pollLast(); //꼬리가 위치한 칸을 비워주기 위해 꼬리 위치를 poll
				map[tail.x][tail.y] = 0; //꼬리 위치한 칸을 비워줌
			}
			
			//방향 전황 정보가 있고 게임 시간이 특정 방향 전환 정보의 x초가 흘렀을 때
			if (!dirInfo.isEmpty() && result == dirInfo.peek().time) {
				String c =  dirInfo.poll().dir; //c로 90도 회전
				
				//D일 경우 오른쪽으로 90도 방향 전환
				if (c.equals("D")) {
					cur = (cur + 3) % 4; //동(3) -> 남(2) -> 서(1) -> 북(0) 순서로 감
				}
				else { //L일 경우 왼쪽으로 90도 방향 전환
					cur = (cur + 1) % 4; //동(3) -> 북(0) -> 서(1) -> 남(2) 순서로 감
				}
	
			}
			
		}
	}

}
