package ie.gmit.sw.ai.Scoring;

import java.io.*;
import java.util.*;

/**
 * The class that handles calculating the log probability score of the decrypted
 * text using the 25 letter keys. This is a core function of the simulated
 * annleaing as without this function there would be no way of judging what the
 * most English result would be.
 * 
 * @author Ervin Mamutov | github.com/imervin
 *
 */
public class Score {
	// Map of 4grams
	private Map<String, Integer> fourgrams;
	// long with total occurenses of grams
	private long total;
	// Score wide filereader
	private FileReader fr;
	// Score wise bufferedreader
	private BufferedReader br;

	/**
	 * Default Constructor that activates the parseFile method and calcTotal
	 * method and sets global variables accordingly.
	 */
	public Score() {
		this.fourgrams = parseFile();
		this.total = calcTotal();
	}

	/**
	 * Will parse the 4grams.txt file and populate the fourgrams map using the
	 * contents of the file.
	 * 
	 * @return A populated HashMap from the contents in 4grams.
	 */
	public Map<String, Integer> parseFile() {
		Map<String, Integer> fourgrams = new HashMap<String, Integer>();

		try {
			String line = null;
			String[] text = new String[2];
			this.fr = new FileReader("./4grams.txt");
			this.br = new BufferedReader(this.fr);

			while ((line = br.readLine()) != null) {
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

	// Ripped from
	// https://stackoverflow.com/questions/30089469/how-to-sum-values-in-a-map-with-a-stream
	/**
	 * Ripped from
	 * https://stackoverflow.com/questions/30089469/how-to-sum-values-in-a-map-with-a-stream
	 * Function that sums all the values of the fourgrams map using a stream
	 * with a map function.
	 * 
	 * @return All the map values summed up.
	 */
	public long calcTotal() {
		return this.fourgrams.values().stream().mapToLong(Number::intValue).sum();
	}

	/**
	 * Will score the log probability of the deciphered text against the
	 * fourgrams map by looping a maximum of 800 times (found that it works
	 * quite well at 800 and worse as it increases) and it will create shingles
	 * on the fly to be tested against the fourgrams. If that shingle happens to
	 * exist in the hashmap then get the frequency of that shingle, if it does
	 * not exist then just mark it as checked by setting the frequency as
	 * 1/total. Add the log10 of that frequency to a variable and rinse and
	 * repeat.
	 * 
	 * @param decryptedText
	 *            A decrypted string
	 * @return The final score of the log probability loop
	 */
	public double scoreProb(String decryptedText) {
		int range = decryptedText.length() <= 800 ? decryptedText.length() : 800;
		double score = 0;

		for (int i = 0; i < range; i++) {
			if (this.fourgrams.get(decryptedText.substring(i, i + 4)) != null) {
				score += Math.log10((double) this.fourgrams.get(decryptedText.substring(i, i + 4)) / this.total);
			} else {
				score += Math.log10((double) 1 / this.total);
			}
		}

		return score;
	}
}
