import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_04_Xpath_Css {

    WebDriver driver;
    @BeforeTest
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void setupTest() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("http://live.demoguru99.com/");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01_LoginWithEmptyEmailAndPassword() throws InterruptedException {
        WebElement myAccountBtn = driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]"));
        myAccountBtn.click();

        WebElement loginButton = driver.findElement(By.xpath ("//button[@title= 'Login']"));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        //Assert result
        List<WebElement> errorsMessages = driver.findElements(By.xpath("//div[text()='This is a required field.']"));

        String expectedErrorMessage = "This is a required field";
        int countExpectedError = 2;
        Assert.assertEquals(countExpectedError,errorsMessages.size());
    }

    @Test
    public void TC_02_LoginWithInvalidEmail() throws InterruptedException {
        WebElement myAccountBtn = driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]"));
        myAccountBtn.click();

        WebElement emailTxtField = driver.findElement(By.xpath("//input[@id='email']"));
        WebElement passwordTxtField = driver.findElement(By.xpath("//input[@id='pass']"));
        emailTxtField.sendKeys("123123@123.123");
        passwordTxtField.sendKeys("123");

        WebElement loginButton = driver.findElement(By.xpath ("//button[@title= 'Login']"));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        WebElement EmailInvalidText = driver.findElement(By.xpath ("//div[@id='advice-validate-email-email']"));
        Assert.assertEquals(EmailInvalidText.getText(),"Please enter a valid email address. For example johndoe@domain.com.\n");
    }

    @Test
    public void TC_03_LoginWithPasswordLessThan6Chars() throws InterruptedException {
        WebElement myAccountBtn = driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]"));
        myAccountBtn.click();

        WebElement emailTxtField = driver.findElement(By.xpath("//input[@id='email']"));
        WebElement passwordTxtField = driver.findElement(By.xpath("//input[@id='pass']"));
        emailTxtField.sendKeys("automation@gmail.com");
        passwordTxtField.sendKeys("123");

        WebElement loginButton = driver.findElement(By.xpath ("//button[@title= 'Login']"));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        WebElement passwordInvalidText = driver.findElement(By.xpath ("//div[@id='advice-validate-password-pass']"));
        Assert.assertEquals(passwordInvalidText.getText(),"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_LoginWithInCorrectPassword() throws InterruptedException {
        WebElement myAccountBtn = driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]"));
        myAccountBtn.click();

        WebElement emailTxtField = driver.findElement(By.xpath("//input[@id='email']"));
        WebElement passwordTxtField = driver.findElement(By.xpath("//input[@id='pass']"));
        emailTxtField.sendKeys("automation@gmail.com");
        passwordTxtField.sendKeys("123123");

        WebElement loginButton = driver.findElement(By.xpath ("//button[@title= 'Login']"));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        WebElement passwordInvalidText = driver.findElement(By.xpath ("//span[contains(text(),'Invalid login or password.')]"));
        Assert.assertEquals(passwordInvalidText.getText(),"Invalid login or password.");

    }

    @Test
    public void TC_05_LoginWithValidCredentials() throws InterruptedException {

        WebElement myAccountBtn = driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]"));
        myAccountBtn.click();

        WebElement emailTxtField = driver.findElement(By.xpath("//input[@id='email']"));
        WebElement passwordTxtField = driver.findElement(By.xpath("//input[@id='pass']"));
        emailTxtField.sendKeys("automation_13@gmail.com");
        passwordTxtField.sendKeys("123123");

        WebElement loginButton = driver.findElement(By.xpath ("//button[@title= 'Login']"));
        loginButton.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/index/");
    }

    @Test
    public void TC_06_CreateNewAccount() throws InterruptedException {
        WebElement myAccountBtn = driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]"));
        myAccountBtn.click();
        WebElement createAccountBtn = driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]"));
        createAccountBtn.click();
        Register("loc", "nguyen the", RandomEmail(),"123123123");

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        WebElement registerSuccess = driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']"));
        Assert.assertTrue(registerSuccess.isDisplayed());
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public String RandomEmail() {
        String partialEmail = "automation_";
        int temp = (int) (Math.random() * 1000);
        return partialEmail + temp + "@gmail.com";
    }

    public void Register(String firstname, String lastname, String email, String password) {
        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstname);
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastname);
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='is_subscribed']")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Register')]")).click();
    }
}
