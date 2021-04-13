/**
 * 16235 나무 재테크
 * https://www.acmicpc.net/problem/16235
 * 
 * @author minchae
 * @date 2021. 4.13
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Tree {
	int x;
	int y;
	int age;
	boolean dead;
	
	public Tree(int x, int y, int age, boolean dead) {
		this.x = x;
		this.y = y;
		this.age = age;
		this.dead = dead; // 죽었으면 true, 아니면 false
	}
	
}

public class Main {
	
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	static int N, M, K; // 땅 크기, 나무 개수, 몇년이 지났는지
	static int[][] A; // 양분 -> S2D2가 땅을 돌아다니면서 땅에 추가할 양분을 저장
	static int[][] map; // 땅에 있는 양분을 저장
	static LinkedList<Tree> treeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		A = new int[N][N];
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5; // 가장 처음에 양분은 모든 칸에 5만큼 들어있으므로 5를 넣어줌
			}
		}
		
		treeList = new LinkedList<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			
			treeList.add(new Tree(x, y, z, false));
		}
		
		simulate();
		
		// K년이 지난 후 살아남은 나무의 수를 출력
		System.out.println(treeList.size());
		
	}
	
	public static void simulate() {
		while (K > 0) {
			/* 봄 -> 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다. 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
			 * 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
			 * 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다. */
			for (Tree tree : treeList) {
				if (map[tree.x][tree.y] >= tree.age) {
					map[tree.x][tree.y] -= tree.age;
					tree.age++;
				}
				else { // 땅에 양분이 부족한 경우 
					tree.dead = true;
				}
			}
			
			/* 여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
			 * 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다. */
			Iterator<Tree> iterator = treeList.iterator();
            while (iterator.hasNext()) {
                Tree tree = iterator.next();
                
                if (tree.dead) {
                	map[tree.x][tree.y] += tree.age / 2;
                	iterator.remove(); // 양분으로 변했기 때문에 리스트에서 삭제함
                }
            }
            
            /* 가을에는 나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
             * 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다. */
            LinkedList<Tree> newTree = new LinkedList<>();
            for (Tree tree : treeList) {
            	if (tree.age % 5 == 0) {
            		for (int j = 0; j < 8; j++) {
            			int nx = tree.x + dx[j];
            			int ny = tree.y + dy[j];
            			
            			if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
            				newTree.add(new Tree(nx, ny, 1, false)); // 나이가 1인 나무 생성
            			}
            		}
            	}
            }
            treeList.addAll(0, newTree);
            
            /* 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다. 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다. */
            for (int i = 0; i < N; i++) {
            	for (int j = 0; j < N; j++) {
            		map[i][j] += A[i][j];
            	}
            }
			
			K--;
		}
		
	}

}
