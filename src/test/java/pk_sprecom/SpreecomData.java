package pk_sprecom;

import org.testng.annotations.DataProvider;

public class SpreecomData 
{

	@DataProvider(name="country_iso")
	public Object[][] iso_name(){
		//2X2, 2X3, 3X3 4X3 4X4
	    return new Object[][] {

	            {"usa","UNITED STATES","USA",200},
	            {"ind","INDIA","IND",200},
	            {"pak","PAKISTAN","PAK",200},
	            {"gb","UNITED KINGDOM","GBR",200}
	            };
	}
	
	
	//-------------------------------------------- This is to read Excel Data------------

		@DataProvider(name = "ISOExcelData")
		public Object[][] ReadDataFromExcel() throws Exception{
			Utility excel = new Utility();
			Object[][] testObjArray = excel.getExcelData(".\\DataFiles\\Spreecom.xlsx","RetrieveCountry");
			System.out.println(testObjArray);
			return testObjArray;

		}

	
	
	
}
