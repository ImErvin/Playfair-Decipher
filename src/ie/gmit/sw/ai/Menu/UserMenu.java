package ie.gmit.sw.ai.Menu;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import ie.gmit.sw.ai.Scoring.SimulatedAnnealing;
import ie.gmit.sw.ai.Text.FileHandler;

/**
 * Class that represents a console UI for the user to work with the program.
 * 
 * @author Ervin Mamutov | github.com/imervin
 *
 */
public class UserMenu {
	// Scanner to get input
	private Scanner console;

	/**
	 * Default constructor instantiates a scanner with system.in
	 */
	public UserMenu() {
		this.console = new Scanner(System.in);
	}

	/**
	 * Will display all the files at provided path using a Stream with a map
	 * function.
	 * 
	 * @param path
	 *            The path to directory to be searched for files.
	 */
	public void displayFiles(String path) {
		Arrays.stream(new File(path).listFiles()).map(file -> file.getName()).forEach(i -> System.out.println(i));
	}

	/**
	 * A sub-menu of the user-menu used to initiate annealing upon user consent.
	 * 
	 * @param path
	 *            The path to the file to be decrypted
	 */
	public void getFile(String path) {
		boolean control = true;
		try {
			SimulatedAnnealing sm = new SimulatedAnnealing(path);
			do {
				System.out.println("Successfully read file do you want to continue to start decryping?");
				System.out.println("[1] Yes");
				System.out.println("[2] No");
				System.out.print("\nInput: ");
				switch (console.nextInt()) {
				case 1:
					boolean control1 = true;
					String output = sm.startSimulation();
					System.out.println(output + "\n");
					System.out.println("Do you want to save this record?");
					do {
						System.out.println("[1] Yes");
						System.out.println("[2] No");
						System.out.print("\nInput: ");

						switch (console.nextInt()) {
						case 1:
							FileHandler fh = new FileHandler();
							String written = fh.writeFile(path, output);
							System.out.println(written);
							control = false;
							control1 = false;
							break;
						case 2:
							control1 = false;
							break;
						default:
							System.out.println("Woops! that option is not on the menu - try again.");
							break;
						}
					} while (control1);

				case 2:
					control = false;
					break;
				default:
					System.out.println("Woops! that option is not on the menu - try again.");
					break;
				}
			} while (control);

		} catch (Exception err) {
			System.out.println("Could not find that directory");
		}
	}
	
	
	public void displayIntroduction() {
		System.out.println("Using Simulated Annealing to Break a Playfair Cipher.");
		System.out.println("Written by Ervin Mamutov - github.com/imervin");
	}

	public void displayMenu() {
		System.out.println("\nUser Menu:");
		System.out.println("[1] View files in directory");
		System.out.println("[2] Decrypt a file");
		System.out.println("[0] Exit.");
		System.out.print("\nInput: ");
	}

	/**
	 * A submenu for the user menu that handles the file search.
	 */
	public void optionOne() {
		boolean control = true;
		do {
			System.out.println("\nView Files:");
			System.out.println("[1] Current Directory");
			System.out.println("[2] Absolute Path");
			System.out.println("[0] Back");
			System.out.print("\nInput: ");
			switch (userOption()) {
			case 1:
				displayFiles(".");
				break;
			case 2:
				System.out.println("*Remember to attach C:/Users/<yourname> if you're on a Windows Platform.");
				System.out.print("Enter path: ");
				String path = console.next();
				try {
					displayFiles(path);
				} catch (Exception err) {
					System.out.println("Could not find that directory");
				}
				break;
			case 0:
				control = false;
				break;
			default:
				System.out.println("Woops! that option is not on the menu - try again.");
				break;
			}
		} while (control);
	}
	
	/**
	 * A submenu for the user menu that handles file decryption
	 */
	public void optionTwo() {
		System.out.println("\nDecrypt a File:");
		System.out.print("Enter the file path: ");
		getFile(console.next());
	}
	
	/**
	 * A helper function that retrieves users input into the console.
	 * @return Users input to the console.
	 */
	public int userOption() {
		int userOption;
		try {
			userOption = console.nextInt();
			return userOption;
		} catch (Exception err) {
			System.out.println("Woops! Seems like you have not entered a numeric value - try again.");
			return -1;
		}
	}
	
	/**
	 * Activates the menu to be used by the runner class.
	 */
	public void activateMenu() {
		boolean control = true;

		displayIntroduction();

		do {
			displayMenu();

			switch (userOption()) {
			case 1:
				optionOne();
				break;
			case 2:
				optionTwo();
				break;
			case 0:
				System.out.println("Thank you for using my application\nMake sure to check out my github.");
				control = false;
				break;
			default:
				System.out.println("Woops! that option is not on the menu - try again.");
				break;
			}

		} while (control);
	}
}
