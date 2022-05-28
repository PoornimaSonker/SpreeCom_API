package pk_sprecom;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Countries_Practice {

	@Test
	public void GetDefaultCountry() {

		RestAssured.baseURI = "https://demo.spreecommerce.org/api/v2/storefront/countries/default";
		RequestSpecification a = RestAssured.given();
		Response r = a.get();

		// Now let us print the body of the message to see what response
		// we have received from the server
		String responseBody = r.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);
		// Status Code Validation
		int statusCode = r.getStatusCode();
		System.out.println("Status code is =>  " + statusCode);
		//Assert.assertEquals(200, statusCode);

		// First get the JsonPath object instance from the Response interface
		Assert.assertEquals(responseBody.contains("UNITED STATES") /* Expected value */, true /* Actual Value */);
	}

}
