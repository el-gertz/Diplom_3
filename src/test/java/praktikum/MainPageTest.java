package praktikum;

import api.UserAPI;
import api.dto.User;
import io.qameta.allure.Allure;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.EnvConfig;
import pages.MainPage;

import java.net.HttpURLConnection;

public class MainPageTest {

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
    @DisplayName("Проверка перехода по кнопке 'Личный кабинет'")
    public void profileButtonTest() {
        WebDriver driver = factory.getDriver();
        MainPage page = new MainPage(driver);
        page.openMainPage();

        page.waitForLoadProfileButton();
        page.clickProfileButton();

        String expectedURL = EnvConfig.PROFILE_URL;
        String actualURL = driver.getCurrentUrl();

        Assert.assertEquals(expectedURL, actualURL);
    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Соусы'")
    public void saucesButtonTest() {
        WebDriver driver = factory.getDriver();
        MainPage page = new MainPage(driver);
        page.openMainPage();

        page.waitForLoadProfileButton();
        page.clickSaucesButton();

        page.waitForCurrentSauces();
    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Начинки'")
    public void fillingsButtonTest() {
        WebDriver driver = factory.getDriver();
        MainPage page = new MainPage(driver);
        page.openMainPage();

        page.clickFillingsButton();

        page.waitForCurrentFillings();
    }

    @Test
    @DisplayName("Проверка перехода к разделу 'Булки'")
    public void bunsButtonTest() {
        WebDriver driver = factory.getDriver();
        MainPage page = new MainPage(driver);
        page.openMainPage();

        page.waitForLoadBunsButton();
        try {
            page.clickBunsButton();
        } catch (Exception e) {
            page.clickSaucesButton();
            Allure.step("Кнопка 'Булки' уже нажата: " + e);
        } finally {
            page.clickBunsButton();
        }

        page.waitForCurrentBuns();
    }
}
