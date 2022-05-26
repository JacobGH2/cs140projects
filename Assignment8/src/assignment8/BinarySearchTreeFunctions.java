package assignment8;
interface BinarySearchTreeFunctions {
	Node getRoot();
	void setRoot(Node root);
	void insertNode(Node z);
	void preOrderWalk(Node x);
	void preOrderWalk(Node x, java.util.ArrayList<String> list);
	void inOrderWalk(Node x);
	void inOrderWalk(Node x, java.util.ArrayList<String> list);
	void postOrderWalk(Node x);
	void postOrderWalk(Node x, java.util.ArrayList<String> list);
	Node getMax(Node x);
	Node getMin(Node x);
	Node getSuccessor(Node x);
	Node getPredecessor(Node x);
	Node getNode(Node x, int key);
	int getHeight(Node x);
	void shiftNode(Node u, Node v);
	void deleteNode(Node z);
}