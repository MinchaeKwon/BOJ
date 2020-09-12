
//ATM -> 최소 대기시간

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int pn = sc.nextInt();
		int[] time = new int[pn];

		for (int i = 0; i < time.length; i++)
			time[i] = sc.nextInt();

		Arrays.sort(time);

		int result = 0;
		int min = 0; // 한 사람당 기다리는 최소 시간
		for (int i = 0; i < time.length; i++) {
			min += time[i];
			result += min;
		}

		System.out.println(result);

		sc.close();

	}

}
