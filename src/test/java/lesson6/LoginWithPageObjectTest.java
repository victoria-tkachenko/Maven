package lesson6;

import lesson6.pages.CreateExpenseRequestPage;
import lesson6.pages.ExpenseRequestsPage;
import lesson6.pages.ExpensesSubMenu;
import lesson6.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static lesson6.Configuration.BASE_URL;
import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class LoginWithPageObjectTest extends BaseTest{

    @BeforeTest
    public void goToPage() {
        driver.get(BASE_URL);
    }

    @Test
    void loginTest() {
        loginPage.inputLogin.sendKeys("Applanatest1");
        loginPage.inputPassword.sendKeys("Student2020!");
        loginPage.buttonSubmit.click();
    }

    @Test
    void loginTestWithFluentPage() {
        new LoginPage(driver)
                .fillInputLogin("Applanatest1")
                .fillInputPassword("Student2020!")
                .submitLogin();
    }

    @Test
    void createNewExpenseRequest() {
        new LoginPage(driver).login("Applanatest1", "Student2020!")
                .navigationMenu.openNavigationMenuItem("Расходы");

        new ExpensesSubMenu(driver).createExpense();
        new ExpenseRequestsPage(driver).createExpense();

        new CreateExpenseRequestPage(driver)
                .fillDescription("test")
                .selectBusinessUnit("Research & Development")
                .selectExpense("01101 - ОС: вычислительная техника инфраструктуры")
                .fillSumPlan("123")
                .selectDate("24")
                .saveAndCloseButton.click();

        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(new CreateExpenseRequestPage(driver).requestSuccessLocator)));

        assertThat(new CreateExpenseRequestPage(driver).requestSuccess, isDisplayed());
    }
}
