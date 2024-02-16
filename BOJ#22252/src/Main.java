/**
 * 22252 정보 상인 호석
 * https://www.acmicpc.net/problem/22252
 * 
 * @author minchae
 * @date 2024. 2. 16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int Q = Integer.parseInt(br.readLine());
		
		HashMap<String, PriorityQueue<Integer>> hm = new HashMap<>();
		
		long answer = 0;
		
		while (Q-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int type = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			int cnt = Integer.parseInt(st.nextToken());
			
			if (type == 1) {
				if (!hm.containsKey(name)) {
					hm.put(name, new PriorityQueue<>(Collections.reverseOrder())); // 내림차순으로 정렬
				}
				
				// 정보 저장
				for (int i = 0; i < cnt; i++) {
					hm.get(name).add(Integer.parseInt(st.nextToken()));
				}
			} else {
				// 정보를 제공한 고릴라가 없는 경우 다음 쿼리로 넘어감
				if (!hm.containsKey(name)) {
					continue;
				}
				
				while (hm.get(name).size() > 0 && cnt-- > 0) {
					answer += hm.get(name).poll();
				}
			}
		}
		
		System.out.println(answer);
	}

}
