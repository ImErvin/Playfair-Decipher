package ie.gmit.sw.ai.Text;

/**
 * This class is responsible for breaking the encrypted text into digraphs to be
 * passed into the playfair decryption.
 * 
 * @author Ervin Mamutov | github.com/imervin
 *
 */
public class Digrapherator {
	private String[] digraphs;

	/**
	 * Constructor takes an encrypted text as arguement and instantiates a
	 * String array by passing a formatted version of the encrypted text into
	 * the diagraphify method
	 * 
	 * @param text
	 */
	public Digrapherator(String text) {
		this.digraphs = this.diagraphify(this.formatify(text));
	}

	/**
	 * Will take an input of encrypted text and turn it into an array of
	 * Digraphs by looping over the input and pushing substrings of size 2 into
	 * a string array.
	 * 
	 * @param text
	 *            Encrypted text
	 * @return A string array of digraphs
	 */
	public String[] diagraphify(String text) {
		String diagraphs[] = new String[text.length() / 2];

		int j = 0;
		for (int i = 0; i < text.length(); i += 2) {
			diagraphs[j] = text.substring(i, i + 2);
			j++;
		}

		return diagraphs;
	}

	/**
	 * Will format the text input by replacing any special characters and spaces and replacing any duplicates with an X.
	 * 
	 * @param text
	 *            Encrypted text
	 * @return Formatted encrypted text
	 */
	public String formatify(String text) {
		String plainText = text.toUpperCase().replaceAll("[^A-Za-z0-9]", "");
		StringBuffer sb = new StringBuffer(plainText);

		for (int i = 0; i < plainText.length() - 1; i += 2) {
			if (sb.charAt(i) == sb.charAt(i + 1)) {
				sb.insert(i + 1, "X");
			}
		}
		if (sb.length() % 2 == 1) {
			sb.append("X");
		}

		return sb.toString();
	}
	
	/**
	 * Getter for digraphs
	 * @return String array of digraphs
	 */
	public String[] getDiagraphs() {
		return this.digraphs;
	}
}
