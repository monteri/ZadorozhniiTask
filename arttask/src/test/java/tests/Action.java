package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Action {
    private Actions act;
    private WebDriver driver;
    private WebDriverWait wait;

    public Action(WebDriver driver) {
        this.driver = driver;
        act = new Actions(this.driver);
        wait = new WebDriverWait(driver, 30);
    }

    public void openPage(String url){
        driver.get(url);
    }

    public void compareCurrencies(){
        // Chosen currency on the page
        WebElement currency = driver.findElement(By.cssSelector("#_desktop_currency_selector > div > span.expand-more._gray-darker.hidden-sm-down"));
        WebElement divCover = driver.findElement(By.className("products"));
        // Products currencies
        List<WebElement> products = divCover.findElements(By.cssSelector("span.price"));
        for (WebElement element : products) {
            // Comparing chosen currency with products currencies
            Assert.assertEquals(element.getText().charAt(element.getText().length() - 1), currency.getText().charAt(currency.getText().length() - 1), "Currency is not equal!");
        }
    }

    public void setUSDCurrency(){
        WebElement currency = driver.findElement(By.cssSelector("#_desktop_currency_selector > div > span.expand-more._gray-darker.hidden-sm-down"));
        currency.click();
        WebElement dollar = driver.findElement(By.cssSelector("#_desktop_currency_selector > div > ul > li:nth-child(3) > a"));
        waitForContentLoad(dollar);
        dollar.click();
    }

    public void sendWordAndSubmit(String key){
        WebElement field = driver.findElement(By.cssSelector("#search_widget > form > input.ui-autocomplete-input"));
        waitForContentLoad(field);
        field.sendKeys(key);
        field.submit();
    }

    public void numberOfProductsAssertion(){
        WebElement productResult = driver.findElement(By.cssSelector("div#js-product-list-top p"));
        List<WebElement> numberOfProducts = driver.findElements(By.cssSelector("div#js-product-list div.thumbnail-container"));
        Assert.assertEquals(numberOfProducts.size(),
                Integer.parseInt(productResult.getText().substring(9, productResult.getText().length() - 1)),
                "The displayed number of products is not equal to the actual number products!");
    }

    public void setSort() {
        WebElement sortMenu = driver.findElement(By.className("select-title"));
        sortMenu.click();
        WebElement sortType = driver.findElement(By.cssSelector("#js-product-list-top > div:nth-child(2) > div > div > div > a:nth-child(5)"));
        sortType.click();
    }

    public void checkSortedPrices(){
        List<WebElement> priceList = driver.findElements(By.cssSelector("div.product-price-and-shipping > span:nth-child(1)"));
        wait.until(ExpectedConditions.invisibilityOfAllElements(priceList));
        priceList = driver.findElements(By.cssSelector("div.product-price-and-shipping > span:nth-child(1)"));
        for (int i = 0; i < priceList.size() - 1; i++) {
            Assert.assertTrue(Float.parseFloat(priceList.get(i).getText()
                            .substring(0, priceList.get(i).getText().length() - 2)
                            .replace(',', '.'))
                            >= Float.parseFloat(priceList.get(i + 1)
                            .getText().substring(0, priceList.get(i).getText().length() - 2)
                            .replace(',', '.')));
        }
    }

    public void dicountCheck(){
        List<WebElement> priceDiv = driver.findElements(By.cssSelector("div.product-price-and-shipping"));
        for (WebElement element : priceDiv) {
            if (element.findElements(By.className("regular-price")).size() != 0
                    && element.findElements(By.className("discount-percentage")).size() != 0){
                WebElement regularPrice = element.findElements(By.className("regular-price")).get(0);
                float priceWithoutDiscount = Float.parseFloat(regularPrice.getText().substring(0, regularPrice.getText().length() - 2).replace(',', '.'));
                WebElement discountPercentage = element.findElements(By.className("discount-percentage")).get(0);
                float percentage = Float.parseFloat(discountPercentage.getText().substring(1, discountPercentage.getText().length() - 1).replace(',', '.'));
                WebElement price = element.findElements(By.className("price")).get(0);
                float actualPrice = Float.parseFloat(price.getText().substring(0, price.getText().length() - 2).replace(',', '.'));
                float expected = (float) (Math.rint(100.0 * priceWithoutDiscount * (100.0 - percentage)/100) / 100.0);
                Assert.assertEquals(actualPrice, expected);
            }
        }
    }

    public void waitForContentLoad(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
