package ie.gmit.sw.Keygen;

import java.security.SecureRandom;
import java.util.*;

public class Shuffler {
	private Random random = new SecureRandom();
	
	public String swapChars(String key){
		int index1, index2;
		char[] chs = key.toCharArray();
		
		do{
			index1 = random.nextInt(25);
			index2 = random.nextInt(25);
		}while(index1 == index2);
		
		System.out.println("Changing " + chs[index1] + " with " + chs[index2]);
		
		chs = replace(chs, index1, index2);	
		
		System.out.println("Original:\t" + key);
		System.out.println("Now:\t\t" + new String(chs));
		
		return chs.toString();
	}
	
	public String reverse(String key){
		System.out.println("Original:\t" + key);
		System.out.println("Now:\t\t" + new StringBuilder(key).reverse().toString());
		return new StringBuilder(key).reverse().toString();
	}
	
	public char[] replace(char[] chs, int index1, int index2){
		char temp = chs[index1];
		chs[index1] = chs[index2];
		chs[index2] = temp;
		
		return chs;
	}
	
	public void printMatrix(char[] chs){
		for (int i = 0; i < chs.length; i++) {
			if(i % 5 == 0){
				System.out.println();
			}
			System.out.print(chs[i]);
		}
	}
	
	public String swapRows(String key){
		int row1, row2;
		char[] chs = key.toCharArray();
		
		System.out.println("\nBefore row swap");
		printMatrix(chs);
		
		do{
			row1 = random.nextInt(5);
			row2 = random.nextInt(5);
		}while(row1 == row2);
		
		
		for(int i = 0; i < 5; i++){
			chs = replace(chs, row1*5 + i,row2*5+i);
		}
				
		System.out.println("\nAfter row swap");
		printMatrix(chs);
		
		
		return new String(chs);
	}
	
	public String swapCols(String key){
		int col1, col2;
		char[] chs = key.toCharArray();
		
		System.out.println("\nBefore column swap");
		printMatrix(chs);
		
		
		do{
			col1 = random.nextInt(5);
			col2 = random.nextInt(5);
		}while(col1 == col2);
		
		for(int i = 0; i < 5; i++){
			chs = replace(chs, i*5 + col1,i*5 + col2);
		}
		
		System.out.println("\nAfter col swap");
		printMatrix(chs);
		
		
		return new String(chs);
	}
	
	public static void main(String[] args) {
		Shuffler s = new Shuffler();
		String key = "NKLTHYDBQXSWIMEFUPZCARGOV";
		
		s.swapChars(key);
		s.reverse(key);
		s.swapRows(key);
		s.swapCols(key);
	}
}
