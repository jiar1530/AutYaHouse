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
import org.junit.jupiter.api.Assertions;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;


import com.selenium.database.Conectar;


@RunWith(Parameterized.class)
public class PerdidaEjecutoriedad {
	
	private static Logger log = Logger.getLogger(PerdidaEjecutoriedad.class);
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
 	// By RegistrarHogar =
 	// By.xpath("//*[@id=\"terceros:tbody_element\"]/tr[2]/td/a");
 	// By RenunciasalSubsidio =
 	// By.xpath("//*[@id=\"terceros:tbody_element\"]/tr[6]/td/a");dev
 	By PerdidadEjecutoriedad = By.xpath("//*[@id='terceros:tbody_element']/tr[20]/td/a");
 	
 	

 // Elementos 5 formulario Renuncia
 	By IdentificadordelHogar = By.xpath("//*[@id=\"formRenunciaRC:idFamilia\"]");
 	By BotonBuscar = By.xpath("//*[@id=\"formRenunciaRC:_id34\"]");
 	By PerdidaEje = By.xpath("//*[@id=\"formResultados:_id58\"]");
 	
 	By Nuevo = By.xpath("//*[@id=\"formResultados:_id43\"]");
	
 
	
   @Parameterized.Parameters   
  
   public static Collection<Object[]> listaTextos() {
		  
	   List<Object[]> args = new ArrayList<>();
	     
	   

	 	 try {
	 		  
	 		Conectar oracle=new Conectar();
	 	 	Connection con;
	 	 	con=oracle.conectar();
	 		  
	
	 	 	String sql = "select id_hogar  from mcy_hogar where estado_actual='5' and ROWNUM <= 2 and fec_marcacion between '01/01/2020' and '31/12/2020'";
	 	 	
	 	 	
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
	 	   
 
   public static String IdHogar;
 

	public PerdidaEjecutoriedad(String IdHogar) {
		this.IdHogar = IdHogar;

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
 		    String rutaimagenes = propiedades.getProperty("rutaimagenesperdidadeejecutoriedad");
 	
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
  
  public void testRegistro() throws InterruptedException {
	  
	  try {
			entrada = new FileInputStream("datos.properties");
			 propiedades.load(entrada); 
			 
			 
			 String url = propiedades.getProperty("url");
			 String usuario1 = propiedades.getProperty("usuario1");	 
			 String password = propiedades.getProperty("password1");
			 
//			 String url1 = propiedades.getProperty("url1");
//			 String url2 = propiedades.getProperty("url2");
//			 String usuario1 = propiedades.getProperty("usuario1");	 
//			 String password = propiedades.getProperty("password1");
	         
	         System.out.println(IdHogar);
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
				if (b1==true) {
					
					
					driver.findElement(CheckForzar).click();
					Thread.sleep(2000); 
					driver.findElement(usuario).click();
					driver.findElement(usuario).sendKeys(usuario1);
					driver.findElement(clave).click();
					driver.findElement(clave).sendKeys(password);
					driver.findElement(BotonIngresar).click();

				}
		     	     
		     
		     //3er Formulario /menu escoger proyecto 
				
				//driver.get(url2);   //Cambio USRL 2
				//Thread.sleep(3000);
				
			    driver.findElement(CampoBusqueda).click();
			    driver.findElement(CampoBusqueda).sendKeys("Mi Casa Ya");
			    Thread.sleep(2000);
			    driver.findElement(MiCasaYa).click();
			    Thread.sleep(3000);
		    
		 
			  //4to Formulario /Escoger Opcion Mi Casa Ya.
				driver.findElement(PerdidadEjecutoriedad).click();
 				Thread.sleep(2000);
//				
 // 			driver.findElement(AnulacionSubsidio).click();
				Thread.sleep(2000);
//			    
				System.out.println("Ingreso a modulo Perdida de Ejecutoriedad:TRUE");

				// 5to Formulario //Formulario Renuncias al Subsidio

				// driver.findElement(CampoCedula).sendKeys("1037479977");
				// driver.findElement(CampoCedula).sendKeys(Integer.toString(id_hogar));
				driver.findElement(IdentificadordelHogar).sendKeys(IdHogar);
				Thread.sleep(2000);
				// driver.findElement(BotonAdicionarIntegrante).click();

				// Boton Buscar
				driver.findElement(BotonBuscar).click();
				Thread.sleep(10000);

				// Boton RENUNCIAR SUBSIDO
				driver.findElement(PerdidaEje).click();
				Thread.sleep(10000);
				
				 System.out.println("Perdida Ejecutoriedad:TRUE");
				 URL url1 = Loader.getResource("log4j.properties");  // Esto carga el fichero como recurso si está en el CLASSPATH
			     PropertyConfigurator.configure(url1);
			     log.info("Log4j, Perdida de Ejecutoriedad Exitoso.");

				// Boton Nuevo
//				driver.findElement(Nuevo).click();
//				Thread.sleep(10000);
				captureScreenshot();
				
			//	driver.findElement(BotonNuevo).click();
			//	Thread.sleep(10000);
				 
			
			    		    
		    
	  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 URL url1 = Loader.getResource("log4j.properties");  // Esto carga el fichero como recurso si está en el CLASSPATH
		     PropertyConfigurator.configure(url1);
		     log.error("Log4j, Automatizacion Fallida.");
		     System.out.println("Perdida de Ejecutoriedad :FALSE");
		     driver.quit();		    			
		}
  }
}