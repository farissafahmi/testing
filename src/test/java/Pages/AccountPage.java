package Pages;

import Helper.RepositoryParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class AccountPage extends BasePage {

    //This account page can be use to do assertion that I successfully login
    public AccountPage(WebDriver browserDriver, RepositoryParser parserInput){
        super(browserDriver, parserInput);
        WebElement accountMsg = driver.findElement(parser.getObjectLocator("textMyAccountSidebar"));
        wait.until(ExpectedConditions.visibilityOf(accountMsg));

    }
}
