package StepDefinition;

import static org.junit.Assert.assertEquals;

import org.json.simple.JSONObject;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_API_putRequest 
{
	
	 RequestSpecification httpRequest;
	 Response response ;
	 
	@Given("^a base URI using put request$")
	public void a_base_URI_using_put_request() throws Throwable 
	{
		 RestAssured.baseURI= "http://localhost:3000/posts";
		 httpRequest = RestAssured.given();
	}

	@When("^I update the existing value in json file using json object$")
	public void i_update_the_existing_value_in_json_file_using_json_object() throws Throwable 
	{
		 JSONObject requestParams = new JSONObject();
		 requestParams.put("id", "5001"); // Cast
		 requestParams.put("title", "automation runner");
		 requestParams.put("author", "cucumber");
		 response=httpRequest.put("/5");
	}

	@Then("^I validate the status code is equals to (\\d+)$")
	public void i_validate_the_status_code_is_equals_to(int arg1)
	{
		int statusCode=response.getStatusCode();
		assertEquals(arg1,statusCode);
	}
}
