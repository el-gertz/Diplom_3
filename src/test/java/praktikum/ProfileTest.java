package praktikum;

import api.UserAPI;
import api.dto.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.EnvConfig;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

import java.net.HttpURLConnection;

public class ProfileTest {

    @Rule
    public DriverRule factory = new DriverRule();

    private final UserAPI api = new UserAPI();
    private final User user = User.random();
    private String accessToken;

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
    @DisplayName("Переход по клику на «Конструктор»")
    public void constructorTest() {
        WebDriver driver = factory.getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.login(user);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        mainPage = profilePage.clickConstructorButton();

        mainPage.waitForLoadProfileButton();

        String expectedURL = EnvConfig.BASE_URL;
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(expectedURL, actualURL);
    }

    @Test
    @DisplayName("Переход по клику на логотип Stellar Burgers")
    public void logoTest() {
        WebDriver driver = factory.getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.login(user);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        mainPage = profilePage.clickLogoButton();

        mainPage.waitForLoadProfileButton();

        String expectedURL = EnvConfig.BASE_URL;
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(expectedURL, actualURL);
    }

    @Test
    @DisplayName("Выход по кнопке 'Выйти' в личном кабинете")
    public void outTest() {
        WebDriver driver = factory.getDriver();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
        loginPage.login(user);

        MainPage mainPage = new MainPage(driver);
        mainPage.clickProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        loginPage = profilePage.clickOutButton();

        loginPage.waitForLoginButton();

        String expectedURL = EnvConfig.PROFILE_URL;
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(expectedURL, actualURL);
    }
}
