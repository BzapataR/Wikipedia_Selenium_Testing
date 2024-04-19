import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SearchTesting  {
    private WebDriver driver;

    @BeforeClass
    void setUpClass() throws InterruptedException {
        driver = SetUp.getDriver();
        // Additional setup specific to HomePageTesting
    }

    @Test (priority = 13)
    void checkSearchTab() throws InterruptedException {
        driver.get("https://en.wikipedia.org/wiki/Special:Search");
        driver.findElement(By.className("cdx-text-input__input")).sendKeys("Software Testing");
        driver.findElement(By.cssSelector("button.cdx-button:nth-child(2)")).click();
        Thread.sleep(2000);
    }
    @Test (priority = 14)
    void getNoResult() throws InterruptedException {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        driver.findElement(By.className("cdx-text-input__input")).sendKeys("fhjasdjkdfhkdjashfkjl");
        Thread.sleep(500);
        driver.findElement(By.cssSelector("button.cdx-button:nth-child(2)")).click();
        Thread.sleep(3000);
    }
    @Test (priority = 15)
    void advancedSearch() throws InterruptedException {
        driver.get("https://en.wikipedia.org/wiki/Special:Search");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));// a timer to wait until element is clickable
        String KeyWord = "Artificial Intelligence";
        driver.findElement(By.xpath("//*[@id=\"search\"]/div[4]/div[1]/span/a")).click();
        driver.findElement(By.id("ooui-32")).sendKeys("Artificial Intelligence AI");
        driver.findElement(By.id("ooui-40")).sendKeys("art");
        driver.findElement(By.id("ooui-48")).sendKeys("Artificial Intelligence");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"mw-search-top-table\"]/div/div/div/span/span/button")).click();
        boolean resultFound = verifyResults(KeyWord);
        Assert.assertTrue(resultFound, "No results matching Key word found");
        ((JavascriptExecutor) driver).executeScript("alert('Result found');");
        Thread.sleep(3000);
        driver.switchTo().alert().accept();//clicking the accept button
    }
    boolean verifyResults(String toMatchString){
        boolean result = false;
        WebElement Articles = driver.findElement(By.className("mw-search-results-container"));
        List<WebElement> Titles = Articles.findElements(By.xpath(".//a"));
        for (WebElement link: Titles){
            String LinkText = link.getText();
            if (LinkText.contains(toMatchString)) {
                System.out.println(LinkText);
                System.out.println(link.getAttribute("href"));
                result = true;
            }
        }
        return result;
    }
}
