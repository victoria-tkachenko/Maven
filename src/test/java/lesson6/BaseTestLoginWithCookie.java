package lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import static lesson6.Configuration.BASE_URL;

public class BaseTestLoginWithCookie {
    WebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeTest
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 10);
        driver.get(BASE_URL);
        loginWithCookie();
    }

    @AfterMethod
    void tearDown() {
        driver.quit();
    }

    public void loginWithCookie() {
        Cookie sessionCookie = driver.manage().getCookieNamed("BAPID");
        driver.manage().deleteCookie(sessionCookie);
        Cookie cookie = new Cookie("BAPID", "49d10fe308efd43828cb1352cf30918f");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }
}
