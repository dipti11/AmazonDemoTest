import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AmazonLogoTest {
    private WebDriver driver;
    private WebElement amazonLogo;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // Initialize the WebDriver
        driver = new ChromeDriver(options);

        // Open Amazon.com
        driver.get("https://www.amazon.com/");
    }

    @Test(priority = 0)
    public void testAmazonLogoVisibility() {
        // Locate the Amazon logo
        amazonLogo = driver.findElement(By.id("nav-logo-sprites"));

        // Check if the Amazon logo is displayed
        Assert.assertTrue(amazonLogo.isDisplayed(), "Amazon logo is not visible");
    }

    @Test(dependsOnMethods = "testAmazonLogoVisibility", priority = 1)
    public void testAmazonLogoFunctionality() {
        // Click the Amazon logo
        amazonLogo.click();

        // Verify that the current URL is Amazon.com homepage URL
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("https://www.amazon.com/"),"Amazon logo click does not redirect to the homepage");
    }

    @AfterClass
    public void tearDown() {
        // Close the WebDriver
        driver.quit();
    }

}
