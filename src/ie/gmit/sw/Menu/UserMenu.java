package ie.gmit.sw.Menu;

import java.util.Scanner;

public class UserMenu {
	private Scanner console;
	private int userOption;

	public UserMenu() {
		this.console = new Scanner(System.in);
	}

	public void displayIntroduction() {
		System.out.println("Using Simulated Annealing to Break a Playfair Cipher.");
		System.out.println("Written by Ervin Mamutov - github.com/imervin");
		System.out.print("\nPress Enter to continue..");
	}

	public void displayMenu() {
		System.out.println("\nUser Menu:");
		System.out.println("[1] Decrypt a file.");
		System.out.println("[0] Exit.");
		System.out.print("\nInput: ");
	}

	public void activateMenu() {
		boolean control = true;

		displayIntroduction();
		console.nextLine();
		do {
			displayMenu();
			
			try{
				userOption = console.nextInt();
				
				switch (userOption) {
				case 1:
					System.out.println("You have selected [1]");
					break;
				case 0:
					System.out.println("Thank you for using my application\nMake sure to check out my github.");
					control = false;
					break;
				default:
					System.out.println("Woops! ["+ userOption + "] is not on the menu - try again.");
					break;
				}
			}catch(Exception err){
				System.out.println("Woops! Seems like you have not entered a numeric value - try again.");
			}
			

		} while (control);
	}
}
