package Pages;

import Helper.RepositoryParser;
import Pages.Components.PDPItems;
import Pages.Components.ShoppingCartItems;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailPage extends BasePage {
    List<PDPItems> pdpItems;

    public ProductDetailPage(WebDriver browserDriver, RepositoryParser objParser){
        super(browserDriver, objParser);
        pdpItems = new ArrayList<PDPItems>();
    }

    //method to get the product name
    public String getProductName(){
        WebElement productName = driver.findElement(parser.getObjectLocator("textProductName"));
        return productName.getText();
    }

    //method to get the product proce
    public String getProductPrice(){
        WebElement productPrice = driver.findElement(parser.getObjectLocator("textProductPrice"));
        return productPrice.getText();
    }

    //method to set size of products, it is not a best practice because the code is hardcoded, but if I have more time to explore
    //I'll promise to make it better :)
    public ProductDetailPage selectSize(String inputSize){
        WebElement btnSize;
        switch(inputSize)
        {
            case "XS":
                btnSize  = driver.findElement(parser.getObjectLocator("btnSizeXS"));
                break;
            case "S":
                btnSize =driver.findElement(parser.getObjectLocator("btnSizeS"));
                break;
            case "M":
                btnSize = driver.findElement(parser.getObjectLocator("btnSizeM"));
                break;
            case "32":
                btnSize  = driver.findElement(parser.getObjectLocator("btnSize32"));
                break;
            case "33":
                btnSize =driver.findElement(parser.getObjectLocator("btnSize33"));
                break;
            case "34":
                btnSize = driver.findElement(parser.getObjectLocator("btnSize34"));
                break;
            default:
                btnSize = driver.findElement(parser.getObjectLocator("btnSizeXS"));
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnSize);
        btnSize.click();
        return this;

    }

    //method to set color of products, it is not a best practice because the code is hardcoded, but if I have more time to explore
    //I'll promise to make it better :)
    public ProductDetailPage selectColor(String inputColor){
        WebElement btnColor;
        switch(inputColor)
        {
            case "Black":
                btnColor  = driver.findElement(parser.getObjectLocator("btnColorBlack"));
                break;
            case "Blue":
                btnColor =driver.findElement(parser.getObjectLocator("btnColorBlue"));
                break;
            case "Orange":
                btnColor = driver.findElement(parser.getObjectLocator("btnColorOrange"));
                break;
            default:
                btnColor = driver.findElement(parser.getObjectLocator("btnColorBlack"));
        }
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnColor);
        btnColor.click();
        return this;

    }

    //method to set quantity of productss
    public ProductDetailPage setQty(String input){
        WebElement inputQty = driver.findElement(parser.getObjectLocator("inputTextQty"));
        inputQty.clear();
        inputQty.sendKeys(input);
        return this;

    }

    //method to add to cart after all the data is filled
    public ProductDetailPage addToCart() throws Exception {
        WebElement btnAtc = driver.findElement(parser.getObjectLocator("btnAtc"));
        btnAtc.click();
        addProductsData();
        return this;
    }


    //method to get ticker after suces add to cart
    public ProductDetailPage getTickerSuccess(){
        sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,-350)", "");
        WebElement textTicker = driver.findElement(parser.getObjectLocator("textTickerSuccess"));
        js.executeScript("arguments[0].scrollIntoView(true);", textTicker);
        String actualText = textTicker.getText();
        String productName = getProductName();
        Assert.assertTrue(actualText.contains(productName));
        return this;
    }

    //method to go to cart with url
    public ShoppingCartSummaryPage goToCart() throws Exception {
        sleep(3000);
        navigateTo("https://magento.softwaretestingboard.com/checkout/cart/");

        return new ShoppingCartSummaryPage(driver,parser);
    }

    //this is initially to store all the products data in list, so I can reuse the data to do assertion but too bad still not working properly
    public List<PDPItems> addProductsData() throws Exception {
        sleep(3000);

        PDPItems pdp = new PDPItems();
        pdp.setProductName(getProductName());
        pdp.setUnitPrice(Double.parseDouble(getProductPrice().trim().substring(1)));
        pdpItems.add(pdp);
        System.out.println("addProductDataList : "+ pdpItems.toString());
        return pdpItems;
    }






}
