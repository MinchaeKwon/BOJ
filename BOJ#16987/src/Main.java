/**
 * 16987 계란으로 계란치기
 * https://www.acmicpc.net/problem/16987
 * 
 * @author minchae
 * @date 2024. 1. 9.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Egg {
	int durability;
	int weight;
	
	public Egg(int durability, int weight) {
		this.durability = durability;
		this.weight = weight;
	}
}

public class Main {
	
	static int N;
	static Egg[] eggs;
	
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		eggs = new Egg[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		simulation(0, 0);
		
		System.out.println(answer);
	}
	
	private static void simulation(int depth, int cnt) {
		// 마지막 계란까지 다 깼거나, 계란이 N - 1개 깨져서 더이상 깰 수 없는 경우 종료
		if (depth == N || cnt == N - 1) {
			answer = Math.max(answer, cnt);
			return;
		}
		
		if (eggs[depth].durability <= 0) { // 현재 손에 든 계란이 이미 깨져서 내구도가 0이하인 경우 깨지 않고 다음으로 넘어감
			simulation(depth + 1, cnt);
		} else {
			for (int i = 0; i < N; i++) {
				// 현재 손에 든 계란과 부딫히려는 계란이 똑같은 경우 다음으로 넘어감
				if (i == depth) {
					continue;
				}
				
				if (eggs[i].durability > 0) {
					// 부딫히는 계란 두 개의 내구도 감소시킴
					eggs[depth].durability -= eggs[i].weight;
					eggs[i].durability -= eggs[depth].weight;
					
					// 내구도가 0이하가 돼서 깨진 계란 개수 증가 시킴
					simulation(depth + 1, cnt + (eggs[depth].durability <= 0 ? 1 : 0) + (eggs[i].durability <= 0 ? 1 : 0));
					
					// 원복
					eggs[depth].durability += eggs[i].weight;
					eggs[i].durability += eggs[depth].weight;
				}
			}
		}
		
	}

}
