package lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import lesson7.listeners.CustomLogger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static lesson6.Configuration.BASE_URL;

public class BaseTestLoginWithCookie {
    EventFiringWebDriver driver;
    WebDriverWait webDriverWait;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupBrowser() {
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new CustomLogger());
        webDriverWait = new WebDriverWait(driver, 10);
        driver.get(BASE_URL);
        loginWithCookie();
    }

    @AfterEach
    void tearDown() {
        List<LogEntry> logs = driver.manage().logs().get(LogType.BROWSER).getAll();
        driver.manage().logs().get(LogType.BROWSER).getAll().forEach(System.out::println);
        driver.quit();
    }

    public void loginWithCookie() {
        Cookie sessionCookie = driver.manage().getCookieNamed("BAPID");
        driver.manage().deleteCookie(sessionCookie);
        Cookie cookie = new Cookie("BAPID", "14ea9ee52938c869de1a00ae1e54be3f");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
    }
}
