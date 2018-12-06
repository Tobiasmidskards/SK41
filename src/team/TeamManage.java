package team;

import java.util.*;
import java.io.*;
import java.time.*;
import java.text.*;
import program.*;

public class TeamManage{

	private FileHandler fileHandler;
	private ArrayList<ArrayList<String>> allMembers;
	private ArrayList<String> currentMember;
	private Integer currentRating;
	private Integer nextRating;

	public TeamManage(){

	}

	public void createNewTeams() {

		ArrayList<ArrayList<String>> allMembers = new ArrayList<ArrayList<String>>();

		FileHandler fileHandler = new FileHandler();

		Scanner memberFile = fileHandler.openFile("db/members.tsv");
		Scanner accountingFile = fileHandler.openFile("db/accounting.tsv");

		String memberColumnNames = memberFile.nextLine();
		String accountingColumnNames = accountingFile.nextLine();


		while(memberFile.hasNextLine()) {

			ArrayList<String> currentMember = new ArrayList<String>();
			String[] memberLine = memberFile.nextLine().split("\\t");
			String[] accountingLine = accountingFile.nextLine().split("\\t");

			for (String values : memberLine){
				currentMember.add(values);
			}
			for (String values : accountingLine){
				currentMember.add(values);
			}
			if (currentMember.get(12).equals("tournamentPlayer")) {
				allMembers.add(currentMember);
			}

		}

		Collections.sort(allMembers, new Comparator<List<String>> () {
			public int compare(List<String> a, List<String> b) {
	        return Integer.parseInt(b.get(8)) - Integer.parseInt(a.get(8));
	    }});

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		fileHandler.writeNewTeams("NyeHold(" +
					String.valueOf(dateFormat.format(date)) + ").txt", allMembers);

		System.out.println("\nInfo: Dokumentet \"NyeHold(" +
					String.valueOf(dateFormat.format(date)) + ").txt\" er nu oprettet." );

	}


}
