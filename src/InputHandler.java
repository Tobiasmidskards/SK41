import java.util.*;

public class InputHandler{

  private String input;
  private Scanner scanner;

  public InputHandler(){
    scanner = new Scanner(System.in);
  }

  public String giveInput(){
    return scanner.nextLine();
  }

}
