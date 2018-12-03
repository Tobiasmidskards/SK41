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

	public void chairman(){
		top();
		System.out.print("\n1. Add Member\n2. Update Member\n3. Delete Member\n4. Exit\n");
		bot();
		input();
	}

	public void accountant(){
		top();
		System.out.print("\n1. Unpaid subscription\n2. Find Junior members\n3. Exit\n");
		bot();
		input();
	}

	public void teamleader(){
		top();
		System.out.print("\n1. Chessmode\n2. Find member by rating\n3. Exit\n");
		bot();
		input();
	}

	public void teamleaderChess(){
		top();
		System.out.print("\n1. Lynskak\n2. Hurtigskak\n3. Standard tuneringsskak\n4. Korrespondanceskak\n");
		bot();
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
