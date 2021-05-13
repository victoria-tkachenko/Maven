package lesson6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AllContactPersonsPage extends BaseView{
    public AllContactPersonsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div/a[contains(text(),'Создать контактное лицо')]")
    public WebElement createContactPerson;

    public By createContactPersonLocator = By.xpath("//div/a[contains(text(),'Создать контактное лицо')]");

    public void setCreateContactPerson() {
        createContactPerson.click();
        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(new CreateContactPersonPage(driver).createContactPersonLocator));
    }
}
