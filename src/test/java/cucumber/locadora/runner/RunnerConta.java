package cucumber.locadora.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/inserir_contar.feature",
		glue = "cucumber.locadora.steps",
		tags = {"~@ignore"}, 
		plugin = {"pretty", "html:target/report-html", "json:target/report.json"},
		monochrome = true,
		snippets = SnippetType.CAMELCASE,
		dryRun	= false,
		strict = false
		)

public class RunnerConta {

}
