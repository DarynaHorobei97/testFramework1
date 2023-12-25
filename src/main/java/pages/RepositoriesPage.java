package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.List;
import java.util.stream.Collectors;

public class RepositoriesPage extends BasePage {

    private List<WebElement> repositories = driver.findElements(By.xpath("//a[@itemprop=\"name codeRepository\"]"));
    private final static String TITLE = "Repositories page";
    public RepositoriesPage(WebDriver driver) {
        super(driver, TITLE);
    }

    public List<String> getRepositoriesNames(){
        List<String> repositoriesList = repositories.stream().map(repo -> repo.getText()).collect(Collectors.toList());
        return repositoriesList;
    }
}
