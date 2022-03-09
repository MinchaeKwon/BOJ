/**
 * 1713 후보 추천하기
 * https://www.acmicpc.net/problem/1713
 * 
 * @author minchae
 * @date 2022. 3. 9.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Student implements Comparable<Student> {
	int num;
	int recommend;
	int time;
	
	public Student(int num, int recommend, int time) {
		this.num  = num;
		this.recommend = recommend;
		this.time = time;
	}

	// 추천 횟수를 기준으로 오름차순 정렬
	@Override
	public int compareTo(Student o) {
		// 추천 횟수가 같다면 시간을 기준으로 오름차순 정렬
		if (this.recommend == o.recommend) {
			return this.time - o.time;
		}
		
		return this.recommend - o.recommend;
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		
		ArrayList<Student> students = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < P; i++) {
			int num = Integer.parseInt(st.nextToken());
			boolean flag = false;
			
			// 이미 사진틀에 게시되어 있는지 확인
			for (Student student : students) {
				// 게시되어 있는 경우 추천 횟수 증가 시킴
				if (num == student.num) {
					student.recommend++;
					flag = true;
					
					break;
				}
			}
			
			// 사진틀에 게시되어 있지 않은 경우
			if (!flag) {
				// 비어있는 사진틀이 없는 경우에는 정렬 후 맨 앞에 있는 학생의 사진을 삭제
				if (students.size() == N) {
					Collections.sort(students);
					students.remove(0);
				}
				
				students.add(new Student(num, 1, i));
			}
		}
		
		// 학생 번호를 기준으로 오름차순 정렬
		Collections.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.num - o2.num;
			}
		});
				
		for (Student student : students) {
			System.out.print(student.num + " ");
		}
	}

}
