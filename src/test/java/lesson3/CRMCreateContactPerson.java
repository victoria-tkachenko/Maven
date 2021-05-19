package lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CRMCreateContactPerson {

    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL = "https://crm.geekbrains.space/user/login";

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        login();

        Actions actions = new Actions(driver);
        WebElement contragentsMenuItem = driver.findElement(By.xpath("//span[text()='Контрагенты']"));
        actions.moveToElement(contragentsMenuItem).perform();

        driver.findElement(By.xpath("//span[text()='Контактные лица']")).click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/a[contains(text(),'Создать контактное лицо')]")));

        driver.findElement(By.xpath("//div/a[contains(text(),'Создать контактное лицо')]")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.name("crm_contact[lastName]")));
        driver.findElement(By.name("crm_contact[lastName]")).sendKeys("Петров");

        driver.findElement(By.name("crm_contact[firstName]")).sendKeys("Петр");

        driver.findElement(By.xpath("//span[text()='Укажите организацию']/..")).click();

        driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys("1234");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//*[@id='select2-drop']/div/input")).sendKeys(Keys.ENTER);

        driver.findElement(By.name("crm_contact[jobTitle]")).sendKeys("специалист");

        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и закрыть')]")).click();

        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Контактное лицо сохранено']")));
        driver.findElement(By.xpath("//*[text()='Контактное лицо сохранено']"));

        System.out.println("Тест PositiveCreateContactPerson выполнен успешно");

        Thread.sleep(5000);

        driver.quit();

    }

    private static void login() {
        driver.get(LOGIN_PAGE_URL);
        driver.findElement(By.id("prependedInput")).sendKeys("Applanatest1");
        driver.findElement(By.id("prependedInput2")).sendKeys("Student2020!");
        driver.findElement(By.id("_submit")).click();

    }
}

