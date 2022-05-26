package assignment05;
interface NodeFunctions {
	int getKey();
	Node getParent();
	Node getLeft();
	Node getRight();
	void setLeft(Node n);
	void setRight(Node n);
	void setParent(Node n);
	String toString();
	boolean equals(Object o);
}