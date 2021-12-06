package tests;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.DatosTarjetasPage;
import pages.HomePage;
import pages.LoginPage;

public class TestDatosTarjetasAdminEmpresa {
	WebDriver driver;
	String PATH_DRIVER = "./drivers/chromedriver.exe";
	String TYPE_DRIVER = "webdriver.chrome.driver";
	String URL = "https://servicios-i.redsys.es:54443/infotar/";
	
	
	LoginPage login;
	HomePage home;
	DatosTarjetasPage datos;
	
	@BeforeMethod
	public void beforeMethod() {
		System.setProperty(TYPE_DRIVER, PATH_DRIVER);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		login = new LoginPage(driver);
	}
	
	@DataProvider(name = "autenticacion")
	public String[] readJSON() throws IOException, ParseException{
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader(".\\jsonfiles\\data.json");
		
		Object obj = jsonParser.parse(reader);
		
		JSONObject logins = (JSONObject)obj;
		JSONArray array= (JSONArray) logins.get("adminEmpresa");
		
		String arr[] = new String[array.size()];
		
		JSONObject users = (JSONObject)array.get(0);
		String user = (String) users.get("username");
		String pwd = (String) users.get("password");
			 
		arr[0] = user + "," + pwd;
	
		return arr;
	}
	
	@Test(dataProvider = "autenticacion")
	public void testNoEmpresa(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irDatosTarjetas();
		datos = new DatosTarjetasPage(driver);
		datos.clicarInforme();
		Assert.assertEquals("Debe seleccionar un valor para la empresa", datos.getMensaje2());
		
		
	}
	
	@Test(dataProvider = "autenticacion")
	public void testDatosTarjetas(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irDatosTarjetas();
		datos = new DatosTarjetasPage(driver);
		datos.selectEmpresa();
		datos.clicarInforme();
		Assert.assertEquals("GARCIA JUAN BANDERA", datos.getMovimientos());		
	}
	
	@Test(dataProvider = "autenticacion")
	public void testLimpiarFiltros(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irDatosTarjetas();
		datos = new DatosTarjetasPage(driver);
		datos.selectEmpresa();
		datos.clicarLimpiarFiltros();
		Assert.assertEquals("Seleccione una opción", datos.filtroLimpio());
	}
	
	@Test(dataProvider = "autenticacion")
	public void testSeleccionarFiltro(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irDatosTarjetas();
		datos = new DatosTarjetasPage(driver);
		datos.clicarVerFiltros();
		datos.clicarPrimerFiltro();
		datos.clicarAceptar();
		datos.clicarInforme();
		Assert.assertEquals("GARCIA JUAN BANDERA", datos.getMovimientos());
	}
	
	@Test(dataProvider = "autenticacion")
	public void testBorrarFiltro(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irDatosTarjetas();
		datos = new DatosTarjetasPage(driver);
		datos.clicarVerFiltros();
		datos.clicarBorrarFiltro();
		datos.clicarAceptarBorrar();
		Assert.assertEquals("Filtro eliminado con éxito", datos.textoBorrado());		
	}
	
	@Test(dataProvider = "autenticacion")
	public void testGuardarFiltro(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irDatosTarjetas();
		datos = new DatosTarjetasPage(driver);
		datos.selectEmpresa();
		datos.clicarInforme();
		datos.clicarGuardarFiltro();
		datos.ponerNombreFiltro("Filtro automatización");
		datos.clicarGuardar();
		Assert.assertEquals("Filtro guardado con éxito", datos.textoGuardado());
		
	}
	
	@Test(dataProvider = "autenticacion")
	public void testFiltroSinNombre(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irDatosTarjetas();
		datos = new DatosTarjetasPage(driver);
		datos.selectEmpresa();
		datos.clicarGuardarFiltro();
		datos.filtroSinNombre();
		datos.clicarGuardar();
		Assert.assertEquals("El nombre del filtro no puede estar vacío", datos.textoSinNombre());
		
	}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
