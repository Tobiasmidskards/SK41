package program;

import java.util.*;
import java.util.Base64;

import management.*;

public class LoginVerifyer{
  boolean loggedIn = false;
  FileHandler fileHandler;
  
  User user = new User(); // no user is logged in ~ empty constructor (init with userID -1)

  public LoginVerifyer(){
    fileHandler = new FileHandler();
  }

  public boolean verifyLogin(String username, String password){

    if(getLogin()){
      System.out.printf("Error: %s is already logged in.\n", user.getFirstname());
      return false;
    }

    boolean succes = false;

    Scanner file = fileHandler.openFile("db/login.txt");
    file.nextLine();

    while(file.hasNextLine() && !succes) {
      String[] line = file.nextLine().split(" ");


      if(username.equals(line[1])){
        byte[] encodedPassword = Base64.getEncoder().encode(password.getBytes());
        byte[] parPassword = line[2].getBytes();

        if (Arrays.equals(encodedPassword, parPassword)) {
          System.out.println("Info: You have successfully logged in.");

          user = new User();

          user.setUserID(Integer.parseInt(line[0]));
          user.setFirstname(line[3]);
          user.setLastname(line[4]);
          user.setEMail(line[5]);

          UserType userType = UserType.TEAMLEADER;

          switch(Integer.parseInt(line[6])) {
            case 0:
              userType = UserType.CHAIRMAN;
              break;
            case 1:
              userType = UserType.ACCOUNTANT;
              break;
            case 2:
              userType = UserType.TEAMLEADER;
              break;
          }

          user.setUserType(userType);


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
    if (user.getUserID() == -1) {
      return false;
    } else {
      return true;
    }
  }

}
