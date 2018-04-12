package ie.gmit.sw.Scoring;

import java.security.SecureRandom;
import java.util.Random;

import ie.gmit.sw.Cyphers.Playfair;
import ie.gmit.sw.Keygen.Keygen;
import ie.gmit.sw.Keygen.Shuffler;
import ie.gmit.sw.Text.Digrapherator;
import ie.gmit.sw.Text.FileHandler;

public class SimulatedAnnealing {
	private Playfair pf;
	private Score s;
	private Keygen kg;
	private Shuffler sh;
	private FileHandler fh;
	private Digrapherator dg;
	private Random random;
	
	public SimulatedAnnealing(){
		this.pf = new Playfair();
		this.s = new Score();
		this.kg = Keygen.getInstance();
		this.sh = new Shuffler();
		this.fh = new FileHandler("./tips.txt");
		this.dg = new Digrapherator(this.fh.readFile());
		this.random = new SecureRandom();
	}
	
	public void startSimulation(){
		String parent = this.kg.generateKey();
		String decipheredText = this.pf.decrypt(this.dg.getDiagraphs(), parent);
		double logProbability = this.s.scoreProb(decipheredText);
		int temp;
		int transition;
		String child = parent;
		
		for(temp = 10; temp >= 0; temp--){
			for(transition = 50000; transition >= 0; transition--){
				child = this.sh.shuffle(parent);
				System.out.println(child.toCharArray());
				decipheredText = this.pf.decrypt(this.dg.getDiagraphs(), child);
				double delta = this.s.scoreProb(decipheredText) - logProbability;
				
				if (delta > 0) {
					parent = child;
				} else {
					double prob = (Math.exp((delta / temp)));
					if (prob > random.nextDouble()) {
						parent = child;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		SimulatedAnnealing sa = new SimulatedAnnealing();
		
		sa.startSimulation();
	}

}
