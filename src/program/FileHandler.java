package program;

import java.util.*;
import java.io.*;

public class FileHandler{

  private File file;
  private Scanner scanner;
	private FileWriter fileWriter;
	private PrintWriter printWriter;

  public FileHandler(){

  }

  public Scanner openFile(String filename){
    try {

			// Opretter et filobjekt med filnavnet.
      file = new File(filename);

			// Tjekker om vi kan læse fra filen.
			if (!file.canRead())
			{
				System.out.println("Cannot read file: " + filename);
			} else {
				// Smækker en scanner på filen, så vi kan læse linierne.
        scanner = new Scanner(file);
      }


    }
    catch (IOException e) {
      System.out.println(e);
    }

		// Returnere den scanner som er på filen, så den kan bruges af den funktion som kalder den.
    return scanner;
  }



	public void createJuniorMembers(String fileName, ArrayList<ArrayList<String>> juniorMembers){
		try {

			// Opretter writers, så vi kan skrive til filen.
			fileWriter = new FileWriter(fileName);
    	printWriter = new PrintWriter(fileWriter);

			// Gemmer nuværende dato, fra operativsystemets ur.
			Date date = new Date();

			// Gør så vi kan læse datoen, med menneskeøjne.
			String dateFormat = String.format("%1$td.%1$tm-%1$tY", date);

			// For at lave filen flot, så de står i kollonner.
			String indent = "                                         ";

			// Skriver til filen.
			printWriter.println("SK 41 JUNIOR MEDLEMMER. \nUdarbejdet d. " + dateFormat + ".\n");
    	printWriter.print("Klubben har følgende junior medlemmer: \n\n");

			for (ArrayList<String> member : juniorMembers){

					// Skriver informationerne på de brugere som er junior. (fra argumentet til funktionen)
					printWriter.printf("%s  -  %s %s\n", member.get(1), member.get(2), member.get(3));
					printWriter.printf("Adresse: %s\n", member.get(5));
					printWriter.printf("Telefon: %s\n\n", member.get(6));

			}

			// God skik at lukke en strøm.
    	printWriter.close();

		} catch (IOException e) {

				// Hvis vi af en eller anden grund ikke kan skrive til filen, printer vi operativsystemets fejlmeddelelse.
				System.out.println(e);
		}
	}

	public void writeNewTeams(String fileName, ArrayList<ArrayList<String>> teamMembers){
		try {
			Integer teamCounter = 0;

			// Så vi kan skrive til en fil.
			fileWriter = new FileWriter(fileName);
    	printWriter = new PrintWriter(fileWriter);

			// Opretter en dato ud fra styresystemets ur.
			Date date = new Date();

			// Formatere det, så et menneske kan læse.
			String dateFormat = String.format("%1$td.%1$tm-%1$tY", date);

			// Denne bruger vi til at lave flotte prints.
			String indent = "                                         ";

			// skriver til fil.
			printWriter.println("SKAK KLUB 41 NYE HOLD. \nUdarbejdet d. " + dateFormat + ".\n");
    	printWriter.print("Holdene er opsat som følgende: \n");

			for (ArrayList<String> member : teamMembers){


					if (teamCounter <= 2) {
						if (teamCounter == 0) {
							printWriter.printf("\nFØRSTE HOLDET:\n");
						}
							printWriter.printf("%s %s - %s rating.\n", member.get(2), member.get(3), member.get(8));


					} else if (teamCounter > 2 || teamCounter <= 5) {
						if (teamCounter == 3) {
							printWriter.printf("\nANDET HOLDET:\n");
						}
						printWriter.printf("%s %s - %s rating.\n", member.get(2), member.get(3), member.get(8));


					} else if (teamCounter > 5 ) {
						if (teamCounter == 6) {
							printWriter.printf("\nTREDJE HOLDET:\n");
						}
						printWriter.printf("%s %s - %s rating.\n", member.get(2), member.get(3), member.get(8));


					}
					teamCounter++;

			}

    	printWriter.close();
		} catch (IOException e) {

		}
	}

	public void writeMissedPayment(String fileName, ArrayList<ArrayList<String>> allMembers){
		try {
			int totalMissing = 0;
			fileWriter = new FileWriter(fileName);
    	printWriter = new PrintWriter(fileWriter);

			Date date = new Date();
			String dateFormat = String.format("%1$td.%1$tm-%1$tY", date);

			String indent = "                                         ";

			printWriter.println("Rapport for medlemmer i restance. \nUdarbejdet d. " + dateFormat + ".\n\n");
    	printWriter.print("Følgende medlemmer mangler at betale: \n\n");

			for (ArrayList<String> member : allMembers){

					String name = member.get(8) + " " + member.get(9);
					name += indent.substring(0, indent.length() - name.length());

					printWriter.printf("%s\t\t\t\t\t\t\t\t%s,-\n", name, member.get(4).substring(1));
					totalMissing = totalMissing + Integer.parseInt(member.get(4));
			}
			String total = "Total:";
			total += indent.substring(0, indent.length() - total.length());

			printWriter.printf("\n%s\t\t\t\t\t\t\t\t%d,-", total,Math.abs(totalMissing));
    	printWriter.close();
		} catch (IOException e) {

		}
	}

	public String nextID(){
		// Opretter en tom string variabel.
		String newID = "";

		// Får vi en scanner på den fil vi vil læse fra.
		scanner = openFile("program/config.conf");

		// Laver memberID vi har læst om til en string efter vi har plusset med en.
		int newIDplus = Integer.parseInt(scanner.nextLine())+1;

		// Plusser vores int med vores tomme streng, så det bliver til en String. [int + string -> string]
		newID += newIDplus;

		// Nu skal vi overskrive filen med det nye memberID.
		try{
			// Opretter fil objekt.
			file = new File("program/config.conf");

			// Skriver en ny fil, med filnavnet. (overskriver den gamle)
			file.createNewFile();

			// Opretter writer, så vi kan skrive til filen.
			printWriter = new PrintWriter(file);

			// Skriver den nye linje i filen.
			printWriter.println(newID);
			printWriter.flush();
			printWriter.close();

		}
		catch (IOException e)
		{
			System.out.println(e);
		}

		return newID;
	}

  public PrintWriter printFile(String filename){
    try {
        file = new File(filename);
        if (file.canWrite()) {
          fileWriter = new FileWriter(file, true);
          printWriter = new PrintWriter(fileWriter);
        }
    } catch (IOException e) {
      System.out.println(e);
    }

    return printWriter;
  }


	public boolean removeRow(String primaryKey, String tableName) {
		try
		{
			file = new File(tableName);

			if (file.canWrite())
			{
				boolean rowDeleted = false;
				RandomAccessFile raFile = new RandomAccessFile(file, "rw"); // rw = read/write
				String lineRead = "";
				String[] row;
				long fileOffset = 0;

				raFile.seek(fileOffset);

				lineRead = raFile.readLine();

				while (lineRead != null && !rowDeleted)
				{
					row = lineRead.split("\t");
					if (primaryKey.equals(row[0]))
					{
						raFile.seek(fileOffset);
						raFile.writeBytes("\\N\t"); // \N to represent removed
						raFile.close(); //important in order to flush the stream

						rowDeleted = true;
						return true;
					}
					else
					{
						fileOffset = raFile.getFilePointer(); // save previous offset to point back to start of line we just read
						lineRead = raFile.readLine();
					}
				}
			}
			else
			{
				System.out.printf("Cannot write to table: '%s'\n", tableName);
				return false;
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return false;
	}

}
