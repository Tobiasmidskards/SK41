public class System{

    private InputHandler input;
    private LoginVerifyer login;
    private boolean systemRunning;

    private MemberManage memberManage;
    private AccountManage accountManage;
    private TeamManage teamManage;

    public System(){
      input = new InputHandler();
      login = new LoginVerifyer();
    }

    public void systemLoop(){
      while(systemRunning){

      }
    }

}
