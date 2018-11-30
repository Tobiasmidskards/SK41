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
    }

    public void systemLoop(){
      while(systemRunning){

      }
    }

}
