package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class ProfileForm extends BasePage {

    private By yourRepositoriesLocator = By.xpath("//span[contains(text(),\"Your repositories\")]");
    private final static String TITLE = "Profile page";
    public ProfileForm(WebDriver driver) {
        super(driver, TITLE);
    }


    public RepositoriesPage goToRepositoriesPage() {
        driver.findElement(yourRepositoriesLocator).click();
        logger.info("Repositories page");
        return new RepositoriesPage(driver);
    }
}
