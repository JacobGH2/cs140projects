package assignment05;

import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import javax.swing.JPanel;

public class DrawingArea extends JPanel implements KeyListener {
	private static final long serialVersionUID = 5739900385175277532L;
	private int maxWidth, maxHeight;
	private java.awt.image.BufferedImage bi;
	private java.awt.Color backgroundColor, foregroundColor;
	private java.awt.Graphics2D biG;
	private int rowHeight = 36;
	private int colWidth = 36;
	private int halfNodeSize = 12;
	private int nodeSize = 2*halfNodeSize;
	private int offsetY = 20;
	private int offsetX = 20;
	private String filename;
	
	public DrawingArea(String filename) {
		this.filename = filename;
		java.awt.Dimension maxDim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		maxWidth = (int) Math.max(2592, maxDim.getWidth());
		maxHeight = (int) Math.max(1944, maxDim.getHeight());
		setPreferredSize(new java.awt.Dimension(maxWidth, maxHeight));
		bi = java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().createCompatibleImage(maxWidth, maxHeight, java.awt.image.BufferedImage.TYPE_INT_ARGB);
		biG = bi.createGraphics();
		
		foregroundColor = new java.awt.Color(0, 0, 0, 255);
		backgroundColor = new java.awt.Color(255, 255, 255, 255);
		
		readFileAndDrawTree();
	}
	
	private void readFileAndDrawTree() {
		java.util.ArrayList<String> treeInfo = new java.util.ArrayList<>();
		try (BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
			String inn;
			while((inn = input.readLine()) != null) {
				if(!inn.startsWith("#")) {
					treeInfo.add(inn);
				}
			}
		} catch(Exception e) {
			System.out.println(e.toString());
			System.exit(0);
		}
		drawTree(treeInfo);
	}
		
	private void drawTree(java.util.ArrayList<String> treeInfo) {
		String[] id = new String[treeInfo.size()];
		String[] path = new String[treeInfo.size()];
				
		int treeHeight = 0;
		
		for(int i = 0; i < treeInfo.size(); i++) {
			java.util.StringTokenizer st = new java.util.StringTokenizer(treeInfo.get(i));
			id[i] = st.nextToken();
			if(st.hasMoreTokens()) {
				path[i] = st.nextToken();
			} else {
				path[i] = "";
			}
			
			if(path[i].length() > treeHeight) {
				treeHeight = path[i].length();
			}
		}
		
		int treeWidth = 1;
		for(int i = 0; i < treeHeight; i++) {
			treeWidth = treeWidth*2;
		}
		
		biG.setColor(backgroundColor);
		biG.fillRect(0, 0, maxWidth, maxHeight);
		biG.setColor(foregroundColor);
		
		int width = treeWidth*colWidth;
		// int height = treeHeight*rowHeight;
		int center = width/2;
		int delta = width/treeWidth;
		int halfDelta = delta/2;
		delta = halfDelta*2;
		
		for(int i = 0; i < id.length; i++) {
			int[] coord = getCoordinates(path[i], treeHeight, center, rowHeight, halfDelta);
			int x = coord[0];
			int y = coord[1];
			biG.drawOval(x+offsetX, y+offsetY, nodeSize, nodeSize);
			int deltaX = (2*halfNodeSize)/3;
			if(id[i].length() > 1) {
				deltaX = halfNodeSize/2;
			}
			if(id[i].length() > 2) {
				deltaX = halfNodeSize/3;
			}
			biG.drawString(id[i], x+offsetX+deltaX, y+offsetY+halfNodeSize+(halfNodeSize/3));
			
			if(path[i].length() > 0) {
				int[] parentCoord = getCoordinates(path[i].substring(1), treeHeight, center, rowHeight, halfDelta);
				int pX = parentCoord[0];
				int pY = parentCoord[1];
				
				double x0 = x+halfNodeSize;
				double y0 = y+halfNodeSize;
				double x1 = pX+halfNodeSize;
				double y1 = pY+halfNodeSize;
				
				double d = getLength(x0, y0, x1, y1);
				double d1 = halfNodeSize;
				double r = d1/d;
				
				int x2 = (int) ((x0*(1.0-r))+(x1*r));
				int y2 = (int) ((y0*(1.0-r))+(y1*r));
				
				int x3 = (int) ((x1*(1.0-r))+(x0*r));
				int y3 = (int) ((y1*(1.0-r))+(y0*r));
				
				biG.drawLine(x2+offsetX, y2+offsetY, x3+offsetX, y3+offsetY);
			}
		}
		repaint(0);
	}
	
	private double getLength(double x0, double y0, double x1, double y1) {
		return Math.sqrt(((x1-x0)*(x1-x0)) + ((y1-y0)*(y1-y0)));
	}
	
	private int[] getCoordinates(String path, int treeHeight, int center, int rowHeight, int halfDelta) {
		byte[] bReverse = path.getBytes();
		byte[] b = new byte[bReverse.length];
		for(int j = 0; j < b.length; j++) {
			b[(b.length-1)-j] = bReverse[j];
		}
		
		int x = center;
		int y = b.length*rowHeight;
		for(int j = 0; j < b.length; j++) {
			// int stepsFromRoot = path.length();
			int m = 1;
			for(int k = 1; k < (treeHeight-j); k++) {
				m = m*2;
			}
			if(b[j] == '0') {
				x = x-(m*halfDelta);
			} else {
				x = x+(m*halfDelta);
			}
		}
		return new int[] {x, y};
	}
	
	public void keyPressed(java.awt.event.KeyEvent e) {
	}
	
	public void keyReleased(java.awt.event.KeyEvent e) {
		if(e.getKeyCode() == java.awt.event.KeyEvent.VK_R) {
			readFileAndDrawTree();
		}
	}
	
	public void keyTyped(java.awt.event.KeyEvent e) {
	}
			
	public void paintComponent(java.awt.Graphics g)	{
		super.paintComponent(g);
		if(bi == null) {
			return;
		}
		g.drawImage(bi, 0, 0, null);
	}
}
