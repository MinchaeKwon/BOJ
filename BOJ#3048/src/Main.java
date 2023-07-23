/**
 * 3048 개미
 * https://www.acmicpc.net/problem/3048
 * 
 * @author minchae
 * @date 2023. 7. 23.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Ant {
    char value;
    int dir;

    public Ant(char value, int dir) {
        this.value = value;
        this.dir = dir;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N1 = Integer.parseInt(st.nextToken());
        int N2 = Integer.parseInt(st.nextToken());

        ArrayList<Ant> ants = new ArrayList<>();

        String input = br.readLine();
        for (int i = 0; i < N1; i++) {
            ants.add(new Ant(input.charAt(N1 - i - 1), 1)); // 첫 번째 개미 그룹은 오른쪽으로 지나가기 때문에 순서를 바꿔서 넣어줘야함
		}

        input = br.readLine();
        for (int i = 0; i < N2; i++) {
            ants.add(new Ant(input.charAt(i), 2));
        }

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            for (int i = 0; i < N1 + N2 - 1; i++) {
                Ant cur = ants.get(i);
                Ant next = ants.get(i + 1);

                // 다음 개미와 방향이 반대일 경우 점프해서 뛰어넘음
                if (cur.dir == 1 && next.dir == 2) {
                    // 개미의 위치 바꿔줌
                    ants.set(i, next);
                    ants.set(i + 1, cur);

                    i++; // 두 개미의 위치가 바뀌었기 때문에 i를 증가시켜서 (i + 2)를 보게 함
                }
            }
        }

        for (Ant ant : ants) {
            System.out.print(ant.value);
        }
    }
}