/**
 * 1082 방 번호
 * https://www.acmicpc.net/problem/1082
 *
 * @author minchae
 * @date 2024. 11. 14.
 *
 * 문제 풀이
 *  - 어떻게 풀어야 할지 정말 너무 고민이 많았던 문제.. 이번에도 대수현의 엄청난 힌트로 문제를 풀었다! 근데 틀렸다ㅠ
 *  - 일단 먼저 만들 수 있는 가장 큰 자릿수를 구함
 *  - 가장 작은 비용이 0이 아닌 경우 그 비용으로 자릿수를 구함 -> M에서 최소 비용 빼면서 구함
 *  - 다 구했다면 첫 번째 자리부터 비용을 바꿔보면서 방 번호가 큰 것을 고름
 *    -> 계속 바꿔보면서 가장 큰 방의 번호를 구함
 *  - N이 1일 때와 첫 방의 번호가 0이 들어간 경우를 고려해야 함
 *
 * 실행 시간
 * 104 ms
 * */

import java.util.*;
import java.io.*;

public class Main {

    static class Room implements Comparable<Room> {
        int num;
        int cost;

        public Room(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Room o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int N, M;
    static Room[] info;
    static ArrayList<Room> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        info = new Room[N];
        result = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            info[i] = new Room(i, Integer.parseInt(st.nextToken()));
        }

        M = Integer.parseInt(br.readLine());

        Arrays.sort(info);

        find();
        check();

        for (Room cur : result) {
            System.out.print(cur.num);
        }
    }

    // 만들 수 있는 방의 최대 자릿수 구하기
    private static void find() {
        if (N == 1 || info[0].cost > M) {
            System.out.print(0);
            return;
        }

        // 일단 첫 자릿수 설정
        if (info[0].num == 0 && info[1].cost <= M) {
            // 첫 번째 자리는 0이 올 수 없음
            // 가장 최소 비용이 0인 경우 다음 방의 번호를 넣어야 함
            result.add(info[1]);
            M -= info[1].cost;
        } else {
            result.add(info[0]);
            M -= info[0].cost;
        }

        if (result.get(0).num == 0) {
            return;
        }

        // 추가 자릿수 확보
        while (info[0].cost <= M) {
            result.add(info[0]);
            M -= info[0].cost;
        }
    }

    private static void check() {
        // 자릿수만큼 반복
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < N; j++) {
                Room cur = result.get(i);

                // 교체 가능한 경우
                if (info[j].cost <= M + cur.cost && info[j].num > cur.num) {
                    M += cur.cost - info[j].cost;
                    result.set(i, info[j]);
                }
            }
        }
    }

}