package cucumber.locadora.runner;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import io.cucumber.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/inserir_contar.feature",
		glue = "cucumber.locadora.steps",
		tags = {"~@unitários"}, 
		plugin = {"pretty", "html:target/report-html", "json:target/report.json"},
		monochrome = true,
		snippets = SnippetType.CAMELCASE,
		dryRun	= false,
		strict = false
		)

public class RunnerConta {
	
	@BeforeClass
	public static void reset() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\leonardoangelo\\Downloads\\driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://srbarriga.herokuapp.com");
		driver.findElement(By.id("email")).sendKeys("leonardoangelo@brq.com");
		driver.findElement(By.name("senha")).sendKeys("123456");
		driver.findElement(By.tagName("button")).click();
		driver.findElement(By.linkText("reset")).click();
		driver.close();
		
	}
}
