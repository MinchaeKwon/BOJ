/**
 * 1525 퍼즐
 * https://www.acmicpc.net/problem/1525
 * 
 * @author minchae
 * @date 2024. 10. 14.
 * 
 * 맵을 사용해 최소 이동 횟수 구함
 * 현재 맵의 상태 key, 이동 횟수 value
 * */

import java.io.*;
import java.util.*;

public class Main {
	

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static HashMap<String, Integer> hm;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		hm = new HashMap<>();
		
		StringBuilder start = new StringBuilder();
		
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < 3; j++) {
				start.append(st.nextToken());
			}
		}
		
		System.out.println(bfs(start.toString()));
	}
	
	private static int bfs(String start) {
		Queue<String> q = new LinkedList<>();
		
		q.add(start);
		hm.put(start, 0);
		
		while (!q.isEmpty()) {
			String cur = q.poll();
			
			// 0의 위치 찾고 좌표 구함
			int curIdx = cur.indexOf('0');
			int x = curIdx / 3;
			int y = curIdx % 3;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (!isRange(nx, ny)) {
					continue;
				}
				
				int nextIdx = nx * 3 + ny; // 0을 넣을 인덱스 위치
				char tmp = cur.charAt(nextIdx); // 바꿀 위치에 있는 문자
				
				StringBuilder next = new StringBuilder(cur);
				
				next.setCharAt(nextIdx, '0'); // 0을 먼저 넣음
				next.setCharAt(curIdx, tmp); // 현재 위치에 바꿀 문자를 넣음
				
				// 처음 방문하는 경우
				if (!hm.containsKey(next.toString())) {
					q.add(next.toString());
					hm.put(next.toString(), hm.get(cur) + 1); // 이동 횟수 증가
				}
			}
		}
		
		// 최소 이동 횟수 반환
		return hm.containsKey("123456780") ? hm.get("123456780") : -1;
	}
	
	private static boolean isRange(int x, int y) {
		return x >= 0 && x < 3 && y >= 0 && y < 3;
	}

}