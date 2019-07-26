package settings;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase 
{
	public static WebDriver driver;
	
	public static void launchBrowser()
	{
		 
			   System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+File.separator+"chromedriver32_Windows.exe");
			   driver = new ChromeDriver();
	}

	public static void closeBrowser()
	{
		if(driver!=null)
		{
			driver.quit();
		}
	}
}
	
