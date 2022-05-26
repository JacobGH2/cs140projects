package assignment05;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class BinarySearchTreeTest 
{
	// use this binary search tree for the smaller trees
	BinarySearchTree tree;
	
	// use this binary search tree for the 31 key tree
	BinarySearchTree t;
	
	// test node 20 added correctly
//NOTE THESE ARE GIVEN VALUES IN THE static block BELOW
	static String[] checkInOrderWalkInsertRootArray = {"(20,,,)"};
	static ArrayList<String> checkInOrderWalkInsertRoot = 
			new ArrayList<>(Arrays.asList(checkInOrderWalkInsertRootArray));
	
	// test nodes 20 and 10 added correctly
	static String[] checkInOrderWalkInsertTwoNodesArray = {"(10,20,,)", "(20,,10,)"};
	static java.util.ArrayList<String> checkInOrderWalkInsertTwoNodes =
			new ArrayList<>(Arrays.asList(checkInOrderWalkInsertTwoNodesArray));
		
	// test nodes 20 and 30 added correctly
	static String[] checkInOrderWalkInsertTwoNodesBArray = {"(20,,,30)", "(30,20,,)"};
	static java.util.ArrayList<String> checkInOrderWalkInsertTwoNodesB =
			new ArrayList<>(Arrays.asList(checkInOrderWalkInsertTwoNodesBArray));
		
	// test nodes 20, 10, 30 added correctly
	static String[] checkInOrderWalkInsertThreeNodesArray = {"(10,20,,)", "(20,,10,30)", "(30,20,,)"};
	static java.util.ArrayList<String> checkInOrderWalkInsertThreeNodes =
			new ArrayList<>(Arrays.asList(checkInOrderWalkInsertThreeNodesArray));
	
	// test nodes 20, 10, 30, 5, 15, 25, 35 added correctly
	static String[] checkInOrderWalkInsertSevenNodesArray = {"(5,10,,)", "(10,20,5,15)",
			"(15,10,,)", "(20,,10,30)", "(25,30,,)", "(30,20,25,35)", "(35,30,,)"};
	static java.util.ArrayList<String> checkInOrderWalkInsertSevenNodes = 
			new ArrayList<>(Arrays.asList(checkInOrderWalkInsertSevenNodesArray));
	
	// test nodes 50, 25, 75, 15, 40, 60, 90, 10, 20, 30, 45, 55, 
	// 70, 80, 95, 8, 12, 28, 4, 9, 11, 13, 27, 29, 53, 57, 54, 56, 
	// 58, 93, 91 added correctly
	static String[] checkInOrderWalkInsertThirtyOneNodesArray = {"(4,8,,)", "(8,10,4,9)",
			"(9,8,,)", "(10,15,8,12)", "(11,12,,)", "(12,10,11,13)", "(13,12,,)", "(15,25,10,20)",
			"(20,15,,)", "(25,50,15,40)", "(27,28,,)", "(28,30,27,29)", "(29,28,,)", 
			"(30,40,28,)", "(40,25,30,45)", "(45,40,,)", "(50,,25,75)", "(53,55,,54)",
			"(54,53,,)", "(55,60,53,57)", "(56,57,,)", "(57,55,56,58)", "(58,57,,)",
			"(60,75,55,70)", "(70,60,,)", "(75,50,60,90)", "(80,90,,)", "(90,75,80,95)",
			"(91,93,,)", "(93,95,91,)", "(95,90,93,)"};
	static java.util.ArrayList<String> checkInOrderWalkInsertThirtyOneNodes = 
			new ArrayList<>(Arrays.asList(checkInOrderWalkInsertThirtyOneNodesArray));
	
	// delete node 45 from the 31 node bst
	static String[] checkInOrderWalkPostDelete45Array = {"(4,8,,)",	"(8,10,4,9)",
			"(9,8,,)", "(10,15,8,12)", "(11,12,,)", "(12,10,11,13)", "(13,12,,)",
			"(15,25,10,20)", "(20,15,,)", "(25,50,15,40)", "(27,28,,)", "(28,30,27,29)",
			"(29,28,,)", "(30,40,28,)", "(40,25,30,)", "(50,,25,75)", "(53,55,,54)",
			"(54,53,,)", "(55,60,53,57)", "(56,57,,)", "(57,55,56,58)", "(58,57,,)",
			"(60,75,55,70)", "(70,60,,)", "(75,50,60,90)", "(80,90,,)", "(90,75,80,95)",
			"(91,93,,)", "(93,95,91,)", "(95,90,93,)"}; 
	static java.util.ArrayList<String> checkInOrderWalkPostDelete45 =
			new ArrayList<>(Arrays.asList(checkInOrderWalkPostDelete45Array));

	// delete nodes 45 and 95 from the 31 node bst
	static String[] checkInOrderWalkPostDelete45And95Array = {"(4,8,,)", "(8,10,4,9)", 
			"(9,8,,)", "(10,15,8,12)", "(11,12,,)", "(12,10,11,13)", "(13,12,,)", 
			"(15,25,10,20)", "(20,15,,)", "(25,50,15,40)", "(27,28,,)", "(28,30,27,29)", 
			"(29,28,,)", "(30,40,28,)", "(40,25,30,)", "(50,,25,75)", "(53,55,,54)", 
			"(54,53,,)", "(55,60,53,57)", "(56,57,,)", "(57,55,56,58)", "(58,57,,)", 
			"(60,75,55,70)", "(70,60,,)", "(75,50,60,90)", "(80,90,,)", "(90,75,80,93)", 
			"(91,93,,)", "(93,90,91,)"};
	static java.util.ArrayList<String> checkInOrderWalkPostDelete45And95 =
			new ArrayList<>(Arrays.asList(checkInOrderWalkPostDelete45And95Array));
	
	// delete nodes 45, 95, and 60 from the 31 node bst
	static String[] checkInOrderWalkPostDelete45And95And60Array = {"(4,8,,)", "(8,10,4,9)",
			"(9,8,,)", "(10,15,8,12)", "(11,12,,)", "(12,10,11,13)", "(13,12,,)",
			"(15,25,10,20)", "(20,15,,)", "(25,50,15,40)", "(27,28,,)", "(28,30,27,29)",
			"(29,28,,)", "(30,40,28,)", "(40,25,30,)", "(50,,25,75)", "(53,55,,54)",
			"(54,53,,)", "(55,70,53,57)", "(56,57,,)", "(57,55,56,58)", "(58,57,,)",
			"(70,75,55,)", "(75,50,70,90)",	"(80,90,,)", "(90,75,80,93)", "(91,93,,)", 
			"(93,90,91,)"};
	static java.util.ArrayList<String> checkInOrderWalkPostDelete45And95And60 =
			new ArrayList<>(Arrays.asList(checkInOrderWalkPostDelete45And95And60Array));
	
	// delete nodes 45, 95, 60, and 50 from the 31 node bst
	static String[] checkInOrderWalkPostDelete45And95And60And50Array ={"(4,8,,)", "(8,10,4,9)",
            "(9,8,,)", "(10,15,8,12)", "(11,12,,)", "(12,10,11,13)", "(13,12,,)",
            "(15,25,10,20)", "(20,15,,)", "(25,53,15,40)", "(27,28,,)", "(28,30,27,29)",
            "(29,28,,)", "(30,40,28,)", "(40,25,30,)", "(53,,25,75)", "(54,55,,)", 
            "(55,70,54,57)", "(56,57,,)", "(57,55,56,58)", "(58,57,,)", "(70,75,55,)", 
            "(75,53,70,90)", "(80,90,,)", "(90,75,80,93)", "(91,93,,)", "(93,90,91,)"};
	static java.util.ArrayList<String> checkInOrderWalkPostDelete45And95And60And50 = 
			new ArrayList<>(Arrays.asList(checkInOrderWalkPostDelete45And95And60And50Array));

	@BeforeEach
	void setUp() throws Exception {
		// tree to use for a few of the smaller insert key tests
		tree = new BinarySearchTree();
		
		// tree to use for the tests related to the 31 key tree
		t = new BinarySearchTree();
		
		// the list of 31 keys, will be used in multiple tests
		int[] keys = new int[] {50, 25, 75, 15, 40, 60, 90, 10, 20, 30, 45, 55, 
				70, 80, 95, 8, 12, 28, 4, 9, 11, 13, 27, 29, 53, 57, 54, 56, 58, 93, 91};
		
		// add the 31 keys to the tree
		for(int i = 0; i < keys.length; i++)
		{
			Node n = new Node(keys[i]);
			t.insertNode(n);
		}
	}

	@Test
	void testInsertRoot() {
		// define one key to add to the tree
		int[] keys = new int[] {20};
		
		// add the key(s)
		for(int i = 0; i < keys.length; i++) {
			Node n = new Node(keys[i]);
			tree.insertNode(n);
		}
		
		// get an in order walk to verify the nodes are in the proper locations
		java.util.ArrayList<String> inOrderWalk = new java.util.ArrayList<>();
		tree.inOrderWalk(tree.getRoot(), inOrderWalk);
		
		// get an array of the in order walk to compare with the expected result
		String[] inOrderWalkArray = inOrderWalk.toArray(new String[0]);
		
		assertArrayEquals(checkInOrderWalkInsertRootArray, inOrderWalkArray);
	}
	
	@Test
	void testInsertTwoNodes() {
		// define two keys to add to the tree
		int[] keys = new int[] {20, 10};
		
		// add the key(s)
		for(int i = 0; i < keys.length; i++) {
			Node n = new Node(keys[i]);
			tree.insertNode(n);
		}
		
		// get an in order walk to verify the nodes are in the proper locations
		java.util.ArrayList<String> inOrderWalk = new java.util.ArrayList<>();
		tree.inOrderWalk(tree.getRoot(), inOrderWalk);
		
		// get an array of the in order walk to compare with the expected result
		String[] inOrderWalkArray = inOrderWalk.toArray(new String[0]);

		assertArrayEquals(checkInOrderWalkInsertTwoNodesArray, inOrderWalkArray);
	}
	
	@Test
	void testInsertTwoNodesB() {
		// define two keys to add to the tree
				int[] keys = new int[] {20, 30};
				
				// add the key(s)
				for(int i = 0; i < keys.length; i++) {
					Node n = new Node(keys[i]);
					tree.insertNode(n);
				}
				
				// get an in order walk to verify the nodes are in the proper locations
				java.util.ArrayList<String> inOrderWalk = new java.util.ArrayList<>();
				tree.inOrderWalk(tree.getRoot(), inOrderWalk);
				
				// get an array of the in order walk to compare with the expected result
				String[] inOrderWalkArray = inOrderWalk.toArray(new String[0]);

				assertArrayEquals(checkInOrderWalkInsertTwoNodesBArray, inOrderWalkArray);
	}
	
	@Test
	void testInsertThreeNodes() {
		// define three keys to add to the tree
		int[] keys = new int[] {20, 10, 30};
		
		// add the key(s)
		for(int i = 0; i < keys.length; i++) {
			Node n = new Node(keys[i]);
			tree.insertNode(n);
		}
		
		// get an in order walk to verify the nodes are in the proper locations
		java.util.ArrayList<String> inOrderWalk = new java.util.ArrayList<>();
		tree.inOrderWalk(tree.getRoot(), inOrderWalk);
		
		
		// get an array of the in order walk to compare with the expected result
		String[] inOrderWalkArray = inOrderWalk.toArray(new String[0]);
				
		assertArrayEquals(checkInOrderWalkInsertThreeNodesArray, inOrderWalkArray);
	}
	
	@Test
	void testInsertSevenNodes() {
		// define seven keys to add to the tree
		int[] keys = new int[] {20, 10, 30, 5, 15, 25, 35};
		
		// add the key(s)
		for(int i = 0; i < keys.length; i++) {
			Node n = new Node(keys[i]);
			tree.insertNode(n);
		}
		
		// get an in order walk to verify the nodes are in the proper locations
		java.util.ArrayList<String> inOrderWalk = new java.util.ArrayList<>();
		tree.inOrderWalk(tree.getRoot(), inOrderWalk);
		
		
		// get an array of the in order walk to compare with the expected result
		String[] inOrderWalkArray = inOrderWalk.toArray(new String[0]);
				
		assertArrayEquals(checkInOrderWalkInsertSevenNodesArray, inOrderWalkArray);
	}
	
	@Test
	void testGetHeightForSevenNodesTree() {
		int[] keys = new int[] {20, 10, 30, 5, 15, 25, 35};
		for(int i = 0; i < keys.length; i++) {
			Node n = new Node(keys[i]);
			tree.insertNode(n);
		}
		assertEquals(2, tree.getHeight(tree.getRoot()));
	}
	
	@Test
	void testGetSuccessorOfRootForSevenNodesTree() {
		int[] keys = new int[] {20, 10, 30, 5, 15, 25, 35};
		for(int i = 0; i < keys.length; i++) {
			Node n = new Node(keys[i]);
			tree.insertNode(n);
		}
		assertEquals("(25,30,,)", tree.getSuccessor(tree.getRoot()).toString());
	}
	
	@Test
	void testGetPredecessorOfRootForSevenNodesTree() {
		int[] keys = new int[] {20, 10, 30, 5, 15, 25, 35};
		for(int i = 0; i < keys.length; i++) {
			Node n = new Node(keys[i]);
			tree.insertNode(n);
		}
		assertEquals("(15,10,,)", tree.getPredecessor(tree.getRoot()).toString());
	}
	
	@Test
	void testInsertThirtyOneNodes() {	
		// get an in order walk to verify the nodes are in the proper locations
		java.util.ArrayList<String> inOrderWalk = new java.util.ArrayList<>();
		tree.inOrderWalk(t.getRoot(), inOrderWalk);
		
		
		// get an array of the in order walk to compare with the expected result
		String[] inOrderWalkArray = inOrderWalk.toArray(new String[0]);
				
		assertArrayEquals(checkInOrderWalkInsertThirtyOneNodesArray, inOrderWalkArray);
	}
	
	@Test
	void testGetHeight() {
		// check the max of the 31 key tree
		assertEquals(5, t.getHeight(t.getRoot()));
	}
	
	@Test
	void testGetHeightForSubtreeRootedAt60() {
		// check the height of a subtree of the 31 key tree
		assertEquals(3, t.getHeight(t.getNode(t.getRoot(), 60)));
	}
	
	@Test
	void testGetMax() {	
		// check the max of the 31 key tree
		assertEquals("(95,90,93,)", t.getMax(t.getRoot()).toString());
	}
		
	@Test
	void testGetMin() {	
		// check the min of the 31 key tree
		assertEquals("(4,8,,)", t.getMin(t.getRoot()).toString());
	}
	
	@Test
	void testGetRoot() {
		// check the max of the 31 key tree
		assertEquals("(50,,25,75)", t.getRoot().toString());
	}
	
	@Test
	void testGetNode() {
		assertEquals("(91,93,,)", t.getNode(t.getRoot(),91).toString());
	}
	
	@Test
	void testGetNodeNotFound() {	
		// check getNode() for key 99 of the 31 key tree, should return
		// null since there is no node with a key of 99
		assertNull(t.getNode(t.getRoot(), 99));
	}
	
	@Test
	void testPredecessorWithNoLeftChild() {	
		// check predecessor for node with key 91 of the 31 key tree
		// the node with key 91 has no left child
		assertEquals("(90,75,80,95)", t.getPredecessor(t.getNode(t.getRoot(), 91)).toString());
	}
	
	@Test
	void testPredecessorWithLefthild() {	
		// check predecessor for node with key 10 of the 31 key tree
		// the node with key 10 has a left child
		assertEquals("(9,8,,)", t.getPredecessor(t.getNode(t.getRoot(), 10)).toString());
	}
	
	@Test
	void testPredecessorOnMin() {
		// check predecessor for node with key 4 of the 31 key tree
		// the node with key 4 is the min key in the tree, so it should return null
		assertNull(t.getPredecessor(t.getNode(t.getRoot(), 4)));
	}
	
	@Test
	void testSuccessorWithNoRightChild() {	
		// check the successor of the node with key 45 of the 31 key tree
		// the node with key 45 has no right child
		assertEquals("(50,,25,75)", t.getSuccessor(t.getNode(t.getRoot(), 45)).toString());
	}
	
	@Test
	void testSuccessorWithRightChild() {
		// check the successor of the node with key 50 of the 31 key tree
		// the node with key 45 has a right child
		assertEquals("(53,55,,54)", t.getSuccessor(t.getNode(t.getRoot(), 50)).toString());
	}
	
	@Test
	void testSuccessorOnMax() {	
		// check the successor of the node with key 95 of the 31 key tree
		// the node with key 95 is the max key of the tree, so it has no successor
		assertNull(t.getSuccessor(t.getNode(t.getRoot(), 95)));
	}
	
	@Test
	void testDeleteNode45() {
		// delete the node with key 45 from the 31 key tree
		int[] keysToDelete = new int[] {45};
		for(int i = 0; i < keysToDelete.length; i++) {
			Node node = t.getNode(t.getRoot(), keysToDelete[i]);
			t.deleteNode(node);
		}
		
		// get an in order walk to verify the nodes are in the proper locations
		java.util.ArrayList<String> inOrderWalk = new java.util.ArrayList<>();
		t.inOrderWalk(t.getRoot(), inOrderWalk);
		
		// get an array of the in order walk to compare with the expected result
		String[] inOrderWalkArray = inOrderWalk.toArray(new String[0]);

		assertArrayEquals(checkInOrderWalkPostDelete45Array, inOrderWalkArray);
	}
	
	@Test
	void testDeleteNode45And95() {
		// delete the node with key 45 from the 31 key tree
		int[] keysToDelete = new int[] {45, 95};
		for(int i = 0; i < keysToDelete.length; i++) {
			Node node = t.getNode(t.getRoot(), keysToDelete[i]);
			t.deleteNode(node);
		}
		
		// get an in order walk to verify the nodes are in the proper locations
		java.util.ArrayList<String> inOrderWalk = new java.util.ArrayList<>();
		t.inOrderWalk(t.getRoot(), inOrderWalk);
		
		// get an array of the in order walk to compare with the expected result
		String[] inOrderWalkArray = inOrderWalk.toArray(new String[0]);

		assertArrayEquals(checkInOrderWalkPostDelete45And95Array, inOrderWalkArray);	
	}
	
	@Test
	void testDeleteNode45And95And60() {
		// delete the nodes with keys 45, 95, and 60 from the 31 key tree
		int[] keysToDelete = new int[] {45, 95, 60};
		for(int i = 0; i < keysToDelete.length; i++) {
			Node node = t.getNode(t.getRoot(), keysToDelete[i]);
			t.deleteNode(node);
		}
		
		// get an in order walk to verify the nodes are in the proper locations
		java.util.ArrayList<String> inOrderWalk = new java.util.ArrayList<>();
		t.inOrderWalk(t.getRoot(), inOrderWalk);
		
		// get an array of the in order walk to compare with the expected result
		String[] inOrderWalkArray = inOrderWalk.toArray(new String[0]);
				
		assertArrayEquals(checkInOrderWalkPostDelete45And95And60Array, inOrderWalkArray);
	}
	
	@Test
	void testDeleteNode45And95And60And50() {
		// delete the nodes with keys 45, 95, 60, and 95 from the 31 key tree
		int[] keysToDelete = new int[] {45, 95, 60, 50};
		for(int i = 0; i < keysToDelete.length; i++) {
			Node node = t.getNode(t.getRoot(), keysToDelete[i]);
			t.deleteNode(node);
		}
		
		// get an in order walk to verify the nodes are in the proper locations
		java.util.ArrayList<String> inOrderWalk = new java.util.ArrayList<>();
		t.inOrderWalk(t.getRoot(), inOrderWalk);
		
		// get an array of the in order walk to compare with the expected result
		String[] inOrderWalkArray = inOrderWalk.toArray(new String[0]);
				
		assertArrayEquals(checkInOrderWalkPostDelete45And95And60And50Array, inOrderWalkArray);
	}
}
