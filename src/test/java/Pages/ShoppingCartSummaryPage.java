package Pages;

import Helper.RepositoryParser;
import Pages.Components.PDPItems;
import Pages.Components.ShoppingCartItems;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import Pages.ProductDetailPage;


import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

public class ShoppingCartSummaryPage extends BasePage {


    private By textCartTitle = By.xpath("//*[@data-ui-id='page-title-wrapper']");
    private By btnProceedToCheckout = By.xpath("//*[@data-role='proceed-to-checkout']");
    private By shoppingCartTableLocator = By.id("shopping-cart-table");


    private By rowProductNameLocator = By.cssSelector("td.col.item > div > strong > a");
    private By rowUnitPriceLocator = By.cssSelector("td.col.price > span > span");
    private By rowQuantityLocator = By.cssSelector("td.col.qty > div > div > label > input");
    private By rowTotalPriceLocator = By.cssSelector("td.col.subtotal > span > span > span");

    private By rowRemoveProductButtonLocator = By.xpath("//*[@title='Remove item' and @class='action action-delete']");

    private String productName;
    private double unitPrice;
    private int qty;
    private double subtotal;


    public ShoppingCartSummaryPage(WebDriver driver, RepositoryParser parser) throws Exception {
        super(driver, parser);
        // wait for page to load
        sleep(3000);
        wait.until(ExpectedConditions.textToBe(textCartTitle, "Shopping Cart"));
    }

    public List<ShoppingCartItems> getShoppingCart() throws Exception {
        sleep(3000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartTableLocator));
        WebElement shoppingCartTable = driver.findElement(shoppingCartTableLocator);
        List<WebElement> tableRows = shoppingCartTable.findElements(By.cssSelector("#shopping-cart-table > tbody > tr.item-info"));
        List<ShoppingCartItems> cartItems = new ArrayList<ShoppingCartItems>();


        for (WebElement row : tableRows) {
            ShoppingCartItems cartItem = new ShoppingCartItems();

            cartItem.setProductName(row.findElement(rowProductNameLocator).getText().trim());
            cartItem.setUnitPrice(Double.parseDouble(row.findElement(rowUnitPriceLocator).getText().trim().substring(1)));
            unitPrice = cartItem.getUnitPrice();
            cartItem.setProductQty(Integer.parseInt(row.findElement(rowQuantityLocator).getAttribute("value")));
            qty = cartItem.getProductQt();
            cartItem.setSubtotal(Double.parseDouble(row.findElement(rowTotalPriceLocator).getText().trim().substring(1)));
            subtotal = cartItem.getSubtotal();
            cartItem.setRemoveProductButton(row.findElement(rowRemoveProductButtonLocator));

            cartItems.add(cartItem);
        }

        return cartItems;
    }

    public CheckoutPage proceedToCheckout(){
        PDPItems pdp = new PDPItems();
        out.println("pdp items name = " + pdp.getProductName());
        WebElement btnCheckout = driver.findElement(parser.getObjectLocator("btnCheckout"));
        wait.until(ExpectedConditions.visibilityOf(btnCheckout));
        btnCheckout.click();
        return new CheckoutPage(driver,parser);
    }


}
