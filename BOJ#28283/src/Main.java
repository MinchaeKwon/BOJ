
/**
 * 28283 해킹
 * https://www.acmicpc.net/problem/28283
 * 
 * @author minchae
 * @date 2024. 1. 13.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Computer implements Comparable<Computer> {
	int n; // 컴퓨터 인덱스
	int t; // 보안 시스템 설치 시간

	public Computer(int n, int t) {
		this.n = n;
		this.t = t;
	}

	@Override
	public int compareTo(Computer o) {
		return this.t - o.t; // 보안 시스템 설치 시간을 기준으로 오름차순 정렬
	}
}

public class Main {

	static int N, M, X, Y;

	static ArrayList<Integer>[] map;

	static long[] A; // 각 컴퓨터가 매분 가져갈 수 있는 돈
	static int[] B; // 초기에 보안 시스템이 설치된 컴퓨터 번호
	static int[] time; // 각 컴퓨터의 보안 시스템 설치 시간

	static boolean[] visited; // 보안 시스템 설치 여부

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		map = new ArrayList[N + 1];

		A = new long[N + 1];
		B = new int[Y + 1];
		time = new int[N + 1];

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			A[i] = Long.parseLong(st.nextToken()); // 컴퓨터를 해킹할 경우 가지고 갈 수 있는 돈
		}

		for (int i = 1; i <= N; i++) {
			map[i] = new ArrayList<>();
		}

		// 네트워크 통신망 입력
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			map[S].add(E);
			map[E].add(S);
		}

		st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= Y; i++) {
			B[i] = Integer.parseInt(st.nextToken()); // 초기 보안 시스템 설치 컴퓨터 번호 입력
		}

		bfs();

		ArrayList<Long> list = new ArrayList<>();

		for (int i = 1; i <= N; i++) {
			// 무수히 많은 금액을 창출할 수 있는 경우 (특정 컴퓨터에 보안 시스템이 설치되지 않아 매분마다 돈을 가져올 수 있음)
			if (!visited[i] && A[i] > 0) {
				System.out.println(-1);
				System.exit(0); // 종료
			}

			list.add(A[i] * time[i]); // 특정 컴퓨터가 얻을 수 있는 돈 * 보안 시스템 설치 시간 (매분 돈을 가져갈 수 있기 때문)
		}

		Collections.sort(list, Collections.reverseOrder()); // 내림차순 정렬
		
		long answer = 0;

		// 무수히 많은 금액을 창출할 수 없는 경우 최대로 얻을 수 있는 돈 X개를 더함
		for (int i = 0; i < X; i++) {
			answer += list.get(i);
		}

		System.out.println(answer);
	}

	// 각 시간마다 보안 시스템 설치
	private static void bfs() {
		PriorityQueue<Computer> pq = new PriorityQueue<>();
		visited = new boolean[N + 1];

		// 초기 보안 시스템 설치
		for (int i = 1; i <= Y; i++) {
			pq.add(new Computer(B[i], 0));
			visited[B[i]] = true;
		}

		while (!pq.isEmpty()) {
			Computer cur = pq.poll();

			// 설치된 통신망으로 직접 연결된 모든 컴퓨터에 보안 시스템이 자동으로 설치함
			for (int next : map[cur.n]) {
				if (!visited[next]) {
					pq.add(new Computer(next, cur.t + 1));
					visited[next] = true;

					time[next] = cur.t + 1;
				}
			}
		}

	}

}
