package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConsultasOnlinePage {
	
	WebDriver driver;
	By gEmpresa = By.id("gEmpresa");
	By empresa = By.id("empresa");
	By departamento = By.id("departamento");
	By titular = By.id("titular");
	By tarjeta = By.id("tarjeta");
	By confirmar = By.id("bQuery");
	By mensajeTarjeta = By.id("cardNumber");
	
	public ConsultasOnlinePage(WebDriver driver) {
		this.driver = driver;
	}
	public void selectGrupo() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(gEmpresa));
		Select grupoEmpresa = new Select(driver.findElement(gEmpresa));
		grupoEmpresa.selectByVisibleText("GR EMP REDSYS");
	}
	
	public void selectEmpresa() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(empresa));
		Select selectEmpresa = new Select(driver.findElement(empresa));
		selectEmpresa.selectByVisibleText("PRUEBAS INFOTAR 1");
	}
	
	public void selectDepartamento() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(departamento));
		Select selectDepartamento = new Select(driver.findElement(departamento));
		selectDepartamento.selectByVisibleText("SIN ASIGNAR");	
	}
	
	public void selectTitular() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(titular));
		Select selectTitular = new Select(driver.findElement(titular));
		selectTitular.selectByVisibleText("GARCIA JUAN BANDERA");
	}
	
	public void selectTarjeta() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(tarjeta));
		Select selectTarjeta = new Select(driver.findElement(tarjeta));
		selectTarjeta.selectByIndex(1);
	}
	
	public void clicarConfirmar() {
		driver.findElement(confirmar).click();
	}
	
	public String getMensajeTarjeta() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.textToBe(mensajeTarjeta, "409111******8014"));
		return driver.findElement(mensajeTarjeta).getText();		
	}

}
