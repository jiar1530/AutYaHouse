package com.selenium.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;


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
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;
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

import com.selenium.database.Conectar;


@RunWith(Parameterized.class)
public class BloqueoHogar {
	
	private static Logger log = Logger.getLogger(ActosAdministrativos.class);
	static Properties propiedades = new Properties();
    static InputStream entrada = null;	
	
 
  private static WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
 // public static String Secuencia;
//  public static String Tipo_Identificacion;
 
  public static void setClipboardData(String string) {
      StringSelection stringSelection = new StringSelection(string);
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
}

    
  //Ubicacion de Elementos
 
  
  By usuario = By.xpath("//*[@id=\"username\"]");
  By clave = By.id("password");
  By BotonIngresar = By.id("sId");
  By CheckForzar = By.xpath("//*[@id=\"forcelogin\"]");

  
//  String nombre_usuario = "90681";
//  String password = "H33std";
//  
  //elementos 2do Formulario
  
  By BotonConfiguracionAvanzada = By.id("details-button");
  By LinkContinuar = By.id("proceed-link");

  //elementos 3r Formulario

  By CampoBusqueda = By.id("search-query");
  //Dev                     
    By MiCasaYa = By.xpath(" //*[@id=\"id_1\"]/div/a");
   // By MiCasaYa = By.xpath("//*[@id=\"id_45\"]/div/a");
  //By MiCasaYa = By.xpath("//*[@id=\"id_46\"]/div/a");
 
 
 // elementos 4to Formulario
    By BloqueoHogar = By.xpath("//*[@id=\"terceros:tbody_element\"]/tr[14]/td/a");
   // By BloqueoHogar = By.xpath("//*[@id=\"id_1\"]/div/a");
 
  
    
    By SeleccionarArchivo = By.xpath("//*[@id=\"archivo\"]");

     By Generar = By.xpath("//*[@id=\"content\"]/input[1]");
   
     By selector2 = By.xpath("//*[@id=\"valoresArchivo\"]/tbody/tr/td[2]");
     
     By Nuevo = By.xpath("//*[@id=\"formResultados\"]/div[5]/input");
  
  
   @Parameterized.Parameters   
  
   public static Collection<Object[]> listaTextos() {
		  
	   List<Object[]> args = new ArrayList<>();
	     
	   

	 	 try {
	 		  
	 		Conectar oracle=new Conectar();
	 	 	Connection con;
	 	 	con=oracle.conectar();
	 		  
 
	 	 	//String sql = "select distinct numero_identificacion from mcy_excepcion where numero_identificacion between '1002258266' and '1002269993' order by numero_identificacion asc"; 
	 	 	String sql = "select a.Numero_identificacion,a.nombre,a.lugar_expedicion,b.nombre_departamento,a.fecha_nacimiento from terceros a,departamentos b where b.codigo_departamento = a.departamento_expedicion and ROWNUM <= 2";
	 	 	
	 	 	
	 	 	
	 	 	
	 		  Statement statement = con.createStatement();
	 		  
	 		  ResultSet result = statement.executeQuery(sql);
	 		
	 		//Extraer Data
	 		   
	 		 
	 		  while (result.next()) {
	 			  
	 			  Object[] argumento = new Object[] { 
	 					  
	 					    result.getString(1),
	 					    result.getString(2),
	 					    result.getString(3),
	 					    result.getString(4),
	 					    result.getString(5),
	 					 
	 			  };
	 		 // }  
	 			  
	 			    args.add(argumento);
	 			//    result.close();
	 			//    statement.close();
	 		//	 System.out.println(repo);
	 		  }
	 		}
	 	catch (Exception ex) {
//	 		  // Do nothing ... 
	 	}
	 		  
	 	return args;

	 	}
	 	   
 

   public static String Cedula;
   public static String Nombre;
   public static String Lugar_expedicion;
   public static String Nombre_Departamento;
   public static String Fecha_Nacimiento1;
   
   
  
   public BloqueoHogar (String Cedula, String Nombre, String Lugar_expedicion,String Nombre_Departamento, String Fecha_Nacimiento1) {
	   this.Cedula = Cedula;
	   this.Nombre = Nombre;
	   this.Lugar_expedicion = Lugar_expedicion;
	   this.Nombre_Departamento = Nombre_Departamento;
	   this.Fecha_Nacimiento1 = Fecha_Nacimiento1;
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
 		    String rutaimagenes = propiedades.getProperty("rutaimagenesregistrarobligatorios");
 	
 		Date d = new Date();
  
 		String FileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
  
 		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
  
 		
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
  
  public void testRegistro() throws InterruptedException, AWTException {
	  
	  try {
			entrada = new FileInputStream("datos.properties");
			 propiedades.load(entrada); 
			 
			 String url = propiedades.getProperty("url");
			 String url2 = propiedades.getProperty("url2");
			 String usuario1 = propiedades.getProperty("usuario1");	 
			 String password = propiedades.getProperty("password1");
			     
			 
			 driver.get(url);
			 driver.findElement(usuario).click();
		     driver.findElement(usuario).sendKeys(usuario1);
		     driver.findElement(clave).click();
		     driver.findElement(clave).sendKeys(password);
		     driver.findElement(BotonIngresar).click();
		     Thread.sleep(2000);
		     
		     //Forzar login
			  
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
		    
			 
			 System.out.println(Cedula);
		     
		     //3er Formulario /menu escoger proyecto 
			    driver.findElement(CampoBusqueda).click();
			    driver.findElement(CampoBusqueda).sendKeys("Mi Casa Ya");
			    Thread.sleep(2000);
			    //driver.findElement(MiCasaYa).click();
			    driver.get("https://miportafoliodev.cifin.co/cifin/MiCasaYa/faces/menu?destino=Menu");
			    Thread.sleep(2000);
			    

			    
			    
		    
		    //4to Formulario /Escoger Opcion Mi Casa Ya.
		    driver.findElement(BloqueoHogar).click();
		    Thread.sleep(2000);
		    System.out.println("Ingreso a modulo Bloqueo Hogar:TRUE");
		    Thread.sleep(3000);
		      
		     driver.findElement(selector2).click();
		     Thread.sleep(3000);
		 
		     
		     
		     //Solucion x
		     
		       setClipboardData("BloqueoHogares.csv");
		       Robot robot = new Robot();
		       robot.keyPress(KeyEvent.VK_CONTROL);
		       robot.keyPress(KeyEvent.VK_V);
		       robot.keyRelease(KeyEvent.VK_V);
		       robot.keyRelease(KeyEvent.VK_CONTROL);
		       robot.keyPress(KeyEvent.VK_ENTER);
		       robot.keyRelease(KeyEvent.VK_ENTER);
		     
		       Thread.sleep(3000);
		       driver.findElement(Generar).click();
		       Thread.sleep(10000);
		       captureScreenshot();
//		       driver.findElement(Nuevo).click();
//		       Thread.sleep(3000);
		   
		       System.out.println("Bloqueo Hogar:TRUE");
				
				 URL url1 = Loader.getResource("log4j.properties");  // Esto carga el fichero como recurso si está en el CLASSPATH
			     PropertyConfigurator.configure(url1);
			     log.info("Log4j, Bloqueo Hogar Exitoso.");
		       
 
	  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			driver.quit();
		}
  }
}