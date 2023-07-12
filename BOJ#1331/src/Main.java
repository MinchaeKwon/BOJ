/**
 * 1331 나이트 투어
 * https://www.acmicpc.net/problem/1331
 * 
 * @author minchae
 * @date 2023. 7. 13.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> visited = new ArrayList<>(); // 특정 경로가 이미 방문한 곳인지 확인

        String[] path = new String[37];

        for (int i = 0; i < 36; i++) {
            path[i] = br.readLine();
        }

        path[36] = path[0]; // 마지막으로 방문하는 칸에서 시작점으로 돌아올 수 있는지 확인하기 위해 맨 마지막에 시작점 경로를 넣어줌

        visited.add(path[0]);

        for (int i = 0; i < 36; i++) {
            int x = path[i].charAt(0);
            int y = path[i].charAt(1);

            int nx = path[i + 1].charAt(0);
            int ny = path[i + 1].charAt(1);

            // 1. 이미 방문한 곳인지 확인
            // 이미 방문한 곳인 경우 Invalid 출력 후 종료 (마지막 지점과 시작점을 비교할 때는 방문했는지 확인 X -> 거리 차이만 확인)
            if (i != 35 && visited.contains(path[i + 1])) {
                System.out.println("Invalid");
                return;
            }

            // 2. 나이트의 이동방향대로 이동했는지 확인
            // 나이트는 x방향으로 1칸, y방향으로 2칸 또는 x방향으로 2칸 y방향으로 1칸 이동 가능
            // 현재 위치와 이전 위치의 차이를 구해서 x좌표의 차가 1 또는 2, y좌표의 차가 2 또는 1이 나오는지 확인
            int diffX = Math.abs(nx - x);
            int diffY = Math.abs(ny - y);

            if ((diffX != 2 || diffY != 1) && (diffX != 1 || diffY != 2)) {
                System.out.println("Invalid");
                return;
            }

            visited.add(path[i + 1]);
        }

        // 올바른 경로일 경우 Valid 출력
        System.out.println("Valid");
    }

}