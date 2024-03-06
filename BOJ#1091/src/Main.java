/**
 * 1091 카드 섞기
 * https://www.acmicpc.net/problem/1091
 * 
 * @author minchae
 * @date 2024. 3. 6.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] P, S;
	static int[] card; // 사용자 순서 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		P = new int[N];
		S = new int[N];
		card = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
			card[i] = i % 3;
		}
		
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;

		while (!Arrays.equals(card, P)) {
			int[] tmp = card.clone();
			
			// 카드 섞기
			for (int i = 0; i < N; i++) {
				card[i] = tmp[S[i]];
			}
			
			cnt++;
			
			boolean inf = true;
			
			for (int i = 0; i < N; i++) {
				if (card[i] != i % 3) {
					inf = false;
				}
			}
			
			// 섞었을 때 초기화 상태가 되는 경우는 아무리 섞어도 원하는 결과를 얻을 수 없음
			if (inf)  {
				cnt = -1;
				break;
			}
		}
		
		System.out.println(cnt);
	}

}