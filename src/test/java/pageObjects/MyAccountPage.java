package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	//constructor:
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']") WebElement msgHeading;
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") WebElement lnkLogout; //added in step no 6
	
	public boolean isMyAccountPageExists() {
		try {
			boolean visible = msgHeading.isDisplayed();
			System.out.println("MyAccountPage heading visible: " + visible);
			return visible;
		} catch (Exception e) {
			System.out.println("MyAccountPage heading NOT found.");
			return false;
		}
	}
	
	public void clickLogout()
	{
		lnkLogout.click();
	}

}
