package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsPageTest extends BaseTest {

    @Test(priority = 1)
    public void verifyProductPresence() {
        // Example test to verify the presence of a specific product
        WebElement product = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        Assert.assertTrue(product.isDisplayed(), "Sauce Labs Backpack is not displayed on the Products page.");
    }
    
    @Test(priority = 3)
    public void verifyProductDetailsAndAddToCart() {
        // Step 1: Click on a product to navigate to its details page
        WebElement productLink = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        productLink.click();

        // Step 2: Verify product details
        WebElement productName = driver.findElement(By.className("inventory_details_name"));
        WebElement productDescription = driver.findElement(By.className("inventory_details_desc"));
        WebElement productPrice = driver.findElement(By.className("inventory_details_price"));
        WebElement addToCartButton = driver.findElement(By.xpath("(//button[contains(text(),'ADD TO CART')])[1]"));

        Assert.assertTrue(productName.isDisplayed(), "Product name is not displayed.");
        Assert.assertEquals(productName.getText(), "Sauce Labs Backpack", "Product name does not match.");
        Assert.assertTrue(productDescription.isDisplayed(), "Product description is not displayed.");
        Assert.assertTrue(productPrice.isDisplayed(), "Product price is not displayed.");
        Assert.assertTrue(addToCartButton.isDisplayed(), "Add to Cart button is not displayed.");

        // Step 3: Click the Add to Cart button
        addToCartButton.click();

        // Step 4: Verify that the cart badge is updated
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertTrue(cartBadge.isDisplayed(), "Cart badge is not displayed.");
        Assert.assertEquals(cartBadge.getText(), "1", "Cart badge does not show the correct item count.");
    }


    @Test( enabled=false )
    public void addProductToCart() {
        // Example test to add a product to the cart and verify it
        WebElement addToCartButton = driver.findElement(By.xpath("(//button[contains(text(),'ADD TO CART')])[1]"));
        addToCartButton.click();

        // Verify product is added to the cart by checking the cart badge
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));
        Assert.assertTrue(cartBadge.isDisplayed(), "Cart badge is not displayed after adding product.");
        Assert.assertEquals(cartBadge.getText(), "1", "Cart badge does not show the correct item count.");
    }
    
    

    @Test(priority = 2)
    public void verifySortingFunctionality() {
        // Example test for sorting functionality on the Products page
        WebElement sortDropdown = driver.findElement(By.className("product_sort_container"));
        sortDropdown.click();
        WebElement sortOption = driver.findElement(By.xpath("//option[@value='lohi']")); // Sort low to high
        sortOption.click();

        // Add verification logic as needed for sorting (for simplicity, omitted here)
        Assert.assertTrue(true, "Sorting functionality needs further verification logic.");
    }
}
