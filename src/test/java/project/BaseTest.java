package project;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseTest {
    protected WebDriver driver;

    
    @BeforeClass
    public void setup() {
    	driver= TestUtil.setupDriver();
    }
    @Test
    public void loginAsStandardUser() {
        // Perform login action with standard user credentials
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys(Config.STANDARD_USER);
        passwordField.sendKeys(Config.PASSWORD);
        loginButton.click();

        // Verify successful login by checking the presence of an element on the Products page
        boolean isOnProductsPage = driver.findElements(By.className("inventory_list")).size() > 0;
        Assert.assertTrue(isOnProductsPage, "Login failed or Products page not displayed");
        //Assert.assertTrue(isOnProductsPage);
    }

    @AfterClass
    public void unloadDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}

