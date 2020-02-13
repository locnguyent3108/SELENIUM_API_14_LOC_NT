import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_08_textArea_DropDown {

    WebDriver driver;
    String newEmail = RandomEmail();

    By customerID = By.xpath("//td[text()='Customer ID']/following-sibling::td");
    By customerName = By.xpath("//td[text()='Customer Name']/following-sibling::td");
    By gender = By.xpath("//td[text()='Gender']/following-sibling::td");
    By birthday = By.xpath("//td[text()='Birthdate']/following-sibling::td");
    By address = By.xpath("//td[text()='Address']/following-sibling::td");
    By city = By.xpath("//td[text()='City']/following-sibling::td");
    By state = By.xpath("//td[text()='State']/following-sibling::td");
    By pin = By.xpath("//td[text()='Pin']/following-sibling::td");
    By mobilePhone = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
    By email = By.xpath("//td[text()='Email']/following-sibling::td");

    By navEditCustomer = By.xpath("//a[contains(text(),'Edit Customer')]");
    By inputCustomerId = By.xpath("//a[contains(text(),'Edit Customer')]");
    By textBoxCustomerName = By.xpath("//input[@name='name']");
    By textBoxAddressCustomer = By.xpath("//textarea[@name='addr']");
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
        Assert.assertEquals(homePageTitle.getText(), "Guru99 Bank");
        createNewAccount();

        WebElement navNewCustomer = driver.findElement(By.xpath("//a[contains(text(),'New Customer')]"));
        navNewCustomer.click();
        AddnewCustomer("customerTest", "male", "02091992", "102 chu van an sai gon",
                "ho chi minh", "viet nam", "100000", "03371592812", newEmail, "123456789");
        VerifyCustomerInformation();
        String customerId = driver.findElement(customerID).getText();
        EditCustomerInformationById(customerId);

        Assert.assertEquals(driver.findElement(textBoxCustomerName).getAttribute("value"),"customerTest");
        Assert.assertEquals(driver.findElement(textBoxAddressCustomer).getText(), "102 chu van an sai gon");
    }



    @Test(priority = 2)
    public void TC02() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement dropdownJob1Element = driver.findElement(By.cssSelector("#job1"));
        Select dropdownJob = new Select(dropdownJob1Element);
        Assert.assertFalse(dropdownJob.isMultiple());
        dropdownJob.selectByVisibleText("Mobile Testing");
        WebElement option = dropdownJob.getFirstSelectedOption();
        Assert.assertEquals(option.getText(),"Mobile Testing");
        dropdownJob.selectByVisibleText("Manual Testing");
        option = dropdownJob.getFirstSelectedOption();
        Assert.assertEquals(option.getText(),"Manual Testing");
        dropdownJob.selectByIndex(9);
        option = dropdownJob.getFirstSelectedOption();
        Assert.assertEquals(option.getText(), "Functional UI Testing");
        Assert.assertEquals(dropdownJob.getOptions().size(), 10);
    }

    @Test(priority = 3)
    public void TC03() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        WebElement dropdownJob2Element = driver.findElement(By.cssSelector("#job2"));
        Select dropdownJob = new Select(dropdownJob2Element);
        Assert.assertTrue(dropdownJob.isMultiple());
        dropdownJob.selectByVisibleText("Automation");
        dropdownJob.selectByVisibleText("Mobile");
        dropdownJob.selectByVisibleText("Desktop");
        Assert.assertEquals(dropdownJob.getAllSelectedOptions().size(),3);
        dropdownJob.deselectAll();
        Assert.assertEquals(dropdownJob.getAllSelectedOptions().size(),0);
    }

    @Test(priority = 4)
    public void TC04() {
        By textRegister = By.cssSelector(".page-title");
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2Fregister");

        waitElementDisplay(textRegister);
        WebElement radioBtnGenderFemale = driver.findElement(By.xpath("//input[@id='gender-female']"));
        radioBtnGenderFemale.click();

        WebElement textBoxFirstName = driver.findElement(By.xpath("//input[@id='FirstName']"));
        textBoxFirstName.sendKeys("loc");

        WebElement textBoxLastName = driver.findElement(By.xpath("//input[@id='LastName']"));
        textBoxLastName.sendKeys("nguyen");

        WebElement dobDay = driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"));
        dobDay.click();
        Select selectDobDay = new Select(dobDay);
        selectDobDay.selectByVisibleText("2");
        Assert.assertEquals(selectDobDay.getOptions().size(),32);

        WebElement dobMonth = driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"));
        dobMonth.click();
        Select selectDobMonth = new Select(dobMonth);
        selectDobMonth.selectByVisibleText("May");
        Assert.assertEquals(selectDobMonth.getOptions().size(),13);

        WebElement dobYear = driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"));
        dobYear.click();
        Select selectDobYear = new Select(dobYear);
        selectDobYear.selectByVisibleText("1980");
        Assert.assertEquals(selectDobYear.getOptions().size(),112);


        WebElement textBoxEmail =  driver.findElement(By.xpath("//input[@id='Email']"));
        textBoxEmail.sendKeys("locnguyent3108@gmail.com");

        WebElement textBoxCompany = driver.findElement(By.xpath("//input[@id='Company']"));
        textBoxCompany.sendKeys("personal");

        String password = "123456789";
        WebElement textboxPassword = driver.findElement(By.xpath("//input[@id='Password']"));
        textboxPassword.sendKeys(password);

        WebElement textboxConfirmPassword = driver.findElement(By.xpath("//input[@id='ConfirmPassword']"));
        textboxConfirmPassword.sendKeys(password);

        WebElement buttonRegister = driver.findElement(By.xpath("//input[@id='register-button']"));
        buttonRegister.click();
    }

    @Test (priority = 5)
    public void TC05() throws InterruptedException {
        By optionNumber = By.xpath("//ul[@id='number-menu']/li");
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        WebElement dropdownNumber = driver.findElement(By.xpath("//span[@id='number-button']"));
        dropdownNumber.click();
        waitElementDisplay(optionNumber);
        List<WebElement> optionNumbers = driver.findElements(optionNumber);
        scrollAndClick("6", optionNumbers);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']/span[last()]")).getText(),"6");

    }
    @AfterTest
    public void tearDown() {
        driver.close();
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

    public void AddnewCustomer(String customerName, String gender, String dob,
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

    public void VerifyCustomerInformation() {
        String dob = driver.findElement(birthday).getText();
        dob = dob.replaceAll("-","");
        String actualDob = dob.substring(4,8) + dob.substring(0,4);

        Assert.assertEquals(driver.findElement(customerName).getText(), "customerTest");
        Assert.assertEquals(driver.findElement(gender).getText(), "male");
        Assert.assertEquals(driver.findElement(address).getText(), "102 chu van an sai gon");
        Assert.assertEquals(driver.findElement(city).getText(), "ho chi minh");
        Assert.assertEquals(driver.findElement(state).getText(), "viet nam");
        Assert.assertEquals(driver.findElement(pin).getText(), "100000");
        Assert.assertEquals(actualDob, "02091992");
        Assert.assertEquals(driver.findElement(mobilePhone).getText(), "03371592812");
        Assert.assertEquals(driver.findElement(email).getText(), newEmail);
    }

    private void EditCustomerInformationById(String customerId) {
        driver.findElement(navEditCustomer).click();
        WebElement textBoxCustomerId = driver.findElement(inputCustomerId);
        textBoxCustomerId.sendKeys(customerId);
        driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
    }
    public void waitElementDisplay(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void scrollAndClick(String expectNumber, List<WebElement> optionNumbers) throws InterruptedException {
        for (WebElement childNumber : optionNumbers) {
            if (childNumber.getText().equals(expectNumber) && childNumber.isDisplayed()){
                    Thread.sleep(2000);
                System.out.println("display number: " + childNumber.getText());
                childNumber.click();
                ((JavascriptExecutor) driver).executeScript("arguments[0].click()",childNumber);
            } else {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)",childNumber);
            }
        }
    }
}
