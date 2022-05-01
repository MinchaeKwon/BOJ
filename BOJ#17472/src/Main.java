/**
 * 17472 다리 만들기 2
 * https://www.acmicpc.net/problem/17472
 * 
 * @author minchae
 * @date 2022. 5. 1.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
	int x;
	int y;
	int count;
	
	public Point(int x, int y, int count) {
		this.x = x;
		this.y = y;
		this.count = count;
	}
}

// 섬들의 간선 정보 저장
class Edge implements Comparable<Edge> {
    int s;
    int e;
    int weight;
	
    public Edge(int s, int e, int weight) {
        this.s = s;
        this.e = e;
        this.weight = weight;
    }
	
    @Override
    public int compareTo(Edge e) {
        // 가중치를 기준으로 오름차순 정렬
    	return this.weight - e.weight;
    }
}

public class Main {

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int N;
	static int M;
	
	static int[][] map;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[][] visited = new boolean[N][M];
		int num = 2;
		
		// 섬에 번호를 새김
		for (int i = 0; i < N; i++) {
           for (int j = 0; j < M; j++) {
        	   if (!visited[i][j] && map[i][j] == 1) {
        		   dfs(i, j, visited, num);
        		   num++;
	            }
	        }
	    }
		
		// 다리를 놓음
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 섬인 경우 -> 다리를 놓아야 하기 때문에 bfs 실행
				// 최소 다리 길이를 찾아야 하기 때문에 섬을 발견하면 방문 확인을 하지 않고 bfs 실행
				if (map[i][j] > 0) {
					bfs(i, j);
				}
			}
		}
		
		parent = new int[num - 1];
		
		for (int i = 0; i < num - 1; i++) {
			parent[i] = i;
		}
		
		
		
	}
	
	// 섬에 번호를 새기는 메소드
	public static void dfs(int x, int y, boolean[][] visited, int num) {
		visited[x][y] = true;
		map[x][y] = num;
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
				if (!visited[nx][ny] && map[nx][ny] == 1) {
					dfs(nx, ny, visited, num);
				}
			}
		}
	}
	
	// 다리를 놓는 메소드
	public static void bfs(int x, int y) {
		Queue<Point> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		
		q.add(new Point(x, y, 0));
		visited[x][y] = true;
		
		int cur = map[x][y]; // 현재 섬의 번호
		
		while (!q.isEmpty()) {
			Point p = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				// 범위를 벗어나지 않으면서 아직 방문하지 않았고, 현재 섬이 아닌 경우
				if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny] && map[nx][ny] != cur) {
					visited[nx][ny] = true;
					
					if (map[nx][ny] == 0) {
						q.add(new Point(nx, ny, p.count + 1));
					} else {
						
					}
				}
			}
		}
	}
	
	// x가 속하는 부모 노드(최상위 노드)를 찾음
    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }
	
    // 두 개의 노드가 속한 집합을 합침(연결함)
    public static void union(int x, int y) {		
    	// 각 원소의 최상위 원소를 찾음
        x = find(x);
        y = find(y);

        // 최상위 원소가 같지 않을 경우 union
        if (x != y) {
        	 parent[x] = y;
        }
    }

}
