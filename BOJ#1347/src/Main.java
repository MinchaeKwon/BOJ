/**
 * 1347 미로 만들기
 * https://www.acmicpc.net/problem/1347
 * 
 * @author minchae
 * @date 2023. 7. 14.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    // 상좌하우
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    
    static char[] path;
    static char[][] map = new char[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        path = br.readLine().toCharArray();

        // 일단 벽으로 다 채워둠
        for (int i = 0; i < 101; i++) {
            Arrays.fill(map[i], '#');
        }

        // 시작점 (중간을 기준으로 시작 -> 위나 아래로 50칸을 가든 범위 안에 들어가기 때문에 50부터 시작하는 것)
        int x = 50;
        int y = 50;

        // 직사각형의 왼쪽 위 좌표
        int minX = 50;
        int minY = 50;

        // 직사각형의 오른쪽 아래 좌표
        int maxX = 50;
        int maxY = 50;
        
        int dir = 2; // 홍준이가 미로 안에서 남쪽을 보며 서있기 때문에 남쪽부터 시작

        map[x][y] = '.';

        for (int i = 0; i < n; i++) {
            switch (path[i]) {
                case 'F': // 앞으로 한칸 움직임
                    x += dx[dir];
                    y += dy[dir];

                    map[x][y] = '.';

                    minX = Math.min(minX, x);
                    minY = Math.min(minY, y);

                    maxX = Math.max(maxX, x);
                    maxY = Math.max(maxY, y);

                    break;

                case 'L': // 왼쪽으로 90도 회전
                    dir = (dir+ 1) % 4;
                    break;

                case 'R': // 오른쪽으로 90도 회전
                    dir = dir == 0 ? 3 : dir - 1;
                    break;
            }
        }

        // 미로 지도 출력
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

    }
}