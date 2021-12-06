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
import pages.LiquidacionTarjetasPage;
import pages.LoginPage;


public class TestLiquidacionTarjetastitular {
	WebDriver driver;
	String PATH_DRIVER = "./drivers/chromedriver.exe";
	String TYPE_DRIVER = "webdriver.chrome.driver";
	String URL = "https://servicios-i.redsys.es:54443/infotar/";

	
	LoginPage login;
	HomePage home;
	LiquidacionTarjetasPage liquidacion;
	
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
		JSONArray array= (JSONArray) logins.get("titular");
		
		String arr[] = new String[array.size()];
		
		JSONObject users = (JSONObject)array.get(0);
		String user = (String) users.get("username");
		String pwd = (String) users.get("password");
			 
		arr[0] = user + "," + pwd;
	
		return arr;
	}
	
	
	@Test(dataProvider = "autenticacion")
	public void testMovimientos(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irLiquidacion();
		liquidacion = new LiquidacionTarjetasPage(driver);
		liquidacion.selectTarjeta();
		liquidacion.selectFechaOpDesde("2020/08/24");
		liquidacion.selectFechaOpHasta("2020/08/29");
		liquidacion.clicarInforme();
		Assert.assertEquals("No se encontraron resultados", liquidacion.getMovimientos());	
	}
	
	@Test(dataProvider = "autenticacion")
	public void testLimpiarFiltros(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irLiquidacion();
		liquidacion = new LiquidacionTarjetasPage(driver);
		liquidacion.selectTarjeta();
		liquidacion.selectFechaOpDesde("2020/08/24");
		liquidacion.selectFechaOpHasta("2020/08/29");
		liquidacion.clicarLimpiarFiltros();
		Assert.assertEquals("Seleccione una opción", liquidacion.filtroLimpio2());
	}
	
	@Test(dataProvider = "autenticacion")
	public void testSeleccionarFiltro(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irLiquidacion();
		liquidacion = new LiquidacionTarjetasPage(driver);
		liquidacion.clicarVerFiltros();
		liquidacion.clicarPrimerFiltro();
		liquidacion.clicarAceptar();
		liquidacion.clicarInforme();
		Assert.assertEquals("No se encontraron resultados", liquidacion.getMovimientos());
	}
	
	@Test(dataProvider = "autenticacion")
	public void testBorrarFiltro(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irLiquidacion();
		liquidacion = new LiquidacionTarjetasPage(driver);
		liquidacion.clicarVerFiltros();
		liquidacion.clicarBorrarFiltro();
		liquidacion.clicarAceptarBorrar();
		Assert.assertEquals("Filtro eliminado con éxito", liquidacion.textoBorrado());		
	}
	
	@Test(dataProvider = "autenticacion")
	public void testGuardarFiltro(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irLiquidacion();
		liquidacion = new LiquidacionTarjetasPage(driver);
		liquidacion.selectTarjeta();
		liquidacion.selectFechaOpDesde("2020/08/24");
		liquidacion.selectFechaOpHasta("2020/08/29");
		liquidacion.clicarInforme();
		liquidacion.clicarGuardarFiltro();
		liquidacion.ponerNombreFiltro("Filtro automatización");
		liquidacion.clicarGuardar();
		Assert.assertEquals("Filtro guardado con éxito", liquidacion.textoGuardado());
		
	}
	
	@Test(dataProvider = "autenticacion")
	public void testFiltroSinNombre(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irLiquidacion();
		liquidacion = new LiquidacionTarjetasPage(driver);
		liquidacion.selectTarjeta();
		liquidacion.clicarGuardarFiltro();
		liquidacion.filtroSinNombre();
		liquidacion.clicarGuardar();
		Assert.assertEquals("El nombre del filtro no puede estar vacío", liquidacion.textoSinNombre());
		
	}
	
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}
}
