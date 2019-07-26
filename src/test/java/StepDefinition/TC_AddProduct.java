package StepDefinition;

import static org.junit.Assert.assertFalse;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import settings.TestBase;

public class TC_AddProduct 
{
	public WebDriver driver=TestBase.driver;
	@Given("^user is on HomePage$")
	public void user_is_on_HomePage() throws Throwable 
	{
		   System.out.print("user is on Home page");
	}

	@When("^user clicks on Product from HomePage$")
	public void user_clicks_on_Product_from_HomePage() throws Throwable 
	{
	    driver.get("http://www.google.com");
	}

	@Then("^user should be able to see PDP$")
	public void user_should_be_able_to_see_PDP() throws Throwable 
	{
		   System.out.print("user is able to see PDP page");
	}

	@Then("^user is able to add the product to Cart$")
	public void user_is_able_to_add_the_product_to_Cart() throws Throwable
	{
		 assertFalse(true);
		System.out.print("Add the product to Cart");
	}
	

}
