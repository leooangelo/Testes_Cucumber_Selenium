package cucumber.locadora.steps;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import io.cucumber.core.api.Scenario;
import junit.framework.Assert;

public class InserirContaSteps {
	private WebDriver driver;
	
	@Dado("^que estou acessando a aplicação$")
	public void queEstouAcessandoAAplicação() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\leonardoangelo\\Downloads\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://srbarriga.herokuapp.com");
		
		
	}

	@Quando("^informo o usuário \"([^\"]*)\"$")
	public void informoOUsuário(String arg1) throws Throwable {
	    driver.findElement(By.id("email")).sendKeys(arg1);
	}

	@Quando("^a senha \"([^\"]*)\"$")
	public void aSenha(String arg1) throws Throwable {
	    driver.findElement(By.name("senha")).sendKeys(arg1);
	}

	@Quando("^seleciono entrar$")
	public void selecionoEntrar() throws Throwable {
	   driver.findElement(By.tagName("button")).click();
	}

	@Então("^visualizo a página inicial$")
	public void visualizoAPáginaInicial() throws Throwable {
	    String texto = driver.findElement(By.xpath("//div[@class= 'alert alert-success']")).getText();
	    Assert.assertEquals("Bem vindo, Leonardo!", texto);
	}

	@Quando("^seleciono Contas$")
	public void selecionoContas() throws Throwable {
	    driver.findElement(By.linkText("Contas")).click();
	    
	}

	@Quando("^seleciono Adicionar$")
	public void selecionoAdicionar() throws Throwable {
		driver.findElement(By.linkText("Adicionar")).click();
	}

	@Quando("^informo a conta \"([^\"]*)\"$")
	public void informoAConta(String arg1) throws Throwable {
	   driver.findElement(By.id("nome")).sendKeys(arg1);
	}

	@Quando("^seleciono Salvar$")
	public void selecionoSalvar() throws Throwable {
		driver.findElement(By.tagName("button")).click();
	}

	@Então("^a conta é inserida com sucesso$")
	public void aContaÉInseridaComSucesso() throws Throwable {
		String texto = driver.findElement(By.xpath("//div[@class= 'alert alert-success']")).getText();
	    Assert.assertEquals("Conta adicionada com sucesso!", texto);
	}
	
	@Então("^sou notificado que o nome da conta é obrigatório$")
	public void souNotificadoQueONomeDaContaÉObrigatório() throws Throwable {
		 String texto = driver.findElement(By.xpath("//div[@class= 'alert alert-danger']")).getText();
		    Assert.assertEquals("Informe o nome da conta", texto);
	}
	
	@Então("^sou notificado que já existe uma conta com esse nome$")
	public void souNotificadoQueJáExisteUmaContaComEsseNome() throws Throwable {
		 String texto = driver.findElement(By.xpath("//div[@class= 'alert alert-danger']")).getText();
		    Assert.assertEquals("Já existe uma conta com esse nome!", texto);
	}
	
	@Então("^recebo a mensagem \"([^\"]*)\"$")
	public void receboAMensagem(String arg1) throws Throwable {
		String texto = driver.findElement(By.xpath("//div[starts-with(@class, 'alert alert-')]")).getText();
	    Assert.assertEquals(arg1, texto);
	}
	@Before
	private void inico() {
		System.out.println("Inicio de teste");
	}
	@After(order = 1, value = {"~@unitários"})
	public void screenshot(Scenario cenario) {
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
		FileUtils.copyFile(file, new File( "target/screenshtots/"+ cenario.getId()+".jpg"));
	} catch(Exception e) {
		e.printStackTrace();
		}
	}
	@After(order= 0, value = {"~@unitários"})
	public void fecharBrowser() {
		driver.close();
		System.out.println("Fim de cenário de teste");
	}
}
