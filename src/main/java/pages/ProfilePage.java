package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {

    private final WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    //кнопка Конструктор
    private final By constructorButton = By.xpath(".//p[contains(text(), 'Конструктор')]");
    //кнопка Логотип
    private final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    //кнопка Выход
    private final By outButton = By.xpath(".//button[contains(text(), 'Выход')]");

    @Step("Клик на кнопку Конструктор")
    public MainPage clickConstructorButton() {
        driver.findElement(constructorButton).click();
        return new MainPage(driver);
    }

    @Step("Клик на кнопку Лого")
    public MainPage clickLogoButton() {
        driver.findElement(logoButton).click();
        return new MainPage(driver);
    }

    @Step("Клик на кнопку Выход")
    public LoginPage clickOutButton() {
        driver.findElement(outButton).click();
        return new LoginPage(driver);
    }

}
