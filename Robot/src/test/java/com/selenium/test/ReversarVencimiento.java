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
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

import com.selenium.database.Conectar;


@RunWith(Parameterized.class)
public class ReversarVencimiento {
	
	 
	private static Logger log = Logger.getLogger(ReversarVencimiento.class);

	static Properties propiedades = new Properties();
    static InputStream entrada = null;
    	

  private static WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
 
 
    
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
   //By MiCasaYa = By.xpath("//*[@id='myTable']/tbody/tr[1]/td");
   By MiCasaYa = By.name("/cifin/MiCasaYa/");
   
  

  
// elementos 4to Formulario
	//By ReversarVencimiento = By.xpath("//*[@id=\'terceros:tbody_element\']/tr[4]/td/a"); 
	By ReversarVencimiento = By.xpath("//*[@id=\"terceros:tbody_element\"]/tr[19]/td/a"); 
	

	//Elementos 5 formulario Registro
	By IdentificadorHogar = By.xpath("//*[@id=\'formRenunciaRC:idFamilia\']");
	 //*[@id="formRenunciaRC:idFamilia"]
	By BotonLimpiar = By.xpath("//*[@id=\'formRenunciaRC:_id35\']");
	By BotonBuscar = By.xpath("//*[@id=\'formRenunciaRC:_id34\']");	
	By BotonReversarVencimiento = By.xpath("//*[@id=\"formResultados:_id50\"]");	
	By BotonNuevo  = By.xpath("//*[@id=\'formResultados:_id52\']");
	By CerrarSesion= By.xpath("//*[@id=\"piePagina\"]/tbody/tr[1]/td[2]/a[3]");
	   

   @Parameterized.Parameters   
  
   public static Collection<Object[]> listaTextos() {
		  
	   List<Object[]> args = new ArrayList<>();
	     
	   

	 	 try {
	 		  
	 		Conectar oracle=new Conectar();
	 	 	Connection con;
	 	 	con=oracle.conectar();
	 	 
	 	 	

 		 
				String sql = "select id_hogar from mcy_hogar WHERE estado_actual=11 and ROWNUM <= 3";
	 	 	
	 		  Statement statement = con.createStatement();
	 		  
	 		  ResultSet result = statement.executeQuery(sql);
	 		
	 		//Extraer Data
	 		   
	 		 
	 		  while (result.next()) {
	 			  
	 			  Object[] argumento = new Object[] { 
	 					  
	 					    result.getString(1),
//	 					    result.getString(2),
//	 					    result.getString(3),
//	 					    result.getString(4),
//	 					    result.getString(5),
	 					 
	 					    
	 			  };
	 			    		  
	 			    args.add(argumento);
	 			    //result.close();
	 			    //statement.close();
	 		  }
	 		}
	 	catch (Exception ex) {
//	 		  // Do nothing ... 
	 	}
	 		  
	 	return args;

	 	}
	 	   
 
   public static String ID_hogar;
   
   
  
   public ReversarVencimiento (String ID_Hogar) {
	   this.ID_hogar = ID_hogar;
	 
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
   //driver.quit();
  }
  
  @Test
  
  public void testRegistro() throws InterruptedException {
	  
	  try {
			entrada = new FileInputStream("datos.properties");
			 propiedades.load(entrada); 
			 
			 String url = propiedades.getProperty("url");
			 String usuario1 = propiedades.getProperty("usuario1");	 
			 String password = propiedades.getProperty("password1");


			 System.out.println("EL ID ES: " +ID_hogar);
			 
			 driver.get(url);
	     		 
		    		 
		     Thread.sleep(2000); 
		        driver.findElement(usuario).click();
		        driver.findElement(usuario).sendKeys(usuario1);
		        driver.findElement(clave).click();
		        driver.findElement(clave).sendKeys(password);
		         driver.findElement(BotonIngresar).click();
		        
		        URL url1 = Loader.getResource("log4j.properties");  // Esto carga el fichero como recurso si está en el CLASSPATH
		        PropertyConfigurator.configure(url1);
		        
		       // PropertyConfigurator.configure("log4j.properties");
		        
//		        log.info("Log4j, Login Correcto.");
//		        log.error("Log4j, Prueba mensaje de error.");
//		        log.debug("Log4j, Prueba mensaje debug.");
//		        log.info("Log4j, Prueba mensaje de info.");
//		        log.warn("Log4j, Prueba mensaje de warn.");
//		        log.fatal("Log4j, Prueba mensaje fatal.");
		       
		        //Forzar login
				  
			     Boolean b1 = driver.equals(CheckForzar);
			     System.out.println("CheckForzar :" + b1);
		     
		           if (b1==true) {
			 	  
				//if (driver.findElement(CheckForzar).isSelected() == false)  
				 
				//   if (driver.findElement(CheckForzar).i()){
				       
				    	driver.findElement(CheckForzar).click();
	 			        Thread.sleep(2000); 
				        driver.findElement(usuario).click();
	 			        driver.findElement(usuario).sendKeys(usuario1);
				        driver.findElement(clave).click();
				        driver.findElement(clave).sendKeys(password);
				        driver.findElement(BotonIngresar).click();
				    	//b1 = true;    
				      }
//				    
				  //  else {
				 
		              
		     
		     //3er Formulario /menu escoger proyecto 
			    driver.findElement(CampoBusqueda).click();
			    driver.findElement(CampoBusqueda).sendKeys("Mi Casa Ya");
			    Thread.sleep(2000);
			   // driver.findElement(MiCasaYa).click();
			    driver.get("https://miportafoliodev.cifin.co/cifin/MiCasaYa/faces/menu?destino=Menu");
			    Thread.sleep(2000);
		    
		    //4to Formulario /Escoger Opcion Mi Casa Ya.
		    driver.findElement(ReversarVencimiento).click();
		    Thread.sleep(4000);
		    System.out.println("Ingreso al Modulo Reversar Vencimiento: True");
		    
   
			driver.findElement(IdentificadorHogar).click();
			//  driver.findElement(CampoCedula).sendKeys("1037479977");
			//  driver.findElement(CampoCedula).sendKeys(Integer.toString(id_hogar));
			driver.findElement(IdentificadorHogar).sendKeys(ID_hogar);
			//driver.findElement(Fecha).sendKeys("18/11/2020");

			driver.findElement(BotonBuscar).click();
			Thread.sleep(10000);

			driver.findElement(BotonReversarVencimiento).click();
			Thread.sleep(20000); 

			//driver.findElement(BotonNuevo).click();
			//Thread.sleep(10000);
     captureScreenshot();
     System.out.println("Reversar vencimiento: True");
    // driver.findElement(BotonNuevo).click();
    // MarcarHogarObligatorios nuevo = new MarcarHogarObligatorios("1");
     driver.findElement(CerrarSesion).click();
  
     log.info("Log4j, Reversar vencimiento Exitoso.");
     
		  //  }
 
	  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Log4j, Automatizacion Fallida.");
		    System.out.println("Reversar Vencimiento: False");
			driver.quit();
		}
  }
}