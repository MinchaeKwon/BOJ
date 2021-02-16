/**
 * 6549 - 히스토그램에서 가장 큰 직사각형
 * https://www.acmicpc.net/problem/6549
 * 
 * @author Minchae Gwon
 * @date 2020.2.16
 * 
 * 스택 이용함
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			
			//0이면 입력 종료
			if (n == 0) {
				break;
			}
			
			int[] histogram = new int[n];
			for (int i = 0; i < n; i++) {
				histogram[i] = Integer.parseInt(st.nextToken());
			}
			
			Stack<Integer> stack = new Stack<>();
			int area = Integer.MIN_VALUE;
			
			for (int i = 0; i < n; i++) {
				//스택이 비어있지 않고 현재 히스토그램의 크기가 스택에서 꺼낸 히스토그램 크기보다 작을 동안 현재 히스토그램의 높이만큼 직사각형 높이를 구함(현재 히스토그램 높이 * 스택에서 꺼낸 히스토그램 개수)
				while (!stack.empty() && histogram[i] < histogram[stack.peek()] ) {
					
				}
				
				//histogram 배열에서 몇번째 인덱스를 꺼낼지를 정해야하므로 i를 스택에 넣음
				stack.add(i);
			}
			
			
		}

	}

}
