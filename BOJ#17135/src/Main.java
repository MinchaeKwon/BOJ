/**
 * 17135 캐슬 디펜스
 * https://www.acmicpc.net/problem/17135
 * 
 * @author minchae
 * @date 2023. 6. 29.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

// 적의 위치, 거리 저장
class Point implements Comparable<Point> {
    int x; // 행
    int y; // 열
    int d; // 거리

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

    // 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격
    @Override
    public int compareTo(Point o) {
        if (this.d == o.d) {
            return this.y - o.y; // 거리가 같을 경우 열의 값이 더 작은 것을 선택 (오름차순)
        }

        return this.d - o.d; // 거리가 더 작은 값을 선택 (오름차순)
    }
}

public class Main {

    static int N, M, D;
    static int[][] map;

    static int[] archer = new int[3]; // 조합을 통해 얻은 궁수의 위치 저장
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken()); // 0은 빈 칸, 1은 적이 있는 칸
            }
        }

        comb(0, 0);

        System.out.println(answer);
    }

    // 궁수 배치 -> 조합 이용(백트래킹)
    private static void comb(int start, int depth) {
        // 궁수 3명 다 뽑은 경우에 적을 공격함
        if (depth == 3) {
            attack();
            return;
        }

        // N+1번째 행의 모든 칸에 성이 있기 때문에 열만 확인하는 것 (성이 있는 곳은 격자 바깥쪽)
        for (int i = start; i < M; i++) {
            archer[depth] = i;
            comb(i + 1, depth + 1);
        }
    }

    public static int distance(int r1, int r2, int c1, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }

    // 적 공격
    private static void attack() {
        // 조합을 사용해 궁수 3명을 뽑으면서 공격으로 제거한 적의 최대 수를 찾기 때문에 맵을 복사해서 사용
        int[][] copy = new int[N][M];

        for (int i = 0; i < N; i++) {
            copy[i] = Arrays.copyOf(map[i], M);
        }

        int cnt = 0;

        // 최대 N번까지 진행 가능 (궁수의 공격이 끝나면 적은 한 칸 아래로 이동하기 때문에 N번이 지나면 격자판에서 적이 다 제외되기 때문)
        for (int n = 0; n < N; n++) {
            ArrayList<Point> targetList = new ArrayList<>(); // 궁수 3명이 공격하려고 하는 적 저장

            for (int k = 0; k < 3; k++) {
                ArrayList<Point> cadidate = new ArrayList<>(); // 특정 궁수가 공격하려고 하는 적을 찾기 위해 사용

                // 특정 궁수가 공격하려고 하는 적을 찾음
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if (copy[i][j] == 1) {
                            int d = Math.abs(i - N) + Math.abs(j - archer[k]); // 두 위치의 거리 구하기

                            // 공격하는 적의 거리가 D이하일 경우
                            if (d <= D) {
                                cadidate.add(new Point(i, j, d));
                            }
                        }
                    }
                }

                if (!cadidate.isEmpty()) {
                    // 정렬 후에 첫 번째에 있는 것이 공격하려고 하는 적
                    Collections.sort(cadidate);
                    targetList.add(cadidate.get(0));
                }
            }

            for (Point point : targetList) {
                // 같은 적이 여러 궁수에게 공격당할 수 있어도 제거되는 적은 한명이기 때문에 적이 있는 경우에만 빈 칸으로 만들고 개수 증가 시킴
                if (copy[point.x][point.y] == 1) {
                    copy[point.x][point.y] = 0;
                    cnt++;
                }
            }

            // 남아있는 적들 한 칸 아래로 이동 -> (i - 1)번째에 있는 값들을 i번째로 옮기면 됨 -> 밑에부터 시작해야 값이 제대로 바뀜
            for (int i = N - 1; i >= 0; i--) {
                for (int j = 0; j < M; j++) {
                    if (i == 0) {
                        copy[i][j] = 0; // 첫 번째 줄일 경우 0을 넣어줌 -> 아래로 한 칸 이동하기 때문에 첫 줄은 무조건 0이 들어감
                    } else {
                        copy[i][j] = copy[i - 1][j];
                    }
                }
            }
        }

        answer = Math.max(answer, cnt); // 공격하는 적의 최대 수를 구함
    }

}