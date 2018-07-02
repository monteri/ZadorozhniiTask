package tests;

import org.testng.annotations.Test;

public class TestClass extends BaseClass{
    private String URL = "http://prestashop-automation.qatestlab.com.ua/ru/";
    private String KEYWORD = "dress";

    @Test
    public void currencyAssertion(){
        actions.openPage(URL);
        actions.compareCurrencies();
    }

    @Test(dependsOnMethods = { "currencyAssertion" })
    public void searchAssertion(){
        actions.setUSDCurrency();
        actions.sendWordAndSubmit(KEYWORD);
        actions.numberOfProductsAssertion();
    }

    @Test(dependsOnMethods = { "searchAssertion" })
    public void sortAssertion(){
        actions.setSort();
        actions.checkSortedPrices();
    }

    @Test(dependsOnMethods = { "sortAssertion" })
    public void discountAssertion(){
        actions.dicountCheck();
    }
}
