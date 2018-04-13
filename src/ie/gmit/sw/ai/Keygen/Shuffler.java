package ie.gmit.sw.ai.Keygen;

import java.security.SecureRandom;
import java.util.*;

/**
 * This class is used to shuffle a generated 25 letter key. As part of the
 * simulated annealing process, the key shuffling is a core feature. There are 6
 * different operations that can be applied to a 25 letter key, these 6
 * operations are found in this class.
 * 
 * @author Ervin Mamutov | github.com/imervin
 *
 */
public class Shuffler {
	// Random to simulate rng
	private Random random;

	/**
	 * Default constructor instantiates random
	 */
	public Shuffler() {
		this.random = new SecureRandom();
	}

	/**
	 * Basically a facade that decides what operation should be applied to the
	 * key, by taking a random number between 0 and 99 it uses a switch
	 * statement to say 2/100 times it must perform 5 of 6 operations (Swap
	 * random rows, swap random colms, flip all rows, flip all cols, reverse the
	 * entire key) and 90/100 times it must perform 1 of 6 operations (swap
	 * single letters)
	 * 
	 * @param key
	 *            A 25 letter key representing all the letters in the English
	 *            language minus J
	 * @return Manipulated key parameter with 1 of 6 operations applied.
	 */
	public String shuffle(String key) {
		int percentile = random.nextInt(100);

		switch (percentile) {
		case 0:
		case 9:
			return reverse(key);
		case 1:
		case 8:
			return swapRows(key);
		case 2:
		case 7:
			return swapCols(key);
		case 3:
		case 6:
			return reverseRows(key);
		case 4:
		case 5:
			return reverseCols(key);
		default:
			return swapChars(key);

		}
	}

	/**
	 * Will take a 25 letter key and swap two random characters by turning the
	 * key into a character array and swapping elements using the helper
	 * function replace.
	 * 
	 * @param key
	 *            A 25 letter key representing all the letters in the English
	 *            language minus J
	 * @return A 25 letter key with two characters swapped
	 */
	public String swapChars(String key) {
		int index1, index2;
		char[] chs = key.toCharArray();

		do {
			index1 = random.nextInt(25);
			index2 = random.nextInt(25);
		} while (index1 == index2);

		chs = replace(chs, index1, index2);

		return new String(chs);
	}

	/**
	 * Will take a 25 letter key and reverse the order by using StringBuilder's
	 * .reverse function.
	 * 
	 * @param key
	 *            A 25 letter key representing all the letters in the English
	 *            language minus J
	 * @return A 25 letter key reversed
	 */
	public String reverse(String key) {
		return new StringBuilder(key).reverse().toString();
	}

	/**
	 * A helper function to swap indices of character array .
	 * 
	 * @param chs
	 *            A character array
	 * @param index1
	 *            First index to be swapped
	 * @param index2
	 *            Second index to be swapped
	 * @return A character array with the two indices swapped
	 */
	public char[] replace(char[] chs, int index1, int index2) {
		char temp = chs[index1];
		chs[index1] = chs[index2];
		chs[index2] = temp;

		return chs;
	}

	/**
	 * Will take a 25 letter key and swap two rows by taking generating a random
	 * number between 0 and 4 (x and y) and swapping letters from x .. x+5 and
	 * letters from y .. y+5.
	 * 
	 * @param key
	 *            A 25 letter key representing all the letters in the English
	 *            language minus J
	 * @return A 25 letter key with two substrings of length 5 swapped
	 */
	public String swapRows(String key) {
		int row1, row2;
		char[] chs = key.toCharArray();

		do {
			row1 = random.nextInt(5);
			row2 = random.nextInt(5);
		} while (row1 == row2);

		for (int i = 0; i < 5; i++) {
			chs = replace(chs, row1 * 5 + i, row2 * 5 + i);
		}

		return new String(chs);
	}

	/**
	 * Will take a 25 letter key and swap two rows by taking generating a random
	 * number between 0 and 4 (x and y) and swapping letters from x*(0..4)+x and
	 * letters from y*(0..4)+y
	 * 
	 * @param key
	 *            A 25 letter key representing all the letters in the English
	 *            language minus J
	 * @return A 25 letter key with two substrings of length 5 swapped
	 */
	public String swapCols(String key) {
		int col1, col2;
		char[] chs = key.toCharArray();

		do {
			col1 = random.nextInt(5);
			col2 = random.nextInt(5);
		} while (col1 == col2);

		for (int i = 0; i < 5; i++) {
			chs = replace(chs, i * 5 + col1, i * 5 + col2);
		}

		return new String(chs);
	}

	/**
	 * Will take a 25 letter key and reverse all substrings of 5 horizontally by
	 * using a StringBuilder and feeding it the 5 substrings reversed.
	 * 
	 * @param key
	 *            A 25 letter key representing all the letters in the English
	 *            language minus J
	 * @return A 25 letter key with all substrings of length 5 reversed
	 */
	public String reverseRows(String key) {
		StringBuilder sb = new StringBuilder();
		String editedKey = key;

		for (int i = 0; i < 5; i++) {
			sb.append(new StringBuilder(editedKey.substring(0, 5)).reverse());
			editedKey = editedKey.substring(5, editedKey.length());
		}

		return sb.toString();
	}

	/**
	 * Will take a 25 letter key and reverse the order of all substrings of
	 * length 5 by using a string builder and pushing the last most substring to
	 * the start of a new string and repeating this process till the string is
	 * null.
	 * 
	 * @param key
	 *            A 25 letter key representing all the letters in the English
	 *            language minus J
	 * @return A 25 letter key with the order of substrings reversed
	 */
	public String reverseCols(String key) {
		StringBuilder sb = new StringBuilder();
		String editedKey = key;

		for (int i = 0; i < 5; i++) {
			sb.append(new StringBuilder(editedKey.substring(editedKey.length() - 5, editedKey.length())));
			editedKey = editedKey.substring(0, editedKey.length() - 5);
		}

		return sb.toString();
	}
}
