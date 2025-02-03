package steps;


import function.FunctionPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CadastroPrincipal {

    private WebDriver driver;

    @Given("que estou na tela principal")
    public void queEstouNaTelaPrincipal() {

        driver = FunctionPage.iniciarDriver();
        driver.get("https://petlov.vercel.app");

        String expectedTitle = "Petlov by Papito";
        String actualTitle = driver.getTitle();
        assertEquals(expectedTitle, actualTitle, "O título da página não corresponde ao esperado!");

    }

    @When("clico no botão")
    public void clicoNoBotao() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.tagName("span")).click();
    }

    @Then("sou direcionado a tela de cadastro.")
    public void souDirecionadoATelaDeCadastro() throws IOException {

        String expectedUrl = "https://petlov.vercel.app/signup";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl, "A URL após o clique não corresponde à esperada!");

        PrintScreen();

      FunctionPage.fecharDriver();

    }

    public void PrintScreen() throws IOException {

        File FinishPrint = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(FinishPrint, new File("target/evidencia.png"));


    }


}

