import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class LanguageChange {

    private WebDriverWait wait;
    private WebDriver driver;
    @BeforeClass
    void setUpClass() throws InterruptedException {
        driver = SetUp.getDriver();
        // Additional setup specific to HomePageTesting
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Initialize WebDriverWait here
    }

    @Test(priority = 6)
    public void testLanguageSwitching() {
        // Navigate to the main Wikipedia page
        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        // Scroll to the bottom of the page to access the language links
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

        // Optionally wait a bit longer for the lazy-loaded elements
        try {
            Thread.sleep(2000); // Extended sleep time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Interrupted during sleep: " + e.getMessage());
        }

        // Find and click the Spanish language link
        WebElement spanishLanguageOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='autonym' and text()='Español']")));
        spanishLanguageOption.click();

        // Extend WebDriverWait duration
        WebDriverWait extendedWait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased timeout
        try {
            // Verify that the language has switched to Spanish using the new element
            WebElement headline = extendedWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='Bienvenidos_a_Wikipedia,']")));
            String headlineText = headline.getText();
            Assert.assertTrue(headlineText.contains("Bienvenidos a Wikipedia"), "The headline does not match the expected text in Spanish.");

        } catch (TimeoutException e) {
            System.out.println("Failed to find the element. Possible issue with page loading or element availability.");
            throw e; // Rethrowing to ensure test fails and logs the issue.
        }
    }
}



