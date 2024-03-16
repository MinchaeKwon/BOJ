/**
 * 16506 CPU
 * https://www.acmicpc.net/problem/16506
 * 
 * @author minchae
 * @date 2024. 3. 17.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	
	static HashMap<String, String> hm = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		
		makeMap();
		
		StringBuilder sb = new StringBuilder();
		
		while (N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			String code = st.nextToken();
			int rD = Integer.parseInt(st.nextToken());
			int rA = Integer.parseInt(st.nextToken());
			int rBC = Integer.parseInt(st.nextToken());
			
			// 0 ~ 4
			if (code.charAt(code.length() - 1) == 'C') {
				sb.append(hm.get(code.substring(0, code.length() - 1)) + "1");
			} else {
				sb.append(hm.get(code) + "0");
			}
			
			// 5
			sb.append("0");
			
			// 6 ~ 8
			String binary = Integer.toBinaryString(rD);
			sb.append(add(binary, 3));
			
			// 9 ~ 11
			binary = Integer.toBinaryString(rA);
			sb.append(add(binary, 3));
			
			// 12 ~ 15
			binary = Integer.toBinaryString(rBC);
			
			if (code.charAt(code.length() - 1) == 'C') {
				sb.append(add(binary, 4));
			} else {
				sb.append(add(binary, 3) + "0");
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void makeMap() {
		hm.put("ADD", "0000");
        hm.put("SUB", "0001");
        hm.put("MOV", "0010");
        hm.put("AND", "0011");
        hm.put("OR", "0100");
        hm.put("NOT", "0101");
        hm.put("MULT", "0110");
        hm.put("LSFTL", "0111");
        hm.put("LSFTR", "1000");
        hm.put("ASFTR", "1001");
        hm.put("RL", "1010");
        hm.put("RR", "1011");
	}
	
	// 자릿수에 맞게 0 추가
	private static String add(String str, int limit) {
        if (limit == str.length()) {
            return str;
        }
        
        return add("0" + str, limit);
    }

}
