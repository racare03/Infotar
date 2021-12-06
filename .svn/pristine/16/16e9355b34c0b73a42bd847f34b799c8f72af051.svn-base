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
import pages.MovimientosPage;

public class TestMovimientos {
	
	WebDriver driver;
	String PATH_DRIVER = "./drivers/chromedriver.exe";
	String TYPE_DRIVER = "webdriver.chrome.driver";
	String URL = "https://servicios-i.redsys.es:54443/infotar/";

	LoginPage login;
	HomePage home;
	MovimientosPage movimientos;
	
	
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
		//FileReader reader = new FileReader(".\\jsonfiles\\data.json");
		FileReader reader = new FileReader("./jsonfiles/data.json");
		
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
		home.irMovimientos();
		movimientos = new MovimientosPage(driver);
		movimientos.clicarInforme();
		Assert.assertEquals("Debe seleccionar un valor para el grupo de empresa y para la empresa", movimientos.getMensaje());
		
		
	}
	
	@Test(dataProvider = "autenticacion")
	public void testMovimientos(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irMovimientos();
		movimientos = new MovimientosPage(driver);
		movimientos.selectGrupo();
		movimientos.selectEmpresa();
		movimientos.selectFechaOpDesde("2020/08/24");
		movimientos.selectFechaOpHasta("2020/08/29");
		movimientos.clicarInforme();
		Assert.assertEquals("GARCIA JUAN BANDERA", movimientos.getMovimientos());		
	}
	
	@Test(dataProvider = "autenticacion")
	public void testLimpiarFiltros(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irMovimientos();
		movimientos = new MovimientosPage(driver);
		movimientos.selectGrupo();
		movimientos.selectEmpresa();
		movimientos.selectFechaOpDesde("2020/08/24");
		movimientos.selectFechaOpHasta("2020/08/29");
		movimientos.clicarLimpiarFiltros();
		Assert.assertEquals("Seleccione una opción", movimientos.filtroLimpio());
	}
	
	@Test(dataProvider = "autenticacion")
	public void testSeleccionarFiltro(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irMovimientos();
		movimientos = new MovimientosPage(driver);
		movimientos.clicarVerFiltros();
		movimientos.clicarPrimerFiltro();
		movimientos.clicarAceptar();
		movimientos.clicarInforme();
		Assert.assertEquals("GARCIA JUAN BANDERA", movimientos.getMovimientos());
	}
	
	@Test(dataProvider = "autenticacion")
	public void testBorrarFiltro(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irMovimientos();
		movimientos = new MovimientosPage(driver);
		movimientos.clicarVerFiltros();
		movimientos.clicarBorrarFiltro();
		movimientos.clicarAceptarBorrar();
		Assert.assertEquals("Filtro eliminado con éxito", movimientos.textoBorrado());		
	}
	
	@Test(dataProvider = "autenticacion")
	public void testGuardarFiltro(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irMovimientos();
		movimientos = new MovimientosPage(driver);
		movimientos.selectGrupo();
		movimientos.selectEmpresa();
		movimientos.selectFechaOpDesde("2020/08/24");
		movimientos.selectFechaOpHasta("2020/08/29");
		movimientos.clicarInforme();
		movimientos.clicarGuardarFiltro();
		movimientos.ponerNombreFiltro("Filtro automatización");
		movimientos.clicarGuardar();
		Assert.assertEquals("Filtro guardado con éxito", movimientos.textoGuardado());
		
	}
	
	@Test(dataProvider = "autenticacion")
	public void testFiltroSinNombre(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irMovimientos();
		movimientos = new MovimientosPage(driver);
		movimientos.selectGrupo();
		movimientos.selectEmpresa();
		movimientos.clicarGuardarFiltro();
		movimientos.filtroSinNombre();
		movimientos.clicarGuardar();
		Assert.assertEquals("El nombre del filtro no puede estar vacío", movimientos.textoSinNombre());
		
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
