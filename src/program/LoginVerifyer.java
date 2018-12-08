package program;

import java.util.*;

// Vi skal bruge Base64 til at kryptere.
import java.util.Base64;

import management.*;

public class LoginVerifyer{

  private FileHandler fileHandler;

  private User user;

  public LoginVerifyer(){
    // Vi initializere de klasser vi skal bruge i konstructoren.

    fileHandler = new FileHandler();

    // Ingen bruger er logged in. Derfor kalder vi en tom konstructor. (som sætter user.userID = -1);
    user = new User();
  }

  public boolean verifyLogin(String username, String password){


    // Tjekker om der allerede er en som er logged ind.
    if(getLogin()){
      System.out.printf("\nFejl: %s er allerede logged ind.\n", user.getFirstname());
      return false;
    }

    // sætter en variabel, så vi kan stoppe med at søge hvis vi har fundet loginoplysningerne i databasen.
    boolean succes = false;

    // Smækker en scanner på vores databasefil.
    Scanner loginFile = fileHandler.openFile("db/login.tsv");

    // Den første linie vil være navnene på kolonnerne, så vi hopper lige den linie over.
    loginFile.nextLine();

    // Hvis der er flere linier OG vi ikke hare fundet personen endnu, så kører whileloopet.
    while(loginFile.hasNextLine() && !succes) {
      // Splitter den linie vi er på op i et array, hvor der tabs.
      // F.eks. "hej med dig" = ["hej", "med", "dig"].
      // Så loginLine[0] vil være = "hej"
      String[] loginLine = loginFile.nextLine().split("\\t");

      // Hvis vi har fundet username, kan vi begynde at tjekke passwordet.
      if(username.equals(loginLine[1])){

        // Vi kryptere det givne password som brugeren har tastet ind.
        byte[] encodedPassword = Base64.getEncoder().encode(password.getBytes());

        // Så laver vi det password fra databasen, om til samme type, så vi kan sammenligne.
        byte[] parPassword = loginLine[2].getBytes();

        // Så sammenligner vi de 2 passwords.
        if (Arrays.equals(encodedPassword, parPassword)) {
          System.out.println("\nInfo: Du er nu logged ind.");

					// Smækker en scanner på vores databsefil.
					Scanner memberFile = fileHandler.openFile("db/members.tsv");

					// Den første linie vil være navnene på kolonnerne, så vi hopper lige den linie over.
					memberFile.nextLine();

					// sætter en variabel, så vi kan stoppe med at søge hvis vi har fundet memberoplysningerne i databasen.
			    boolean memberSucces = false;

					while(memberFile.hasNextLine() && !memberSucces) {
						// Splitter den linie vi er på op i et array, hvor der tabs.
			      // F.eks. "hej med dig" = ["hej", "med", "dig"].
			      // Så loginLine[0] vil være = "hej"

			      String[] memberLine = memberFile.nextLine().split("\\t");

						if(loginLine[0].equals(memberLine[0])){

							// Bruger setters til at sætte informationerne ind i User instancen.
							user.setUserID(Integer.parseInt(loginLine[0])); // Vi laver string om til en Int.
							user.setFirstname(memberLine[2]);
							user.setLastname(memberLine[3]);
							user.setEMail(memberLine[7]);

							memberSucces = true;
						}
					}

					// sætter typen på ham der er logged in, baseret på den string der er i databasen.
          switch(Integer.parseInt(loginLine[3])) {
            case 0:
              user.setUserType(UserType.CHAIRMAN);
              break;
            case 1:
              user.setUserType(UserType.ACCOUNTANT);
              break;
            case 2:
              user.setUserType(UserType.TEAMLEADER);
              break;
          }

					// succes, der er en som er logged ind!
					// stop funktionen.
          return true;

        } else {

					// Vi kunne ikke finde passwordet i vores login.txt
          System.out.println("\nFejl: Forkert password. Prøv igen.");
          return false;
        }

      }

    }

		// Vi kunne ikke finde brugernavnet i vores login.txt
    System.out.println("\nFejl: Der findes ingen bruger: " + username + ".");

    // byte[] encodedBytes1 = Base64.getEncoder().encode(loginLine[2].getBytes());
    // byte[] encodedBytes2 = Base64.getEncoder().encode(pass.getBytes());

    // byte[] decodedBytes1 = Base64.getDecoder().decode(encodedBytes1);
    // byte[] decodedBytes2 = Base64.getDecoder().decode(encodedBytes2);

    return false;

  }

  public void logOut(){
    if(getLogin()) {
			System.out.printf("\nInfo: %s have logged out.\n", user.getFirstname());
      user = new User();
    } else {
      System.out.println("\nError: There is no user logged in.");
    }

  }

  public User getUser(){
    return user;
  }

  public boolean getLogin(){

    // Hvis userID er -1, så er der ikke nogen som er logged in.

    if (user.getUserID() == -1) {
      return false;
    } else {
      return true;
    }
  }

  public String createPassword(String password){

    // Missing implementation
    return password;
  }

}
