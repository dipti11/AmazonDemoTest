import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class AmazonDeliveryLocationTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        // Initialize the WebDriver
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        // Open Amazon.com
        driver.get("https://www.amazon.com/");
    }

    @Test(priority = 0)
    public void testAmazonDeleiveryLocationSelection() throws InterruptedException {
        // Click the delivery location button
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement deliveryLocationButton = driver.findElement(By.id("nav-global-location-popover-link"));
        deliveryLocationButton.click();

        // Enter the ZIP code
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement zipCodeInput = driver.findElement(By.id("GLUXZipUpdateInput"));
        zipCodeInput.sendKeys("10001");

        // Click the Apply button
        WebElement applyButton = driver.findElement(By.id("GLUXZipUpdate"));
        applyButton.click();
        WebElement submitButton = driver.findElement(By.xpath("//div[@class='a-popover-footer']//input[@id='GLUXConfirmClose']"));
        submitButton.click();

        // Verify that the delivery location has been updated
        sleep(3000); //wait untill the page is loaded
        WebElement updatedDeliveryLocation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@id,'nav-global-location')]//span[contains(@class,'nav-line-2')]")));
        String updatedDeliveryLocationText = updatedDeliveryLocation.getText();
        Assert.assertTrue(updatedDeliveryLocationText.contains("10001"), "The delivery location has not been updated");

    }

    @AfterClass
    public void tearDown() {
        // Close the WebDriver
        driver.quit();
    }

}
