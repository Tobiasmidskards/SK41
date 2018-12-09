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

		// Smækker en scanner på den fil vi vil åbne.
    scanner = filehandler.openFile("db/members.tsv");

		// Læser første linje, som vi ikke skal bruge
    scanner.nextLine();

		// Bruger denne til at udskrive flot.
    String indent = "                  ";

    String[] rows = new String[]{"BrugerID","Navn","Telefon","Adresse"};

		// Vi tilpasser "brugerID", "Navn" osv, så de står flot når vi printer.
		rows[0] += indent.substring(0, 12 - rows[0].length());

    for (int s = 1; s<rows.length-1;s++) {
      rows[s] += indent.substring(0, indent.length() - rows[s].length());
    }

    System.out.println("\nViser alle brugere:\n" + rows[0] + rows[1] + rows[2] + rows[3]);

		// Priner alle members, ud fra members.tsv. (Ikke dem uden en primarykey)
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

		// Bruger disse til at skrive til fil.
		memberWriter = filehandler.printFile("db/members.tsv");
		accountWriter = filehandler.printFile("db/accounting.tsv");

		String userID = "";

		line = "";

		// Får det højeste memberID plusset med 1.
		userID = filehandler.nextID();
		line = userID + "\t";

		for(int i = 0; i < form.length; i++){

			// Hvis brugeren ikke har indtastet noget, melder vi fejl.
			if(form[i].equals("")){
				return false;
			}
			// Vi skal ikke bruge form[7] og form[8], til at skrive i members.tsv. De skal i accounting.tsv
			if (i == 7 || i == 8 ){

			} else {
				// Laver en linje med alt den information vi skal bruge i members.tsv
				line += form[i] + "\t";
			}
		}

		// Skriver den nye linje til fil.
    memberWriter.println(line);
    memberWriter.flush();

		// Nu laver vi linjen til accounting.tsv
		// Får en dato fra operativsystemets ur.
    date = new Date();

		// Formatere det til menneskesprog.
		String dateFormat = String.format("%1$tY-%1$tm-%1$td", date);

		String memberstatus = form[7];
		String playertype = form[8];


    if(form[8].equals("1")){
      form[8] = "funPlayer";
    } else {
      form[8] = "tournamentPlayer";
    }

    if(form[7].equals("1")){
      form[7] = "active";
    } else {
      form[7] = "passive";
    }

		// Laver linien.
    line = userID + "\t" + form[7] + "\t" + form[8] + "\t" + dateFormat + "\t" + "0";

		// Skriver linjen til filen.
    accountWriter.println(line);
    accountWriter.flush();

		// Lukker strømmen.
    memberWriter.close();
    accountWriter.close();

		System.out.printf("\n%s er nu oprettet med ID: [%s]\n", form[0], userID);

		// Returnere true, som en succes.
		return true;
  }

  public boolean updateMember(String memberID, String fieldNumber, String newField){

			// En string[] til at holde på informationerne til members.tsv
			String[] currentMember = new String[10];

			// En string[] til at holde på informationerne til acounting.tsv
			String[] currentMemberAccounting = new String[5];

			// En ny instans af filehandler.
			fileHandler = new FileHandler();

			// Smækker scannere på de filer vi vil læse fra.
			Scanner memberFile = fileHandler.openFile("db/members.tsv");
			Scanner accountingFile = fileHandler.openFile("db/accounting.tsv");

			// Læser første linie, som vi ikke skal bruge.
			memberFile.nextLine();
			accountingFile.nextLine();

			// Læser alle linier fra members.tsv
			while (memberFile.hasNextLine()){
				int h;

				// Læser den linie vi er på, og splitter den op, så vi får de tokens vi gerne vil have.
				String[] memberLine = memberFile.nextLine().split("\\t");

				// Finder den member, som vi søger efter.
				if (memberLine[0].equals(memberID)){

					// Læser informationen og gemmer det i vores field.
					for(h = 0; h < memberLine.length;h++){
						currentMember[h] = memberLine[h];
					}

					// Så gør vi det samme for accounting.tsv
					while (accountingFile.hasNextLine()){

						String[] accountingLine = accountingFile.nextLine().split("\\t");

						if (accountingLine[0].equals(memberID)){

							for(int j = 0; j < accountingLine.length; j++){
								currentMemberAccounting[j] = accountingLine[j];
							}

							// Når vi har samlet alt informationen, så kan vi slette den gamle information.
							fileHandler.removeRow(memberID, "db/members.tsv");
							fileHandler.removeRow(memberID, "db/accounting.tsv");

							// Nu vil vi gerne skrive de nye informationer.
							memberWriter = filehandler.printFile("db/members.tsv");

							// Ændre informationen i vores string[] til accounting, hvis det er det vi vil ændre.
							if(fieldNumber.equals("9")){
								currentMemberAccounting[1] = newField;
							} else if(fieldNumber.equals("10")){
								currentMemberAccounting[2] = newField;
							} else {
								currentMember[Integer.parseInt(fieldNumber)+1] = newField;
							}

							// Laver linien til members.tsv, som vi vil skrive.
							line = "";

							for(int i = 0; i < currentMember.length; i++){
									line += currentMember[i] + "\t";
							}

							memberWriter.println(line);
							memberWriter.flush();
							memberWriter.close();


							fileHandler = new FileHandler();

							accountWriter = filehandler.printFile("db/accounting.tsv");

							// Laver linien til accounting.tsv, som vi vil skrive.
							line = "";

							for(int j = 0; j < currentMemberAccounting.length; j++){
									line += currentMemberAccounting[j] + "\t";
							}

							accountWriter.println(line);
							accountWriter.flush();
							accountWriter.close();

							System.out.printf("\nInfo: Bruger med ID [%s] er nu blevet opdateret.\n", currentMember[0]);

							return true;
						}
					}

				}
			}



			return false;


  }

	public String oldValue(String memberID, String fieldNumber){
		ArrayList<String> currentMember = new ArrayList<String>();
		FileHandler fileHandler = new FileHandler();

		Scanner memberFile = fileHandler.openFile("db/members.tsv");
		Scanner accountingFile = fileHandler.openFile("db/accounting.tsv");

		memberFile.nextLine();
		accountingFile.nextLine();

		String retval = "";

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

						if(fieldNumber.equals("9")){
							retval = currentMember.get(11);
						} else if(fieldNumber.equals("10")){
							retval = currentMember.get(12);
						} else {
							retval = currentMember.get(Integer.parseInt(fieldNumber)+1);
						}

					}

				}
			}


		}


		return retval;
	}

  public void deleteMember(String memberID){
		if(filehandler.removeRow(memberID, "db/members.tsv")){
			System.out.println("\nBruger med ID [" + memberID + "] er blevet slettet.");
		} else {
			System.out.println("\nDer blev ikke fundet en bruger med ID [" + memberID + "]. Prøv igen.");
		}
		// Vi sletter ikke fra accounting, da der stadig kan være ubetalte regninger.

  }

  public Member getMember(){
    return this.temporaryMember;
  }

}
