package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import static org.testng.Assert.assertTrue;

public class MainPage extends BasePage {

    private By headerLocator = By.xpath("//h3[text()=\"Home\"]");
    private By imgLocator = By.xpath("//span[@class=\"Button-label\"]/img[@class=\"avatar circle\"]");
    private final static String TITLE = "Main page";
    public MainPage(WebDriver driver) {
        super(driver, TITLE);
    }

    public WebElement getHeaderLocator() {
        return driver.findElement(headerLocator);
    }

   @Step("Go to Profile form")
    public ProfileForm goToProfileForm(){
        assertTrue(driver.findElement(imgLocator).isDisplayed());
        driver.findElement(imgLocator).click();
        return new ProfileForm(driver);
    }
}
