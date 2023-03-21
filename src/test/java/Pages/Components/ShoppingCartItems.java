package Pages.Components;

import org.openqa.selenium.WebElement;

public class ShoppingCartItems {
    private String productName;
    private double unitPrice;
    private int qty;
    private double subTotal;
    private WebElement removeProductButton;

    /*this class is intended to set and get data that I'll get from Cart so it can be easier to do the assertion by comparing the data.
but too bad I'm not success yet. But here's the idea and I hope we can have discussion to do the assertion better in Selenium later :)
*/
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

    public Integer getProductQt(){
        return qty;
    }

    public void setProductQty(int qty){
        this.qty = qty;
    }

    public Double getSubtotal(){
        return subTotal;
    }

    public void setSubtotal(Double subTotal){
        this.subTotal = subTotal;
    }

    public void setRemoveProductButton(WebElement removeProductButton) {
        this.removeProductButton = removeProductButton;
    }


    public void removeProductFromBasket() {
        removeProductButton.click();
    }
}
