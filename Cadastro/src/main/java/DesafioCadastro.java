import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DesafioCadastro {


    private static final String URL = "https://igorsmasc.github.io/fomulario_cadastro_selenium/";
    private static WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        driver = new ChromeDriver();
    }

    @BeforeEach
    public void beforeEach() {
        driver.get(URL);
    }

    @AfterAll
    public static void afterAll() {
        //driver.quit();
    }

    @Test
    public void preencherFormulario() {
        WebElement nome = driver.findElement(By.id("nome"));
        WebElement sobrenome = driver.findElement(By.id("sobrenome"));
        WebElement sexo = driver.findElement(By.id("masculino"));
        WebElement conhecimento1 = driver.findElement(By.id("java"));
        WebElement conhecimento2 = driver.findElement(By.id("selenium"));
        WebElement conhecimento3 = driver.findElement(By.id("react"));
        WebElement areaInteresse = driver.findElement(By.id("area-interesse"));
        WebElement areaPrincipal = driver.findElement(By.id("motivacao"));
        WebElement porque = driver.findElement(By.id("porque"));
        WebElement maisInfo = driver.findElement(By.id("btn-info"));
        WebElement sobreEmpresa = driver.findElement(By.linkText("Sobre a empresa"));
        WebElement botaoEnviar = driver.findElement(By.xpath("//*[@id=\"formulario\"]/div[8]/button[2]"));


        //teste botão Mais Informações
        maisInfo.click();
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("Este formulário é para cadastro de candidatos interessados " +
                "em vagas de emprego. Por favor, preencha todos os campos obrigatórios e forneça " +
                "informações precisas e atualizadas. Obrigado!", alert.getText());
        alert.dismiss();

        //teste link Sobre a empresa
        sobreEmpresa.click();
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        Assertions.assertEquals("https://igorsmasc.github.io/fomulario_cadastro_selenium/sobre.html", driver.getCurrentUrl());
        WebElement tituloAba = driver.findElement(By.tagName("h1"));
        Assertions.assertEquals("A melhor empresa do mundo", tituloAba.getText());
        driver.close(); //Fechar apenas 1 aba
        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);

        //teste formulario incompleto
        botaoEnviar.click();
        Alert alertIncompleto = driver.switchTo().alert();
        Assertions.assertEquals("Por favor, preencha todos os campos.", alertIncompleto.getText());
        alert.dismiss();

        //preenchendo o formulario
        nome.sendKeys("Jonas");
        sobrenome.sendKeys("Albuquerque");
        sexo.click();
        conhecimento1.click();
        conhecimento2.click();

        Select interesses = new Select(areaInteresse);
        interesses.selectByVisibleText("Backend");
        interesses.selectByVisibleText("Testes");

        Select opcaoPrincipal = new Select(areaPrincipal);
        opcaoPrincipal.selectByIndex(2);

        porque.sendKeys("Porque sim!");


        //testando preenchimento do formulario
        Assertions.assertTrue(sexo.isSelected());
        Assertions.assertTrue(conhecimento1.isSelected());
        Assertions.assertTrue(conhecimento2.isSelected());
        Assertions.assertFalse(conhecimento3.isSelected());

        List<String> interessesText = interesses.getAllSelectedOptions().stream().map(WebElement::getText)
                .collect(Collectors.toList());
        List<String> interessesEsperados = Arrays.asList("Backend", "Testes");
        Assertions.assertEquals(2, interesses.getAllSelectedOptions().size());
        Assertions.assertArrayEquals(interessesText.toArray(), interessesEsperados.toArray());

        Assertions.assertEquals("Backend", opcaoPrincipal.getFirstSelectedOption().getText());

        //clicando no botão enviar com o formulario completo
        botaoEnviar.click();
        Alert alertCompleto = driver.switchTo().alert();
        Assertions.assertEquals("Você preencheu tudo corretamente e é sua última resposta?", alertCompleto.getText());
        alertCompleto.accept();


        WebElement cad = driver.findElement(By.tagName("td"));
        Assertions.assertEquals("Jonas",cad.getText());
    }
}

