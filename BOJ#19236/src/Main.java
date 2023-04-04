/**
 * 청소년 상어
 * https://www.acmicpc.net/submit/19236
 * 
 * @author minchae
 * @date 2023. 4. 4.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Shark {
	int x;
	int y;
	int dir; // 상어의 현재 방향
	int eat; // 상어가 먹은 물고기 번호
	
	public Shark(int x, int y, int dir, int eat) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.eat = eat;
	}
}

class Fish {
    int num; // 물고기 번호
    int dir; // 물고기 방향
    int x;
    int y;
    boolean isAlive = true;

    public Fish(int num, int dir, int x, int y, boolean isAlive) {
        this.num = num;
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
    }
}

public class Main {

    // 상, 왼쪽 위, 좌, 왼쪽 아래, 하, 오른쪽 아래, 우, 오른쪽 위 (반시계 방향으로 설정)
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};

    static int maxEat = 0; // 상어가 먹을 수 있는 물고기 번호 합의 최댓값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map = new int[4][4];
        Fish[] fishes = new Fish[17];
        
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1;

                fishes[num] = new Fish(num, dir, i, j, true);
                map[i][j] = num;
            }
        }

        // 맨 처음 상어가 (0, 0)에 있는 물고기를 먹음
        Fish fish = fishes[map[0][0]];
        fish.isAlive = false;

        map[0][0] = -1; // 상어의 위치는 -1로 표현

        // 초기 상어의 위치, 방향, 먹은 물고기 번호 저장
        Shark shark = new Shark(0, 0, fish.dir, fish.num);

        dfs(shark, map, fishes);

        System.out.println(maxEat);
    }

    // 상어 이동 -> 백트래킹 -> 갈 수 있는 방향의 물고기 다 먹어보고 최댓값 확인
    private static void dfs(Shark shark, int[][] map, Fish[] fishes) {
        maxEat = Math.max(maxEat, shark.eat); // 먹은 물고기 번호의 최댓값 구함
        
        moveFish(map, fishes); // 물고기 이동시키기

        // 상어 이동시키기 -> 최대 3칸까지 이동 가능함
        for (int i = 1; i < 4; i++) {
            int nx = shark.x + dx[shark.dir] * i;
            int ny = shark.y + dy[shark.dir] * i;

            // 경계를 벗어나지 않고 물고기가 있는 경우 (상어는 물고기가 있는 칸으로만 이동 가능함)
            if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && map[nx][ny] > 0) {
            	/*
            	 * 배열 복사해서 사용 -> 물고기 이동하고 상어가 이동하면서 물고기를 먹기 때문에 map과 fishes의 상태가 바뀜
            	 * 백트래킹이기 때문에 변경한 값을 이전으로 되돌려야 하는데 변경해야 하는 값이 많기 때문에 애초에 배열을 복사해서 사용
            	 * */
            	
                // 맵 상태 복사
                int[][] copyMap = new int[4][4];
                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        copyMap[j][k] = map[j][k];
                    }
                }

                // 물고기 상태 복사
                Fish[] copyFishes = new Fish[17];
                for (int j = 1; j < 17; j++) {
                    copyFishes[j] = new Fish(fishes[j].num, fishes[j].dir, fishes[j].x, fishes[j].y, fishes[j].isAlive);
                }

                // 이동해서 물고기 먹기
                copyMap[shark.x][shark.y] = 0; // 원래 상어가 있던 자리 빈 칸으로 만듦
                copyMap[nx][ny] = -1;
                
                Fish eatFish = copyFishes[map[nx][ny]];
                eatFish.isAlive = false;
                
                Shark newShark = new Shark(nx, ny, eatFish.dir, shark.eat + eatFish.num);

                dfs(newShark, copyMap, copyFishes); // 다시 탐색
            	
            }
        }

    }

    // 물고기 이동 -> 8개의 방향 탐색하면서 물고기 이동시킴
    private static void moveFish(int[][] map, Fish[] fishes) {
        for (int i = 1; i < 17; i++) {
            Fish fish = fishes[i];

            // 이미 죽은 물고기인 경우에는 탐색할 필요 없음
            if (!fish.isAlive) {
                continue;
            }

            // 8방향으로 물고기가 이동할 수 있는지 확인 -> 확인하다가 이동하면 for문 빠져나옴
            for (int j = 0; j < 8; j++) {
                int nextDir = (fish.dir + j) % 8;
                int nx = fish.x + dx[nextDir];
                int ny = fish.y + dy[nextDir];

                // 이동할 위치가 경계를 넘지 않고 상어가 없는 경우
                if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && map[nx][ny] != -1) {
                    if (map[nx][ny] == 0) { // 빈 칸일 경우
                        // 물고기 이동
                        map[fish.x][fish.y] = 0;
                        fish.x = nx;
                        fish.y = ny;
                    } else { // 다른 물고기가 있는 경우
                        // 현재 물고기와 바꿀 물고기의 위치 변경
                        Fish change = fishes[map[nx][ny]];
                        change.x = fish.x;
                        change.y = fish.y;
                        map[fish.x][fish.y] = change.num;

                        // 현재 물고기 위치 변경
                        fish.x = nx;
                        fish.y = ny;
                    }

                    map[nx][ny] = fish.num;
                    fish.dir = nextDir;
                    
                    break;
                }
            }

        }
    }
    

}