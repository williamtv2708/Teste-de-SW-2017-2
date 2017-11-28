package br.upf.topicos.industria.teste;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteReleaseCadastro {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "https://www.katalon.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@org.junit.Test
	public void testUntitledTestCase() throws Exception {
		driver.get("http://localhost:8080/IndustriaJSF/faces/cadastro.xhtml");
		driver.findElement(By.id("form:funcName")).click();
		driver.findElement(By.id("form:funcName")).clear();
		driver.findElement(By.id("form:funcName")).sendKeys("William");
		sleep();
		driver.findElement(By.xpath("//html")).click();
		driver.findElement(By.id("form:mesAno_input")).click();
		sleep();
		driver.findElement(By.linkText("30")).click();
		driver.findElement(By.xpath("//html")).click();
		driver.findElement(By.xpath("//div[@id='form:j_idt16']/div[2]/span")).click();
		sleep();
		driver.findElement(By.xpath("//html")).click();
		driver.findElement(By.id("form:lazy_label")).click();
		driver.findElement(By.id("form:lazy_6")).click();
		sleep();
		driver.findElement(By.xpath("//html")).click();
		driver.findElement(By.id("form:ajax")).click();
		assertEquals(
				"Funcionário undefined cadastrado com sucesso com os seguintes dados:\n - Data de Trabalho: undefined\n - Funcionário sem férias no Mês\n - Funcionário com 5 Dias de folga.",
				closeAlertAndGetItsText());
		sleep();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	// temporizador em 3 segundos
	public void sleep() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
