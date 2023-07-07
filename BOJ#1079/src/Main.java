/**
 * 1079 마피아
 * https://www.acmicpc.net/problem/1079
 * 
 * @author minchae
 * @date 2023. 7. 8.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] guilty; // 유죄지수
    static int[][] R;
    static int mafia;

    static boolean[] dead; // 죽었는지 확인
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        guilty = new int[N];
        R = new int[N][N];
        dead = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            guilty[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        mafia = Integer.parseInt(br.readLine());

        playGame(N, 0);

        System.out.println(answer);
    }

    private static void playGame(int cnt, int day) {
        // 마피아가 죽었거나, 마피아 혼자 남은 경우
        if (dead[mafia] || cnt == 1) {
            answer = Math.max(answer, day);
            return;
        }

        if (cnt % 2 == 0) {
            // 참가자 수가 짝수일 경우 -> 밤이기 때문에 마피아가 죽일 사람 한 명을 고름 -> 죽은 사람은 게임에 더이상 참여 X

            for (int i = 0; i < N; i++) {
                // 이미 죽었거나, 마피아 자신인 경우 (죽은 사람은 게임에 참여하지 않고, 마피아가 죽일 사람을 고르기 때문에 확인하는 것)
                if (dead[i] || i == mafia) {
                    continue;
                }

                // 참가자 i가 죽었다면, 다른 참가자 j의 유죄 지수는 R[i][j]만큼 변함
                for (int j = 0; j < N; j++) {
                    // 이미 죽은 사람인 경우 넘어감
                    if (dead[j]) {
                        continue;
                    }

                    guilty[j] += R[i][j];
                }

                dead[i] = true;

                playGame(cnt - 1, day + 1); // 한 명 죽이고 낮으로 바뀜
                
                // 원래 상태로 돌림
                dead[i] = false;

                for (int j = 0; j < N; j++) {
                    if (dead[j]) {
                        continue;
                    }

                    guilty[j] -= R[i][j];
                }
            }
        } else {
            // 참가자 수가 홀수이 경우 -> 낮이기 때문에 참가자들이 가장 죄가 있을 것 같은 사람 한 명을 죽임

            int guiltyMax = 0; // 유죄 지수가 가장 높은 사람을 찾기 때문에 0으로 시작
            int idx = 0;

            // 낮에는 현재 게임에 남아있는 사람 중에 유죄 지수가 가장 높은 사람을 죽인다. 그런 사람이 여러 명일 경우 그중 번호가 가장 작은 사람이 죽음
            for (int i = 0; i < N; i++) {
                // 죽지 않고 유죄 지수가 더 높은 경우 (같은 경우는 신경 X -> i가 0부터 시작하기 때문에 유죄 지수만 비교해도 번호가 가장 작은 사람이 선택됨)
                if (!dead[i] && guiltyMax < guilty[i]) {
                    guiltyMax = guilty[i];
                    idx = i;
                }
            }

            // 낮일 경우에 유죄 지수는 바뀌지 않음
            dead[idx] = true;

            playGame(cnt - 1, day); // 한 명 죽이고 밤으로 바뀜

            dead[idx] = false;
        }
    }
}