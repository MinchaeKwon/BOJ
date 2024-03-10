/**
 * 18809 Gaaaaaaaaaarden
 * https://www.acmicpc.net/problem/18809
 * 
 * @author minchae
 * @date 2024. 3. 10.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int x;
		int y;
		int time;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M, G, R;
	static int[][] map; // 0은 호수, 1은 배양액을 뿌릴 수 없는 땅, 2는 배양액을 뿌릴 수 있는 땅
	
	static ArrayList<Node> candidate = new ArrayList<>();
	static int[] green; // 초록 배양액 뿌린 candidate 인덱스
	static int[] red; // 빨간 배양액 뿌린 candidate 인덱스
	
	static boolean[] visited;
	
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		green = new int[G];
		red = new int[R];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 2) {
					candidate.add(new Node(i, j));
				}
			}
		}
		
		visited = new boolean[candidate.size()];

		pickGreen(0, 0);
		
		System.out.println(answer);
	}
	
	private static void pickGreen(int depth, int start) {
		if (depth == G) {
			pickRed(0, 0);
			return;
		}
		
		for (int i = start; i < candidate.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				green[depth] = i;
				
				pickGreen(depth + 1, i + 1);
				
				visited[i] = false;
			}
		}
	}
	
	private static void pickRed(int depth, int start) {
		if (depth == R) {
			bfs();
			return;
		}
		
		for (int i = start; i < candidate.size(); i++) {
			if (!visited[i]) {
				visited[i] = true;
				red[depth] = i;
				
				pickRed(depth + 1, i + 1);
				
				visited[i] = false;
			}
		}
	}
	
	private static void bfs() {
		int[][] copyMap = new int[N][M];
		int[][] time = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			copyMap[i] = Arrays.copyOf(map[i], M);
		}
		
		Queue<Node> q = new LinkedList<>();
		
		for (int n : green) {
			Node p = candidate.get(n);
			
			q.add(new Node(p.x, p.y, 0));
			copyMap[p.x][p.y] = 3;
		}
		
		for (int n : red) {
			Node p = candidate.get(n);
			
			q.add(new Node(p.x, p.y, 0));
			copyMap[p.x][p.y] = 4;
		}
		
		int cnt = 0; // 피우는 꽃의 개수
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (copyMap[cur.x][cur.y] == 5) {
				continue;
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!isRange(nx, ny) || copyMap[nx][ny] == 0) {
					continue;
				}
				
				if (copyMap[nx][ny] == 1 || copyMap[nx][ny] == 2) { // 배양액을 퍼뜨릴 수 있는 경우
					q.add(new Node(nx, ny, cur.time + 1));
					
					copyMap[nx][ny] = copyMap[cur.x][cur.y];
					time[nx][ny] = cur.time + 1;
				} else if (copyMap[nx][ny] == 3) { // 다음 칸이 초록 배양액인 경우
					// 현재 칸이 빨간 배양액인데 초록 배양액과 퍼지는 시간이 같은 경우
					if (copyMap[cur.x][cur.y] == 4 && time[cur.x][cur.y] + 1 == time[nx][ny]) {
						cnt++;
						copyMap[nx][ny] = 5; // 꽃을 피움
					}
				} else if (copyMap[nx][ny] == 4) { // 다음 칸이 빨간 배양액인 경우
					// 현재 칸이 초록 배양액인데 빨간 배양액과 퍼지는 시간이 같은 경우
					if (copyMap[cur.x][cur.y] == 3 && time[cur.x][cur.y] + 1 == time[nx][ny]) {
						cnt++;
						copyMap[nx][ny] = 5; // 꽃을 피움
					}
				}
			}
		}
		
		answer = Math.max(answer, cnt); // 최댓값 갱신
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
