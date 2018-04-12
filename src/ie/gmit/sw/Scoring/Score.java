package ie.gmit.sw.Scoring;

import java.io.*;
import java.util.*;

public class Score {
	private Map<String, Integer> fourgrams;
	private long total;
	private FileReader fr;
	private BufferedReader br;
	
	public Score() {
		this.fourgrams = parseFile();
		this.total = calcTotal();
		System.out.println(this.total);
	}
	
	public Map<String, Integer> parseFile(){
		Map<String, Integer> fourgrams = new HashMap<String, Integer>();
		
		try {
			String line = null;
			String[] text = new String[2];
			this.fr = new FileReader("./4grams.txt");
			this.br = new BufferedReader(this.fr);
			
			while((line = br.readLine()) != null) {
				text = line.split(" ");
				fourgrams.put(text[0], Integer.parseInt(text[1]));
            }   
			
			return fourgrams;
		} catch (FileNotFoundException e) {
			System.out.println(e);
			return null;
		} catch (IOException e) {
			System.out.println("Error reading file");
			return null;
		}
	}
	
	// Ripped from https://stackoverflow.com/questions/30089469/how-to-sum-values-in-a-map-with-a-stream
	public long calcTotal(){
		return this.fourgrams.values().stream().mapToLong(Number::intValue).sum();
	}
	
	public static void main(String[] args) {
		Score s = new Score();
	}
	
}
