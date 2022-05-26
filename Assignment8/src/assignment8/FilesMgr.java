package assignment8;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FilesMgr {
	private JFrame frame;
	private String defaultDir; 
	private String filesDir; 
	private Properties properties = null;
	private final String propertyFileName = "propertyfile.txt"; 

	public FilesMgr(JFrame frameIn) {
		frame = frameIn;
		locateDefaultDirectory();
		loadPropertiesFile();
	}

	private void locateDefaultDirectory() {
		//CODE TO DISCOVER THE ECLIPSE DEFAULT DIRECTORY:
		//There will be a property file if the program has been used for a while
		//because it which will store the locations of the pasm and pexe files
		//but we allow the possibility that it does not exist yet.
		File temp = new File(propertyFileName);
		if(!temp.exists()) {
			PrintWriter out; // make a file that we will delete later
			try {
				out = new PrintWriter(temp);
				out.close();
				defaultDir = temp.getAbsolutePath();
				temp.delete();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			defaultDir = temp.getAbsolutePath();
		}
		// change to forward slashes, making it platform independent, that is, if needed, 
		// change the MS Windows "\\" to "/", which works on all platforms for Java
		defaultDir = defaultDir.replace('\\','/'); // for Windows machines
		int lastSlash = defaultDir.lastIndexOf('/');
		//remove the file name and keep the directory path
		defaultDir  = defaultDir.substring(0, lastSlash + 1);
	}

	void loadPropertiesFile() {
		try { // load properties file propertyFileName, if it exists
			properties = new Properties();
			properties.load(new FileInputStream(propertyFileName));
			filesDir = properties.getProperty("SourceDirectory");
			// Clean up any errors in what is stored in properties file:
			if (filesDir == null || filesDir.length() == 0 
					|| !new File(filesDir).exists()) {
				filesDir = defaultDir;
			}
		} catch (Exception e) {
			// Properties file did not exist
			filesDir = defaultDir;
		}
	}

	public File identifyFile(String sortOrSearch) {
		File source = null;
		JFileChooser chooser = new JFileChooser(filesDir);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				sortOrSearch, "txt");
		chooser.setFileFilter(filter);
		// Code to load the selected file
		int openOK = chooser.showOpenDialog(null);
		if(openOK == JFileChooser.APPROVE_OPTION) {
			source = chooser.getSelectedFile();
		}
		if(source != null && source.exists()) {
			// Code to remember which file you have the sort data in
			filesDir = source.getAbsolutePath();
			filesDir = filesDir.replace('\\','/'); // deal with Windows machines
			int lastSlash = filesDir.lastIndexOf('/');
			filesDir = filesDir.substring(0, lastSlash + 1);
			try { 
				properties.setProperty("SourceDirectory", filesDir);
				properties.store(new FileOutputStream(propertyFileName), 
						"File locations");
			} catch (Exception e) {
				// Never seen this happen
				JOptionPane.showMessageDialog(
						frame, 
						"Problem with Java.\n" +
								"Error writing properties file",
								"Warning",
								JOptionPane.OK_OPTION);
			}
		} else {// source file does not exist
			JOptionPane.showMessageDialog(
					frame, 
					"The source file has problems or does not exist.\n" +
							"Cannot load the sort data",
							"Warning",
							JOptionPane.OK_OPTION);				
		}
		return source;
	}
}
