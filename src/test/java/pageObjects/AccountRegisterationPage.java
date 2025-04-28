package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterationPage extends BasePage {
	
	//1. Constructor
	public AccountRegisterationPage(WebDriver driver)
	{
		super(driver);
	}
	
	//2.Locators
@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstname;
@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastname;
@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
@FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfirmPassword;
@FindBy(xpath="//input[@name='agree']") WebElement chkdPolicy;
@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;

@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;


	//3.Actions
public void setFirstName(String fname) {
	txtFirstname.sendKeys(fname);
}

public void setLastName(String lname) {
	txtLastname.sendKeys(lname);
}
public void setEmail(String email) {
	txtEmail.sendKeys(email);
}
public void setTelephone(String tel) {
	txtTelephone.sendKeys(tel);
}
public void setPassword(String pwd) {
	txtPassword.sendKeys(pwd);
}
public void setConfirmPassword(String pwd) {
	txtConfirmPassword.sendKeys(pwd);
}
public void setPrivacyPolicy() {
	chkdPolicy.click();
}
public void clickContinue() {
	btnContinue.click();
	//so12
	//btnContinue.submit();

	//so13
	//Actions act=new Actions(driver);
	//act.moveToElement(btnContinue).click().perform();

	//so14
	//JavascriptExecutor js=(JavascriptExecutor)driver;
	//js.executeScript("arguments[0].click();", btnContinue);

	//Sol 5
	//btnContinue.sendKeys(Keys.RETURN);

	//So16
	//WebDriverWait mywait = new WebDriverWait(driver, Duration.ofSeconds(10));
	//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
}

public String getConfirmationMsg() {
	try 
	{
		return (msgConfirmation.getText());
	}
	catch(Exception e)
	{
		return(e.getMessage());
	}
}	
	
	
	
	
	
}



