package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class Common
{

public static StringBuffer sb =new StringBuffer(); 
public static FileWriter csvFile;
public static String Journey="";
public static boolean iskillSwitchToggleEnabled=false;

public static void fileWriter()
{
	try
	{
		csvFile = new FileWriter(Environment.csvPath,true) ;	
	}
	catch(Exception e)
	{
		
	}
}

public static void initializenewString()
{
	sb = new StringBuffer();
}



public static void closeCSVFile() throws Exception
{
	try {
		Common.sb.append('\n');
		Common.csvFile.write(Common.sb.toString());
	    Common.csvFile.flush();
	    Common.csvFile.close();

	} catch(Exception e)
	{
		System.out.println("Unable to Write CSV File "+e.getMessage());
	}
}

public void generateRandomString(int length)
{
 
}
 
public int generateRandomInteger(int min,int max)
{
	if (min >= max) {
		throw new IllegalArgumentException("max must be greater than min");
	}

	Random r = new Random();
	return r.nextInt((max - min) + 1) + min;

}
 
public boolean isAlpha()
{
return false;
}
 
public boolean isNumeric()
{

return false;
}
 
public boolean isAlphaNumeric()
{
return false;
}
 
public void ReadFile()
{
 
}
 
public void WriteFile()
{
 
}



public int countRecords(String object,List<String> recordset)
{
	int counter=0;
	for(String eachRecord:recordset)
	{
		if(eachRecord.equals(object))
		{
			counter++;	
		}
	}
	return counter;
}
 
 public String getTagValuefromPageSource(String pageSource,String tagName,int maxloopsize)
 {
		String s2="";
		String [] listofvalue=null;
		try
		{
			String tagValue=pageSource.substring(pageSource.indexOf(tagName));

			for(int eachSeparator=0;eachSeparator<maxloopsize;eachSeparator++)
			{
				listofvalue=tagValue.split(",");
				
				s2=s2+"|"+listofvalue[eachSeparator];
			}
			s2=s2.replaceAll("[^a-zA-Z0-9]+","");
		}
		catch(Exception e)
		{
			System.out.println(" Tag name not found "+tagName+" Java Exception "+e.getLocalizedMessage());
		}
		System.out.println(s2);
		return s2;
 }
 public String getSessionValuefromPageSource(String pageSource,String tagName,int maxloopsize)
 {
	 String s2="";
	 String [] listofvalue=null;
	 try
	 {
		 	String tagValue=pageSource.substring(pageSource.indexOf(tagName));

		 	for(int eachSeparator=0;eachSeparator<maxloopsize;eachSeparator++)
		 	{
		 		listofvalue=tagValue.split(",");
		 		s2=s2+"|"+listofvalue[eachSeparator];
		 	}
// s2=s2.replaceAll("[^a-zA-Z0-9]+","");
	 }
	 catch(Exception e)
	 {
		 System.out.println(" Session not found "+tagName+" Java Exception "+e.getLocalizedMessage());
	 }
	 System.out.println(s2);
	 return s2;
 }
 public static int   compareTimeDifference(String startDate,String endDate) throws Exception
	{
		//String startDate="27/02/2019 15:00";
		//String endDate="27/02/2019 15:23";
		int totalMinutes=0;
		long elapsed=0;
		if(startDate!=null && endDate!=null)
		{
		String startTime = startDate.split(" ")[1];
		String endTime = endDate.split(" ")[1];
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date d1 = sdf.parse(startTime);
		Date d2 = sdf.parse(endTime);
		 elapsed = d2.getTime() - d1.getTime(); 
		}
	//	System.out.println(elapsed / (60 * 1000) % 60); // this will return the time difference in minutes
		return totalMinutes=(int) (elapsed / (60 * 1000) % 60);
		
	}


public String getCurrentSysTime()
	{
	    Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
	    DateFormat dateFormat = new SimpleDateFormat("MMddyyyy HH:mm");
	    String formattedDate=dateFormat.format(date);
	    return formattedDate;
	}

}

