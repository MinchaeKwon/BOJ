/**
 * 5430 AC
 * https://www.acmicpc.net/problem/5430
 * 
 * @author minchae
 * @date 2024. 2. 23.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

	static char[] order;
	static Deque<String> dq;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		
		while (T-- > 0) {
			order = br.readLine().toCharArray();
			
			int n = Integer.parseInt(br.readLine());
			
			String input = br.readLine();
			String[] nums = input.substring(1, input.length() - 1).split(",");
			
			dq = new ArrayDeque<>();
			
			for (String num : nums) {
				if (!num.equals("")) {
					dq.add(num);	
				}
			}
			
			System.out.println(simulation());
		}
		
	}
	
	private static String simulation() {
		boolean reverse = false;
		
		for (char c : order) {
			if (c == 'R') {
				reverse = !reverse;
			} else {
				if (dq.isEmpty()) {
					return "error";
				}
				
				if (reverse) {
					dq.pollLast();
				} else {
					dq.pollFirst();
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		
		while (!dq.isEmpty()) {
			sb.append(reverse ? dq.pollLast() : dq.pollFirst());
			
			if (dq.size() > 0) {
				sb.append(",");
			}
		}
		
		sb.append("]");

		return sb.toString();
	}

}
