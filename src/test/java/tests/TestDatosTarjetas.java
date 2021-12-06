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

public class TestDatosTarjetas {
	
	WebDriver driver;
	String PATH_DRIVER = "./drivers/chromedriver.exe";
	String TYPE_DRIVER = "webdriver.chrome.driver";
	String URL = "https://servicios-i.redsys.es:54443/infotar/";
	String usuario = "s6169rc";
	String pass = "Almoca30";
	
	LoginPage login;
	HomePage home;
	DatosTarjetasPage datosTarjetas;
	
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
		JSONArray array= (JSONArray) logins.get("administradores");
		
		String arr[] = new String[array.size()];
		
		for(int i=0; i<array.size(); i++) {
			 JSONObject users = (JSONObject)array.get(i);
			 String user = (String) users.get("username");
			 String pwd = (String) users.get("password");
			 
			 arr[i] = user + "," + pwd;
		}
		return arr;
	}
	
	@Test(dataProvider = "autenticacion")
	public void testNoGrupoNoEmpresa(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irDatosTarjetas();
		datosTarjetas = new DatosTarjetasPage(driver);
		datosTarjetas.clicarInforme();
		Assert.assertEquals("Debe seleccionar un valor para el grupo de empresa y para la empresa", datosTarjetas.getMensaje());
	}
	
	@Test(dataProvider = "autenticacion")
	public void testDatosTarjetas(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irDatosTarjetas();
		datosTarjetas = new DatosTarjetasPage(driver);
		datosTarjetas.selectGrupo();
		datosTarjetas.selectEmpresa();
		datosTarjetas.clicarInforme();
		Assert.assertEquals("GARCIA JUAN BANDERA", datosTarjetas.getMovimientos());
	}
	
	@Test(dataProvider = "autenticacion")
	public void testLimpiarFiltros(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irDatosTarjetas();
		datosTarjetas = new DatosTarjetasPage(driver);
		datosTarjetas.selectGrupo();
		datosTarjetas.selectEmpresa();
		datosTarjetas.clicarLimpiarFiltros();
		Assert.assertEquals("Seleccione una opción", datosTarjetas.filtroLimpio());
	}
	
	@Test(dataProvider = "autenticacion")
	public void testSeleccionarFiltro(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irDatosTarjetas();
		datosTarjetas = new DatosTarjetasPage(driver);
		datosTarjetas.clicarVerFiltros();
		datosTarjetas.clicarPrimerFiltro();
		datosTarjetas.clicarAceptar();
		datosTarjetas.clicarInforme();
		Assert.assertEquals("GARCIA JUAN BANDERA", datosTarjetas.getMovimientos());
	}
	
	@Test(dataProvider = "autenticacion")
	public void testBorrarFiltro(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irDatosTarjetas();
		datosTarjetas = new DatosTarjetasPage(driver);
		datosTarjetas.clicarVerFiltros();
		datosTarjetas.clicarBorrarFiltro();
		datosTarjetas.clicarAceptarBorrar();
		Assert.assertEquals("Filtro eliminado con éxito", datosTarjetas.textoBorrado());
	}
	
	@Test(dataProvider = "autenticacion")
	public void testGuardarFiltro(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irDatosTarjetas();
		datosTarjetas = new DatosTarjetasPage(driver);
		datosTarjetas.selectGrupo();
		datosTarjetas.selectEmpresa();
		datosTarjetas.clicarInforme();
		datosTarjetas.clicarGuardarFiltro();
		datosTarjetas.ponerNombreFiltro("Filtro automatización");
		datosTarjetas.clicarGuardar();
		Assert.assertEquals("Filtro guardado con éxito", datosTarjetas.textoGuardado());
	}
	
	@Test(dataProvider = "autenticacion")
	public void testFiltroSinNombre(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irDatosTarjetas();
		datosTarjetas = new DatosTarjetasPage(driver);
		datosTarjetas.selectGrupo();
		datosTarjetas.selectEmpresa();
		datosTarjetas.clicarGuardarFiltro();
		datosTarjetas.filtroSinNombre();
		datosTarjetas.clicarGuardar();
		Assert.assertEquals("El nombre del filtro no puede estar vacío", datosTarjetas.textoSinNombre());	
		}
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
	
}
