package management;

import program.*;

import java.io.PrintWriter;
import java.util.*;

public class MemberManage{

  private MemberFH memberFH;
  private Member temporaryMember;
  private FileHandler filehandler;
  private PrintWriter memberWriter;
  private PrintWriter accountWriter;
  private Scanner scanner;
  private String line;
  private Date date;

	public MemberManage() {
    memberFH = new MemberFH();
    filehandler = new FileHandler();
    memberWriter = filehandler.printFile("db/members.txt");
    accountWriter = filehandler.printFile("db/accounting.txt");
	}

  public void showAllMembers(){
    scanner = filehandler.openFile("db/members.txt");
    scanner.nextLine();

    String indent = "              ";

    String[] rows = new String[]{"BrugerID","Navn","Fødselsdag","Telefon","Adresse"};

    for (int s = 0; s<rows.length;s++) {
      rows[s] += indent.substring(0, indent.length() - rows[s].length());
    }

    System.out.println("\nViser alle brugere:\n" + rows[0] + rows[1] + rows[2] + rows[3] + rows[4]);

    while(scanner.hasNextLine()){
      String[] line = scanner.nextLine().split("\\t");

      for (int s = 0; s<line.length;s++) {
        if(s == 1 || s == 3 || s == 7) {
          // Do nothing :'(
        } else {
          line[s] += indent.substring(0, indent.length() - line[s].length());
        }

      }

      System.out.printf("%s%s%s%s%s\n", line[0], line[2], line[4], line[6] ,line[5]);
    }


  }

  public void addMember(String cpr, String firstname, String lastName, String dateOfBirth,
                        String address, String phoneNumber, String email, String rating,
                        String elo, String memberstatus, String playertype){


    // er spilleren allerede i systemet?
    //
    // if(cpr.equals(database.cpr)) {
    //   afbryd
    // }

    // Skal altid være et id, som ikke findes. Hvordan gør vi det bedst?
    
    int userID = 10;

    line = userID + "\t" + cpr + "\t" + firstname + "\t" +
           lastName + "\t" + dateOfBirth + "\t" + address + "\t" +
           phoneNumber + "\t" + email + "\t" + rating + "\t" + elo;

    memberWriter.println(line);
    memberWriter.flush();

    date = new Date();
    String dateFormat = String.format("%1$td%1$tm%1$tY", date);
    String debt = "0";

    if(playertype.equals("0")){
      playertype = "funPlayer";
    } else {
      playertype = "tournamentPlayer";
    }

    if(memberstatus.equals("0")){
      memberstatus = "active";
    } else {
      memberstatus = "passive";
    }

    line = userID + "\t" + memberstatus + "\t" + playertype + "\t" + dateFormat + "\t" + debt;
    accountWriter.println(line);
    accountWriter.flush();

    memberWriter.close();
    accountWriter.close();
  }

  public void updateMember(){

  }

  public void deleteMember(){

  }

  public Member getMember(){
    return this.temporaryMember;
  }

}
