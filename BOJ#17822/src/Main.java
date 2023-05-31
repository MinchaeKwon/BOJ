/**
 * 17822 원판 돌리기
 * https://www.acmicpc.net/problem/17822
 * 
 * @author minchae
 * @date 2023. 5. 31.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashSet;

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 원판 T번 회전
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            rotateMap(x, d, k);
            HashSet<Node> same = findSameNum();

            // 인접하면서 수가 같은 것이 있는 경우 수를 지움
            if (same.size() > 0) {
                for (Node node : same) {
                    map[node.x][node.y] = 0;
                }
            } else {
                calculateNum();
            }
        }

        int result = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                result += map[i][j];
            }
        }

        System.out.println(result);
    }

    // 번호가 x의 배수인 원판을 d방향으로 k칸 회전시킨다. d가 0인 경우는 시계 방향, 1인 경우는 반시계 방향
    private static void rotateMap(int x, int d, int k) {
        for (int num = x; num <= N; num += x) {
            for (int i = 0; i < k; i++) {
                int tmp;

                if (d == 0) {
                    // 시계 방향으로 회전
                    tmp = map[num][M];
    
                    for (int j = M; j > 1; j--) {
                        map[num][j] = map[num][j - 1];
                    }
    
                     map[num][1] = tmp;
                } else {
                    // 반시계 방향으로 회전
                    tmp = map[num][1];
    
                    for (int j = 1; j < M; j++) {
                        map[num][j] = map[num][j + 1];
                    }
    
                    map[num][M] = tmp;
                }
            }
        }
    }

    // 인접하면서 수가 같은 것을 찾음
    private static HashSet<Node> findSameNum() {
        HashSet<Node> hs = new HashSet<>(); // 중복제거를 위해 사용

        // 같은 원판끼리 인접하는 경우 탐색
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < M; j++) {
                if (map[i][j] > 0) {
                    // (i, 1), (i, M)이 인접하기 때문에 값이 같은 경우 hashset에 추가
                    if (j == 1) {
                        if (map[i][j] == map[i][M]) {
                            hs.add(new Node(i, j));
                            hs.add(new Node(i, M));
                        }
                    }

                    // (i, j), (i, j + 1) 인접
                    if (map[i][j] == map[i][j + 1]) {
                        hs.add(new Node(i, j));
                        hs.add(new Node(i, j + 1));
                    }
                }
            }
        }

        // 다른 원판끼리 인접하는 경우 탐색
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] > 0) {
                    // 다른 원판이니까 i를 증가시키면서 확인
                    if (map[i][j] == map[i + 1][j]) {
                        hs.add(new Node(i, j));
                        hs.add(new Node(i + 1, j));
                    }
                }
            }
        }

        return hs;
    }

    // 원판에 적힌 수의 평균을 구하고, 평균보다 큰 수에서는 1을 빼고, 작은 수에는 1을 더함
    private static void calculateNum() {
        double sum = 0;
        double cnt = 0;

        // 원판에 적힌 수의 합과 개수 구함
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                // 원판에 숫자가 있을 경우에만 더함
                if (map[i][j] > 0) {
                    sum += map[i][j];
                    cnt++;
                }
            
            }
        }

        // 평균 구함
        if (cnt > 0) {
            sum /= cnt;
        }

        // 평균보다 큰 수에서는 1를 빼고, 작은 수에서는 1을 더함
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] > 0) {
                    if (map[i][j] > sum) {
                        map[i][j]--;
                    } else if (map[i][j] < sum) {
                        map[i][j]++;
                    }
                }
            }
        }
    }

}