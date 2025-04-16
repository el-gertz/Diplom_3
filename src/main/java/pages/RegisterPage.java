package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    //поле Имя
    private final By nameField = By.xpath("//div[label[contains(text(), 'Имя')]]/input");
    //поле Email
    private final By emailField = By.xpath("//div[label[contains(text(), 'Email')]]/input");
    //поле Пароль
    private final By passwordField = By.xpath("//div[label[contains(text(), 'Пароль')]]/input");

    //кнопка Зарегистрироваться после заполнения формы
    private final By registerButton = By.xpath(".//button[contains(text(), 'Зарегистрироваться')]");
    //кнопка "Войти"
    private final By loginButton = By.xpath(".//a[contains(text(), 'Войти')]");
    //текст 'Некорректный пароль'
    private final By invalidPassword = By.xpath(".//p[contains(text(), 'Некорректный пароль')]");

    @Step("Заполнение полей формы регистрации")
    public void fillRegisterFields(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик на кнопку Зарегистрироваться")
    public LoginPage clickRegisterButton() {
        driver.findElement(registerButton).click();
        return new LoginPage(driver);
    }

    @Step("Переход на страницу регистрации")
    public void openRegisterPage() {
        driver.get(EnvConfig.REGISTER_URL);
    }

    @Step("Клик на кнопку Войти")
    public LoginPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    @Step("Проверка отображения сообщения о некорректном пароле")
    public boolean displayedInvalidPasswordMessage() {
        return driver.findElement(invalidPassword).isDisplayed();
    }
}
