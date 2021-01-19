/**
 * 16234 - 인구 이동
 * https://www.acmicpc.net/problem/16234
 * 
 * @author Minchae Gwon
 * @date 2021.1.20
 *
 * 인구 이동은 다음과 같이 진행되고, 더 이상 아래 방법에 의해 인구 이동이 없을 때까지 지속된다.
 * 1. 국경선을 공유하는 두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루동안 연다.
 * 2. 위의 조건에 의해 열어야하는 국경선이 모두 열렸다면, 인구 이동을 시작한다.
 * 3. 국경선이 열려있어 인접한 칸만을 이용해 이동할 수 있으면, 그 나라를 오늘 하루 동안은 연합이라고 한다.
 * 4. 연합을 이루고 있는 각 칸의 인구수는 (연합의 인구수) / (연합을 이루고 있는 칸의 개수)가 된다. 편의상 소수점은 버린다.
 * 5. 연합을 해체하고, 모든 국경선을 닫는다.
 * 각 나라의 인구수가 주어졌을 때, 인구 이동이 몇 번 발생하는지 구하는 프로그램을 작성하시오.
 * 
 * 입력
 * 첫째 줄에 N, L, R이 주어진다. (1 ≤ N ≤ 50, 1 ≤ L ≤ R ≤ 100)
 * 둘째 줄부터 N개의 줄에 각 나라의 인구수가 주어진다. r행 c열에 주어지는 정수는 A[r][c]의 값이다. (0 ≤ A[r][c] ≤ 100)
 * 인구 이동이 발생하는 횟수가 2,000번 보다 작거나 같은 입력만 주어진다.
 * 
 * 출력
 * 인구 이동이 몇 번 발생하는지 첫째 줄에 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0; //인구 이동 횟수를 저장
		
		//인구 이동이 일어나지 않을 때까지 이동을 해야하므로 무한루프
		while (true) {
			boolean[][] visited = new boolean[N][N];
			boolean flag = false; //인구 이동 여부 확인
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					//방문하지 않은 나라일때, 연합 국가를 찾아서 인구 이동이 일어났다면
					if (!visited[i][j] && bfs(i, j, visited, map, N, L, R)) {
						flag = true; //인구 이동을 했으므로 true로 바꿔줌
					}
				}
			}
			
			if (flag) {
				result++;
			}
			else {
				break;
			}
		}
		
		System.out.println(result);

	}
	
	//연합이 있는지 확인 후 인구 이동
	public static boolean bfs(int x, int y, boolean[][] visited, int[][] map, int n, int L, int R) {
		Queue<Point> q = new LinkedList<>();
		ArrayList<Point> list = new ArrayList<>(); //연합 국가들의 위치를 저장할 리스트
		
		Point p = new Point(x, y);
		
		q.add(p);
		visited[x][y] = true;
		list.add(p);
		
		int total = map[x][y]; //연합 국가들의 인구 수를 저장
		
		while (!q.isEmpty()) {
			p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int tx = p.x + dx[i];
				int ty = p.y + dy[i];
				
				//해당 국가 상하좌우에 연합 할 수 있는 국가를 확인함
				if (tx >= 0 && tx < n && ty >= 0 && ty < n) {
					int diff = Math.abs(map[p.x][p.y] - map[tx][ty]);
					
					//두 나라의 인구 차이가 L명 이상, R명 이하라면, 두 나라가 공유하는 국경선을 하루동안 염 -> 연합
					if (!visited[tx][ty] && diff >= L && diff <= R) {
						Point nation = new Point(tx, ty);
						
						q.add(nation);
						visited[tx][ty] = true;
						
						list.add(nation);
						total += map[tx][ty]; //연합 국가들의 인구 수를 모두 더함
					}
				}
			}
			
		}
		
		//연합 국가가 있는 경우 연합 국가의 인구 수를 반환(list.size() == 1이라면 연합 국가가 없는 것)
		if (list.size() != 1) {
			int population = total / list.size(); //(연합의 인구수) / (연합을 이루고 있는 칸의 개수) = 연합 국가의 인구 수
			
			for (int i = 0; i < list.size(); i++) {
				Point point = list.get(i);
				map[point.x][point.y] = population; //연합 국가들의 인구 수를 바꿔서 인구 이동을 함
			}
			
			//인구 이동을 했으므로 true 반환
			return true;
		}
		
		//국경선이 열리지 않아 연합 국가가 없으면 false 반환
		return false;
		
	}

}
