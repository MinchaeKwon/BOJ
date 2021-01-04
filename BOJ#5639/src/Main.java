/* 5639 - 이진 검색 트리
 * 2020.12.31
 * 
 * 입력
 * 트리를 전위 순회한 결과가 주어진다. 노드에 들어있는 키의 값은 106보다 작은 양의 정수이다. 모든 값은 한 줄에 하나씩 주어지며, 노드의 수는 10,000개 이하이다. 같은 키를 가지는 노드는 없다.
 * 
 * 출력
 * 입력으로 주어진 이진 검색 트리를 후위 순회한 결과를 한 줄에 하나씩 출력한다. 
 */

import java.util.Scanner;

class Tree {
	int key;
	Tree left;
	Tree right;
	
	public Tree(int key) {
		this.key = key;
	}
	
	public Tree insertNode(Tree node, int key) {
		Tree newNode = null;
		
		if (node == null) {
			return new Tree(key);
		}
		if (key < node.key) { //삽입하려는 값이 부모노드보다 작으면 왼쪽 자식노드로 추가
			newNode = insertNode(node.left, key);
			node.left = newNode;
		} else if (key > node.key) { //삽입하려는 값이 부모노드보다 크면 오른쪽 자식노드로 추가
			newNode = insertNode(node.right, key);
			node.right = newNode;
		}
		
		return node;
	}
	
	//후위순회
	public void postorder(Tree root) {
		if (root.left != null) postorder(root.left);
		if (root.right != null) postorder(root.right);
		System.out.println(root.key);
	}
}

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int data = sc.nextInt();
		Tree tree = new Tree(data);
		
		while (sc.hasNext()) {
			data = sc.nextInt();
			tree = tree.insertNode(tree, data);	
		}
		tree.postorder(tree);
		
		sc.close();
	}

}
