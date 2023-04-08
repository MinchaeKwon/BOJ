/**
 * 어항 정리
 * https://www.acmicpc.net/problem/23291
 * 
 * @author minchae
 * @date 2023. 4. 8.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, K;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][25];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			map[i][0] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		while (getDifference()) {
			addFish();
			
			firstFold();
			control();
			restore();
			
			secondFold();
			control();
			restore();
			
			answer++;
		}
		
		System.out.println(answer);
	}
	
	// 물고기가 가장 많이 들어있는 어항과 가장 적게 들어있는 어항의 물고기 수 차이가 K를 초과하는지 확인
	private static boolean getDifference() {
		int maxFish = -1;
		int minFish = 100001;
		
		// 일렬로 되어있는 상태에서 물고기 차이 구하는 것이기 때문에 for문 한 번만 사용
		for (int i = 0; i < N; i++) {
			maxFish = Math.max(maxFish, map[i][0]);
			minFish = Math.min(minFish, map[i][0]);
		}
		
		return maxFish - minFish > K;
	}
	
	// 물고기의 수가 가장 적은 어항에 물고기를 한 마리 넣음
	private static void addFish() {
		int minFish = 100001;
		
		// 물고기 수가 가장 적은 어항 찾음
		for (int i = 0; i < N; i++) {
			minFish = Math.min(minFish, map[i][0]);
		}
		
		// 물고기 수가 가장 적은 어항의 물고기 수 1 증가시킴
		for (int i = 0; i < N; i++) {
			if (map[i][0] == minFish) {
				map[i][0]++;
			}
		}
	}
	
	// 첫 번째 공중부양
	private static void firstFold() {
		int startX = 0;
        int w = 1;
        int h = 1;
        
        // 공중 부양시킨 어항 중 가장 오른쪽에 있는 어항의 아래에 바닥에 있는 어항이 있을때까지 반복
        while (startX + w + h <= N) {
            for (int i = w - 1; i >= 0; i--) {
                for (int j = 0; j < h; j++) {
                    int nx = startX + w + j;
                    int ny = w - i;
                    
                    map[nx][ny] = map[startX + i][j];
                    map[startX + i][j] = 0;
                }
            }
            
            startX += w;
            if (w == h) {
            	h++;
            } else {
            	w++;
            }
        }
	}
	
	// 물고기 수 조절
	private static void control() {
		int[][] save = new int[N][25];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 25; j++) {
				
				if (map[i][j] > 0) {
					for (int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						
						if (nx >= 0 && nx < N && ny >= 0 && ny < 25) {
							if (map[nx][ny] > 0) {
								// 모든 인접한 두 어항에 대해서 물고기 수의 차이를 구하고 5로 나눔
								int diff = (map[i][j] - map[nx][ny]) / 5;
								
								// 0보다 크다면 차이에 해당하는 물고기를 물고기 많은 어항의 물고기를 적은 어항으로 보냄
								if (diff > 0) {
									save[i][j] -= diff;
									save[nx][ny] += diff;
								}
							}
						}
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 25; j++) {
				map[i][j] += save[i][j];
			}
		}
	}

	// 어항 바닥에 일렬로 놓기 -> 가장 왼쪽에 있는 어항부터, 그리고 아래에 있는 어항부터 가장 위에 있는 어항까지 순서대로 바닥에 놓음
	private static void restore() {
		int x = 0;
        while (map[x][0] == 0) {
        	x++;
        }

        int index = 0;
        int[] save = new int[N];
        
        for (int i = x; i < N; i++) {
            for (int j = 0; j < 25; j++) {
                if(map[i][j] == 0) break;
                save[index++] = map[i][j];
                map[i][j] = 0;
            }
        }
        
        for (int i = 0; i < N; i++) {
        	map[i][0] = save[i];
        }
	}
	
	// 두 번째 공중부양
	// 가운데를 중심으로 왼쪽 N/2개를 공중 부양시켜 전체를 시계 방향으로 180도 회전 시킨 다음, 오른쪽 N/2개의 위에 놓아야 한다. 이 작업은 두 번 반복
	private static void secondFold() {
		for (int i = 0; i < N / 2; i++) {
			map[N - 1 - i][1] = map[i][0];
            map[i][0] = 0;
        }

        for (int i = 0; i < N / 4; i++) {
            for (int j = 0; j < 2; j++) {
            	map[N - 1 - i][3 - j] = map[N / 2 + i][j];
            	map[N / 2 + i][j] = 0;
            }
        }
	}
	
}
