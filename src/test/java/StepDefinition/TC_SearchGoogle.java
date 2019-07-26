package StepDefinition;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import settings.TestBase;

public class TC_SearchGoogle 
{
	public WebDriver driver=TestBase.driver;
	@Given("^Launching the Chrome Browser$")
	public void launching_the_Chrome_Browser() throws Throwable
	{
		/* System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+File.separator+"chromedriver32_Windows.exe");
		  driver = new ChromeDriver();*/
		System.out.println(" Launching the Chrome Browser");
	}

	@When("^user opens a browser$")
	public void user_opens_a_browser() throws Throwable 
	{
	    driver.get("http://www.google.com");
	}

	@When("^enters \"([^\"]*)\"$")
	public void enters(String arg1) throws Throwable 
	{
		System.out.println(" entered URL as "+arg1);
	}

	@Then("^user should be navigated to google search Page$")
	public void user_should_be_navigated_to_google_search_Page() throws Throwable 
	{
		System.out.println("launched Google Search Page ");
	}


	

}
