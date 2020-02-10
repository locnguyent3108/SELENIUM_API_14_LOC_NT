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

import java.util.concurrent.TimeUnit;

public class Topic_06_WebBrowser_Commands {

    WebDriver driver;
    By PathMyAccount = By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]");
    By PathCreateAccount = By.xpath("//span[contains(text(),'Create an Account')]");
    

    @BeforeTest
    public void setUpClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }


    @Test(priority = 1)
    public void verifyUrl() throws Exception {
        driver.get("http://live.demoguru99.com/");

        WebElement BtnMyAccount = driver.findElement(PathMyAccount);
        BtnMyAccount.click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

        WebElement BtnCreateAccount = driver.findElement(PathCreateAccount);
        BtnCreateAccount.click();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

    }

    @Test(priority = 2)
    public void verifyTitle() throws Exception {
        driver.get("http://live.demoguru99.com/");

        WebElement BtnMyAccount = driver.findElement(PathMyAccount);
        BtnMyAccount.click();
        Assert.assertEquals(driver.getTitle(), "Customer Login");

        WebElement BtnCreateAccount = driver.findElement(PathCreateAccount);
        BtnCreateAccount.click();
        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }

    @Test(priority = 3)
    public void navigateFunctions() throws Exception {
        driver.get("http://live.demoguru99.com/");

        WebElement BtnMyAccount = driver.findElement(PathMyAccount);
        BtnMyAccount.click();

        WebElement BtnCreateAccount = driver.findElement(PathCreateAccount);
        BtnCreateAccount.click();

        driver.navigate().back();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

        driver.navigate().forward();
        Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

    }

    @Test(priority = 4)
    public void getPageSource() throws Exception {
        driver.get("http://live.demoguru99.com/");

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

    @Test(priority = 5)
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

    @Test(priority = 6)
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

    @Test(priority = 7)
    public void checkElementSelected() throws Exception {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //
        WebElement RdAgeUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
        RdAgeUnder18.click();
        Assert.assertTrue(RdAgeUnder18.isSelected());

        //
        WebElement Development = driver.findElement(By.xpath("//input[@id='development']"));
        Development.click();
        Assert.assertTrue(Development.isSelected());

        //
        Development.click();
        Assert.assertFalse(Development.isSelected());
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.close();
    }
}
