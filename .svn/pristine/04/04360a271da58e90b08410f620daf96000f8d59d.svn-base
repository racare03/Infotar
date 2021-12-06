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

import pages.ConsultasOnlinePage;
import pages.HomePage;
import pages.LoginPage;

public class TestConsultasOnlineAdminGrupo {
	WebDriver driver;
	String PATH_DRIVER = "./drivers/chromedriver.exe";
	String TYPE_DRIVER = "webdriver.chrome.driver";
	String URL = "https://servicios-i.redsys.es:54443/infotar/";

	
	LoginPage login;
	HomePage home;
	ConsultasOnlinePage consultasOnline;
	
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
	public void consultaOnline(String data) {
		String users[] = data.split(",");
		login.iniciarSesion(users[0], users[1]);
		home = new HomePage(driver);
		home.irConsultasOnline();
		consultasOnline = new ConsultasOnlinePage(driver);
		consultasOnline.selectEmpresa();
		consultasOnline.selectDepartamento();
		consultasOnline.selectTitular();
		consultasOnline.selectTarjeta();
		consultasOnline.clicarConfirmar();
		Assert.assertEquals("409111******8014", consultasOnline.getMensajeTarjeta());
			
	}
	
	
	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
