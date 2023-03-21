package Pages;

import Helper.RepositoryParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import Pages.CheckoutPage;


public class PaymentPage extends BasePage {
    WebElement txtEstTotalPrice;
    WebElement checkBoxAddress;
    WebElement btnPlaceOrder;
    private String estTotalPrice;


    public PaymentPage(WebDriver browserDriver, RepositoryParser parserInput){
        super(browserDriver, parserInput);
        sleep(3000);
    }

    //the idea to get the price so i can do the assertion, but again this is still failed
    public String getUnitPrice(){
        txtEstTotalPrice = driver.findElement(parser.getObjectLocator("txtEstTotalPrice"));
        setUnitPrice(txtEstTotalPrice.getText());
        return estTotalPrice;
    }

    //the idea to set the price so i can do the assertion, but again this is still failed
    public void setUnitPrice(String unitPrice){
        this.estTotalPrice = unitPrice;
    }

   //method to choose if the shipping address is the same with billing addressss
   public PaymentPage checklistShippingAddress(){
        sleep(3000);
        checkBoxAddress = driver.findElement(parser.getObjectLocator("checkBoxAddress"));
       if (!checkBoxAddress.isSelected()){
               checkBoxAddress.click();
           }
       return this;
   }

   //method to continue place order
    public SuccessPage placeOrder(){
        btnPlaceOrder = driver.findElement(parser.getObjectLocator("btnPlaceOrder"));
        btnPlaceOrder.click();
        return new SuccessPage(driver, parser);
    }





}
