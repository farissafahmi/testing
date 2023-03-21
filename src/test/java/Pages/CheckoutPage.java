package Pages;

import Helper.RepositoryParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Optional;

import java.time.Duration;


public class CheckoutPage extends BasePage {
    WebElement inputFirstName;
    WebElement inputLastName;
    WebElement inputCompany;
    WebElement inputStreetAddr;
    WebElement inputShipAddrCity;
    WebElement listProvince;
    WebElement inputShipAddrPostCode;
    WebElement listCountry;
    WebElement inputPhnNumber;
    WebElement radioBtnShipping1;
    WebElement btnNext;


    public CheckoutPage(WebDriver browserDriver, RepositoryParser parserInput){
        super(browserDriver, parserInput);
        sleep(5000);
        WebElement formShipping = driver.findElement(parser.getObjectLocator("titleShippingPage"));
        wait.until(ExpectedConditions.textToBePresentInElement(formShipping, "Shipping Address"));
    }

    //Method to input shipping address when user doesnt have an address yet
    public CheckoutPage inputShippingAddress(){
        inputFirstName = driver.findElement(parser.getObjectLocator("inputFirstName"));
        inputFirstName.clear();
        inputFirstName.sendKeys("Farissa");

        inputLastName = driver.findElement(parser.getObjectLocator("inputLastName"));
        inputLastName.clear();;
        inputLastName.sendKeys("Putri");

        inputCompany = driver.findElement(parser.getObjectLocator("inputCompany"));
        inputCompany.clear();
        inputCompany.sendKeys("PT Chocobi");

        inputStreetAddr = driver.findElement(parser.getObjectLocator("inputStreetAddr"));
        inputStreetAddr.clear();
        inputStreetAddr.sendKeys("Chocobi Chocolate Address");

        inputShipAddrCity = driver.findElement(parser.getObjectLocator("inputShipAddrCity"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", inputShipAddrCity);
        inputShipAddrCity.clear();
        inputShipAddrCity.sendKeys("Neo City");

        listProvince = driver.findElement(parser.getObjectLocator("listProvince"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", listProvince);
        Select drplistProvince = new Select(listProvince);
        drplistProvince.selectByVisibleText("Alabama");

        inputShipAddrPostCode = driver.findElement(parser.getObjectLocator("inputShipAddrPostCode"));
        inputShipAddrPostCode.clear();
        inputShipAddrPostCode.sendKeys("11510");

        listCountry = driver.findElement(parser.getObjectLocator("listCountry"));
        Select drpListCountry = new Select(listCountry);
        drpListCountry.selectByVisibleText("United States");

        inputPhnNumber = driver.findElement(parser.getObjectLocator("inputPhnNumber"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", inputPhnNumber);
        inputPhnNumber.clear();
        inputPhnNumber.sendKeys("09182828211");

        return this;

    }

    //Method to wait for elememt to be visible, I needed this to do if conditional and I can't use ExpectedCondition from selenium
    //because it keep throwing error No Such element
    public boolean waitForElementToBeVisible(String xpathLocator, Duration delay) {
        WebDriverWait wait = new WebDriverWait(driver, delay);
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(parser.getObjectLocator(xpathLocator))));
            return true;
        }catch (Throwable t) {
            return false;
        }
    }

    //Method to choose the first button courier, I really want to improve the script so I can provide dynamic data
    //but I already take a long time to finish this test
    public CheckoutPage chooseFirstCourier(){
        radioBtnShipping1 = driver.findElement(parser.getObjectLocator("radioBtnShipping1"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", radioBtnShipping1);
        radioBtnShipping1.click();
        return this;
    }

    //Method to continue processing the checkout to payment page
    public PaymentPage continueToPayment(){
        Boolean btnAdd= waitForElementToBeVisible("btnAddAddress", Duration.ofSeconds(3));
        if (btnAdd.equals(false)) {
            inputShippingAddress();
        }
        chooseFirstCourier();

        btnNext = driver.findElement(parser.getObjectLocator("btnNext"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btnNext);
        btnNext.click();


        return new PaymentPage(driver, parser);
    }



}
