/**
 * 15686 - 치킨 배달
 * https://www.acmicpc.net/problem/15686
 * 
 * @author Minchae Gwon
 * @date 2021.1.25
 * 
 * 치킨 거리는 집과 가장 가까운 치킨집 사이의 거리이다. 즉, 치킨 거리는 집을 기준으로 정해지며, 각각의 집은 치킨 거리를 가지고 있다. 도시의 치킨 거리는 모든 집의 치킨 거리의 합이다.
 * 
 * 입력
 * 첫째 줄에 N(2 ≤ N ≤ 50)과 M(1 ≤ M ≤ 13)이 주어진다.
 * 둘째 줄부터 N개의 줄에는 도시의 정보가 주어진다.
 * 도시의 정보는 0, 1, 2로 이루어져 있고, 0은 빈 칸, 1은 집, 2는 치킨집을 의미한다.
 * 집의 개수는 2N개를 넘지 않으며, 적어도 1개는 존재한다. 치킨집의 개수는 M보다 크거나 같고, 13보다 작거나 같다.
 * 
 * 출력
 * 첫째 줄에 폐업시키지 않을 치킨집을 최대 M개를 골랐을 때, 도시의 치킨 거리의 최솟값을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//치킨집이나 집의 위치를 저장할 클래스
class Point {
	int x;
	int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static ArrayList<Point> chicken;
	static ArrayList<Point> house;
	static int minChickenDist = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N + 1][N + 1];
		
		chicken = new ArrayList<>();
		house = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				//1은 집
				if (map[i][j] == 1) {
					house.add(new Point(i, j));
				}
				//2는 치킨집
				else if (map[i][j] == 2) {
					chicken.add(new Point(i, j));
				}
			}
		}
		
		boolean[] visited = new boolean[chicken.size()];
		//백트랙킹 이용
		comb(0, M, visited);
		System.out.println(minChickenDist);
		
		Arrays.fill(visited, false);
		//재귀 이용
		comb2(0, chicken.size(), M, visited);
		System.out.println(minChickenDist);
	}
	
	//조합 - 백트래킹
	public static void comb(int start, int r, boolean[] visited) {
		if (r == 0) {
			ArrayList<Point> pick = new ArrayList<>();
			
			for (int i = 0; i < chicken.size(); i++) {
				if (visited[i]) {
					pick.add(chicken.get(i));
				}
			}
			
			chickenDistance(pick);
			
			return;
		}
		
		for (int i = start; i < chicken.size(); i++) {
			visited[i] = true;
			comb(i + 1, r - 1, visited);
			visited[i] = false;
		}
	}
	
	//조합 - 재귀
	public static void comb2(int depth, int pick, int r, boolean[] visited) {
		if (r == 0) {
			ArrayList<Point> picked = new ArrayList<>();
			
			for (int i = 0; i < chicken.size(); i++) {
				if (visited[i]) {
					picked.add(chicken.get(i));
				}
			}
			
			chickenDistance(picked);
			
			return;
		}
		
		if (depth == pick) {
			return;
		} else {
			visited[depth] = true;
			comb2(depth + 1, pick, r - 1, visited);
			
			visited[depth] = false;
			comb2(depth + 1, pick, r, visited);
		}
	}
	
	public static void chickenDistance(ArrayList<Point> pick) {
		int total = 0; //모든 집의 치킨 거리의 합을 저장
		
		for (int i = 0; i < house.size(); i++) {
			int min = Integer.MAX_VALUE;
			
			for (int j = 0; j < pick.size(); j++) {
				int distance = Math.abs(house.get(i).x - pick.get(j).x) + Math.abs(house.get(i).y - pick.get(j).y);
				
				min = Math.min(min, distance);
			}
			
			total += min;
		}
		
		//치킨 거리의 최솟값 저장
		minChickenDist = Math.min(minChickenDist, total);
	}

}
