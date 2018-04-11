package ie.gmit.sw.Keygen;

import java.security.SecureRandom;
import java.util.*;

public class Shuffler {
	private Random random = new SecureRandom();
	
	public String swapChars(String key){
		int index1;
		int index2;
		char[] chs = key.toCharArray();
		
		do{
			index1 = random.nextInt(25);
			index2 = random.nextInt(25);
		}while(index1 == index2);
		
		System.out.println("Changing " + chs[index1] + " with " + chs[index2]);
		
		char temp = chs[index1];
		chs[index1] = chs[index2];
		chs[index2] = temp;
		
		System.out.println("Original:\t" + key);
		System.out.println("Now:\t\t" + new String(chs));
		
		return chs.toString();
	}
	
	
	public static void main(String[] args) {
		Shuffler s = new Shuffler();
		
		
		s.swapChars("NKLTHYDBQXSWIMEFUPZCARGOV");
	}
}
