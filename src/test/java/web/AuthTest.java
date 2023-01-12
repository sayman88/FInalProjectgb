package web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthTest {
    static WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupBrowser() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.manage().window(). maximize() ;
        driver.get("https://test-stand.gb.ru/login");
    }

    //"Авторизация на сайте https://test-stand.gb.ru"
    // 1. Ввести логин
    // 2. Ввести пароль
    // 3. проверить успешность авторизации по появлению заголовка "Blog"
    @Test
    void authTestPositive() throws InterruptedException {

        driver.findElement(By.xpath("//input[contains(@type, 'text')]")).sendKeys("3184");

        driver.findElement(By.xpath("//input[contains(@type, 'password')]")).sendKeys("109a33176f");
        driver.findElement(By.xpath("//span[contains(@class, 'mdc-button__label')]")).click();
        //  Thread.sleep(5000);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(@class, 'svelte-1e9zcmy')]")));
        Assertions.assertTrue(driver.findElement(By.xpath("//h1[contains(@class, 'svelte-1e9zcmy')]")).isDisplayed());
    }
    //"Авторизация на сайте https://test-stand.gb.ru"
    // 1. Ввести невалидный логин
    // 2. проверить ответ по неправильному логину, код 401
    @Test
    void authTestNegative() throws InterruptedException {

        driver.findElement(By.xpath("//input[contains(@type, 'text')]")).sendKeys("31");

        //     driver.findElement(By.xpath("//input[contains(@type, 'password')]")).sendKeys("109a33176f");
        driver.findElement(By.xpath("//span[contains(@class, 'mdc-button__label')]")).click();
        Thread.sleep(1000);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[.='401']")));
        Assertions.assertTrue(driver.findElement(By.xpath("//h2[.='401']")).isDisplayed());
    }
    //"Авторизация на сайте https://test-stand.gb.ru"
    // 1. Проверка граничного значения, логин 3 символа
    // 2. Ввести пароль
    // 3. проверить успешность авторизации по появлению заголовка "Blog"
    @Test
    void authTestKorner() throws InterruptedException {

        driver.findElement(By.xpath("//input[contains(@type, 'text')]")).sendKeys("333");

        driver.findElement(By.xpath("//input[contains(@type, 'password')]")).sendKeys("109a33176f");
        driver.findElement(By.xpath("//span[contains(@class, 'mdc-button__label')]")).click();
        //  Thread.sleep(5000);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(@class, 'svelte-1e9zcmy')]")));
        Assertions.assertTrue(driver.findElement(By.xpath("//h1[contains(@class, 'svelte-1e9zcmy')]")).isDisplayed());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

}