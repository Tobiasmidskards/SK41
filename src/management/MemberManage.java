package management;

import program.*;

import java.io.PrintWriter;
import java.util.*;

public class MemberManage{

  private Member temporaryMember;
  private FileHandler filehandler;
  private PrintWriter memberWriter;
  private PrintWriter accountWriter;
  private Scanner scanner;
  private String line;
  private Date date;

	public MemberManage() {
    filehandler = new FileHandler();
    memberWriter = filehandler.printFile("db/members.tsv");
    accountWriter = filehandler.printFile("db/accounting.tsv");
	}

  public void showAllMembers(){
    scanner = filehandler.openFile("db/members.tsv");
    scanner.nextLine();

    String indent = "                  ";

    String[] rows = new String[]{"BrugerID","Navn","Telefon","Adresse"};

		rows[0] += indent.substring(0, 12 - rows[0].length());

    for (int s = 1; s<rows.length-1;s++) {
      rows[s] += indent.substring(0, indent.length() - rows[s].length());
    }

    System.out.println("\nViser alle brugere:\n" + rows[0] + rows[1] + rows[2] + rows[3]);

    while(scanner.hasNextLine()){
      String[] line = scanner.nextLine().split("\\t");

			if(!line[0].equals("\\N")){
				String name = "";

				line[0] += indent.substring(0, 12 - line[0].length());
				line[6] += indent.substring(0, indent.length() - line[6].length());

				name = line[2].substring(0,1) + ". " + line[3]; // T. Sørensen
				name += indent.substring(0, indent.length() - name.length());

				System.out.printf("%s%s%s%s\n", line[0], name, line[6] ,line[5]);
			}

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

  public void deleteMember(String memberID){
		if(filehandler.removeRow(memberID, "db/members.tsv")){
			System.out.println("\nBruger med ID [" + memberID + "] er blevet slettet.");
		} else {
			System.out.println("\nDer blev ikke fundet en bruger med ID [" + memberID + "]. Prøv igen.");
		}
		// Vi sletter ikke fra accounting, da der stadig kan være ubetalte regninger.
		//filehandler.removeRow(memberID, "db/accounting.txt");

  }

  public Member getMember(){
    return this.temporaryMember;
  }

}
