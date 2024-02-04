/**
 * 2608 로마 숫자
 * https://www.acmicpc.net/problem/2608
 * 
 * @author minchae
 * @date 2024. 2. 5.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

public class Main {
	
	static HashMap<String , Integer> hm = new HashMap<>();
	
	static int answer1 = 0;
	static String answer2 = "";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] input1 = br.readLine().toCharArray();
		char[] input2 = br.readLine().toCharArray();
		
		makeMap();
		
		makeArabic(input1);
		makeArabic(input2);
		
		makeRoman(answer1);
		
		System.out.println(answer1);
		System.out.println(answer2);
	}
	
	private static void makeMap() {
		hm.put("I", 1);
		hm.put("V", 5);
		hm.put("X", 10);
		hm.put("L", 50);
		hm.put("C", 100);
		hm.put("D", 500);
		hm.put("M", 1000);
		hm.put("IV", 4);
		hm.put("IX", 9);
		hm.put("XL", 40);
		hm.put("XC", 90);
		hm.put("CD", 400);
		hm.put("CM", 900);
	}
	
	// 로마 숫자 -> 아라비아 숫자
	private static void makeArabic(char[] arr) {
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			
			// 마지막 문자가 아니고 뒤에 다른 문자가 있을수도 있는 문자인 경우 (IV, IX, XL, XC, CD, CM)
			if (i != arr.length - 1 && (c == 'I' || c == 'X' || c == 'C')) {
				String key = String.valueOf(arr[i]) + String.valueOf(arr[i + 1]);
				
				if (hm.containsKey(key)) {
					answer1 += hm.get(key);
					i++; // i + 1번까지 더했기 때문에 증가시켜줌
					
					continue;
				}
			}
			
			answer1 += hm.get(String.valueOf(arr[i]));
		}
	}
	
	// 아라비아 숫자 -> 로마 숫자
	private static void makeRoman(int n) {
		// 내림차순 정렬
		ArrayList<Entry<String, Integer>> entryList = new ArrayList<>(hm.entrySet());
		entryList.sort(Entry.comparingByValue(Collections.reverseOrder()));
		
		// 0이 될 때까지 진행
		while (n != 0) {
			int quotient = 0;
			
			// 큰 숫자를 가지는 문자부터 확인
			for(Entry<String, Integer> e : entryList){
			    quotient = n / e.getValue(); // 몫 구하기

				if (quotient > 0) {
					// 해당 값을 가지는 키를 더함
					for (int i = 0; i < quotient; i++) {
						answer2 += e.getKey();
					}
					
					n %= e.getValue(); // 나머지 숫자 구하고 해당 과정 반복함
					
					break; // 몫을 구하고 문자를 더한 경우 for문 종료 -> 다음 자릿수로 넘어감
				}
			}
		}
	}

}
