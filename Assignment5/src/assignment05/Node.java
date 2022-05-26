package assignment05;
public class Node implements NodeFunctions {
	private final int key;
	private Node parent;
	private Node left;
	private Node right;
	
	public Node(int key) {
		this.key = key;
		parent = null;
		left = null;
		right = null;
	}
	
	public int getKey() {
		return this.key;
	}
	
	public Node getParent() {
		return this.parent;
	}
	
	public Node getLeft() {
		return this.left;
	}
	
	public Node getRight() {
		return this.right;
	}
	
	public void setLeft(Node n) {
		this.left = n;
	}
	
	public void setRight(Node n) {
		this.right = n;
	}
	
	public void setParent(Node n) {
		this.parent = n;
	}
	
	public String toString() {
		
		
		
		String returnString = "(";
		
		returnString += (this.getKey() + ",");
		
		if (this.parent != null) {
			returnString += (this.parent.getKey() + ",");
		} else {
			returnString += ",";
		}
		if (this.left != null) {
			returnString += (this.left.getKey() + ",");
		} else {
			returnString += ",";
		}
		if (this.right != null) {
			returnString += (this.right.getKey() + ")");
		} else {
			returnString += ")";
		}
		return returnString;
	}
	
	public boolean equals(Node o) {
		if (o.getKey() == this.getKey()) {
			return true;
		}
		return false;
	}
}
