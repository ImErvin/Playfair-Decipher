package ie.gmit.sw.ai.Text;

import java.io.*;

/**
 * Class that handles file reading and writing.
 * 
 * 
 * @author Ervin Mamutov | github.com/imervin
 *
 */
public class FileHandler {

	private String fileName;
	private FileReader fr;
	private BufferedReader br;

	/**
	 * Default constructor
	 */
	public FileHandler() {

	}

	/**
	 * String constructor that sets filename
	 * 
	 * @param fileName
	 */
	public FileHandler(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Reads the file using the member filename variable parses what is inside
	 * using a filereader and a bufferedreader.
	 * 
	 * @return The contents of a file in string format.
	 */
	public String readFile() {
		try {
			String line = null;
			String text = "";
			this.fr = new FileReader(this.fileName);
			this.br = new BufferedReader(this.fr);

			while ((line = br.readLine()) != null) {
				text += line;
			}

			fr.close();
			br.close();
			return text;
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println("Error reading file '" + this.fileName + "'");
		}

		return null;
	}

	/**
	 * Will write the output of the SA algorithm to a file.
	 * 
	 * @param filename
	 *            A unique filename to source the content to
	 * @param content
	 *            The response of the SA algorithm to be written into a file
	 * @return An error or success message to be shown to the user in the
	 *         console
	 */
	public String writeFile(String filename, String content) {
		try {
			String file = "./annealed-" + filename;
			FileWriter fw = new FileWriter(file);
			fw.write(content);
			fw.close();

			return "Saved as " + file;
		} catch (FileNotFoundException e) {
			// File not found
			e.printStackTrace();
			return "error";
		} catch (IOException e) {
			// Error when writing to the file
			e.printStackTrace();
			return "error";
		}
	}
}
