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

	public int nextID(){
		int newID = 0;
		scanner = openFile("program/config.conf");
		newID = Integer.parseInt(scanner.nextLine())+1;


		try{
			File file = new File("program/config.conf");
			file.createNewFile();
			PrintWriter printWriter = new PrintWriter(file);
			printWriter.println(newID);
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
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
