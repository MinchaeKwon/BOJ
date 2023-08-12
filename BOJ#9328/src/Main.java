/**
 * 9328 열쇠
 * https://www.acmicpc.net/problem/9328
 * 
 * @author minchae
 * @date 2023. 8. 12.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Building {
    int x;
    int y;

    public Building(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    // 상하좌우
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static int h, w;

    static char[][] map; // '.'는 빈 공간, '*'는 벽, '$'는 상근이가 훔쳐야하는 문서, 알파벳 대문자는 문, 알파벳 소문자는 열쇠
    static boolean[] key; // 상근이가 가지고 있는 열쇠 유무

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h + 2][w + 2]; // 상근이는 처음에 빌딩의 밖에 있으므로 범위 +2 해서 저장

            // 테투리 빈 공간으로 채워주기
            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    map[i][j] = '.';
                }
            }

            for (int i = 1; i <= h; i++) {
                String input = br.readLine();

                for (int j = 1; j <= w; j++) {
                    map[i][j] = input.charAt(j - 1);
                }
            }

            key = new boolean[26];

            String input = br.readLine();
            if (!input.equals("0")) {
                for (int i = 0; i < input.length(); i++) {
                    key[input.charAt(i) - 'a'] = true;
                }
            }

            System.out.println(bfs());
        }

    }

    private static int bfs() {
        int result = 0;

        Queue<Building> q = new LinkedList<>();
        boolean[][] visited = new boolean[h + 2][w + 2];

        q.add(new Building(0, 0));
        visited[0][0] = true;

        ArrayList<Building>[] gate = new ArrayList[26]; // 열지 못한 문 (특정 열쇠로 열 수 있는 문이 여러 개가 될수도 있기 때문에 리스트배열로 사용)

        for (int i = 0; i < 26; i++) {
            gate[i] = new ArrayList<>();
        }

        while (!q.isEmpty()) {
            Building cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                // 범위를 벗어나지 않고, 벽이 아닌 경우
                if (checkRange(nx, ny) && !visited[nx][ny] && map[nx][ny] != '*') {
                    char value = map[nx][ny];

                    if (value - 'A' >= 0 && value - 'A' <= 25) { // 문 찾기 -> 알파벳 대문자인지 확인
                        if (key[value - 'A']) {
                            // 열쇠를 가지고 있는 경우
                            q.add(new Building(nx, ny));
                            visited[nx][ny] = true;

                            map[nx][ny] = '.'; // 문을 열었기 때문에 빈 공간으로 만들어줌
                        } else {
                            // 열쇠가 없는 경우 열지 못한 문에 추가 (아래 조건을 통해 열쇠를 새로 발견할 경우 열지 못했던 문을 열 수 있으므로 저장하는 것)
                            gate[value - 'A'].add(new Building(nx, ny));
                        }
                    } else if (value - 'a' >= 0 && value - 'a' <= 25) { // 열쇠 찾기 -> 알파벳 소문자인지 확인
                        q.add(new Building(nx, ny));
                        visited[nx][ny] = true;

                        key[value - 'a'] = true;

                        for (int i = 0; i < 26; i++) {
                            for (Building tmp : gate[i]) {
                                // 열쇠가 있고, 빈 공간이 아닌 경우 (gate에는 대문자 아니면 빈 공간이 들어가게 됨)
                                if (key[i] && map[tmp.x][tmp.y] != '.') {
                                    q.add(new Building(tmp.x, tmp.y));
                                    visited[tmp.x][tmp.y] = true;

                                    map[tmp.x][tmp.y] = '.';
                                }
                            }
                        }
                    } else if (value == '$') { // 문서 찾기
                        q.add(new Building(nx, ny));
                        visited[nx][ny] = true;

                        result++; // 문서 개수 증가
                    } else { // 빈 공간
                        q.add(new Building(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        return result;
    }

    // 범위 확인
    private static boolean checkRange(int x, int y) {
        return x >= 0 && x < h + 2 && y >= 0 && y < w + 2;
    }
}