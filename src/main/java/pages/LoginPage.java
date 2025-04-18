package pages;

import api.dto.User;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //поле Email
    private final By emailField = By.xpath("//div[label[contains(text(), 'Email')]]/input");
    //поле Пароль
    private final By passwordField = By.xpath("//div[label[contains(text(), 'Пароль')]]/input");
    //кнопка Войти
    private final By loginButton = By.xpath(".//button[contains(text(), 'Войти')]");


    @Step("Ожидание отображения кнопки Войти")
    public void waitForLoginButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    @Step("Заполнение полей формы логина")
    public void fillLoginFields(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Заполнение формы входа")
    public MainPage login(User user) {
        fillLoginFields(user.getEmail(), user.getPassword());
        driver.findElement(loginButton).click();
        return new MainPage(driver);
    }

    @Step("Переход на страницу логина")
    public void openLoginPage() {
        driver.get(EnvConfig.PROFILE_URL);
    }

}
