package assignment05;

import java.util.ArrayList;
import java.util.Arrays;

public class BinarySearchTree implements BinarySearchTreeFunctions {
	private Node root;
	//private static Node findNode;
	private static ArrayList<Node> nodeList;
	
	public BinarySearchTree() {
		root = null;
	}
	
	public Node getRoot() {
		return this.root;
	}
	
	public void setRoot(Node root) {
		this.root = root;
	}
		
	public void insertNode(Node z) {
		Node x = this.root;
		Node y = null;
		while (x != null) { 
			y = x;
			if (z.getKey() < x.getKey()) {
				x = x.getLeft();
			} else {
				x = x.getRight();
			}

		}	
		z.setParent(y); 
		if (y == null) {
			setRoot(z);
		} else if (z.getKey() < y.getKey()) {
			y.setLeft(z);
		} else {
			y.setRight(z); 
		}

	}
	
	public void preOrderWalk(Node x) {
		if(x != null) {
			System.out.println(x.toString());
			preOrderWalk(x.getLeft());
			preOrderWalk(x.getRight());
		}
	}
	
	public void preOrderWalk(Node x, java.util.ArrayList<String> list) {
		if(x != null) {
			list.add(x.toString());
			preOrderWalk(x.getLeft(), list);
			preOrderWalk(x.getRight(), list);
		}
	}
	
	public void preOrderWalk(Node x, String id, java.util.ArrayList<String> result)	{
		if(x != null) {
			System.out.println(x.toString() + " " + id);
			result.add(x.getKey() + " " + id);
			preOrderWalk(x.getLeft(), "0"+id, result);
			preOrderWalk(x.getRight(), "1"+id, result);
		}
	}
	
	public void inOrderWalk(Node x) {
		if (x == null) { 
			return; 
		} 
		inOrderWalk(x.getLeft()); 
		nodeList.add(x);
		inOrderWalk(x.getRight());

	}
	
	public void inOrderWalk(Node x, java.util.ArrayList<String> list) {
		if (x == null) { 
			return; 
		} 
		inOrderWalk(x.getLeft(), list); 
		list.add(x.toString()); 
		inOrderWalk(x.getRight(), list);
	}
	
	public void postOrderWalk(Node x) {
		if (x == null) {
			return;
		}
		postOrderWalk(x.getLeft());
		postOrderWalk(x.getRight());
		System.out.println(x.toString());
	}
	
	public void postOrderWalk(Node x, java.util.ArrayList<String> list) {
		if (x == null) {
			return;
		}
		postOrderWalk(x.getLeft(), list);
		postOrderWalk(x.getRight(), list);
		list.add(x.toString());
	}
		
	public Node getMax(Node x) {
		nodeList = new ArrayList<>();
		inOrderWalk(x);
		int max = 0;
		for (Node n: nodeList) {
			if (n.getKey() > max) {
				max = n.getKey();
			}
		}
		return this.getNode(this.getRoot(), max);
	}
	
	public Node getMin(Node x) {
		nodeList = new ArrayList<>();
		inOrderWalk(x);
		int min = nodeList.get(0).getKey();
		for (Node n: nodeList) {
			if (n.getKey() < min) {
				min = n.getKey();
			}
		}
		return this.getNode(this.getRoot(), min); 
	}
	
	public Node getSuccessor(Node x) {
		int lowerKey = x.getKey();
		nodeList = new ArrayList<>();
		inOrderWalk(this.getRoot());
		int[] keyList = new int[nodeList.size()];
		int i = 0;
		for (Node n: nodeList) {
			keyList[i] = n.getKey();
			i++;
		}
		int successor = -1;
		Arrays.sort(keyList);
		if (lowerKey == keyList[nodeList.size()-1]) {
			return null;
		}
		for (i = 1; i <= keyList.length; i++) {
			if (keyList[i-1] == lowerKey) {
				successor = keyList[i];
			}
		}
		if (successor == -1) {
			return null;
		}
		
		return this.getNode(this.getRoot(), successor);
		
	}
	
	public Node getPredecessor(Node x) {
		int higherKey = x.getKey();
		nodeList = new ArrayList<>();
		inOrderWalk(this.getRoot());
		int[] keyList = new int[nodeList.size()];
		int i = 0;
		for (Node n: nodeList) {
			keyList[i] = n.getKey();
			i++;
		}
		int predecessor = -1;
		Arrays.sort(keyList);
		if (higherKey == keyList[0]) {
			return null;
		}
		for (i = keyList.length - 2; i >= 0; i--) {
			if (keyList[i+1] == higherKey) {
				predecessor = keyList[i];
			}
		}
		
		
		return this.getNode(this.getRoot(), predecessor);
	}
	
	/*public Node getNode(Node x, int key) {
		helperIOWalk(x, key);
		return findNode;
	}
	
	
	
	public void helperIOWalk(Node x, int key) {
		
		if (x == null) { 
			return; 
		} 
		helperIOWalk(x.getLeft(), key); 
		if (x.getKey() == key) { 
			findNode = x;
		}
		helperIOWalk(x.getRight(), key);
	} */
	
	public Node getNode(Node root, int targetKey) {
		 if (root == null || root.getKey() == targetKey) 
			 return root;
		 
		 if (root.getKey() < targetKey) 
			 return getNode(root.getRight(), targetKey);
		 
		 return getNode(root.getLeft(), targetKey);
		
	}
	
	public int getHeight(Node x) {
		if (x == null) {
			return -1;
		} else {
			int lDepth = getHeight(x.getLeft());
			int rDepth = getHeight(x.getRight());

			if (lDepth > rDepth) {
				return(lDepth + 1);
			} else {
				return(rDepth + 1);
			}
		}
	}
	
	public void shiftNode(Node u, Node v) {
		if (u.getParent() == null) {
			this.setRoot(v);
		} else {
			if (u == u.getParent().getLeft()) {
				u.getParent().setLeft(v);
			} else {
				u.getParent().setRight(v);
			}
		}
		if (v != null) {
			v.setParent(u.getParent());
		}
	}
	
	public void deleteNode(Node z) {
		if (z.getLeft() == null && z.getRight() == null) {
			 shiftNode(z, z.getRight());
		}
		if (z.getLeft() == null && z.getRight() != null) {
			shiftNode(z, z.getRight());
		}
		if (z.getLeft() != null && z.getRight() == null) {
			shiftNode(z, z.getLeft());
		}
		if (z.getLeft() != null && z.getRight() != null) {
			Node y = this.getSuccessor(z);
			if (y.getParent() != z) {
				shiftNode(y, y.getRight());
				y.setRight(z.getRight());
				y.getRight().setParent(y);
			}
			shiftNode(z, y);
			y.setLeft(z.getLeft());
			y.getLeft().setParent(y);
		}
	}
}