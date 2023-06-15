/**
 * 17779 게리맨더링 2
 * https://www.acmicpc.net/problem/17779
 * 
 * @author minchae
 * @date 2023. 6. 15.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        
        int total = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }

        int answer = Integer.MAX_VALUE;
        
        // 4중 for문 사용
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (x + d1 + d2 >= N || y - d1 < 0 || y + d2 >= N) {
                            continue;
                        }

                        boolean[][] border = new boolean[N][N];

                        // 경계선 설정
                        for (int i = 0; i <= d1; i++) {
                            border[x + i][y - i] = true;
                            border[x + i + d2][y - i + d2] = true;
                        }

                        for (int i = 0; i <= d2; i++) {
                            border[x + i][y + i] = true;
                            border[x + d1 + i][y - d1 + i] = true;
                        }

                        int[] sum = new int[5]; // 각 구역의 인구수 저장

                        // 1구역 인구수
                        for (int i = 0; i < x + d1; i++) {
                            for (int j = 0; j <= y; j++) {
                                // break 하지 않으면 경계선을 넘어서 구역이 나누어지기 때문에 경계선을 만나면 break를 통해 바로 다음 줄로 넘어감
                                // (경계선과 경계선 안에 포함되어있는 곳은 5번 선거구)
                                if (border[i][j]) {
                                    break;
                                }

                                sum[0] += map[i][j];
                            }
                        }

                        // 2구역 인구수
                        for (int i = 0; i <= x + d2; i++) {
                            for (int j = N - 1; j > y; j--) {
                                if (border[i][j]) {
                                    break;
                                }
                                
                                sum[1] += map[i][j];
                            }
                        }
                        
                        // 3구역 인구수
                        for (int i = x + d1; i < N; i++) {
                            for (int j = 0; j < y - d1 + d2; j++) {
                                if (border[i][j]) {
                                    break;
                                }
                                
                                sum[2] += map[i][j];
                            }
                        }
                        
                        // 4구역 인구수
                        for (int i = x + d2 + 1; i < N; i++) {
                            for (int j = N - 1; j >= y - d1 + d2; j--) {
                                if (border[i][j]) {
                                    break;
                                }
                                
                                sum[3] += map[i][j];
                            }
                        }
                        
                        // 5구역 인구수
                        sum[4] = total;
                        for (int i = 0; i < 4; i++) {
                            sum[4] -= sum[i];
                        }

                        Arrays.sort(sum);
                         
                        answer = Math.min(answer, sum[4] - sum[0]);
                    }
                }
            }
        }

        System.out.println(answer);

    }
}