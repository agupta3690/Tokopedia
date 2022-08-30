package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


/*
==========================================
Title:  Tokopedia | Step Definition
Author: Arun Gupta 
Date:   29 Aug 2022
==========================================
 */

public class ValidateOrderProcessAPI {


	RequestSpecification request;
	Response response;	
	String path = "/processOrder";


	@Given("User sets the API request {string}")
	public void user_sets_the_api_request_URI(String URI) {
		
		// Setting up the base URI
		RestAssured.baseURI = URI;
		ExtentCucumberAdapter.addTestStepLog("<b>" + "<font color=" + "green>" + "URI loaded" + "</font>" + "</b>");

	}

	@Given("User authenticates the API request with OAuth {string}")
	public void user_authenticates_the_api_request_with_o_auth_token(String token) {
		
		//Authenticating the request using OAuth 2 token
		request = given().auth().oauth2(token);
		ExtentCucumberAdapter.addTestStepLog("<b>" + "<font color=" + "green>" + "User authenticated" + "</font>" + "</b>");

	}

	@When("User sends the valid API request with {string}, {string}, {string}, {string}, {string}")
	public void user_sends_the_valid_api_request_with(String order_id, String order_desc, String order_status, String timestamp, String special_order) {
	
		//Creating the map object of the request payload
		Map<String, String> map = new HashMap<String, String>();
		map.put("order_id", order_id);
		map.put("order_description", order_desc);
		map.put("order_status", order_status);
		map.put("last_updated_timestamp", timestamp);
		map.put("special_order", special_order);
		JSONObject jo = new JSONObject(map);
		
		//Making the post request with the valid payload
		given().header("Content-type", "application/json").body(jo).when().post(path).thenReturn().asString();
		ExtentCucumberAdapter.addTestStepLog("<b>" + "<font color=" + "green>" + "Valid request sent to the server" + "</font>" + "</b>");

	}

	@Then("User gets the response with correct {string}, {string} and status {string}")
	public void user_gets_the_response_with_correct(String contentType, String serverType, String code) {

		//Validating the header content and the response status code
		assertThat(contentType).as("Response returned invalid content type").isEqualTo(response.getHeader("Content-Type"));
		assertThat(serverType).as("Response returned invalid server information").isEqualTo(response.getHeader("Server"));
		assertThat(code).as("Response returned incorrect status code").isEqualTo(response.getStatusCode());

	}

	@Then("User gets the response with updated {string} and {string} with success {string}")
	public void user_gets_the_response_with_updated_new_and_with(String order_status, String timestamp, String code) {
		
		//Validating the updated order status, timestamp and the status code
		JsonPath attribute = response.jsonPath();
		assertThat(order_status).as("Response returned the same object status").isNotEqualTo(attribute.getString("order_status"));
		assertThat(timestamp).as("Response returned the same timestamp").isNotEqualTo(attribute.getString("last_updated_timestamp"));
		assertThat(code).as("Response returned incorrect status code").isEqualTo(response.getStatusCode());
		
	}


	@When("User sends the API request with invalid {string} and valid {string}, {string}, {string}, {string}")
	public void user_sends_the_api_request_with_invalid_order_status(String order_id, String order_desc, String order_status, String timestamp, String special_order) {

		//Creating the map object of the request payload
		Map<String, String> map = new HashMap<String, String>();
		map.put("order_id", order_id);
		map.put("order_description", order_desc);
		map.put("order_status", order_status);
		map.put("last_updated_timestamp", timestamp);
		map.put("special_order", special_order);
		JSONObject jo = new JSONObject(map);
		
		//Making the post request with the payload having invalid order status
		given().header("Content-type", "application/json").body(jo).when().post(path).thenReturn().asString();
		ExtentCucumberAdapter.addTestStepLog("<b>" + "<font color=" + "green>" + "Request with invalid order status sent to the server" + "</font>" + "</b>");

		
	}


	@When("User sends the API request with non-existing {string} and valid {string}, {string}, {string}, {string}")
	public void user_sends_the_api_request_with_non_existing_order_id(String order_id, String order_desc, String order_status, String timestamp, String special_order) {

		//Creating the map object of the request payload
		Map<String, String> map = new HashMap<String, String>();
		map.put("order_id", order_id);
		map.put("order_description", order_desc);
		map.put("order_status", order_status);
		map.put("last_updated_timestamp", timestamp);
		map.put("special_order", special_order);
		JSONObject jo = new JSONObject(map);
		
		//Making the post request with the payload having non-existing order ID
		given().header("Content-type", "application/json").body(jo).when().post(path).thenReturn().asString();
		ExtentCucumberAdapter.addTestStepLog("<b>" + "<font color=" + "green>" + "Request with non-existing order id sent to the server" + "</font>" + "</b>");
		
	}
	
	@Then("User gets the response with proper error {string}")
	public void user_gets_the_response_with_proper_error_code(String code) {
		
		//Validating the error code received for the invalid request
		assertThat(code).as("Response returned incorrect status code").isEqualTo(response.getStatusCode());
		
	}



}
