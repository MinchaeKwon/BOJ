/**
 * 1333 부재중 전화
 * https://www.acmicpc.net/problem/1333
 * 
 * @author minchae
 * @date 2024. 9. 2
 * */

import java.util.*;
import java.io.*;

public class Main {

	static int N, L, D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		int time = 0;
		int cur = 0;

		for (int i = 0; i < N; i++) {
			time += L; // 노래 길이만큼 시간 추가
			
			// 현재 시간이 노래가 재생된 시간보다 작으면 D추가
			while (cur < time) {
				cur += D;
			}
			
			// 전화벨이 울리는 시간 사이에 현재 시간이 있는 경우
			if (time <= cur && time + 5 > cur) {
				break;
			}
			
			// 없으면 나머지 5초 증가
			time += 5;
		}

		System.out.println(cur);
	}

}
