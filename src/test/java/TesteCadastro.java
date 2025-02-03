import function.FunctionPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteCadastro {

    private WebDriver driver;

    @BeforeEach
    void setUp() {


        // inicialiazação da página
        driver = FunctionPage.iniciarDriver();
        driver.get("https://petlov.vercel.app/signup");

    }

    @Test
    @DisplayName("Validar que o campo Nome do ponto de doação está visível e preenchê-lo")
    void iniciarTela() {

         // validação do titulo da página

        String expectedTitle = "Petlov by Papito";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle, "O título da página não corresponde ao esperado!");

        // espera pelo carregamento do formulario

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement formulario =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signup-page")));
        Assert.assertEquals("O Formulário de cadastro não está disponivél na página", true, formulario.isDisplayed());


        // inicia o preenchimento do formulario

        WebElement name = driver.findElement(By.xpath("//input[@placeholder='Nome do ponto de doação']"));
        name.sendKeys("Maily Dogs");

        WebElement email = driver.findElement(By.xpath("//input[@placeholder='E-mail']"));
        email.sendKeys("teste@teste.com");

        WebElement cep = driver.findElement(By.xpath("//input[@placeholder='CEP']"));
        cep.sendKeys("07176560");

        WebElement cepButton =driver.findElement(By.xpath("//input[@value='Buscar CEP']"));
        cepButton.click();

        WebElement cepField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Rua']")));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(cepField, "value", "")));

        WebElement numero = driver.findElement(By.xpath("//input[@placeholder='Número']"));
        numero.sendKeys("102");

        WebElement opcaoDog = driver.findElement(By.xpath("//span[text()=\"Cachorros\"]"));
        opcaoDog.click();

        WebElement btnCadastrar = driver.findElement(By.xpath("//button[text()='Cadastrar']"));
        btnCadastrar.click(); // Clica no botão de cadastro apenas após a rua ser carregada

        try {
            Thread.sleep(3000); // Pausa de 3 segundos (3000 milissegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 🔹 Espera até que a mensagem de sucesso apareça
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body p")));

        String actualMessage = result.getText().trim().replace("\n", "").replace("\r", "");

        String target = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";
        wait.until(ExpectedConditions.textToBePresentInElement(result, target)); // Garante que o texto está correto
        Assert.assertEquals(target, actualMessage);

    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
