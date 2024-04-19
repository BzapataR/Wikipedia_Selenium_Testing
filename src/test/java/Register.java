import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Register {
    private String baseUrl = "https://en.wikipedia.org/wiki/Main_Page";
    private WebDriver driver;

    @BeforeClass
    void setUpClass() throws InterruptedException {
        driver = SetUp.getDriver();
        driver.get(baseUrl);
        // Additional setup specific to HomePageTesting
    }

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        // Add a 1-second delay before each action
        Thread.sleep(1000);
    }

    @Test(priority = 1)
    public void testNavigateToCreateAccountPage() {
        // Find the "Create account" link and click it
        WebElement createAccountLink = driver.findElement(By.xpath("//span[text()='Create account']"));
        createAccountLink.click();

        // Assertion to check if the Create account page is loaded
        Assert.assertTrue(driver.getCurrentUrl().contains("https://en.wikipedia.org/wiki/Special:CreateAccount"));
    }

    @Test(priority = 2)
    public void testValidRegistration() {
        // Fill in registration form
        driver.findElement(By.id("wpName2")).sendKeys("testusername");
        driver.findElement(By.id("wpPassword2")).sendKeys("testpassword");
        driver.findElement(By.id("wpRetype")).sendKeys("testpassword");
        driver.findElement(By.id("wpEmail")).sendKeys("test@example.com");

        // Click on the create account button
        driver.findElement(By.id("wpCreateaccount")).click();

        // Assertion to check successful registration
        Assert.assertTrue(driver.getCurrentUrl().contains("https://en.wikipedia.org/wiki/Special:UserLogin"));
    }

    @Test(priority = 3)
    public void testInvalidRegistration() {
        // Fill in registration form with invalid data
        driver.findElement(By.id("wpName2")).sendKeys("testusername");
        driver.findElement(By.id("wpPassword2")).sendKeys("testpassword");
        driver.findElement(By.id("wpRetype")).sendKeys("differentpassword");
        driver.findElement(By.id("wpEmail")).sendKeys("invalidemail");

        // Click on the create account button
        driver.findElement(By.id("wpCreateaccount")).click();

        // Assertion to check if error message is displayed
        Assert.assertTrue(driver.getPageSource().contains("The passwords you entered do not match"));
    }
}
