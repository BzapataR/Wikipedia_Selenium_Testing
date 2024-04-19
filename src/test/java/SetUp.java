import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class SetUp {
    private static WebDriver driver;
    @BeforeSuite
    void CreateDriver(){
        if (driver == null) {
            // Initialize WebDriver if not already initialized
            driver = new FirefoxDriver();
        }
    }
    private SetUp() {
        // Private constructor to prevent instantiation
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            System.out.println("Initialize WebDriver if not already initialized");
            driver = new FirefoxDriver();
        }
        return driver;
    }

    //@BeforeTest
    static void Logins() throws InterruptedException {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        Thread.sleep(300);
        WebElement login_link = driver.findElement(By.linkText("Log in"));
        login_link.click();
        driver.findElement(By.id("wpName1")).sendKeys("selTesting");
        driver.findElement(By.id("wpPassword1")).sendKeys("pybbek-zogcek-vAjqu1");
        driver.findElement(By.id("wpLoginAttempt")).click();
    }
    @AfterSuite
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
