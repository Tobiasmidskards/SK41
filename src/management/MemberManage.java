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

  public boolean addMember(String[] form){

		memberWriter = filehandler.printFile("db/members.tsv");
		accountWriter = filehandler.printFile("db/accounting.tsv");

		int userID = filehandler.nextID();

		line = userID + "\t";

		for(int i = 0; i < form.length; i++){

			if(form[i].equals("")){
				return false;
			}
			if (i == 7 || i == 8 ){

			} else {
				line += form[i] + "\t";
			}
		}




		System.out.println(userID);

    memberWriter.println(line);
    memberWriter.flush();

    date = new Date();
		String dateFormat = String.format("%1$tY-%1$tm-%1$td", date);
    String debt = "0";

		String memberstatus = form[7];
		String playertype = form[8];

    if(playertype.equals("1")){
      playertype = "funPlayer";
    } else {
      playertype = "tournamentPlayer";
    }

    if(memberstatus.equals("1")){
      memberstatus = "active";
    } else {
      memberstatus = "passive";
    }

    line = userID + "\t" + memberstatus + "\t" + playertype + "\t" + dateFormat + "\t" + debt;
    accountWriter.println(line);
    accountWriter.flush();

    memberWriter.close();
    accountWriter.close();

		System.out.printf("\n%s er nu oprettet med ID: [%s]\n", form[0], userID);
		return true;
  }

  public boolean updateMember(String memberID, String field){

		Scanner scanner = new Scanner(System.in);

		memberWriter = filehandler.printFile("db/members.tsv");
		accountWriter = filehandler.printFile("db/accounting.tsv");

		scanner = filehandler.openFile("db/members.tsv");

		while(scanner.hasNextLine()){
			String[] line = scanner.nextLine().split("\\t");
			if(memberID.equals(line[0])){

				//filehandler.removeRow(memberID, "db/members.tsv")

				switch(field){
					case "1":
						line[2] = scanner.nextLine();
						break;
					case "2":
						line[3] = scanner.nextLine();
						break;
					case "3":
						line[5] = scanner.nextLine();
						break;
					case "4":
						line[4] = scanner.nextLine();
						break;
					case "5":
						line[6] = scanner.nextLine();
						break;
					case "6":
						line[7] = scanner.nextLine();
						break;
					case "7":
						break;
					case "8":
						break;
					case "9":
						line[8] = scanner.nextLine();
						break;
					case "10":
						line[9] = scanner.nextLine();
						break;
					default:
						return false;

			}

		}



		}

		return false;
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
