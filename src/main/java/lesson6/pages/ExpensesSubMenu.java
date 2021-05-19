package lesson6.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ExpensesSubMenu extends BaseView{
    public ExpensesSubMenu(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[text()='Заявки на расходы']")
    public WebElement expensesRequestsButton;

    @Step("Создание заявки на расход")
    public void createExpense() {
        expensesRequestsButton.click();
        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(new ExpenseRequestsPage(driver).createExpenseButtonLocator));
    }
}
