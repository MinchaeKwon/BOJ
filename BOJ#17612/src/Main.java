/**
 * 17612 쇼핑몰
 * https://www.acmicpc.net/problem/17612
 * 
 * @author minchae
 * @date 2024. 9. 5.
 * */

import java.util.*;
import java.io.*;

public class Main {

	static class Node implements Comparable<Node> {
		int id;
		int w;
		int time; // 계산 끝난 시간
		int num; // 계산대 번호

		public Node(int id, int w) {
			this.id = id;
			this.w = w;
		}

		@Override
		public int compareTo(Main.Node o) {
			// 계산 소요 시간이 같으면 계산대 번호가 더 큰 사람이 먼저 나감
			if (this.time == o.time) {
				return Integer.compare(o.num, this.num);
			}

			return Integer.compare(this.time, o.time);
		}
	}

	static int N, k;

	static Queue<Node> customer;
	static PriorityQueue<Node> counter;
	static Stack<Integer> empty;

	static int idx;
	static long answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		customer = new LinkedList<>();
		counter = new PriorityQueue<>();
		empty = new Stack<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			customer.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		solve();

		System.out.println(answer);
	}

	private static void solve() {
		int time = 0;

		for (int i = 0; i < k; i++) {
			// 더이상 손님이 없으면 종료
			if (customer.isEmpty()) {
				break;
			}

			Node cur = customer.poll();

			cur.time = cur.w;
			cur.num = i;

			counter.add(cur); // 계산대에 손님 추가
		}

		// 계산대에 들어가지 못하는 손님이 있는 경우
		while (!customer.isEmpty()) {
			if (!counter.isEmpty()) {
				time = counter.peek().time; // 가장 빨리 나오는 계산대의 시간으로 갱신
			}

			// 해당 시간대에 계산을 마치는 고객을 밖으로 보냄
			int size = counter.size();

			for (int i = 0; i < size; i++) {
				if (counter.peek().time == time) {
					Node c = counter.poll();

					empty.push(c.num); // 빈 계산대에 추가

					answer += ++idx * (long) c.id; // 빠져나오는 순서대로 답 추가
				} else {
					break; // 아직 계산하는 중인 경우 종료 (우선순위 큐이기 때문에 현재 시간이 아닌 경우 종료하는 것)
				}
			}

			// 아직 남은 손님이 있고 빈 계산대가 있는 경우
			while (!customer.isEmpty() && !empty.isEmpty()) {
				Node cur = customer.poll();

				cur.num = empty.pop(); // 계산대 번호 추가
				cur.time = time + cur.w; // 계산 끝나는 시간 추가

				counter.add(cur); // 계산대에 사람 추가
			}
		}

		// 아직 계산대에 남아있는 사람들 중 나오는 순서대로 정답 추가
		while (!counter.isEmpty()) {
			answer += ++idx * (long) counter.poll().id;
		}
	}

}
