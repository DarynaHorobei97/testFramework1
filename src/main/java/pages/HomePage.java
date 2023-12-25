package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;


public class HomePage extends BasePage {
    private By signInButtonLocator = By.xpath("(//a[@href=\"/login\"])[1]");
    private final static String TITLE = "Home page";

    public HomePage(WebDriver driver) {
        super(driver, TITLE);
    }

    @Step("Go to login page")
    public LoginPage goToLoginPage()  {
//        webDriverWait.until(elementToBeClickable(driver.findElement(signInButtonLocator)));
//        driver.findElement(signInButtonLocator).click();
        return new LoginPage(driver);
    }
}
