package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	//WebDriver driver; //not required as we have already created this constructor in base page.
	
	// 1. constructor
	public HomePage(WebDriver driver) 	// from child class 'homepage' we are invoking parent class (BasePage) constructor. // concept called - Reusability
	
	{
		super(driver);										
	}
	
	//2.locators
@FindBy(xpath="//span[normalize-space()='My Account']") WebElement lnkMyaccount;

@FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkRegister;

@FindBy(xpath="//a[normalize-space()='Login']") WebElement linkLogin;  //Login link added in step 5


	//3.Actions:

public void clickMyAccount()
{
	lnkMyaccount.click();
}

public void clickRegister()
{
	lnkRegister.click();
}

public void clickLogin()
{
	linkLogin.click();
}


	

}
