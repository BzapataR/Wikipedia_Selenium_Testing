import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Watchlist {
    private static WebDriver driver;

    @BeforeClass
    void setUpClass() throws InterruptedException {
        driver = SetUp.getDriver();
        // Additional setup specific to HomePageTesting
    }

    private static final String BASE_URL = "https://en.wikipedia.org/wiki/Main_Page";
    private static final int ACTION_DELAY = 2000; // milliseconds for better stability in tests

    @Test(priority = 16)
    public void testArticleAddedToWatchlist() throws InterruptedException {
        // Perform login
        //login("Testexample123", "Testpass123");

        // Open the main menu dropdown
        WebElement menuDropdown = driver.findElement(By.id("vector-main-menu-dropdown-checkbox"));
        menuDropdown.click();
        Thread.sleep(ACTION_DELAY);

        // Click on the "Random article" link identified by the <span> tag
        WebElement randomArticleLink = driver.findElement(By.xpath("//span[text()='Random article']"));
        randomArticleLink.click();
        Thread.sleep(ACTION_DELAY);

        // Add the article to the watchlist by clicking the star icon
        WebElement watchlistStar = driver.findElement(By.cssSelector("span.vector-icon.mw-ui-icon-star.mw-ui-icon-wikimedia-star"));
        watchlistStar.click();
        Thread.sleep(ACTION_DELAY);

        // Retrieve the article title
        String articleTitle = driver.findElement(By.id("firstHeading")).getText();

        // Navigate to the watchlist page by clicking the watchlist icon
        WebElement watchlistIcon = driver.findElement(By.cssSelector("span.vector-icon.mw-ui-icon-watchlist.mw-ui-icon-wikimedia-watchlist"));
        watchlistIcon.click();
        Thread.sleep(ACTION_DELAY);

        // Click on the "View and edit watchlist" link
        WebElement viewAndEditLink = driver.findElement(By.xpath("//a[@href='/wiki/Special:EditWatchlist']/span"));
        viewAndEditLink.click();
        Thread.sleep(ACTION_DELAY);


        // Click on the checkbox to select all articles watchlisted
        WebElement selectAllCheckbox = driver.findElement(By.id("ooui-php-2"));
        selectAllCheckbox.click();
        Thread.sleep(ACTION_DELAY);

        // Click on the "Remove titles" span element to remove the selected articles from watchlist
        WebElement removeTitlesButton = driver.findElement(By.xpath("//span[@class='oo-ui-labelElement-label' and text()='Remove titles']"));
        removeTitlesButton.click();
        Thread.sleep(ACTION_DELAY);


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

        // Check if login was successful
        WebElement confirmationElement = driver.findElement(By.xpath("//span[contains(text(),'" + username + "')]"));
        Assert.assertNotNull(confirmationElement, "Login failed. Confirmation element not found.");
    }
    @Test (priority = 101)
    static void logout() throws InterruptedException {
        WebElement expandableMenu = driver.findElement(By.xpath("//*[@id=\"vector-user-links-dropdown-checkbox\"]"));
        expandableMenu.click();
        WebElement userMenu = driver.findElement(By.id("pt-logout"));
        userMenu.click();
        Thread.sleep(ACTION_DELAY);
    }
}





