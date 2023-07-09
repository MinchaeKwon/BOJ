/**
 * 1063 킹
 * https://www.acmicpc.net/problem/1063
 * 
 * @author minchae
 * @date 2023. 7. 9.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char[] king = st.nextToken().toCharArray();
        char[] stone = st.nextToken().toCharArray();

        int N = Integer.parseInt(st.nextToken());

        while (N-- > 0) {
            String order = br.readLine();

            // 움직이고 나서 범위 벗어나거나 돌과 겹치는지 확인하기 위해 배열 복사해서 사용
            char[] copyKing = king.clone();
            char[] copyStone = stone.clone();

            move(order, copyKing); // 킹을 움직임

            // 킹의 위치가 범위를 벗어나는지 확인
            if (copyKing[0] < 'A' || copyKing[0] > 'H' || copyKing[1] < '1' || copyKing[1] > '8') {
                continue;
            }
        
            // 킹을 움직였는데 돌과 같은 위치인 경우
            if (Arrays.equals(copyKing, copyStone)) {
                // 돌을 움직임
                move(order, copyStone);

                // 돌의 위치가 범위를 벗어나는지 확인
                if (copyStone[0] < 'A' || copyStone[0] > 'H' || copyStone[1] < '1' || copyStone[1] > '8') {
                    continue;
                }
            }

            // 범위 벗어나지 않는 경우 위치 갱신
            king = copyKing;
            stone = copyStone;
        }
        
        // 킹과 돌의 위치 출력
        System.out.println(king);
        System.out.println(stone);
    }

    /*
     * R : 한 칸 오른쪽으로
     * L : 한 칸 왼쪽으로
     * B : 한 칸 아래로
     * T : 한 칸 위로
     * RT : 오른쪽 위 대각선으로
     * LT : 왼쪽 위 대각선으로
     * RB : 오른쪽 아래 대각선으로
     * LB : 왼쪽 아래 대각선으로
     */

     // 킹이나 돌을 움직이는 함수
    private static void move(String order, char[] pos) {
        switch (order) {
            case "R":
                pos[0]++;
                break;
            case "L":
                pos[0]--;
                break;
            case "B":
                pos[1]--;
                break;
            case "T":
                pos[1]++;
                break;
            case "RT":
                pos[0]++;
                pos[1]++;
                break;
            case "LT":
                pos[0]--;
                pos[1]++;
                break;
            case "RB":
                pos[0]++;
                pos[1]--;
                break;
            case "LB":
                pos[0]--;
                pos[1]--;   
                break;    
        }
    }

}