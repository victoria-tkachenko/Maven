package lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import lesson6.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    LoginPage loginPage;

    @BeforeTest
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeTest
    public void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 5);
        loginPage = new LoginPage(driver);
    }

    @AfterTest
    void tearDown() {
        driver.quit();
    }
}
