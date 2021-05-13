package lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;

public class CRMTests {

    WebDriver driver;
    WebDriverWait webDriverWait;
    private static final String BASE_URL = "https://crm.geekbrains.space/";


    @BeforeTest
    void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    void setupBrowser() {
        driver = new ChromeDriver();
       webDriverWait = new WebDriverWait(driver, 10);
       driver.get(BASE_URL);
       loginWithCookie();
    }

    @Test(description = "Создание нового проекта в CRM", enabled = false)
    void createNewProjectTest() throws InterruptedException {
        driver.get("https://crm.geekbrains.space/project/my");

        driver.findElement(By.xpath("//a[text()='Создать проект']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='crm_project[name]']")));

        driver.findElement(By.xpath("//input[@name='crm_project[name]']")).sendKeys("NewPr16");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Укажите организацию']")));

        driver.findElement(By.xpath("//span[text()='Укажите организацию']")).click();

        driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("1234");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='select2-drop']/div/input")));

        driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);


        Select selectBU = new Select(driver.findElement(By.name("crm_project[businessUnit]")));
        selectBU.selectByVisibleText("Research & Development");

        Select selectCurator = new Select(driver.findElement(By.name("crm_project[curator]")));
        selectCurator.selectByVisibleText("Applanatest Applanatest Applanatest");

        Select selectProjectLeader = new Select(driver.findElement(By.name("crm_project[rp]")));
        selectProjectLeader.selectByVisibleText("Applanatest1 Applanatest1 Applanatest1");

        Select selectProjectManager = new Select(driver.findElement(By.name("crm_project[manager]")));
        selectProjectManager.selectByVisibleText("Амелин Владимир");

        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и закрыть')]")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Проект сохранен']")));

        driver.findElement(By.xpath("//*[text()='Проект сохранен']"));
        WebElement element = driver.findElement(By.xpath("//*[text()='Проект сохранен']"));

        Assert.assertTrue(element.isDisplayed());

        System.out.println("Тест PositiveCreateProject выполнен успешно");

//        Thread.sleep(10000);

    }

    @Test(description = "Создание контактного лица в CRM", enabled = true)
    void createContactPerson() throws InterruptedException {
        driver.get("https://crm.geekbrains.space/contact/");

        Actions actions = new Actions(driver);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='title' and text()='Контрагенты']")));
        WebElement contragentsMenuItem = driver.findElement(By.xpath("//span[@class='title' and text()='Контрагенты']"));
        actions.moveToElement(contragentsMenuItem).perform();

        driver.findElement(By.xpath("//span[text()='Контактные лица']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/a[contains(text(),'Создать контактное лицо')]")));

        driver.findElement(By.xpath("//div/a[contains(text(),'Создать контактное лицо')]")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("crm_contact[lastName]")));
        driver.findElement(By.name("crm_contact[lastName]")).sendKeys("Петров");

        driver.findElement(By.name("crm_contact[firstName]")).sendKeys("Петр");

        driver.findElement(By.xpath("//span[text()='Укажите организацию']/..")).click();

        driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("1234");

        webDriverWait.until(ExpectedConditions.textToBePresentInElementValue(By.xpath("//*[@id='select2-drop']/div/input"), "1234"));

        Thread.sleep(5000);// здесь оставила, т.к. нужна пауза, иначе "специалист"  вводится в это же поле "Организация"

        driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("crm_contact[jobTitle]")));

        driver.findElement(By.name("crm_contact[jobTitle]")).sendKeys("специалист");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Сохранить и закрыть')]")));

        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и закрыть')]")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Контактное лицо сохранено']")));
        driver.findElement(By.xpath("//*[text()='Контактное лицо сохранено']"));

        WebElement element = driver.findElement(By.xpath("//*[text()='Контактное лицо сохранено']"));

        Assert.assertTrue(element.isDisplayed());

        System.out.println("Тест PositiveCreateContactPerson выполнен успешно");

//        Thread.sleep(5000);
    }

    @Test (description = "Создание командировки", enabled = true)
    void createBusinessTrip() throws InterruptedException {
        driver.get("https://crm.geekbrains.space/business-trip/create/");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("crm_business_trip[businessUnit]")));

        Select selectBU = new Select(driver.findElement(By.name("crm_business_trip[businessUnit]")));
        selectBU.selectByVisibleText("Research & Development");

        driver.findElement(By.name("crm_business_trip[company]")).sendKeys("1234");

        Select selectExpenditure = new Select(driver.findElement(By.name("crm_business_trip[class]")));
        selectExpenditure.selectByVisibleText("Административная");

        driver.findElement(By.xpath("//a[@class='btn add-list-item']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-name='field__description']")));

        driver.findElement(By.xpath("//input[@data-name='field__description']")).sendKeys("Ведение переговоров");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(), 'Выберите сотрудника')]/..")));

        driver.findElement(By.xpath("//span[contains(text(), 'Выберите сотрудника')]/..")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='select2-drop-mask']/../div/div/input")));

        driver.findElement(By.xpath("//*[@id='select2-drop-mask']/../div/div/input")).sendKeys("Slave");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.findElement(By.xpath("//*[@id='select2-drop-mask']/../div/div/input")).sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//div[@data-ftid='crm_business_trip_foreignUsers']/../div/a")).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("crm_business_trip[foreignUsers][0][description]")));

        driver.findElement(By.name("crm_business_trip[foreignUsers][0][description]")).sendKeys("Ведение переговоров");

        driver.findElement(By.xpath("//input[@data-name='field__user']")).sendKeys("Петров");

        driver.findElement(By.name("crm_business_trip[arrivalCity]")).sendKeys("Ростов-на-Дону");

        driver.findElement(By.xpath("//label[contains(text(),'Планируемая дата выезда')]//" +
                "ancestor::div[@class='control-group control-group-date']/div/input")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='15']")));

        driver.findElement(By.xpath("//a[text()='15']")).click();

        driver.findElement(By.xpath("//label[contains(text(),'Планируемая дата возвращения')]//" +
                "ancestor::div[@class='control-group control-group-date']/div[@class='controls']/input")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='20']")));

        driver.findElement(By.xpath("//a[text()='20']")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Сохранить и закрыть')]")));

        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и закрыть')]")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Командировка сохранена']")));

        driver.findElement(By.xpath("//*[text()='Командировка сохранена']"));

        WebElement element = driver.findElement(By.xpath("//*[text()='Командировка сохранена']"));

        Assert.assertTrue(element.isDisplayed());

//        Thread.sleep(10000);

    }

    @Test(description = "Создание организации", enabled = true)
    void createOrganization() {
        driver.get("https://crm.geekbrains.space/company/create");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.findElement(By.name("crm_company[name]")).sendKeys("Первая");

        driver.findElement(By.name("crm_company[shortName]")).sendKeys("Перв");

        driver.findElement(By.name("crm_company[companyType][]")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("crm_company[industry]")));

        Select selectIndustry = new Select(driver.findElement(By.name("crm_company[industry]")));
        selectIndustry.selectByVisibleText("Банковская деятельность");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Добавить адрес')]")));

        driver.findElement(By.xpath("//a[contains(text(),'Добавить адрес')]")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("crm_company[addresses][1][street]")));

        driver.findElement(By.name("crm_company[addresses][1][street]")).sendKeys("Крылова");

        driver.findElement(By.name("crm_company[addresses][1][building]")).sendKeys("2");

        driver.findElement(By.xpath("//a[contains(text(),'Добавить телефон')]")).click();

        Select selectPhoneType = new Select(driver.findElement(By.name("crm_company[phones][1][type]")));
        selectPhoneType.selectByVisibleText("Рабочий");
        driver.findElement(By.name("crm_company[phones][1][prefixCode]")).sendKeys("900");
        driver.findElement(By.name("crm_company[phones][1][phone]")).sendKeys("12345");

        Select selectManager = new Select(driver.findElement(By.name("crm_company[manager]")));
        selectManager.selectByVisibleText("Applanatest Applanatest Applanatest");

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'Сохранить и закрыть')]")));

        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и закрыть')]")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Организация сохранена']")));

        driver.findElement(By.xpath("//*[text()='Организация сохранена']"));

        WebElement element = driver.findElement(By.xpath("//*[text()='Организация сохранена']"));

        Assert.assertTrue(element.isDisplayed());

    }

    @AfterMethod
    void closeBrowser() {
        driver.quit();
    }

    private void loginWithCookie() {
        Cookie sessionCookie = driver.manage().getCookieNamed("BAPID");
        driver.manage().deleteCookie(sessionCookie);
        Cookie cookie = new Cookie("BAPID", "2b18a283e9ec5f218feb24ac692bcead");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }

}
