package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class IO_XLSX
{

	public List<String> readFile(String path)
	{
		ArrayList<String> lines = new ArrayList<String>();
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			try
			{
				while ((line = br.readLine()) != null)
					lines.add(line);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;

	}

	public boolean writeFile(List<String> lines, String path)
	{
		try
		{
			Workbook wb = new XSSFWorkbook();
			FileOutputStream fileOut = new FileOutputStream("D:\\logs\\workbook.xlsx");
			Sheet sheet = wb.createSheet("new sheet");

			// Create a row and put some cells in it. Rows are 0 based.
			Row row = sheet.createRow((short) 1);
			// Create a cell and put a value in it.
			Cell cell = row.createCell(0);
			cell.setCellValue(1);

			// Or do it on one line.
			row.createCell(1).setCellValue(1.2);
			row.createCell(2).setCellValue("This is a string");
			row.createCell(3).setCellValue(true);

			// Write the output to a file
			try
			{
				wb.write(fileOut);
				fileOut.close();
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			

			return true;

		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	
	
	
	
	public boolean writeCell(String filename, String sheetname,int rowno, int colno, String value)
	{
		try
		{
			File file=new File(filename);
			FileOutputStream fileOut;
			Workbook wb;
			Sheet sheet;
			
			if(!file.exists())
			{
				file.createNewFile();
			}
			
			fileOut = new FileOutputStream(file);
			wb = new XSSFWorkbook();
			sheet=wb.createSheet(sheetname);
				
			Row row = sheet.createRow((short) rowno);
			Cell cell = row.createCell(colno);
			cell.setCellValue(value);
			wb.write(fileOut);
			fileOut.close();
		}
			
	
		
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			

			return true;

		}
		



	public static void main(String[] args)
	{
		IO_XLSX io_xlsx = new IO_XLSX();
		//io_xlsx.writeFile(null, null);
		io_xlsx.writeCell("D:\\logs\\workbook.xlsx", "sheet1", 3, 2, "32");
	}

}
