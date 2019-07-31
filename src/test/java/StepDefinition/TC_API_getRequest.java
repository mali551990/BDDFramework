package StepDefinition;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_API_getRequest 
{
	
	 RequestSpecification httpRequest;
	 Response response ;
	 
	@Given("^a base URI$")
	public void a_base_URI() throws Throwable 
	{
		 RestAssured.baseURI= "http://restapi.demoqa.com/utilities/weather/city";
		 httpRequest = RestAssured.given();
	}

	@When("^i enter the city$")
	public void i_enter_the_city() throws Throwable
	{
		 response = httpRequest.get("/Hyderabad");
	}

	@Then("^I get the body response$")
	public void i_get_the_body_response() throws Throwable 
	{
	 System.out.println("Response Body "+response.getBody().asString());
	}

	@Then("^the status code is equals (\\d+)$")
	public void the_status_code_is_equals(int arg1)
	{
		int statusCode=response.getStatusCode();
		assertEquals(arg1,statusCode);
	}
}
