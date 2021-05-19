package lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Feature;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@Feature("Создать новый проект")
public class CRMCreateProject {

    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();

        login();

        driver.get("https://crm.geekbrains.space/project/my");

        driver.findElement(By.xpath("//a[text()='Создать проект']")).click();

        Thread.sleep(5000);

        driver.findElement(By.xpath("//input[@name='crm_project[name]']")).sendKeys("NewPr11");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//span[text()='Укажите организацию']")).click();

        driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("1234");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//input[@name='crm_project[type]' and contains(@data-name, 'field__1')]")).click();
        //у этого поля баг, в версии браузера 90.0.4430.93 выпадающий список отсутствует, поэтому контактное лицо
        // выбрать не могу. Очередной баг - это то, что система позволяет создать проект без выбора контактного лица,
        // хотя поле отмеченно как обязательное к заполнению.
//        driver.findElement(By.xpath("//select[@name=\"crm_project[contactMain]\"]/option[3]")).click();


        Select selectBU = new Select(driver.findElement(By.name("crm_project[businessUnit]")));
        selectBU.selectByVisibleText("Research & Development");

        Select selectCurator = new Select(driver.findElement(By.name("crm_project[curator]")));
        selectCurator.selectByVisibleText("Applanatest Applanatest Applanatest");

        Select selectProjectLeader = new Select(driver.findElement(By.name("crm_project[rp]")));
        selectProjectLeader.selectByVisibleText("Applanatest1 Applanatest1 Applanatest1");

        Select selectProjectManager = new Select(driver.findElement(By.name("crm_project[manager]")));
        selectProjectManager.selectByVisibleText("Амелин Владимир");

        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и закрыть')]")).click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 7);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Проект сохранен']")));

        driver.findElement(By.xpath("//*[text()='Проект сохранен']"));

        System.out.println("Тест PositiveCreateProject выполнен успешно");

        Thread.sleep(10000);

        driver.quit();

    }

    private static void login() {
        driver.get(LOGIN_PAGE_URL);
        driver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.id("_submit")).click();

    }

    private static void loginWithCookie() {

    }
}
