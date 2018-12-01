package program;

import java.util.*;

// Vi skal bruge Base64 til at kryptere.
import java.util.Base64;

import management.*;

public class LoginVerifyer{

  FileHandler fileHandler;

  User user;

  public LoginVerifyer(){
    // Vi initializere de klasser vi skal bruge i konstructoren.

    fileHandler = new FileHandler();

    // Ingen bruger er logged in. Derfor kalder vi en tom konstructor. (som sætter user.userID = -1);
    user = new User();
  }

  public boolean verifyLogin(String username, String password){


    // Tjekker om der allerede er en som er logged ind.
    if(getLogin()){
      System.out.printf("Error: %s is already logged in.\n", user.getFirstname());
      return false;
    }

    // sætter en variabel, så vi kan stoppe med at søge hvis vi har fundet loginoplysningerne i databasen.
    boolean succes = false;

    // Smækker en scanner på vores databsefil.
    Scanner file = fileHandler.openFile("db/login.txt");

    // Den første linie vil være navnene på kolonnerne, så vi hopper lige den linie over.
    file.nextLine();

    // Hvis der er flere linier OG vi ikke hare fundet personen endnu, så kører whileloopet.
    while(file.hasNextLine() && !succes) {

      // Splitter den linie vi er på op i et array, hvor der mellemrum.
      // F.eks. "hej med dig" = ["hej", "med", "dig"].
      // Så line[0] vil være = "hej"

      String[] line = file.nextLine().split(" ");

      // Hvis vi har fundet username, kan vi begynde at tjekke passwordet.
      if(username.equals(line[1])){

        // Vi kryptere det givne password som brugeren har tastet ind.
        byte[] encodedPassword = Base64.getEncoder().encode(password.getBytes());

        // Så laver vi det password fra databasen, om til samme type, så vi kan sammenligne.
        byte[] parPassword = line[2].getBytes();

        // Så sammenligner vi de 2 passwords.
        if (Arrays.equals(encodedPassword, parPassword)) {
          System.out.println("Info: You have successfully logged in.");

          // Bruger setters til at sætte informationerne ind i User instancen.
          user.setUserID(Integer.parseInt(line[0])); // Vi laver string om til en Int.
          user.setFirstname(line[3]);
          user.setLastname(line[4]);
          user.setEMail(line[5]);


          switch(Integer.parseInt(line[6])) {
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

          return true;

        } else {
          System.out.println("Error: Wrong password. Try again.");
          return false;
        }

      }

    }

    System.out.println("Error: No user with username: " + username + ".");

    // byte[] encodedBytes1 = Base64.getEncoder().encode(line[2].getBytes());
    // byte[] encodedBytes2 = Base64.getEncoder().encode(pass.getBytes());

    // byte[] decodedBytes1 = Base64.getDecoder().decode(encodedBytes1);
    // byte[] decodedBytes2 = Base64.getDecoder().decode(encodedBytes2);


    return false;

  }

  public void logOut(){
    if(getLogin()) {
      user = new User();
    } else {
      System.out.println("Error: There is no user logged in.");
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
