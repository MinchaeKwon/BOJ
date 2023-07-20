/**
 * 2784 가로 세로 퍼즐
 * https://www.acmicpc.net/problem/2784
 * 
 * @author minchae
 * @date 2023. 7. 21.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static String[] input = new String[6];
    static boolean[] visited = new boolean[6];
    static String[] result = new String[6]; // (0 ~ 2까지는 행에 들어가는 단어, 3 ~ 5까지는 열에 들어가는 단어)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 6; i++) {
            input[i] = br.readLine();
        }

        Arrays.sort(input);

        solve(0);
        System.out.println(0); // 6개의 단어를 퍼즐에 놓을 수 없는 경우 0 출력
    }

    // 백트래킹 사용
    private static void solve(int cnt) {
        if (cnt == 6) {
            char[] R, C;

            for (int i = 0; i < 3; i++) {
                C = result[i + 3].toCharArray(); // 특정 열의 알파벳이 들어감

                for (int j = 0; j < 3; j++) {
                    R = result[j].toCharArray(); // 특정 행의 알파벳이 들어감

                    // i번째로 뽑힌 열의 단어를 행의 단어 i번째 알파벳과 비교 -> 같지 않은 경우 종료
                    if (R[i] != C[j]) {
                        return;
                    }
                }
            }

            // 행의 단어와 열의 단어가 같을 경우 result 출력 -> 행과 열의 단어가 같기 때문에 3번만 반복
            for (int i = 0; i < 3; i++) {
                System.out.println(result[i]);
            }

            System.exit(0); // 종료
        }

        for (int i = 0; i < 6; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[cnt] = input[i];

                solve(cnt + 1);

                visited[i] = false;
            }
        }
    }
}