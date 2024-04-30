/**
 * 15898 피아의 아틀리에 ~신비한 대회의 연금술사~
 * https://www.acmicpc.net/problem/15898
 * 
 * @author minchae
 * @date 2024. 4. 26.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	
	static int[][][][] map;
	static int[][][][] mapColor;
	
	static int[][] gama;
	static int[][] gamaColor;
	
	static boolean[] visited;
	
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		map = new int[10][4][4][4];
		mapColor = new int[10][4][4][4];
		
		for (int t = 0; t < n; t++) {
			int[][] tmp = new int[4][4];
			
			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < 4; j++) {
					tmp[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			map[t][0] = tmp;
			
			// 4방향으로 회전한 맵 저장
			for (int i = 1; i < 4; i++) {
				map[t][i] = rotate(map[t][i - 1]);
			}
			
			tmp = new int[4][4];
			
			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for (int j = 0; j < 4; j++) {
					tmp[i][j] = st.nextToken().charAt(0) - 'A';
				}
			}
			
			mapColor[t][0] = tmp;
			
			// 4방향으로 회전한 맵 저장
			for (int i = 1; i < 4; i++) {
				mapColor[t][i] = rotate(mapColor[t][i - 1]);
			}
		}
		
		gama = new int[5][5];
		gamaColor = new int[5][5];
		visited = new boolean[10];
		
		simulate(0);

		System.out.println(answer);
	}
	
	// 시계 방향 회전
	private static int[][] rotate(int[][] map) {
		int[][] newMap = new int[4][4];
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				newMap[j][4 - i - 1] = map[i][j];
			}
		}
		
		return newMap;
	}
	
	private static void simulate(int depth) {
		if (depth == 3) {
			answer = Math.max(answer, calculateScore());
			return;
		}

		for (int i = 0; i < n; i++) {
			if (visited[i] == false) {
				visited[i] = true;

				int[][] storeGama = new int[5][5];
				int[][] storeGamaColor = new int[5][5];
				for (int r = 0; r < 5; r++) {
					for (int c = 0; c < 5; c++) {
						storeGama[r][c] = gama[r][c];
						storeGamaColor[r][c] = gamaColor[r][c];
					}
				}

				for (int r = 0; r < 2; r++) {
					for (int c = 0; c < 2; c++) {
						for (int rotate = 0; rotate < 4; rotate++) {
							coloring(i, r, c, rotate);

							simulate(depth + 1);

							for (int k = 0; k < 5; k++) {
								for (int h = 0; h < 5; h++) {
									gama[k][h] = storeGama[k][h];
									gamaColor[k][h] = storeGamaColor[k][h];
								}
							}
						}

					}
				}

				visited[i] = false;
			}
		}

	}
	
	private static int calculateScore() {
		int R = 0;
		int B = 0;
		int G = 0;
		int Y = 0;
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				int color = gamaColor[i][j];
				int sum = gama[i][j];
				
				if (color == 'R' - 'A') {
					R += sum;
				} else if (color == 'B' - 'A') {
					B += sum;
				} else if (color == 'G' - 'A') {
					G += sum;
				} else if (color == 'Y' - 'A') {
					Y += sum;
				}
			}
		}
		
		return R * 7 + B * 5 + G * 3 + Y * 2;
	}

	private static void coloring(int kindIndex, int startRow, int startCol, int rotateIndex) {
		for (int k = 0; k < 4; k++) {
			for (int h = 0; h < 4; h++) {
				gama[startRow + k][startCol + h] += map[kindIndex][rotateIndex][k][h];

				if (gama[startRow + k][startCol + h] < 0) {
					gama[startRow + k][startCol + h] = 0;
				} else if (gama[startRow + k][startCol + h] > 9) {
					gama[startRow + k][startCol + h] = 9;
				}

				if (mapColor[kindIndex][rotateIndex][k][h] != 'W' - 'A') {
					gamaColor[startRow + k][startCol + h] = mapColor[kindIndex][rotateIndex][k][h];
				}
			}
		}

	}

}
