/**
 * 상어 초등학교
 * https://www.acmicpc.net/problem/21608
 * 
 * @author minchae
 * @date 2023. 4. 3.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Student implements Comparable<Student> {
	int x;
	int y;
	int emptyCnt; // 인접한 칸 중에서 비어있는 칸 개수
	int likeCnt; // 좋아하는 학생이 인접한 칸 개수
	
	public Student(int x, int y, int emptyCnt, int likeCnt) {
		this.x = x;
		this.y = y;
		this.emptyCnt = emptyCnt;
		this.likeCnt = likeCnt;
	}
	
	@Override
	public int compareTo(Student o) {
		/*
		 * 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
		 * 2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
		 * 3. 2를 만족하는 칸도 여러 개인 경우에는 행(x)의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열(y)의 번호가 가장 작은 칸으로 자리를 정한다.
		 * */
		
//		if (this.likeCnt != o.likeCnt) return o.likeCnt - this.likeCnt; // 내림차순
//		if (this.emptyCnt != o.emptyCnt) return o.emptyCnt - this.emptyCnt; // 내림차순
//		if (this.x != o.x) return this.x - o.x; // 오름차순
//		return this.y = o.y; // 오름차순
		
		if (this.likeCnt == o.likeCnt) {
			if (this.emptyCnt == o.emptyCnt) {
				if (this.y == o.y) {
					return this.x - o.x;
				} else {
					return this.y -o.y;
				}
			} else {
				return o.emptyCnt - this.emptyCnt;
			}
		} else {
			return o.likeCnt - this.likeCnt;
		}
	}
	
}

public class Main {

	static int[] dx = {-1, 0, 1, 0}; // 상하
	static int[] dy = {0, -1, 0, 1}; // 좌우
	
	static int N;
	static int[][] map;
	
	static int[] order;
	static ArrayList<Integer>[] studentList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		order = new int[N * N + 1];
		studentList = new ArrayList[N * N + 1];
		
		for (int i = 1; i < N * N + 1; i++) {
			studentList[i] = new ArrayList<>();
		}
		
		for (int i = 1; i < N * N + 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			order[i] = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j < 4; j++) {
				studentList[order[i]].add(Integer.parseInt(st.nextToken()));
			}
		}

		// 자리 배치 하기
		for (int i = 1; i < N * N + 1; i++) {
			placement(order[i]);
		}
		
        System.out.println(getSatisfaction());

	}
	
	// 자리 배치
	private static void placement(int num) {
		PriorityQueue<Student> pq = new PriorityQueue<>();
		
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				
				int emptyCnt = 0;
				int likeCnt = 0;
				
				// 이미 자리 배치가 된 경우
				if (map[i][j] != 0) {
                    continue;
                }
				
				// 상하좌우 인접한 칸 확인
				for (int k = 0; k < 4; k++) {
					int nx = i + dx[k];
					int ny = j + dy[k];
					
					if (nx >= 1 && nx < N + 1 && ny >= 1 && ny < N + 1) {
						for (int like : studentList[num]) {
							// 인접한 칸에 좋아하는 학생이 있는 경우
							if (map[nx][ny] == like) {
								likeCnt++;
							}
						}
						
						// 인접한 칸이 비어있는 경우
						if (map[nx][ny] == 0) {
							emptyCnt++;
						}
					}
				}
				
				pq.add(new Student(i, j, emptyCnt, likeCnt)); // 우선순위 큐에 삽입
			}
		}
		
		Student student = pq.poll();
		map[student.x][student.y] = num; // 자리 결정
		
	}
	
	// 만족도 구하기
	private static int getSatisfaction() {
		int total = 0;
		
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
            	int num = map[i][j];
            	 
                int cnt = 0;
                
                for (int like : studentList[num]){
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx >= 1 && nx < N + 1 && ny >= 1 && ny < N + 1) {
                            if (map[nx][ny] == like){
                            	cnt++;
                            }
                        }
                    }
                }
                
                switch (cnt) {
         		case 0:
         			total += 0;
         			break;
         		case 1:
         			total += 1;
         			break;
         		case 2:
         			total += 10;
         			break;
         		case 3:
         			total += 100;
         			break;
         		case 4:
         			total += 1000;
         			break;
         		}
            }
        }
        
        return total;
    }

}
