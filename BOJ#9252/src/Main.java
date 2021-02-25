/**
 * 9252 - LCS2
 * https://www.acmicpc.net/problem/9252
 * 
 * @author Minchae Gwon
 * @date 2021.2.26
 * 
 * 문제
 * LCS(Longest Common Subsequence, 최장 공통 부분 수열)문제는 두 수열이 주어졌을 때, 모두의 부분 수열이 되는 수열 중 가장 긴 것을 찾는 문제이다.
 * 예를 들어, ACAYKP와 CAPCAK의 LCS는 ACAK가 된다.
 * 
 * 입력
 * 첫째 줄과 둘째 줄에 두 문자열이 주어진다. 문자열은 알파벳 대문자로만 이루어져 있으며, 최대 1000글자로 이루어져 있다.
 * 
 * 출력
 * 첫째 줄에 입력으로 주어진 두 문자열의 LCS의 길이를, 둘째 줄에 LCS를 출력한다.
 * LCS가 여러 가지인 경우에는 아무거나 출력하고, LCS의 길이가 0인 경우에는 둘째 줄을 출력하지 않는다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s1 = br.readLine();
		String s2 = br.readLine();
		
		int[][] dp = new int[s1.length() + 1][s2.length() + 1];
		String[][] lcs = new String[s1.length() + 1][s2.length() + 1];
		
		//초기화 해주지 않으면 null이 들어감
		for (String[] init : lcs) {
			Arrays.fill(init, "");
		}
		
		//LCS 길이와 LCS 구하기
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				//인덱스 0부터 저장되어있기 때문에 -1을 해줌
				if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
					lcs[i][j] += lcs[i - 1][j - 1] + s1.charAt(i - 1);
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
					
					if (dp[i - 1][j] > dp[i][j - 1]) {
						lcs[i][j] += lcs[i - 1][j];
					} else {
						lcs[i][j] += lcs[i][j - 1];
					}
				}
			}
		}
		
		System.out.println(dp[s1.length()][s2.length()]);
		System.out.println(lcs[s1.length()][s2.length()]);

	}

}
