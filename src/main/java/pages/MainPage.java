package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //кнопка «Личный кабинет»
    private final By profileButton = By.xpath(".//p[contains(text(), 'Личный Кабинет')]");
    //кнопки Конструктора
    private final By bunsButton = By.xpath("//div[span[contains(text(), 'Булки')]]");
    private final By saucesButton = By.xpath("//div[span[contains(text(), 'Соусы')]]");
    private final By fillingsButton = By.xpath("//div[span[contains(text(), 'Начинки')]]");
    //кнопка Войти в аккаунт
    private final By loginButton = By.xpath(".//button[contains(text(), 'Войти в аккаунт')]");
    //кнопка Оформить заказ
    private final By orderButton = By.xpath(".//button[contains(text(), 'Оформить заказ')]");

    @Step("Переход на главную страницу")
    public void openMainPage() {
        driver.get(EnvConfig.BASE_URL);
    }

    @Step("Клик на кнопку Личный кабинет")
    public LoginPage clickProfileButton() {
        driver.findElement(profileButton).click();
        return new LoginPage(driver);
    }

    @Step("Клик на кнопку Войти в аккаунт")
    public LoginPage clickLoginButton() {
        driver.findElement(loginButton).click();
        return new LoginPage(driver);
    }

    @Step("Клик на кнопку Булки")
    public MainPage clickBunsButton() {
        driver.findElement(bunsButton).click();
        return new MainPage(driver);
    }

    @Step("Клик на кнопку Соусы")
    public MainPage clickSaucesButton() {
        driver.findElement(saucesButton).click();
        return new MainPage(driver);
    }

    @Step("Клик на кнопку Начинки")
    public void clickFillingsButton() {
        driver.findElement(fillingsButton).click();

    }

    @Step("Ожидание отображения кнопки Личный кабинет")
    public void waitForLoadProfileButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(profileButton));
    }

    @Step("Ожидание отображения кнопки Оформить заказ")
    public void waitForLoadOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButton));
    }

    @Step("Ожидание отображения кнопки Булки")
    public void waitForLoadBunsButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsButton));
    }

    @Step("Ожидание скролла до соусов")
    public void waitForCurrentSauces() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(saucesButton, "class", "current"));
    }

    @Step("Ожидание скролла до начинки")
    public void waitForCurrentFillings() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(fillingsButton, "class", "current"));
    }

    @Step("Ожидание скролла до булочек")
    public void waitForCurrentBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeContains(bunsButton, "class", "current"));
    }
}
