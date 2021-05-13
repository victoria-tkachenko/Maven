package lesson6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class CreateExpenseRequestPage extends BaseView{
    public CreateExpenseRequestPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(name = "crm_expense_request[description]")
    public WebElement requestDescription;

    public CreateExpenseRequestPage fillDescription(String description) {
        requestDescription.sendKeys(description);
        return this;
    }

    @FindBy(name = "crm_expense_request[businessUnit]")
    public WebElement businessUnitSelect;

    public CreateExpenseRequestPage selectBusinessUnit(String businessUnit) {
        new Select(businessUnitSelect).selectByVisibleText(businessUnit);
        return this;
    }

    @FindBy(name = "crm_expense_request[expenditure]")
    public WebElement expenseSelect;

    public CreateExpenseRequestPage selectExpense(String expenseName) {
        new Select(expenseSelect).selectByVisibleText(expenseName);
        return this;
    }

    @FindBy(name = "crm_expense_request[sumPlan]")
    public WebElement sumPlanInput;

    public By sumPlanInputLocator = By.name("crm_expense_request[sumPlan]");

    public CreateExpenseRequestPage fillSumPlan(String sum) {
        sumPlanInput.sendKeys(sum);
        return this;
    }

    @FindBy(xpath = "//label[@class='required']//ancestor::div[contains(@class,'control')]" +
            "//input[@placeholder='Укажите дату']")
    public WebElement calendar;

    @FindBy(xpath = "//td[@data-handler='selectDay']")
    public List<WebElement> dates;

    public CreateExpenseRequestPage selectDate(String date) {
        calendar.click();
        dates.stream().filter(element -> element.getText().equals(date)).findFirst().get().click();
        return this;
    }

    @FindBy(xpath = "//button[contains(text(),'Сохранить и закрыть')]")
    public WebElement saveAndCloseButton;

    @FindBy(xpath = requestSuccessLocator)
    public WebElement requestSuccess;

    public static final String requestSuccessLocator = "//*[text()='Заявка на расход сохранена']";
}
