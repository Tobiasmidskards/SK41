package finance;

import java.util.*;
import java.io.*;
import java.time.*;
import java.text.*;
import program.*;

import java.util.Calendar;


import java.text.DateFormat;
import java.text.SimpleDateFormat;


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
					if (line.equals("\\N")) {

					} else {
						currentMember.add(line);

					}

				}
				allMembers.add(currentMember);
			}


		}

		fileHandler.createJuniorMembers("JuniorMedlemmer(" +
					String.valueOf(dateFormat.format(date)) + ").txt", allMembers);

		System.out.println("\nInfo: Dokumentet \"JuniorMedlemmer(" +
					String.valueOf(dateFormat.format(date)) + ").txt\" er nu oprettet." );


	}

	/*public void updateAccounting(){
		fileHandler = new FileHandler();

		Scanner accountingFile = fileHandler.openFile("db/accounting.tsv");


		while(accountingFile.hasNextLine()) {
			String[] accountingLine = accountingFile.nextLine().split("\\t");

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar lastUpdateDate = Calendar.getInstance();
			Calendar nowDate = Calendar.getInstance();
			nowDate.add(Calendar.DATE, 1);
			String[] dateStrings = accountingLine[5].split("-");

			try{

				lastUpdateDate.set(Integer.parseInt(dateStrings[0]), // This is + 1 year.
											 Integer.parseInt(dateStrings[1]),
											 Integer.parseInt(dateStrings[2]));

			}	catch(Exception e){

			}

			lastUpdateDate.add(Calendar.MONTH, -1); // corrects month.

			// String formatted = sdf.format(cal.getTime());
			// String formatted1 = sdf.format(lastUpdate.getTime());

			String[] info = new String[5];

			info[0] = accountingLine[1];
			info[1] = accountingLine[2];

			Scanner memberFile = fileHandler.openFile("db/members.tsv");

			while(memberFile.hasNextLine()){
				String[] memberLine = memberFile.nextLine().split("\\t");

				if(memberLine[0].equals(accountingLine[0])){

				}

			}

			if(lastUpdateDate.compareTo(nowDate) < 0){



			}

		}

	}*/

	public void missedPayment(){

		ArrayList<ArrayList<String>> allMembers = new ArrayList<ArrayList<String>>();

		fileHandler = new FileHandler();

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
