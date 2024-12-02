
/**
 * 19940 피자 오븐
 * https://www.acmicpc.net/problem/19940
 * 
 * @author minchae
 * @date 2024. 12. 1.
 * 
 * 문제 풀이
 * - N이 60이 넘어간다면 ADDH를 하는 것이 이득
 * - 먼저 시간 별로 어떤 버튼을 누를 지 BFS를 통해 계산
 * - N을 입력받으면서 시간과 남는 분을 계산하고 BFS를 통해 계산한 횟수를 출력 함
 */

import java.io.*;
import java.util.*;

public class Main {

	static class Oven {
		int addH; // 사용 횟수
		int addT;
		int minT;
		int addO;
		int minO;
		int time; // 현재 시간

		public Oven(int addH, int addT, int minT, int addO, int minO, int time) {
			this.addH = addH;
			this.addT = addT;
			this.minT = minT;
			this.addO = addO;
			this.minO = minO;
			this.time = time;
		}
	}

	static int[][] info = { 
			{ -1, 0, 0, 0, 0, 1 }, // -1분
			{ 1, 0, 0, 0, 1, 0 }, // +1분
			{ -10, 0, 0, 1, 0, 0 }, // -10분
			{ 10, 0, 1, 0, 0, 0 }, // +10분
			{ 60, 1, 0, 0, 0, 0 } // +60분
	};

	static boolean[] visited;
	static Oven[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		visited = new boolean[61];
		answer = new Oven[61];

		bfs();

		StringBuilder sb = new StringBuilder();

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());

			int hour = N / 60;
			int minute = N % 60;

			sb.append(answer[minute].addH + hour).append(" ").append(answer[minute].addT).append(" ")
					.append(answer[minute].minT).append(" ").append(answer[minute].addO).append(" ")
					.append(answer[minute].minO).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static void bfs() {
		Queue<Oven> q = new LinkedList<>();

		q.add(new Oven(0, 0, 0, 0, 0, 0));
		visited[0] = true;
		answer[0] = new Oven(0, 0, 0, 0, 0, 0);

		while (!q.isEmpty()) {
			Oven cur = q.poll();

			for (int[] cnt : info) {
				int next = cur.time + cnt[0];

				// 0분 미만, 60분 초과, 이미 방문한 경우 넘어감
				if (next < 0 || next > 60 || visited[next]) {
					continue;
				}

				visited[next] = true; // 방문 처리

				Oven nextOven = new Oven(
						cur.addH + cnt[1],
						cur.addT + cnt[2], 
						cur.minT + cnt[3], 
						cur.addO + cnt[4],
						cur.minO + cnt[5], next);

				answer[next] = nextOven;
				q.add(nextOven);

			}
		}
	}

}
