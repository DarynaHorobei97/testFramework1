package pages;

import helpers.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static helpers.ColorPrinter.printColorMessage;


public class LoginPage extends BasePage {
    private WebElement logLocator = driver.findElement(By.className("header-logo"));
    private WebElement loginFieldLocator = driver.findElement(By.id("login_field"));
    private WebElement passwordFieldLocator = driver.findElement(By.id("password"));
    private WebElement signInButtonLocator = driver.findElement(By.xpath("//input[@value=\"Sign in\"]"));
    //private WebElement errorTextLocator = driver.findElement(By.xpath("//div[contains(text(), \"Incorrect username or password.\")]"));
    private final static String TITLE = "Login page";
    @FindBy(className = "header-logo")
    private WebElement logo;


    public LoginPage(WebDriver driver) {
        super(driver, TITLE);
    }

    public WebElement getLogo() {
        return logLocator;
    }


    public MainPage loginSuccessful(String login, String password){
        logger.info("Authorization has started");
        loginFieldLocator.sendKeys(login);
        passwordFieldLocator.sendKeys(password);
        signInButtonLocator.click();
        logger.info("Successful authorization");
        printColorMessage("Successful authorization", logger, Level.INFO);
        return new MainPage(driver);
    }

    public LoginPage loginFailed(String login, String password){
        loginFieldLocator.sendKeys(login);
        passwordFieldLocator.sendKeys(password);
        signInButtonLocator.click();
        return this;
    }

    public LoginPage validateErrorMessage(String expectedMessage){
       // Assertions.assertEquals(expectedMessage, errorTextLocator.getText(),
               // "Error message is invalid!" );
        return this;
    }
}
