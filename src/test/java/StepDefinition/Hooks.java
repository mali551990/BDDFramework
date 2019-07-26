package StepDefinition;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import settings.TestBase;

public class Hooks
{

	@Before
	public void beforeScenario()
	{
		System.out.println("Before scenario executing");
		TestBase.launchBrowser();
	}
	
	@After
	public void afterScenario(Scenario scenario)
	{
		System.out.println("After scenario executing");
		if(scenario.isFailed())
		{
			final byte [] scrnShot=((TakesScreenshot) TestBase.driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(scrnShot, "image/png");
		}
		TestBase.closeBrowser();
	}
}
