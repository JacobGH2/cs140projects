package helhp2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class HelhoskiP2 {
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Three arguments needed");
			System.exit(0);
		}


		// decide which method is called
		String operation = args[0].toString();

		if (operation.equals("t2b")) {
			textToBinary(args[1], args[2]);
		}
		if (operation.equals("b2t")) {
			binaryToText(args[1], args[2]);
		}



	}

	public static void textToBinary(String inputFilename, String outputFilename) {
		try {
		int numLines;
		byte[] tempArray = new byte[8];
		ByteBuffer current = ByteBuffer.wrap(tempArray);
		BufferedReader textInput = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilename)));
		FileOutputStream binOutput = new FileOutputStream(outputFilename);

		// writing number of lines
		ArrayList<String> lines = new ArrayList<String>();
		String line;
		while ((line = textInput.readLine()) != null) {
			lines.add(line);
		}
		numLines = lines.size();

		current.putInt(numLines);
		binOutput.write(tempArray, 0, 4);


		// read text and write as binary


		for (int i = 0; i < numLines; i++) {
			StringTokenizer st = new StringTokenizer(lines.get(i),"\t");
			String type = st.nextToken();

			switch(type) {

			case "int":

				String intString = st.nextToken();
				int intValue = Integer.parseInt(intString);
				current.putChar(0, 'i');
				binOutput.write(tempArray, 0, 2);
				current.putInt(0, intValue);
				binOutput.write(tempArray, 0, 4);
				break;

			case "double":
				current.putChar(0, 'd');
				binOutput.write(tempArray, 0, 2);
				String doubleString = st.nextToken();
				double doubleValue = Double.parseDouble(doubleString);
				current.putDouble(0, doubleValue);
				binOutput.write(tempArray, 0, 8);
				break;

			case "float":
				current.putChar(0, 'f');
				binOutput.write(tempArray, 0, 2);
				String floatString = st.nextToken();
				float floatValue = Float.parseFloat(floatString);
				current.putFloat(0, floatValue);
				binOutput.write(tempArray, 0, 4);
				break;

			case "short":
				current.putChar(0, 'h');
				binOutput.write(tempArray, 0, 2);
				String shortString = st.nextToken();
				short shortValue = Short.parseShort(shortString);
				current.putShort(0, shortValue);
				binOutput.write(tempArray, 0, 2);
				break;

			case "long":
				current.putChar(0, 'l');
				binOutput.write(tempArray, 0, 2);
				String longString = st.nextToken();
				long longvalue = Long.parseLong(longString);
				current.putLong(0, longvalue);
				binOutput.write(tempArray, 0, 8);
				break;

			case "int array":
				current.putChar(0, 'a');
				binOutput.write(tempArray, 0, 2);
				StringTokenizer intToken = new StringTokenizer(st.nextToken(), ",");
				int numInts = intToken.countTokens();
				current.putInt(0, numInts);
				binOutput.write(tempArray, 0, 4);
				while (intToken.hasMoreTokens()) {
					String intArrayString = intToken.nextToken();
					int intArrayValue = Integer.parseInt(intArrayString);
					current.putInt(0, intArrayValue);
					binOutput.write(tempArray, 0, 4);

				}
				break;

			case "double array":
				current.putChar(0, 'e');
				binOutput.write(tempArray, 0, 2);
				StringTokenizer doubleToken = new StringTokenizer(st.nextToken(), ",");
				int numDoubles = doubleToken.countTokens();
				current.putInt(0, numDoubles);
				binOutput.write(tempArray, 0, 4);
				for (int k = 0; k < numDoubles; k++) {
					String doubleArrayString = doubleToken.nextToken();
					double doubleArrayValue = Double.parseDouble(doubleArrayString);
					current.putDouble(0, doubleArrayValue);
					binOutput.write(tempArray, 0, 8);

				}
				break;

			case "string":
				// write type
				current.putChar(0, 's');
				binOutput.write(tempArray, 0, 2);
				// write length
				String[] separate = lines.get(i).split("\t", 2);
				char[] characters = separate[1].toCharArray();
				current.putInt(0, characters.length);
				binOutput.write(tempArray, 0, 4);
				// write characters
				for (char c: characters) {
					current.putChar(0, c);
					binOutput.write(tempArray, 0, 2);
				}
				break;

			default:
				break;

			}
		}

		textInput.close();
		binOutput.close();
		} catch(Exception e) {
			e.toString();
		}
	}

	public static void binaryToText(String inputFilename, String outputFilename) {
		try {
		byte[] tempArray = new byte[8];
		FileInputStream binInput = new FileInputStream(inputFilename);
		PrintWriter textOutput = new PrintWriter(new BufferedWriter(new FileWriter(outputFilename)));

		// determine number of blocks
		byte[] numBlocks = binInput.readNBytes(4);
		ByteBuffer numBlocksBuffer = ByteBuffer.wrap(numBlocks);
		int numberBlocks = numBlocksBuffer.getInt();



		// read in bytes and output strings to file
		ByteBuffer current = ByteBuffer.wrap(tempArray);
		for (int i = 0; i < numberBlocks; i++) {
			binInput.read(tempArray, 0, 2);
			char type = current.getChar(0);
			int arrayLength = 0;
			switch (type) {

			case 'i':

				textOutput.print("int\t");
				binInput.read(tempArray, 0, 4);
				textOutput.print(current.getInt(0));

				break;

			case 'd':

				textOutput.print("double\t");
				binInput.read(tempArray, 0, 8);
				textOutput.print(current.getDouble(0));

				break;

			case 'l':

				binInput.read(tempArray, 0, 8);
				long value = current.getLong(0);
				textOutput.print("long\t" + Long.toString(value));
				break;

			case 'h':

				textOutput.print("short\t");
				binInput.read(tempArray, 0, 2);
				textOutput.print(current.getShort(0));
				break;

			case 'f':

				textOutput.print("float\t");
				binInput.read(tempArray, 0, 4);
				textOutput.print(current.getFloat(0));
				break;

			case 'a':

				textOutput.print("int array\t");
				binInput.read(tempArray, 0 ,4);
				int intTemp = current.getInt(0);
				arrayLength = intTemp;
				for (int j = 0; j < arrayLength; j++) {
					binInput.read(tempArray, 0 ,4);
					textOutput.print(current.getInt(0));
					if ((arrayLength - j) != 1) {
						textOutput.print(",");

					}
				}
				break;

			case 'e':

				textOutput.print("double array\t");
				binInput.read(tempArray, 0, 4);
				int doubleTemp = current.getInt(0);
				arrayLength = doubleTemp;
				for (int k = 0; k < arrayLength; k++) {
					binInput.read(tempArray, 0, 8);
					textOutput.print(current.getDouble(0));
					if ((arrayLength - k) != 1) {
						textOutput.print(",");
					}

				}
				break;

			case 's':

				textOutput.print("string\t");
				binInput.read(tempArray, 0 ,4);
				int stringTemp = current.getInt(0);
				arrayLength = stringTemp;
				for (int m = 0; m < arrayLength; m++) {
					binInput.read(tempArray, 0, 2);
					textOutput.print(current.getChar(0));

				}
				break;


			default:
				break;
			}

			textOutput.println();


		}


		binInput.close();
		textOutput.close();

		} catch(Exception e) {
			e.toString();
		}
	}

}
