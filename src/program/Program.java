package program;

import finance.*;
import management.*;
import team.*;

public class Program{


    // allokere hukkommelse til alle vores attributter.
    private InputHandler input;
    private LoginVerifyer login;
    private boolean systemRunning;

    private MemberManage memberManage;
    private AccountManage accountManage;
    private TeamManage teamManage;

		private UI ui;
		private MenuState state;

    private String inputString;

    public Program(){

      // initializere de klasser vi skal bruge.
      input = new InputHandler();
      login = new LoginVerifyer();
			ui = new UI();
			state = MenuState.LOGIN;

      // Kalder mainloop fra konstructoren.
      systemLoop();
    }

    public void systemLoop(){
      // Vores main loop, som hele tiden kører.
      // Håndtere menuen,
      systemRunning = true;

      while(systemRunning){
				ui.clear();

        //Anden form for if/else metode
				switch(state){
					case LOGIN:
						login();
						break;
					case MANAGEMENT:
						chairmanMenu();
						break;
					case FINANCE:
						accountantMenu();
						break;
					case TEAM:
						teamleaderMenu();
						break;
				}
      }
    }

		public void login(){
			ui.login();

			inputString = input.giveInput();

			switch(inputString){
				case "1":
					loginPrompt();
					ui.promptEnterMessage();
					break;
				case "2":
					login.logOut();
					ui.promptEnterMessage();
					break;
				case "3":
					login.logOut();
					systemRunning = false;
					break;
			}

		}

    public void loginPrompt(){
      // Spørg efter input
      System.out.println("\nPlease login:");
      System.out.print("Username: ");
      String username = input.giveInput();
      System.out.print("Password: ");
      String password = input.giveInput();

      // Tjekker om de givne oplysninger er sande.
      login.verifyLogin(username, password);

			if(login.getLogin()){
				switch(login.getUser().getUserType()) {
					case CHAIRMAN:
						state = MenuState.MANAGEMENT;
						break;
					case TEAMLEADER:
						state = MenuState.TEAM;
						break;
					case ACCOUNTANT:
						state = MenuState.FINANCE;
						break;
					default:
						state = MenuState.LOGIN;
						break;
				}
			}
    }

		public void chairmanMenu(){
			ui.chairman();
			inputString = input.giveInput();

			switch(inputString){
				case "1":
          chairmanAdd();
					break;
				case "2":
          chairmanUpdate();
					break;
				case "3":
          chairmanUpdate();
					break;
        case "4":
					state = MenuState.LOGIN;
          break;
        }
			}

    public void chairmanAdd(){
      System.out.println("Fill out the form");
      System.out.print("First Name: ");
      String Firstname = input.giveInput();
      System.out.print("Last Name: ");
      String Lastname = input.giveInput();
      System.out.print("Address: ");
      String Address = input.giveInput();
      System.out.print("CPR: ");
      String CPR = input.giveInput();
      System.out.print("Birthday (dd-mm-yyyy): ");
      String Birthday = input.giveInput();
      System.out.print("Membertype: ");
      String Membertype = input.giveInput();
      System.out.print("Phone number: ");
      String Phonenumber = input.giveInput();
      System.out.print("Email: ");
      String Email = input.giveInput();
      System.out.print("Player Type: ");
      String Playertype = input.giveInput();

      ui.promptEnterMessage();
    }

    public void chairmanUpdate (){
  		System.out.println("Please enter Member ID");
  		System.out.print("Member ID: ");
      String ID = input.giveInput();
    	}

    public void accountantMenu(){
      ui.accountant();
      inputString = input.giveInput();

      switch(inputString){
        case "1":
          accountantUnpaid();
          break;
        case"2":
          findJuniorMem();
          break;
        case"3":
					state = MenuState.LOGIN;
          break;
      }
    }
    public void accountantUnpaid(){
      System.out.println("Please enter Member ID");
      System.out.print("Member ID: ");
      String ID = input.giveInput();
    }

    public void findJuniorMem(){
      System.out.println("Please enter Memeber ID");
      System.out.print("Member ID: ");
      String ID = input.giveInput();
    }


    public void teamleaderMenu(){
      ui.teamleader();
      inputString = input.giveInput();

      switch(inputString){
        case "1":
          teamleaderPromt();
          break;
        case "2":

          break;
        case "3":
					state = MenuState.LOGIN;
          break;
      }
    }
    public void teamleaderPromt(){
      ui.teamleaderChess();
      inputString = input.giveInput();

      switch (inputString) {
        case "1":

          break;
        case "2":

          break;
        case "3":
					state = MenuState.TEAM;
          break;

      }
    }

}
