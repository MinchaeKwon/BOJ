/**
 * 모노미노도미노 2
 * https://www.acmicpc.net/problem/20061
 * 
 * @author minchae
 * @date 2023. 5. 23.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Main {

    static boolean[][] blue = new boolean[4][6];
    static boolean[][] green = new boolean[6][4];

    static int score = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        /*
         * t = 1: 크기가 1×1인 블록을 (x, y)에 놓은 경우
         * t = 2: 크기가 1×2인 블록을 (x, y), (x, y+1)에 놓은 경우
         * t = 3: 크기가 2×1인 블록을 (x, y), (x+1, y)에 놓은 경우
         */
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            moveBlue(t, x);
            moveGreen(t, y);

            checkBlueBoard();
            checkGreenBoard();
        }

        System.out.println(score);
        System.out.println(getBlockCnt());
    }

    // 파란색 보드로 블록 이동 -> 블록의 이동은 다른 블록을 만나거나 보드의 경계를 만나기 전까지 계속해서 이동
    private static void moveBlue(int t, int x) {
        int pos = 0;

        if (t == 1) { // 1 x 1
            // 범위 : 보드의 경계
            for (int i = 0; i < 6; i++) {
                // 다른 블록을 만난 경우 이동 중지
                if (blue[x][i]) {
                    break;
                }

                pos = i;
            }

            blue[x][pos] = true;
        } else if (t == 2) {
            // 1 x 2
            for (int i = 1; i < 6; i++) {
                if (blue[x][i] || blue[x][i - 1]) {
                    break;
                }

                pos = i;
            }

            blue[x][pos] = true;
            blue[x][pos - 1] = true;
        } else {
            // 2 x 1
            for (int i = 0; i < 6; i++) {
                if (blue[x][i] || blue[x + 1][i]) {
                    break;
                }

                pos = i;
            }
            blue[x][pos] = true;
            blue[x + 1][pos] = true;
        }
    }

    // 초록색 보드로 블록 이동
    private static void moveGreen(int t, int y) {
        int pos = 0;

        if (t == 1) {
            // 1 x 1
            for (int i = 0; i < 6; i++) {
                if (green[i][y]) {
                    break;
                }

                pos = i;
            }

            green[pos][y] = true;
        } else if (t == 2) {
            // 1 x 2
            for (int i = 0; i < 6; i++) {
                if (green[i][y] || green[i][y + 1]) {
                    break;
                }

                pos = i;
            }

            green[pos][y] = true;
            green[pos][y + 1] = true;
        } else {
            // 2 x 1
            for (int i = 1; i < 6; i++) {
                if (green[i][y] || green[i - 1][y]) {
                    break;
                }

                pos = i;
            }

            green[pos][y] = true;
            green[pos - 1][y] = true;
        }
    }

    // 파란색 보드에서 어떤 행이 타일로 가득 차 있는지 확인
    private static void checkBlueBoard() {
        //  행이나 열이 타일로 가득 찬 경우가 없을 때까지 점수를 획득하는 과정이 모두 진행된 후, 연한 칸에 블록이 있는 경우 확인

        ArrayList<Integer> result = new ArrayList<>();

        // 2 ~ 5줄 확인
        for (int i = 2; i <= 5; i++) {
            int cnt = 0;
            for (int j = 0; j < 4; j++) {
                if (blue[j][i]) {
                    cnt++;
                }
            }

            // 한 줄이 채워진 경우
            if (cnt == 4) {
                result.add(i);
            }
        }

        // 없어지는 블록이 있을 경우
        if (result.size() > 0) {
            removeBlueBlock(result); // 블록이 있는 열의 수만큼 블록 사라지고 사라진 열의 수만큼 오른쪽으로 이동
        }

        result = new ArrayList<>();

        // 0 ~ 1줄 확인
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j < 4; j++) {
                if (blue[j][i]) {
                    // 0 ~ 1줄에 블록이 있으면 바로 이동해야하기 때문에 break
                    result.add(i);
                    break;
                }
            }
        }

        if (result.size() > 0) {
            removeBlueBlock(result);
        }
    }

    // 초록색 보드에서 어떤 행이 타일로 가득 차 있는지 확인
    private static void checkGreenBoard() {
        ArrayList<Integer> result = new ArrayList<>();

        // 2 ~ 5줄 확인
        for (int i = 2; i <= 5; i++) {
            int cnt = 0;
            for (int j = 0; j < 4; j++) {
                if (green[i][j]) {
                    cnt++;
                }
            }

            // 한 줄이 채워진 경우
            if (cnt == 4) {
                result.add(i);
            }
        }

        // 없어지는 블록이 있을 경우
        if (result.size() > 0) {
            removeGreenBlock(result); // 블록이 있는 행의 수만큼 블록이 사라지고 사라진 행의 수만큼 아래쪽으로 이동
        }

        result = new ArrayList<>();

        // 0 ~ 1줄 확인
        for (int i = 0; i <= 1; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j]) {
                    // 0 ~ 1줄에 블록이 있으면 바로 이동해야하기 때문에 break
                    result.add(i);
                    break;
                }
            }
        }

        if (result.size() > 0) {
            removeGreenBlock(result);
        }
    }

    // 파란색 보드의 블록 제거하고 이동
    private static void removeBlueBlock(ArrayList<Integer> list) {
        for (int col : list) {
            if (col > 1) {
                // 2 ~ 5줄인 경우 해당 열의 블록 삭제하고 0번째 열까지의 블록을 해당 열까지 이동
                for (int j = col; j > 0; j--) {
                    for (int i = 0; i < 4; i++) {
                        blue[i][j] = blue[i][j - 1];
                    }
                }

                for (int i = 0; i < 4; i++) {
                    blue[i][0] = false;
                }

                score++;
            } else {
                // 0 ~ 1줄인 경우 맨 마지막 열의 블록이 사라지고 이동하기 때문에 5부터 시작
                for (int j = 5; j > 0; j--) {
                    for (int i = 0; i < 4; i++) {
                        blue[i][j] = blue[i][j - 1];
                    }
                }

                for (int i = 0; i < 4; i++) {
                    blue[i][0] = false;
                }
            }
        }
    }

    // 초록색 보드의 블록 제거하고 이동
    private static void removeGreenBlock(ArrayList<Integer> list) {
        for (int row : list) {
            System.out.println("green " + row + " ");
            if (row > 1) {
                // 2 ~ 5줄인 경우 해당 행의 블록 삭제하고 0번째 행까지의 블록을 해당 행까지 이동
                for (int i = row; i > 0; i--) {
                    for (int j = 0; j < 4; j++) {
                        green[i][j] = green[i - 1][j];
                    }
                }

                for (int i = 0; i < 4; i++) {
                    green[0][i] = false;
                }

                score++;
            } else {
                // 0 ~ 1줄인 경우 맨 마지막 행의 블록이 사라지기 때문에 5부터 시작
                for (int i = 5; i > 0; i--) {
                    for (int j = 0; j < 4; j++) {
                        green[i][j] = green[i - 1][j];
                    }
                }

                for (int i = 0; i < 4; i++) {
                    green[0][i] = false;
                }
            }
        }
    }

    // 파란색, 초록색 보드의 블록 개수 세기
    private static int getBlockCnt() {
        int cnt = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (blue[i][j]) {
                    cnt++;
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (green[i][j]) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}