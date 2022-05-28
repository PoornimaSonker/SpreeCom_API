package pk_sprecom;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateAddressUsingFile {
//Global Variable Declaration

	String ID;
	String outh_token;

	@BeforeTest
	public void oAuth_Token() {

		RestAssured.baseURI = "https://demo.spreecommerce.org";
		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("grant_type", "password");
		requestParams.put("username", "poornima.s@spree.com");
		requestParams.put("password", "spree123");
// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		request.body(requestParams.toJSONString());
		Response response = request.post("/spree_oauth/token");
		response.prettyPrint();
		int statusCode = response.getStatusCode();
// System.out.println(response.asString());
		Assert.assertEquals(statusCode, 200);

// Now let us print the body of the message to see what response
// we have received from the server
		String responseBody = response.getBody().asString();

// String responseBody = response.getBody().toString();
// System.out.println("Response Body is => " + responseBody);
		JsonPath jsonPathEvaluator = response.getBody().jsonPath();
		outh_token = jsonPathEvaluator.get("access_token").toString();
		System.out.println("oAuth Token is => " + outh_token);
// First get the JsonPath object instance from the Response interface
// Assert.assertEquals(responseBody.contains("Product was created with
// UI.") /*Expected value*/, true /*Actual Value*/, "Response body
// contains ");
	}

	@Test(priority=1)
	public void CreateAddress() throws IOException, ParseException {

//Create json object of JSONParser class to parse the JSON data
		JSONParser jsonparser = new JSONParser();
//Create object for FileReader class, which help to load and read JSON file
		FileReader reader = new FileReader(".\\JSON_Files\\CreateAddress.json");
//Returning/assigning to Java Object
		Object obj = jsonparser.parse(reader);
//Convert Java Object to JSON Object, JSONObject is typecast here
		JSONObject prodjsonobj = (JSONObject) obj;// for serialization add databind dependency
//BDD format
//String bearerToken = "cFWLwgbsV-mTZtiYxzkR9YvQQTcE-NvSAhK44CmyVG";
		Response response = RestAssured.given().auth().oauth2(outh_token).contentType(ContentType.JSON)
				.body(prodjsonobj).post("https://demo.spreecommerce.org/api/v2/storefront/account/addresses").then()
				.extract().response();
		response.getBody().prettyPrint();

// Now let us print the body of the message to see what response
// we have received from the server
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is => " + responseBody);
// Status Code Validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is => " + statusCode);
		Assert.assertEquals(200, statusCode);
		
		 JsonPath jsonPathEvaluator = response.getBody().jsonPath();
		 ID=jsonPathEvaluator.get("data.id").toString();
		  System.out.println("oAuth Token is =>  " + ID);

// First get the JsonPath object instance from the Response interface
		Assert.assertEquals(responseBody.contains("Markk") /* Expected value */, true /* Actual Value */,
				"Response body contains Markk");
// convert the body into lower case and then do a comparison to ignore casing.
		Assert.assertEquals(responseBody.contains("M") /* Expected value */, true /* Actual Value */,
				"Response body contains Wint");

	}

	
	@Test(priority=2)
	public void Update_Address() throws IOException, ParseException {

		  //Create json object of JSONParser class to parse the JSON data
		  JSONParser jsonparser=new JSONParser();
		  //Create object for FileReader class, which help to load and read JSON file
		  FileReader reader = new FileReader(".\\JSON_Files\\UpdateAddress.json");
		  //Returning/assigning to Java Object
		  Object obj = jsonparser.parse(reader);
		  //Convert Java Object to JSON Object, JSONObject is typecast here
		  JSONObject prodjsonobj = (JSONObject)obj;
		System.out.println("https://demo.spreecommerce.org/api/v2/storefront/account/addresses/"+ID);
		  //String bearerToken = "PUx5Gt4DZ3ncE7CugwyULGLTy6BMLCXf4XztxZ_LVQ8";
		Response response = RestAssured.given()
				.auth()
				.oauth2(outh_token)
				.contentType(ContentType.JSON)
				.body(prodjsonobj)
				.patch("https://demo.spreecommerce.org/api/v2/storefront/account/addresses/"+ID)
				.then()
				.extract()
				.response();
		response.getBody().prettyPrint();
		

		// Now let us print the body of the message to see what response
		  // we have received from the server
		  String responseBody = response.getBody().asString();
		  System.out.println("Response Body is =>  " + responseBody);
		  // Status Code Validation
		 // int statusCode = response.getStatusCode();
		 // System.out.println("Status code is =>  " + statusCode);
		 // Assert.assertEquals(200, statusCode);	

		  Map<String, String> default_billing_address = response.jsonPath().getJsonObject("data.attributes");
	        String firstName=default_billing_address.get("firstname");
	        Assert.assertEquals(firstName, "Stephen");
	}
	
}