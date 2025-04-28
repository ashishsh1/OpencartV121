package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void verify_account_registeration() {
    	
    	logger.info("*** Starting TC001_AccountRegistrationTest ***");
    	
    	try 
    	{
        HomePage hp = new HomePage(driver);
        hp.clickMyAccount();
    	logger.info("*** clicked on my account link ***");

        hp.clickRegister();
    	logger.info("*** clicked on  reg link ***");


        AccountRegisterationPage regpage = new AccountRegisterationPage(driver);
    	
    	logger.info("***Providing customer details ***");
        regpage.setFirstName(randomString().toUpperCase());
        regpage.setLastName(randomString().toUpperCase());
        regpage.setEmail(randomString() + "@gmail.com"); // unique email each run
        regpage.setTelephone(randomNumber());

        String password= randomAlphaNumeric();
        regpage.setPassword(password);
        regpage.setConfirmPassword(password);
        regpage.setPrivacyPolicy();
        regpage.clickContinue();

    	logger.info("*** validating expected message..***");
        String confmsg = regpage.getConfirmationMsg();
        Assert.assertEquals(confmsg, "Your Account Has Been Created!");
    	}
    	catch(Exception e)
    	{
        	logger.error("*** Test Failed..***");
        	//logger.debug("Debug logs..");
        	Assert.fail();
    	}
    	
    	logger.info("*** Finished..TC001_AccountRegistrationTest  ***");
    }


    
}
