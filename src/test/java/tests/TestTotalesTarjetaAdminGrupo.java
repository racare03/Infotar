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

import pages.HomePage;
import pages.LoginPage;
import pages.TotalesTarjetaPage;


public class TestTotalesTarjetaAdminGrupo {
	WebDriver driver;
	String PATH_DRIVER = "./drivers/chromedriver.exe";
	String TYPE_DRIVER = "webdriver.chrome.driver";
	String URL = "https://servicios-i.redsys.es:54443/infotar/";
	
	
	LoginPage login;
	HomePage home;
	TotalesTarjetaPage totales;
	
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
		JSONArray array= (JSONArray) logins.get("adminGrupo");
		
		String arr[] = new String[array.size()];
		
		JSONObject users = (JSONObject)array.get(0);
		String user = (String) users.get("username");
		String pwd = (String) users.get("password");
			 
		arr[0] = user + "," + pwd;
	
		return arr;
	}
	
	@Test(dataProvider = "autenticacion")
	public void testNoGrupoNoEmpresa(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irTotalesTarjeta();
		totales = new TotalesTarjetaPage(driver);
		totales.clicarInforme();
		Assert.assertEquals("Debe seleccionar un valor para el grupo de empresa y para la empresa", totales.getMensaje());
		
		
	}
	
	@Test(dataProvider = "autenticacion")
	public void testTotales(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irTotalesTarjeta();
		totales = new TotalesTarjetaPage(driver);
		totales.selectEmpresa();
		totales.selectFechaOpDesde("2020/08/24");
		totales.selectFechaOpHasta("2020/08/29");
		totales.clicarInforme();
		Assert.assertEquals("GARCIA JUAN BANDERA", totales.getMovimientos());		
	}
	
	@Test(dataProvider = "autenticacion")
	public void testLimpiarFiltros(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irTotalesTarjeta();
		totales = new TotalesTarjetaPage(driver);
		totales.selectEmpresa();
		totales.selectFechaOpDesde("2020/08/24");
		totales.selectFechaOpHasta("2020/08/29");
		totales.clicarLimpiarFiltros();
		Assert.assertEquals("Seleccione una opción", totales.filtroLimpio());
	}
	
	@Test(dataProvider = "autenticacion")
	public void testSeleccionarFiltro(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irTotalesTarjeta();
		totales = new TotalesTarjetaPage(driver);
		totales.clicarVerFiltros();
		totales.clicarPrimerFiltro();
		totales.clicarAceptar();
		totales.clicarInforme();
		Assert.assertEquals("GARCIA JUAN BANDERA", totales.getMovimientos());
	}
	
	@Test(dataProvider = "autenticacion")
	public void testBorrarFiltro(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irTotalesTarjeta();
		totales = new TotalesTarjetaPage(driver);
		totales.clicarVerFiltros();
		totales.clicarBorrarFiltro();
		totales.clicarAceptarBorrar();
		Assert.assertEquals("Filtro eliminado con éxito", totales.textoBorrado());		
	}
	
	@Test(dataProvider = "autenticacion")
	public void testGuardarFiltro(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irTotalesTarjeta();
		totales = new TotalesTarjetaPage(driver);
		totales.selectEmpresa();
		totales.selectFechaOpDesde("2020/08/24");
		totales.selectFechaOpHasta("2020/08/29");
		totales.clicarInforme();
		totales.clicarGuardarFiltro();
		totales.ponerNombreFiltro("Filtro automatización");
		totales.clicarGuardar();
		Assert.assertEquals("Filtro guardado con éxito", totales.textoGuardado());
		
	}
	
	@Test(dataProvider = "autenticacion")
	public void testFiltroSinNombre(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irTotalesTarjeta();
		totales = new TotalesTarjetaPage(driver);
		totales.selectEmpresa();
		totales.clicarGuardarFiltro();
		totales.filtroSinNombre();
		totales.clicarGuardar();
		Assert.assertEquals("El nombre del filtro no puede estar vacío", totales.textoSinNombre());
		
	}
	
	
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}

