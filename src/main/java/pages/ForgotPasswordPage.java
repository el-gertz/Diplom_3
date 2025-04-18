package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    //Кнопка Войти
    private final By loginButton = By.xpath(".//a[contains(text(), 'Войти')]");

    @Step("Клик на кнопку Войти")
    public LoginPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    @Step("Переход на страницу Восстановления пароля")
    public void openForgotPasswordPage() {
        driver.get(EnvConfig.FORGOT_PASS_URL);
    }

}
