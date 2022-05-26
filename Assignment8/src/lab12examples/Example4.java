package lab12examples;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class Example4 {
	private static int width = 400;
	private static int height = 400;
	private JFrame frame;
	private String[] buttonLabels = {"top left", "top right",
			"bottom left", "bottom right"};
	private JButton[] buttons = new JButton[buttonLabels.length];

	public static void main(String[] args) {
		// The Java Tutorial says we should: 
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Example4().createAndShowGUI();
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
				
		// create the buttons
		for(int i = 0; i < buttonLabels.length; i++) {
			buttons[i] = new JButton(buttonLabels[i]);
			// add the action listeners to the buttons		
			buttons[i].addActionListener(new ButtonActionListener(buttons[i]));
		}
		
		// create the panel to hold the four buttons
		JPanel buttonPanel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(buttonPanel, BoxLayout.Y_AXIS);
		buttonPanel.setLayout(boxLayout);
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
		
		// create a panel to hold the left buttons
		JPanel topButtonPanel = new JPanel();
		BoxLayout topBoxLayout = new BoxLayout(topButtonPanel, BoxLayout.X_AXIS);
		topButtonPanel.setLayout(topBoxLayout);
		topButtonPanel.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		
		// create a panel to hold the right buttons
		JPanel bottomButtonPanel = new JPanel();
		BoxLayout bottomBoxLayout = new BoxLayout(bottomButtonPanel, BoxLayout.X_AXIS);
		bottomButtonPanel.setLayout(bottomBoxLayout);
		bottomButtonPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 3));
		
		// add the two panels to the main button panel
		buttonPanel.add(topButtonPanel);
		buttonPanel.add(bottomButtonPanel);
		
		// add the buttons to the two panels
		topButtonPanel.add(buttons[0]);
		topButtonPanel.add(buttons[1]);
		bottomButtonPanel.add(buttons[2]);
		bottomButtonPanel.add(buttons[3]);
								
		frame.setContentPane(buttonPanel);
		// create the menu bar and set it in the frame
		JMenuBar menuBar = new JMenuBar();
		populateMenuBar(menuBar);
		frame.setJMenuBar(menuBar);

		frame.validate();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	// action listener for the buttons
	class ButtonActionListener implements ActionListener {
		// the button associated with the action listener, so that we can
		// share this one class with multiple buttons
		private javax.swing.JButton btn;
		
		ButtonActionListener(JButton b)	{
			this.btn = b;
		}
		
		public void actionPerformed(ActionEvent e) {
			System.out.println("action performed on " + btn.getText() + " button");
		}
	}
	
	// action listener for the menu items
	class MenuItemActionListener implements ActionListener {
		// the menu item associated with the action listener, so that we can
		// share this one class with multiple menu items
		private javax.swing.JMenuItem mi;
		
		MenuItemActionListener(JMenuItem m)	{
			this.mi = m;
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
				javax.swing.JOptionPane.showOptionDialog(null, "This is unimplemented,\nclick OK to continue", 
						"Warning", javax.swing.JOptionPane.DEFAULT_OPTION, 
						javax.swing.JOptionPane.WARNING_MESSAGE, null, options, options[0]);
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
