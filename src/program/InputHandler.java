package program;

import java.util.*;
import java.io.*;

public class InputHandler{

  //Attributter
  private String input;
  private Scanner scanner;

  //Konstructor
  public InputHandler(){
    scanner = new Scanner(System.in);
  }

	//Metoder
  public String giveInput(){
    return scanner.nextLine();
  }

}
