/**
 * 마법사 상어와 파이어볼
 * https://www.acmicpc.net/problem/20056
 * 
 * @author minchae
 * @date 2023. 4. 5.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class FireBall {
    int x; // 행
    int y; // 열
    int mass; // 질량
    int speed; // 속도
    int dir; // 방향

    public FireBall(int x, int y, int mass, int speed, int dir) {
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.speed = speed;
        this.dir = dir;
    }
}

public class Main {

    // 상, 오른쪽 위, 우, 오른쪽 아래, 하, 왼쪽 아래, 좌, 왼쪽 위
    static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
    static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };

    static int N;
    static ArrayList<FireBall>[][] map;
    static ArrayList<FireBall> fireBallList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int mass = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            fireBallList.add(new FireBall(x, y, mass, speed, dir));
        }

        // ArrayList 2차원 배열에 파이어볼 저장 -> 이동하면서 같은 칸에 여러 개의 파이어볼이 있을수도 있기 때문
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        while (K-- > 0) {
            moveFireBall();
            combineDevide();
        }

        int answer = 0;
        for (FireBall ball : fireBallList) {
            answer += ball.mass;
        }
        System.out.println(answer);
    }

    // 파이어볼 이동시키기
    private static void moveFireBall() {
        // 파이어볼 이동
        for (FireBall ball : fireBallList) {
            // 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동
            ball.x = (N + ball.x + dx[ball.dir] * (ball.speed % N)) % N;
            ball.y = (N + ball.y + dy[ball.dir] * (ball.speed % N)) % N;

            map[ball.x][ball.y].add(ball);
        }
    }

    // 파이어볼 합치고 나누기
    private static void combineDevide() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = map[i][j].size(); // 파이어볼 개수

                // 같은 칸에 파이어볼이 2개 이상인 경우
                if (cnt > 1) {
                    int massSum = 0; // 질량 합
                    int speedSum = 0; // 속도 합
                    boolean odd = true; // 홀수만 있는지 확인
                    boolean even = true; // 짝수만 있는지 확인

                    // 파이어볼 합치기
                    for (FireBall ball : map[i][j]) {
                        massSum += ball.mass;
                        speedSum += ball.speed;

                        // 방향이 하나라도 홀수거나 짝수이면 짝수, 홀수를 false로 설정
                        if (ball.dir % 2 == 0) {
                            odd = false;
                        } else {
                            even = false;
                        }

                        fireBallList.remove(ball);
                    }

                    // 질량이 0이 돼서 소멸하는 파이어볼이 있기 때문에 여기서 clear 해줌
                    map[i][j].clear(); // 파이어볼을 합치고 나눈걸 새로 리스트에 추가했기 때문에 기존 맵에 저장되어 있는 파이어볼은 삭제

                    int mass = massSum / 5; // 나누어진 파이어볼의 질량

                    // 질량이 0인 파이어볼은 소멸
                    if (mass <= 0) {
                        continue;
                    }

                    int speed = speedSum / cnt; // 나누어진 파이어볼의 속도

                    // 파이어볼 4개로 나누기 -> 모두 홀수이거나 짝수인 경우와 그렇지 않은 경우 구분
                    if (odd || even) {
                        // 방향 0, 2, 4, 6
                        for (int k = 0; k < 8; k += 2) {
                            fireBallList.add(new FireBall(i, j, mass, speed, k));
                        }
                    } else {
                        // 방향 1, 3, 5, 7
                        for (int k = 1; k < 8; k += 2) {
                            fireBallList.add(new FireBall(i, j, mass, speed, k));
                        }
                    }

                } else {
                    map[i][j].clear();
                }

            }
        }

    }

}