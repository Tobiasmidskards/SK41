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
    	printWriter.print("FØLGENDE MEDLEMMER MANGLER AT BETALE: \n\n");
			for (ArrayList<String> member : allMembers){

					printWriter.printf("%s %s mangler at betale %skr.\n", member.get(7), member.get(8), member.get(4).substring(1));
					totalMissing = totalMissing + Integer.parseInt(member.get(4));
			}
			printWriter.printf("\n DER MANGLER I ALT AT BLIVE BETALT: %d", Math.abs(totalMissing));
    	printWriter.close();
		} catch (IOException e) {

		}
	}



}
