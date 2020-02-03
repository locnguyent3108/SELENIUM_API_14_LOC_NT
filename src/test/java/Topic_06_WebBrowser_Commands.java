import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {

    WebDriver driver;
    By PathMyAccount = By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]");
    By PathCreateAccount = By.xpath("//span[contains(text(),'Create an Account')]");

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
    public void verifyUrl() throws Exception {
        WebElement BtnMyAccount = driver.findElement(PathMyAccount);
        BtnMyAccount.click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

        WebElement BtnCreateAccount = driver.findElement(PathCreateAccount);
        BtnCreateAccount.click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

    }

    @Test
    public void verifyTitle() throws Exception {
        WebElement BtnMyAccount = driver.findElement(PathMyAccount);
        BtnMyAccount.click();
        Assert.assertEquals(driver.getTitle(), "Customer Login");

        WebElement BtnCreateAccount = driver.findElement(PathCreateAccount);
        BtnCreateAccount.click();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test
    public void navigateFunctions() throws Exception {
        WebElement BtnMyAccount = driver.findElement(PathMyAccount);
        BtnMyAccount.click();

        WebElement BtnCreateAccount = driver.findElement(PathCreateAccount);
        BtnCreateAccount.click();

        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

        driver.navigate().forward();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

    }

    @Test
    public void getPageSource() throws Exception {
        WebElement myAccountBtn = driver.findElement(PathMyAccount);
        myAccountBtn.click();

        String loginPageSource = driver.getPageSource();
        Boolean loginText = loginPageSource.contains("Login or Create an Account");
        Assert.assertTrue(loginText);

        WebElement BtnCreateAccount = driver.findElement(PathCreateAccount);
        BtnCreateAccount.click();
        String createAccountPageSource = driver.getPageSource();
        Boolean TxtCreateAccount = createAccountPageSource.contains("Create an Account");
        Assert.assertTrue(TxtCreateAccount);

    }

    //-----test case ELEMENT

    @Test
    public void checkElementDisplay() throws Exception {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // get Email elements
        WebElement lblEmail = driver.findElement(By.xpath("//label[contains(text(),'Email:')]"));
        Assert.assertTrue(lblEmail.isDisplayed());
        WebElement txtBoxEmail = driver.findElement(By.xpath("//input[@id='mail']"));
        Assert.assertTrue(txtBoxEmail.isDisplayed());

        //get Age elements
        WebElement lblAge = driver.findElement(By.xpath("//label[contains(text(),'18 or older')]"));
        Assert.assertTrue(lblAge.isDisplayed());
        WebElement RdAgeUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
        Assert.assertTrue(RdAgeUnder18.isDisplayed());
    }

    @Test
    public void checkElementEnabled() throws Exception {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        WebElement emailTxtBox = driver.findElement(By.xpath("//input[@id='mail']"));
        Assert.assertTrue(emailTxtBox.isEnabled());
        WebElement radioBtnAge = driver.findElement(By.xpath("//input[@id='under_18']"));
        Assert.assertTrue(radioBtnAge.isEnabled());
        WebElement educationTxtBox = driver.findElement(By.xpath("//textarea[@id='edu']"));
        Assert.assertTrue(educationTxtBox.isEnabled());
        WebElement radioInterest = driver.findElement(By.xpath("//input[@id='development']"));
        Assert.assertTrue(radioInterest.isEnabled());
        WebElement dropdownJob1 = driver.findElement(By.xpath("//select[@id='job1']"));
        Assert.assertTrue(dropdownJob1.isEnabled());
    }

    @Test
    public void checkElementSelected() throws Exception {

    }
}
