package com.selenium.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

import com.selenium.database.Conectar;

@RunWith(Parameterized.class)
public class RegistrarHogarCamposNoObligatoriosQA {
	static Properties propiedades = new Properties();
    static InputStream entrada = null;	
    
    private static Logger log = Logger.getLogger(RegistrarHogarCamposNoObligatoriosQA.class);
	
 //   SoftAssert softAssert = new SoftAssert();
 
  private static WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
 
 
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
  
 // By CampoBusqueda = By.id("search-query");
  By CampoBusqueda = By.xpath("//*[@id=\"search-query\"]");

 // By MiCasaYa = By.xpath("//*[@id=\"id_2\"]/div/a");
  //Dev
   // By MiCasaYa = By.xpath("//*[@id=\"id_1\"]/div/a");
   // By MiCasaYa = By.xpath("//*[@id=\"id_69\"]/div/a");
   // By MiCasaYa = By.xpath("//*[@id=\"id_68\"]/div/a"); 

  //QA
  By MiCasaYa = By.xpath("//*[@id=\"id_5\"]/div/a");
    
  //Ambiente UAT
 //  By MiCasaYa = By.xpath("//*[@id=\"id_18\"]/div/a");
  
  
  // elementos 4to Formulario
//  By RegistrarHogar = By.xpath("//*[@id=\"terceros:tbody_element\"]/tr[2]/td/a");
  //Dev
 // By RegistrarHogar = By.xpath("//*[@id=\"terceros:tbody_element\"]/tr[1]/td/a");
    
    //QA
    By RegistrarHogar = By.xpath("//*[@id=\"terceros:tbody_element\"]/tr[2]/td/a");
  
  //Elementos 5 formulario Registro
  By BotonAdicionarIntegrante = By.xpath("//*[@id=\"addMemberButton\"]");
  By DesplegableTipoDocumento = By.xpath("//*[@id=\"tercerosLista:0:identificacion\"]");
  By OpcionCedula = By.xpath("//*[@id=\"tercerosLista:0:identificacion\"]/option[2]");
  By CampoCedula = By.xpath("//*[@id=\"tercerosLista:0:numIdentificacion\"]");
  By DesplegableDepartamento = By.xpath("//*[@id=\"form1:departamento\"]");
  By DesplegableMunicipio = By.xpath("//*[@id=\"form1:municipio\"]");
  By DesplegableRango = By.xpath("//*[@id=\"form1:rango\"]");
  By DesplegableTipoDeVivienda = By.xpath("//*[@id=\"form1:tipoVivienda\"]");
  By BotonValidar = By.xpath("//*[@id=\"validarButton\"]");		



  //Datos Opcionales
  
  By Fecha_Nacimiento = By.xpath("//*[@id=\"tercerosLista:0:fechaNacimiento\"]");
  By DesplegableGenero = By.xpath("//*[@id=\"tercerosLista:0:genero\"]");
  By DesplegableOrientacion = By.xpath("//*[@id=\"tercerosLista:0:orientacionSexual\"]");
  By DesplegableEstadoCivil = By.xpath("//*[@id=\"tercerosLista:0:estadoCivil\"]");
  By DesplegableCondicionEspecial = By.xpath("//*[@id=\"tercerosLista:0:estadoCivil\"]");
  By DesplegableEtnica = By.xpath("//*[@id=\"tercerosLista:0:pertenenciaEtnica\"]");
  By CheckEliminar = By.xpath("//*[@id=\"tercerosLista:0:_id42\"]/img");
  By Celular = By.xpath("//*[@id=\"form1:celular\"]");
  By Telefono = By.xpath("//*[@id=\"form1:telefonoFijo\"]");
  By CorreoElectronico = By.xpath("//*[@id=\"form1:email\"]");
  By Direccion = By.xpath("//*[@id=\"form1:direccionCorrespondencia\"]");
  By DesplegableContrato = By.xpath("//*[@id=\"form1:tipoContrato\"]");
  By DesplegableConstructor =  By.xpath("//*[@id=\"form1:vendor\"]");
  By DesplegableProyecto = By.xpath("//*[@id=\"form1:project\"]");
  By OpcionConstructor = By.xpath("//*[@id=\"form1:vendor\"]/option[5]");
//*[@id="form1:vendor"]
  By OpcionConstructor1 = By.xpath("//*[@id=\"form1:vendor\"]/option[1]");
  
  By OpcionProyecto = By.xpath("//*[@id=\"form1:project\"]/option[2]");
  
  By OpcionProyecto1 = By.xpath("//*[@id=\"form1:project\"]/option[1]");	
  
  @Parameterized.Parameters   
  
  public static Collection<Object[]> listaTextos() {
		  
	   List<Object[]> args = new ArrayList<>();
	     
	   

	 	 try {
	 		  
	 		 
	 		
	 		Conectar oracle=new Conectar();
	 	 	Connection con;
	 	 	con=oracle.conectar();
	 		  
	 	     //  String sql = "Select * from mcy_hogar ho where ho.id_hogar=3577";
		    //  String sql = "Select * from mcy_hogar";
		    // String sql = "select Numero_identificacion,nombre from terceros WHERE ROWNUM <= 10";
	 	 	// String sql = "select Numero_identificacion,nombre, lugar_expedicion from terceros WHERE ROWNUM <= 10";
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
	 		   
	 			  
	 			    args.add(argumento);
	 			
	 		  }
	 		}
	 	catch (Exception ex) {
	 		  // Do nothing ... 
	 	}
	 		  
	 	return args;

	 	}
	 	  
  
  
  public static String Cedula;
  public static String Nombre;
  public static String Lugar_expedicion;
  public static String Nombre_Departamento;
  public static String Fecha_Nacimiento1;
  
  //public RegistrarHogar_1(String Secuencia, String Tipo_Identificacion,String Cedula,String Nombre) {
  public RegistrarHogarCamposNoObligatoriosQA(String Cedula, String Nombre, String Lugar_expedicion,String Nombre_Departamento, String Fecha_Nacimiento1) {
	   this.Cedula = Cedula;
	   this.Nombre = Nombre;
	   this.Lugar_expedicion = Lugar_expedicion;
	   this.Nombre_Departamento = Nombre_Departamento;
	   this.Fecha_Nacimiento1 = Fecha_Nacimiento1;
  }
 
  @Before
  public void setUp() {
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
		    String rutaimagenes = propiedades.getProperty("rutaimagenesregistrarnoobligatorios");
	
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
			 
			 String url = propiedades.getProperty("url");
			 String usuario1 = propiedades.getProperty("usuario1");	 
			 String password = propiedades.getProperty("password1");
			 String fecha_nacimiento2 = propiedades.getProperty("fecha_nacimiento2");
			 String genero = propiedades.getProperty("genero");
			 String orientacion = propiedades.getProperty("orientacion");
			 String estado_civil = propiedades.getProperty("estado_civil");
			 String condicion_especial = propiedades.getProperty("condicion_especial");
			 String etnia = propiedades.getProperty("etnia");
			 String celular = propiedades.getProperty("celular");
			 String telefono = propiedades.getProperty("telefono");
			 String correo_electronico = propiedades.getProperty("correo_electronico");
			 String direccion = propiedades.getProperty("direccion");
			 String contrato = propiedades.getProperty("contrato");
					 
			 
			 System.out.println(Cedula);
			  System.out.println(Nombre);
			  System.out.println(Lugar_expedicion);
		      System.out.println(Nombre_Departamento); 
		      System.out.println(Fecha_Nacimiento1);
		      
		    // driver.get("http://10.1.104.75:9090/sso-auth-server/login");
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
		            }else {
		         
		         
		         System.out.println("CONTINUE");
		         //2do Formulario/ Conexion no es privada
//		           driver.findElement(BotonConfiguracionAvanzada).click();
//		           Thread.sleep(2000);
//		           driver.findElement(LinkContinuar).click();
//		           Thread.sleep(2000);

		           //3er Formulario /menu escoger proyecto 
		           driver.findElement(CampoBusqueda).click();
		           driver.findElement(CampoBusqueda).sendKeys("Mi Casa Ya");
		         //driver.get("https://aplicaciones.cifin.co/cifin/MiCasaYa")
		           driver.findElement(MiCasaYa).click();
		           Thread.sleep(2000);
		           
		           //4to Formulario /Escoger Opcion Mi Casa Ya.
		           driver.findElement(RegistrarHogar).click();
		           Thread.sleep(2000);
		           System.out.println("Ingreso al Modulo Registrar Hogares No Obligatorios: True");
		           
		           //5to Formulario //Formulario Registro Hogar
		            driver.findElement(BotonAdicionarIntegrante).click();
		            Thread.sleep(3000);
		            driver.findElement(DesplegableTipoDocumento).click();
		            Thread.sleep(2000);
		            driver.findElement(OpcionCedula).click();
		          //  driver.findElement(CampoCedula).sendKeys("1037479977");
		          //  driver.findElement(CampoCedula).sendKeys(Integer.toString(id_hogar));
		            driver.findElement(CampoCedula).sendKeys(Cedula);
		          //  Thread.sleep(2000);
		          // driver.findElement(BotonAdicionarIntegrante).click();
		            
		            //Datos Opcionales
		            
		       
		            
		            driver.findElement(Fecha_Nacimiento).sendKeys(fecha_nacimiento2);
		            
		            driver.findElement(DesplegableGenero).click();
		            //Parametros Prueba
		           // driver.findElement(DesplegableGenero).sendKeys("Masculino");
		            driver.findElement(DesplegableGenero).sendKeys(genero);
		           // Otro; 
		            
		            driver.findElement(DesplegableOrientacion).click();
		            driver.findElement(DesplegableOrientacion).sendKeys(orientacion);
		            //Lesbiana,Gay,transgenero, bisexual, intersexual, heterosexual 
		            
		            //driver.findElement(DesplegableEstadoCivil).sendKeys("Soltero");
		            driver.findElement(DesplegableEstadoCivil).sendKeys(estado_civil);
		            
		            driver.findElement(DesplegableCondicionEspecial).sendKeys(condicion_especial);
		            //Victima de Conflicto Armado, Discapacitado
		            
		            //driver.findElement(DesplegableEtnica).sendKeys("Otro /No determinado");
		            driver.findElement(DesplegableEtnica).sendKeys(etnia);
		            //Rrom, Negro, Afrodescendiente, Raizaal, Palenqueros
		            
		            //Eliminar Registro
		            //driver.findElement(CheckEliminar).click(); 
		            
		            //Adicionar Integrante
		            // driver.findElement(BotonAdicionarIntegrante).click();
		            //Crear estructura para tomar de la data 2do dato de pruebas
		           
		            
//		            js.executeScript("window.scrollBy(0,1000)");
//		            Thread.sleep(3000);
//		            driver.findElement(DesplegableDepartamento).click();
//		            driver.findElement(DesplegableDepartamento).sendKeys("BOGOTA DISTRITO C.A");
//		           // driver.findElement(DesplegableDepartamento).sendKeys(Nombre_Departamento);
//		              driver.findElement(DesplegableDepartamento).click();
//		            Thread.sleep(3000);
//		            driver.findElement(DesplegableMunicipio).click();
//		            driver.findElement(DesplegableMunicipio).sendKeys("BOGOTA");
//		           // driver.findElement(DesplegableMunicipio).sendKeys(Lugar_expedicion);
//		            driver.findElement(DesplegableMunicipio).click();
//		            Thread.sleep(3000);
		            
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
		            
		         
		            driver.findElement(DesplegableConstructor).click();
		            driver.findElement(OpcionConstructor).click();
		            Thread.sleep(2000);
		            driver.findElement(DesplegableProyecto).click();
		            driver.findElement(OpcionProyecto).click();
		            Thread.sleep(2000);
		            
		            
		          //   List<WebElement> dynamicElement =driver.findElements(OpcionConstructor1);
		            
		            
		         //     if (dynamicElement.size() != 0) {
		            	
				 //           driver.findElement(DesplegableConstructor).click();
				 //           driver.findElement(OpcionConstructor).click();
				 //           Thread.sleep(2000);
				 //           driver.findElement(DesplegableProyecto).click();
				 //           driver.findElement(OpcionProyecto).click();
				 //           Thread.sleep(2000);  
				            
				            
//		             	System.out.println("ENTRE A IF DESPLEGABLE");
//		            	driver.findElement(OpcionConstructor1).click();
// 		            	Thread.sleep(2000);
//		            	driver.findElement(DesplegableProyecto).click();
//			            driver.findElement(OpcionProyecto1).click();
			            
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
			            Thread.sleep(3000);
			            
			            //Datos Opcionales
			            js.executeScript("window.scrollBy(0,1000)");
			            driver.findElement(Celular).sendKeys(celular);
			            driver.findElement(Telefono).sendKeys(telefono);
			            driver.findElement(CorreoElectronico).sendKeys(correo_electronico);
			            driver.findElement(Direccion).sendKeys(direccion);
			            driver.findElement(DesplegableContrato).click();
			            driver.findElement(DesplegableContrato).sendKeys(contrato);
			            Thread.sleep(2000);
			            driver.findElement(DesplegableContrato).click();
			            
		            
		            //Boton Validacion
			        
		            driver.findElement(BotonValidar).click();
		            Thread.sleep(5000);
		            captureScreenshot();
		            URL url1 = Loader.getResource("log4j.properties");  // Esto carga el fichero como recurso si está en el CLASSPATH
		            PropertyConfigurator.configure(url1);
		            log.info("Log4j, Registro Hogar Exitoso.");
		            System.out.println("Automatizacion Registrar Hogares Obligatorios: True");
		    
		            }          
	   
//		              
		  //       }
	  }
		     catch (IOException e) {
		 		// TODO Auto-generated catch block
		 		e.printStackTrace();
		 		log.error("Log4j, Automatizacion Fallida.");
		 		System.out.println("Automatizacion Registrar Hogares Obligatorios: False");
		 		driver.quit();
		 	}
	 
    }	
	

}
