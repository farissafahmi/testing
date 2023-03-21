package com.softwaretestingboard.magento;

import Pages.PaymentPage;
import Pages.SuccessPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import Helper.RepositoryParser;
import Pages.LoginPage;

import java.io.IOException;


public class CheckoutJourney {
    public String url = "https://magento.softwaretestingboard.com/";
    public String urlLogin = "https://magento.softwaretestingboard.com/customer/account/login/";
    public String urlPants = "https://magento.softwaretestingboard.com/men/bottoms-men/pants-men.html";
    private RepositoryParser parser;
    private WebDriver driver;


    @BeforeClass
    public void setUp() throws IOException{
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        parser = new RepositoryParser("src/test/java/Helper/objectRepository.properties");
    }



    @Test
    public void checkoutEndToEnd() throws Exception {
        //End to end journey for checkout from login until order placed
        LoginPage logPage = new LoginPage(driver, parser);
        logPage.navigateToLogin(urlLogin)
                .loginWithCorrectData("farissa5@lala.com", "Far3006!")
                .navigateTo(url)
                .goToMenCategory()
                .clickTopsJacketMen()
                .selectProduct(1)
                .selectSize("S")
                .selectColor("Black")
                .setQty("2")
                .addToCart()
                .getTickerSuccess()
                .navigateTo(urlPants)
                .selectProduct(0)
                .selectSize("32")
                .selectColor("Blue")
                .setQty("1")
                .addToCart()
                .getTickerSuccess()
                .goToCart()
                .proceedToCheckout()
                .continueToPayment()
                .checklistShippingAddress()
                .placeOrder()
                .goToMyOrders();
    }

    @AfterClass
    public void tearDown()
    {
        driver.close();
    }
}
