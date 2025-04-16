package praktikum;

import api.UserAPI;
import api.dto.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.net.HttpURLConnection;

public class LoginTest {
    private final UserAPI api = new UserAPI();
    private final User user = User.random();
    private String accessToken;

    @Rule
    public DriverRule factory = new DriverRule();

    @Before
    public void prepareTestData() {
        accessToken = api.create(user).statusCode(HttpURLConnection.HTTP_OK)
                .extract().path("accessToken");
    }

    @After
    public void clearTestData() {
        if (accessToken != null) api.delete(accessToken);
    }


    @Test
    @DisplayName("Вход по кнопке 'Войти' на странице регистрации")
    public void registrationLoginTest() {
        WebDriver driver = factory.getDriver();
        RegisterPage page = new RegisterPage(driver);

        page.openRegisterPage();

        LoginPage loginPage = page.clickLoginButton();
        MainPage mainPage = loginPage.login(user);
        //Ожидание появления кнопки оформить заказ после логина
        mainPage.waitForLoadOrderButton();

        String expectedURL = EnvConfig.BASE_URL;
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(expectedURL, actualURL);
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    public void mainPageLoginButtonTest() {
        WebDriver driver = factory.getDriver();
        MainPage page = new MainPage(driver);
        page.openMainPage();
        page.waitForLoadProfileButton();
        LoginPage loginPage = page.clickLoginButton();
        MainPage mainPage = loginPage.login(user);
        //Ожидание появления кнопки оформить заказ после логина
        mainPage.waitForLoadOrderButton();

        String expectedURL = EnvConfig.BASE_URL;
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(expectedURL, actualURL);
    }

    @Test
    @DisplayName("Вход по кнопке 'Личный кабинет' на главной")
    public void mainPageProfileLoginTest() {
        WebDriver driver = factory.getDriver();
        MainPage page = new MainPage(driver);
        page.openMainPage();
        page.waitForLoadProfileButton();
        LoginPage loginPage = page.clickProfileButton();
        loginPage.login(user);
        //Ожидание появления кнопки оформить заказ после логина
        page.waitForLoadOrderButton();

        String expectedURL = EnvConfig.BASE_URL;
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(expectedURL, actualURL);
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void forgotPasswordPageLoginTest() {
        WebDriver driver = factory.getDriver();
        ForgotPasswordPage page = new ForgotPasswordPage(driver);
        page.openForgotPasswordPage();
        LoginPage loginPage = page.clickLoginButton();
        MainPage mainPage = loginPage.login(user);
        //Ожидание появления кнопки оформить заказ после логина
        mainPage.waitForLoadOrderButton();

        String expectedURL = EnvConfig.BASE_URL;
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(expectedURL, actualURL);
    }
}
