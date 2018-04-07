package ie.gmit.sw;

import java.util.*;

// Adapted from https://gist.github.com/tahaemara/473702df72163773d8393d93428c4b6b
public class Cypher {
	private String[] digrams;
	private String alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
	public Cypher(String text) {
		this.digrams = this.digramify(this.formatify(text));
	}
	
	public String generateMatrix(String key) {

        String matrixstring = key + alphabet;
        StringBuilder mat = new StringBuilder();
        Set set = new LinkedHashSet();

        for (char chr : matrixstring.toLowerCase().toCharArray()) {
            set.add(chr);
        }
        for (Object chr : set) {
            mat.append(chr);
        }
        System.out.println(mat.toString());
        return mat.toString();
    }
	
	public String[] digramify(String text){
		String digrams[] = new String[text.length() / 2];
		
		int j = 0;
		for(int i = 0; i < text.length(); i += 2){
			digrams[j] = text.substring(i, i + 2);
	        j++;
		}
		
		return digrams;
	}
	
	public String formatify(String text){
		String plainText = text.toUpperCase().replaceAll("[^A-Za-z0-9]", "");
		StringBuffer sb = new StringBuffer(plainText);
		
		for(int i = 0; i < plainText.length() - 1; i += 2){
			if(sb.charAt(i) == sb.charAt(i+1)){
				sb.insert(i+1, "X");
			}
		}
		if(sb.length() % 2 == 1){
			sb.append("X");
		}

		return sb.toString();
	}
	
	public String decrypt(String[] digrams, String matrix) {

        StringBuilder ciphertex = new StringBuilder();
        matrix = matrix.toUpperCase();
        for (String digram : digrams) {//using zero index
            int row1 = matrix.indexOf(digram.charAt(0)) / 5;
            int col1 = matrix.indexOf(digram.charAt(0)) % 5;
            int row2 = matrix.indexOf(digram.charAt(1)) / 5;
            int col2 = matrix.indexOf(digram.charAt(1)) % 5;
            
            char chr1;
            char chr2;
            System.out.printf("%d, %d, %d, %d\n", row1, row2, col1, col2);
            
            if (col1 == col2) {
                chr2 = matrix.charAt(((row2 + 4) % 5 * 5 + col2));
                chr1 = matrix.charAt(((row1 + 4) % 5 * 5 + col1));
            } else if (row1 == row2) {
                chr1 = matrix.charAt(row1 * 5 + ((col1 + 4) % 5));
                chr2 = matrix.charAt(row2 * 5 + ((col2 + 4) % 5));
            } else {
                chr1 = matrix.charAt(row1 * 5 + col2);
                chr2 = matrix.charAt(row2 * 5 + col1);
            }
            ciphertex.append(Character.toString(chr1) + Character.toString(chr2));
        }

        return ciphertex.toString();
    }
    
	
	public String[] getDigrams(){
		return this.digrams;
	}
	
	
	public static void main(String[] args) {
		Cypher c = new Cypher("This is a test for digramst");
		String key = "a";
		
		String matrix = c.generateMatrix(key);
		char chrs[] = matrix.toCharArray();
        for (int i = 0; i < chrs.length; i++) {
            if (i % 5 == 0 & i != 0) {
                System.out.println("");
            }
            System.out.print(chrs[i]);
        }
		
		System.out.println(c.decrypt(c.getDigrams(), matrix));
	}
}