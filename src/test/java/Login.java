import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login{
    private WebDriver driver;

    @BeforeClass
    void setUpClass() throws InterruptedException {
        driver = SetUp.getDriver();
        // Additional setup specific to HomePageTesting
    }

    private static final String BASE_URL = "https://en.wikipedia.org/wiki/Main_Page";
    private static final int ACTION_DELAY = 2000; // milliseconds

    @Test(priority = 4)
    public void testSuccessfulLogin() throws InterruptedException {
        login("Testexample123", "Testpass123");

        WebElement confirmationElement = driver.findElement(By.xpath("//span[contains(text(),'Testexample123')]"));
        Assert.assertNotNull(confirmationElement, "Login failed. Confirmation element not found.");
    }

    private void login(String username, String password) throws InterruptedException {
        driver.get(BASE_URL);
        Thread.sleep(ACTION_DELAY);
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        loginLink.click();
        Thread.sleep(ACTION_DELAY);

        WebElement usernameInput = driver.findElement(By.id("wpName1"));
        usernameInput.sendKeys(username);
        Thread.sleep(ACTION_DELAY);

        WebElement passwordInput = driver.findElement(By.id("wpPassword1"));
        passwordInput.sendKeys(password);
        Thread.sleep(ACTION_DELAY);

        WebElement loginButton = driver.findElement(By.id("wpLoginAttempt"));
        loginButton.click();
        Thread.sleep(ACTION_DELAY);
    }
}
