/**
 * 14888 연산자 끼워넣기
 * https://www.acmicpc.net/problem/14888
 * 
 * @author minchae
 * @date 2023. 6. 20.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static int[] operator = new int[4];

    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE; // 최댓값이 음수로 나올 수 있기 때문에 0이 아닌 MIN_VALUE 사용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }

        dfs(arr[0], 1); // 첫 번째 숫자부터 시작하기 때문에 depth는 1부터 시작

        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int num, int depth) {
        if (depth == N) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) {
                operator[i]--; // 연산자 개수 감소

                switch (i) {
                    case 0 :
                    dfs(num + arr[depth], depth + 1);
                    break;

                    case 1 :
                    dfs(num - arr[depth], depth + 1);
                    break;

                    case 2 :
                    dfs(num * arr[depth], depth + 1);
                    break;

                    case 3 :
                    dfs(num / arr[depth], depth + 1);
                    break;
                }

                operator[i]++; // 연산자 개수 다시 증가 시킴
            }
        }
    }
    
}