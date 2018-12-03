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
