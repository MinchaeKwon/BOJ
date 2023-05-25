/**
 * 17825 주사위 윷놀이
 * https://www.acmicpc.net/problem/17825
 * 
 * @author minchae
 * @date 2023. 5. 25.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
     
    // 연결리스트 사용
    static class Node {
        int score; // 점수
        boolean isEmpty = true; // 칸이 비어있는지 확인
        boolean isFinish; // 도착점인지 확인
        Node next; // 다음 칸
        Node shortcut; // 파란색 화살표가 있는 칸인지 확인 -> 두 방향으로 연결됨
    
        public Node(int score) {
            this.score = score;
        }
    
        // 노드 연결
        public Node addNext(int score) {
            Node nextNode = new Node(score);
            this.next = nextNode;
    
            return nextNode;
        }
    
        // 특정 노드 찾기 -> 지름길을 나타내기 위함
        static Node findNode(Node start, int target) {
            Node temp = start;
    
            while (true) {
                if (temp == null) {
                    return null;
                }
    
                if (temp.score == target) {
                    return temp;
                }
    
                temp = temp.next;
            }
        }
     }
    
    static int[] dice = new int[10]; // 주사위 숫자
    static int[] order = new int[10]; // 말들의 이동 순서

    static Node[] horse = new Node[4]; // 4개의 말의 위치
    static Node start; // 시작점

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        initMap();
        permulation(0);
        System.out.println(result);
    }

    // 윷놀이 판 만들기
    private static void initMap() {
        start = new Node(0);

        Node node = start;

        // 윷놀이 판의 바깥쪽 점수 세팅
        for (int i = 2; i <= 40; i += 2) {
            node = node.addNext(i);
        }

        // 도착지
        Node end = node.addNext(0); // 도착지의 점수는 없음
        end.isFinish = true;
        end.next = end; // 도착 지점을 넘어가면 안되기 때문에 자기 자신을 가르키도록 함

        Node cross = new Node(25); // 가운데 교차점 (25)

        // 25(교차점) -> 30 -> 35 -> 40(도착점)
        node = cross.addNext(30);
        node = node.addNext(35);
        node.next = Node.findNode(start, 40);

        // 10 -> 13 -> 16 -> 19 -> 25(교차점)
        node = Node.findNode(start, 10);
        node = node.shortcut = new Node(13);
        node = node.addNext(16);
        node = node.addNext(19);
        node.next = cross;

        // 20 -> 22 -> 24 -> 25(교차점)
        node = Node.findNode(start, 20);
        node = node.shortcut = new Node(22);
        node = node.addNext(24);
        node.next = cross;

        // 30 -> 28 -> 27 -> 26 -> 25(교차점)
        node = Node.findNode(start, 30);
        node = node.shortcut = new Node(28);
        node = node.addNext(27);
        node = node.addNext(26);
        node.next = cross;
        
    }

    // 10개의 주사위 결과를 가지고 말들을 이동 시킴 -> 중복순열 사용 / 백트래킹을 통해 얻을 수 있는 점수의 최댓값을 구함
    private static void permulation(int depth) {
        if (depth == 10) {
            result = Math.max(result, move());
            return;
        }

        for (int i = 0; i < 4; i++) {
            order[depth] = i;
            permulation(depth + 1);
        }
    }

    // 주사위 굴리기
    private static int move() {
        Arrays.fill(horse, start); // 시작점에 말 배치

        int score = 0;

        // 순서대로 말들을 움직임
        for (int i = 0; i < 10; i++) {
            Node cur = horse[order[i]];
            cur.isEmpty = true; // 말을 이동시키니까 현재 칸은 비워줌

            // 주사위에 나온 숫자만큼 말을 이동
            for (int j = 0; j < dice[i]; j++) {
                if (j == 0 && cur.shortcut != null) {
                    cur = cur.shortcut; // 이동을 시작하는 위치가 파란색 칸일 경우 파란색 화살표를 타고 이동
                } else {
                    cur = cur.next; // 빨간색 화살표를 타고 이동
                }
            }

            horse[order[i]] = cur; // 이동 후에 말의 위치 설정

            // 이동을 마친 칸에 다른 말이 있는 경우 (도착점이면 상관 X)
            if (!cur.isEmpty && !cur.isFinish) {
                score = 0;
                break;
            } else {
                // 말이 없는 경우 이동가능하기 때문에 해당 칸에 말을 놓고 점수 계산
                cur.isEmpty = false;
                score += cur.score;
            }
        }

        // 게임 종료 후에 다음 게임을 위해 초기화 (백트래킹을 사용하기 때문에 초기화 시킴)
        for (int i = 0; i < 4; i++) {
            horse[i].isEmpty = true;
        }

        return score;
    }
}