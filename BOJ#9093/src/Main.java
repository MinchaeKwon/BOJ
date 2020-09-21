//단어 뒤집기
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		sc.nextLine(); /*nextInt()메소드를 실행 할 때 숫자를 콘솔에 입력하고 엔터를 누를때 입력된 값을 리턴시켰지만 Enter값은 그대로 남아있다.
		nextLine() 메소드는 Enter값을 기준으로 메소드를 종료시키기 때문에 nextLine()메소드가 실행될 때 남아있는 Enter값을 그대로 읽어 바로 종료된 것이다.
                 그래서 정수를  입력하고 그다음 문자를 입력하려고 할 때 next() 메소드를 사용하거나 nextLine()메소드를 한번더 써줘서 enter값을 없애줘야한다.*/
		
		for(int i = 0; i < T; i++) {
			String str = sc.nextLine();
			Stack<Character> stack = new Stack<>();
			
			for(int j = 0; j < str.length(); j++) {
				char s = str.charAt(j);
				
				if(s == ' ') {
					while(stack.empty() == false)
						System.out.print(stack.pop());
					System.out.print(" ");
				}
				else
					stack.push(s);
			}
			while(stack.empty() == false)
				System.out.print(stack.pop());
			
			System.out.println();
		}
		
		sc.close();
		
	}

}
