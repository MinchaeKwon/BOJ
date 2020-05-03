//오큰수
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Stack<Integer> stack = new Stack<>();
	
		int n = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		
		int[] arr = new int[n];
		int[] result = new int[n];
		
		for(int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(s[i]);
		
		for(int i = n - 1; i >= 0; i--) { //단순하게 배열의 마지막 값의 오큰수는 -1이라서 n-1부터 시작해봄
			while(!stack.isEmpty() && arr[i] >= stack.peek()) //arr에 있는 값보다 더 큰 값이 오큰수가 될 수 있으니까  arr보다 작은 수는 스택에서 pop해줘서 오큰수를 찾음
				stack.pop();
			
			if(stack.isEmpty()) //스택이 비었다는 것은 arr[i]값보다 큰 값이 없다는 것 -> -1을 넣어줌
				result[i] = -1;
			else //top이 오큰수가 돼서 배열에 넣어줌
				result[i] = stack.peek();
			
			stack.push(arr[i]); //스택에 arr값을 넣어줌
		}
		
		for(int i = 0; i < n; i++)
			bw.write(result[i] + " ");
		
		//버퍼를 잡아 놓았기 때문에 반드시 flush() / close() 를 반드시 호출해 주어 뒤처리를 해주어야합니다.
		bw.close();
	}

}