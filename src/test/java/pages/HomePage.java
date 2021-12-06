package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	//Variables
	WebDriver driver;
	By mensajeExito = By.xpath("/html/body/div[1]/form/div/div/p");
	By informeGestion = By.xpath("//*[@id=\"menu\"]/li[1]/div/a/label");
	By informeMovimientos = By.xpath("//*[@id=\"menu\"]/li[1]/ul/li[1]/a");
	By informeLiquidacion = By.cssSelector("a[href='/infotar/app/informes-gestion/liquidacion-tarjetas']");
	By informeTotales = By.xpath("//*[@id=\"menu\"]/li[1]/ul/li[3]/a");
	By informeDatosTarjetas = By.xpath("//*[@id=\"menu\"]/li[1]/ul/li[4]/a");
	//By consultasOnline = By.xpath("//*[@id=\"menu\"]/li[2]/div/a/label");
	By consultasOnline = By.cssSelector("a[href='/infotar/app/consultas-online']");
	
	//Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Obtener mensaje de bienvenida
	public String getMensajeExito() {
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.textToBe(mensajeExito,"Bienvenid@ a la nueva aplicación de Infotar"));
		return driver.findElement(mensajeExito).getText();
	}
	
	//Ir a Movimientos
		public void irMovimientos() {
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.elementToBeClickable(informeGestion));
			driver.findElement(informeGestion).click();
			driver.findElement(informeMovimientos).click();		
		}
		
		//Ir a Liquidacion de tarjetas
				public void irLiquidacion() {
					WebDriverWait wait = new WebDriverWait(driver,10);
					wait.until(ExpectedConditions.elementToBeClickable(informeGestion));
					driver.findElement(informeGestion).click();
					wait.until(ExpectedConditions.elementToBeClickable(informeLiquidacion));
					driver.findElement(informeLiquidacion).click();		
				}
				
		//Ir a Totales de Tarjeta
		public void irTotalesTarjeta() {
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.elementToBeClickable(informeGestion));
			driver.findElement(informeGestion).click();
			wait.until(ExpectedConditions.elementToBeClickable(informeTotales));
			driver.findElement(informeTotales).click();
		}
		
		//Ir a Datos de Tarjetas
		public void irDatosTarjetas() {
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.elementToBeClickable(informeGestion));
			driver.findElement(informeGestion).click();
			wait.until(ExpectedConditions.elementToBeClickable(informeDatosTarjetas));
			driver.findElement(informeDatosTarjetas).click();
		}
		
		//Ir a Consultas Online
		public void irConsultasOnline() {
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.elementToBeClickable(consultasOnline));
			driver.findElement(consultasOnline).click();
				
		}
	

}
