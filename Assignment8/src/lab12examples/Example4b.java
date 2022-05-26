package lab12examples;

import java.awt.Dimension;



import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class Example4b {
	private static int width = 450;
	private static int height = 300;
	private JFrame frame;
	private String[] buttonLabels = {"row 0 column 0", "row 0 column 1",
			"row 0 column 2", "row 1 column 0 - 1",	"row 1 column 2",
			"row 2 column 0", "row 2 column 1 - 2",	"row 3 column 0",
			"row 3 column 1", "row 3 column 2",	"row 4 column 0",
			"row 4 column 2"};
	private JButton[] buttons;

	public static void main(String[] args) {
		// The Java Tutorial says we should: 
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Example4b().createAndShowGUI();
			}
		});
	}
	
	void populateMenuBar(JMenuBar menuBar) {
		// create the two menus
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");

		fileMenu.setMnemonic(KeyEvent.VK_F); // ALT-F option
		editMenu.setMnemonic(KeyEvent.VK_D); // ALT-D option

		// create the menu items for the two menus
		JMenuItem fileExit = new JMenuItem("Exit");
		JMenuItem editColor = new JMenuItem("Color");

		fileExit.setMnemonic(KeyEvent.VK_E); // ALT-F, ALT-E option
		editColor.setMnemonic(KeyEvent.VK_L); // ALT-F, ALT-L option
		
		fileExit.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_E, ActionEvent.CTRL_MASK)); // CTRL-E shortcut
		editColor.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_L, ActionEvent.CTRL_MASK)); // CTRL-L shortcut

		// add the two menus to the menu bar
		menuBar.add(fileMenu);
		menuBar.add(editMenu);

		// add the two menu items to the two menus
		fileMenu.add(fileExit);
		editMenu.add(editColor);

		// add the action listeners to the menu items
		fileExit.addActionListener(new MenuItemActionListener(fileExit));
		editColor.addActionListener(new MenuItemActionListener(editColor));		
	}

	public void createAndShowGUI() {

		// create the window and specify the size and what to do when the window is closed
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));

		// specify how the program will exit when the frame is closed
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowClosingListener());

		// create the panel to hold the four buttons
		JPanel buttonPanel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		buttonPanel.setLayout(gridBagLayout);
		GridBagConstraints buttonPanelConstraints = new GridBagConstraints();

		// make the weights non zero so that the components spread out 
		buttonPanelConstraints.weightx = 1;
		buttonPanelConstraints.weighty = 1;

		// have the components fill all of the cells that they occupy 
		buttonPanelConstraints.fill = GridBagConstraints.HORIZONTAL;

		// create the buttons array
		buttons = new JButton[buttonLabels.length];

		int j = 0;
		int[][] constraints = new int[buttons.length][3];
		// button row 1
		// put button at (0, 0)
		constraints[j++] = new int[] {0, 0, 1};
		// put button at (0, 1)
		constraints[j++] = new int[] {1, 0, 1};
		// put button at (0, 2)
		constraints[j++] = new int[] {2, 0, GridBagConstraints.REMAINDER};
		// button row 2
		// put button at (1, 0)
		constraints[j++] = new int[] {0, 1, 2};
		// put button at (1, 1)
		constraints[j++] = new int[] {2, 1, GridBagConstraints.REMAINDER};
		// button row 3
		// put button at (2, 0)
		constraints[j++] = new int[] {0, 2, 1};
		// put button at (2, 1)
		constraints[j++] = new int[] {1, 2, GridBagConstraints.REMAINDER};
		// button row 4
		// put button at (3, 0)
		constraints[j++] = new int[] {0, 3, 1};
		// put button at (3, 1)
		constraints[j++] = new int[] {1, 3, 1};
		// put button at (3, 2)
		constraints[j++] = new int[] {2, 3, GridBagConstraints.REMAINDER};
		// button row 5
		// put button at (4, 0)
		constraints[j++] = new int[] {0, 4, 1};
		// put button at (4, 2)
		constraints[j++] = new int[] {2, 4, 1};
		
		buttonPanelConstraints.insets = new Insets(10,10,10,10);
		for(int i = 0; i < buttons.length; i++) {
			buttonPanelConstraints.gridx = constraints[i][0];
			buttonPanelConstraints.gridy = constraints[i][1];
			buttonPanelConstraints.gridwidth = constraints[i][2];
			if(buttonPanelConstraints.gridy == 0) buttonPanelConstraints.insets = new Insets(10,10,10,10);
			else buttonPanelConstraints.insets = new Insets(0,10,10,10);
			// create a button
		    buttons[i] = new JButton(buttonLabels[i]);
			gridBagLayout.setConstraints(buttons[i], buttonPanelConstraints);
			buttonPanel.add(buttons[i]);
			// add the action listeners to the buttons
			buttons[i].addActionListener(new ButtonActionListener(i));
		}

		// create the menu bar and set it in the frame
		JMenuBar menuBar = new JMenuBar();
		populateMenuBar(menuBar);
		frame.setJMenuBar(menuBar);

		frame.setContentPane(buttonPanel);
		frame.validate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	// action listener for the buttons
	class ButtonActionListener implements ActionListener {
		// the button associated with the action listener, so that we can
		// share this one class with multiple buttons
		private JButton btn;

		ButtonActionListener(int i) {
			btn = buttons[i];
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("action performed on " + btn.getText() + " button");
		}
	}

	// action listener for the menu items
	class MenuItemActionListener implements ActionListener {
		// the menu item associated with the action listener, so that we can
		// share this one class with multiple menu items
		private JMenuItem mi;

		MenuItemActionListener(JMenuItem m) {
			mi = m;
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("action performed on " + mi.getText() + " menu item");

			// if exit is selected from the file menu, exit the program
			if(mi.getText().toLowerCase().equals("exit")) {
				exit();
			}

			// if color is selected from the edit menu, put a popup on the screen 
			// saying something 
			if(mi.getText().toLowerCase().equals("color")) {
				Object[] options = {"OK"};
				JOptionPane.showOptionDialog(null, "This is unimplemented,\nclick OK to continue", 
						"Warning", JOptionPane.DEFAULT_OPTION, 
						JOptionPane.WARNING_MESSAGE, null, options, options[0]);
			}
		}
	}

	// JDialog to confirm exit
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
}
