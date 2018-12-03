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

    private String inputString;

    public Program(){

      // initializere de klasser vi skal bruge.
      input = new InputHandler();
      login = new LoginVerifyer();
			ui = new UI();

      // Kalder mainloop fra konstructoren.
      systemLoop();
    }

    public void systemLoop(){
      // Vores main loop, som hele tiden kører.
      // Håndtere menuen,
      systemRunning = true;

      while(systemRunning){
				ui.clear();
        //kun print inde i UI klassen
        ui.login();
        //Kig i klasse InputHandler
        inputString = input.giveInput();

        //Anden form for if/else metode
				switch(inputString){
					case "1":
						loginPrompt();
						break;
					case "2":
						login.logOut();
						break;
					case "3":
						login.logOut();
						systemRunning = false;
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

		public void chairmanMenu(){
			//ui.chaiman();
			inputString = input.giveInput();

			switch(inputString){
				case "1":

					break;
				case "2":

					break;
				case "3":

					break;
			}
		}

}
