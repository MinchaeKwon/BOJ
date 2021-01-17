/**
 *
 * @author minchae
 * https://www.acmicpc.net/problem/10828
 * 스택
 *
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] stack = new int[n];
		int top = -1;
		
		for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			
			if(s.equals("push")) {
				int pn = Integer.parseInt(st.nextToken());
				top++;
				stack[top] = pn;
			}
			else if(s.equals("pop")) {
				if(top == -1)
					System.out.println(-1);
				else {
					System.out.println(stack[top]);
					top--;
				}
			}
			else if(s.equals("size"))
				System.out.println(top + 1);
			else if(s.equals("empty")) {
				if(top == -1)
					System.out.println(1);
				else
					System.out.println(0);
			}
			else if(s.equals("top")) {
				if(top == -1)
					System.out.println(-1);
				else
					System.out.println(stack[top]);
			}
		}
		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		
//		int n = Integer.parseInt(br.readLine());
//		Stack<Integer> stack = new Stack<>();
//		
//		for(int i = 0; i < n; i++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			String s = st.nextToken();
//			
//			switch (s) {
//			case "push":
//				int pn = Integer.parseInt(st.nextToken());
//				stack.push(pn);
//				
//				break;
//			case "pop":
//				if (stack.isEmpty()) {
//					System.out.println(-1);
//				} else {
//					System.out.println(stack.pop());
//				}
//				
//				break;
//			case "size":
//				System.out.println(stack.size());
//				
//				break;
//			case "top":
//				if (stack.isEmpty()) {
//					System.out.println(-1);
//				} else {
//					System.out.println(stack.peek());
//				}
//							
//				break;
//			case "empty":
//				if (stack.isEmpty()) {
//					System.out.println(1);
//				} else {
//					System.out.println(0);
//				}
//				
//				break;
//			}
//		}
		
	}

}
