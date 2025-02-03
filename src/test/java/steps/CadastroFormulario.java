package steps;

import function.FormularioPage;
import function.FunctionPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CadastroFormulario {

    private WebDriver driver;
    private Instant wait;


    @Given("que estou na tela principal do sistema de cadastro")
    public void queEstouNaTelaPrincipalDoSistemaDeCadastro() {


        driver = FunctionPage.iniciarDriver();
        driver.get("https://petlov.vercel.app/signup");
        String expectedTitle = "Petlov by Papito";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle, "O título da página não corresponde ao esperado!");

    }

    @And("visualizo o formulário de cadastro disponível")
    public void visualizoOFormularioDeCadastroDisponivel() {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement formulario =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signup-page")));
    Assert.assertEquals("O Formulário de cadastro não está disponivél na página", true, formulario.isDisplayed());


    }

    @When("preencho todos os campos obrigatórios com as informações do pet")
    public void preenchoTodosOsCamposObrigatoriosComAsInformacoesDoPet() {

        FormularioPage formularioPage = new FormularioPage(driver);
        formularioPage.preenchoTodosOsCamposObrigatoriosComAsInformacoesDoPet(
                "Pet Amor",
                "contato@petamor.com",
                "01234567",
                "250"
        );

//       WebElement name = driver.findElement(By.xpath("//input[@placeholder='Nome do ponto de doação']"));
//       name.sendKeys("Maily Dogs");
//
//        WebElement email = driver.findElement(By.xpath("//input[@placeholder='E-mail']"));
//        email.sendKeys("teste@teste.com");
//
//        WebElement cep = driver.findElement(By.xpath("//input[@placeholder='CEP']"));
//        cep.sendKeys("07176560");
//
//        WebElement cepButton =driver.findElement(By.xpath("//input[@value='Buscar CEP']"));
//        cepButton.click();
//
//        WebElement numero = driver.findElement(By.xpath("//input[@placeholder='Número']"));
//        numero.sendKeys("102");

    }

    @And("seleciono a opção {string} como tipo de pet")
    public void selecionoAOpcaoComoTipoDePet(String arg0) {

        WebElement opcaoDog = driver.findElement(By.xpath("//span[text()=\"Cachorros\"]"));
        opcaoDog.click();

    }

    @Then("clico no botão {string}")
    public void clicoNoBotao(String arg0) {

        WebElement btnCadastrar = driver.findElement(By.xpath("//button[text()='Cadastrar']"));
        btnCadastrar.click(); // Clica no botão de cadastro apenas após a rua ser carregada

        try {
            Thread.sleep(4000); // Pausa de 3 segundos (3000 milissegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @And("vejo a mensagem de sucesso confirmando o cadastro")
    public void vejoAMensagemDeSucessoConfirmandoOCadastro() {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.urlToBe("https://petlov.vercel.app/success"));
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("A URL não corresponde à esperada!", "https://petlov.vercel.app/success", currentUrl);



       WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Seu ponto de doação foi adicionado com sucesso. Ju')]")));

      String actualMessage = result.getText().trim().replace("\n", "").replace("\r", "");

        try {
            Thread.sleep(5000); // Pausa de 3 segundos (3000 milissegundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      String target = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";
       wait.until(ExpectedConditions.textToBePresentInElement(result, target)); // Garante que o texto está correto
       Assert.assertEquals(target, actualMessage);
        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");

        driver = FunctionPage.fecharDriver();

    }



}
