package Pages;

import Helper.RepositoryParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {

    private By productLinksLocator = By.xpath("//*[@class= \"item product product-item\"]");

    public HomePage(WebDriver browserDriver, RepositoryParser objParser){
        super(browserDriver, objParser);
    }



    //function to go to men category page
    public HomePage goToMenCategory(){
        //get object of category men
        WebElement btnMenCat = driver.findElement(parser.getObjectLocator("btnCatLv1Men"));
        //wait until the page is loading succesfully
        wait.until(ExpectedConditions.visibilityOf(btnMenCat));
        //click in men category
        btnMenCat.click();
        //assert the title of the page is Men category
        WebElement textTitle = driver.findElement(parser.getObjectLocator("textTitlePage"));
        wait.until(ExpectedConditions.textToBePresentInElement(textTitle, "Men"));
        return this;
    }

    //function to click tops jacket men
    public HomePage clickTopsJacketMen(){
        //get object tops jacket men
        WebElement linkTopsJacket = driver.findElement(parser.getObjectLocator("linkTopsJacketMen"));
        //wait until the page is loading succesfully
        wait.until(ExpectedConditions.elementToBeClickable(linkTopsJacket));
        //click on the link jacket
        linkTopsJacket.click();
        //assert the title of the page is Jackets Category
        WebElement textTitle = driver.findElement(parser.getObjectLocator("textTitlePage"));
        wait.until(ExpectedConditions.textToBePresentInElement(textTitle, "Jackets"));
        return this;
    }

    //function to return list of products in home page
    public List<WebElement> getProductNameLinks() {
        //wait until the page load completely
        wait.until(ExpectedConditions.elementToBeClickable(productLinksLocator));
        //get the product link list
        List<WebElement> productLinks = driver.findElements(productLinksLocator);
        return productLinks;
    }

    //function to select the product
    public ProductDetailPage selectProduct(int productIndex){
        List<WebElement> productNameLinks = getProductNameLinks();
        productNameLinks.get(productIndex).click();
        sleep(3000);
        return new ProductDetailPage(driver, parser);
    }

}
