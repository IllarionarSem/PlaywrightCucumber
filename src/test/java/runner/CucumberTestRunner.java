package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/cucumber-html-report", "json:target/cucumber-json-report.json"},
        features = "src/test/resources",
        glue = "stepdef",
        strict = true)
public class CucumberTestRunner {

}
