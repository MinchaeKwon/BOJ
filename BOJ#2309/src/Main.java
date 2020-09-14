//일곱 난쟁이

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int[] n = new int[9];
		int sum = 0;
		boolean check = false;
		
		for(int i = 0; i < n.length; i++) { //9명 키 입력
			n[i] = sc.nextInt();
			sum += n[i]; //일단 9명의 키를 다 더함
		}
		
		for(int i = 0; i < n.length; i++) {
			if(check == false) {
				for(int j = 0; j < n.length; j++) {
					if(i != j) { //i, j가 같은 것은 같은 사람이기 때문에 이를 제외하고 실행
						if(sum - n[i] - n[j] == 100) { //만약 두사람의 키를 뺐을 때 100이 나오면 난쟁이 7명을 찾은 것
							n[i] = 0;
							n[j] = 0;
							check = true;
							break;
						}
					}
				}
			}
		}
		
		Arrays.sort(n);
		
		for(int i = 0; i < n.length; i++)
			if(n[i] != 0)
				System.out.println(n[i]);
		
		sc.close();
	}

}
