package team;

public class Team{

  private String teamID, teamLeaderID;
  private ArrayList<Member> players;

  public Team(){
    this.players = new ArrayList<Member>();
  }

  public void addMember(){

  }

  public void removeMember(){

  }


	public String getTeamID() {
		return this.teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = teamID;
	}

	public String getTeamLeaderID() {
		return this.teamLeaderID;
	}

	public void setTeamLeaderID(String teamLeaderID) {
		this.teamLeaderID = teamLeaderID;
	}

	public ArrayList<Member> getPlayers() {
		return this.players;
	}


}
