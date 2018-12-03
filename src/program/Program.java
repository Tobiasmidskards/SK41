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

    private String inputString;

    public Program(){

      // initializere de klasser vi skal bruge.
      input = new InputHandler();
      login = new LoginVerifyer();

      // Kalder mainloop fra konstructoren.
      systemLoop();
    }

    public void systemLoop(){
      // Vores main loop, som hele tiden kører.
      // Håndtere menuen,
      systemRunning = true;

      while(systemRunning){
				System.out.print("1. Login\n2. Logout\n- ");
        inputString = input.giveInput();

				switch(inputString){
					case "1":
						loginPrompt();
						break;
					case "2":
						login.logOut();
						break;
					case "3":
						break;
				}
      }
    }

    public void loginPrompt(){
      // Spørg efter input
      System.out.println("Please login:");
      System.out.print("Username: ");
      String username = input.giveInput();
      System.out.print("Password: ");
      String password = input.giveInput();

      // Tjekker om de givne oplysninger er sande.
      login.verifyLogin(username, password);
    }

}
