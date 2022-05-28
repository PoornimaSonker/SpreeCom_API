package pk_sprecom;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class CountriesUsingExtentReportCallingUtility 
{
	Utility CTR = new Utility();
	
		//One time call before executing the method 
	@BeforeTest
	private void Creating_Report() {
		CTR.Customize_Test_Report("CountriesReport.html", "Chrome");
	}

	@AfterTest
	public void afterTest() 
	{
		CTR.extent.flush();
	}

	@AfterMethod
	private void TestResult(ITestResult result) {
		CTR.getResult(result);
	}

		@Test(priority=1)
		public void getDefaultCountry() {
			CTR.test = CTR.extent.createTest("Test Case 1", "Default Country");
			RestAssured.baseURI = "https://demo.spreecommerce.org/api/v2/storefront";
			RequestSpecification httpRequest = RestAssured.given();
			Response response = httpRequest.get("/countries/default");

			// Now let us print the body of the message to see what response
			// we have recieved from the server
			String responseBody = response.getBody().asString();
			System.out.println("Response Body is =>  " + responseBody);
			// Status Code Validation
			int statusCode = response.getStatusCode();
			System.out.println("Status code is =>  " + statusCode);
			Assert.assertEquals(200, statusCode);

			// First get the JsonPath object instance from the Response interface
			Assert.assertEquals(
					responseBody.contains("UNITED STATES") /* Expected value */,
					true /* Actual Value */, "Response body contains UNITED STATES");

		}
		
		@Test(priority=2)
		  public void getCountry() {
			CTR.test = CTR.extent.createTest("Test Case 2", "Country should display India");
			  RestAssured.baseURI = "https://demo.spreecommerce.org/api/v2/storefront";
			  RequestSpecification httpRequest = RestAssured.given();
			  Response response = httpRequest.get("/countries/ind");
			  
				// Now let us print the body of the message to see what response
			  // we have recieved from the server
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
