package assignment05;
class TestBinarySearchTree {
	public static void main(String[] args) {
		// create the bst
		BinarySearchTree tree = new BinarySearchTree();
		
		// add nodes for the keys provided on the command line
		for(int i = 0; i < args.length; i++) {
			int key = Integer.parseInt(args[i]);
			Node n = new Node(key);
			tree.insertNode(n);
		}
		
		// print out the information required to draw the tree
		java.util.ArrayList<String> treeInfo = new java.util.ArrayList<>();
		tree.preOrderWalk(tree.getRoot(), "", treeInfo);
		System.out.println("#start of treeInfo");
		for(String node : treeInfo) {
			System.out.println(node);
		}
		System.out.println("#end of treeInfo");
		
		// try out some functions
		System.out.println("tree.getRoot() = " + tree.getRoot());
		System.out.println("tree.getPredecessor(" + tree.getRoot() + ") = " + tree.getPredecessor(tree.getRoot()));
		System.out.println("tree.getSuccessor(" + tree.getRoot() + ") = " + tree.getSuccessor(tree.getRoot()));
		System.out.println("tree.getMin(" + tree.getRoot() + ") = " + tree.getMin(tree.getRoot()));
		System.out.println("tree.getMax(" + tree.getRoot() + ") = " + tree.getMax(tree.getRoot()));
		System.out.println("tree.getHeight(" + tree.getRoot() + ") = " + tree.getHeight(tree.getRoot()));
		
		// define some keys to be deleted from the tree
		int[] keysToDelete = new int[] {};
		for(int i = 0; i < keysToDelete.length; i++) {
			// get the corresponding node to delete
			Node nodeToDelete = tree.getNode(tree.getRoot(), keysToDelete[i]);
			
			// delete the node
			tree.deleteNode(nodeToDelete);
			
			// get the information to draw the updated tree
			treeInfo = new java.util.ArrayList<>();
			tree.preOrderWalk(tree.getRoot(), "", treeInfo);
			System.out.println("#start of treeInfo");
			for(String node : treeInfo) {
				System.out.println(node);
			}
			System.out.println("#end of treeInfo");
		}
	}
}
