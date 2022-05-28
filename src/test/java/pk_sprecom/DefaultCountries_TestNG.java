package pk_sprecom;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DefaultCountries_TestNG {
  @Test
  public void GetDeafultCountries() 
  
  {
  
	  RestAssured.baseURI = "https://demo.spreecommerce.org/api/v2/storefront/countries/default";
	  RequestSpecification httpRequest = RestAssured.given();
	  Response response = httpRequest.get();
	  
		// Now let us print the body of the message to see what response
	  // we have recevied from the server
	  String responseBody = response.getBody().prettyPrint();
	  System.out.println("Response Body is =>  " + responseBody);
	  // Status Code Validation
	  int statusCode = response.getStatusCode();
	  System.out.println("Status code is =>  " + statusCode);
	  Assert.assertEquals(201, statusCode);
 
	  // First get the JsonPath object instance from the Response interface
	  Assert.assertEquals(responseBody.contains("UNITED STATES") /*Expected value*/, true /*Actual Value*/);
	  
	  
  }
}
