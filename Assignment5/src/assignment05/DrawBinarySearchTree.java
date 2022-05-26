package assignment05;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

class DrawBinarySearchTree {
	private static int defaultWidth = 600;
	private static int defaultHeight = 600;
	
	public static void main(String[] args) {
		if( args.length < 1 )
		{
			System.out.println("format: drawBinarySearchTree \"input file\" \"<width>\" \"<height>\"");
			System.exit(0);
		}
		
		DrawingArea da;
		JFrame f;
		JPanel daPanel;
		JPanel mainPanel;
		JScrollPane scrollPane;
		
		int width = defaultWidth;
		int height = defaultHeight;
		
		if(args.length > 1) {
			width = Integer.parseInt(args[1]);
		}
		
		if(args.length > 2) {
			height = Integer.parseInt(args[2]);
		}

		
		f = new JFrame();
		da = new DrawingArea(args[0]);
		
		daPanel = new JPanel();
		daPanel.setLayout(new BorderLayout());
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		daPanel.add(da, BorderLayout.CENTER);
		daPanel.validate();
		daPanel.setVisible(true);
		f.addKeyListener(da);
		
		scrollPane = new JScrollPane(daPanel);
		scrollPane.validate();
		scrollPane.setVisible(true);
		mainPanel.add(scrollPane, java.awt.BorderLayout.CENTER);
		
		f.setPreferredSize(new java.awt.Dimension(width, height));
		f.setMinimumSize(new java.awt.Dimension(width, height));
		f.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		
		f.setContentPane(mainPanel);
		f.validate();
		f.setVisible(true);
	}
}
