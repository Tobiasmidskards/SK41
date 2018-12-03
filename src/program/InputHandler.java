package program;

import java.util.*;
import java.io.*;

public class InputHandler{

  //attributter
  private String input;
  private Scanner scanner;

  //konstructor
  public InputHandler(){
    scanner = new Scanner(System.in);
  }

  public String giveInput(){
    return scanner.nextLine();
  }

}
