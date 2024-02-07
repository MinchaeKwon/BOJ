/**
 * 3425 고스택
 * https://www.acmicpc.net/problem/3425
 * 
 * @author minchae
 * @date 2024. 2. 7.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {
	
	static int MAX = 1_000_000_000;
	
	static ArrayList<String> orderList = new ArrayList<>();
	static Stack<Long> s = new Stack<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		while (true) {
			orderList = new ArrayList<>();
			
			String input = br.readLine();
			
			if (input.equals("QUIT")) {
				break;
			}
			
			while (!input.equals("END")) {
				String[] str = input.split(" ");
				
				if (str.length == 1) {
					orderList.add(str[0]);
				} else {
					orderList.add(str[0]);
					orderList.add(str[1]);
				}
				
				input = br.readLine();
			}
			
			int N = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < N; i++) {
				long num = Long.parseLong(br.readLine());
				
				if (simulation(num)) {
					System.out.println(s.peek());
				} else {
					System.out.println("ERROR");
				}
			}
			
			br.readLine();
			System.out.println();
		}
		
	}
	
	private static boolean simulation(long num) {
		s = new Stack<>();
		
		s.add(num);
		
		for (int i = 0; i < orderList.size(); i++) {
			String order = orderList.get(i);
			
			if (order.equals("NUM")) {
				s.add(Long.parseLong(orderList.get(i + 1)));
				
				i++;	
			} else if (order.equals("POP")) {
				if (s.isEmpty()) {
					return false;
				}
				
				s.pop();
			} else if (order.equals("INV")) {
				if (s.isEmpty()) {
					return false;
				}
				
				s.push(s.pop() * -1);
			} else if (order.equals("DUP")) {
				if (s.isEmpty()) {
					return false;
				}
				
				s.push(s.peek());
			} else if (order.equals("SWP")) {
				if (s.size() < 2) {
					return false;
				}
				
				long n1 = s.pop();
				long n2 = s.pop();
				
				s.push(n1);
				s.push(n2);
			} else if (order.equals("ADD")) {
                if (s.size() < 2) {
					return false;
				}
				
				long n1 = s.pop();
				long n2 = s.pop();
				
				if (Math.abs(n1 + n2) > MAX) {
					return false;
				}
				
				s.push(n1 + n2);
			} else if (order.equals("SUB")) {
                if (s.size() < 2) {
					return false;
				}
				
				long n1 = s.pop();
				long n2 = s.pop();
				
				if (Math.abs(n2 - n1) > MAX) {
					return false;
				}
				
				s.push(n2 - n1);
			} else if (order.equals("MUL")) {
                if (s.size() < 2) {
					return false;
				}
				
				long n1 = s.pop();
				long n2 = s.pop();
				
				if (Math.abs(n1 * n2) > MAX) {
					return false;
				}
				
				s.push(n1 * n2);
			} else if (order.equals("DIV")) {
				if (s.size() < 2) {
					return false;
				}
				
				long n1 = s.pop();
				
				if (n1 == 0) {
					return false;
				}
				
				long n2 = s.pop();
				
				s.push(n2 / n1);
			} else if (order.equals("MOD")) {
				if (s.size() < 2) {
					return false;
				}
				
				long n1 = s.pop();
				
				if (n1 == 0) {
					return false;
				}
				
				long n2 = s.pop();
				
				s.push(n2 % n1);
			}
		}
		
		return s.size() == 1;
	}

}
