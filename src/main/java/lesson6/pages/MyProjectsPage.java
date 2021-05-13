package lesson6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyProjectsPage extends BaseView{
    public MyProjectsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()='Создать проект']")
    public WebElement createProject;

    public By createProjectLocator = By.xpath("//a[text()='Создать проект']");

    public void setCreateProject() {
        createProject.click();
        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(new CreateProjectPage(driver).createProjectPageLocator));
    }
}
