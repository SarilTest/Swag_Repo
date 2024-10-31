package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BoundaryTestPostCode extends BaseTest {

    @DataProvider(name = "PostalCodeBoundaryData")
    public Object[][] postalCodeData() {
        return new Object[][]{
                {"John", "Doe", "12345"},        // Minimum Boundary (5 characters)
                {"John", "Doe", "1234567890"},   // Maximum Boundary (10 characters)
                {"John", "Doe", "12345678901"}   // Above Maximum Boundary (11 characters)
        };
    }

    @Test(dataProvider = "PostalCodeBoundaryData")
    public void testPostalCodeBoundary(String firstName, String lastName, String postalCode) {
        // Navigate to the checkout information page
        navigateToCheckoutPage();

        // Enter checkout information
        WebElement firstNameField = driver.findElement(By.id("first-name"));
        WebElement lastNameField = driver.findElement(By.id("last-name"));
        WebElement postalCodeField = driver.findElement(By.id("postal-code"));
        
        firstNameField.clear();
        lastNameField.clear();
        postalCodeField.clear();

        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(postalCode);

        // Attempt to continue to the next step in the checkout process
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        if (postalCode.length() >= 5 && postalCode.length() <= 10) {
            // Expected to succeed if postal code is within valid length range
            WebElement overviewTitle = driver.findElement(By.className("title"));
            Assert.assertTrue(overviewTitle.isDisplayed(), "Checkout should proceed with postal code length: " + postalCode.length());
            driver.navigate().back(); // Go back to the cart to reset for the next test
        } else {
            // Expected to fail if postal code length exceeds maximum
            WebElement errorMessage = driver.findElement(By.cssSelector("[data-test='error']"));
            Assert.assertTrue(errorMessage.isDisplayed(), "Error message should display for invalid postal code length: " + postalCode.length());
        }
    }
    public void navigateToCheckoutPage() {
        // Add an item to the cart
        WebElement addToCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addToCartButton.click();
        
        // Navigate to the cart page
        WebElement cartButton = driver.findElement(By.className("shopping_cart_link"));
        cartButton.click();

        // Proceed to the checkout information page
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();
    }
}
