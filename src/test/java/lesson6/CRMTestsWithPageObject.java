package lesson6;

import lesson6.pages.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;


public class CRMTestsWithPageObject extends BaseTestLoginWithCookie {

    @Test(description = "Создание нового проекта в CRM", enabled = true)
    void createNewProjectTest() throws InterruptedException {
        new MainPage(driver).openMyProjectsPage();
        new MyProjectsPage(driver).setCreateProject();
        new CreateProjectPage(driver).fillProjectName("Pr22")
                .fillOrganization("1234")
                .selectBusinessUnit("Research & Development")
                .selectCurator("Applanatest Applanatest Applanatest")
                .selectProjectLeader("Applanatest1 Applanatest1 Applanatest1")
                .selectProjectManager("Амелин Владимир")
                .saveAndCloseButton.click();

        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(new CreateProjectPage(driver).requestSuccessLocator)));

        assertThat(new CreateProjectPage(driver).requestSuccess, isDisplayed());
    }

    @Test(description = "Создание контактного лица в CRM", enabled = true)
    void createContactPerson() throws InterruptedException {

        new MainPage(driver).openAllContactPersonsPage();
        new AllContactPersonsPage(driver).setCreateContactPerson();
        new CreateContactPersonPage(driver)
                .fillLastName("Петров")
                .fillFirstName("Петр")
                .fillOrganization("1234")
                .fillJobTitle("специалист")
                .saveAndCloseButton.click();

        webDriverWait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath(new CreateContactPersonPage(driver).requestSuccessLocator)));

        assertThat(new CreateContactPersonPage(driver).requestSuccess, isDisplayed());
    }
}
