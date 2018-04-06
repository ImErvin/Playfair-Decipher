package ie.gmit.sw;

import java.util.*;

// Adapted from https://gist.github.com/tahaemara/473702df72163773d8393d93428c4b6b
public class Cypher {
	public ArrayList<String> digrams;
	
	public Cypher() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<String> digramify(String text){
		ArrayList<String> digrams = new ArrayList<String>();
		
		for(int i = 0; i < text.length(); i += 2){
			digrams.add(text.substring(i, i+2));
		}
		
		return digrams;
	}
	
	public String decrypt(){
		return null;
	}
	
	
	public static void main(String[] args) {
		Cypher c = new Cypher();
		
		System.out.println(c.digramify("This is a test message for digrams"));
	}
}