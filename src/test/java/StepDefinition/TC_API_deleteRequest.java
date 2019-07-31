package StepDefinition;

import static org.junit.Assert.assertEquals;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_API_deleteRequest 
{
	 RequestSpecification httpRequest;
	 Response response ;
	 
	@Given("^set base URI$")
	public void set_base_URI() throws Throwable 
	{
		RestAssured.baseURI= "http://localhost:3000/posts";
		 httpRequest = RestAssured.given();
	}

	@When("^I perform delete request$")
	public void i_perform_delete_request() throws Throwable
	{
		response=httpRequest.delete("/29");
	}

	@Then("^I validate the status code is equals (\\d+)$")
	public void i_validate_the_status_code_is_equals(int arg1) throws Throwable 
	{
		int statusCode=response.getStatusCode();
		assertEquals(arg1,statusCode);
	}
	
}
