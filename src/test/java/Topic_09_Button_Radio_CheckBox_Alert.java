import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_09_Button_Radio_CheckBox_Alert {
    By buttonClickJsAlertBy = By.xpath("//button[contains(text(),'Click for JS Alert')]");
    By buttonClickJsConfirmBy = By.xpath("//button[contains(text(),'Click for JS Confirm')]");
    By buttonClickJsPromptBy = By.xpath("//button[contains(text(),'Click for JS Prompt')]");
    WebDriver driver;
    JavascriptExecutor js = (JavascriptExecutor) driver;
    @BeforeTest
    public void setUpClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC01(){
        //Truy cap live.guru99
        driver.get("http://live.guru99.com/");
        // Click my account button
        WebElement buttonMyAccount = driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]"));
        clickByJs(buttonMyAccount);
        WebElement buttonCreateAccount = driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]"));
        clickByJs(buttonCreateAccount);
    }


    @Test
    public void TC04() {
      driver.get("https://automationfc.github.io/basic-form/index.html");
      WebElement buttonClickJsAlert = driver.findElement(buttonClickJsAlertBy);
      clickByJs(buttonClickJsAlert);
      String alertText = driver.switchTo().alert().getText();
    }

    @Test
    public void TC05() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement buttonClickJsConfirm = driver.findElement(buttonClickJsConfirmBy);
        clickByJs(buttonClickJsConfirm);
        Assert.assertEquals(driver.switchTo().alert().getText(),"I am a JS Confirm");
        driver.switchTo().alert().accept();
    }

    @Test
    public void TC06() throws InterruptedException {
        String name = "12345";
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement buttonClickJsPrompt = driver.findElement(buttonClickJsPromptBy);
        clickByJs(buttonClickJsPrompt);
        Thread.sleep(2000);
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(name);
        alert.accept();
    }

    //by pass authen
    @Test
    public void TC07() {
       //bypass authenication alert
        String url = "https://google.com/auth";
        String username = "abc";
        String password = "admin";

        //tach url ra lam 2 phan
        // 1 la "https://" 2 la phan con lai
        String[] splitUrl = url.split("//");

        //chen username va password theo format http://username:password@url
        url = splitUrl[0] + username + password + "@" + splitUrl[1];
    }
    @Test
    public void TC03() {
        driver.get("https://material.angular.io/components/radio/examples");
        WebElement radioSpring = driver.findElement(By.xpath("//div[contains(text(),'Spring')]"));
        clickByJs(radioSpring);

        driver.get("https://material.angular.io/components/checkbox/examples");
        WebElement checkButton = driver.findElement(By.xpath("//mat-checkbox[@id='mat-checkbox-1']//div[@class='mat-checkbox-inner-container']"));
        clickByJs(checkButton);
        int afterCheck = driver.findElements(By.xpath("//input[@aria-checked='true']")).size();
        System.out.println(afterCheck);
    }
    public void clickByJs(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",element);
    }




}
