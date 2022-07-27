package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import pages.EtsyHomePage;
import utilities.ConfigReader;
import utilities.TestBase;

public class EtsyTests extends TestBase {
    @Test
    public void testEtsy(){
        driver.get(ConfigReader.getProperty("EtsyURL"));
        EtsyHomePage etsyHomePage=new EtsyHomePage();
        etsyHomePage.searchBox.sendKeys("Carpet"+ Keys.ENTER);

    }

}
