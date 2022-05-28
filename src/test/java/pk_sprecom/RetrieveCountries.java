package pk_sprecom;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RetrieveCountries 
{

	@Test
	public void GetRetriveCountry() 
	{
		
		RestAssured.baseURI = "https://demo.spreecommerce.org/api/v2/storefront/countries/Ind";
		  RequestSpecification httpRequest = RestAssured.given();
		  Response response = httpRequest.get();
		  
			// Now let us print the body of the message to see what response
		  // we have recevied from the server
		  String responseBody = response.getBody().asString();
		  System.out.println("Response Body is =>  " + responseBody);
		  // Status Code Validation
		  int statusCode = response.getStatusCode();
		  System.out.println("Status code is =>  " + statusCode);
		  Assert.assertEquals(200, statusCode);
	 
		  // First get the JsonPath object instance from the Response interface
		  Assert.assertEquals(responseBody.contains("INDIA") /*Expected value*/, true /*Actual Value*/);
}

}
