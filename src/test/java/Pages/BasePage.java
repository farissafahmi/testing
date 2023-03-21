package Pages;

import Helper.RepositoryParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class BasePage {
   WebDriver driver;
   RepositoryParser parser;
    WebDriverWait wait;

    /*this is main class which takes care of Browser setup, and other reusable methods.
    With base class I can avoid code duplication and can reuse the code as much as I want
    * */
    public BasePage(WebDriver browserDriver, RepositoryParser objParser) {
        driver = browserDriver;
        parser = objParser;
        wait = new WebDriverWait(browserDriver, Duration.ofSeconds(3));
    }

    public BasePage sleep(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return this;
    }

    public LoginPage navigateToLogin(String url){
        //open test page
        driver.get(url);
        //maximize browser window
        driver.manage().window().maximize();
        return new LoginPage(driver, parser);
    }

    public HomePage navigateTo(String url){
        //open test page
        driver.get(url);
        //maximize browser window
        driver.manage().window().maximize();
        return new HomePage(driver, parser);
    }}

