/* 1991 - 트리 순회
 * 2020.12.30
 * 
 * 입력
 * 첫째 줄에는 이진 트리의 노드의 개수 N(1≤N≤26)이 주어진다. 둘째 줄부터 N개의 줄에 걸쳐 각 노드와 그의 왼쪽 자식 노드, 오른쪽 자식 노드가 주어진다.
 * 노드의 이름은 A부터 차례대로 영문자 대문자로 매겨지며, 항상 A가 루트 노드가 된다. 자식 노드가 없는 경우에는 .으로 표현된다.
 * 
 * 출력
 * 첫째 줄에 전위 순회, 둘째 줄에 중위 순회, 셋째 줄에 후위 순회한 결과를 출력한다. 각 줄에 N개의 알파벳을 공백 없이 출력하면 된다.
 */

import java.util.Scanner;

class Node {
	Node left;
	Node right;
	char data;
	
	public Node(char data) {
		this.data = data;
	}
}

class Tree {
	Node root; //루트 노드(처음에는 null)
	
	public void createNode(char data, char left, char right) {
		if (root == null) {
			root = new Node(data);
			
			//자식 노드가 있을 경우에만 노드 생성
			if (left != '.') {
				root.left = new Node(left);
			}
			if (right != '.') {
				root.right = new Node(right);
			}
		}
		else { //root가 null이 아닌 경우에는 어디에 삽입될지 찾아야함
			searchNode(root, data, left, right);
		}
	}
	
	public void searchNode(Node root, char data, char left, char right) {
		if (root == null) {
			return;
		}
		else if (root.data == data) { //자식 노드가 삽입될 위치를 찾았다면
			//값이 있을 경우에만 노드 생성
			if (left != '.') {
				root.left = new Node(left);
			}
			if (right != '.') {
				root.right = new Node(right);
			}
		}
		else { //찾지 못한 경우 재귀로 탐색
			searchNode(root.left, data, left, right);
			searchNode(root.right, data, left, right);
		}
	}
	
	//전위순회
	public void preorder(Node root) {
		System.out.print(root.data);
		if (root.left != null) preorder(root.left);
		if (root.right != null) preorder(root.right);
	}
	
	//중위순회
	public void inorder(Node root) {
		if (root.left != null) inorder(root.left);
		System.out.print(root.data);
		if (root.right != null) inorder(root.right);		
	}
	
	//후위순회
	public void postorder(Node root) {
		if (root.left != null) postorder(root.left);
		if (root.right != null) postorder(root.right);
		System.out.print(root.data);
	}
}

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		Tree tree = new Tree();
		
		for (int i = 0; i < n; i++) {
			char parent = sc.next().charAt(0);
			char left = sc.next().charAt(0);
			char right = sc.next().charAt(0);
			
			tree.createNode(parent, left, right);
		}
		
		//전위순회
		tree.preorder(tree.root);
		System.out.println();
		
		//중위순회
		tree.inorder(tree.root);
		System.out.println();
		
		//후위순회
		tree.postorder(tree.root);
		System.out.println();
		
		sc.close();
	}


}
