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
      file = new File(filename);


			if (!file.canRead())
			{
				System.out.println("Cannot read file: " + filename);
			} else {
        scanner = new Scanner(file);
      }


    }
    catch (IOException e) {
      System.out.println(e);
    }

    return scanner;
  }

	public void writeNewTeams(String fileName, ArrayList<ArrayList<String>> teamMembers){
		try {
			Integer teamCounter = 0;
			FileWriter fileWriter = new FileWriter(fileName);
    	PrintWriter printWriter = new PrintWriter(fileWriter);

			Date date = new Date();
			String dateFormat = String.format("%1$td.%1$tm-%1$tY", date);

			String indent = "                                         ";

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
			FileWriter fileWriter = new FileWriter(fileName);
    	PrintWriter printWriter = new PrintWriter(fileWriter);

			Date date = new Date();
			String dateFormat = String.format("%1$td.%1$tm-%1$tY", date);

			String indent = "                                         ";

			printWriter.println("Rapport for medlemmer i restance. \nUdarbejdet d. " + dateFormat + ".\n\n");
    	printWriter.print("Følgende medlemmer mangler at betale: \n\n");

			for (ArrayList<String> member : allMembers){

					String name = member.get(7) + " " + member.get(8);
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
			File table = new File(tableName);

			if (table.canWrite())
			{
				boolean rowDeleted = false;
				RandomAccessFile raFile = new RandomAccessFile(table, "rw"); // rw = read/write
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
