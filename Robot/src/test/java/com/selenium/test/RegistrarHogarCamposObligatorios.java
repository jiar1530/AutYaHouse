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
public class RegistrarHogarCamposObligatorios {
	
	 
	private static Logger log = Logger.getLogger(RegistrarHogarCamposObligatorios.class);

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
   
  // By MiCasaYa = By.xpath("//*[@id=\"id_70\"]/div/a");
  //  By MiCasaYa = By.xpath("//*[@id=\"id_45\"]/div/a");
  //By MiCasaYa = By.xpath("//*[@id=\"id_46\"]/div/a");
//*[@id="id_70"]/div/a
 //*[@id="id_1"]
 

 
 
 // By MiCasaYa = By.xpath("//*[@id=\"id_1\"]/div/a");
  
  //  By MiCasaYa = By.xpath("//*[@id=\"id_18\"]/div/a");


  
  //  By MiCasaYa = By.xpath("//*[@id=\"id_2\"]/div/a");
  
  // elementos 4to Formulario
   //FONVIVIENDA
    By RegistrarHogar = By.xpath("//*[@id=\"terceros:tbody_element\"]/tr[2]/td/a");
   //BANCOS 
  // By RegistrarHogar = By.xpath("//*[@id=\"terceros:tbody_element\"]/tr[1]/td/a");
  //  By RegistrarHogar = By.xpath(Xpath1);


  
  //Elementos 5 formulario Registro
  By BotonAdicionarIntegrante = By.xpath("//*[@id=\"addMemberButton\"]");
  By DesplegableTipoDocumento = By.xpath("//*[@id=\"tercerosLista:0:identificacion\"]");
  By OpcionCedula = By.xpath("//*[@id=\"tercerosLista:0:identificacion\"]/option[2]");
  By CampoCedula = By.xpath("//*[@id=\"tercerosLista:0:numIdentificacion\"]");
  By DesplegableDepartamento = By.xpath("//*[@id=\"form1:departamento\"]");
  By DesplegableMunicipio = By.xpath("//*[@id=\"form1:municipio\"]");
  By DesplegableRango = By.xpath("//*[@id=\"form1:rango\"]");
  By DesplegableTipoDeVivienda = By.xpath("//*[@id=\"form1:tipoVivienda\"]");
  By DesplegableTipodecontrato = By.xpath("//*[@id=\"form1:tipoContrato\"]");
  
  By BotonNuevo = By.xpath("//*[@id=\"form1:botonNuevo\"]");
  By BotonValidar = By.xpath("//*[@id=\"validarButton\"]");		 

  By CerrarSesion= By.xpath("//*[@id=\"piePagina\"]/tbody/tr[1]/td[2]/a[3]");
  
	   

   @Parameterized.Parameters   
  
   public static Collection<Object[]> listaTextos() {
		  
	   List<Object[]> args = new ArrayList<>();
	     
	   

	 	 try {
	 		  
	 		Conectar oracle=new Conectar();
	 	 	Connection con;
	 	 	con=oracle.conectar();
	 	 
	 	 	
	 			entrada = new FileInputStream("datos.properties");
	 			propiedades.load(entrada); 
	 			String rutadriver = propiedades.getProperty("rutadriver"); 
	 			
	 			String numerocedula1 = propiedades.getProperty("numerocedula1");
				String numerocedula2 = propiedades.getProperty("numerocedula2");	
	
 		    //  String sql = "Select * from mcy_hogar";
 		    // String sql = "select Numero_identificacion,nombre from terceros WHERE ROWNUM <= 10";
	 	 	//String sql = "select Numero_identificacion,nombre, lugar_expedicion from terceros WHERE ROWNUM <= 10";
	 	 	//String sql = "select a.Numero_identificacion,a.nombre,a.lugar_expedicion,b.nombre_departamento,a.fecha_nacimiento from terceros a,departamentos b where b.codigo_departamento = a.departamento_expedicion and ROWNUM <= 2"; 
	 	 	//String sql = "select distinct numero_identificacion from mcy_excepcion where numero_identificacion between '1002258266' and '1002269993' order by numero_identificacion asc"; 
	 	 	  //Valido para bd Dev
	 	 	//String sql = "select distinct numero_identificacion from mcy_excepcion where numero_identificacion between '1002258266' and '1002258326' order by numero_identificacion asc";
	 	 	 //String sql = "select distinct numero_identificacion from terceros where numero_identificacion between '31960950' and '31962044' and ROWNUM <= 2";
	 	    // String sql = "select distinct numero_identificacion from terceros where numero_identificacion between '1036687576' and '1036687895' order by numero_identificacion asc";
	 	    // String sql = "select distinct numero_identificacion from terceros where numero_identificacion between '1036687576' and '1036687895' and ROWNUM <= 2";
	 	     String sql = "select distinct numero_identificacion from terceros where numero_identificacion between 'numerocedula1' and 'numerocedula2' and ROWNUM <= 2";
	 	 	//String sql = "select distinct numero_identificacion from mcy_excepcion where numero_identificacion between '1002258266' and '1002258326' order by numero_identificacion asc";
	 	 	//Valido pra QA
	 	    //String sql = "select distinct numero_identificacion from mcy_excepcion where numero_identificacion between '1005867412' and '1005867414' order by numero_identificacion asc";
	 	 	
	 	 	
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
	 	   
 
//   public static String Cedula;
//   public static String Tipo_Identificacion;
//   public static String Municipio;
////   public static String Nombre;
   public static String Cedula;
   //public static String Nombre;
   //public static String Lugar_expedicion;
   //public static String Nombre_Departamento;
   //public static String Fecha_Nacimiento1;
   
   
  
   public RegistrarHogarCamposObligatorios (String Cedula) {
	   this.Cedula = Cedula;
	  // this.Nombre = Nombre;
	   //this.Lugar_expedicion = Lugar_expedicion;
	   //this.Nombre_Departamento = Nombre_Departamento;
	   //this.Fecha_Nacimiento1 = Fecha_Nacimiento1;
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
  
  public void testRegistro() throws InterruptedException {
	  
	  try {
			entrada = new FileInputStream("datos.properties");
			 propiedades.load(entrada); 
			 
			 String url = propiedades.getProperty("url");
			 String usuario1 = propiedades.getProperty("usuario1");	 
			 String password = propiedades.getProperty("password1");

			 
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
		    driver.findElement(RegistrarHogar).click();
		    Thread.sleep(2000);
		    System.out.println("Ingreso al Modulo Registrar Hogares Obligatorios: True");
		    
   
    //5to Formulario //Formulario Registro Hogar
     driver.findElement(BotonAdicionarIntegrante).click();
     Thread.sleep(3000);
     driver.findElement(DesplegableTipoDocumento).click();
     Thread.sleep(2000);
     driver.findElement(OpcionCedula).click();
   // driver.findElement(CampoCedula).sendKeys("1002297080");
   //  driver.findElement(CampoCedula).sendKeys(Integer.toString(id_hogar));
      driver.findElement(CampoCedula).sendKeys(Cedula);
     
     //driver.findElement(CampoCedula).sendKeys(NumeroCedula);
     
   //  Thread.sleep(2000);
   // driver.findElement(BotonAdicionarIntegrante).click();
     
     js.executeScript("window.scrollBy(0,1000)");
     Thread.sleep(3000);
      driver.findElement(DesplegableDepartamento).click();
     // driver.findElement(DesplegableDepartamento).sendKeys("BOGOTA DISTRITO C.A");
     driver.findElement(DesplegableDepartamento).sendKeys("ANTIOQUIA");
   //  driver.findElement(DesplegableDepartamento).sendKeys(Nombre_Departamento);
     driver.findElement(DesplegableDepartamento).click();
     Thread.sleep(3000);
     driver.findElement(DesplegableMunicipio).click();
     //driver.findElement(DesplegableMunicipio).sendKeys("BOGOTA");
     driver.findElement(DesplegableMunicipio).sendKeys("MEDELLIN");
     //driver.findElement(DesplegableMunicipio).sendKeys(Lugar_expedicion);
     driver.findElement(DesplegableMunicipio).click();
     Thread.sleep(2000);
     driver.findElement(DesplegableRango).click();
     driver.findElement(DesplegableRango).click();
     driver.findElement(DesplegableRango).sendKeys("3-Hasta 2 smmlv");
     driver.findElement(DesplegableRango).click();
     Thread.sleep(2000);
     driver.findElement(DesplegableTipoDeVivienda).click();
     driver.findElement(DesplegableTipoDeVivienda).sendKeys("VIS");
     Thread.sleep(2000);
     driver.findElement(DesplegableTipoDeVivienda).click();
     Thread.sleep(2000);
     driver.findElement(DesplegableTipodecontrato).click();
     Thread.sleep(2000);
     driver.findElement(DesplegableTipodecontrato).sendKeys("Credito");
     Thread.sleep(2000);
     driver.findElement(DesplegableTipodecontrato).click();
  
     Thread.sleep(3000);
     driver.findElement(BotonValidar).click();
     Thread.sleep(5000);
     captureScreenshot();
     System.out.println("Automatizacion Registrar Hogares Obligatorios: True");
    // driver.findElement(BotonNuevo).click();
    // MarcarHogarObligatorios nuevo = new MarcarHogarObligatorios("1");
     driver.findElement(CerrarSesion).click();
  
     log.info("Log4j, Registro Hogar Exitoso.");
     
		  //  }
 
	  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Log4j, Automatizacion Fallida.");
		    System.out.println("Automatizacion Registrar Hogares: False");
			driver.quit();
		}
  }
}