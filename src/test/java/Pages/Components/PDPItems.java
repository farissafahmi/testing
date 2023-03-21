package Pages.Components;

import org.openqa.selenium.WebElement;

/*this class is intended to set and get data that I'll get from PDP so it can be easier to do the assertion by comparing the data.
but too bad I'm not success yet. But here's the idea and I hope we can have discussion to do the assertion better in Selenium and testNG later :)
*/
public class PDPItems {
    private String productName;
    private double unitPrice;



    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }

    public double getUnitPrice(){
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice){
        this.unitPrice = unitPrice;
    }

}
