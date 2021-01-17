//단어 뒤집기
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < T; i++) {
			String str = br.readLine();
			Stack<Character> stack = new Stack<>();
			
			for(int j = 0; j < str.length(); j++) {
				char s = str.charAt(j);
				
				if(s == ' ') {
					while(!stack.empty()) {
						bw.write(stack.pop());	
					}
					bw.write(" ");
				}
				else {
					stack.push(s);	
				}
			}
			while(!stack.empty()) {
				bw.write(stack.pop());	
			}
			
			bw.write("\n");
		}
		
		bw.flush();
		bw.close();
		
	}

}
