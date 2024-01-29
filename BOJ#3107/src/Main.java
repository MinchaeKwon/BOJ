/**
 * 3107 IPv6
 * https://www.acmicpc.net/problem/3107
 * 
 * @author minchae
 * @date 2024. 1. 29.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		
		// 0으로만 이루어진 그룹이 있을 경우 구분하기 위해 다른 문자열을 넣음
		if (input.contains("::")) {
			input = input.replace("::", ":zero:");
		}
		
		String[] arr = input.split(":");
		ArrayList<String> ipv6 = new ArrayList<>();
		
		for (String str : arr) {
			// ::이 맨 앞에 있을 경우 배열 첫 번째는 비어있기 때문에 다음으로 넘어감
			if (str.isEmpty()) {
				continue;
			}
			
			// 길이가 4미만일 경우 앞에 0을 붙임
			while (str.length() < 4) {
				str = "0" + str;
			}
			
			ipv6.add(str);
		}
		
		String[] answer = new String[8];
		
		int idx = 0;
		int zeroCnt = 8 - ipv6.size() + 1; // 0으로만 이루어진 그룹 개수 (zero가 포함되어 있기 때문에 +1을 해줌)
		
		for (String str : ipv6) {
			if (str.equals("zero")) {
				// 0으로 이루어진 그룹 개수만큼 0000을 붙여줌
				while (zeroCnt-- > 0) {
					answer[idx++] = "0000";
				}
			} else {
				answer[idx++] = str;
			}
		}
		
		System.out.println(String.join(":", answer));

	}

}
