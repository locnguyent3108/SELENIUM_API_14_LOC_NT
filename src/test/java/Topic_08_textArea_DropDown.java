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
    By customerID = By.xpath("//td[text()='Customer ID']/following-sibling::td");
    By customerName = By.xpath("//td[text()='Customer Name']/following-sibling::td");
    By gender = By.xpath("//td[text()='Gender']/following-sibling::td");
    By Birthday = By.xpath("//td[text()='Birthdate']/following-sibling::td");
    By address = By.xpath("//td[text()='Address']/following0sibling::td");
    By city = By.xpath("//td[text()='City']/following0sibling::td");
    By state = By.xpath("//td[text()='State']/following0sibling::td");
    By pin = By.xpath("//td[text()='Pin']/following0sibling::td");
    By mobilePhone = By.xpath("//td[text()='Mobile No.']/following0sibling::td");
    By email = By.xpath("//td[text()='Email']/following0sibling::td");

    @BeforeTest
    public void setUpClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test(priority = 1)
    public void TC01() {
        driver.get("http://demo.guru99.com/v4/");
        WebElement homePageTitle = driver.findElement(By.xpath("//h2[@class='barone']"));
        Assert.assertEquals(homePageTitle.getText(),"Guru99 Bank");
        createNewAccount();

        WebElement navNewCustomer = driver.findElement(By.xpath("//a[contains(text(),'New Customer')]"));
        navNewCustomer.click();
        addNewCustomer("customerTest","male","02091992","102 chu van an sai gon",
                "ho chi minh", "viet nam", "100000", "03371592812", RandomEmail(),"123456789");

        Assert.assertEquals(driver.findElement(customerName).getText(),"customerTest");
        Assert.assertEquals(driver.findElement(gender).getText(),"male");
        Assert.assertEquals(driver.findElement(address).getText(), "102 chu van an sai gon");
        Assert.assertEquals(driver.findElement(city).getText(), "ho chi minh");
        Assert.assertEquals(driver.findElement(state).getText(), "viet nam");
        Assert.assertEquals(driver.findElement(pin).getText(), "100000");
        Assert.assertEquals(driver.findElement(mobilePhone).getText(), "03371592812");




    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }

    public void waitElementDisplay(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public String RandomEmail() {
        String partialEmail = "automation_";
        int temp = (int) (Math.random() * 1000);
        return partialEmail + temp + "@gmail.com";
    }


    public void createNewAccount() {
        WebElement registerButton = driver.findElement(By.xpath("//a[contains(text(),'here')]"));
        registerButton.click();

        /**
         * wait register page is displayed
         */

        By registerTitle = By.xpath("//h2[contains(text(),'Enter your email address to get')]");
        waitElementDisplay(registerTitle);

        WebElement txtBoxEmail = driver.findElement(By.xpath("//input[@name='emailid']"));
        txtBoxEmail.sendKeys(RandomEmail());

        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        /**
         * get username and password
         */
        String userName = driver.findElement(By.xpath("//td[contains(text(),'User ID')]/following-sibling::td")).getText();
        String password = driver.findElement(By.xpath("//td[contains(text(),'Password')]/following-sibling::td")).getText();
        driver.get("http://demo.guru99.com/v4/");

        /**
         * login with new account (mngr245368 / tYnYmev)
         */
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        WebElement btnLogin = driver.findElement(By.xpath("//input[@name='btnLogin']"));
        btnLogin.click();
        System.out.println("");
    }

    public void addNewCustomer(String customerName, String gender, String dob,
                               String address, String city, String state, String PIN, String phoneNumber,
                               String email, String password) {

        WebElement txtBoxCustomerName = driver.findElement(By.xpath("//input[@name='name']"));
        txtBoxCustomerName.sendKeys(customerName);

        if (gender == "male") {
            WebElement rdGender = driver.findElement(By.xpath("//input[@value='m']"));
            rdGender.click();
        } else {
            WebElement rdGender = driver.findElement(By.xpath("//input[@value='f']"));
            rdGender.click();
        }

        driver.findElement(By.xpath("//input[@id='dob']"))
                .sendKeys(dob);

        driver.findElement(By.xpath("//textarea[@name='addr']"))
                .sendKeys(address);

        driver.findElement(By.xpath("//input[@name='city']"))
                .sendKeys(city);

        driver.findElement(By.xpath("//input[@name='state']"))
                .sendKeys(state);

        driver.findElement(By.xpath("//input[@name='pinno']"))
                .sendKeys(PIN);

        driver.findElement(By.xpath("//input[@name='telephoneno']"))
                .sendKeys(phoneNumber);

        driver.findElement(By.xpath("//input[@name='emailid']"))
                .sendKeys(email);

        driver.findElement(By.xpath("//input[@name='password']"))
                .sendKeys(password);

        driver.findElement(By.xpath("//input[@name='sub']")).click();
    }
}
