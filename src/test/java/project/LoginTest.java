package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    WebDriver driver;
    String url = "https://www.saucedemo.com/v1/index.html";

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\saril\\eclipse-workspace\\Testing\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(url);
    }

   @DataProvider(name = "loginData")
   public Object[][] loginDataProvider() {
       return new Object[][]{
           {"standard_user", "secret_sauce", true},   // valid user
          {"locked_out_user", "secret_sauce", false}, // locked user
            {"problem_user", "secret_sauce", true},      // another valid user
          {"performance_glitch_user", "secret_sauce", true}      // another valid user
        };
    }

   //This test covers login tests for all 4 users 
   //Standard user  -> Test should pass as the product catalogue is displayed
   //locked user -> Test should pass as the code assert the errorMessage on the login page if its not logged in
   //Problem user  -> Test should pass as the product catalogue is displayed ( login successful)
   //PerformanceGlitch_user -> Test should fail as its take longer than 1 sec to get the product catalogue page.
   
    @Test(dataProvider = "loginData", timeOut = 1000)
    public void loginTest(String username, String password, boolean isValidUser) {
        // Locate username and password fields and login button
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Enter username and password
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);

        // Click login button
        loginButton.click();

        // Check for successful login
        if (isValidUser) {
            boolean loginSuccess = driver.findElements(By.className("inventory_list")).size() > 0;
            Assert.assertTrue(loginSuccess, "Login should succeed for valid user: " + username);
            logout();
        } else {
            WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message should display for invalid user: " + username);
            usernameField.clear();
            passwordField.clear();
        }
       
    }
    //Logout after the test is performed 
    public void logout() {
        // Open the menu and click the logout link
        WebElement menuButton = driver.findElement(By.cssSelector("div[class='bm-burger-button'] button"));
        menuButton.click();
        WebElement logoutLink = driver.findElement(By.id("logout_sidebar_link"));
        logoutLink.click();

        // Verify redirection to the login page
        WebElement loginButton = driver.findElement(By.id("login-button"));
        Assert.assertTrue(loginButton.isDisplayed(), "Logout failed; login button not displayed.");
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}