package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Setup implements Environment
{
	
public Object Separator=File.separator;
public static WebDriver driver;
public static WebDriver appiumdriver;



public void intializeDriver(String drivertype) throws Exception
{

		switch(drivertype)
		{
		case "CHROME":
		
			if(getOSName().contains("WINDOWS"))
			{
				System.setProperty("webdriver.chrome.driver", getDriverPath(drivertype));
			    // driver = new ChromeDriver();
				appiumdriver= new ChromeDriver();

			}
			else if(getOSName().contains("LINUX"))
			{
				System.setProperty("webdriver.chrome.driver", getDriverPath(drivertype));
				ChromeOptions options = new ChromeOptions(); 
				options.addArguments("--headless");
		        options.addArguments("--no-sandbox");
		        options.addArguments("start-maximized");
		        options.addArguments("--disable-gpu");
		        options.addArguments("--disable-extensions");
		        options.addArguments("disable-infobars");
		        //driver = new ChromeDriver(options);
				appiumdriver= new ChromeDriver();
			}
		break;

		case "CHROMEHEADLESS":
		System.setProperty("webdriver.chrome.driver", getDriverPath(drivertype));
	    ChromeOptions options1 = new ChromeOptions();  
	    options1.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");  
		driver = new ChromeDriver(options1);
		break;
		
		case "FIREFOX":
		System.setProperty("webdriver.gecko.driver", getDriverPath(drivertype));
		driver = new FirefoxDriver();
		break;
		
		case "IE":
		File file = new File(IEDriver);
		System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
		driver = new InternetExplorerDriver();	
		break;
		     
		case "ANDROID_CHROME":
		DesiredCapabilities androidcapabilities = new DesiredCapabilities();
		
		androidcapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		androidcapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
		androidcapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		androidcapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		androidcapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
		androidcapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 90);
		androidcapabilities.setCapability("appWaitDuration"	, "50000");
		androidcapabilities.setCapability("androidDeviceReadyTimeout", "50");
		androidcapabilities.setCapability("adbExecTimeout", "30000");
		
		androidcapabilities.setCapability("autoDismissAlerts", true);
		androidcapabilities.setCapability("nativeWebScreenshot", true);

		
		//appiumdriver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), androidcapabilities);
		break;
		
		case "CHROMEMOBILE_WEB":
			ChromeOptions chromeOptions = new ChromeOptions();
			Map<String, Object> mobileEmulation = new HashMap<>();

			if(getOSName().contains("WINDOWS"))
			{
				System.setProperty("webdriver.chrome.driver", Chrome_Windows_X86);
				/*chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--window-size=1024,768");
				chromeOptions.addArguments("--no-sandbox");
				chromeOptions.addArguments("--disable-gpu");
*/				mobileEmulation.put("deviceName", "iPhone X");
				chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
			}
			else if(getOSName().contains("mac"))
			{
				System.setProperty("webdriver.chrome.driver", Chrome_mac);	
				chromeOptions.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--window-size=1024,768");
				chromeOptions.addArguments("--no-sandbox");
				chromeOptions.addArguments("--disable-gpu");
				mobileEmulation.put("deviceName", "iPhone X");
				chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
			}
			else if(getOSName().contains("LINUX"))
			{
				System.setProperty("webdriver.chrome.driver", getDriverPath(drivertype));
				chromeOptions.addArguments("--headless");
				chromeOptions.addArguments("--no-sandbox");
				chromeOptions.addArguments("start-maximized");
				chromeOptions.addArguments("--disable-gpu");
				chromeOptions.addArguments("--disable-extensions");
				chromeOptions.addArguments("disable-infobars");
				mobileEmulation.put("deviceName", "iPhone X");
				chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

			}
			appiumdriver= new ChromeDriver(chromeOptions);
			
		break;

			
		case "BROWSERSTACK":
			androidcapabilities = new DesiredCapabilities();

			androidcapabilities.setCapability("os_version", "8.0");
			androidcapabilities.setCapability("device", "Google Pixel");
			androidcapabilities.setCapability("real_mobile", "true");
			androidcapabilities.setCapability("browserstack.local", "false");
			androidcapabilities.setCapability("browserstack.debug", "true");
			androidcapabilities.setCapability("browserstack.console", "info");
			androidcapabilities.setCapability("browserstack.appium_version", "1.7.1");
			androidcapabilities.setCapability("browserstack.networkLogs", "true");

			 /*DesiredCapabilities caps = new DesiredCapabilities();
			 caps.setCapability("os_version", "12");
			 caps.setCapability("device", "iPhone 8");
			 caps.setCapability("real_mobile", "true");
			 caps.setCapability("browserstack.local", "false");
			 caps.setCapability("browserstack.debug", "true");
			 caps.setCapability("browserstack.console", "info");
			 caps.setCapability("browserstack.networkLogs", "true");
			 caps.setCapability("browserstack.appium_version", "1.9.1");
*/
			//appiumdriver = new AndroidDriver<MobileElement>(new URL("https://"+constants.bs_userName+":"+constants.bs_accessKey+"@hub-cloud.browserstack.com/wd/hub"), androidcapabilities);
			// appiumdriver = new IOSDriver<MobileElement>(new URL("https://"+constants.bs_userName+":"+constants.bs_accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);

		break;
		
		case "IOS_SAFARI":
			DesiredCapabilities ioscapabilities = new DesiredCapabilities();
			ioscapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
			ioscapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12.2");
			ioscapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone Xʀ");
			ioscapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
			ioscapabilities.setCapability(MobileCapabilityType.UDID, "2F2799EE-BD71-4970-83EF-224B5061877E");
			ioscapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
			ioscapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 100);
			ioscapabilities.setCapability("nativeWebScreenshot", true);
			
		//	appiumdriver = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), ioscapabilities);
			break;
			
		default:
		    throw new Exception ("Browser name is invalid " +drivertype);
		}
		
		if(driver!=null)
		{
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
		else if(appiumdriver!=null)
		{
			appiumdriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		}
}
 
 
public void destroy() throws Exception
{
try
{
	if(driver!=null)
	{
		driver.quit();
	}
	else if(appiumdriver!=null)
	{
		appiumdriver.quit();
	}

}
	catch(Exception e)
	{
	     	throw new RuntimeException("driver instance not Killed successfully" +e.getLocalizedMessage());
	}
}
 
 

public String getDriverPath(String driver)
{
	String os = System.getProperty("os.name").toLowerCase();
	System.out.println("OS name is ....................................................................." +os);
	String path="";
	if (os.contains("win")&&driver.contains("CHROME"))
	{
		path=Chrome_Windows_X86;
	}
	
	else if (os.contains("win")&&driver.equals("FIREFOX"))
	{
		path=GeckoDriver;
	}
	else if ((os.contains("linux") || os.contains("linux"))&&driver.contains("CHROME")) 
	{
		path = "/home/ubuntu/chromedriver";
		System.out.println("linux path to driver " +path);
	}
	else if ((os.contains("linux") || os.contains("linux"))&&driver.equals("FIREFOX")) 
	{
		path=""; // need to add driver
		setExecutablePermissionforDriver("chmod +x "+path);
	}
	else if (os.contains("mac")) 
	{
		path ="";
	}	
	return path;
}

public void setExecutablePermissionforDriver(String cmd)
{
	String s = null;

	try {

		Process p = Runtime.getRuntime().exec(cmd);

		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

		System.out.println("Here is the standard output of the command:\n");
		while ((s = stdInput.readLine()) != null) {
			System.out.println(s);
		}

		System.out.println("Here is the standard error of the command (if any):\n");
		while ((s = stdError.readLine()) != null) {
			System.out.println(s);
		}

	} catch (IOException e)
	{
		System.out.println("Exception   "+e.getMessage());
		e.printStackTrace();
		System.exit(-1);
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
