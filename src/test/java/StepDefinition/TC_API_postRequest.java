package StepDefinition;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.apache.commons.lang3.RandomUtils;
import org.json.simple.JSONObject;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC_API_postRequest 
{

	 RequestSpecification httpRequest;
	 Response response ;
	 
	@Given("^a Base URI$")
	public void a_Base_URI() throws Throwable
	{
		 RestAssured.baseURI= "http://restapi.demoqa.com/customer";
		 httpRequest = RestAssured.given();
	}

	@When("^I add the new entry in the json body using json object$")
	public void i_add_the_new_entry_in_the_json_body_using_json_object() throws Throwable 
	{
		 httpRequest.header("Content-Type", "application/json");
		 int randomEmail=RandomUtils.nextInt(100, 500);
		 JSONObject requestParams = new JSONObject();
		 requestParams.put("FirstName", "cxcbbx"); // Cast
		 requestParams.put("LastName", "kkkk");
		 requestParams.put("UserName", randomEmail+"GFGDHHFF");
		 requestParams.put("Password", "xcb");
		 requestParams.put("Email", randomEmail+"gdo@gmail.com");

		 httpRequest.body(requestParams.toJSONString());
		  response = httpRequest.post("/register");
	}
	

	@Then("^I validate the status code equals (\\d+)$")
	public void i_validate_the_status_code_equals(int arg1) throws Throwable
	{
		int statusCode=response.getStatusCode();
		assertEquals(arg1,statusCode);
	}

}
