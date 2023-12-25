package ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;


import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GitHubTest extends BaseTest {

    Logger logger;

  @Test
  public void precondition(){
    System.out.println("A am precondition");
    assertTrue(true);
}

   @Test(dependsOnMethods = "precondition")
    public void validateLogoOnTheLoginPageIsDisplayed() {
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.goToLoginPage().getLogo().isDisplayed());
    }

    @Test
    public void validateAnUserCanLoginSuccessfully() {
        HomePage homePage = new HomePage(driver);
        Assert.assertEquals("Home", homePage.goToLoginPage().loginSuccessful("test9874@ukr.net", "test9874@ukr.net").getHeaderLocator()
                .getText());
    }

    @Test
    public void validateFailedLogin() {
        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage().loginFailed("test9874@ukr.net1", "test9874@ukr.net")
                .validateErrorMessage("Incorrect username or password.");
    }

    @DataProvider(name = "dataProvider1")
    public Object[][] credentialsProvider(){
       return new Object[][]{
               {"test9874@ukr.net1", ""},
               {"1test9874@ukr.net1", "test9874@ukr.net"},
               {"test9874@ukr.net1", "1test9874@ukr.net"}
       };
    }

    @Test(dataProvider = "dataProvider1")
    public void validateNegativeLoginParametrized(String login, String password){
        HomePage homePage = new HomePage(driver);
        homePage.goToLoginPage().loginFailed(login, password)
                .validateErrorMessage("Incorrect username or password.");

    }

    @Test
    public void validateRepositoriesList() {
        Logger logger = LogManager.getLogger();
        logger.info("validateRepositoriesList starts");
        HomePage homePage = new HomePage(driver);
        List<String> actualRepositoriesList = homePage.goToLoginPage().loginSuccessful("test9874@ukr.net",
                        "test9874@ukr.net").goToProfileForm()
                .goToRepositoriesPage().getRepositoriesNames();
        List<String> expectedRepositoriesList = new ArrayList<>();
        expectedRepositoriesList.add("test2");
        expectedRepositoriesList.add("test1");
        expectedRepositoriesList.add("test");
        assertEquals(expectedRepositoriesList, actualRepositoriesList,
                "Actual repositories are not equal to expected ones");
        logger.info("validateRepositoriesList passed successfully");
    }

    @DataProvider(name = "dataProvider")
    public Object[][] createData(){
       return new Object[][]{
               {"Alex", 20},
               {"Ann", 18},
               {"Ann", 18}
       };
    }

    @Test(dataProvider = "dataProvider")
    public void validateData(String n1, Integer n2) {
        System.out.println(n1 + " " + n2);
    }

    @Test
    public void someChecks(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(false);
        softAssert.assertEquals(2, 3);
        softAssert.assertTrue(false);
        softAssert.assertAll();
    }

}
