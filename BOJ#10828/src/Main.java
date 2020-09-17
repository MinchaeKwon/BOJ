/**
 *
 * @author minchae
 * https://www.acmicpc.net/problem/10828
 * 스택
 *
 */

import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] stack = new int[n];
		int top = -1;
		String s;
		
		for(int i = 0; i < n; i++) {
			s = sc.next();
			
			if(s.equals("push")) {
				int pn = sc.nextInt();
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
		
		sc.close();
	}

}
