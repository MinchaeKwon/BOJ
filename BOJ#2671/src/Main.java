/**
 * 2671 잠수함식별
 * https://www.acmicpc.net/problem/2671
 * 
 * @author minchae
 * @date 2021. 7. 8.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String sound = br.readLine();
		
		// +는 앞의 문자가 1개 이상 있다는 것
		// 문제에서  특정한 소리의 반복은 ~로 표시하는데 이것이 한 번 이상 반복되는 것이므로 +를 사용
		String pattern = "(100+1+|01)+";
//		String pattern = "^(100+1+|01)+$"; // 문자열의 시작을 나타내는 ^와 종료를 나타내는 $를 사용
		
		System.out.println(sound.matches(pattern) ? "SUBMARINE" : "NOISE");
	}

}
