package lesson6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;



public class ExpenseRequestsPage extends BaseView {
    public ExpenseRequestsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()='Создать заявку на расход']")
    public WebElement createExpenseButton;

    public void createExpense() {
        createExpenseButton.click();
        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(new CreateExpenseRequestPage(driver).sumPlanInputLocator));
    }

    public By createExpenseButtonLocator = By.xpath("//a[text()='Создать заявку на расход']");
}
