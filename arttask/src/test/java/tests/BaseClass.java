package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import tests.WebDriverListener;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseClass {
    protected EventFiringWebDriver driver;
    protected Action actions;

    private WebDriver getDriver() {
        try {
            System.setProperty("webdriver.chrome.driver", Paths.get(BaseClass.class.getResource("/chromedriver.exe").toURI()).toFile().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return new ChromeDriver();
    }

    @BeforeClass
    public void setUp() {
        WebDriver webDriver = getDriver();
        driver = new EventFiringWebDriver(webDriver);
        WebDriverListener eventListener = new WebDriverListener();
        driver.register(eventListener);
        actions = new Action(driver);
    }
}



