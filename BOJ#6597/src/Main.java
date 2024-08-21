/**
 * 6597 트리 복구
 * https://www.acmicpc.net/problem/6597
 * 
 * @author minchae
 * @date 2024. 8. 22.
 */

import java.util.*;
import java.io.*;

// 루트를 기준으로 왼쪽, 오른쪽 나눠서 재귀 호출
public class Main {
	
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			while (true) {
				String input = br.readLine();
				
				if (input.isBlank()) {
					break;
				}
				
				StringTokenizer st = new StringTokenizer(input);
				
				String preOrder = st.nextToken();
				String inOrder = st.nextToken();
				
				sb = new StringBuilder();
				
				makePostOrder(preOrder, inOrder);
				
				System.out.println(sb.toString());
			}
		} catch (Exception e) {
			
		}
	}
	
	private static void makePostOrder(String pre, String in) {
		//비어 있는 경우 종료
		if (pre.length() == 0) {
			return;
		}
		
		int size = pre.length();
		char root = pre.charAt(0); // 전위 순회의 첫 번째는 항상 트리의 루트
		
		// 중위 순회에서 루트 인덱스 찾기
		// 전위, 중위 left만큼 자르면 각각 똑같은 서브트리만큼 남게 됨
		int left = in.indexOf(root); // 왼쪽 서브트리 개수
		
		makePostOrder(pre.substring(1, left + 1), in.substring(0, left));
		makePostOrder(pre.substring(left + 1, size), in.substring(left + 1, size));
	
		sb.append(root); // 후위순회 결과 저장
	}

}
