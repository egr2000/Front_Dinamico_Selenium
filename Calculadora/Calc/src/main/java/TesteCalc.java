import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCalc {

    static WebDriver driver;
    private static final String URL = "https://igorsmasc.github.io/calculadora_atividade_selenium/";

    @BeforeAll
    public static void beforeAll() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeEach
    void beforeEach() {
        driver.get(URL);
    }

    @AfterAll
    public static void afterAll() {
        driver.navigate().refresh();
    }

    @Test
    public void testeSoma() {
        WebElement numero1 = driver.findElement(By.id("one"));
        WebElement numero7 = driver.findElement(By.id("seven"));
        WebElement operacao = driver.findElement(By.xpath("//*[@id='add']"));
        WebElement resolver = driver.findElement(By.id("equals"));
        WebElement visor = driver.findElement(By.tagName("input"));
        numero1.click();
        operacao.click();
        numero7.click();
        Assertions.assertEquals("1+7", visor.getAttribute("value"));
        resolver.click();
        Assertions.assertEquals("8", visor.getAttribute("value"));
    }

    @Test
    public void testeSubstracao() {
        WebElement numero3 = driver.findElement(By.id("three"));
        WebElement numero7 = driver.findElement(By.id("seven"));
        WebElement operacao = driver.findElement(By.xpath("//*[@id='subtract']"));
        WebElement resolver = driver.findElement(By.id("equals"));
        WebElement visor = driver.findElement(By.tagName("input"));
        numero3.click();
        numero3.click();
        operacao.click();
        numero7.click();
        Assertions.assertEquals("33-7", visor.getAttribute("value"));
        resolver.click();
        Assertions.assertEquals("26", visor.getAttribute("value"));
    }

    @Test
    public void testeMultiplicacao() {
        WebElement numero3 = driver.findElement(By.id("three"));
        WebElement numero4 = driver.findElement(By.id("four"));
        WebElement numero1 = driver.findElement(By.id("one"));
        WebElement operacao = driver.findElement(By.xpath("//*[@id='multiply']"));
        WebElement resolver = driver.findElement(By.id("equals"));
        WebElement visor = driver.findElement(By.tagName("input"));
        numero1.click();
        numero4.click();
        operacao.click();
        numero3.click();
        Assertions.assertEquals("14*3", visor.getAttribute("value"));
        resolver.click();
        Assertions.assertEquals("42", visor.getAttribute("value"));
    }

    @Test
    public void testeDivisao() {
        WebElement numero3 = driver.findElement(By.id("three"));
        WebElement numero1 = driver.findElement(By.id("one"));
        WebElement numero0 = driver.findElement(By.id("zero"));
        WebElement operacao = driver.findElement(By.xpath("//*[@id='divide']"));
        WebElement resolver = driver.findElement(By.id("equals"));
        WebElement visor = driver.findElement(By.tagName("input"));
        numero3.click();
        numero0.click();
        operacao.click();
        numero1.click();
        numero0.click();
        Assertions.assertEquals("30/10", visor.getAttribute("value"));
        resolver.click();
        Assertions.assertEquals("3", visor.getAttribute("value"));
    }

    @Test
    public void testeClear() {
        WebElement numero3 = driver.findElement(By.id("three"));
        WebElement numero1 = driver.findElement(By.id("one"));
        WebElement numero0 = driver.findElement(By.id("zero"));
        WebElement operacao = driver.findElement(By.xpath("//*[@id='clear']"));
        WebElement visor = driver.findElement(By.tagName("input"));
        numero3.click();
        numero1.click();
        numero1.click();
        numero0.click();
        Assertions.assertEquals("3110", visor.getAttribute("value"));
        operacao.click();
        Assertions.assertEquals("", visor.getAttribute("value"));
    }
}


