package program;

import java.util.*;
import java.util.Base64;

public class LoginVerifyer{
  boolean loggedIn = false;
  FileHandler fileHandler = new FileHandler();
  // UserType userType = new UserType();
  // UserType loggedInUserType;


  public LoginVerifyer(){
    // . . .
  }

  public boolean verifyLogin(String username, String password){

    boolean succes = false;

    Scanner file = fileHandler.openFile("db/login.txt");
    file.nextLine();

    while(file.hasNext() || !succes) {
      String[] line = file.nextLine().split(" ");



      if(username.equals(line[1])){
        byte[] encodedPassword = Base64.getEncoder().encode(password.getBytes());
        byte[] parPassword = line[2].getBytes();

        // System.out.printf("%s %s", encodedPassword, parPassword);
        if (Arrays.equals(encodedPassword, parPassword)) {
          System.out.println("hvad så der");
          succes = true;
          loggedIn = true;
        }
      }

    }

    // String pass = "eddy";
    //
    // byte[] encodedBytes1 = Base64.getEncoder().encode(line[2].getBytes());
    // byte[] encodedBytes2 = Base64.getEncoder().encode(pass.getBytes());

    // byte[] decodedBytes1 = Base64.getDecoder().decode(encodedBytes1);
    // byte[] decodedBytes2 = Base64.getDecoder().decode(encodedBytes2);


    return succes;

  }

  // public static void logOut(){
  //   loggedIn = false;
  // }
  //
  //
  // // SET AND GET METHODS.
  // public static UserType getLoggedInUserType(){
  //   return loggedInUserType;
  // }
  //
  // public static UserType getUserType(){
  //   return userType;
  // }
  //
  // public static boolean getLogin(){
  //   return loggedIn;
  // }

}
