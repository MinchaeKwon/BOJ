/**
 * 7576 - 토마토
 * https://www.acmicpc.net/problem/7576
 * 
 * @author Minchae Kwon
 * @date 2021.1.15
 *
 * 입력
 * 첫 줄에는 상자의 크기를 나타내는 두 정수 M,N이 주어진다. M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다.
 * 단, 2 ≤ M,N ≤ 1,000 이다. 둘째 줄부터는 하나의 상자에 저장된 토마토들의 정보가 주어진다. 즉, 둘째 줄부터 N개의 줄에는 상자에 담긴 토마토의 정보가 주어진다.
 * 하나의 줄에는 상자 가로줄에 들어있는 토마토의 상태가 M개의 정수로 주어진다. 정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.
 * 토마토가 하나 이상 있는 경우만 입력으로 주어진다.
 * 
 * 출력
 * 여러분은 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다. 
 * 만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//토마토의 위치를 저장하기 위한 클래스
class Tomato {
	int x;
	int y;
	int day;
	
	public Tomato(int x, int y, int day) {
		this.x = x;
		this.y = y;
		this.day = day;
	}
}

public class Main {

	//상하좌우의 토마토를 확인하기 위한 배열
	static int[] dx = {-1, 1, 0, 0}; //상, 하
	static int[] dy = {0, 0, -1, 1}; //좌, 우
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int M = sc.nextInt();
		int N = sc.nextInt();
		
		int[][] box = new int[M][N];
		Queue<Tomato> queue = new LinkedList<>();
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				box[i][j] = sc.nextInt();
				
				//익은 토마토를 큐에 넣어줌
				if (box[i][j] == 1) {
					queue.add(new Tomato(i, j, 0));
				}
			}
		}
		
		//토마토 익히기
		System.out.println(getMinDay(M, N, box, queue));
		
		sc.close();
		
	}
	
	public static int getMinDay(int M, int N, int[][] box, Queue<Tomato> q) {
		int day = 0;
		
		//토마토 익히기
		while (!q.isEmpty()) { //익힌 토마토가 없어질때까지 주변 토마토를 익힘
			Tomato tomato = q.poll();
			day = tomato.day;
			
			for (int i = 0; i < 4; i++) {
				//상하좌우를 차례대로 확인하기 위한 좌표 얻음
				int x = tomato.x + dx[i];
				int y = tomato.y + dy[i];
				
				//토마토 상자의 범위를 벗어나는지 확인
				if (x >= 0 && x < M && y >= 0 && y < N) {
					//상하좌우를 확인하면서 토마토가 안익었다면 1로 바꿔주고 하루를 더해줌(하루가 지나야 상하좌우의 토마토가 익기때문)
					if (box[x][y] == 0) {
						box[x][y] = 1;
						q.add(new Tomato(x, y, day + 1));
					}
				}
			}
		}
		
		//모든 토마토가 익었는지 확인
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (box[i][j] == 0) { //하나라도 0(토마토가 익지 않은 것)이라면 모든 토마토가 익지 않은 것이기 때문에 -1 출력
					return -1;
				}
			}
		}
		
		return day;
		
	}

}
