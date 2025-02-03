package steps;

import function.FunctionPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class TelaCadastro {

     private WebDriver driver;

    @Given("que estou na tela de iniciar cadastro")
    public void queEstouNaTelaDeIniciarCadastro() {

        driver = FunctionPage.iniciarDriver();
        driver.get("https://petlov.vercel.app/signup");

    }

    @When("visualizo o campo \"Nome do ponto de doação")
    public void visualizoOCampoNomeDoPontoDeDoacao()  {

        WebElement campoPonto = driver.findElement(By.xpath("//input[@placeholder='Nome do ponto de doação']"));
        Assertions.assertTrue(campoPonto.isDisplayed(), "O campo Nome do ponto de doação não está visível!");

    }


    @Then("preencho campo")
    public void preenchoCampo() {

        driver.findElement(By.xpath("//input[@placeholder='Nome do ponto de doação']")).sendKeys("Maily Doacao");
   driver = FunctionPage.fecharDriver();

    }
}
