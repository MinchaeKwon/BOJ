//쇠막대기
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Stack<Character> stack = new Stack<>();
		String s = sc.next();
		int result = 0;
		
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '(')
				stack.push(s.charAt(i));
			else if(s.charAt(i) == ')') {
				if(s.charAt(i - 1) == '(') { //레이저일때
					stack.pop(); //레이저의 시작
					result += stack.size(); //레이저로 자른 조각을 더함
				}
				else {
					stack.pop(); //막대기의 시작을 뺌
					result += 1; //마지막일때는 1를 더함(2번 자르면 3개가 나오기 때문)
				}
			}
		}
		
		System.out.println(result);
		
		sc.close();
	}

}
