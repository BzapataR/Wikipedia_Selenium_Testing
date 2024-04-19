import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class PageEditing{ //Using Wikipedia Sandbox to prevent any making any lasting change or prevent any banning.
    private WebDriver driver;

    @BeforeClass
    void setUpClass() throws InterruptedException {
        driver = SetUp.getDriver();
        // Additional setup specific to HomePageTesting
    }
    void BoldTextTest (String testString, WebElement targetElement){
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/form/div[2]/div[3]/div[1]/div[1]/div/div[2]/div[1]/span[1]/a")).click();
        targetElement.sendKeys(testString);
        targetElement.sendKeys(Keys.chord(Keys.COMMAND,Keys.ARROW_RIGHT));

    }
    void ItalicsTextTest (String testString, WebElement targetElement){
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/form/div[2]/div[3]/div[1]/div[1]/div/div[2]/div[1]/span[2]/a")).click();
        targetElement.sendKeys(testString);
        targetElement.sendKeys(Keys.chord(Keys.COMMAND,Keys.ARROW_RIGHT));
    }
    void ReferenceTest (String testString, WebElement targetElement){
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/form/div[2]/div[3]/div[1]/div[1]/div/div[2]/div[2]/span[5]/a")).click();
        targetElement.sendKeys(testString);
        targetElement.sendKeys(Keys.chord(Keys.COMMAND,Keys.ARROW_RIGHT));
    }
    void InsertImage() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/form/div[2]/div[3]/div[1]/div[1]/div/div[2]/div[2]/span[3]/a")).click();
        driver.findElement(By.id("wikieditor-toolbar-file-target")).sendKeys("My Cat 8-10-23.jpeg");
        driver.findElement(By.id("wikieditor-toolbar-file-caption")).sendKeys("A picture of my Cat");
        driver.findElement(By.id("wikieditor-toolbar-file-alt")).sendKeys("A picture of my Cat");
        driver.findElement(By.xpath("/html/body/div[9]/div[3]/div/button[1]")).click();
        /*
        driver.findElement(By.xpath("/html/body/div[10]/div/div[1]/div[2]/div[2]/div/div[2]/div/div[2]/form/fieldset/div/div[1]/div/div/div/label/a/input")).sendKeys("/Users/brianzapataresendiz/Downloads/1148px-Selenium_Logo.png");
        WebElement checkbox = driver.findElement(By.cssSelector("#ooui-17"));
        Thread.sleep(1000);
        checkbox.click();
        driver.findElement(By.xpath("/html/body/div[10]/div/div[1]/div[2]/div[1]/div/div[1]/span/a")).click();
        driver.findElement(By.id("ooui-22")).sendKeys("Selenium Logo");
        driver.findElement(By.xpath("/html/body/div[10]/div/div[1]/div[2]/div[1]/div/div[1]/span/a")).click();
        Code to upload a picture (not done)*/

    }
    void SignatureTest(WebElement targetElement){
        targetElement.sendKeys(Keys.chord(Keys.COMMAND,Keys.ARROW_RIGHT));
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/form/div[2]/div[3]/div[1]/div[1]/div/div[2]/div[2]/span[1]/a")).click();
    }

    @Test (priority = 17)
    void editTools()  throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));// a timer to wait until element is clickable
        driver.manage().window().maximize();
        driver.get("https://en.wikipedia.org/w/index.php?title=Wikipedia:Sandbox&action=edit&oldid=466665401&edit&summary=Clearing+sandbox&minor=yes");

        Thread.sleep(300);
        try {
            driver.findElement(By.cssSelector(".oo-ui-actionWidget > a:nth-child(1)")).click();
        }
        catch (Exception e){
            System.out.println("No pop up");
        }


        WebElement textbox = driver.findElement(By.xpath("//*[@id=\"wpTextbox1\"]"));



        Thread.sleep(1600);
        String test_string = "This page is edited with Selenium";

        textbox.sendKeys(test_string);
        Thread.sleep(1000);
        BoldTextTest(test_string, textbox);
        Thread.sleep(1000);
        ItalicsTextTest(test_string,textbox);
        Thread.sleep(1000);
        ReferenceTest(test_string,textbox);
        Thread.sleep(1000);
        InsertImage();
        Thread.sleep(1000);
        SignatureTest(textbox);
        Thread.sleep(3000);

        driver.findElement(By.id("wpPreview")).click();

        WebElement preview =driver.findElement(By.id("wikiPreview"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", preview);

        Thread.sleep(5000);
    }
    @Test (priority = 18)
    void viewChanges()  throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));// a timer to wait until element is clickable
        driver.get("https://en.wikipedia.org/w/index.php?title=Wikipedia:Sandbox&action=edit&oldid=466665401&edit&summary=Clearing+sandbox&minor=yes");
        Thread.sleep(300);
        try {
            driver.findElement(By.cssSelector(".oo-ui-actionWidget > a:nth-child(1)")).click();
        }
        catch (Exception _){
            System.out.println("popup not found");
        }
        WebElement textbox = driver.findElement(By.xpath("//*[@id=\"wpTextbox1\"]"));
        String test_string = "This page is edited with Selenium";
        Thread.sleep(300);
        textbox.sendKeys(test_string);

        WebElement viewDiff = driver.findElement(By.id("wpDiff"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewDiff);
        Thread.sleep(300);
        viewDiff.click();
        WebElement differnces = driver.findElement(By.id("wikiDiff"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", differnces);
        Thread.sleep(5000);
    }
}
