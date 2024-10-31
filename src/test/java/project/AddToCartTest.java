package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {
	
	
    @Test(priority = 0)
    public void testProductList() {
        // Example test to verify the presence of a specific product
        WebElement product = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        Assert.assertTrue(product.isDisplayed(), "Sauce Labs Backpack is not displayed on the Products page.");
    }
    

    @Test(priority = 1)
    public void addItemToCartAndVerify() {
        // Step 1: Add a product to the cart
        WebElement addToCartButton = driver.findElement(By.xpath("(//button[contains(text(),'ADD TO CART')])[1]"));
        addToCartButton.click();

        // Step 2: Verify that the cart badge is updated
        WebElement cartBadge = driver.findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']"));
        String badge= cartBadge.getText();
        System.out.println(badge);
        Assert.assertTrue(cartBadge.isDisplayed(), "Cart badge is not displayed.");
        Assert.assertEquals(cartBadge.getText(), "1", "Cart badge does not show the correct item count.");

        // Optional: Navigate to the cart to verify the item is present
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
        cartIcon.click();

        // Step 3: Verify the product is present in the cart
        WebElement productInCart = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        Assert.assertTrue(productInCart.isDisplayed(), "Sauce Labs Backpack is not present in the cart.");
    }
}
