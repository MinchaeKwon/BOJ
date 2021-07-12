/**
 * 1013 Contact
 * https://www.acmicpc.net/problem/1013
 * 
 * @author minchae
 * @date 2021. 7. 12.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			String sound = br.readLine();
			
			// +는 앞의 문자가 1개 이상 있다는 것
			String pattern = "(100+1+|01)+";
			
			System.out.println(sound.matches(pattern) ? "YES" : "NO");	
		}
	}

}