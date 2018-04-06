package ie.gmit.sw;

import java.util.*;

// Adapted from https://gist.github.com/tahaemara/473702df72163773d8393d93428c4b6b
public class Cypher {
	private ArrayList<String> digrams;
	
	public Cypher(String text) {
		this.digrams = this.digramify(this.formatify(text));
	}
	
	public ArrayList<String> digramify(String text){
		ArrayList<String> digrams = new ArrayList<String>();
		
		for(int i = 0; i < text.length(); i += 2){
			digrams.add(text.substring(i, i+2));
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
	
	public String decrypt(){
		return null;
	}
	
	public ArrayList<String> getDigrams(){
		return this.digrams;
	}
	
	
	public static void main(String[] args) {
		Cypher c = new Cypher("This is a test message for digramst");
		
		System.out.println(c.getDigrams());
	}
}