package ie.gmit.sw.Text;

import java.io.*;

public class FileHandler {

	private String fileName;
	private FileReader fr;
	private BufferedReader br;

	public FileHandler(String fileName) {
		this.fileName = fileName;
	}

	public String readFile() {
		try {
			String line = null;
			this.fr = new FileReader(this.fileName);
			this.br = new BufferedReader(this.fr);
			
			while((line = br.readLine()) != null) {
                System.out.println(line);
            }   
			
			return line;
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println("Error reading file '" + this.fileName + "'");
		}

		return null;
	}
	
	public static void main(String[] args) {
		FileHandler f = new FileHandler("./tips.txt");
		
		f.readFile();
	}

}
