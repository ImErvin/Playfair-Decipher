package ie.gmit.sw;

import ie.gmit.sw.Menu.UserMenu;
import ie.gmit.sw.Text.FileHandler;

public class Runner {
	public static void main(String[] args) {
		UserMenu menu = new UserMenu();
		FileHandler fh = new FileHandler("src/tips.txt");
		fh.readFile();
		menu.activateMenu();
	}
}
