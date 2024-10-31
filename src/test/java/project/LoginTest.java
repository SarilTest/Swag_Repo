package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


/**
 * This is the test for validating all user logins provided in the login page of the website
 */

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
    	driver= TestUtil.setupDriver();
    }

   @DataProvider(name = "loginData")
   public Object[][] loginDataProvider() {
       return Config.USER_CREDENTIALS_ARRAY;
    }

   /**
    * This test covers login tests for all 4 users 
   		Standard user  -> Test should pass as the product catalogue is displayed
   		locked user -> Test should pass as the code assert the errorMessage on the login page if its not logged in
   		Problem user  -> Test should pass as the product catalogue is displayed ( login successful)
   		PerformanceGlitch_user -> Test should fail as its take longer than 1 sec to get the product catalogue page.
    */
   
   
    @Test(dataProvider = "loginData", timeOut = 2000)
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
    public void unloadDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}