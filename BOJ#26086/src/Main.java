/**
 * 26086 어려운 스케줄링
 * https://www.acmicpc.net/problem/21608
 * 
 * @author minchae
 * @date 2024. 10. 24.
 */

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] cmd = new int[Q]; // 명령 저장, -1 정렬, -2 순서 뒤집기, 나머지 추가될 작업 번호
		int lastIdx = -1;
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			
			int order = Integer.parseInt(st.nextToken());
			
			if (order == 0) {
				cmd[i] = Integer.parseInt(st.nextToken());
			} else if (order == 1) {
				cmd[i] = -1;
				lastIdx = i;
			} else {
				cmd[i] = -2;
			}
		}
		
		LinkedList<Integer> dq = new LinkedList<>();
		
		// 마지막 정렬이 일어나기 전까지는 그냥 저장
		for (int i = 0; i <= lastIdx; i++) {
			if (cmd[i] > 0) {
				dq.add(cmd[i]);	
			}
		}
		
		// 정렬 명령이 있는 경우에만 정렬하기
		if (lastIdx != -1) {
			Collections.sort(dq);
		}
		
		// 이후에는 순서 뒤집기 여부에 따라 번호 저장
		boolean reverse = false;
		
		for (int i = lastIdx + 1; i < Q; i++) {
			if (cmd[i] > 0) {
				if (!reverse) {
					dq.addFirst(cmd[i]);
				} else {
					dq.addLast(cmd[i]);
				}
			} else if (cmd[i] == -2) {
				reverse = !reverse; // 순서 뒤집음
			}
		}
		
		System.out.println(!reverse ? dq.get(k - 1) : dq.get(dq.size() - k));
	}

}
