//9372 - 상근이의 여행

import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] result = new int[n];
		
		for (int i = 0; i < n; i++) {
			int country = sc.nextInt();
			int plane = sc.nextInt();
			
			for (int j = 0; j < plane; j++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
			}
			
			//모든 국가를 여행하기 위해 타야 하는 비행기 종류의 최소 개수는 '국가개수-1'이다
			result[i] = country - 1;
		}
		
		for (int plane : result) {
			System.out.println(plane);
		}
		
		sc.close();
	}

}
