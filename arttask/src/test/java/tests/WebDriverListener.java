package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.Reporter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WebDriverListener implements WebDriverEventListener {
    @Override
    public void beforeAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {
        log("Navigate to " + url + "<br>");
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {

    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {
        log("Navigate back" + "<br>");
    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
        log("Current URL: " + driver.getCurrentUrl() + "<br>");
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
        log("Navigate forward" + "<br>");
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {
        log("Current URL: " + driver.getCurrentUrl() + "<br>");
    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {
        log("Refresh page" + "<br>");
    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        log("Current URL: " + driver.getCurrentUrl() + "<br>");
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        log("Search for element " + by.toString() + "<br>");
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        if (element != null) {
            log("Found element " + element.getTagName() + "<br>");
        }
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        log("Click on element " + element.getTagName() + "<br>");
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        String value = Arrays.stream(keysToSend).map(CharSequence::toString).collect(Collectors.joining());
        log(String.format("Change value of %s: %s" + "<br>", element.getTagName(), value));
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        log(String.format("Changed element " + element.getTagName() + "<br>"));
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {
        log("Execute script: " + script + "<br>");
    }

    @Override
    public void afterScript(String script, WebDriver driver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {

    }

    public static void log(String message) {
        Reporter.log(String.format("[%-12s] %s", LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME), message));
    }
}
