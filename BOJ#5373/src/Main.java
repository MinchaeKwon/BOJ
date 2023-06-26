/**
 * 5373 큐빙
 * https://www.acmicpc.net/problem/5373
 * 
 * @author minchae
 * @date 2023. 6. 27.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static final int U = 0, D = 1, F = 2, B = 3, L = 4, R = 5;
    static char[] color = { 'w', 'y', 'r', 'o', 'g', 'b' };

    static char[][][] cube = new char[6][3][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            initCube(); // 큐브 초기화
            
            int n = Integer.parseInt(br.readLine()); // 큐브를 돌린 횟수

            StringTokenizer st = new StringTokenizer(br.readLine());

            while (n-- > 0) {
                String order = st.nextToken();

                char face = order.charAt(0);
                char dir = order.charAt(1);

                rotateCube(face, dir);
            }

            printCube();
        }

    }

    // 큐브 상태 초기화
    private static void initCube() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    cube[i][j][k] = color[i];
                }
            }
        }
    }

    // 큐브 돌리는 방법에 따라 큐브 돌리기
    private static void rotateCube(char face, char dir) {
        boolean clockwise = dir == '+';

        switch (face) {
            case 'U' : // 윗면
            turn(U, L, F, R, B, clockwise);
            break;
            
            case 'D' : // 아랫면
            turn(D, B, R, F, L, clockwise);
            break;

            case 'F' : // 앞면
            turn(F, U, L, D, R, clockwise);
            break;

            case 'B' : // 뒷면
            turn(B, R, D, L, U, clockwise);
            break;

            case 'L' : // 왼쪽면
            turn(L, F, U, B, D, clockwise);
            break;

            case 'R' : // 오른쪽면
            turn(R, D, B, U, F, clockwise);
            break;
        }
    }

    // 큐브 돌리기 -> 돌리는 면, 해당 면에 접한 면들의 정보, 돌리는 방향(시계/반시계)
    // 앞, 위, 왼쪽, 아래, 오른쪽 (뒷면은 접하지 않기 때문에 매개변수로 받지 않음)
    private static void turn(int f, int u, int l, int d, int r, boolean clockwise) {
        char[][] tmp = new char[3][3];
        char[] tmp2 = new char[3];

        if (clockwise) {
            // 시계방향
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    tmp[i][j] = cube[f][2 - j][i];
                }
            }

            // 돌아가는 면 기준 가장 앞에 있는 것을 임시 저장 해둠
            for (int i = 0; i < 3; ++i) {
                tmp2[i] = cube[u][i][0];
            }

            // 큐브 돌림
            for (int i = 0; i < 3; ++i) {
                cube[u][i][0] = cube[l][0][2 - i];
            }

            for (int i = 0; i < 3; ++i) {
                cube[l][0][2 - i] = cube[d][2][i];
            }

            for (int i = 0; i < 3; ++i) {
                cube[d][2][i] = cube[r][2 - i][2];
            } 

            for (int i = 0; i < 3; ++i) {
                cube[r][2 - i][2] = tmp2[i];
            }
        } else {
            // 반시계방향
            for (int i = 0; i < 3; ++i) {
                for (int j = 0; j < 3; ++j) {
                    tmp[i][j] = cube[f][j][2 - i];
                }
            }

            // 돌아가는 면 기준 가장 앞에 있는 것을 임시 저장 해둠
            for (int i = 0; i < 3; ++i) {
                tmp2[i] = cube[u][i][0];
            }

            // 큐브 돌림
            for (int i = 0; i < 3; ++i) {
                cube[u][i][0] = cube[r][2 - i][2];
            }

            for (int i = 0; i < 3; ++i) {
                cube[r][2 - i][2] = cube[d][2][i];
            }

            for (int i = 0; i < 3; ++i) {
                cube[d][2][i] = cube[l][0][2 - i];
            } 

            for (int i = 0; i < 3; ++i) {
                cube[l][0][2 - i] = tmp2[i];
            }
        }

        cube[f] = tmp;
    }

    // 큐브 윗 면의 색상 출력
    private static void printCube() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(cube[U][j][2 - i]);
            }
            System.out.println();
        }
    }

}