package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;  // Log4j2
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Master", "Regression"})
    @Parameters({"browser"})
    public void setup(String br) throws IOException {
        // Load config.properties file
        FileReader file = new FileReader("./src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        try {
            String executionEnv = p.getProperty("execution_env").toLowerCase();

            if (executionEnv.equals("remote")) {
                DesiredCapabilities capabilities = new DesiredCapabilities();

                // Set browser
                switch (br.toLowerCase()) {
                    case "chrome":
                        capabilities.setBrowserName("chrome");
                        break;
                    case "edge":
                        capabilities.setBrowserName("MicrosoftEdge");
                        break;
                    case "firefox":
                        capabilities.setBrowserName("firefox");
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported browser: " + br);
                }

                logger.info("Connecting to Selenium Grid at: " + p.getProperty("gridURL"));
                driver = new RemoteWebDriver(new URL(p.getProperty("gridURL")), capabilities);

            } else if (executionEnv.equals("local")) {
                // Local browser setup
                switch (br.toLowerCase()) {
                    case "chrome":
                        driver = new ChromeDriver();
                        break;
                    case "edge":
                        driver = new EdgeDriver();
                        break;
                    case "firefox":
                        driver = new FirefoxDriver();
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported browser: " + br);
                }
            } else {
                throw new RuntimeException("Invalid execution environment specified: " + executionEnv);
            }

            driver.manage().deleteAllCookies();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(p.getProperty("appURL1"));
            driver.manage().window().maximize();

        } catch (Exception e) {
            logger.error("WebDriver setup failed!", e);
            throw new RuntimeException("WebDriver setup failed due to: " + e.getMessage(), e);
        }
    }

    @AfterClass(groups = {"Sanity", "Master", "Regression"})
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @SuppressWarnings("deprecation")
    public String randomString() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .filteredBy(Character::isLetter)
                .build();
        return generator.generate(5);
    }

    @SuppressWarnings("deprecation")
    public String randomNumber() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .build();
        return generator.generate(10);
    }

    @SuppressWarnings("deprecation")
    public String randomAlphaNumeric() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(Character::isLetterOrDigit)
                .build();
        return generator.generate(8);
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
}
