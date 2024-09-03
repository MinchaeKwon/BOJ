/**
 * 13975 파일 합치기 3
 * https://www.acmicpc.net/problem/13975
 * 
 * @author minchae
 * @date 2024. 9. 4.
 */

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			int K = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 오름차순 정렬 -> 크기가 작은 파일부터 더해감
			PriorityQueue<Long> pq = new PriorityQueue<>();
			
			for (int i = 0; i < K; i++) {
				pq.add(Long.parseLong(st.nextToken()));
			}
			
			long answer = 0; // 파일을 더하다보면 int 범위 넘어갈 수 있음
			
			// 파일이 두 개 이상 있을 때만 진행
			while (pq.size() > 1) {
				// 가장 작은 두 수를 뺌
				long n1 = pq.poll();
				long n2 = pq.poll();
				
				answer += n1 + n2; // 두 파일을 합치는데 필요한 비용 추가
				
				pq.add(n1 + n2); // 두 파일을 더한 값을 추가
			}
			
			System.out.println(answer);
		}

	}

}
