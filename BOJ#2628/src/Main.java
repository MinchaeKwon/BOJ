/**
 * 2628 종이 자르기
 * https://www.acmicpc.net/problem/2628
 * 
 * @author minchae
 * @date 2022. 4. 21.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		int cnt = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> widthList = new ArrayList<>();
		ArrayList<Integer> heightList = new ArrayList<>();
		
		widthList.add(0);
		widthList.add(h);
		
		heightList.add(0);
		heightList.add(w);
		
		for (int i = 0; i < cnt; i++) {
			st = new StringTokenizer(br.readLine());
			
			int type = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			if (type == 0) {
				widthList.add(num);
			} else {
				heightList.add(num);
			}
		}
		
		Collections.sort(widthList);
		Collections.sort(heightList);
		
		int result = 0;
		
		// 종이 자르고 넓이 구함
		for (int i = 1; i < widthList.size(); i++) {
			int width = widthList.get(i) - widthList.get(i - 1);
			
			for (int j = 1; j < heightList.size(); j++) {
				int height = heightList.get(j) - heightList.get(j - 1);
				
				int area = width * height;
				result = Math.max(result, area);
				
			}
		}

		System.out.println(result);
	}

}
