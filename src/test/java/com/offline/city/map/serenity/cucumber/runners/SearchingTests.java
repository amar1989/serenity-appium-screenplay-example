package com.offline.city.map.serenity.cucumber.runners;

;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.core.webdriver.RemoteDriver;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.thucydides.core.annotations.Managed;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features   = "src/test/resources/features/search_locations",
        glue       = "com.offline.city.map.serenity.cucumber.steps",
        plugin     = {"pretty"},
        strict     = true,
        monochrome = true

)
public class SearchingTests {

    @Managed(driver = "appium")
    public RemoteDriver driver;

}
