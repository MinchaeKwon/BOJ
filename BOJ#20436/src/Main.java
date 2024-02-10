/**
 * 20436 ZOAC 3
 * https://www.acmicpc.net/problem/20436
 * 
 * @author minchae
 * @date 2024. 2. 10.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	
	static class Pair {
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static HashMap<Character, Pair> consonant = new HashMap<>(); // 자음 위치 저장
	static HashMap<Character, Pair> vowel = new HashMap<>(); // 모음 위치 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		makeMap();
		
		String input = br.readLine();
		
		Pair startL = consonant.get(input.charAt(0));
		Pair startR = vowel.get(input.charAt(2));
		
		char[] str = br.readLine().toCharArray();
		
		int answer = 0;
		
		for (char c : str) {
			if (consonant.containsKey(c)) { // 자음에 포함되는 경우
				Pair end = consonant.get(c);
				
				answer += Math.abs(startL.x - end.x) + Math.abs(startL.y - end.y) + 1; // 누르는 시간이 1초이므로 +1을 해줌
				
				startL = end;
			} else { // 모음에 포함되는 경우
				Pair end = vowel.get(c);
				
				answer += Math.abs(startR.x - end.x) + Math.abs(startR.y - end.y) + 1; // 누르는 시간이 1초이므로 +1을 해줌
				
				startR = end;
			}
		}
		
		System.out.println(answer);
	}
	
	// 자음, 모음 위치 저장
	private static void makeMap() {
		consonant.put('q', new Pair(0, 0));
		consonant.put('w', new Pair(0, 1));
		consonant.put('e', new Pair(0, 2));
		consonant.put('r', new Pair(0, 3));
		consonant.put('t', new Pair(0, 4));
		
		vowel.put('y', new Pair(0, 5));
		vowel.put('u', new Pair(0, 6));
		vowel.put('i', new Pair(0, 7));
		vowel.put('o', new Pair(0, 8));
		vowel.put('p', new Pair(0, 9));
		
		consonant.put('a', new Pair(1, 0));
		consonant.put('s', new Pair(1, 1));
		consonant.put('d', new Pair(1, 2));
		consonant.put('f', new Pair(1, 3));
		consonant.put('g', new Pair(1, 4));
		
		vowel.put('h', new Pair(1, 5));
		vowel.put('j', new Pair(1, 6));
		vowel.put('k', new Pair(1, 7));
		vowel.put('l', new Pair(1, 8));
		
		consonant.put('z', new Pair(2, 0));
		consonant.put('x', new Pair(2, 1));
		consonant.put('c', new Pair(2, 2));
		consonant.put('v', new Pair(2, 3));
		
		vowel.put('b', new Pair(2, 4));
		vowel.put('n', new Pair(2, 5));
		vowel.put('m', new Pair(2, 6));
	}
}
