/**
 * 2842 집배원 한상덕
 * https://www.acmicpc.net/problem/2842
 * 
 * @author minchae
 * @date 2024. 4. 11.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
	
	static int N;
	static char[][] map;
	static int[][] num; // 고도 저장
	
	static int sx, sy;
	static int house;
	
	static ArrayList<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		num = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 'P') {
					sx = i;
					sy = j;
				} else if (map[i][j] == 'K') {
					house++;
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		int max = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				num[i][j] = Integer.parseInt(st.nextToken());
				list.add(num[i][j]);
				
				if (map[i][j] != '.') {
					min = Math.min(min, num[i][j]);
					max = Math.max(max, num[i][j]);
				}
			}
		}
		
		Collections.sort(list);
		
		int left = 0; // 고도가 제일 낮은 곳의 인덱스
		int right = list.indexOf(max); // 고도가 제일 높은 곳의 인덱스
		
		int minIdx = list.indexOf(min);
		int maxIdx = list.size() - 1;
		
		int answer = Integer.MAX_VALUE;
		
		// 최소 인덱스가 최대 인덱스를 넘지 않아야 함
		// 최소 인덱스가 가장 낮은 고도의 인덱스를 넘지 않아야 함
		// 최대 인덱스가 마지막 인덱스를 넘지 않아야 함
		while (left <= right && left <= minIdx && right <= maxIdx) {
			if (bfs(left, right, house)) {
				answer = Math.min(answer, list.get(right) - list.get(left));
				left++;
			} else {
				right++;
			}
		}

		System.out.println(answer);
	}
	
	private static boolean bfs(int left, int right, int cnt) {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		q.add(new Pair(sx, sy));
		visited[sx][sy] = true;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			if (cnt == 0) {
				return true;
			}
			
			for (int i = 0; i < 8; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!isRange(nx, ny) || visited[nx][ny]) {
					continue;
				}
				
				// 두 고도의 범위에 다음 칸의 고도가 없는 경우
				if (num[nx][ny] > list.get(right) || num[nx][ny] < list.get(left)) {
					continue;
				}
				
				if (map[nx][ny] == 'K') {
					cnt--;
				}
				
				q.add(new Pair(nx, ny));
				visited[nx][ny] = true;
			}
		}
		
		return false;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0&& x < N && y >= 0 && y < N;
	}

}
