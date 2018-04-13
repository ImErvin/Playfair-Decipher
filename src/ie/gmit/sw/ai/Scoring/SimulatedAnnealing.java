package ie.gmit.sw.ai.Scoring;

import java.security.SecureRandom;
import java.util.Random;

import ie.gmit.sw.ai.Cyphers.Playfair;
import ie.gmit.sw.ai.Keygen.Keygen;
import ie.gmit.sw.ai.Keygen.Shuffler;
import ie.gmit.sw.ai.Text.Digrapherator;
import ie.gmit.sw.ai.Text.FileHandler;

/**
 * This class is basically facade and brings all the other classes together to
 * simulate annealing. This is the core function of the project.
 * 
 * @author Ervin Mamutov | github.com/imervin
 *
 */
public class SimulatedAnnealing {
	private Playfair pf;
	private Score s;
	private Keygen kg;
	private Shuffler sh;
	private FileHandler fh;
	private Digrapherator dg;
	private Random random;

	/**
	 * Constructor that takes file path as a parameter and instantiates all
	 * other member variables.
	 * 
	 * @param filename
	 *            The file path necessary for the FileHandler constructor
	 */
	public SimulatedAnnealing(String filename) {
		this.pf = new Playfair();
		this.s = new Score();
		this.kg = Keygen.getInstance();
		this.sh = new Shuffler();
		this.fh = new FileHandler(filename);
		this.dg = new Digrapherator(this.fh.readFile());
		this.random = new SecureRandom();
	}

	/**
	 * Will extract the best key combination to decrypt the encrypted text to
	 * produce the most English decryption by using the SimulatedAnnealing
	 * algorithm. The parent node in this situation is the generated key and the
	 * child node is a shuffle of the parent. If the child is better than the
	 * better during a transition, then set the child to the parent but also
	 * sometimes set the child to the parent too. This is calculated using a
	 * delta which is childLog - parentLog since the log produces a number in
	 * the negative, anything that produces a positive must mean that one was
	 * significantly greater than the other. After some playing around with
	 * tempretures I found that 15 worked the best to produce a key, I also
	 * checked to see if the parentLog was the bestLog upon finishing
	 * transitions to stop the algorithm from completely shuffling the best key
	 * by "wandering off". This was my method of doing "Until Number of Expanded
	 * Nodes < Transition Threshold" slide 18 of HeuristicSearch
	 * 
	 * @return The best key and the deciphered text that comes with the best
	 *         key.
	 */
	public String startSimulation() {
		String parent = this.kg.generateKey();
		String decipheredText = this.pf.decrypt(this.dg.getDiagraphs(), parent);
		double parentLog = this.s.scoreProb(decipheredText);
		int temp;
		int transition;
		String child = parent;
		double childLog;
		String mostEnglish = "";
		double bestLog = parentLog;

		System.out.println("DECRYPTING.. PLEASE WAIT");

		for (temp = 15; temp >= 0; temp--) {
			for (transition = 50000; transition >= 0; transition--) {
				child = this.sh.shuffle(parent);
				decipheredText = this.pf.decrypt(this.dg.getDiagraphs(), child);
				childLog = this.s.scoreProb(decipheredText);
				double delta = childLog - parentLog;

				if (delta > 0) {
					parentLog = childLog;
					if (parentLog > bestLog) {
						bestLog = parentLog;
						mostEnglish = decipheredText;
					}
					parent = child;
				} else {
					if ((Math.exp((delta / temp))) > random.nextDouble()) {
						parent = child;
						parentLog = childLog;
					}
				}
			}
			if (parentLog == bestLog) {
				break;
			}
		}

		return "Done\n-------------------\nBest Key: " + parent + "\nOutput: " + mostEnglish;

	}
}
