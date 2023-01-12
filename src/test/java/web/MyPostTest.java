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

public class MyPostTest {
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
        driver.get("https://test-stand.gb.ru/?sort=createdAt&order=ASC");
        driver.findElement(By.xpath("//input[contains(@type, 'text')]")).sendKeys("3184");

        driver.findElement(By.xpath("//input[contains(@type, 'password')]")).sendKeys("109a33176f");
        driver.findElement(By.xpath("//span[contains(@class, 'mdc-button__label')]")).click();
        //  Thread.sleep(5000);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(@class, 'svelte-1e9zcmy')]")));
        Assertions.assertTrue(driver.findElement(By.xpath("//h1[contains(@class, 'svelte-1e9zcmy')]")).isDisplayed());

    }
    //"Переход в раздел сериалы" с главной странице кинопоиска
    //1. Навести кнопку мыши на раздел кинопоиск
    //2. Выбрать сериалы и перейти на страницу
    //3. Убедиться, что верно перешли на раздел сериалы, сравнив url
    @Test
    void goToPreviousNextPageTest() throws InterruptedException {
        driver.findElement(By.xpath("//a[.='Next Page']")).click();
        Thread.sleep(1000);
        Assertions.assertTrue(driver.getCurrentUrl().contains("page=2"));
        driver.findElement(By.xpath("//a[.='Previous Page']")).click();
        Thread.sleep(1000);
        Assertions.assertTrue(driver.getCurrentUrl().contains("page=1"));
    }
    @Test
    void ImgPostTest()  {
        Assertions.assertTrue(driver.findElement(By.xpath("//img[contains(@class, 'svelte-127jg4t')]")).isDisplayed());
    }
    @Test
    void NamePostTest() {
        Assertions.assertTrue(driver.findElement(By.xpath("//h2[contains(@class, 'svelte-127jg4t')]")).isDisplayed());
    }
    @Test
    void DescriptionPostTest()  {
        Assertions.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'description')]")).isDisplayed());
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

}
