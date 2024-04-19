import org.apache.commons.logging.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Page_Creation{
    private WebDriver driver;

    @BeforeClass
    void setUpClass() throws InterruptedException {
        driver = SetUp.getDriver();
        // Additional setup specific to HomePageTesting
    }

    void ReferenceTest (WebElement targetElement){
        targetElement.sendKeys("Selenium (software) page");
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/form/div[2]/div[3]/div[1]/div[1]/div/div[2]/div[2]/span[4]/a")).click();
        targetElement.sendKeys("https://en.wikipedia.org/wiki/Selenium_(software)");
        targetElement.sendKeys(Keys.chord(Keys.COMMAND, Keys.ARROW_RIGHT));
    }
    @Test (priority = 19)
    void MakePage() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));// a timer to wait until element is clickable
        driver.manage().window().maximize();
        driver.get("https://en.wikipedia.org/wiki/Wikipedia:Article_wizard");
        //Next Button
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/div[1]/div/table/tbody/tr[2]/td[2]/div[2]/div/span/a")).click();
        //The next Button for readability and notability
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/div[1]/div/table/tbody/tr[2]/td[2]/div/p/span/a")).click();
        //clicking the, "I'm not connected to the subject" button
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/div[1]/div/table/tbody/tr[2]/td[2]/div/div[3]/span/a")).click();
        //Entering the name of the page
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/main/div[3]/div[3]/div[1]/div/table/tbody/tr[2]/td[2]/div[1]/form/input[6]")).sendKeys("Selenium Playgrounds");
        //Creating new article draft
        Thread.sleep(300);
        driver.findElement(By.cssSelector(".mw-ui-button")).click();
        //Clicking out of the alert
        try {
            driver.findElement(By.cssSelector("span.oo-ui-flaggedElement-progressive:nth-child(2) > a:nth-child(1)")).click();
        } catch (Exception e) {
            System.out.println("No pop up");
        }
        //finding the text box
        WebElement text_box = driver.findElement(By.xpath("//*[@id=\"wpTextbox1\"]"));

        Thread.sleep(1000);
        String test_string = "This page is edited with Selenium ";
        Thread.sleep(1000);
        text_box.sendKeys(Keys.chord(Keys.COMMAND, Keys.UP));
        text_box.sendKeys(Keys.chord(Keys.DOWN));
        text_box.sendKeys(test_string);
        text_box.sendKeys(Keys.chord(Keys.COMMAND, Keys.DOWN));
        ReferenceTest(text_box);

       //enter Captcha Manually
        driver.findElement(By.xpath("//*[@id=\"wpSave\"]")).click();
        //Thread.sleep(5000);
        driver.findElement(By.id("wpSave")).click();
        //driver.findElement(By.id("wpSave")).click();
    }
    @Test(priority = 20)
    void ViewPage() throws InterruptedException {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        driver.findElement(By.xpath("//*[@id=\"vector-user-links-dropdown-checkbox\"]")).click();//clicking user menu
        driver.findElement(By.partialLinkText("Contributions")).click();//clicking on contributions
        driver.findElement(By.linkText("Draft:Selenium Playgrounds")).click();
        WebElement refernces = driver.findElement(By.className("mw-references-wrap"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", refernces);
        Thread.sleep(1500);
    }
    @Test(priority = 21)
    void pageTools() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://w.wiki/9mFG");
        WebElement general_tools = driver.findElement(By.xpath("//*[@id=\"p-tb\"]/div[2]/ul"));
        WebElement print_export_tools = driver.findElement(By.xpath("//*[@id=\"p-electronpdfservice-sidebar-portlet-heading\"]/div[2]/ul"));
        List<WebElement> gen_tool_links = general_tools.findElements(By.xpath(".//a"));
        List<WebElement> prt_ex_links = print_export_tools.findElements(By.xpath(".//a"));
        List<String> links = new ArrayList<>();
        for(WebElement link: gen_tool_links){
            links.add(link.getAttribute("href"));
        }
        for(WebElement link: prt_ex_links){
            links.add(link.getAttribute("href"));
        }
        links.removeLast();
        for(String url: links){
            driver.get(url);
            Thread.sleep(1000);
            if(Objects.equals(url, "Get shortened URL")){
                driver.findElement(By.xpath("//*[@id=\"mw-teleport-target\"]/div/div/div[1]/div[2]/div[3]/div/span/a")).click();
            }
            driver.get("https://w.wiki/9mFG");
        }

    }
}
