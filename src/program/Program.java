package program;

import finance.*;
import management.*;
import team.*;

public class Program{

    private InputHandler input;
    private LoginVerifyer login;
    private boolean systemRunning;

    private MemberManage memberManage;
    private AccountManage accountManage;
    private TeamManage teamManage;

    public Program(){
      input = new InputHandler();
      login = new LoginVerifyer();

      systemLoop();
    }

    public void systemLoop(){
      systemRunning = true;

      while(systemRunning){
        String username = input.giveInput();
        String password = input.giveInput();

        login.verifyLogin(username, password);
      }
    }

}
