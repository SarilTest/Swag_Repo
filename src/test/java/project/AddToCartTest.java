package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {
	
	
    @Test(priority = 0)
    public void testProductList() {
        // testing product existence
        WebElement product = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        Assert.assertTrue(product.isDisplayed(), "Sauce Labs Backpack is not displayed on the Products page.");
    }
    

    @Test(priority = 1)
    public void addItemToCartAndVerify() {
        // Add to cart
        WebElement addToCartButton = driver.findElement(By.xpath("(//button[contains(text(),'ADD TO CART')])[1]"));
        addToCartButton.click();

        // checking cart updated or not
        WebElement cartBadge = driver.findElement(By.xpath("//span[@class='fa-layers-counter shopping_cart_badge']"));
        String badge= cartBadge.getText();
        System.out.println(badge);
        Assert.assertTrue(cartBadge.isDisplayed(), "Cart badge is not displayed.");
        Assert.assertEquals(cartBadge.getText(), "1", "Cart badge does not show the correct item count.");

        // verify items present to add to cart
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
        cartIcon.click();

        //verify items in cart
        WebElement productInCart = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        Assert.assertTrue(productInCart.isDisplayed(), "Sauce Labs Backpack is not present in the cart.");
    }
}
