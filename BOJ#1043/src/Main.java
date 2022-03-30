/**
 * 1043 거짓말
 * https://www.acmicpc.net/problem/1043
 * 
 * @author minchae
 * @date 2022. 3. 30.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static ArrayList<Integer>[] partyList;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		partyList = new ArrayList[M];
		parent = new int[N + 1];
		
		st = new StringTokenizer(br.readLine());
		
		int knowN = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < knowN; i++) {
			// 진실을 알고 있는 사람은 parent[num]에 num을 저장, 진실을 모르는 사람은 0
			int num = Integer.parseInt(st.nextToken());
			parent[num] = num;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int partyN = Integer.parseInt(st.nextToken());
			
			partyList[i] = new ArrayList<>();
			
			for (int j = 0; j < partyN; j++) {
				partyList[i].add(Integer.parseInt(st.nextToken()));
			}
			
			// 파티에 참석하는 사람이 2명 이상일 경우 같은 집합으로 합침
			if (partyList[i].size() >= 2) {
				partyUnion(i);
			}
		}
		
		int result = 0;
		
		// 각 파티를 돌면서 거짓말 할 수 있는지 확인
		for (int i = 0; i < M; i++) {
			if (isLie(i)) {
				result++;
			}
		}
		
		System.out.println(result);
	}
	
	// 파티에 참석한 인원을 같은 집합으로 합침 : 맨 처음 값과 나머지 값들을 union하면 같은 집합이 됨
	public static void partyUnion(int n) {
		int first = partyList[n].get(0);
		
		for (int i = 1; i < partyList[n].size(); i++) {
			union(first, partyList[n].get(i));
		}
	}
	
	// 파티에서 거짓말 할 수 있는지 확인
	public static boolean isLie(int n) {
		for (int i = 0; i < partyList[n].size(); i++) {
			int num = find(partyList[n].get(i)); // 파티에 참석한 사람의 부모를 알아냄
			
			// 진실을 알고 있는 사람을 입력 받을 때 parent[num]에 num을 넣었기 때문에 값이 같다면 진실을 알고 있다는 것
			if (parent[num] == num) {
				return false;
			}
		}
		
		return true;
	}
	
	// x가 속하는 부모 원소(최상위 원소)를 찾음
    public static int find(int x) {
    	// parent가 0으로 초기화 되어있기 때문에 parent[x] == 0 조건을 추가
        if (parent[x] == 0 || parent[x] == x) {
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
        	// 진실을 아는 사람이 있을 경우 : 같은 파티에 있기 때문에 진실을 모르는 사람의 부모를 진실을 아는 사람이 되게 함
        	if (x == parent[x]) {
        		parent[y] = x;	
        	} else {
        		parent[x] = y;
        	}
        }
	}

}
