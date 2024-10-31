package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test
    public void completeCheckoutProcess() {
        // Step 1: Add two products to the cart
    	WebElement addToCartButton1 = driver.findElement(By.xpath("(//button[contains(text(),'ADD TO CART')])[1]"));
    	addToCartButton1.click();
        WebElement addToCartButton2 = driver.findElement(By.xpath("(//button[contains(text(),'ADD TO CART')])[2]"));
        addToCartButton2.click();

        // Step 2: Verify cart badge shows 2 items
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertTrue(cartBadge.isDisplayed(), "Cart badge is not displayed.");
        Assert.assertEquals(cartBadge.getText(), "2", "Cart badge does not show the correct item count.");

        // Step 3: Proceed to the cart page
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
        cartIcon.click();

        // Step 4: Click Checkout button
        WebElement checkoutButton = driver.findElement(By.xpath("//a[normalize-space()='CHECKOUT']"));
        checkoutButton.click();

        // Step 5: Enter user information on the checkout page
        WebElement firstNameField = driver.findElement(By.id("first-name"));
        WebElement lastNameField = driver.findElement(By.id("last-name"));
        WebElement postalCodeField = driver.findElement(By.id("postal-code"));
        
        firstNameField.sendKeys("Tom");
        lastNameField.sendKeys("Cruise");
        postalCodeField.sendKeys("AB111AB");

        // Step 6: Continue to the checkout overview page
        WebElement continueButton = driver.findElement(By.cssSelector("input[value='CONTINUE']"));
        continueButton.click();

        // Step 7: Verify that the overview page displays correct items and total amount
        WebElement product1 = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        WebElement product2 = driver.findElement(By.xpath("//div[text()='Sauce Labs Bolt T-Shirt']"));
        WebElement totalAmount = driver.findElement(By.className("summary_total_label"));

        Assert.assertTrue(product1.isDisplayed(), "Product 'Sauce Labs Backpack' is not displayed on the checkout overview page.");
        Assert.assertTrue(product2.isDisplayed(), "Product 'Sauce Labs Bike Light' is not displayed on the checkout overview page.");
        Assert.assertTrue(totalAmount.isDisplayed(), "Total amount is not displayed on the checkout overview page.");

        // Step 8: Complete the checkout
        WebElement finishButton = driver.findElement(By.cssSelector(".btn_action.cart_button"));
        finishButton.click();

        // Step 9: Verify the completion message
        WebElement completionMessage = driver.findElement(By.className("complete-header"));
        Assert.assertTrue(completionMessage.isDisplayed(), "Completion message is not displayed.");
        Assert.assertEquals(completionMessage.getText(), "THANK YOU FOR YOUR ORDER", "Completion message does not match expected text.");
    }
}
