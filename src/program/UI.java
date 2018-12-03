package program;

public class UI{

	public UI(){

	}

	public void top(){
		System.out.print("\n---------- SK41 Management v0.1 -----------\n");
	}

	public void bot(){
		System.out.print("------------------ v0.1 -------------------\n");
	}

	public void login(){
		top();
		System.out.print("\n1. Login\n2. Logout\n3. Exit\n ");
		bot();
		input();
	}

	public void input(){
		System.out.print("\n- ");
	}


}
