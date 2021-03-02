package com.selenium.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

import com.selenium.database.Conectar;


@RunWith(Parameterized.class)
public class RenunciaConRestitucion {
	
	static Properties propiedades = new Properties();
    static InputStream entrada = null;
	
    private static Logger log = Logger.getLogger(RenunciaConRestitucion.class);
    
	private static WebDriver driver;
	private Map<String, Object> vars;
	JavascriptExecutor js;
	// public static String Secuencia;
	// public static String Tipo_Identificacion;

	// Ubicacion de Elementos

	By usuario = By.xpath("//*[@id=\"username\"]");
	By clave = By.id("password");
	By BotonIngresar = By.id("sId");
	By CheckForzar = By.xpath("//*[@id=\"forcelogin\"]");

	//String nombre_usuario = "208045";
	//String password = "GAUS20";

	// elementos 2do Formulario

	By BotonConfiguracionAvanzada = By.id("details-button");
	By LinkContinuar = By.id("proceed-link");

	// elementos 3r Formulario

	  By CampoBusqueda = By.id("search-query");
	 //By MiCasaYa = By.xpath("//*[@id=\"id_69\"]/div/a"); 
	//By MiCasaYa = By.xpath("//*[@id=\'id_1\']/div/a");
	 //QA
	  By MiCasaYa = By.xpath("//*[@id=\"id_5\"]/div/a");

	// elementos 4to Formulario
	// By RegistrarHogar =
	// By.xpath("//*[@id=\"terceros:tbody_element\"]/tr[2]/td/a");
	 By RenunciasalSubsidio = By.xpath("//*[@id=\"terceros:tbody_element\"]/tr[6]/td/a");
	//By RenunciasalSubsidio = By.xpath("//*[@id='terceros:tbody_element']/tr[6]/td/a");

	// Elementos 5 formulario Renuncia
	//By IdentificadordelHogar = By.xpath("//*[@id=\"formRenunciaRC:idFamilia\"]"); 
	By IdentificadordelHogar = By.xpath("//*[@id=\"formAnulaRC:idFamilia\"]");
	
	// By BotonBuscar = By.xpath("//*[@id=\"validarButton\"]");
	//By BotonBuscar = By.xpath("//*[@id=\"formRenunciaRC:_id34\"]"); 
	By BotonBuscar = By.xpath("//*[@id=\"formAnulaRC:_id34\"]");
	
	By Renunciar = By.xpath("//*[@id=\"formResultados:_id49\"]"); 
	By Nuevo = By.xpath("//*[@id=\"formResultados:_id51\"]"); 

	@Parameterized.Parameters

	public static Collection<Object[]> listaTextos() {

		List<Object[]> args = new ArrayList<>();

		try {

			Conectar oracle = new Conectar();
			Connection con;
			con = oracle.conectar();

			// String sql = "Select * from mcy_hogar ho where ho.id_hogar=3577";
			// String sql = "Select * from mcy_hogar";
			 String sql = "select Numero_identificacion,nombre from terceros WHERE ROWNUM<= 2";
			//String sql = "SELECT MH.ID_HOGAR,MH.ESTADO_ACTUAL,MSC.ID_ESTADO  FROM MCY_HOGAR MH  INNER JOIN MCY_SUBSIDIO_COMPLEMENTARIO MSC ON MH.ID_HOGAR = MSC.ID_HOGAR AND MH.ESTADO_ACTUAL = '12' AND MSC.ID_ESTADO = '10'";

			Statement statement = con.createStatement();

			ResultSet result = statement.executeQuery(sql);

			// Extraer Data

			while (result.next()) {

				Object[] argumento = new Object[] {

						result.getString(1),
						// result.getString(4),

				};
				// }

				args.add(argumento);
				// result.close();
				// statement.close();
				// System.out.println(repo);
			}
		} catch (Exception ex) {
			// Do nothing ...
		}

		return args;

	}

	public static String IdHogar;

	public RenunciaConRestitucion(String IdHogar) {
		this.IdHogar = IdHogar;

	}

	@Before
	public void setUp() {

		try {
			entrada = new FileInputStream("datos.properties");
			propiedades.load(entrada); 
			String rutadriver = propiedades.getProperty("rutadriver"); 
			  
			//System.setProperty("webdriver.chrome.driver", "C:\\Users\\jpataco\\eclipse-workspace\\Proyecto_1\\driver\\chromedriver.exe");
			 System.setProperty("webdriver.chrome.driver", rutadriver);
			 
		    driver = new ChromeDriver();
		    js = (JavascriptExecutor) driver;
		    vars = new HashMap<String, Object>();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  private static void captureScreenshot() throws IOException {
			try {
				
				entrada = new FileInputStream("datos.properties");
				propiedades.load(entrada); 
			    String rutaimagenes = propiedades.getProperty("rutaimagenesrenunciasconrestitucion");
		
			Date d = new Date();

			String FileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

			
			FileHandler.copy(screenshot, new File(rutaimagenes + FileName));
			
			} catch (IOException e) {
				// TODO Auto-generated catch blocko
				e.printStackTrace();
			}
			
		}
	

	@After
	public void tearDown() {
		 driver.quit();
	}

	@Test

	public void testRegistro() throws InterruptedException {

		 try {
		 entrada = new FileInputStream("datos.properties");
		 propiedades.load(entrada); 
		 
		 String url1 = propiedades.getProperty("url");
		 String url2 = propiedades.getProperty("url2");
		 String usuario1 = propiedades.getProperty("usuario1");	 
		 String password = propiedades.getProperty("password1");
		
		System.out.println(IdHogar);

		// driver.get("http://localhost:9090/sso-auth-server/login");
		driver.get(url1);
		
		driver.findElement(usuario).click();
		driver.findElement(usuario).sendKeys(usuario1);
		driver.findElement(clave).click();
		driver.findElement(clave).sendKeys(password);
		driver.findElement(BotonIngresar).click();
		Thread.sleep(2000);

		// Forzar login
		   Boolean b1 = driver.equals(CheckForzar);
		     System.out.println("CheckForzar :" + b1);
		  
		      if (b1==true) {

		        driver.findElement(CheckForzar).click();
		        Thread.sleep(2000); 
		        driver.findElement(usuario).click();
		        driver.findElement(usuario).sendKeys(usuario1);
		        driver.findElement(clave).click();
		        driver.findElement(clave).sendKeys(password);
		        driver.findElement(BotonIngresar).click();
		        
		      }

		// 2do Formulario/ Conexion no es privada
//		 driver.findElement(BotonConfiguracionAvanzada).click();
//		 Thread.sleep(2000);
//		 driver.findElement(LinkContinuar).click();
//		 Thread.sleep(2000);

		// 3er Formulario /menu escoger proyecto
		driver.findElement(CampoBusqueda).click();
		driver.findElement(CampoBusqueda).sendKeys("Mi Casa Ya");
		driver.findElement(MiCasaYa).click();
		driver.get("https://miportafoliodev.cifin.co/cifin/MiCasaYa/faces/menu?destino=Menu");
		Thread.sleep(5000);
	
		      
		// driver.get(url2);
		// Thread.sleep(3000);

		// 4to Formulario /Escoger Opcion Mi Casa Ya.

		  driver.findElement(RenunciasalSubsidio).click();
		  Thread.sleep(2000);

		// 5to Formulario //Formulario Renuncias al Subsidio

		  
		// driver.findElement(CampoCedula).sendKeys("1037479977");
		// driver.findElement(CampoCedula).sendKeys(Integer.toString(id_hogar));
		 System.out.println("el identificador de hogar es:" + IdHogar);
		 driver.findElement(IdentificadordelHogar).sendKeys(IdHogar);
		Thread.sleep(2000);
		// driver.findElement(BotonAdicionarIntegrante).click();

		// Boton Buscar
		driver.findElement(BotonBuscar).click();
		Thread.sleep(10000);

		// Boton RENUNCIAR SUBSIDO
		//driver.findElement(Renunciar).click();
		//Thread.sleep(10000);
		captureScreenshot();
		
		// Boton Nuevo
		driver.findElement(Nuevo).click();
		Thread.sleep(10000);
		 }
	     catch (IOException e) {
	 		// TODO Auto-generated catch block
	 		e.printStackTrace();
	 		driver.quit();
	 	}
	}
}
	
	


