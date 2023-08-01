/**
 * 3678 카탄의 개척자
 * https://www.acmicpc.net/problem/3678
 * 
 * @author minchae
 * @date 2023. 8. 2.
 * 
 * https://rightbellboy.tistory.com/128
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    // 게임판에 숫자가 배치되는 순서 (정육각형)
    static int[] dx = {-2, -1, 1, 2, 1, -1};
    static int[] dy = {0, -1, -1, 0, 1, 1};

    static int[][] map = new int[240][120];
    static int[] result = new int[100001]; // 게임판 숫자 저장
    static int[] cnt = new int[6]; // 숫자 사용한 횟수
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int c = Integer.parseInt(br.readLine());

        initMap();

        while (c-- > 0) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(result[n]);
        }
    }

    // 게임판 초기화
    private static void initMap() {
        // 맵의 중간부터 시작
        int x = 120;
        int y = 60;

        int idx = 1;

        map[x][y] = 1;
        cnt[1] = 1;
        result[idx++] = 1; // 다음에 올 값을 위해 숫자 넣어준 후에 인덱스 증가

        int size = 1;

        while (idx < 10001) {
            x += dx[5];
            y += dy[5];

            int nextNum = getNextNum(x, y); // 다음에 올 숫자 구한

            map[x][y] = nextNum;
            cnt[nextNum] += 1;
            result[idx++] = nextNum;

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < size; j++) {
                    if (i == 0 && j == size - 1) {
                        continue;
                    }

                    if (idx >= 10001) {
                        break;
                    }

                    x += dx[i];
                    y += dy[i];

                    nextNum = getNextNum(x, y); // 다음에 올 숫자 구함

                    map[x][y] = nextNum;
                    cnt[nextNum] += 1;
                    result[idx++] = nextNum;
                }
            }

            size++;
        }
    }

    // 다음에 올 숫자 구하기 -> 인접한 숫자 확인
    private static int getNextNum(int x, int y) {
        boolean[] available = new boolean[6];

        // 특정 칸에 인접한 것들 확인
        for (int i = 0; i < 6; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            available[map[nx][ny]] = true;
        }

        int min = 2000;
        int value = 0;

        // 자신을 제외한 나머지 확인
        for (int i = 1; i < 6; i++) {
            // 인접하지 않고 사용한 개수가 가장 적으면서 숫자가 가장 작은 경우
            if (!available[i] && cnt[i] < min) {
                min = cnt[i];
                value = i;
            }
        }

        return value;
    }
}