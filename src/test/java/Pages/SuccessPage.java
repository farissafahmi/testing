package Pages;

import Helper.RepositoryParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class SuccessPage extends BasePage {
    WebElement txtOrderNumber;
    String orderNumber;



    public SuccessPage(WebDriver browserDriver, RepositoryParser parserInput){
        super(browserDriver, parserInput);
        sleep(3000);
    }

    //initially this is to get data order number and will compare the data after checkout succesfully
    public String getOrderNumber() {
//        txtOrderNumber = driver.findElement(parser.getObjectLocator("txtOrderNumber"));
//        orderNumber = txtOrderNumber.getText();
        System.out.println(orderNumber);
        return orderNumber;
    }

    //initially this is to set data order number and will compare the data after checkout succesfully

    public void setOrderNumber(String orderNumber){
        this.orderNumber = orderNumber;
    }

    //method to go to my order page
    public SuccessPage goToMyOrders(){
        txtOrderNumber = driver.findElement(parser.getObjectLocator("txtOrderNumber"));
        txtOrderNumber.click();
        return this;
   }
}
