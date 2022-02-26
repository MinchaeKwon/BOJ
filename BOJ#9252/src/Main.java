/**
 * 9252 - LCS2
 * https://www.acmicpc.net/problem/9252
 * 
 * @date 2021. 2. 26. / 수정 2022. 2. 27.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        char[] c1 = br.readLine().toCharArray();
        char[] c2 = br.readLine().toCharArray();
 
        int[][] dp = new int[c1.length + 1][c2.length + 1];
 
        for(int i = 1; i <= c1.length; i++) {
        	for(int j = 1; j <= c2.length; j++) {
        		dp[i][j] = c1[i - 1] == c2[j - 1] ? dp[i - 1][j - 1] + 1 : Math.max(dp[i][j - 1], dp[i - 1][j]);
        	}
        }
 
        StringBuilder result = new StringBuilder();
        
        int i = c1.length;
        int j = c2.length;
 
        // LCS 구하기
        while(i >= 1 && j >= 1) {
            if (dp[i][j] == dp[i - 1][j]) {
            	i--;	
            } else if (dp[i][j] == dp[i][j - 1]) {
            	j--;
            } else {
                result.insert(0, c2[j - 1]);
                i--;
                j--;
            }
        }
        
        System.out.println(result.length());
        System.out.println(result);
		

        // 수정 전 코드 - 메모리 초과
//		String s1 = br.readLine();
//		String s2 = br.readLine();
//		
//		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
//		String[][] lcs = new String[s1.length() + 1][s2.length() + 1];
//		
//		// 초기화 해주지 않으면 null이 들어감
//		for (String[] init : lcs) {
//			Arrays.fill(init, "");
//		}
//		
//		// LCS 길이와 LCS 구하기
//		for (int i = 1; i <= s1.length(); i++) {
//			for (int j = 1; j <= s2.length(); j++) {
//				// 인덱스 0부터 저장되어있기 때문에 -1을 해줌
//				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
//					dp[i][j] = dp[i - 1][j - 1] + 1;
//					lcs[i][j] += lcs[i - 1][j - 1] + s1.charAt(i - 1);
//				} else {
//					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
//					
//					if (dp[i - 1][j] > dp[i][j - 1]) {
//						lcs[i][j] += lcs[i - 1][j];
//					}
//					else {
//						lcs[i][j] += lcs[i][j - 1];
//					}
//				}
//			}
//		}
//		
//		System.out.println(dp[s1.length()][s2.length()]);
//		System.out.println(lcs[s1.length()][s2.length()]);
	}

}
