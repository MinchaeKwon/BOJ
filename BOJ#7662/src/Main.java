/**
 * 7662 이중 우선순위 큐
 * https://www.acmicpc.net/problem/7662
 * 
 * @author minchae
 * @date 2021. 4. 6
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < T; i++) {
			int k = Integer.parseInt(br.readLine());
			
			TreeMap<Integer, Integer> tm = new TreeMap<>(); // <숫자, 해당 숫자의 개수>
			
			for (int j = 0; j < k; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				String calc = st.nextToken();
				int n = Integer.parseInt(st.nextToken());
				
				if (calc.equals("I")) {
					// 넣으려는 숫자가 하나도 없을 경우 1을 넣고 여러 개 있는 경우에는 해당 숫자를 key로 가지는 value를 가져와서 1을 더해줌(개수를 더해줌)
//					if (!tm.containsKey(n)) {
//						tm.put(n, 1);
//					}
//					else {
//						tm.put(n, tm.get(n) + 1);
//					}
					
					tm.put(n, tm.getOrDefault(n, 0) + 1);
				}
				else if (calc.equals("D") && !tm.isEmpty()) {
					if (n == -1) { // 최솟값 삭제
						int min = tm.firstKey();
						
						if (tm.get(min) == 1) { // 최솟값 숫자가 한개만 있을 경우 바로 삭제
							tm.remove(min);
						}
						else { //여러 개 있을 경우 개수를 한개 줄여줌
							tm.put(min, tm.get(min) - 1);
						}
					}
					else { // 최댓값 삭제 -> n이 1일 경우
						int max = tm.lastKey();
						
						if (tm.get(max) == 1) { // 최댓값 숫자가 한개만 있을 경우 바로 삭제
							tm.remove(max);
						}
						else { //여러 개 있을 경우 개수를 한개 줄여줌
							tm.put(max, tm.get(max) - 1);
						}
					}
				}
				
			}
			
			if (tm.isEmpty()) {
				System.out.println("EMPTY");
			}
			else {
				System.out.println(tm.lastKey() + " " + tm.firstKey());
			}
			
		}

	}

}
