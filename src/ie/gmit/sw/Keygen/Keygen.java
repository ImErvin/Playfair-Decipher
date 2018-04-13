package ie.gmit.sw.Keygen;

import java.security.SecureRandom;
import java.util.*;

public class Keygen {
	private final String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	private Random random;
	private static Keygen instance;
	
	private Keygen() {
		this.random = new SecureRandom();
	}
	
	public static Keygen getInstance() {
		return (instance == null) ? new Keygen() : instance;
	}
	
	public String generateKey() {
		String matrixstring = ALPHABET;
		StringBuilder mat = new StringBuilder();
		List<Character> list = new ArrayList<Character>();

		for (char chr : matrixstring.toUpperCase().toCharArray()) {
			list.add(chr);
		}

		Collections.shuffle(list, this.random);

		for (Object chr : list) {
			mat.append(chr);
		}

		return mat.toString();
	}
	

	// Ripped from https://stackoverflow.com/questions/4989091/removing-duplicates-from-a-string-in-java
	public String duplicateChecker(String key){
		
		char[] chars = key.toCharArray();
		Set<Character> charSet = new LinkedHashSet<Character>();
		for (char c : chars) {
		    charSet.add(c);
		}

		StringBuilder sb = new StringBuilder();
		for (Character character : charSet) {
		    sb.append(character);
		}
		
		return sb.toString();
	}
}
