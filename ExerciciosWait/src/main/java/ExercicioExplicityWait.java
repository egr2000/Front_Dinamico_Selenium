import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ExercicioExplicityWait {

    private WebDriver driver;
    private final String URL = "https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver";
    private WebDriverWait wait;

    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(URL);
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
    }

    @Test
    public void testAlertAfter5Seconds() {
        driver.findElement(By.id("alert")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("I got opened after 5 secods", alert.getText());
    }

    @Test
    public void testChangeText() {
        WebElement texto = driver.findElement(By.id("h2"));
        Assertions.assertEquals("site", texto.getText());
        driver.findElement(By.id("populate-text")).click();
        wait.until(ExpectedConditions.textToBe(By.id("h2"), "Selenium Webdriver"));
        Assertions.assertEquals("Selenium Webdriver", texto.getText());
    }

    @Test
    public void testButtonOculted() {
        WebElement element = driver.findElement(By.id("display-other-button"));
        WebElement botaoEscondido = driver.findElement(By.id("hidden"));
        WebElement alerta = driver.findElement(By.id("alert"));
        element.click();
        wait.until(ExpectedConditions.visibilityOf(botaoEscondido));
        botaoEscondido.click();
        wait.until(ExpectedConditions.invisibilityOf(botaoEscondido));
    }

    @Test
    public void testButtonDisable() {
        WebElement element = driver.findElement(By.id("enable-button"));
        WebElement botaoEscondido = driver.findElement(By.id("disable"));
        element.click();
        wait.until(ExpectedConditions.elementToBeClickable(botaoEscondido));
    }

    @Test
    public void testButtonCheckbox(){
        WebElement element = driver.findElement(By.id("checkbox"));
        WebElement check = driver.findElement(By.id("ch"));
        element.click();
        wait.until(ExpectedConditions.elementSelectionStateToBe(check,true));
    }

}
