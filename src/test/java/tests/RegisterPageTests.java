package tests;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ProductPage;
import pages.RegisterPage;
import tests.util.DataProviders;

public class RegisterPageTests extends TestBase{

    RegisterPage registerPage;
    ProductPage productPage;

    @BeforeMethod
    public void pageInit(){
        registerPage = PageFactory.initElements(driver, RegisterPage.class);
        productPage = PageFactory.initElements(driver, ProductPage.class);

    }
    @Test
    public void userCanRegisterPositiveTest() throws InterruptedException {
        registerPage.clickLoginorRegisterButton();
        registerPage.inputInEmailAddress();
        registerPage.inputInPassword();
        registerPage.inputInConfirmPassword();
        registerPage.clickRegisterButton();
        Thread.sleep(5000);
        Assert.assertTrue(productPage.isItMessageProductPage());
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "usingCSVFile")
    public void registerWithNotValidEmailNegativeTest(String email) throws InterruptedException {
        registerPage.clickLoginorRegisterButton();
        registerPage.inputInEmailAddressNotValid(email);
        registerPage.inputInPassword();
        registerPage.inputInConfirmPassword();
        registerPage.clickRegisterButton();
        Thread.sleep(5000);
        Assert.assertTrue(registerPage.noTransitionToAnotherPage());
        //Assert.assertTrue(registerPage.errorMessage());
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "usingCSVFile1")
    public void registerWithNotValidPasswordNegativeTest(String password) throws InterruptedException {
        registerPage.clickLoginorRegisterButton();
        registerPage.inputInEmailAddress();
        registerPage.inputInPasswordNotValid(password);
        registerPage.inputInConfirmPasswordNotValid(password);
        registerPage.clickRegisterButton();
        Thread.sleep(5000);
        Assert.assertTrue(registerPage.errorMessage());
    }

    @Test
    public void registerWithNotValidConfirmPasswordNegativeTest() throws InterruptedException {
        registerPage.clickLoginorRegisterButton();
        registerPage.inputInEmailAddress();
        registerPage.inputInPassword();
        registerPage.inputInConfirmPasswordNotValid1();
        registerPage.clickRegisterButton();
        Thread.sleep(5000);
        Assert.assertTrue(registerPage.errorMessage());
    }
    @Test
    public void registerWithTwoAccountsNegativeTest() throws InterruptedException {
        registerPage.clickLoginorRegisterButton();
        registerPage.inputInEmailAddressAlreadyExists();
        registerPage.inputInPassword();
        registerPage.inputInConfirmPassword();
        registerPage.clickRegisterButton();
        Thread.sleep(5000);
        Assert.assertTrue(registerPage.errorMessage());
    }
}