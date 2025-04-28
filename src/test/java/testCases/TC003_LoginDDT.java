package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 * Data is valid - login success - test pass - logout
 * Data is valid - login failed - test fail
 * 
 * Data is Invalid - login success - test fail - logout
 * Data is Invalid - login failed  - test pass
 */
public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class,groups="DataDriven") // getting dataProvider from different class.
	public void verify_loginDDt(String email, String pwd, String exp) //exp use for validation part
	{
		logger.info("** starting TC003_LoginDDT**");
		
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();

		// Login Page Actions
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();

		// My Account Page Verification
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
		//if data is valid: we will verify 2 conditions:
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage==true)
			{
				Assert.assertTrue(true);
				macc.clickLogout();
			}
			else
			{
				Assert.assertTrue(false);
			}	
		}
		
		//if data is Invalid: we will verify 2 conditions:
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}	
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("** ending TC003_LoginDDT**");

		
		
	}
}
