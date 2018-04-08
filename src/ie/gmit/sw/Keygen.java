package ie.gmit.sw;

import java.util.*;

public class Keygen {
	private final String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	private Random random;
	
	public Keygen() {
		this.random = new Random();
	}
	
	public String generateMatrix() {
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
	
	public String generateKey(String secretkey){
		String key = secretkey.toUpperCase().replace("J", "I") + ALPHABET;

		return key.replaceAll("(.)\\1", "$1");
	}
	
	public static void main(String[] args) {
		Keygen k = new Keygen();
		
		
	}
}
