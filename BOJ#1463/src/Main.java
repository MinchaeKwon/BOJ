//1로 만들기 -> 작은 것부터 구해나가는 방식(Bottom-Up)
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] dp = new int[n + 1];
		
		dp[0] = dp[1] = 0;
		
		//n-1 또는 n/2 또는 n/3 를 했을 때 가장 작은 값이 답 -> dp[n-1]+1, dp[n/2]+1, dp[n/3]+1 중 최소값을 찾는 것 -> dp[2]부터 하면 답 구할 수 있음
		for(int i = 2; i <= n; i++) { //0과 1은 어차피 연산 횟수가 0이기 때문에 2부터 시작함
			dp[i] = dp[i - 1] + 1; //연산 한 번 실행했으니까 +1 해줌 / 그냥 먼저 n-1한 것을 dp[i]에 넣어놓고 밑에 if문에서 최소값 비교하면 됨
			
			if(i % 2 == 0)
				dp[i] = Math.min(dp[i], dp[i / 2] + 1); //n-1한 것과 비교해서 최소값을 dp[i]에 넣음
			if(i % 3 == 0)
				dp[i] = Math.min(dp[i], dp[i / 3] + 1); //n-1한 것과 비교해서 최소값을 dp[i]에 넣음
		} // +1을 해주는 이유는 연산을 한 번 실행했기 때문에 +1을 해주는 것이다.
		
		System.out.println(dp[n]);
		
		sc.close();
	}

}
