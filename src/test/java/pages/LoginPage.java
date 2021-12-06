package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	//Variables
	WebDriver driver;
	
	By usuario = By.id("username");
	By password = By.id("password");
	By boton = By.id("bLogin");
	By mensajeError = By.xpath("//*[@id=\"text-dialog\"]/p");
	
	//Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Hacer loguin
		public void iniciarSesion(String user, String pass) {
			//cambiar al frame
			driver.switchTo().frame("frameContenido");
			//rellenar usuario pass y dar click al botón
			driver.findElement(usuario).sendKeys(user);
			driver.findElement(password).sendKeys(pass);
			driver.findElement(boton).click();
		}
		
		//Obtener mensaje de error
		public String getMensajeError() {
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.textToBe(mensajeError,"Error, credenciales incorrectas"));
			return driver.findElement(mensajeError).getText();
		}
	
}
