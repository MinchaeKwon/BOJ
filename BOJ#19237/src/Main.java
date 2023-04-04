/**
 * 어른 상어
 * https://www.acmicpc.net/submit/19236
 * 
 * @author minchae
 * @date 2023. 4. 5.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Shark {
	int num;
	int x;
	int y;
	int dir;
	int[][] priority = new int[4][4]; // 현재 방향, 우선 순위 저장
	
	public Shark(int num, int x, int y) {
		this.num = num;
		this.x = x;
		this.y = y;
	}
	
	int findNextDir(ArrayList<Integer> candidates) {
        for (int i = 0; i < 4; i++) {
            if (candidates.contains(priority[dir][i])) {
                return priority[dir][i];
            }
        }
        return -1;
    }
}

public class Main {
	
	// 상하좌우
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int N, M, k;
	
	static int[][] map; // 상어 위치 저장
	static int[][] smell; // 상어 냄새 저장
	static int[][] owner; // 냄새의 주인 번호 저장
	static HashMap<Integer, Shark> sharkMap = new HashMap<>(); // 상어가 격자에서 벗어나는 경우가 있기 때문에 HashMap 사용
	
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); // 상어 개수
		k = Integer.parseInt(st.nextToken()); // 각 상어가 가지는 냄새 수
		
		map = new int[N][N];
		smell = new int[N][N];
		owner = new int[N][N];
		
		// 상어 위치, 번호, 냄새 저장
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				// 빈 칸이 아닌 경우
				if (map[i][j] > 0) {
					// 상어 번호와 냄새를 저장
					sharkMap.put(map[i][j], new Shark(map[i][j], i, j));
					
					smell[i][j] = k;
					owner[i][j] = map[i][j];
				}
			}
		}
		
		// 상어 방향 저장
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sharkMap.get(i).dir = Integer.parseInt(st.nextToken()) - 1;
		}
		
		// 상어의 방향 우선순위 저장 -> 상어 당 4줄씩
		for (int i = 1; i <= M; i++) {
			for (int j = 0; j < 4; j++) {
				st = new StringTokenizer(br.readLine());
				
				for (int z = 0; z < 4; z++) {
                    sharkMap.get(i).priority[j][z] = Integer.parseInt(st.nextToken()) - 1;
                }
			}
		}
		
		while (answer < 1000) {
			moveShark();
			decreaseSmell();
			createSmell();
			
			answer++;
			
			// 상어가 같은 칸에 들어가는 경우에 번호가 높은 상어를 격자 밖으로 밀어내기 때문에 size가 1인 경우는 1번 상어만 남아있는 경우
			if (sharkMap.size() == 1) {
				System.out.println(answer);
				return;
			}
		}
		
		System.out.println(-1);
	}
	
	// 상어 이동시키기
	private static void moveShark() {
		ArrayList<Integer> removeShark = new ArrayList<>(); // 삭제할 상어 번호 저장 (바로 삭제하면 ConcurrentModification 에러 발생)
		
		// 상어 이동시킴 -> 동시에 이동하는 것이기 때문에 해당 칸에 상어가 있어도 상관없음 (만약 같은 칸에서 만난다면 번호 높은 상어는 삭제)
		for (Integer num : sharkMap.keySet()){
		    Shark shark = sharkMap.get(num);
		    
		    ArrayList<Integer> emptyList = new ArrayList<>();
		    ArrayList<Integer> myList = new ArrayList<>();
		    
		    // 네 방향 탐색하면서 이동할 수 있는 후보칸 찾음
		    for (int i = 0; i < 4; i++) {
		    	int nx = shark.x + dx[i];
			    int ny = shark.y + dy[i];
			    
			    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
			    	if (owner[nx][ny] == 0) { // 인접한 칸이 빈칸인 경우
			    		emptyList.add(i);
			    	} else if (owner[nx][ny] == shark.num) { // 인접한 칸에 자기 자신의 냄새가 있는 경우
			    		myList.add(i);
			    	}
			    }	
			}
		    
		    int nextDir = shark.findNextDir(emptyList); // 먼저 빈칸 중에 이동할 수 있는 방향 확인
		    
		    // 빈칸 중에 이동할 수 있는 칸이 없다면 자기 자신의 냄새가 있는 방향 확인
		    if (nextDir == -1) {
		    	nextDir = shark.findNextDir(myList);
		    }
		    
		    // 상어 자리 옮김
		    map[shark.x][shark.y] = 0;
		    
		    switch (nextDir) {
		    case 0:
		    	shark.x -= 1;
		    	break;
		    case 1:
		    	shark.x += 1;
		    	break;
		    case 2:
		    	shark.y -= 1;
		    	break;
		    case 3:
		    	shark.y += 1;
		    	break;
		    }
		    
		    // 이동한 칸에 상어가 없는 경우 || 상어가 있는데 현재 상어의 번호가 더 작은 경우
		    if (map[shark.x][shark.y] == 0 || map[shark.x][shark.y] > shark.num) {
		    	map[shark.x][shark.y] = shark.num;
		    	shark.dir = nextDir;
		    } else {
		    	// 번호가 커서 격자 밖으로 밀려난 상어는 removeShark에 추가
		    	removeShark.add(num);
		    }
		    
		}
		
		for (int num : removeShark) {
			sharkMap.remove(num);
		}
		
	}
	
	// 냄새가 저장된 배열의 모든 칸의 냄새를 1 감소시킴
	private static void decreaseSmell() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 냄새가 0인 것은 빈칸이기 때문에 넘어감
				if (smell[i][j] == 0) {
					continue;
				}
				
				smell[i][j]--;
				
				// 냄새 감소시키고 해당 칸의 냄새가 0이라면 상어 번호도 지움
				if (smell[i][j] == 0) {
					owner[i][j] = 0;
				}
			}
		}
	}
	
	// 상어가 이동한 칸에 냄새를 남김
	private static void createSmell() {
		for (Integer num : sharkMap.keySet()){
		    Shark shark = sharkMap.get(num);
		    
		    smell[shark.x][shark.y] = k;
		    owner[shark.x][shark.y] = shark.num;
		}
	}

}
