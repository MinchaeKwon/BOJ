/**
 * 16235 나무 재테크
 * https://www.acmicpc.net/problem/16235
 * 
 * @author minchae
 * @date 2024. 2. 13.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16235 {
	
	static class Tree implements Comparable<Tree> {
		int x;
		int y;
		int age;
		
		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}

		@Override
		public int compareTo(BOJ_16235.Tree o) {
			return this.age - o.age; // 나이를 기준으로 오름차순 정렬 (나이가 어린 나무부터 양분을 섭취하기 때문)
		}
	}
	
	// 상하좌우, 대각선
	static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
	
	static int N, M, K;
	static int[][] A;
	static int[][] map;
	
	static PriorityQueue<Tree> treeQ = new PriorityQueue<>();
	static Queue<Tree> dead = new LinkedList<>(); // 죽은 나무

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[N][N];
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5; // 가장 처음에 양분은 모든 칸에 5만큼 들어있음
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken()); // 나무의 나이
			
			treeQ.add(new Tree(x, y, z));
		}
		
		while (K-- > 0) {
			eat();
			change();
			breeding();
			add();
		}

		System.out.println(treeQ.size()); // 살아남은 나무의 개수 출력
	}
	
	// 나무가 자신의 나이만큼 양분을 먹고 나이 증가
	private static void eat() {
		Queue<Tree> alive = new LinkedList<>(); // 산 나무
		
		while (!treeQ.isEmpty()) {
			Tree cur = treeQ.poll();
			
			if (map[cur.x][cur.y] >= cur.age) { // 자신의 나이만큼 양분을 섭취할 수 있는 경우
				map[cur.x][cur.y] -= cur.age;
				cur.age++;
				
				alive.add(cur);
			} else { // 양분을 섭취할 수 없는 경우
				dead.add(cur);
			}
		}
		
		treeQ.addAll(alive);
	}
	
	// 죽은 나무가 양분으로 변함
	private static void change() {
		while (!dead.isEmpty()) {
			Tree cur = dead.poll();
			
			map[cur.x][cur.y] += cur.age / 2; // 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가
		}
	}
	
	// 나무 번식
	private static void breeding() {
		Queue<Tree> newTree = new LinkedList<>();
		
		for (Tree cur : treeQ) {
			if (cur.age % 5 == 0) {
				for (int i = 0; i < 8; i++) {
					int nx = cur.x + dx[i];
					int ny = cur.y + dy[i];
					
					if (isRange(nx, ny)) {
						newTree.add(new Tree(nx, ny, 1));
					}
				}	
			}
		}
		
		treeQ.addAll(newTree);
	}
	
	// 양분 추가 (S2D2가 땅을 돌아다니면서 땅에 양분을 추가)
	private static void add() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += A[i][j];
			}
		}
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
