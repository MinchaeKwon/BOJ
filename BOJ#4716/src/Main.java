
/**
 * 4716 풍선
 * https://www.acmicpc.net/problem/4716
 * 
 * @author minchae
 * @date 2024. 8. 17.
 */

import java.util.*;
import java.io.*;

public class Main {

	static class Team implements Comparable<Team> {
		int cnt;
		int aDist;
		int bDist;

		public Team(int cnt, int aDist, int bDist) {
			this.cnt = cnt;
			this.aDist = aDist;
			this.bDist = bDist;
		}

		@Override
		public int compareTo(Main.Team o) {
			return Math.abs(o.aDist - o.bDist) - Math.abs(this.aDist - this.bDist);
		}
	}

	static int N, A, B;
	static ArrayList<Team> teams;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			if (N == 0 && A == 0 && B == 0) {
				break;
			}

			teams = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());

				int K = Integer.parseInt(st.nextToken());
				int aDist = Integer.parseInt(st.nextToken());
				int bDist = Integer.parseInt(st.nextToken());

				teams.add(new Team(K, aDist, bDist));
			}

			Collections.sort(teams); // 방에서 가까운 팀 순서대로 정렬

			System.out.println(getDist());
		}
	}

	private static int getDist() {
		int result = 0;

		for (Team t : teams) {
			if (t.aDist < t.bDist) { // A와 더 가까운 경우
				if (t.cnt <= A) { // A에서 풍선을 가져와서 팀에 배치할 수 있는 경우
					result += t.cnt * t.aDist; // 거리 더함
					A -= t.cnt;
				} else { // 아닌 경우
					int tmp = t.cnt - A; // A에서 가져오고 남은 나머지
					result += A * t.aDist + tmp * t.bDist; // A에서 마저 가져오고 나머지는 B에서 가져옴

					A = 0;
					B -= tmp;
				}
			} else { // B와 더 가까운 경우
				if (t.cnt <= B) { // B에서 풍선을 가져와서 팀에 배치할 수 있는 경우
					result += t.cnt * t.bDist; // 거리 더함
					B -= t.cnt;
				} else { // 아닌 경우
					int tmp = t.cnt - B; // B에서 가져오고 남은 나머지
					result += B * t.bDist + tmp * t.aDist; // B에서 마저 가져오고 나머지는 A에서 가져옴

					B = 0;
					A -= tmp;
				}
			}
		}

		return result;
	}

}
