package lesson6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CreateProjectPage extends BaseView{
    public CreateProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='crm_project[name]']")
            public WebElement projectName;

    public CreateProjectPage fillProjectName(String name) {
        projectName.sendKeys(name);
        return this;
    }

    @FindBy(xpath = "//span[text()='Укажите организацию']")
            public WebElement indicateOrganization;

    @FindBy(xpath = "//*[@id='select2-drop']/div/input")
    public WebElement inputOrganization;

    public CreateProjectPage fillOrganization(String organization) throws InterruptedException {
        indicateOrganization.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='select2-drop']/div/input")));
        inputOrganization.sendKeys(organization);
        Thread.sleep(3000);// тут нужно безусловно подождать, чтобы нажать ВВОД
        inputOrganization.sendKeys(Keys.ENTER);
        return this;
    }

    @FindBy(name = "crm_project[businessUnit]")
    public WebElement businessUnitSelect;

    public CreateProjectPage selectBusinessUnit(String businessUnit) {
        new Select(businessUnitSelect).selectByVisibleText(businessUnit);
        return this;
    }

    @FindBy(name = "crm_project[curator]")
    public WebElement curatorSelect;

    public CreateProjectPage selectCurator (String curator) {
        new Select(curatorSelect).selectByVisibleText(curator);
        return this;
    }

    @FindBy(name = "crm_project[rp]")
    public WebElement projectLeaderSelect;

    public CreateProjectPage selectProjectLeader (String projectLeader) {
        new Select(projectLeaderSelect).selectByVisibleText(projectLeader);
        return this;
    }

    @FindBy(name = "crm_project[manager]")
    public WebElement projectManagerSelect;

    public CreateProjectPage selectProjectManager (String projectManager) throws InterruptedException {
        new Select(projectManagerSelect).selectByVisibleText(projectManager);
        Thread.sleep(7000);
        return this;
    }

    @FindBy(xpath = "//button[contains(text(),'Сохранить и закрыть')]")
    public WebElement saveAndCloseButton;

    @FindBy(xpath = requestSuccessLocator)
    public WebElement requestSuccess;

    public static final String requestSuccessLocator = "//*[text()='Проект сохранен']";

    public By createProjectPageLocator = By.xpath("//input[@name='crm_project[name]']");
}
