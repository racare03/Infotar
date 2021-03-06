package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import pages.HomePage;
import pages.LoginPage;

public class TestLogin {
	
	WebDriver driver;
	String PATH_DRIVER = "./drivers/chromedriver.exe";
	String TYPE_DRIVER = "webdriver.chrome.driver";
	String URL = "https://servicios-i.redsys.es:54443/infotar/";
	//String usuario = "s6169rc";
	//String pass = "Almoca25";
	
	LoginPage login;
	HomePage home;
	
	
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty(TYPE_DRIVER, PATH_DRIVER);
		driver = new ChromeDriver();
		
//		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
//		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.get(URL);
		login = new LoginPage(driver);
	}
	
	@DataProvider(name = "autenticacion")
	public Object[][] getData(){
		Object[][]data = new Object[3][2];
		
		data[0][0]="s6169rc"; data[0][1]="Almoca30";
		data[1][0]="E0049S79"; data[1][1]="Lunes1510";
		data[2][0]="E0049S81"; data[2][1]="Lunes1510";
		return data;		
	}
	
	@Test(dataProvider = "autenticacion")
    public void testLoginExito(String usuario, String pass) {
		login.iniciarSesion(usuario, pass);
		//verificación
		home = new HomePage(driver);
		Assert.assertEquals("Bienvenid@ a la nueva aplicación de Infotar", home.getMensajeExito());
	}
	
	@Test
	public void testLoginError() {
		login.iniciarSesion("abcde", "abcede45");
		Assert.assertEquals("Error, credenciales incorrectas", login.getMensajeError());
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
