import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_08_textArea_DropDown {

    WebDriver driver;
    @BeforeTest
    public void setUpClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void TC01() {
        driver.get("http://demo.guru99.com/v4/");
        WebElement homePageTitle = driver.findElement(By.xpath("//h2[@class='barone']"));
        Assert.assertEquals(homePageTitle.getText(),"Guru99 Bank");

        WebElement registerButton = driver.findElement(By.xpath("//a[contains(text(),'here')]"));
        registerButton.click();

        /**
         * wait register page is displayed
         */

        By registerTitle = By.xpath("//h2[contains(text(),'Enter your email address to get')]");
        waitElementDisplay(registerTitle);
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }

    public void waitElementDisplay(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
