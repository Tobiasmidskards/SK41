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
		System.out.print("\n------------ SK41 Management  -------------\n");
	}

	public void bot(){
		System.out.print("\n------------------ v0.1 -------------------\n");
	}
	//Det vi kalder inde i Program = UI.login
	public void login(){
		top();
		System.out.print("\n1. Login\n2. Logout\n3. Exit\n");
		bot();
		input();
	}

	public void input(){
		System.out.print("\n- ");
	}

	public void promptEnterMessage(){
		scanner = new Scanner(System.in);
		System.out.print("\nEnter anything to continue..");
		scanner.nextLine();
	}


}
