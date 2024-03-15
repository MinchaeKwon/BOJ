/**
 * 3568 iSharp
 * https://www.acmicpc.net/problem/3568
 * 
 * @author minchae
 * @date 2024. 3. 15.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] input = br.readLine().replace(",", "").replace(";", "").split(" ");
		
		for (int i = 1; i < input.length; i++) {
			String result = getResult(input[i]);
			System.out.println(input[0] + result);
		}

	}
	
	private static String getResult(String str) {
		StringBuilder op = new StringBuilder();
		StringBuilder variable = new StringBuilder();
		
		for (int i = str.length() - 1; i >= 0; i--) {
			char c = str.charAt(i);
			
			if (c == '*' || c == '&') {
				op.append(c);
			} else if (c == '[') {
				op.append("]");
			} else if (c == ']') {
				op.append("[");
			} else {
				variable.append(c);
			}
		}
		
		return op.toString() + " " + variable.reverse() + ";";
	}

}
