package finance;

import java.util.*;
import java.io.*;
import java.time.*;
import java.text.*;
import program.*;

public class AccountManage{

	private FileHandler fileHandler;
	private ArrayList<ArrayList<String>> allMembers;
	private ArrayList<String> currentMember;

	public AccountManage(){

	}
	public void findJuniorMembers(){

		ArrayList<ArrayList<String>> allMembers = new ArrayList<ArrayList<String>>();

		FileHandler fileHandler = new FileHandler();

		Scanner memberFile = fileHandler.openFile("db/members.tsv");

		String memberColumnNames = memberFile.nextLine();

		Integer bornYear = 0;

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat yearFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();

		while (memberFile.hasNextLine()) {

			ArrayList<String> currentMember = new ArrayList<String>();
			String[] memberLine = memberFile.nextLine().split("\\t");

			bornYear = Integer.parseInt(memberLine[4].substring(4));

			if ((Integer.parseInt(yearFormat.format(date)) - bornYear) < 18){
				for (String line : memberLine) {
					currentMember.add(line);
				}
				allMembers.add(currentMember);
			}


		}

		fileHandler.createJuniorMembers("JuniorMedlemmer(" +
					String.valueOf(dateFormat.format(date)) + ").txt", allMembers);

		System.out.println("\nInfo: Dokumentet \"JuniorMedlemmer(" +
					String.valueOf(dateFormat.format(date)) + ").txt\" er nu oprettet." );


	}

	public void missedPayment(){

		ArrayList<ArrayList<String>> allMembers = new ArrayList<ArrayList<String>>();

		FileHandler fileHandler = new FileHandler();

		Scanner accountingFile = fileHandler.openFile("db/accounting.tsv");

		String accountingColumnNames = accountingFile.nextLine();


		while(accountingFile.hasNextLine()) {

				String[] accountingLine = accountingFile.nextLine().split("\\t");

				if (Integer.parseInt(accountingLine[4]) < 0) {

					Scanner memberFile = fileHandler.openFile("db/members.tsv");
					String memberColumnNames = memberFile.nextLine();
					boolean isFound = false;

					while (memberFile.hasNextLine() || !isFound) {

						ArrayList<String> currentMember = new ArrayList<String>();
						String[] memberLine = memberFile.nextLine().split("\\t");

						if (memberLine[0].equals(accountingLine[0])) {
							for (String line : accountingLine){
								currentMember.add(line);
							}
							for (String line : memberLine){
								currentMember.add(line);

							}
							allMembers.add(currentMember);
							isFound = true;
						}
					}
				}
			}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		fileHandler.writeMissedPayment("ManglendeBetalinger(" +
					String.valueOf(dateFormat.format(date)) + ").txt", allMembers);

		System.out.println("\nInfo: Dokumentet \"ManglendeBetalinger(" +
					String.valueOf(dateFormat.format(date)) + ").txt\" er nu oprettet." );
	}
}
