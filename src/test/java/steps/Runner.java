package steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    dryRun = false,
    monochrome = false,
    features = "src/test/resources/features",
    glue = "steps",
    plugin = {
            "pretty",
            "html:target/Relatorio-html.html"
    }

)

public class Runner{

}
