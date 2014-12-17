package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class IO_TXT {
	
	public List<String> readFile(String path)
	{
		ArrayList<String> lines=new ArrayList<String>();
		try {
			BufferedReader br=new BufferedReader (new FileReader(path));
			String line;
			try {
				while((line=br.readLine())!=null)
					lines.add(line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lines;
		
	}
	
	public boolean writeFile(List<String> lines,String path)
	{
		try {
			PrintWriter pw=new PrintWriter(new FileOutputStream(new File(path)));
			for(String line:lines)
			{
				pw.println(line);
			}
			pw.flush();
			pw.close();
			return true;
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	public static void main(String[] args) {

		File directory = new File("");
		System.out.println(directory.getAbsolutePath());
		System.out.println();
		IO_TXT io=new IO_TXT();
		List<String> lines=io.readFile("D:\\logs\\daily.log");
		io.writeFile(lines, "D:\\logs\\io_txt.out");
		for(String line:lines)
			System.out.println(line);
	}

}
