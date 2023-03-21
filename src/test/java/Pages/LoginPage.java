package Pages;


import Helper.RepositoryParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver browserDriver, RepositoryParser parserInput){
        super(browserDriver, parserInput);

    }

    //method to set email address
    public void setEmailAddress(String inputEmailAddress){
        WebElement email = driver.findElement(parser.getObjectLocator("inputTextEmail"));
        email.clear();
        email.sendKeys(inputEmailAddress);
    }

    //method to set password
    public void setPassword(String inputPassword){
        WebElement password = driver.findElement(parser.getObjectLocator("inputTextPassword"));
        password.clear();
        password.sendKeys(inputPassword);
    }
    //method to click the sign in button
    public void clickSignInButton(){
        WebElement signInBtn = driver.findElement(parser.getObjectLocator("buttonLogin"));
        signInBtn.click();

    }
    //method to do login with correct data
    public AccountPage loginWithCorrectData(String inputEmailAddress, String inputPassword){
        setEmailAddress(inputEmailAddress);
        setPassword(inputPassword);
        clickSignInButton();
        return new AccountPage(driver, parser);
    }
}
