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
			accountManage = new AccountManage();
			teamManage = new TeamManage();
			ui = new UI();
      memberManage = new MemberManage();
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
					case TEAM2:
						teamleaderPromt();
						break;
				}
      }

			ui.bye();
    }

		public void login(){
      UserType isLoggedin = login.getUser().getUserType();

			ui.login(isLoggedin);

			inputString = input.giveInput();

			switch(inputString){
				case "0":
					if(login.getLogin()){
						changeMenu();
					} else {
						ui.wrongInput();
					}

					break;
				case "1":
					loginPrompt();
					ui.promptEnterMessage();
					break;
				case "2":
					systemRunning = false;
					break;
				default:
					ui.wrongInput();
					break;
			}

		}

    public void loginPrompt(){
      // Spørg efter input

      if(login.getLogin()){
        login.logOut();
      } else {
        System.out.println("\nDu bedes indtaste dine loginoplysninger.\n");
        System.out.print("Brugernavn: ");
        String username = input.giveInput();
        System.out.print("Adgangskode: ");
        String password = input.giveInput();

        // Tjekker om de givne oplysninger er sande.
        login.verifyLogin(username, password);

				changeMenu();
			}
    }

		public void changeMenu(){
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
					case NONE:
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
          chairmanRemove();
					break;
        case "4":
					memberManage.showAllMembers();
          ui.promptEnterMessage();
          break;
        case "5":
          state = MenuState.LOGIN;
          break;
				default:
					ui.wrongInput();
					break;
        }
			}

    public void chairmanAdd(){

			String[] form = new String[11];

      System.out.println("\nDu er ved at oprette en bruger i systemet. \nIndtast venligst følgende oplysninger:\n");
			System.out.print("CPR-nummer (xxxxxxxxxx): ");
      form[0] = input.giveInput();
			System.out.print("Navn: ");
      form[1] = input.giveInput();
			System.out.print("Efternavn: ");
      form[2] = input.giveInput();
			System.out.print("Fødselsdag (ddmmyyyy): ");
      form[3] = input.giveInput();
			System.out.print("Addresse: ");
      form[4] = input.giveInput();
      System.out.print("Telefon: ");
      form[5] = input.giveInput();
      System.out.print("Email: ");
      form[6] = input.giveInput();
      System.out.print("\n1: Aktiv\n2: Passiv\n\nMedlemstype: ");
      form[7] = input.giveInput();
      System.out.print("\n1: Spiller for sjov\n2: Tourneringsspiller\n\nSpillertype : ");
      form[8] = input.giveInput();
      System.out.print("Rating : ");
      form[9] = input.giveInput();
      System.out.print("ELO : ");
      form[10] = input.giveInput();

			if(!memberManage.addMember(form)){
				ui.wrongInput();
			} else {
				ui.promptEnterMessage();
			}

    }

    public void chairmanUpdate (){
  		System.out.println("\nHvilket medlem ønsker du at opdatere?");
  		System.out.print("\nMedlems ID: ");
      String memberID = input.giveInput();
			System.out.println("\nHvilket felt vil du opdatere?\n ");
			System.out.println("1. Navn\n2. Efternavn\n3. Adresse\n4. Fødselsdag\n5. Telefon\n6. e-mail\n7. medlemstype\n8. Spillertype\n9. Rating\n10. ELO");
			ui.input();
			String field = input.giveInput();

			if(!memberManage.updateMember(memberID, field)) {
				ui.wrongInput();
			} else {
				ui.promptEnterMessage();
			}

    }

		public void chairmanRemove (){
			System.out.println("Indtast ID på det medlem du ønsker at fjerne.");
			System.out.print("Medlems ID: ");
			String ID = input.giveInput();
			memberManage.deleteMember(ID);
			ui.promptEnterMessage();
			}

    public void accountantMenu(){
      ui.accountant();
      inputString = input.giveInput();

      switch(inputString){
        case "1":
					accountManage.missedPayment();
          ui.promptEnterMessage();
          break;
        case"2":
          accountManage.findJuniorMembers();
					ui.promptEnterMessage();
          break;
        case"3":
					state = MenuState.LOGIN;
          break;
				default:
					ui.wrongInput();
					break;
      }
    }
    public void accountantUnpaid(){
      System.out.println("Indtast ID på det ønskede medlem.");
      System.out.print("Medlems ID: ");
      String ID = input.giveInput();
    }

    public void findJuniorMem(){
      System.out.println("Indtast ID på det ønskede medlem.");
      System.out.print("Medlems ID: ");
      String ID = input.giveInput();
    }


    public void teamleaderMenu(){
      ui.teamleader();
      inputString = input.giveInput();

      switch(inputString){
        case "1":
					ui.clear();
          state = MenuState.TEAM2;
          break;
        case "2":
					teamManage.allMemberRatings();
					ui.promptEnterMessage();
          break;
				case "3":
					teamManage.createNewTeams();
					ui.promptEnterMessage();
					break;
        case "4":
					state = MenuState.LOGIN;
          break;
				default:
					ui.wrongInput();
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

          break;
				case "4":

					break;
				case "5":
					state = MenuState.TEAM;
					break;
				default:
					ui.wrongInput();
					break;

      }
    }

    public void blitzChess(){
      System.out.println("Indtast venligst ");
    }
}
