/**
 * 26086 어려운 스케줄링
 * https://www.acmicpc.net/problem/21608
 * 
 * @author minchae
 * @date 2024. 2. 26.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Deque<Integer> dq = new ArrayDeque<>();
		
		boolean reverse = false;
		
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int order = Integer.parseInt(st.nextToken());
			
			switch (order) {
			case 0:
				int num = Integer.parseInt(st.nextToken());
				
				if (reverse) {
					dq.addLast(num);
				} else {
					dq.addFirst(num);
				}
				
				break;
			case 1:
				ArrayList<Integer> list = new ArrayList<>(dq);
				
				Collections.sort(list, Collections.reverseOrder());
				
				dq.clear();
				dq.addAll(list);
				
				break;
			case 2:
				reverse = !reverse;
				break;
			}
		}
		
		while (k-- > 1) {
			if (reverse) {
				dq.pollLast();
			} else {
				dq.pollFirst();
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		bw.write((reverse ? dq.pollLast() : dq.pollFirst()) + "\n");

		bw.flush(); 
		bw.close();
	}

}
