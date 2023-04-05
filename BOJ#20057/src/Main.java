/**
 * 마법사 상어와 토네이도
 * https://www.acmicpc.net/problem/20057
 * 
 * @author minchae
 * @date 2023. 4. 5.
 */

 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.StringTokenizer;

public class Main {

    // 좌 하 우 상 (토네이도의 이동 칸이 좌하는 1, 3, 5 / 우상은 2, 4, 6으로 증가함)
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    // 모래가 퍼지는 x방향
    static int[][] sdx = {
        {-1, 1, -2, -1, 1, 2, -1, 1, 0}, // 좌
        {-1, -1, 0, 0, 0, 0, 1, 1, 2}, // 하
        {-1, 1, -2, -1, 1, 2, -1, 1, 0}, // 우
        {1, 1, 0, 0, 0, 0, -1, -1, -2}  // 상
    };

    // 모래가 퍼지는 y방향
    static int[][] sdy = {
        {1, 1, 0, 0, 0, 0, -1, -1, -2}, // 좌
        {-1, 1, -2, -1, 1, 2, -1, 1, 0}, // 하
        {-1, -1, 0, 0, 0, 0, 1, 1, 2}, // 우
        {1, -1, 2, 1, -1, -2, 1, -1, 0}  // 상
    };

    static int[] sandRatio = {1, 1, 2, 7, 7, 2, 10, 10, 5}; // 모래가 퍼지는 비율
    static int[] dc = {1, 1, 2, 2}; // 토네이도가 좌하우상으로 이동할 때 몇 칸을 이동하는지 저장

    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(moveTonado(N / 2, N / 2)); // 격자의 가운데 칸부터 토네이도의 이동이 시작

    }

    // 토네이도 이동 -> 모래 퍼뜨림 -> 토네이도 이동 칸 증가
    private static int moveTonado(int x, int y) {
        int outSand = 0; // 격자 밖으로 나간 모래의 양

        while (true) {
            for (int i = 0; i < 4; i++) {
                // 토네이도는 한 번에 한 칸씩 이동하기 때문에 for문 돌려서 한 칸씩 이동함 (x + dx[i] * dc[i] -> 이렇게 하는건 한 번에 여러 칸 움직이는 것)
                for (int move = 0; move < dc[i]; move++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
    
                    // 토네이도가 격자를 벗어나거나 (0, 0)에 위치하는 경우 소멸된 것으로 판단
                    if (nx < 0 || ny < 0 || nx >= N || ny >= N){
                        return outSand;
                    }
    
                    int sand = map[nx][ny];
                    map[nx][ny] = 0;
                    int sandSum = 0; // 알파 자리에 모래를 퍼뜨리기 위해 사용하는 것
    
                    // 9개의 방향으로 모래 퍼뜨림
                    for (int j = 0; j < 9; j++) {
                        int sx = nx + sdx[i][j];
                        int sy = ny + sdy[i][j];
                        int spreadSand = (sand * sandRatio[j]) / 100;
    
                        if (sx < 0 || sx >= N || sy < 0 || sy >= N) {
                            outSand += spreadSand; // 격자를 벗어나는 경우에 outSand에 더함
                        } else {
                            map[sx][sy] += spreadSand; // 범위 안에 있는 경우 퍼뜨리는 위치의 모래양과 합침
                        }
    
                        sandSum += spreadSand;
                    }
    
                    // 9개의 방향에 모래를 퍼뜨렸으면 남은 모래를 알파 자리에 퍼뜨림
                    int ax = nx + dx[i];
                    int ay = ny + dy[i];
                    int alphaSand = sand - sandSum;
    
                    if (ax < 0 || ax >= N || ay < 0 || ay >= N) {
                        outSand += alphaSand; // 격자를 벗어나는 경우에 outSand에 더함
                    } else {
                        map[ax][ay] += alphaSand; // 범위 안에 있는 경우 퍼뜨리는 위치의 모래양과 합침
                    }
    
                    // 이동한 위치를 현재 위치로 바꿈
                    x = nx;
                    y = ny;
                } 

            }

            // 토네이도가 이동하는 칸의 개수 증가 -> 2개씩 늘어남
            for (int i = 0; i < 4; i++) {
                dc[i] += 2;
            }

        }
    }


}