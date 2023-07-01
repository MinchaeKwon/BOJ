/**
 * 16637 괄호 추가하기
 * https://www.acmicpc.net/problem/16637
 * 
 * @author minchae
 * @date 2023. 7. 1.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int N;
    static ArrayList<Integer> num = new ArrayList<>();
    static ArrayList<Character> op = new ArrayList<>();

    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String input = br.readLine();

        for (int i = 0; i < N; i++) {
            char c = input.charAt(i);

            if (c == '+' || c == '-' || c == '*') {
                op.add(c);
            } else {
                num.add(Character.getNumericValue(c));
            }
        }

        dfs(0, num.get(0));

        System.out.println(answer);
    }

    // dfs를 통해 괄호 추가해보면서 최댓값 얻음
    private static void dfs(int depth, int total) {
        if (depth >= op.size()) {
            answer = Math.max(answer, total);
            return;
        }

        // 괄호 없이 진행
        int res1 = calculate(depth, total, num.get(depth + 1));
        dfs(depth + 1, res1);

        // 괄호가 추가하고 진행
        if (depth + 1 < op.size()) {
            int res2 = calculate(depth + 1, num.get(depth + 1), num.get(depth + 2)); // total의 오른쪽에 있는 값 계산
            int result = calculate(depth, total, res2); // 현재 계산된 값과 방금 전 괄호 추가하고 계산한 결과를 괄호 오른쪽에 있는 연산자로 dfs 돌림

            dfs(depth + 2, result);
        }
    }

    // 수식에 따라 값 계산
    private static int calculate(int idx, int n1, int n2) {
        int result = 0;

        switch (op.get(idx)) {
            case '+':
                result = n1 + n2;
                break;

            case '-':
                result = n1 - n2;
                break;

            case '*':
                result = n1 * n2;
                break;
        }

        return result;
    }
}