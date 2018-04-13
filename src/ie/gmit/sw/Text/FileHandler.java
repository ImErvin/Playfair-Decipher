package ie.gmit.sw.Text;

import java.io.*;

public class FileHandler {

	private String fileName;
	private FileReader fr;
	private BufferedReader br;

	public FileHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public FileHandler(String fileName) {
		this.fileName = fileName;
	}

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
