package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class TestGridConnection {

    public static void main(String[] args) {
        try {
            // Selenium Grid hub URL
            URL gridUrl = new URL("http://localhost:4444/wd/hub");

            // Set browser capabilities (e.g., Chrome)
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");

            // Initialize the remote driver
            WebDriver driver = new RemoteWebDriver(gridUrl, capabilities);

            // Launch test
            driver.get("https://www.google.com");
            System.out.println("Page Title: " + driver.getTitle());

            driver.quit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
