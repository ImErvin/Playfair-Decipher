package ie.gmit.sw;

import ie.gmit.sw.Cyphers.Playfair;
import ie.gmit.sw.Keygen.Keygen;
import ie.gmit.sw.Menu.UserMenu;
import ie.gmit.sw.Text.Digrapherator;
import ie.gmit.sw.Text.FileHandler;

public class Runner {
	public static void main(String[] args) {
		FileHandler fh = new FileHandler("./tips.txt");
		Digrapherator d = new Digrapherator(fh.readFile());
		Playfair pf = new Playfair();
		Keygen kg = new Keygen();
		
		String key = kg.generateKey();
		String[] digraphs = d.getDiagraphs();
		System.err.println(pf.decrypt(digraphs, "KMPACXWYBOFVITHNLEDZGSQUR"));
	}
}
