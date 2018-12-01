package program;

import java.util.*;
import java.util.Base64;

import management.*;

public class LoginVerifyer{
  boolean loggedIn = false;
  FileHandler fileHandler;
  User user = User();


  public LoginVerifyer(){
    fileHandler = new FileHandler();
  }

  public boolean verifyLogin(String username, String password){

    boolean succes = false;

    Scanner file = fileHandler.openFile("db/login.txt");
    file.nextLine();

    while(file.hasNextLine() && !succes) {
      String[] line = file.nextLine().split(" ");


      if(username.equals(line[1])){
        byte[] encodedPassword = Base64.getEncoder().encode(password.getBytes());
        byte[] parPassword = line[2].getBytes();

        if (Arrays.equals(encodedPassword, parPassword)) {
          System.out.println("You have successfully logged in.");
          loggedIn = true;
          return true;

        } else {
          System.out.println("Wrong password. Try again.");
          return false;
        }

      }

    }

    System.out.println("No user with username: " + username + ".");

    // byte[] encodedBytes1 = Base64.getEncoder().encode(line[2].getBytes());
    // byte[] encodedBytes2 = Base64.getEncoder().encode(pass.getBytes());

    // byte[] decodedBytes1 = Base64.getDecoder().decode(encodedBytes1);
    // byte[] decodedBytes2 = Base64.getDecoder().decode(encodedBytes2);


    return false;

  }

  public void logOut(){
    loggedIn = false;
  }

  // SET AND GET METHODS.
  public UserType getLoggedInUserType(){
    return loggedInUserType;
  }

  public UserType getUserType(){
    return userType;
  }

  public boolean getLogin(){
    return loggedIn;
  }

}
