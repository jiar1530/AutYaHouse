package com.selenium.test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
//import org.apache.commons.io.FileUtils;
import java.util.Random;

import javax.imageio.ImageIO;

import com.selenium.test.MarcarHogarObligatorios;  
import org.apache.commons.io.FileUtils;
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
//import org.testng.reporters.Files;
import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;


import com.selenium.database.Conectar;


@RunWith(Parameterized.class)
public class ActosAdministrativosQA {
	
	private static Logger log = Logger.getLogger(ActosAdministrativosQA.class);
	static Properties propiedades = new Properties();
    static InputStream entrada = null;	
	
 
    private static WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
 // public static String Secuencia;
//  public static String Tipo_Identificacion;
 
    
  //Ubicacion de Elementos
 
  
  By usuario = By.xpath("//*[@id=\"username\"]");
  By clave = By.id("password");
  By BotonIngresar = By.id("sId");
  By CheckForzar = By.xpath("//*[@id=\"forcelogin\"]");

    
  //elementos 2do Formulario
  
  By BotonConfiguracionAvanzada = By.id("details-button");
  By LinkContinuar = By.id("proceed-link");

  //elementos 3r Formulario

  By CampoBusqueda = By.id("search-query");
                     
//ambiente dev
  //  By MiCasaYa = By.xpath("//*[@id=\"id_46\"]/div/a"); 
     //By MiCasaYa = By.xpath("//*[@id=\"id_69\"]/div/a"); 
	//By MiCasaYa = By.xpath("//*[@id=\"id_1\"]/div/a"); 
	 //Ambiente uat 
  //  By MiCasaYa = By.xpath("//*[@id=\'id_18\']/div/a");
    //QA
    By MiCasaYa = By.xpath("//*[@id=\"id_5\"]/div/a");
   

// elementos 4to Formulario
	//By ActosAdministrativos = By.xpath("//*[@id=\'terceros:tbody_element\']/tr[4]/td/a"); 
	By ActosAdministrativos = By.xpath("//*[@id=\"terceros:tbody_element\"]/tr[3]/td/a"); 
	
	
  
  //Elementos 5 formulario Registro
	By IdentificadordeHogar = By.xpath("//*[@id=\'idFamilia\']");
	//By IdentificadordeHogar = By.xpath("//*[@id=\"formRegCob:idFamilia\"]");
	By Fecha = By.xpath("//*[@id=\'FechaActo\']"); //*[@id="FechaActo"]
	By NúmerodeActoAdministrativo = By.xpath("//*[@id=\'numActo\']");
	By BotonLimpiar = By.xpath("//*[@id=\'content\']/input[2]");
	By BotonGenerar = By.xpath("//*[@id=\'content\']/input[1]");
	//By BotonGenerar = By.xpath("//*[@id=\"formRegCob:_id34\"]");
	
	By BotonNuevo  = By.xpath("//*[@id='btnNuevo']");
	
 
	
   @Parameterized.Parameters   
  
   public static Collection<Object[]> listaTextos() {
		  
	   List<Object[]> args = new ArrayList<>();
	     
	   

	 	 try {
	 		  
	 		Conectar oracle=new Conectar();
	 	 	Connection con;
	 	 	con=oracle.conectar();
	 		  
	
 		   
	 	 	//String sql = "select distinct numero_identificacion from mcy_excepcion where numero_identificacion between '1002258266' and '1002269993' order by numero_identificacion asc"; 
	 	 	//String sql = "select distinct numero_identificacion from mcy_excepcion where numero_identificacion between '1002258266' and '1002258326' order by numero_identificacion asc";
	 	 	//String sql = "select id_hogar from mcy_hogar WHERE estado_actual=3 and ROWNUM <= 2";
	 	 	String sql = "select id_hogar from mcy_hogar WHERE estado_actual= '3' and id_hogar = '6478'";
	 	 	
	 	 	
	 		  Statement statement = con.createStatement();
	 		  
	 		  ResultSet result = statement.executeQuery(sql);
	 		
	 		//Extraer Data
	 		   
	 		 
	 		  while (result.next()) {
	 			  
	 			  Object[] argumento = new Object[] { 
	 					  
	 					    result.getString(1),
	 					  // result.getString(2),
	 					  // result.getString(3),
	 					  // result.getString(4),
	 					  // result.getString(5),
	 					 
	 			  };
	 		 // }  
	 			  
	 			    args.add(argumento);
	 			    result.close();
	 			    statement.close();
	 		//	 System.out.println(repo);
	 		  }
	 		}
	 	catch (Exception ex) {
//	 		  // Do nothing ... 
	 	}
	 		  
	 	return args;

	 	}
	 	   
 
   public static String id_hogar;
 //public static String FECHA;
 //public static String NumeroActoAdministrativo;
   
   
 
   public ActosAdministrativosQA(String id_hogar) {
		this.id_hogar = id_hogar;
		//this.FECHA = FECHA;
		//this.NumeroActoAdministrativo = NumeroActoAdministrativo;

	}
   
 
  @Before
  public void setUp() throws InterruptedException {
	try {
		entrada = new FileInputStream("datos.properties");
		propiedades.load(entrada); 
		String rutadriver = propiedades.getProperty("rutadriver"); 
	
		
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
 		    String rutaimagenes = propiedades.getProperty("rutaimagenesactosadministrativos");
 	
 		Date d = new Date();
  
 		String FileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
  
 		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
  
 		//FileHandler.copy(screenshot, new File("C:\\Users\\jpataco\\eclipse-workspace\\Proyecto_1\\output\\RegistroHogar_O_" + FileName));
 		FileHandler.copy(screenshot, new File(rutaimagenes + FileName));
 		
 		} catch (IOException e) {
			// TODO Auto-generated catch block
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
			 
			 String url = propiedades.getProperty("url");
			 String usuario1 = propiedades.getProperty("usuario1");	 
			 String password = propiedades.getProperty("password1");
			 String fecha_acto = propiedades.getProperty("fecha_acto");
	         String numero_acto = propiedades.getProperty("numero_acto"); 
	         
	         System.out.println(id_hogar);
	 		//System.out.println(FECHA);
	 		//System.out.println(NumeroActoAdministrativo);
			 
			 driver.get(url);
				driver.findElement(usuario).click();
				driver.findElement(usuario).sendKeys(usuario1);
				driver.findElement(clave).click();
				driver.findElement(clave).sendKeys(password);
				driver.findElement(BotonIngresar).click();
				Thread.sleep(3000);

				//Forzar login
				Boolean b1 = driver.equals(CheckForzar);	     
				if (b1==false) {
					
					
					driver.findElement(CheckForzar).click();
					Thread.sleep(2000); 
					driver.findElement(usuario).click();
					driver.findElement(usuario).sendKeys(usuario1);
					driver.findElement(clave).click();
					driver.findElement(clave).sendKeys(password);
					driver.findElement(BotonIngresar).click();

				}
		     	     
		     
		     //3er Formulario /menu escoger proyecto 
			    driver.findElement(CampoBusqueda).click();
			    driver.findElement(CampoBusqueda).sendKeys("Mi Casa Ya");
			    Thread.sleep(2000);
			    driver.findElement(MiCasaYa).click();
			  //driver.get("https://aplicaciones.cifin.co/cifin/MiCasaYa")
			    Thread.sleep(3000);
		    
		 
			  //4to Formulario /Escoger Opcion Mi Casa Ya.
				driver.findElement(ActosAdministrativos).click();
				Thread.sleep(8000);
				System.out.println("Ingreso a modulo Actos Administrativos:TRUE");

				
				driver.findElement(IdentificadordeHogar).click();
				//  driver.findElement(CampoCedula).sendKeys("1037479977");
				//  driver.findElement(CampoCedula).sendKeys(Integer.toString(id_hogar));
				driver.findElement(IdentificadordeHogar).sendKeys(id_hogar);
				//driver.findElement(Fecha).sendKeys("12/11/2020");
				driver.findElement(Fecha).sendKeys(fecha_acto);
				//driver.findElement(NúmerodeActoAdministrativo).sendKeys("44");
				driver.findElement(NúmerodeActoAdministrativo).sendKeys(numero_acto);
				captureScreenshot();
//				driver.findElement(BotonLimpiar).click();
//				Thread.sleep(10000);
				
				driver.findElement(BotonGenerar).click();
				Thread.sleep(10000);
				captureScreenshot();
				
			//	driver.findElement(BotonNuevo).click();
			//	Thread.sleep(10000);
				 
				 System.out.println("Actos Administrativos:TRUE");
				 URL url1 = Loader.getResource("log4j.properties");  // Esto carga el fichero como recurso si está en el CLASSPATH
			     PropertyConfigurator.configure(url1);
			     log.info("Log4j, Acto Administrativo Exitoso.");
			    		    
		    
	  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 URL url1 = Loader.getResource("log4j.properties");  // Esto carga el fichero como recurso si está en el CLASSPATH
		     PropertyConfigurator.configure(url1);
		     log.error("Log4j, Automatizacion Fallida.");
		     System.out.println("Actos Administrativos:FALSE");
		     driver.quit();		    			
		}
  }
}