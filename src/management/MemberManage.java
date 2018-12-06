package management;

import program.*;

import java.io.PrintWriter;
import java.util.*;

public class MemberManage{

	private ArrayList<ArrayList<String>> allMembers;
	private ArrayList<String> currentMember;
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

  public boolean updateMember(String memberID, String fieldNumber){

			// ArrayList<ArrayList<String>> allMembers = new ArrayList<ArrayList<String>>();
			// ArrayList<ArrayList<String>> currentMember = new ArrayList<String>();
			// FileHandler fileHandler = new FileHandler();
			//
			// Scanner memberFile = fileHandler.openFile("db/members.tsv");
			// Scanner accountingFile = fileHandler.openFile("db/accounting.tsv");
			//
			// String memberColumnNames = memberFile.nextLine();
			// String accountingColumnNames = memberFile.nextLine();

			return true;


  }

	public String oldValue(String memberID, String fieldNumber){
		ArrayList<String> currentMember = new ArrayList<String>();
		FileHandler fileHandler = new FileHandler();

		Scanner memberFile = fileHandler.openFile("db/members.tsv");
		Scanner accountingFile = fileHandler.openFile("db/accounting.tsv");

		String memberColumnNames = memberFile.nextLine();
		String accountingColumnNames = memberFile.nextLine();

		while (memberFile.hasNextLine()){

			String[] memberLine = memberFile.nextLine().split("\\t");

			if (memberLine[0].equals(memberID)){
				for (String line : memberLine){
					currentMember.add(line);

				}
				while (accountingFile.hasNextLine()){

					String[] accountingLine = accountingFile.nextLine().split("\\t");

					if (memberLine[0].equals(accountingLine[0])){
						for (String line : accountingLine) {
							currentMember.add(line);
						}

					}

				}
			}


		}
		return currentMember.get(Integer.parseInt(fieldNumber)+1);
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
