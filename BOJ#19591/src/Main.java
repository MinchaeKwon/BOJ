/**
 * 19591 독특한 계산기
 * https://www.acmicpc.net/problem/19591
 * 
 * @author minchae
 * @date 2024. 2. 22.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	
	static Deque<Long> nums = new ArrayDeque<>();
	static Deque<Character> operators = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		int idx = 0;
		String num = "";
		
		// 첫 숫자가 음수인 경우 고려
		if (input.charAt(0) == '-') {
			idx++;
			num += "-";
		}
		
		boolean flag = false; // 숫자가 0으로 시작하는 경우를 고려하기 위해 사용
		
		for (int i = idx; i < input.length(); i++) {
			char c = input.charAt(i);
			
			if (c == '+' || c == '-' || c == '*' || c == '/') {
				if (flag) {
					nums.add(Long.valueOf(num));
				} else {
					nums.add((long) 0); // 0만 들어오는 경우도 있기 때문에 flag == false인 경우 0을 추가
				}
				
				operators.add(c);
				
				num = "";
				flag = false;
			} else {
				if (c != '0') {
					flag = true;
				}
				
				// 0 이외의 다른 숫자로 시작하면 붙여줌
				if (flag) {
					num += c;
				}
			}
		}
		
		if (!num.equals("-") && !num.equals("")) {
			nums.add(Long.valueOf(num));
		} else {
			nums.add((long) 0);
		}
		
		long answer = 0;

		while (operators.size() > 1) {
			char preOp = operators.getFirst();
			char lastOp = operators.getLast();
			
			long preNum1 = nums.removeFirst(); // 첫 번째 숫자
			long lastNum1 = nums.removeLast(); // 마지막 숫자
			
			long preNum2 = nums.getFirst(); // 두 번째 숫자
			long lastNum2 = nums.getLast(); // 마지막 숫자 전의 숫자
			
			if ((preOp == '*' || preOp == '/') && (lastOp == '+' || lastOp == '-')) {
				// 앞 선택
				selectFirst(lastNum1, calculate(preNum1, preNum2, preOp));
			} else if ((preOp == '+' || preOp == '-') && (lastOp == '*' || lastOp == '/')) {
				// 뒤 선택
				selectLast(preNum1, calculate(lastNum2, lastNum1, lastOp));
			} else {
				// 연산자의 우선순위가 같다면 해당 연산자를 계산했을 때 결과가 큰 것부터 계산
				// 계산했을 때 결과 값 또한 같다면 앞에 것을 먼저 계산
				
				long preResult = calculate(preNum1, preNum2, preOp);
				long lastResult = calculate(lastNum2, lastNum1, lastOp);
				
				if (preResult >= lastResult) {
					selectFirst(lastNum1, calculate(preNum1, preNum2, preOp));
				} else if (preResult < lastResult) {
					selectLast(preNum1, calculate(lastNum2, lastNum1, lastOp));
				}
			}
		}
		
		if (operators.size() == 1) {
			answer = calculate(nums.removeFirst(), nums.removeFirst(), operators.removeFirst());
		} else {
			answer = nums.removeFirst();
		}
		
		System.out.println(answer);
	}
	
	private static void selectFirst(long lastNum, long res) {
		operators.removeFirst();// 맨 앞 연산자 삭제
		
		nums.addLast(lastNum);// 처음에 제거한 lastNum1을 다시 추가
		nums.removeFirst(); // 계산된 두 번째 숫자인 preNum2를 제거
		nums.addFirst(res); // 계산한 값 추가
	}
	
	private static void selectLast(long preNum, long res) {
		operators.removeLast();// 맨 뒤 연산자 삭제
		
		nums.addFirst(preNum);// 처음에 제거한 preNum1을 다시 추가
		nums.removeLast(); // 계산된 두 번째 숫자인 lastNum2를 제거
		nums.addLast(res); // 계산한 값 추가	
	}
	
	private static long calculate(long a, long b, char op) {
		if (op == '+') {
			return a + b;
		} else if (op == '-') {
			return a - b;
		} else if (op == '*') {
			return a * b;
		} else {
			return a / b;
		}
	}

}
