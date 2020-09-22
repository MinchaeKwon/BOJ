//스택 수열
import java.util.*;

public class Main {
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
     
      int n = sc.nextInt();
      int[] nArr = new int[n];
      
      StringBuilder sb = new StringBuilder();
      Stack<Integer> stack = new Stack<>();
      
      int index = 1;
      
       for(int i = 0; i < n; i++) {
          nArr[i] = sc.nextInt();
          
          while(index <= nArr[i]) {
             stack.push(index);
             index++;
             sb.append("+ \n");
          }
          
          if(stack.empty() == false) {
             while(stack.peek() == nArr[i]) {
                stack.pop();
                sb.append("- \n");
                
                if(stack.empty())
                   break;
             }
          }
       }
       
       if(stack.empty())
          System.out.println(sb.toString());
       else
          System.out.println("NO");
       
       sc.close();
   }

}