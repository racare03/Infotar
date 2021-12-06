package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TotalesTarjetaPage {
	
	//variables
	WebDriver driver;
	By botonInforme = By.id("bInforme");
	By mensaje = By.id("msg1");
	By mensaje2 = By.id("msg2");
	By gEmpresa = By.id("gEmpresa");
	By empresaby = By.id("empresa");
	By tarjetaby = By.id("tarjeta");
	By fechaDesdeBy = By.id("fechaOpeDesde");
	By fechaHastaBy = By.id("fechaOpeHasta");
	By totalesDetalle = By.xpath("//*[@id=\"tb-tarjetas\"]/tbody/tr/td[7]");
	By botonLimpiar = By.id("bClear");
	By botonVerFiltros = By.id("bVerFiltros");
	By primerElemento = By.cssSelector("#filterTable > tbody > tr.odd > td:nth-child(1)");
	By botonAceptar =By.id("okButton");
	//By primerFiltro = By.xpath("/html/body/div[11]/div[2]/div/div[2]/div[3]/div[2]/div/table/tbody/tr[1]/td/input[2]");
	By primerFiltro = By.id("bDelete");
	By mensajeTextoBorrado = By.cssSelector("#text-dialog > p");
	By botonGuardarFiltro = By.id("bGuardarFiltro");
	By inputNuevoFiltro = By.id("nuevoFiltro");
	By botonGuardar = By.id("okButton");
	By mensajeTextoGuardado = By.id("text-dialog");
	By mensajeSinNombre = By.id("text-dialog");
	
public TotalesTarjetaPage(WebDriver driver) {
	this.driver = driver;
}

//Hacer click en el botón Informe
	public void clicarInforme() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(botonInforme));
		driver.findElement(botonInforme).click();
	}
	
	//Obtener Mensaje de selccionar empresa y grupo de empresa
	public String getMensaje() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.textToBe(mensaje,"Debe seleccionar un valor para el grupo de empresa y para la empresa"));
		return driver.findElement(mensaje).getText();
	}
	
	//Obtener Mensaje de selccionar empresa para Admin empresa
	public String getMensaje2() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.textToBe(mensaje2,"Debe seleccionar un valor para la empresa"));
		return driver.findElement(mensaje2).getText();
	}
	
	//Seleccionar Grupo de Empresa
	public void selectGrupo() {
		Select grupoEmpresa = new Select(driver.findElement(gEmpresa));
		grupoEmpresa.selectByVisibleText("GR EMP REDSYS");
	}
	
	//Seleccionar Empresa
	public void selectEmpresa() {
		Select empresa = new Select(driver.findElement(empresaby));
		empresa.selectByVisibleText("PRUEBAS INFOTAR 1");
	}
	
	//Seleccionar Tarjeta
	public void selectTarjeta() {
		Select tarjeta = new Select(driver.findElement(tarjetaby));
		tarjeta.selectByIndex(1);
	}
	
	//Seleccionar fecha de operación desde
	public void selectFechaOpDesde(String fecha) {
		WebElement fechaDesde = driver.findElement(fechaDesdeBy);
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", fechaDesde);
		fechaDesde.clear();
		driver.findElement(fechaDesdeBy).sendKeys(fecha);		
	}
		
		//Seleccionar fecha de operación hasta
		public void selectFechaOpHasta(String fecha) {
			WebElement fechaHasta = driver.findElement(fechaHastaBy);
			((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", fechaHasta);
			fechaHasta.clear();
			driver.findElement(fechaHastaBy).sendKeys(fecha);
			
	}
		
	public String getMovimientos() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.textToBe(totalesDetalle,"GARCIA JUAN BANDERA"));
		return driver.findElement(totalesDetalle).getText();
		
	}
	
	public void clicarLimpiarFiltros() {
		driver.findElement(botonLimpiar).click();
	}
	
	public String filtroLimpio() {
		Select empresa = new Select(driver.findElement(empresaby));
		String valorSeleccionado = empresa.getFirstSelectedOption().getText();
		return valorSeleccionado;
	}
	
	//Filtro limpio para titulares
	public String filtroLimpio2() {
		Select tarjeta = new Select(driver.findElement(tarjetaby));
		String valorSeleccionado = tarjeta.getFirstSelectedOption().getText();
		return valorSeleccionado;
	}
	
	public void clicarVerFiltros() {
		driver.findElement(botonVerFiltros).click();
	}
	
	
	public void clicarPrimerFiltro() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(primerElemento));
		driver.findElement(primerElemento).click();
	}
	
	public void clicarAceptar() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(botonAceptar));
		driver.findElement(botonAceptar).click();
	}
	
	public void clicarBorrarFiltro() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(primerFiltro));
		WebElement  element=driver.findElement(primerFiltro);  
		JavascriptExecutor ex=(JavascriptExecutor)driver;
		ex.executeScript("arguments[0].click()", element);
	}
	
	public void clicarAceptarBorrar() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(botonAceptar));
		//Almaceno en la lista los dos botones con el mismo id
		List<WebElement> a = driver.findElements(botonAceptar);
		a.get(1).click();
	}
	
	public String textoBorrado() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.textToBe(mensajeTextoBorrado,"Filtro eliminado con éxito"));
		return driver.findElement(mensajeTextoBorrado).getText();
	}
	
	public void clicarGuardarFiltro() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(botonGuardarFiltro));
		driver.findElement(botonGuardarFiltro).click();
	}
	
	public void ponerNombreFiltro(String nombreFiltro) {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(botonGuardar));
		driver.findElement(inputNuevoFiltro).sendKeys(nombreFiltro);
	}
	
	public void clicarGuardar() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(botonGuardar));
		driver.findElement(botonGuardar).click();
	}
	
	public String textoGuardado() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.textToBe(mensajeTextoGuardado, "Filtro guardado con éxito"));
		return driver.findElement(mensajeTextoGuardado).getText();
	}
	
	public void filtroSinNombre() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(botonGuardar));
		driver.findElement(inputNuevoFiltro).sendKeys("");		
	}
	
	public String textoSinNombre() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.textToBe(mensajeSinNombre, "El nombre del filtro no puede estar vacío"));
		return driver.findElement(mensajeSinNombre).getText();
	}
	

}
