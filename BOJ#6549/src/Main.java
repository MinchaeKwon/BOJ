/**
 * 6549 - 히스토그램에서 가장 큰 직사각형
 * https://www.acmicpc.net/problem/6549
 * 
 * @author Minchae Gwon
 * @date 2020.2.28
 * 
 * 스택 이용
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
			
			Stack<Integer> stack = new Stack<>(); //스택에 높이순으로 직사각형을 저장함
			long area = Integer.MIN_VALUE;
			
			for (int i = 0; i < n; i++) {
				/* 스택에서 꺼내는 히스토그램이 현재 히스토그램보다 왼쪽에 있는 것임
				 * 스택이 비어있지 않고 현재 히스토그램의 크기가 스택에서 꺼낸 히스토그램 크기보다 작을 동안 현재 히스토그램의 높이만큼 직사각형 높이를 구함
				 * -> 현재 스택에서 꺼낸 인덱스의 히스토그램(높이) * i(가로) */
				while (!stack.empty() && histogram[i] < histogram[stack.peek()] ) {
					long h = histogram[stack.pop()]; //넓이를 구할 직사각형의 높이
					int w = i; //넓이를 구할 직사각형 가로 -> 직사각형 가로 오른쪽 끝
					
					//그냥 i를 가로로 하면 제대로 된 직사각형의 넓이를 구할 수 없음 -> 직사각형 가로 왼쪽 끝을 고려해줘야함
					if (!stack.empty()) {
						w = i - stack.peek() - 1;
					}
					
					area = Math.max(area, w * h);
				}
				
				//histogram 배열에서 몇번째 인덱스를 꺼낼지를 정해야하므로 i를 스택에 넣음(왼쪽에 있는 히스토램을 꺼내기 위해 스택에 인덱스 집어넣음)
				stack.add(i);
			}
			
			//for문을 다 돌고 스택에 남은 히스토그램을 통해 직사각형 넓이를 구해줌
			while (!stack.empty()) {
				long h = histogram[stack.pop()]; //넓이를 구할 직사각형의 높이
				int w = n; //넓이를 구할 직사각형 가로
				
				if (!stack.empty()) {
					w = n - stack.peek() - 1;
				}
				
				area = Math.max(area, w * h);
			}
			
			System.out.println(area);
		}

	}

}
