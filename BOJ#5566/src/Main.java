/**
 * 5566 주사위 게임
 * https://www.acmicpc.net/problem/5566
 * 
 * @author minchae
 * @date 2023. 8. 10.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] order = new int[N];

        for (int i = 0; i < N; i++) {
            order[i] = Integer.parseInt(br.readLine());
        }

        int idx = 0;
        int cnt = 0;

        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(br.readLine());

            idx += num; // 던진 주사위 눈의 개수만큼 인덱스 증가

            cnt++; // 주사위 던진 횟수 증가

            // 범위를 벗어나면 종료 (주사위를 던졌을 때, 지시사항만큼 움직였을 때 도착칸이거나 범위를 벗어났을 때 게임 종료)
            if (idx >= N - 1 || idx + order[idx] >= N - 1) {
                break;
            }

            idx += order[idx]; // 도착 칸에 쓰여있는 지시만큼 다시 말을 움직임
        }

        System.out.println(cnt);
    }
}