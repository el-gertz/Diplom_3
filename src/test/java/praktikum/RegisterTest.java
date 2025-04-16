package praktikum;

import api.UserAPI;
import api.dto.User;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import pages.EnvConfig;
import pages.LoginPage;
import pages.RegisterPage;

public class RegisterTest {

    private final UserAPI api = new UserAPI();
    private final User user = User.random();
    private String accessToken;

    @After
    public void clearTestData() {
        if (accessToken != null) api.delete(accessToken);
    }

    @Rule
    public DriverRule factory = new DriverRule();


    @Test
    @DisplayName("Успешная регистрация")
    public void registerTest() {
        WebDriver driver = factory.getDriver();
        RegisterPage page = new RegisterPage(driver);
        page.openRegisterPage();


        page.fillRegisterFields(user.getName(), user.getEmail(), user.getPassword());
        LoginPage loginPage = page.clickRegisterButton();
        loginPage.waitForLoginButton();

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        accessToken = localStorage.getItem("accessToken");

        String expectedURL = EnvConfig.PROFILE_URL;
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(expectedURL, actualURL);
    }

    @Test
    @DisplayName("Ошибка некорректного пароля")
    public void invalidPasswordTest() {
        WebDriver driver = factory.getDriver();
        RegisterPage page = new RegisterPage(driver);
        page.openRegisterPage();

        page.fillRegisterFields(user.getName(), user.getEmail(), "1");
        page.clickRegisterButton();

        Assert.assertTrue(page.displayedInvalidPasswordMessage());
    }
}
