package config;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class TestBase extends Setup
{
public Page globalPObject;
public static Report ExtentReport = new Report();
@BeforeTest
public void setupReport() throws Exception
{
	ExtentReport.setReportPath();
	ExtentReport.initializeReport();

}

@Parameters({"browser"}) 
@BeforeMethod
public void initializepObject(@Optional("browser")String driver) throws Exception 
{
	if(driver.equals("browser"))
	{
		intializeDriver(Environment.driver);
	}
	else
	{
		intializeDriver(driver);
	}

 intializePageObject();
}
 
public void intializePageObject()
{
globalPObject = new Page();
Actionkey.getExceptionMessage="";
}

 
@AfterMethod
public void tearDown() throws Exception
{
Common.closeCSVFile();
destroy();
ExtentReport.endTest();
}

 @AfterSuite
 public void CloseReport() throws Exception
 {
	 ExtentReport.closeReport();
	 intializeDriver("CHROMEHEADLESS");
	 if(getOSName().toLowerCase().startsWith("win"))
	 {
		 TestBase.driver.get("file:///"+Environment.reportPath+File.separator+"Report"+File.separator+"STMExtentReport.html");	 
	 }
	 else
	 {
		 TestBase.driver.get("file:////"+Environment.reportPath+File.separator+"Report"+File.separator+"STMExtentReport.html");
	 }
	 takeScreenshot(Environment.reportPath+File.separator+"Report"+File.separator+"ExtentReport"+".png");
	 TestBase.driver.findElement(By.xpath("//*[@id=\"slide-out\"]/li[2]/a")).click();
	 takeScreenshot(Environment.reportPath+File.separator+"Report"+File.separator+"ExtentDashboard"+".png");
	 TestBase.driver.quit();
	 ExtentReport.displayReport();
	 try
	 {
		 // code snippet to copy csv file to Output foler
		 File csv=new File(Environment.csvPath);
		 File OutputFolder= new File(Environment.reportPath+File.separator+"Report");
		 FileUtils.copyFileToDirectory(csv, OutputFolder);
		 // code snippet do copy TestSummaryFile to Output folder
		 File TestSummary=new File(System.getProperty("user.dir")+File.separator+"trafficSummary.txt");
		 FileUtils.copyFileToDirectory(TestSummary, OutputFolder);

	 }
	 catch(Exception e)
	 {
		 System.out.println("Error while copying CSV File and/or Traffic Summary.txt" +e.getMessage());
	 }

 }
 
 public void takeScreenshot(String imageName)
 {
		TakesScreenshot scrShot =((TakesScreenshot)Setup.driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(imageName);
		try
		{
			FileUtils.copyFile(SrcFile, DestFile);
		}
		catch(Exception e)
		{
			try {
				
				throw new Exception("Unable to copy the file from source to Destination " +e.getMessage());
			} catch (Exception e1) 
			{
				e1.getLocalizedMessage();
			}
		}
 }
 
 @BeforeSuite
 public void ArchiveMove() throws Exception
 {
	 File csv=new File(Environment.csvPath);
	 if(csv.exists())
	 {
		 csv.delete();
		 System.out.println("File "+csv+" deleted succesfully");
	 }
	 else
	 {
		 System.out.println("File "+csv+" doesnt exist to delete");
	 }
	 LocalDateTime current = LocalDateTime.now();
	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMYYYY");
	 String formattedDate = current.format(formatter);

	 LocalDateTime currentOne = LocalDateTime.now();
	 DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HHmmss");
	 String formattedtime= currentOne.format(formatter1);
	 File srcDir = new File("Output");
	 File destDir = new File("OutputArchive"+File.separator+"Report"+"_"+formattedDate+"_"+formattedtime);

	 try {

		 FileUtils.copyDirectory(srcDir, destDir);
		 FileUtils.cleanDirectory(srcDir);
	 } 
	 catch (IOException e) 
     {
			e.getLocalizedMessage();
     }

 }
 
 public String getOSName()
 {
 	String os = System.getProperty("os.name").toLowerCase();
 	System.out.println("OS name is ....................................................................." +os);
 	String OS="";
 	if(os.contains("windows"))
 	{
 		OS="WINDOWS";
 	}
 	else if(os.contains("linux"))
 	{
 		OS="LINUX";
 	}
 	else if(os.contains("mac"))
 	{
 		OS="MAC";
 	}
 	
 	 return OS;

 }

 

}