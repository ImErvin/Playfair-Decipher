package ie.gmit.sw.ai.Keygen;

import java.security.SecureRandom;
import java.util.*;

/***
 * This class is a singleton used to generate a 25 character string consisting of unique alphabetical letters to be used in playfair decryption.
 * 
 * @author Ervin Mamutov | github.com/imervin
 *
 */
public class Keygen {
	// Alphabet minus J
	private final String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	// Random for simulating rng
	private Random random;
	// A singleton instance of the keygen
	private static Keygen instance;
	
	/***
	 * Private constructor instantiates a new secure random.
	 */
	private Keygen() {
		this.random = new SecureRandom();
	}

	/**
	 * A getter for the instance variable.
	 * @return
	 * A new instance of Keygen if one does not already exist.
	 */
	public static Keygen getInstance() {
		return (instance == null) ? new Keygen() : instance;
	}
	
	/**
	 * Generates a random 25 letter key using the ALPHABET variable above. Uses the ArrayList collection's shuffle to generate a random combination
	 * of the 25 letters and a StringBuilder to rebuild the ArrayList into a string.
	 * @return
	 * The produce of the string builder.
	 */
	public String generateKey() {
		String matrixstring = ALPHABET;
		StringBuilder sb = new StringBuilder();
		List<Character> list = new ArrayList<Character>();

		for (char chr : matrixstring.toUpperCase().toCharArray()) {
			list.add(chr);
		}

		Collections.shuffle(list, this.random);

		for (Object chr : list) {
			sb.append(chr);
		}

		return sb.toString();
	}
}
