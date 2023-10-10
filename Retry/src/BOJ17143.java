/**
 * 백준 17143 낚시왕
 * https://www.acmicpc.net/problem/17143
 * 
 * @author minchae
 * @date 2023. 10. 10.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Shark {
	int r;
	int c;
	int s; // 속력
	int d; // 이동방향
	int z; // 크기
	
	public Shark(int r, int c, int s, int d, int z) {
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
	}
}

public class BOJ17143 {
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	static int R, C, M;
	static Shark[][] map;
	
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Shark[R][C];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			
			map[r][c] = new Shark(r, c, s, d, z);
		}
		
		for (int i = 0; i < C; i++) {
			find(i);
			move();
		}
		
		System.out.println(result);

	}
	
	private static void find(int y) {
		for (int i = 0; i < R; i++) {
			// 가장 가까이에 있는 상어 찾은 경우
			if (map[i][y] != null) {
				result += map[i][y].z;
				map[i][y] = null;
				
				return;
			}
		}
	}
	
	private static void move() {
		Shark[][] copy = new Shark[R][C];
		
		for (int x = 0; x < R; x++) {
			for (int y = 0; y < C; y++) {
				Shark cur = map[x][y];
				
				if (cur == null) {
					continue;
				}
				
				// 격자에 부딪혀서 돌아오는 경우 고려한 계산
				int s = cur.s;
				s %= (cur.d < 2 ? R - 1 : C - 1) * 2;
                
                for (int i = 0; i < s; i++) {
                    int nr = cur.r + dx[cur.d];
                    int nc = cur.c + dy[cur.d];

                    // 가장자리에 도달하면 방향을 바꾸고 속력을 유지한 채로 나머지를 이동함
                    if (!checkRange(nr, nc)) {
                        // 이전 칸으로 돌아감
                        cur.r -= dx[cur.d];
                        cur.c -= dy[cur.d];

                        cur.d = cur.d % 2 == 0 ? cur.d + 1 : cur.d - 1; // 반대 방향으로 바꾸기
                        
                        continue;
                    }

                    // 곰팡이 위치 갱신
                    cur.r = nr;
                    cur.c = nc;
                }
				
				if (copy[cur.r][cur.c] == null || cur.z > copy[cur.r][cur.c].z) {
					copy[cur.r][cur.c] = cur;
				}
			}
		}
		
		map = copy;
	}
	
	private static boolean checkRange(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}

}
