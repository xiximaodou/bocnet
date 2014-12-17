package init;

import tools.XMLParser;

public class SysConf
{
	
	private static String sysConfigFile=null;
	private static String tmpDir=null;
	
	public static String getSysConfigFile()
	{
		return sysConfigFile;
	}
	public static void setSysConfigFile(String sysConfigFile)
	{
		SysConf.sysConfigFile = sysConfigFile;
	}
	public  static String getTmpDir()
	{
		if(tmpDir==null&&sysConfigFile!=null)
		{
			XMLParser xmlParser = new XMLParser(sysConfigFile);
			tmpDir = xmlParser.getData("TmpDir");
		}
		return tmpDir;
	}
	public static void setTmpDir(String tmpDir)
	{
		SysConf.tmpDir = tmpDir;
	}


	
	
}
