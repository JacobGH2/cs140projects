package assignment8;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;



// implement leftMethods and rightMethods

public class assignment8 {
	private int width = 700;
	//private int width2 = 310;
	private int height = 350;
	private static final int borderWidth = 10;
	public static int NUM_BUTTONS = 8;
	private JFrame frame;
	private String[] leftButtonNames = {"sort ints", "add to bst", 
			"add to treeset", "add to priority queue", "add to hashset",
			"add to arraylist", "add to sorted arraylist", "add to array"};
	private String[] rightButtonNames = {"search sorted ints", "search bst", 
			"search treeset", "search priority queue", "search hashset", 
			"search arraylist", "search sorted arraylist", "search array"};
	private JButton[] leftButtons = new JButton[NUM_BUTTONS];
	private JButton[] rightButtons = new JButton[NUM_BUTTONS];
	private JLabel[] leftLabels = new JLabel[NUM_BUTTONS];
	private JLabel[] rightLabels = new JLabel[NUM_BUTTONS];
	//private LeftMethods[] leftMethods = new LeftMethods[NUM_METHODS];
	
	private File sortFile;
	private File searchFile;
	
	private LeftMethods[] leftMethods = new LeftMethods[8];
	private RightMethods[] rightMethods = new RightMethods[8];
	
	private int[] sortData; //= new int[1000];
	private int[] searchData; //= new int[1000];
	
	private int[] copyOfSortData;
	private BinarySearchTree tree;
	private TreeSet<Integer> treeset = new TreeSet<>();
	private PriorityQueue<Integer> queue = new PriorityQueue<>();
	private HashSet<Integer> hashset = new HashSet<>();
	private ArrayList<Integer> list = new ArrayList<>();
	private ArrayList<Integer> list2 = new ArrayList<>();
	private int[] unsortedCopy;
	public State state = State.NOTHING_LOADED;
	
	
	public static void main(String[] args) {
		// The Java Tutorial says we should: 
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new assignment8().createAndShowGUI();
			}
		});
	}
	
	void populateMenuBar(JMenuBar menuBar) {
		// create the two menus
		JMenu fileMenu = new JMenu("File");
		
		fileMenu.setMnemonic(KeyEvent.VK_F); // ALT-F option
		
		// create the menu items for the two menus
		JMenuItem readSortFile = new JMenuItem("Read Sort File");
		JMenuItem readSearchFile = new JMenuItem("Read Search File");
		JMenuItem reset = new JMenuItem("Reset");
		JMenuItem exit = new JMenuItem("Exit");

		exit.setMnemonic(KeyEvent.VK_E); // ALT-F, ALT-E option
		reset.setMnemonic(KeyEvent.VK_R);
		readSearchFile.setMnemonic(KeyEvent.VK_S); // ALT-F, ALT-L option
		readSortFile.setMnemonic(KeyEvent.VK_O);
		
		exit.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_E, ActionEvent.CTRL_MASK)); // CTRL-E shortcut
		reset.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		readSearchFile.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, ActionEvent.CTRL_MASK)); // CTRL-L shortcut
		readSortFile.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		// add the two menus to the menu bar
		menuBar.add(fileMenu);
		
		// add the two menu items to the two menus
		fileMenu.add(readSortFile);
		fileMenu.add(readSearchFile);
		fileMenu.add(reset);
		fileMenu.add(exit);

		// add the action listeners to the menu items
		exit.addActionListener(new MenuItemActionListener(exit));
		reset.addActionListener(new MenuItemActionListener(reset));
		readSearchFile.addActionListener(new MenuItemActionListener(readSearchFile));
		readSortFile.addActionListener(new MenuItemActionListener(readSortFile));
	}
	
	public void createAndShowGUI() {
		
		if (leftButtonNames.length != NUM_BUTTONS || rightButtonNames.length != NUM_BUTTONS) {
			throw new RuntimeException("Number of button labels and buttons do not match");
		}
		
		// create the window and specify the size and what to do when the window is closed
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));	
		// specify how the program will exit when the frame is closed
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowClosingListener());
				
		// create main frame
		JPanel buttonPanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(buttonPanel, BoxLayout.X_AXIS);
		buttonPanel.setLayout(boxLayout);
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		// create the left buttons
		for(int i = 0; i < leftButtonNames.length; i++) {
			leftButtons[i] = new JButton(leftButtonNames[i]);
			// add the action listeners to the buttons		
			leftButtons[i].addActionListener(new ButtonActionListener(leftButtons[i], i, true));
			leftLabels[i] = new JLabel("no result");
		} 

		// create the right buttons
		for(int i = 0; i < rightButtonNames.length; i++) {
			rightButtons[i] = new JButton(rightButtonNames[i]);
			// add the action listeners to the buttons		
			rightButtons[i].addActionListener(new ButtonActionListener(rightButtons[i], i, false));
			rightLabels[i] = new JLabel("no result");
		} 
		
		// left panel
		JPanel leftButtonPanel = new JPanel();
		leftButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, borderWidth));
		makePanel(leftButtonPanel, leftButtons, leftLabels);
	
		// right panel
		JPanel rightButtonPanel = new JPanel();
		rightButtonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, borderWidth));
		makePanel(rightButtonPanel, rightButtons, rightLabels);
		
		buttonPanel.add(leftButtonPanel);
		buttonPanel.add(rightButtonPanel);
		
		// create the menu bar and set it in the frame
		JMenuBar menuBar = new JMenuBar();
		populateMenuBar(menuBar);
		frame.setJMenuBar(menuBar);
		
		populateMethods();

		frame.setContentPane(buttonPanel);
		frame.validate();
		frame.setLocationRelativeTo(null);
		guiUpdate();
		frame.setVisible(true);
	}
	
	private void makePanel(JPanel panel, JButton[] bArray, JLabel[] lArray) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		panel.setLayout(gridBagLayout);
		GridBagConstraints panelConstraints = new GridBagConstraints();
		panelConstraints.weightx = 1;
		panelConstraints.weighty = 1;
		panelConstraints.fill = 0;
		panelConstraints.anchor = GridBagConstraints.LINE_START;
		for (int i = 0; i < bArray.length; i++) {
			panelConstraints.gridx = 0;
			panelConstraints.gridy = i;
			panelConstraints.gridwidth = 1;
			if (panelConstraints.gridy == 0) panelConstraints.insets = new Insets(10, 10, 10, 10);
			else panelConstraints.insets = new Insets(0,10,10,10);
			gridBagLayout.setConstraints(bArray[i], panelConstraints);
			panel.add(bArray[i]);

		}
		for (int i = 0; i < bArray.length; i++) {
			panelConstraints.gridx = 1;
			panelConstraints.gridy = i;
			panelConstraints.gridwidth = 1;
			if (panelConstraints.gridy == 0) panelConstraints.insets = new Insets(10, 10, 10, 10);
			else panelConstraints.insets = new Insets(0,10,10,10);
			gridBagLayout.setConstraints(lArray[i], panelConstraints);
			panel.add(lArray[i]);

		}
	}
	
	private void exit() {
		int decision = JOptionPane.showConfirmDialog(
				frame, "Do you really wish to exit?",
				"Confirmation", JOptionPane.YES_NO_OPTION);
		if (decision == JOptionPane.YES_OPTION) {
			System.exit(0);
		}		
	}
	
	
	
	
	private class WindowClosingListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			exit();
		}
	}
	
	private class ButtonActionListener implements ActionListener {
		// the button associated with the action listener, so that we can
		// share this one class with multiple buttons
		
		
		// enable buttons in menu listener
		private javax.swing.JButton btn;
		private int index;
		private boolean left;
		
		ButtonActionListener(JButton b, int i, boolean left)	{
			this.btn = b;
			this.index = i;
			this.left = left;
		}
		
		public void actionPerformed(ActionEvent e) {
			if (left) {
				long t0 = System.currentTimeMillis();
				leftMethods[index].leftMethod();
				long t1 = System.currentTimeMillis();
				leftLabels[index].setText((t1 - t0) + "ms");
				State.setLeftButtonClicked(index, true);
				guiUpdate();
			}
			
			if (!left) {
				int foundCount;
				long m0 = System.currentTimeMillis();
				foundCount = rightMethods[index].rightMethodInt();
				long m1 = System.currentTimeMillis();
				rightLabels[index].setText(foundCount + " / " + (m1-m0) + "ms");
			}
		}
	}
	
	class MenuItemActionListener implements ActionListener {
		// the menu item associated with the action listener, so that we can
		// share this one class with multiple menu items
		private javax.swing.JMenuItem mi;
		private JFrame frame = new JFrame("Project");
		private FilesMgr filesMgr = new FilesMgr(frame);
		
		MenuItemActionListener(JMenuItem m)	{
			this.mi = m;
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("action performed on " + mi.getText() + " menu item");

			// if exit is selected from the file menu, exit the program
			if(mi.getText().toLowerCase().equals("exit")) {
				exit();
			}
			if (mi.getText().toLowerCase().equals("reset")) {
				state = state.reset();
				guiUpdate();
				for (JLabel j : leftLabels) {
					j.setText("no result");
				}
				for (JLabel r : rightLabels) {
					r.setText("no result");
				}
				guiUpdate();
				
			} 
			
			
			if (mi.getText().toLowerCase().equals("read sort file")) {
			
				sortFile = filesMgr.identifyFile("Sort File");
					
				
				if (sortFile != null) {
					try {
						readData(sortFile, true);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					state = state.loadSortFile();
					guiUpdate();
					
				}
			}
			
			if (mi.getText().toLowerCase().equals("read search file")) {

				searchFile = filesMgr.identifyFile("Search file");

				if (searchFile != null) {
					try {
						readData(searchFile, false);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					state = state.loadSearchFile();
					guiUpdate();
					
				}
				
			}
		}
	}
	
	
	
	
	
	private void populateMethods() {
		leftMethods[0] = new LeftMethods() {
			
			public void leftMethod() {
				copyOfSortData = new int[sortData.length];
				
				for (int k = 0; k < sortData.length; k++) {
					copyOfSortData[k] = sortData[k];
				}
				
				// iterate through array
		        for (int i = 0; i < copyOfSortData.length - 1; i++) {
		            
		            int minIndex = i;
		        	
		        	for (int j = i + 1; j < copyOfSortData.length; j++) {
		                if (copyOfSortData[j] < copyOfSortData[minIndex]) {
		                    minIndex = j;
		                }
		        	}
		            // swap new minimum with current minIndex
		            int temp = copyOfSortData[minIndex];
		            copyOfSortData[minIndex] = copyOfSortData[i];
		            copyOfSortData[i] = temp;
		        }
			}
		};
		leftMethods[1] = new LeftMethods() {
			public void leftMethod() {
				tree = new BinarySearchTree();
				for (int i = 0; i < sortData.length; i++) {
					Node n = new Node(sortData[i]);
					tree.insertNode(n);
				}
				}
		};
		leftMethods[2] = new LeftMethods() {
			public void leftMethod() {
				if (!treeset.isEmpty()) treeset.clear();
				for (int i = 0; i < sortData.length; i++) {
					treeset.add(sortData[i]);
				}
			}
		};
		leftMethods[3] = new LeftMethods() {
			public void leftMethod() {
				if (!queue.isEmpty()) queue.clear();
				for (int i = 0; i < sortData.length; i++) {
					queue.add(sortData[i]);
				}
			}
		};
		leftMethods[4] = new LeftMethods() {
			public void leftMethod() {
				if(hashset.size() > 0) {
					hashset.clear();
				}
				for (int i = 0; i < sortData.length; i++) {
					hashset.add(Integer.valueOf(sortData[i]));
				}
				System.out.println("hashSetValues.size() = " + hashset.size());
			}
		};
		leftMethods[5] = new LeftMethods() {
			public void leftMethod() {
				if (!list.isEmpty()) list.clear();
				for (int i = 0; i < sortData.length; i++) {
					list.add(sortData[i]);
				}
			}
		};
		leftMethods[6] = new LeftMethods() {
			public void leftMethod() {
				if (!list2.isEmpty()) list2.clear();
				for (int i = 0; i < sortData.length; i++) {
					list2.add(sortData[i]);
				}
				Collections.sort(list2);
			}
			};
		leftMethods[7] = new LeftMethods() {
			public void leftMethod() {
				unsortedCopy = new int[sortData.length];
				for (int i = 0; i < sortData.length; i++) {
					unsortedCopy[i] = sortData[i];
				}
			}
		};
		rightMethods[0] = new RightMethods() {
			public int rightMethodInt() {
				int foundCount = 0;
				for(int i = 0; i < searchData.length; i++) {
					// REPLACE WITH BINARY SEARCH
					int searchValue = searchData[i];
					int bottom = 0;
					int top = copyOfSortData.length - 1;
					while (bottom <= top) {
						int middle = (bottom+top)/2;
						if (searchValue < copyOfSortData[middle]) {
							top = middle - 1;
						} else {
							if (searchValue > copyOfSortData[middle]) {
								bottom = middle+1;
							} else {
								foundCount++;
								bottom = top + 1;

							}
						}
					}
				}
				System.out.println("foundCount = " + foundCount);
				return foundCount;

			}
		};
		rightMethods[1] = new RightMethods() {
			public int rightMethodInt() {
				int foundCount = 0;
				for(int i = 0; i < searchData.length; i++) {
					if ((tree.getNode(tree.getRoot(),Integer.valueOf(searchData[i])) != null)) {
						foundCount++;
					}
				}
				System.out.println("foundCount = " + foundCount);
				return foundCount;
				// menu listener for exit, reset, sort, search
				// button listener states, left and right execution
			}
		};
		rightMethods[2] = new RightMethods() {
			public int rightMethodInt() {
				int foundCount = 0;
				for(int i = 0; i < searchData.length; i++) {
					if (treeset.contains(Integer.valueOf(searchData[i])))
						foundCount++;
				}
				System.out.println("foundCount = " + foundCount);
				return foundCount;
			}
		};
		rightMethods[3] = new RightMethods() {
			public int rightMethodInt() {
				int foundCount = 0;
				for(int i = 0; i < searchData.length; i++) {
					if (queue.contains(Integer.valueOf(searchData[i])))
						foundCount++;
				}
				System.out.println("foundCount = " + foundCount);
				return foundCount;
			}
		};
		rightMethods[4] = new RightMethods() {
			public int rightMethodInt() {
				int foundCount = 0;
				for(int i = 0; i < searchData.length; i++) {
					if (hashset.contains(Integer.valueOf(searchData[i])))
						foundCount++;
				}
				System.out.println("foundCount = " + foundCount);
				return foundCount;
			}
		};
		rightMethods[5] = new RightMethods() {
			public int rightMethodInt() {
				int foundCount = 0;
				for(int i = 0; i < searchData.length; i++) {
					if (list.contains(Integer.valueOf(searchData[i])))
						foundCount++;
				}
				System.out.println("foundCount = " + foundCount);
				return foundCount;
			}
		};
		rightMethods[6] = new RightMethods() {
			public int rightMethodInt() {
				int foundCount = 0;
				for(int i = 0; i < searchData.length; i++) {
					if (Collections.binarySearch(list2,Integer.valueOf(searchData[i])) >= 0) {
						foundCount++;
					}
						
				}
				System.out.println("foundCount = " + foundCount);
				return foundCount;
			}
		}; 
		rightMethods[7] = new RightMethods() {
			// may need to stop after first time finding in sortedCopy
			public int rightMethodInt() {
				int foundCount = 0;
				for(int i = 0; i < searchData.length; i++) {
					for (int j : unsortedCopy) {
						if (j == searchData[i])
							foundCount++;
					}
				}
				System.out.println("foundCount = " + foundCount);
				return foundCount;
			}
		};
		
		


	}
	
	public void guiUpdate() {
		//state = State.SORT_FILE_LOADED;
		for (int i = 0; i < NUM_BUTTONS; i++) {
			leftButtons[i].setEnabled(state.leftButtonEnabled(i));
			rightButtons[i].setEnabled(state.rightButtonEnabled(i));
			
		}
	}
	
	public void readData(File dataFile, boolean sort) throws FileNotFoundException {
		ArrayList<Integer> values = new ArrayList<>();
		Scanner s = new Scanner(dataFile);
		while (s.hasNextLine()) {
			String inn = s.nextLine();
			if (inn.trim().length() > 0	 ) {
				values.add(Integer.valueOf(inn.trim()));
			}
		}
		
		s.close();
		
		
		if (sort) {
			sortData = new int[values.size()];
			for (int i = 0; i < values.size(); i++) {
				sortData[i] = values.get(i);
			}
		} else {
			searchData = new int[values.size()];
			for (int i = 0; i < values.size(); i++) {
				searchData[i] = values.get(i);
			}
		}
		
		
	}
}
