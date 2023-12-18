/**
 * 1966 프린터 큐
 * https://www.acmicpc.net/problem/1966
 * 
 * @author minchae
 * @date 2023. 12. 18.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			Queue<int[]> q = new LinkedList<>(); // 문서 인덱스와 중요도 저장
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; i++) {
				q.add(new int[] {i, Integer.parseInt(st.nextToken())});
			}
			
			int answer = 0; // 몇 번째로 인쇄되었는지 확인
			
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				
				boolean high = false;
				
				for (int[] doc : q) {
					// 현재 문서 뒤에 중요도가 높은 문서가 있는 경우
					if (cur[1] < doc[1]) {
						high = true;
						break;
					}
				}
				
				if (high) {
					q.add(cur);
				} else {
					answer++;
					
					// 인쇄된 문서의 인덱스가 몇 번째로 인쇄되었는지 궁금한 문서의 인덱스와 일치할 경우
					if (cur[0] == M) {
						break;
					}
				}
			}
			
			System.out.println(answer);
		}

	}

}
