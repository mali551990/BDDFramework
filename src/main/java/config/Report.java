package config;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Report 
{
private  ExtentReports extent;
private static ExtentTest logger;
public static String ReportOutputFolderPath;
public static Logger log4j;
public void setReportPath()
{
	ReportOutputFolderPath=CreateOutputFolder();
}
 
public void initializeReport()
{
PropertyConfigurator.configure("log4j.properties");
extent= new ExtentReports(ReportOutputFolderPath+File.separator+"STMExtentReport.html", false);
}
 
public void testcaseName(String testCaseDescription)
{
logger = extent.startTest(testCaseDescription);
log4j=Logger.getLogger(testCaseDescription);
}
 
public void log(String Status,String logDescription,boolean takeScreenshot)
{
 
switch (Status)
	{
	case "PASS":
	logger.log(LogStatus.PASS, logDescription,logger.addScreenCapture(takeScreenShot()));
	break;
	case "FAIL":
	logger.log(LogStatus.FAIL, logDescription,logger.addScreenCapture(takeScreenShot()));
	endTest();
	break;
	case "FAIL_CONTINUE":
	logger.log(LogStatus.FAIL, logDescription,logger.addScreenCapture(takeScreenShot()));
	break;
	case "INFO":
	//logger.log(LogStatus.INFO, logDescription);
		log4j.info(logDescription);
	break;
	case "WARNING":
	logger.log(LogStatus.WARNING, logDescription);
	break;
	default:
	try 
	{
	throw new Exception(" Invalid Status Code : "+Status);
	} catch (Exception e) {
	e.printStackTrace();
	}
	break;
}
 
}
 
void setEnvironment() throws Exception
{
 
java.net.InetAddress localMachine = java.net.InetAddress.getLocalHost();
extent.
addSystemInfo("Host Name", localMachine.getHostName())
        .addSystemInfo("Environment", "TEST")
        .addSystemInfo("User Name", System.getProperty("user.name"));

}
 
		protected String takeScreenShot()
		{
			String filepath=null;
			try
			{
			Calendar calendar = Calendar.getInstance();
			 //filepath=ReportOutputFolderPath+File.separator+calendar.getTimeInMillis()+".png"; //commented for returning relative file path
			filepath="/Output/Report/"+calendar.getTimeInMillis()+".png";
			TakesScreenshot scrShot=null;
			if(Setup.driver!=null)
			{
				 scrShot =((TakesScreenshot)Setup.driver);
			}
			else if(Setup.appiumdriver!=null)
			{
				 scrShot =((TakesScreenshot)Setup.appiumdriver);
			}
			
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile=new File(filepath);
				FileUtils.copyFile(SrcFile, DestFile);
			}
			catch(Exception e)
			{
				try {
					
					System.out.println("Unable to copy the file from source to Destination " +e.getMessage());
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
			return filepath;
		}
		
		 
		public void closeReport()
		{
		extent.close();
		}
		
		public void endTest()
		{
			extent.endTest(logger);
			extent.flush();
		}
		
		public String CreateOutputFolder()
		{
			String OutputFolderPath="";
	        LocalDateTime current = LocalDateTime.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMYYYY");
	        String formattedDate = current.format(formatter);

	        LocalDateTime currentOne = LocalDateTime.now();
	        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HHmmss");
	        String formattedtime= currentOne.format(formatter1);
	        OutputFolderPath=Environment.reportPath+File.separator+"Report";
//	        OutputFolderPath="D:\\WorkSpaceResults\\"+formattedDate+"\\"+formattedtime;
	        try
	        {	        	
	        	File file=new File(OutputFolderPath);

	        	if(!file.exists())
	        	{
					file.mkdirs();	        		
	        	}
	
	        }
	        catch(Exception e)
	        {
                System.out.println("Failed to create multiple directories!");
	        }
			return OutputFolderPath;
		}
		
		public void displayReport() throws Exception
		{
	        File file = new File(ReportOutputFolderPath+File.separator+"STMExtentReport.html");
	        Desktop desktop = Desktop.getDesktop();
	        if(file.exists())
	        {
	        	desktop.open(file);
	        }

		}
		
		
		
		
}

