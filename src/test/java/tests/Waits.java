package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class Waits {

    WebDriver driver;
    WebDriverWait explicitlyWait;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

    }
    @Test
    public void expWait(){
        WebElement addRemoveBtn = driver.findElement(By.xpath("//form[@id='checkbox-example']//button"));
        WebElement checkboxBtn = driver.findElement(By.xpath("//div[@id='checkbox']//input"));



        checkboxBtn.click();
        addRemoveBtn.click();

        explicitlyWait = new WebDriverWait(driver, 10);

        String expectedMessage = explicitlyWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message"))).getText();


        Assert.assertEquals(expectedMessage, "It's gone!");



    }
    @Test
    public void fluWait(){

        WebElement enableBtn = driver.findElement(By.xpath("//form[@id='input-example']/button"));
        enableBtn.click();
        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(20,TimeUnit.SECONDS)
                .pollingEvery(5,TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement actualMessage = fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.id("message"));
            }
        });

        Assert.assertEquals(actualMessage.getText(),"It's enabled!");



    }

    @AfterMethod
    public void tearDown(){
        driver.quit();

    }



}
