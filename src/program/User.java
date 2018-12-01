package program;

public class User{
  private String firstname, lastname, eMail;
  private UserType userType;
  private int userID;

  public User(){
    this.userID = -1;
  }

  public User(String firstname, String lastname, String eMail, UserType userType, int userID) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.eMail = eMail;
    this.userType = userType;
    this.userID = userID;
  }

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEMail() {
		return this.eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

}
