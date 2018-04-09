package ie.gmit.sw.Cyphers;

public class Playfair {
	
	public Playfair() {
		// TODO Auto-generated constructor stub
	}
	
	public String decrypt(String[] digraphs, String key) {

        StringBuilder ciphertex = new StringBuilder();
        for (String diagraph : digraphs) {//using zero index
            int row1 = key.indexOf(diagraph.charAt(0)) / 5;
            int col1 = key.indexOf(diagraph.charAt(0)) % 5;
            int row2 = key.indexOf(diagraph.charAt(1)) / 5;
            int col2 = key.indexOf(diagraph.charAt(1)) % 5;
            
            char chr1;
            char chr2;
            
            if (col1 == col2) {
                chr2 = key.charAt(((row2 + 4) % 5 * 5 + col2));
                chr1 = key.charAt(((row1 + 4) % 5 * 5 + col1));
            } else if (row1 == row2) {
                chr1 = key.charAt(row1 * 5 + ((col1 + 4) % 5));
                chr2 = key.charAt(row2 * 5 + ((col2 + 4) % 5));
            } else {
                chr1 = key.charAt(row1 * 5 + col2);
                chr2 = key.charAt(row2 * 5 + col1);
            }
            ciphertex.append(Character.toString(chr1) + Character.toString(chr2));
        }

        return ciphertex.toString();
    }
}
