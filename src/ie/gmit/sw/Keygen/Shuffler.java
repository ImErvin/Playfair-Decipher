package ie.gmit.sw.Keygen;

import java.security.SecureRandom;
import java.util.*;

public class Shuffler {
	private Random random;
	
	public Shuffler(){
		this.random = new SecureRandom();
	}
	
	public String shuffle(String key){
		int percentile = random.nextInt(100);
		
		switch(percentile){
			case 0:
			case 9:
				System.out.println("Reverse");
				return reverse(key);
			case 1:
			case 8:
				System.out.println("SwapRows");
				return swapRows(key);
			case 2:
			case 7:
				System.out.println("SwapCols");
				return swapCols(key);
			case 3:
			case 6:
				System.out.println("Reverse rows");
				return reverseRows(key);
			case 4:
			case 5:
				System.out.println("Reverse cols");
				return reverseCols(key);
			default:
				System.out.println("Swap chars");
				return swapChars(key);
			
		}
	}
	
	public String swapChars(String key){
		int index1, index2;
		char[] chs = key.toCharArray();
		
		do{
			index1 = random.nextInt(25);
			index2 = random.nextInt(25);
		}while(index1 == index2);
				
		chs = replace(chs, index1, index2);	
		
		return chs.toString();
	}
	
	public String reverse(String key){
		return new StringBuilder(key).reverse().toString();
	}
	
	public char[] replace(char[] chs, int index1, int index2){
		char temp = chs[index1];
		chs[index1] = chs[index2];
		chs[index2] = temp;
		
		return chs;
	}
	
	public String swapRows(String key){
		int row1, row2;
		char[] chs = key.toCharArray();
		
		do{
			row1 = random.nextInt(5);
			row2 = random.nextInt(5);
		}while(row1 == row2);
		
		
		for(int i = 0; i < 5; i++){
			chs = replace(chs, row1*5 + i,row2*5+i);
		}
		
		return new String(chs);
	}
	
	public String swapCols(String key){
		int col1, col2;
		char[] chs = key.toCharArray();
		
		do{
			col1 = random.nextInt(5);
			col2 = random.nextInt(5);
		}while(col1 == col2);
		
		for(int i = 0; i < 5; i++){
			chs = replace(chs, i*5 + col1,i*5 + col2);
		}

		return new String(chs);
	}
	
	
	public String reverseRows(String key){
		StringBuilder sb = new StringBuilder();
		String editedKey = key;
		
		for(int i = 0; i < 5; i++){
			sb.append(new StringBuilder(editedKey.substring(0, 5)).reverse());
			editedKey = editedKey.substring(5, editedKey.length());
		}

		return sb.toString();
	}
	
	public String reverseCols(String key){
		StringBuilder sb = new StringBuilder();
		String editedKey = key;
		
		for(int i = 0; i < 5; i++){
			sb.append(new StringBuilder(editedKey.substring(editedKey.length() - 5, editedKey.length())));
			editedKey = editedKey.substring(0, editedKey.length() - 5);
		}
		
		return sb.toString();
	}
}
