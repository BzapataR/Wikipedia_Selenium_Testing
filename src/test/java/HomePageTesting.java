import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.File;


public class HomePageTesting {
    private WebDriver driver;

    @BeforeClass
    void setUpClass() throws InterruptedException {
        driver = SetUp.getDriver();
        //Watchlist.logout();
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        // Additional setup specific to HomePageTesting
        //SetUp.Logins();
    }

    public static String verifyLinks(String linkUrl)// test each url to see if it will connect if not then return url
    {
        try
        {
            URL url = new URL(linkUrl);

            //Now we will be creating url connection and getting the response code
            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
            httpURLConnect.setConnectTimeout(5000);
            httpURLConnect.connect();
            if(httpURLConnect.getResponseCode()>=400)
            {
                System.out.println(STR."\{linkUrl} is a broken link - Error message: \{httpURLConnect.getResponseMessage()} \{httpURLConnect.getResponseCode()}  ");
                return(linkUrl);
            }

            //Fetching and Printing the response code obtained
            else{
                //System.out.println(STR."\{linkUrl} - \{httpURLConnect.getResponseMessage()}");
                return(null);
            }
        }
        catch (Exception _) {
        }
        return(null);
    }
@Test(priority = 100)
    void getHomeLinks() throws InterruptedException {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        driver.manage().window().maximize();
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println(STR."Number of links \{links.size()}");
        List<String> BadLinks= new ArrayList<>();
        List<String> HomeUrls = new ArrayList<>();
        int SuccessfulLinks = 0;
        for( WebElement link: links){
            String url = link.getAttribute("href");
            HomeUrls.add(url);
        }
        for(String link: HomeUrls){
            driver.get(link);
            String BadLink = verifyLinks(link);
            if (BadLink!= null) {
                BadLinks.add(BadLink);
            }
            else{
                SuccessfulLinks++;
            }
        }
        Assert.assertTrue(BadLinks.isEmpty(), STR."The following links are broken: \{BadLinks.toString()}");
        ((JavascriptExecutor) driver).executeScript("alert('" + SuccessfulLinks + " / " + links.size() + " Links opened');");
        Thread.sleep(10000);
        driver.switchTo().alert().accept();//clicking the accept button



}
    @Test(priority = 7)
    void getFeaturedArticle() throws InterruptedException {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        try {
            driver.findElement(By.linkText("Full article...")).click();
        } catch (Exception e) {
            driver.findElement(By.xpath("//*[@id=\"mp-tfa\"]/p/b/a")).click();
        }
        String Feat_Arti_URL = driver.getCurrentUrl();
        System.out.println(STR. "\nFeatured Article Link: \{Feat_Arti_URL}\n");
        Thread.sleep(1000);
    }
    @Test(priority = 8)
    void getInTheNewsArticles() throws InterruptedException {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        driver.manage().window().maximize();
        WebElement news_div = driver.findElement(By.id("mp-itn"));
        WebElement ul_div = news_div.findElement(By.xpath(".//ul"));
        List<WebElement> target_div = ul_div.findElements(By.xpath(".//b"));
        List<String> urls = new ArrayList<>();
        for (WebElement tag : target_div) {
            List<WebElement> links = tag.findElements(By.xpath(".//a"));
            for (WebElement link : links) {
                System.out.println(STR."Link Text: \{link.getText()}");
                System.out.println(STR."Link Href: \{link.getAttribute("href")}");
                urls.add(link.getAttribute("href"));
            }
        }
        for(String links: urls){
            driver.get(links);
            Thread.sleep(1000);
        }
    }
    @Test(priority = 9)
    void getDidYouKnowArticles() throws InterruptedException {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        driver.manage().window().maximize();
        WebElement news_div = driver.findElement(By.id("mp-dyk"));
        WebElement ul_div = news_div.findElement(By.xpath(".//ul"));
        List<WebElement> target_div = ul_div.findElements(By.xpath(".//b"));
        List<String> urls = new ArrayList<>();
        for (WebElement tag : target_div) {
            List<WebElement> links = tag.findElements(By.xpath(".//a"));
            for (WebElement link : links) {
                System.out.println(STR."Link Text: \{link.getText()}");
                System.out.println(STR."Link Href: \{link.getAttribute("href")}");
                urls.add(link.getAttribute("href"));
            }
        }
        for(String links: urls){
            driver.get(links);
            Thread.sleep(1000);
        }
    }
    @Test(priority = 10)
    void getOnThisDayArticles() throws InterruptedException {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        driver.manage().window().maximize();
        WebElement news_div = driver.findElement(By.id("mp-otd"));
        List<WebElement> target_div = news_div.findElements(By.xpath(".//ul//b"));
        target_div.subList(target_div.size() - 4, target_div.size()).clear();// removing the last 4 items
        List<String> urls = new ArrayList<>();
        for (WebElement tag : target_div) {
            List<WebElement> links = tag.findElements(By.xpath(".//a"));
            for (WebElement link : links) {
                System.out.println(STR."Link Text: \{link.getText()}");
                System.out.println(STR."Link Href: \{link.getAttribute("href")}");
                urls.add(link.getAttribute("href"));
            }
        }
        for(String links: urls){
            driver.get(links);
            Thread.sleep(1000);
        }
    }

    @Test(priority = 11)
    void getFeaturedList() throws InterruptedException {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        boolean featuredListToday= false;
        try {
            driver.findElement(By.linkText("Full list...")).click();
            String Feat_List_URL = driver.getCurrentUrl();
            System.out.println(STR. "\nFeatured List Link: \{Feat_List_URL}\n");
            featuredListToday = true;
        } catch (Exception e) {
            System.out.println("No Featured List Today");
        }
        Thread.sleep(3000);
        Assert.assertTrue(featuredListToday, "Try again a different day");
    }

    @Test(priority = 12)
    void getImageOfTheDay() throws IOException, InterruptedException {
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        driver.manage().window().maximize();
        WebElement imageElement = driver.findElement(By.xpath("//*[@id=\"mp-tfp\"]/table/tbody/tr/td[1]/span/a/img"));
        String imageURL = imageElement.getAttribute("src");
        imageElement.click();
        System.out.println(imageURL);
        URL url = new URL(imageURL);
        // Create a File object to store the downloaded image
        File imageFile = new File("picture_of_the_day.jpg");
        FileUtils.copyURLToFile(url, imageFile);
        Thread.sleep(3000);
    }
}


