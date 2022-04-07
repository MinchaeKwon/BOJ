/**
 * 2469 사다리 타기
 * https://www.acmicpc.net/problem/2469
 * 
 * @author minchae
 * @date 2022. 4. 7.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        char[] startPerson = new char[k];
        for(int i = 0; i < k; i++) {
            startPerson[i] = (char) ('A' + i);
        }
        
        char[] destPerson = br.readLine().toCharArray();

        char[][] map = new char[n][k - 1];
        int questionIdx = 0; // '?'가 나오는 행
        
        for(int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();

            if (map[i][0] == '?') {
            	questionIdx = i;
            }
        }

        // 위에서부터 '?'행이 나오기 전까지 진행
        for (int i = 0; i < questionIdx; i++) {
            for (int j = 0; j < k - 1; j++) {
            	// 막대가 있는 경우 swap
                if (map[i][j] == '-') {
                    char tmp = startPerson[j];
                    
                    startPerson[j] = startPerson[j + 1];
                    startPerson[j + 1] = tmp;
                }
            }
        }

        // 밑에서부터 '?'행이 나오기 전까 진행
        for (int i = n - 1; i > questionIdx; i--) {
            for (int j = 0; j < k - 1; j++) {
            	// 막대가 있는 경우 swap
                if (map[i][j] == '-') {
                    char tmp = destPerson[j];
                    
                    destPerson[j] = destPerson[j + 1];
                    destPerson[j + 1] = tmp;
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < k - 1; i++) {
            if (startPerson[i] == destPerson[i]) {
                sb.append("*");
            } else if (startPerson[i] == destPerson[i + 1] || startPerson[i + 1] == destPerson[i]) {
                char tmp = startPerson[i];
                
                startPerson[i] = startPerson[i+1];
                startPerson[i + 1] = tmp;
                
                sb.append("-");
            } else {
            	// 두 칸 이상 떨어져 있다면 원하는 순서를 얻을 수 없는 경우이기 때문에 'x'로 구성된 길이 k-1인 문자열을 출력
            	sb.setLength(0);
                for(int j = 0; j < k - 1; j++) {
                	sb.append("x");
                }

                break;
            }
        }

        System.out.println(sb);
    }
}
