package ie.gmit.sw;

import java.util.LinkedHashSet;
import java.util.Set;

public class Cypher {
	
	private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWYZ";
	
	public String generateMatrix(String key) {

        String matrixstring = key + alphabet;
        StringBuilder mat = new StringBuilder();
        Set<Character> set = new LinkedHashSet<Character>();

        for (char chr : matrixstring.toLowerCase().toCharArray()) {
            set.add(chr);
        }
        for (Object chr : set) {
            mat.append(chr);
        }

        return mat.toString();
    }
	
	public String[] divideToPairs(String message) {

        message = formatMessage(message);
        String pairs[] = new String[message.length() / 2];
        int j = 0;

        for (int i = 0; i < message.length(); i = i + 2) {
            pairs[j] = message.substring(i, i + 2);
            j++;
        }

        return pairs;
    }
	
	public String formatMessage(String message) {

        message = message.toLowerCase().replace(" ", "");
        StringBuilder mes = new StringBuilder(message);

        for (int i = 0; i < message.length() - 1; i += 2) {
            if (mes.charAt(i) == mes.charAt(i + 1)) {
                mes.insert(i + 1, "x");
            }
        }
        if (mes.length() % 2 == 1) {
            mes.append("x");
        }

        return mes.toString();
    }
	
	 public String encrypt(String[] pairs, String matrix) {

	        StringBuilder ciphertex = new StringBuilder();

	        for (String pair : pairs) {//using zero index
	            byte row1 = (byte) (matrix.indexOf(pair.charAt(0)) / 5);
	            byte col1 = (byte) (matrix.indexOf(pair.charAt(0)) % 5);
	            byte row2 = (byte) (matrix.indexOf(pair.charAt(1)) / 5);
	            byte col2 = (byte) (matrix.indexOf(pair.charAt(1)) % 5);
	            //System.out.println("char 1 " + pair.charAt(0) + "  at " + row1 + "   " + col1);
	            //System.out.println("char 2 " + pair.charAt(1) + "  at " + row2 + "   " + col2);

	            char chr1;
	            char chr2;
	            if (col1 == col2) {
	                chr2 = matrix.charAt(((row2 + 1) % 5 * 5 + col2));
	                chr1 = matrix.charAt(((row1 + 1) % 5 * 5 + col1));
	            } else if (row1 == row2) {
	                chr1 = matrix.charAt(row1 * 5 + ((col1 + 1) % 5));
	                chr2 = matrix.charAt(row2 * 5 + ((col2 + 1) % 5));
	            } else {
	                chr1 = matrix.charAt(row1 * 5 + col2);
	                chr2 = matrix.charAt(row2 * 5 + col1);
	            }
	            ciphertex.append(Character.toString(chr1) + Character.toString(chr2));
	        }

	        return ciphertex.toString();
	    }
	
	public String decrypt(String[] pairs, String matrix) {

        StringBuilder ciphertex = new StringBuilder();

        for (String pair : pairs) {//using zero index
            byte row1 = (byte) (matrix.indexOf(pair.charAt(0)) / 5);
            byte col1 = (byte) (matrix.indexOf(pair.charAt(0)) % 5);
            byte row2 = (byte) (matrix.indexOf(pair.charAt(1)) / 5);
            byte col2 = (byte) (matrix.indexOf(pair.charAt(1)) % 5);
            //System.out.println("char 1 " + pair.charAt(0) + "  at " + row1 + "   " + col1);
            //System.out.println("char 2 " + pair.charAt(1) + "  at " + row2 + "   " + col2);

            char chr1;
            char chr2;
            if (col1 == col2) {
                chr2 = matrix.charAt(((row2 - 1) % 5 * 5 + col2));
                chr1 = matrix.charAt(((row1 - 1) % 5 * 5 + col1));
            } else if (row1 == row2) {
                chr1 = matrix.charAt(row1 * 5 + ((col1 - 1) % 5));
                chr2 = matrix.charAt(row2 * 5 + ((col2 - 1) % 5));
            } else {
                chr1 = matrix.charAt(row1 * 5 + col2);
                chr2 = matrix.charAt(row2 * 5 + col1);
            }
            ciphertex.append(Character.toString(chr1) + Character.toString(chr2));
        }

        return ciphertex.toString();
    }
	
	public static void main(String[] args) {
        String plaintext = "playfair encryption algorithm";// algorithm";
        String key = "THEQUICKBROWNFOXJUMPEDOVERTHELAZYDOGS";
        
        Cypher pf = new Cypher();

        String matrix = pf.generateMatrix(key.replace("X", ""));
        String[] pairs = pf.divideToPairs(plaintext.replace("j", "i"));

        System.out.println("Plaintext : " + plaintext + "\nAlphabet  : " + alphabet + "\nwith key  : "+key);
        System.out.print("Generated matrix :  ");
        char chrs[] = matrix.toCharArray();
        for (int i = 0; i < chrs.length; i++) {
            if (i % 5 == 0 & i != 0) {
                System.out.println("");
            }
            System.out.print(chrs[i]);
        }
        
        String ciphertext=pf.encrypt(pairs, matrix);
        System.out.println("\nEncrypted message: " + ciphertext);
        System.out.println("Recovered message: " + pf.decrypt(pf.divideToPairs(ciphertext), matrix));
    }
	
	
	public void decrypt(String key){
		
	}
}
