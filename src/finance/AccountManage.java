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

	//Konstruktør
	public AccountManage(){

	}

	// Metode til at finde junor medlemmer og udskrive dem i et dokument.
	public void findJuniorMembers(){

		// ArrayList af ArrayList bruges til at samle medlemmerne i.
		ArrayList<ArrayList<String>> allMembers = new ArrayList<ArrayList<String>>();

		FileHandler fileHandler = new FileHandler();

		Scanner memberFile = fileHandler.openFile("db/members.tsv");

		// Springer første linje over da det er kolonne navnene.
		String memberColumnNames = memberFile.nextLine();

		Integer bornYear = 0;

		// Laver 2 formater som vi kan bruge med date
		// yearFormat vil returnere "yyyy"
		//dateFormat vil returnere "yyyy-MM-dd"
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat yearFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();

		// Kører så længe memberFile har en række mere-
		while (memberFile.hasNextLine()) {

			// Gemmer den enkelte medlem her som vi senere tilføjer til allMembers.
			// Da den er inden i while-løkken, vil der blive oprettet en ny instans for hver gang den køres.
			ArrayList<String> currentMember = new ArrayList<String>();

			// Deler rækken op ved hvert tab og tilføjer den til et array.
			String[] memberLine = memberFile.nextLine().split("\\t");

			// Hvis ID er lig med "\N" gør vi ingenting.
			if (memberLine[0].equals("\\N")) {

			} else {

			// Gemmer de sidste 4 tal i deres fødelsår og gemmer dem i bornYear
			// Det ville være feks. være 1995 hvis du var født 29091995.
			bornYear = Integer.parseInt(memberLine[4].substring(4));

			// Trækker fødelsår fra nuværnde år for at få alder, hvis under 18 tilføjes alle kolonner til currentMember.
			if ((Integer.parseInt(yearFormat.format(date)) - bornYear) < 18){
				for (String line : memberLine) {
					currentMember.add(line);
				}

				// Den currentMember der lige er oprettet, tilføjes til allMembers.
				allMembers.add(currentMember);
			}


		}
		}

		// Kalder metoden createJuniorMembers i fileHandler klassen.
		// Kaldes med filnavn og en ArrayListe med alle medlemmer.
		fileHandler.createJuniorMembers("JuniorMedlemmer(" +
					String.valueOf(dateFormat.format(date)) + ").txt", allMembers);

		// Printer success meddelse.
		System.out.println("\nInfo: Dokumentet \"JuniorMedlemmer(" +
					String.valueOf(dateFormat.format(date)) + ").txt\" er nu oprettet." );


	}

	// Metode til at finde alle der mangler at betale
	public void missedPayment(){

		ArrayList<ArrayList<String>> allMembers = new ArrayList<ArrayList<String>>();

		fileHandler = new FileHandler();
		Scanner accountingFile = fileHandler.openFile("db/accounting.tsv");

		// Springer første linje over da det er kolonne navnene.
		String accountingColumnNames = accountingFile.nextLine();

		// Kører så længe accountingFile har en række mere-
		while(accountingFile.hasNextLine()) {

				// Deler rækken op ved hvert tab og tilføjer den til et array.
				String[] accountingLine = accountingFile.nextLine().split("\\t");

				// Hvis ID er lig med "\N" gør vi ingenting.
				if (Integer.parseInt(accountingLine[4]) < 0) {
					if (accountingLine[0].equals("\\N")) {

					} else {

					//Vi importerer også members.tsv, da vi skal bruge informationer fra begge filer.
					Scanner memberFile = fileHandler.openFile("db/members.tsv");

					// Springer første linje over da det er kolonne navnene.
					String memberColumnNames = memberFile.nextLine();
					boolean isFound = false;

					// Kører så længe memberFile har en række mere.
					// Vi stopper med at køre loopet hvis isFound sættes til true,
					// da der ikke er grund til at køre resten igennem hvis medlemmet findes.
					while (memberFile.hasNextLine() || !isFound) {

						// Gemmer den enkelte medlem her som vi senere tilføjer til allMembers.
						// Da den er inden i while-løkken, vil der blive oprettet en ny instans for hver gang den køres.
						ArrayList<String> currentMember = new ArrayList<String>();

						// Deler rækken op ved hvert tab og tilføjer den til et array.
						String[] memberLine = memberFile.nextLine().split("\\t");

						//Hvis ID'et fra members.tsv og accounting.tsv matcher, tilføjes alle informationer til currentmember.
						if (memberLine[0].equals(accountingLine[0])) {

							// Vi tilføjer først alt fra accounting.tsv
							for (String line : accountingLine){
								currentMember.add(line);
							}
							// Derefter fra members.tsv
							for (String line : memberLine){
								currentMember.add(line);

							}
							// Her tilføjes currenMember til allMembers.
							allMembers.add(currentMember);

							// Sætes til true da vi fandt den ønskede member.
							isFound = true;
						}
					}
				}
			}
		}

		// Laver 1 format som vi kan bruge med date
		//dateFormat vil returnere "yyyy-MM-dd"
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		// Kalder metoden writeMissedPayment i fileHandler klassen.
		// Kaldes med filnavn og en ArrayListe med alle medlemmer.
		fileHandler.writeMissedPayment("ManglendeBetalinger(" +
					String.valueOf(dateFormat.format(date)) + ").txt", allMembers);

		// Printer success meddelse.
		System.out.println("\nInfo: Dokumentet \"ManglendeBetalinger(" +
					String.valueOf(dateFormat.format(date)) + ").txt\" er nu oprettet." );
	}
}
