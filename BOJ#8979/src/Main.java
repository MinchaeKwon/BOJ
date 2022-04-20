/**
 * 8979 올림픽
 * https://www.acmicpc.net/problem/8979
 * 
 * @author minchae
 * @date 2022. 4. 20.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Nation implements Comparable<Nation> {
	int num;
	int gold;
	int silver;
	int bronze;
	int rank;
	
	public Nation(int num, int gold, int silver, int bronze) {
		this.num = num;
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
		this.rank = 1;
	}

	@Override
	public int compareTo(Nation o) {
		if (this.gold == o.gold) {
			if (this.silver == o.silver) {
				return o.bronze - this.bronze;
			}
			
			return o.silver - this.silver;
		}
		
		return o.gold - this.gold;
	}
}

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Nation[] nation = new Nation[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int num = Integer.parseInt(st.nextToken());
			int gold = Integer.parseInt(st.nextToken());
			int silver = Integer.parseInt(st.nextToken());
			int bronze = Integer.parseInt(st.nextToken());
			
			nation[i] = new Nation(num, gold, silver, bronze);
		}
		
		Arrays.sort(nation);
		
		calculateRank(N, nation);
		
		for (int i = 0; i < N; i++) {
			if (nation[i].num == K) {
				System.out.println(nation[i].rank);
				break;
			}
		}
	}
	
	public static void calculateRank(int n, Nation[] nation) {
		for(int i = 1; i < n; i++) {
			if (nation[i].gold == nation[i - 1].gold && nation[i].silver == nation[i - 1].silver && nation[i].bronze == nation[i - 1].bronze) {
				// 금, 은, 동메달 수가 모두 같다면 두 나라의 등수를 똑같은 등수로 변경
				nation[i].rank = nation[i - 1].rank;
			} else {
				// 개수가 다르다면 현재 등수 + 1을 해줌
				nation[i].rank = i + 1;
			}
		}
	}

}
