import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class AmazonLanguageSelectionTest {
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
    public void testAmazonLanguageSelection() {
        // Locate the language selection button and click it
        WebElement languageButton = driver.findElement(By.id("icp-nav-flyout"));
        languageButton.click();

        // Locate the language dropdown and select a different language (e.g., Español)
        WebElement language = driver.findElement(By.xpath("//div[@id='icp-language-settings']//span[(text()='ES')]"));
        language.click();

        // Locate the save changes button and click it
        WebElement saveChangesButton = driver.findElement(By.id("icp-save-button"));
        saveChangesButton.click();

        // Verify that the language change is applied to the page (e.g., check the text of the language button)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement updatedLanguageButton = driver.findElement(By.xpath("//a[@id='icp-nav-flyout']//div[(text()='ES')]"));
        String updatedLanguageButtonText = updatedLanguageButton.getText();
        Assert.assertTrue(updatedLanguageButtonText.contains("ES"), "Language change is not applied");
    }

    @AfterClass
    public void tearDown() {
        // Close the WebDriver
        driver.quit();
    }

}
