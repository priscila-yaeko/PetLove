package function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class FormularioPage {
    private WebDriver driver;


    // criado essa class para proteger os dados do xpath
    public FormularioPage(WebDriver driver){
        this.driver = driver;
    }

    private By campoNome = By.xpath("//input[@placeholder='Nome do ponto de doação']");
    private By campoEmail = By.xpath("//input[@placeholder='E-mail']");
    private By campoCEP = By.xpath("//input[@placeholder='CEP']");
    private By botaoBuscarCEP = By.xpath("//input[@value='Buscar CEP']");
    private By campoNumero = By.xpath("//input[@placeholder='Número']");


    // criado essa class para preencher os campos com seus xpath
    private void preencherCampo(By by, String valor) {
        WebElement elemento = driver.findElement(by);
        elemento.clear(); // Limpa o campo antes de preencher (evita concatenação indesejada)
        elemento.sendKeys(valor);
    }

    // Método para clicar em um botão
    private void clicar(By by) {
        driver.findElement(by).click();
    }

    // Método que preenche todos os campos obrigatórios com valores dinâmicos
    public void preenchoTodosOsCamposObrigatoriosComAsInformacoesDoPet(
            String nome, String email, String cep, String numero) {

        preencherCampo(campoNome, nome);
        preencherCampo(campoEmail, email);
        preencherCampo(campoCEP, cep);
        clicar(botaoBuscarCEP);
        preencherCampo(campoNumero, numero);
    }
}

