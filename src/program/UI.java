package program;

import java.util.*;

public class UI{

	Scanner scanner;

	public UI(){

	}

	public void clear() {
		System.out.print("\033[H\033[2J");
	}

	public void top(){
		System.out.print("\n░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓ SK41 Management ▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░\n");

	}

	public void bot(){
		System.out.print("\n░░░▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓▓▓ Version 1.0 ▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒░░░░\n");
	}

	//Det vi kalder inde i Program = UI.login
	public void login(UserType login){
		top();
		switch(login){
			case CHAIRMAN:
				System.out.println("\n0. Brugerhåndtering");
				break;
			case TEAMLEADER:
				System.out.println("\n0. Holdhåndtering");
				break;
			case ACCOUNTANT:
				System.out.println("\n0. Økonomi menu");
				break;
		}
		if(login != UserType.NONE){
			System.out.print("1. Log ud");
		} else {
			System.out.print("\n1. Log ind");
		}

		System.out.print("\n2. Afslut\n");
		bot();
		input();
	}

	public void chairman(){
		top();
		System.out.print("\nBrugerhåndtering\n");
		System.out.print("\n1. Tilføj medlem\n2. Opdatér medlem\n3. Fjern medlem\n4. Vis alle medlemmer\n5. Tilbage\n");
		bot();
		input();
	}

	public void accountant(){
		top();
		System.out.print("\nØkonomi\n");
		System.out.print("\n1. Medlemmer i restance\n2. Find junior medlemmer\n3. Tilbage\n");
		bot();
		input();
	}

	public void teamleader(){
		top();
		System.out.print("\nHoldhåndtering\n");
		System.out.print("\n1. Skakmode\n2. Find medlem på rating\n3. Tilbage\n");
		bot();
		input();
	}

	public void teamleaderChess(){
		top();
		System.out.print("\nHold Menu\n");
		System.out.print("\n1. Lynskak\n2. Hurtigskak\n3. Standard tuneringsskak\n4. Korrespondanceskak\n5. Tilbage\n");
		bot();
		input();
	}

	public void input(){
		System.out.print("\n- ");
	}

	public void wrongInput(){
		System.out.println("\nForkert valg. Prøv igen.");
		promptEnterMessage();
	}

	public void bye(){
		System.out.println("\nProgrammet afsluttes. Ses!\n");
	}

	public void promptEnterMessage(){
		scanner = new Scanner(System.in);
		System.out.print("\nTryk enter for at fortsætte..");
		scanner.nextLine();
	}


}
