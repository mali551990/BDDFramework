package config;

import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.standard.MediaSize.Other;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
//import io.appium.java_client.TouchShortcuts;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import java.util.Date;
import java.util.HashMap;

public class Actionkey extends Report
{
	public static String Traffic;
	public static String objectname;
	public static String getExceptionMessage="";

	public By bylocator(String [] Object)
	{
		By by=null;
		try
		{
			switch (Object[0])
			{
			case "id":
				by=(By.id(Object[1]));
				break;
			case "xpath":
				by=(By.xpath(Object[1]));
				break;
			case "css":
				by=(By.cssSelector(Object[1]));
				break;
			case "link":
				by=(By.linkText(Object[1]));
				break;
			case "class":
				by=(By.className(Object[1]));
				break;
			case "name":
				by=(By.name(Object[1]));
				break;
			case "partial":
				by=(By.partialLinkText(Object[1]));
				break;
			case "tag":
				by=(By.tagName(Object[1]));
				break;
			default:
				break;
			}
		}catch(Exception e)
		{

		}
		return   by; 

	}

	public By  ListByLocator(String [] Object)
	{
		By by=null;

		try{
			switch (Object[0])
			{
			case "id":
				Setup.driver.findElements(By.id(Object[1]));
				break;
			case "xpath":
				Setup.driver.findElements(By.xpath(Object[1]));
				break;
			case "css":
				Setup.driver.findElements(By.cssSelector(Object[1]));
				break;
			case "link":
				Setup.driver.findElements(By.linkText(Object[1]));
				break;
			case "class":
				Setup.driver.findElements(By.className(Object[1]));
				break;
			case "name":
				Setup.driver.findElements(By.name(Object[1]));
				break;
			case "partial":
				Setup.driver.findElements(By.partialLinkText(Object[1]));
				break;
			case "tag":
				Setup.driver.findElements(By.tagName(Object[1]));
				break;
			default:
				break;
			}


		}

		catch(Exception e)
		{

		}


		return by; 

	}


	public void click(String [] object)
	{
		try
		{
			waitforObjectToLoad(object, 60);
			Setup.driver.findElement(bylocator(object)).click();
			log("INFO", "Successful in clicking the object "+object[2], false);

		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", "Error in clicking the object "+object[2], true);
		}
	}
	public void clickbyIndex(String [] object, int Index)
	{
		try
		{
			waitforObjectToLoad(object, 20);
			List<WebElement> listOfElements =Setup.driver.findElements(bylocator(object));
			listOfElements.get(Index).click();
			log("INFO", "Successful in clicking the object "+object[2], false);

		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", "Error in clicking the object "+object[2]+" using index ["+Index+"]", false);
		}
	}

	public void clickbyJS(String [] object)
	{
		WebElement element=null;
		try
		{
			JavascriptExecutor executor =null;
			element=returnWebElement(object);
			if(Setup.driver!=null)
			{
				 executor = (JavascriptExecutor)Setup.driver;

			}
			else if(Setup.appiumdriver!=null)
			{
				 executor = (JavascriptExecutor)Setup.appiumdriver;
			}
			executor.executeScript("arguments[0].click();", element);
			log("INFO", "Successful in clicking the object "+object[2], false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", "Error in clicking the object "+object[2], true);
		}
	}

	public void scrollandClick(String [] object)
	{
		int scrollinPixel=250;
		boolean objectfound=false;
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) Setup.driver;

			for(int eachHorizontalScroll=0;eachHorizontalScroll<10;eachHorizontalScroll++)
			{
				sleep(1500);
				js.executeScript("window.scrollBy(0,"+scrollinPixel+")");
				objectfound=isElementDisplayed(object);
				if(objectfound)
				{
					Setup.driver.findElement(bylocator(object)).click();
					break;
				}
			}

			if(objectfound==false)
			{
				log("FAIL", "Element is not visible to Click  "+object[2], true);
			}
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();

		}
	}


	public void waitforObjectToLoad(String [] object, int timeinSeconds)
	{
		try
		{
			WebDriverWait wait = null;
			if(Setup.driver!=null)
			{
				 wait = new WebDriverWait(Setup.driver, timeinSeconds);

			}
			else if(Setup.appiumdriver!=null)
			{
				 wait = new WebDriverWait(Setup.appiumdriver, timeinSeconds);
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(bylocator(object)));
			log("INFO", "Object [ "+object[2]+" ] has been loaded before timeout of ["+timeinSeconds+" ] in seconds", false);

		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", "Object ["+object[2]+" ] failed to load within maximum timeout of [ "+timeinSeconds+"] in Seconds", false);
		}
	}

	public void waitforObjectToDisappear(String [] object, int timeinSeconds)
	{
		boolean isElementPresent=false;
		try
		{
			for(int eachSecond=0;eachSecond<timeinSeconds;eachSecond++)
			{
				try
				{
					if(Setup.driver!=null)
					{
						Setup.driver.findElement(bylocator(object));
					}
					else if(Setup.appiumdriver!=null)
					{
						Setup.appiumdriver.findElement(bylocator(object));
					}
					Thread.sleep(1500);
				}
				catch(NoSuchElementException e)
				{
					isElementPresent=false;
					break;
				}
			}
			if(isElementPresent==false)
			{
				log("INFO", "Object [ "+object[2]+" ] disappeared , before Reaching Maximum Timeout : ["+timeinSeconds+" ]", true);

			}
			else
			{
				log("FAIL", "Object [ "+object[2]+" ] is still not disappeared , Reached Maximum Timeout : ["+timeinSeconds+" ]", true);
			}

		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", "Generic Action key Exception "+e.getMessage()+" possible cause : check parameter value", true);
		}
	}

	public void randomClick()
	{
		try
		{

		}
		catch(Exception e)
		{

		}
	}

	public void waitUntilElementisVisible(String [] object, int timeinSeconds)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(Setup.driver, timeinSeconds);
			WebElement element=Setup.driver.findElement(bylocator(object));
			wait.until(ExpectedConditions.visibilityOf(element));

			log("INFO", "Object [ "+object[2]+" ] is visible , before Reaching Maximum Timeout : ["+timeinSeconds+" ]", false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("INFO", "Object [ "+object[2]+" ] is NOT visible ,  Maximum Timeout : ["+timeinSeconds+" ] Reached", true);
		}
	}

	public void switchToFrame(String frame)
	{
		try
		{
			if(Setup.driver!=null) 
			{
				Setup.driver.switchTo().frame(frame);

			}
			else if(Setup.appiumdriver!=null)
			{
				Setup.appiumdriver.switchTo().frame(frame);
			}
			log("INFO", "Successful in switching to the frame [ "+frame+" ] ", false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", "Error in switching to the frame [ "+frame+" ] ", false);
		}
	}

	public void switchToFrame(int index)
	{
		try
		{
			if(Setup.driver!=null)
			{
				Setup.driver.switchTo().frame(index);
			}
			else if (Setup.appiumdriver!=null)
			{
				Setup.appiumdriver.switchTo().frame(index);
			}
			log("INFO", "Successful in switching to the frame index [ "+index+" ] ", false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", "Error in switching to the frame index [ "+index+" ] ", false);
		}
	}

	public void switchToWindow()
	{
		try
		{
			String parentWindow=Setup.driver.getWindowHandle();
			Set<String>windowList=Setup.driver.getWindowHandles();
			String newWindow=null;
			for (String eachWindow:windowList)
			{
				if(!eachWindow.equals(parentWindow))
				{
					Setup.driver.switchTo().window(eachWindow);
					newWindow=eachWindow;
					break;
				}
			}
			log("INFO", "Successful in switching to the new Window  ["+newWindow+ "]", false);

		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", "Error in switching to the new Window ", false);
		}
	}

	public void type(String [] object,String valuetoType)
	{
		try
		{
			
			Setup.driver.findElement(bylocator(object)).sendKeys(valuetoType);
			log("INFO", "Entered the text  ["+valuetoType+ "] in the Text Object  [ "+object[2]+"]", false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", "Error in entering the text  ["+valuetoType+ "] in the Text Object  [ "+object[2]+"]", true);
		}
	}


	public boolean isElementDisplayed(String[] object)
	{
		boolean iselementDisplayed=false;
		try
		{
			if(Setup.driver!=null)
			{
				iselementDisplayed=Setup.driver.findElement(bylocator(object)).isDisplayed();
			}
			else if(Setup.appiumdriver!=null)
			{
				iselementDisplayed=Setup.appiumdriver.findElement(bylocator(object)).isDisplayed();
			}
			log("INFO", " Element is displayed "+object[2], false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("INFO", " Element is NOT displayed "+object[2], true);
		}
		return iselementDisplayed;
	}

	
	public boolean isElementPresent(String[] object)
	{
		boolean iselementPresent=false;
		try
		{
			if(Setup.driver!=null)
			{
				iselementPresent=Setup.driver.findElement(bylocator(object))!=null;
			}
			else if(Setup.appiumdriver!=null)
			{
				iselementPresent=Setup.appiumdriver.findElement(bylocator(object))!=null;
			}
			log("INFO", " Element "+object[2]+" is displayed "+object[2], false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("INFO", " Element "+object[2]+" is NOT displayed ", true);
		}
		return iselementPresent;
	}


	public boolean isElementChecked(String[] object)
	{
		boolean isChecked=false;
		try
		{
			isChecked=Setup.driver.findElement(bylocator(object)).isSelected();
			isChecked=true;
			log("INFO", " Element is Checked/Selected  "+object[2], false);
		}
		catch(Exception e)
		{
			isChecked=false;
			log("INFO", " Element is UnChecked/Not Selected  "+object[2], true);
		}
		return isChecked;
	}


	public void selectDropdownByValue(String [] object,String value)
	{
		try
		{
			Select selectdropdown=new Select(Setup.driver.findElement(bylocator(object)));
			selectdropdown.selectByValue(value);
			log("INFO", " Dropdown [ "+object[2]+"] selected by value  ["+value+"]", false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", " Dropdown [ "+object[2]+"] not selected by value  ["+value+"]", true);
		}
	}

	public void selectDropdownByVisibleText(String [] object,String value)
	{
		try
		{
			Select selectdropdown=new Select(Setup.driver.findElement(bylocator(object)));
			selectdropdown.selectByVisibleText(value);
			log("INFO", " Dropdown [ "+object[2]+"] selected by visible Text  ["+value+"]", false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", " Dropdown [ "+object[2]+"] Failed to select by visible Text  ["+value+"]", true);
		}
	}

	public void selectDropdownByIndex(String [] object, int index)
	{
		try
		{
			Select selectdropdown=new Select(Setup.driver.findElement(bylocator(object)));
			selectdropdown.selectByIndex(index);
			log("INFO", " Dropdown [ "+object[2]+"] selected by index  ["+index+"]", false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();

			log("FAIL", " Dropdown [ "+object[2]+"] Failed to select by index  ["+index+"]", true);
		}
	}


	public void acceptAlert()
	{
		try
		{
			Alert alert=Setup.driver.switchTo().alert();
			alert.accept();
			log("INFO", " Successful in Switching to Alert ", false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", " Error in Switching to Alert ", true);
		}
	}

	public void dismissAlert()
	{
		try
		{
			Alert alert=Setup.driver.switchTo().alert();
			alert.dismiss();
			log("INFO", " Dismissed the  Alert successfully", false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", " Error in dismissing the Alert", true);
		}
	}

	public String getTextFromAlert()
	{
		String alertText="";
		try
		{
			Alert alert=Setup.driver.switchTo().alert();
			alertText=alert.getText();
			log("INFO", " Alert Text fetched Successfully " +alertText, false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", " Error in fetching the Text " +alertText, false);
		}
		return alertText;
	}

	public String getFirstSelectedOptionFromDropdown(String [] object)
	{
		String firstSelectedOption="";
		try
		{
			Select selectdropdown=new Select(Setup.driver.findElement(bylocator(object)));
			firstSelectedOption=selectdropdown.getFirstSelectedOption().getText();
			log("INFO", " Dropdown selected value has been fetched successfully " +firstSelectedOption, false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", " Unable to get the text from Dropdown " +object[2], false);
		}
		return firstSelectedOption;
	}

	public List<String> getListOfDropdownValues(String [] object)
	{
		List <String> dropdownValues= new ArrayList<String>();
		try
		{
			Select selectdropdown=new Select(Setup.driver.findElement(bylocator(object)));
			List<WebElement> allOptions = selectdropdown.getOptions();

			for(WebElement eachDropdownValue:allOptions)
			{
				dropdownValues.add(eachDropdownValue.getText());
			}
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();

		}
		return dropdownValues;
	}

	public List<String> getTextfromChildNodes()
	{
		List <String> listChildNodes= new ArrayList<String>();
		try
		{

		}
		catch(Exception e)
		{

		}
		return listChildNodes;
	}


	public String getText(String [] object)
	{
		String Text=null;
		try
		
		{
			if(Setup.driver!=null)
			{
				Text =Setup.driver.findElement(bylocator(object)).getText();
				if(Text=="")
				{
					Text =Setup.driver.findElement(bylocator(object)).getAttribute("value");
				}

			}
			else if(Setup.appiumdriver!=null)
			{
				Text =Setup.appiumdriver.findElement(bylocator(object)).getText();	
				if(Text=="")
				{
					Text =Setup.appiumdriver.findElement(bylocator(object)).getAttribute("value");
				}
			}

			log("INFO","Successfully fetched the Text value from the Text Box ["+ object[2]+"]",false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL","Error in fetching the Text value from the Text Box ["+ object[2]+"]",true);
		}
		return Text;

	}

	public String getAttribute(String [] object ,String attributename)
	{
		String Text=null;
		try
		{
			Text =Setup.driver.findElement(bylocator(object)).getAttribute(attributename);
			log("INFO", "Fetched Attribute ["+Text+"] from Object ["+object[2]+"]", false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();

			log("FAIL", "Error in Fetching Attribute from Object ["+object[2]+"]", true);

		}
		return Text;

	}
	public String getTextByIndex(String [] object,int index)
	{
		String Text=null;
		try
		{
			//Setup.driver.findElements(bylocator(object)).get(index).getText();
			log("INFO", "Fetched Text ["+Text+"] from Object ["+object[2]+"]", false);

		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("INFO", "Error in Fetching Attribute from Object ["+object[2]+"]", false);
		}
		return Text;

	}

	public void navigateToURL(String url)
	{
		try
		{
			if(Setup.driver!=null)
			{
				Setup.driver.navigate().to(url);
				Setup.driver.manage().window().maximize();
			}
			else if(Setup.appiumdriver!=null)
			{
				Setup.appiumdriver.navigate().to(url);
				Setup.appiumdriver.manage().window().maximize();

			}
			log("PASS", "Successful in Navigating to URL" +url, true);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", "Error in Navigating to URL => "+url +"Exception " +e.getMessage(), true);
		}

	}

	public void switchToParentWindow()
	{
		try
		{
			log("FAIL", "Method not implemented", true);
		}
		catch(Exception e)
		{

		}
	}

	public void verifyText()
	{
		try
		{
			log("FAIL", "Method not implemented", true);
		}
		catch(Exception e)
		{

		}
	}

	public void verifyAlertisPresent()
	{
		try
		{
			log("FAIL", "Method not implemented", true);

		}
		catch(Exception e)
		{

		}
	}

	public void verifyElementisDisabled()
	{
		try
		{
			log("FAIL", "Method not implemented", true);

		}
		catch(Exception e)
		{

		}
	}

	public void scrollDown()
	{
		try
		{
			JavascriptExecutor js=null;
			if(Setup.driver!=null)
			{
				 js = (JavascriptExecutor) Setup.driver;
			}
			else if(Setup.appiumdriver!=null)
			{
				 js = (JavascriptExecutor) Setup.appiumdriver;
			}
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();

		}
	}

	public void scrollUp()
	{
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) Setup.driver;

			js.executeScript("window.scrollBy(0,-250)", "");
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();

		}
	}
	public void scrollintoView(String [] object)
	{
		try
		{
			WebElement element =null;
			JavascriptExecutor je=null;
			if(Setup.driver!=null)
			{
				 element = Setup.driver.findElement(bylocator(object));
				 je = (JavascriptExecutor) Setup.driver;

			}
			else if(Setup.appiumdriver!=null)
			{
				 element = Setup.appiumdriver.findElement(bylocator(object));
				 je = (JavascriptExecutor) Setup.appiumdriver;
			}
			je.executeScript("arguments[0].scrollIntoView(true);",element);

		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();

		}
	}

	public WebElement returnWebElement(String [] object)
	{
		WebElement element=null;
		try
		{
			if(Setup.driver!=null)
			{
				element=Setup.driver.findElement(bylocator(object));
			}
			else if(Setup.appiumdriver!=null)
			{
				element=Setup.appiumdriver.findElement(bylocator(object));
			}
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();

		}
		return element;
	}


	public void keyBoardEvent(String buttontoClick) throws Exception
	{
		Robot robot = new Robot();

		switch (buttontoClick)
		{
		case "KeyDown":
			break;

		}
	}


	public void mouseHover(String [] object)
	{
		try
		{
			WebElement element =null;
			Actions actions = null ;
			if(Setup.driver!=null)
			{
				 actions = new Actions(Setup.driver);
				 element = Setup.driver.findElement(bylocator(object));

			}
			else if(Setup.appiumdriver!=null)
			{
				 actions = new Actions(Setup.appiumdriver);
				 element = Setup.appiumdriver.findElement(bylocator(object));

			}
			actions.moveToElement(element);
			actions.build().perform();
			log("INFO", "Successfully moved to the element  "+object[2] , false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", "Failed to move to the element  "+object[2] , false);
		}

	}


	public void doubleClick()
	{

	}

	public void rightClick()
	{

	}

	public void rightClickandSelectAnyMenu()
	{

	}

	public void dragandDrop(String source,String destination)
	{

	}

	public void sleep(Integer milliseconds)
	{
		try
		{
			Thread.sleep(milliseconds);
			log("INFO", "wait time is applied as "+milliseconds+"  milliseconds " , false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", "wait time is NOT applied due to Java Exception "+e.getMessage() , false);
		}
	}

	public void moveToElement(String [] object) 
	{
		
		try
		{
			if(Setup.driver!=null)
			{
				Actions actions = new Actions(Setup.driver);
				WebElement element = Setup.driver.findElement(bylocator(object));
				actions.moveToElement(element);
				actions.build().perform();

			}
			else if(Setup.appiumdriver!=null)
			{
				Actions actions = new Actions(Setup.appiumdriver);
				WebElement element = Setup.appiumdriver.findElement(bylocator(object));
				actions.moveToElement(element);
				actions.build().perform();
			}
			log("INFO", "Successfully moved to the element  "+object[2] , false);
		}
		catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
			log("FAIL", "Failed to move to the element  "+object[2] , false);
		}
		
		
	}


	public List<WebElement> getListofWebElement(String [] object)
	{
		List<WebElement> element=null;
		try
		{
			waitforObjectToLoad(object, 20);
			if(Setup.driver!=null) 
			{
				element=Setup.driver.findElements(bylocator(object));
			}
			else if(Setup.appiumdriver!=null) 
			{
				element=Setup.appiumdriver.findElements(bylocator(object));
			}
			
		}
		catch(Exception e)
		{

		}
		return element;
	}
	
	public String setIncedoFlag() throws IOException 
	{
		Cookie cookie=null;
		if(Setup.driver!=null)
		{
			cookie= Setup.driver.manage().getCookieNamed(constants.cookieName);  
		}
		else if(Setup.appiumdriver!=null)
		{
			cookie= Setup.appiumdriver.manage().getCookieNamed(constants.cookieName);  
		}
		String cookieVal= cookie.getValue();
		@SuppressWarnings("deprecation")
		String decodedCookieVal = URLDecoder.decode(cookieVal);
		log("INFO", "The Adobe Cookie value is  "+decodedCookieVal, false);
		Pattern pattern = Pattern.compile("MCMID\\|.*?\\|");
		Matcher matcher = pattern.matcher(decodedCookieVal);
		if (matcher.find()) {
			log("INFO", "Matched: "+ matcher.group(0), false);     
		} else {
			log("INFO", "No match.", false);
		}
		String mcmidString = matcher.group(0);
		log("INFO", "The mcmidString value is "+ mcmidString, false);    
		String mcmid = mcmidString.replaceAll("[^0-9]", "");
		log("INFO", "The mcmid value is "+ mcmid, false);    
		System.out.println("The mcmid value is "+ mcmid);
		try {
			final MessageDigest messageDigest;
			messageDigest = MessageDigest.getInstance(constants.messageDigest);
			messageDigest.reset();
			messageDigest.update(mcmid.getBytes(Charset.forName(constants.charset)));
			log("INFO", "MD5 is "+ messageDigest, false); 
			BigInteger bi = new BigInteger(1,messageDigest.digest());
			log("INFO", "Big Int is "+ bi, false); 
			String ModVal = bi.mod(new BigInteger(constants.modValue)).toString();
			log("INFO", "Mod Value is "+ ModVal, false); 
			if((Integer.parseInt(ModVal.toString()) )<50)
			{
				Traffic = "IncedoJourney";
			}
			else
			{
				Traffic = "BAUJourney";
			}
			log("INFO", "Journey is set to "+Traffic , false);
			Common.fileWriter();
			Common.initializenewString();
			Common.sb.append(mcmid);
			Common.sb.append(',');
			Common.sb.append(bi);
			Common.sb.append(',');
			Common.sb.append(ModVal);
			Common.sb.append(',');
			Common.sb.append(Traffic);
			Common.sb.append(',');
			Common.sb.append(GetCurrentTimeStamp());
			Common.sb.append(',');     
		} catch(Exception e)
		{
			getExceptionMessage=e.getLocalizedMessage();
		}
		return Traffic;

	}



		public String GetCurrentTimeStamp ()
		{
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			return timeStamp;

	    }
	
    public void scrolldownuntiltheObjectisVisible(String [] object)
    {
    
    	try
    	{
			JavascriptExecutor js = null;
			WebElement element =null;

    		if(Setup.driver!=null)
    		{
    			js=(JavascriptExecutor) Setup.driver;
    			element=Setup.driver.findElement(bylocator(object));

    		}
    		else if(Setup.appiumdriver!=null)
    		{
    			js=(JavascriptExecutor) Setup.appiumdriver;
    			element=Setup.appiumdriver.findElement(bylocator(object));
    		}
			for(int eachHorizontalScroll=0;eachHorizontalScroll<8;eachHorizontalScroll++)
			{
				if(element.isDisplayed())
				{
					break;
				}
				else
				{
					js.executeScript("window.scrollBy(0,100)", "");
				}
			}

    	}
    	catch(Exception e)
    	{
			getExceptionMessage=e.getLocalizedMessage();

    	}
    }
   
    	public String regExMatch(String ipSource, String regex)
    	{
    		
    		Pattern p = Pattern.compile(regex);
    	    Matcher m = p.matcher(ipSource);
    	    if (m.find()) {
    	      System.out.println("Matched: " + m.group(0));
    	      log("INFO","RegEx Matched for String ["+regex+"] ",false);
    	    } else 
    	    {
    	      System.out.println("No match.");
    	      log("FAIL","RegEx Matched not Matched  Regex value ["+regex+"] , source  ["+ipSource+"]",true);
    	    }
			return m.group(0);
    		
    	}
    	
    	public boolean  waitforElement_Ignore_Exceptions(String [] object, int maxTimeOut)
    	{
    		boolean iselementdisplayed=false;
    		WebDriverWait wait=null;
    		try
    		{
    			if(Setup.driver!=null)
    			{
       			 wait = new WebDriverWait(Setup.driver, maxTimeOut);

    			}
    			else if(Setup.appiumdriver!=null)
    			{
       			 wait = new WebDriverWait(Setup.appiumdriver, maxTimeOut);
    			}
    			wait.until(ExpectedConditions.presenceOfElementLocated(bylocator(object)));
    			log("INFO", "Object [ "+object[2]+" ] has been loaded before timeout of ["+maxTimeOut+" ] in seconds", false);
    			iselementdisplayed=true;
    		}
    		catch(Exception e)
    		{
    			iselementdisplayed=false;
    			getExceptionMessage=e.getLocalizedMessage();
    			log("INFO", "Object ["+object[2]+" ] failed to load within maximum timeout of [ "+maxTimeOut+"] in Seconds", false);
    		}
    		return iselementdisplayed;
    	}
    	
    	public Cookie getcookieValue(String cookieName)
    	{
    		Cookie cookie=null;
    		try
    		{
    			 cookie=Setup.driver.manage().getCookieNamed(cookieName);
    			String cookie_value=cookie.getValue();
    			log("INFO","cookie value : "+cookie_value,false);
    		}
    		catch(Exception e)
    		{
    		 getExceptionMessage=e.getLocalizedMessage();

    		}
    		return cookie;
    	}
    	
    	public void deleteCookie(Cookie cookie)
    	{
    		try
    		{
    			Setup.driver.manage().deleteCookie(cookie);
    		}
    		catch(Exception e)
    		{
       		 getExceptionMessage=e.getLocalizedMessage();

    		}
    	}
    	
    	public void createCookie(Cookie cookie,String cookieName,String cookieValue)
    	{
    		try
    		{
    			Setup.driver.manage().addCookie(new Cookie.Builder(cookieName, cookieValue)
    				    .domain(cookie.getDomain())
    				    .expiresOn(cookie.getExpiry())
    				    .path(cookie.getPath())
    				    .isSecure(cookie.isSecure())
    				    .build());
    		}
    		catch(Exception e)
    		{
       		 getExceptionMessage=e.getLocalizedMessage();
    		}
    	}

    	public void setCookie(String bucket, String env) throws Exception
    	{
    		Cookie cookie=null;
    		try
    		{
    			if(Setup.driver!=null)
    			{
        			cookie=Setup.driver.manage().getCookieNamed(constants.cookieName);
        			String cookie_value=cookie.getValue();    				
        			log("INFO","cookie value: "+cookie_value, false);
        			Setup.driver.manage().deleteCookie(cookie);
        			Setup.driver.manage().addCookie(new Cookie.Builder(constants.cookieName, getcookieValue(env, bucket))
        					.domain(cookie.getDomain())
        					.expiresOn(cookie.getExpiry())
        					.path(cookie.getPath())
        					.isSecure(cookie.isSecure())
        					.build());

    			}
    			else if(Setup.appiumdriver!=null)
    			{
        			cookie=Setup.appiumdriver.manage().getCookieNamed(constants.cookieName);
        			String cookie_value=cookie.getValue();    				
        			log("INFO","cookie value: "+cookie_value, false);
        			Setup.appiumdriver.manage().deleteCookie(cookie);
        			Setup.appiumdriver.manage().addCookie(new Cookie.Builder(constants.cookieName, getcookieValue(env, bucket))
        					.domain(cookie.getDomain())
        					.expiresOn(cookie.getExpiry())
        					.path(cookie.getPath())
        					.isSecure(cookie.isSecure())
        					.build());

    			}
    		}
    		catch(Exception e)
    		{
    			log("FAIL","exception on updating cookie "+e.getMessage(), false);
    		}
    		sleep(3000);

    	}
    	
    	
    	public void setCookie(String bucket, String env,String browser) throws Exception
    	{
    		Cookie cookie=null;
    		try
    		{
    			if(Setup.driver!=null)
    			{
        			cookie=Setup.driver.manage().getCookieNamed(constants.cookieName);
        			String cookie_value=cookie.getValue();    				
        			log("INFO","cookie value: "+cookie_value, false);
        			Setup.driver.manage().deleteCookie(cookie);
        			Setup.driver.manage().addCookie(new Cookie.Builder(constants.cookieName, getcookieValue(env, bucket,browser))
        					.domain(cookie.getDomain())
        					.expiresOn(cookie.getExpiry())
        					.path(cookie.getPath())
        					.isSecure(cookie.isSecure())
        					.build());

    			}
    			else if(Setup.appiumdriver!=null)
    			{
        			cookie=Setup.appiumdriver.manage().getCookieNamed(constants.cookieName);
        			String cookie_value=cookie.getValue();    				
        			log("INFO","cookie value: "+cookie_value, false);
        			Setup.appiumdriver.manage().deleteCookie(cookie);
        			Setup.appiumdriver.manage().addCookie(new Cookie.Builder(constants.cookieName, getcookieValue(env, bucket,browser))
        					.domain(cookie.getDomain())
        					.expiresOn(cookie.getExpiry())
        					.path(cookie.getPath())
        					.isSecure(cookie.isSecure())
        					.build());

    			}
    		}
    		catch(Exception e)
    		{
    			log("FAIL","exception on updating cookie "+e.getMessage(), false);
    		}
    		sleep(3000);

    	}
    	public void setCookieforIncedo() throws Exception
    	{
    		Cookie cookie=null;
    		try
    		{
    			cookie=Setup.driver.manage().getCookieNamed(constants.cookieName);
    			String cookie_value=cookie.getValue();
    			log("INFO","cookie value: "+cookie_value, false);
    			Setup.driver.manage().deleteCookie(cookie);
    			Setup.driver.manage().addCookie(new Cookie.Builder(constants.cookieName, constants.incedoCookieQA8)
    					.domain(cookie.getDomain())
    					.expiresOn(cookie.getExpiry())
    					.path(cookie.getPath())
    					.isSecure(cookie.isSecure())
    					.build());
    		}
    		catch(Exception e)
    		{
    			log("FAIL","exception on updating cookie "+e.getMessage(), false);
    		}
    		Thread.sleep(3000);

    	}
    	public void setCookieforBAU() throws Exception
    	{
    		Cookie cookie=null;
    		try
    		{
    			cookie=Setup.driver.manage().getCookieNamed(constants.cookieName);
    			String cookie_value=cookie.getValue();
    			log("INFO","cookie value: "+cookie_value, false);
    			Setup.driver.manage().deleteCookie(cookie);
    			Setup.driver.manage().addCookie(new Cookie.Builder(constants.cookieName, constants.bauCookieQA8)
    					.domain(cookie.getDomain())
    					.expiresOn(cookie.getExpiry())
    					.path(cookie.getPath())
    					.isSecure(cookie.isSecure())
    					.build());
    		}
    		catch(Exception e)
    		{
    			log("FAIL","exception on updating cookie "+e.getMessage(), false);
    		}
    		Thread.sleep(3000);

    	}
    	public void setCookieforHoldout() throws Exception
    	{
    		Cookie cookie=null;
    		try
    		{
    			cookie=Setup.driver.manage().getCookieNamed(constants.cookieName);
    			String cookie_value=cookie.getValue();
    			log("INFO","cookie value: "+cookie_value, false);
    			Setup.driver.manage().deleteCookie(cookie);
    			Setup.driver.manage().addCookie(new Cookie.Builder(constants.cookieName, constants.holdoutCookieQA8)
    					.domain(cookie.getDomain())
    					.expiresOn(cookie.getExpiry())
    					.path(cookie.getPath())
    					.isSecure(cookie.isSecure())
    					.build());
    		}
    		catch(Exception e)
    		{
    			log("FAIL","exception on updating cookie "+e.getMessage(), false);
    		}
    		Thread.sleep(3000);

    	}

    	public String setFlag(String experiment) throws Exception 
    	{
    		Common.Journey="";
    		int modvalue=0;
    		String Traffic="";
    		Cookie cookie=null;
    		if(Setup.driver!=null)
    		{
    			cookie= Setup.driver.manage().getCookieNamed(constants.cookieName);  

    		}
    		else if(Setup.appiumdriver!=null)
    		{
    			cookie= Setup.appiumdriver.manage().getCookieNamed(constants.cookieName);  
    		}
    		String cookieVal= cookie.getValue();
    		@SuppressWarnings("deprecation")
    		String decodedCookieVal = URLDecoder.decode(cookieVal);
    		log("INFO", "The Adobe Cookie value is "+ decodedCookieVal, false);

    		Pattern pattern = Pattern.compile("MCMID\\|.*?\\|");
    		Matcher matcher = pattern.matcher(decodedCookieVal);
    		if (matcher.find())
    		{
    			log("INFO", "Matched: " + matcher.group(0), false);
    		}
    		else 
    		{
    			log("INFO", "no Match " , false);
    		}
    		String mcmidString = matcher.group(0);
    		log("INFO", "The mcmidString value is "+ mcmidString , false);

    		String mcmid = mcmidString.replaceAll("[^0-9]", "");
    		log("INFO", "The mcmid value is "+ mcmid , false);

    		try {
    			final MessageDigest messageDigest;
    			messageDigest = MessageDigest.getInstance(constants.messageDigest);
    			messageDigest.reset();
    			messageDigest.update(mcmid.getBytes(Charset.forName(constants.charset)));

    			log("INFO", "MD5 is "+ messageDigest , false);
    			BigInteger bi = new BigInteger(1,messageDigest.digest());
    			log("INFO"," Big Int is " + bi, false);
    			String ModVal = bi.mod(new BigInteger(constants.modValue)).toString();
    			log("INFO"," Mod Value is " + ModVal, false);
		        modvalue=Integer.parseInt(ModVal.toString());

    			if(experiment.contains("exp0"))
		        {
		        	if((modvalue<=49))
					{
						Traffic = "IncedoJourney";
					}
					else 
					{
						Traffic = "BAUJourney";
					}
		        }
		        else if(experiment.contains("exp1"))
		        {	
		        	if(modvalue >=0 && modvalue<= 124)
		        	{
		        		Traffic = "ControlJourney";
		        	}
		        	
		        	else if(modvalue >=125 && modvalue<= 249) 
		        	{
		        		Traffic = "IncedoJourney";
		        	}
		        	
		        	else  if(modvalue >= 250 && modvalue <= 299) 
		        	{
		        		Traffic = "HoldoutJourney";
		        	}
		        	else if(modvalue >= 300 && modvalue <= 996) 
		        	{
		        		Traffic = "BAUJourney";
		        	}
		        	
		        }
		        else if(experiment.contains("exp2"))
		        {	
		        	if(modvalue >=0 && modvalue<= 24)
		        	{
		        		Traffic = "ControlJourney";
		        	}
		        	
		        	else if(modvalue >=25 && modvalue<= 49) 
		        	{
		        		Traffic = "IncedoJourney";
		        		//exp2 express cart
		        	}
		        	else  if(modvalue >= 50 && modvalue <= 99) 
		        	{
		        		Traffic = "HoldoutJourney";
		        	}
		        	else if(modvalue >= 100 && modvalue <= 996) 
		        	{
		        		Traffic = "BAUJourney";
		        	}
		        	
		        	
		        }
				
				log("INFO", "Journey is set to "+Traffic +"for "+experiment , false);
    			Common.fileWriter();
    			Common.initializenewString();
    			Common.sb.append(mcmid);
    			Common.sb.append(',');
    			Common.sb.append(bi);
    			Common.sb.append(',');
    			Common.sb.append(ModVal);
    			Common.sb.append(',');
    			Common.sb.append(Traffic);
    			Common.sb.append(',');
    			Common.sb.append(GetCurrentTimeStamp());
    			Common.sb.append(',');	
    			Global.oGbl.report.log("PASS", "Journey is set to " + Traffic, true);
    		} 
    		catch (Exception e) 
    		{
    			getExceptionMessage=e.getLocalizedMessage();

    		}
    		Common.Journey=Traffic;
    		return Traffic;

    	}

    	public String getcookieValue(String environment,String bucket)
    	{
    		String cookieValue="";
    		
    		switch (environment)
    		{
    		case "QA3":
    			if(bucket.contains("Incedo"))
    			{
    				cookieValue=constants.incedoCookieQA3;
    			}
    			else if(bucket.contains("BAU"))
    			{
    				cookieValue=constants.bauCookieQA3;

    			}
    			else if(bucket.contains("Holdout"))
    			{
        			log("FAIL","Hold out cookie value not implemented in Set cookie method for environment "+environment,false);
    			}
    			break;
    		case "QA8":
    			if(bucket.contains("Incedo")&&(Environment.driver.equals("CHROME")))
    			{
    				cookieValue=constants.incedoCookieQA8;
    			}
    			else if(bucket.contains("BAU")&&(Environment.driver.equals("CHROME")))
    			{
    				cookieValue=constants.bauCookieQA8;
    			}
    			else if(bucket.contains("Holdout")&&(Environment.driver.equals("CHROME")))
    			{
    				cookieValue=constants.holdoutCookieQA8;
    			}
    			else if(bucket.contains("Incedo")&&(Environment.driver.equals("CHROMEMOBILE_WEB")))
    			{
    				cookieValue=constants.incedo_m_CookieQA8;
    			}
    			else if(bucket.contains("BAU")&&(Environment.driver.equals("CHROMEMOBILE_WEB")))
    			{
    				cookieValue=constants.bau_m_CookieQA8;
    			}
    			else if(bucket.contains("Holdout")&&(Environment.driver.equals("CHROMEMOBILE_WEB")))
    			{
    				cookieValue=constants.holdout_m_CookieQA8;
    			}
    			break;
    		case "QA2":
    			if(bucket.contains("Incedo"))
    			{
    				cookieValue=constants.incedoCookieQA2;
    			}
    			else if(bucket.contains("BAU"))
    			{
    				cookieValue=constants.bauCookieQA2;
    			}
    			else if(bucket.contains("Holdout"))
    			{
    				cookieValue=constants.holdoutCookieQA2;
    			}
    		case "QA4":
    			if(bucket.contains("Incedo"))
    			{
    				cookieValue=constants.incedoCookieQA4;
    			}
    			else if(bucket.contains("BAU"))
    			{
    				cookieValue=constants.bauCookieQA4;
    			}
    			else if(bucket.contains("Holdout"))
    			{
    				cookieValue=constants.holdoutCookieQA4;
    			}
    			break;
    	
    		default:
    			log("FAIL","Environment not implemented in Set cookie method, Environment name: "+environment,false);
    			break;
    		}
    	
    	return cookieValue;
    			
    		
    		
    	}

    	
    	public String getcookieValue(String environment,String bucket,String browser)
    	{
    		String cookieValue="";
    		
    		switch (environment)
    		{
    		case "QA3":
    			if(bucket.contains("Incedo"))
    			{
    				cookieValue=constants.incedoCookieQA3;
    			}
    			else if(bucket.contains("BAU"))
    			{
    				cookieValue=constants.bauCookieQA3;

    			}
    			else if(bucket.contains("Holdout"))
    			{
        			log("FAIL","Hold out cookie value not implemented in Set cookie method for environment "+environment,false);
    			}
    			break;
    		case "QA8":
    			if(bucket.contains("Incedo")&&(browser.equals("CHROME")))
    			{
    				cookieValue=constants.incedoCookieQA8;
    			}
    			else if(bucket.contains("BAU")&&(browser.equals("CHROME")))
    			{
    				cookieValue=constants.bauCookieQA8;
    			}
    			else if(bucket.contains("Holdout")&&(browser.equals("CHROME")))
    			{
    				cookieValue=constants.holdoutCookieQA8;
    			}
    			else if(bucket.contains("Incedo")&&(browser.equals("CHROMEMOBILE_WEB")))
    			{
    				cookieValue=constants.incedo_m_CookieQA8;
    			}
    			else if(bucket.contains("BAU")&&(browser.equals("CHROMEMOBILE_WEB")))
    			{
    				cookieValue=constants.bau_m_CookieQA8;
    			}
    			else if(bucket.contains("Holdout")&&(browser.equals("CHROMEMOBILE_WEB")))
    			{
    				cookieValue=constants.holdout_m_CookieQA8;
    			}
    			break;
    		case "QA2":
    			if(bucket.contains("Incedo"))
    			{
    				cookieValue=constants.incedoCookieQA2;
    			}
    			else if(bucket.contains("BAU"))
    			{
    				cookieValue=constants.bauCookieQA2;
    			}
    			else if(bucket.contains("Holdout"))
    			{
    				cookieValue=constants.holdoutCookieQA2;
    			}
    		case "QA4":
    			if(bucket.contains("Incedo"))
    			{
    				cookieValue=constants.incedoCookieQA4;
    			}
    			else if(bucket.contains("BAU"))
    			{
    				cookieValue=constants.bauCookieQA4;
    			}
    			else if(bucket.contains("Holdout"))
    			{
    				cookieValue=constants.holdoutCookieQA4;
    			}
    			break;
    	
    		default:
    			log("FAIL","Environment not implemented in Set cookie method, Environment name: "+environment,false);
    			break;
    		}
    	
    	return cookieValue;
    			
    		
    		
    	}
    	public void mobile_tap(String [] object)
    	{
    		try
    		{
    			Setup.appiumdriver.findElement(bylocator(object)).click();
    			log("INFO", "Successfully tapped to the element  "+object[2] , false);

    		}
    		catch(StaleElementReferenceException st)
    		{
    			getExceptionMessage=st.getLocalizedMessage();
    			log("INFO", "Error in clicking on the element :Stale Element Reference "+object[2] , true);
    			try 
    			{
    			 Thread.sleep(3500);
    			} catch (InterruptedException e) 
    			{
				}
    			//Setup.appiumdriver.findElement(bylocator(object)).click();
    			log("INFO", "Successfully tapped to the element  "+object[2] , false);
    		}
    		catch(NoSuchElementException e)
    		{
    			getExceptionMessage=e.getLocalizedMessage();
    			log("FAIL", "object not found  "+object[2] +e.getMessage(), true);
    			
    		}
    		
    	}
    	public void mobile_tapbyIndex(String [] object,int index)
    	{
    		try
    		{
    			Setup.appiumdriver.findElements(bylocator(object)).get(index).click();
    			log("INFO", "Successfully tapped on the element  "+object[2] , false);

    		}
    		catch(Exception e)
    		{
    			getExceptionMessage=e.getLocalizedMessage();
    			log("FAIL", "Unable to Tap an element "+object[2] , true);
    			
    		}
    		
    	}
    	public void mobile_scrollDownUsingCoordinates(String [] object)
    	{
			WebElement element  =Setup.appiumdriver.findElement(bylocator(object));
			JavascriptExecutor js = (JavascriptExecutor) Setup.appiumdriver;
    		try
    		{
    			int x = element.getLocation().getX();
    	        int y = element.getLocation().getY();
    	        js.executeScript("window.scrollBy(" + x + "," + y + ")", "");
    			log("INFO", "Successfully moved to the element using x: "+x+"y:  "+y+" "+object[2] , false);
    		}
    		catch(Exception e)
    		{
    			getExceptionMessage=e.getLocalizedMessage();
    			 js.executeScript("arguments[0].scrollIntoView(true);",element);
    			log("INFO", "Failed in Scrolling x and Y co ordinate hence executing scroll into view JS  "+object[2] , true);
    		}
    		
    	}
    

    	public void mobile_type(String [] object, String valuetoType)
    	{
    		try
    		{
    			Setup.appiumdriver.findElement(bylocator(object)).clear();
    			Setup.appiumdriver.findElement(bylocator(object)).sendKeys(valuetoType);
    			log("INFO", " entered the text [  "+valuetoType+" ] in element  "+object[2] , false);

    		}
    		catch(Exception e)
    		{
    			getExceptionMessage=e.getLocalizedMessage();

    			log("FAIL", "Error in clicking on the element  "+object[2] , true);
    		}
    	}
    	
    	public List<WebElement> getListofMobileElement(String [] object)
    	{
    		List<WebElement> element=null;
    		try
    		{
    			waitforObjectToLoad(object, 30);
    		//TODO	
    			element=Setup.appiumdriver.findElements(bylocator(object));
    			
    		}
    		catch(Exception e)
    		{
    			getExceptionMessage=e.getLocalizedMessage();
    		}
    		return element;
    	}
    	
    	public void mobile_WaitforPageLoad(String []object,int timeOut)
    	{
    		try
    		{
    			WebDriverWait wait = new WebDriverWait(Setup.appiumdriver, timeOut);
    			wait.until(ExpectedConditions.elementToBeClickable(bylocator(object)));
    			log("INFO", "Page has been loaded Successfully before the timeout (in Seconds) "+timeOut , true);
    		}
    		catch(Exception e)
    		{
    			getExceptionMessage=e.getLocalizedMessage();
    			log("FAIL", "Page has NOT been loaded Successfully before the timeout (in Seconds)  "+timeOut , true);
    		}
    	}
    	
    	public boolean mobile_isPageDisplayed(String []object,int timeOut)
    	{
    		boolean isPagedisplayed=false;
    		try
    		{
    			WebDriverWait wait = new WebDriverWait(Setup.appiumdriver, timeOut);
    			wait.until(ExpectedConditions.elementToBeClickable(bylocator(object)));
    			log("INFO", "Page has been loaded Successfully before the timeout (in Seconds) "+timeOut , true);
    			isPagedisplayed=true;
    		}
    		catch(Exception e)
    		{
    			getExceptionMessage=e.getLocalizedMessage();
    			log("INFO", "Page has NOT been loaded Successfully before the timeout (in Seconds)  "+timeOut , true);
    		}
    		return isPagedisplayed;
    	}
    	
    	public String verifyanyOneObjectisDisplayed(String [] object1,String [] object2, int timeinSeconds)
    	{
			WebDriverWait wait1 = null,wait2=null;
			String loadedObject="";
    		for(int eachObject=0;eachObject<2;eachObject++)
    		{
	    		try
	    		{
	    			if(Setup.driver!=null)
	    			{
	    				 wait1 = new WebDriverWait(Setup.driver, timeinSeconds);
	    			}
	    			else if(Setup.appiumdriver!=null)
	    			{
	    				 wait1 = new WebDriverWait(Setup.appiumdriver, timeinSeconds);
	    			}
	    			wait1.until(ExpectedConditions.presenceOfElementLocated(bylocator(object1)));
	    			log("INFO", "Object [ "+object1[2]+" ] has been loaded before timeout of ["+timeinSeconds+" ] in seconds", false);
	    			loadedObject="object1";
	    		}
	    		catch(Exception e)
	    		{
	    			try 
	    			{
	    			  wait2 = new WebDriverWait(Setup.appiumdriver, timeinSeconds);
		    		  wait2.until(ExpectedConditions.presenceOfElementLocated(bylocator(object2)));
		    		 log("INFO", "Object [ "+object2[2]+" ] has been loaded before timeout of ["+timeinSeconds+" ] in seconds", false);
		    		loadedObject="object2";

	    			} catch(Exception e1)
	    			{
	    				loadedObject="none";
	    			}
	    		}
	    		
	    		if(loadedObject.equals("object1"))
	    		{
	    			break;
	    		}
    		}
    		
    		return loadedObject;
    	}

    	public void refresh()
    	{
    		try
    		{
    			Setup.appiumdriver.navigate().refresh();
	    		 log("INFO", "Page has been Refreshed Successfully", false);

    		}
    		catch (Exception e)
    		{
	    		 log("FAIL", "Error in Refreshing the page "+e.getLocalizedMessage(), false);

    		}
    	}
    	
    	
    	
    	public String getCurrentURL()
    	{
    		String url="";
    		try
    		{
    			url=Setup.appiumdriver.getCurrentUrl();
	    		 log("INFO", "got Current URL => "+url, false);
    		}
    		catch (Exception e)
    		{
	    		 log("FAIL", "Error in Refreshing the page "+e.getLocalizedMessage(), false);
    		}
    		
    		return url;
    	}
    	
    	
    	public int getselectedRadioButtonIndex(String [] object)
    	{
    		int getIndex=0;
    		List<WebElement> welementlist=Setup.appiumdriver.findElements(bylocator(object));
    		
    		for(WebElement eachRDO:welementlist)
    		{	
	    		try
	    		{
	    			
	    			String getattribute=eachRDO.getAttribute("checked");
	    			
	    			if(getattribute=="")
	    			{
			    	 log("INFO", "got selected RDO index as  "+getIndex, false);
	    			 break;	
	    			}
	    			
	    		}
	    		catch (Exception e)
	    		{
	    		}
    			getIndex++;
    		}
    		return getIndex;
    	}
    	
    	
    	public void waitForPageLoadtoComplete(int loadtimeout) 
    	{
    		 ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() 
    		 {
    	                    public Boolean apply(WebDriver driver) 
    	                    {
    	                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
    	                    }
    	                    
    	     };
    	      try {
    	        	
    	            Thread.sleep(1000);
    	            WebDriverWait wait = new WebDriverWait(Setup.appiumdriver, loadtimeout);
    	            wait.until(expectation);
			    	log("INFO", "PAGE LOADED SUCCESSFULLY  before reaching Timeout "+loadtimeout, false);
    	        } 
    	        catch (Throwable error) 
    	        {
			    	 log("FAIL", "PAGE NOT LOADED SUCCESSFULLY  before reaching Timeout "+error, true);
    	        }
    	    
        }
    	
    	public void mobile_tapbyignoringClickableException(String [] object,int index)
    	{
    		try
    		{
    			Setup.appiumdriver.findElements(bylocator(object)).get(index).click();
    			log("INFO", "Successfully tapped on the element  "+object[2] , false);

    		}
    		catch(Exception e)
    		{
    			scrollDown();
    			getExceptionMessage=e.getLocalizedMessage();
    			Setup.appiumdriver.findElements(bylocator(object)).get(index+1).click();
    			log("INFO", "clicked radio by index  "+(index+1)+object[2] , true);
    		}
    		
    	}
    	
    	public void navigateBack()
    	{
    		try
    		{
    			if(Setup.driver!=null)
    			{
    				Setup.driver.navigate().back();
    				Setup.driver.manage().window().maximize();
    			}
    			else if(Setup.appiumdriver!=null)
    			{
    				Setup.appiumdriver.navigate().back();

    			}
    			log("PASS", "Successful in Navigating back from current page" , true);
    		}
    		catch(Exception e)
    		{
    			getExceptionMessage=e.getLocalizedMessage();
    			log("FAIL", "Error in Navigating back , Exception " +e.getMessage(), true);
    		}

    	}


}
