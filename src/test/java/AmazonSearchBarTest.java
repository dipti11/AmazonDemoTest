import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AmazonSearchBarTest {
    private WebDriver driver;

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

    @Test(priority = 1)
    public void testSearchBar() {
        // Locate the search bar and enter a search query
        WebElement searchBar = driver.findElement(By.id("twotabsearchtextbox"));
        searchBar.sendKeys("Phone");

        // Locate and click the search button
        WebElement searchButton = driver.findElement(By.id("nav-search-submit-button"));
        searchButton.click();

        // Check if the results page contains the search query
        WebElement searchResultText = driver.findElement(By.xpath("//span[contains(@class,\"a-color-base a-text-normal\")]"));
        String resultText = searchResultText.getText();
        Assert.assertTrue(resultText.contains("Phone"), "Search results page does not contain the search query");
    }

    @AfterClass
    public void tearDown() {
        // Close the WebDriver
        driver.quit();
    }

}
