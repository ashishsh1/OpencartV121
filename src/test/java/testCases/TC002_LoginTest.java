package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups={"Sanity","Master"})
	public void verify_login() 
	{
		
		logger.info("**** Starting TC002_LoginTest ****");

		try 
		{
			// Home Page Actions
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			logger.info("Navigated to Login Page");

			// Login Page Actions
			LoginPage lp = new LoginPage(driver);
			lp.setEmail(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
			logger.info("Submitted login form");

			// My Account Page Verification
			MyAccountPage macc = new MyAccountPage(driver);
			
			logger.info("Checking if My Account page exists");
			boolean targetPage = macc.isMyAccountPageExists();
			logger.info("Result of My Account page check: " + targetPage);
			
			//Assert.assertEquals(targetPage, true, "Login failed");
			Assert.assertTrue(targetPage, "Login failed - My Account page not found.");
			logger.info("Login successful - My Account page verified");

		} 
		catch (Exception e) 
		{
			logger.error("Login test failed due to exception: " + e.getMessage());
			Assert.fail("Exception during login test: " + e.getMessage());
		}

		logger.info("**** Finished TC002_LoginTest ****");
	}
}
