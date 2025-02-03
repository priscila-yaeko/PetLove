package function;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FunctionPage {
    private static WebDriver driver;

    public static WebDriver iniciarDriver() {

        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));


        }

        return driver;

    }

    public static WebDriver fecharDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;


        }
        return null;
    }
}