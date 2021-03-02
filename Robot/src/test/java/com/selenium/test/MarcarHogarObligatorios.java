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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.Loader;

import com.selenium.database.Conectar;


@RunWith(Parameterized.class)
public class MarcarHogarObligatorios {
	
	private static Logger log = Logger.getLogger(RegistrarHogarCamposNoObligatorios.class);
	
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
	 // String password = "H33std";
	  //String nombre_usuario = "243763";
	  //String password = "H31dyl";
	  
	  //elementos 2do Formulario
	  
	  By BotonConfiguracionAvanzada = By.id("details-button");
	  By LinkContinuar = By.id("proceed-link");

	  //elementos 3r Formulario
	//*[@id="search-query"]
	  
	  By CampoBusqueda = By.id("search-query");

	//  By MiCasaYa = By.xpath("//*[@id=\"id_68\"]/div/a");
	//
	  
	  //En Dev
	 By MiCasaYa = By.xpath("//*[@id=\"id_70\"]/div/a");
	 //By MiCasaYa = By.xpath("//*[@id='id_69']/div/a");
	 // By MiCasaYa = By.xpath("//*[@id=\"id_46\"]/div/a");
	//*[@id="id_70"]/div/a

	  
	
	 
	  //En Uat
	//  By MiCasaYa = By.xpath("//*[@id=\"id_18\"]/div/a");
	//  By MiCasaYa = By.xpath("//*[@id=\"id_20\"]/div/a");
	  
	  //By MiCasaYa = By.xpath("//*[@id=\"id_2\"]/div/a");

	  // elementos 4to Formulario
	  // By MarcarHogar = By.xpath("//*[@id=\'terceros:tbody_element\']/tr[3]/td/a");
	    By MarcarHogar = By.xpath("//*[@id=\"terceros:tbody_element\"]/tr[2]/td/a");
	 
	
	//*[@id="terceros:tbody_element"]/tr[3]/td/a
	//*[@id="terceros:tbody_element"]/tr[3]/td/a
	
	  //Elementos 5 formulario Marcar Hogar
	  
	  By CampoIdentificadorHogar = By.xpath("//*[@id=\"formConsulta:idFamilia\"]");
	  By BotonConsultar = By.xpath("//*[@id=\"formConsulta:_id35\"]");
	  By DesplegableRango1 = By.xpath("//*[@id=\"formResultados:rango\"]");
	  By OpcionRango1 = By.xpath("//*[@id=\"formResultados:rango\"]/option[2]"); 
	  By DesplegableRango2 = By.xpath("//*[@id=\"formResultados:rango2\"]");
	  By OpcionRango2 = By.xpath("//*[@id=\"formResultados:rango2\"]/option[2]");
	  By DesplegableTipoVivienda1 = By.xpath("//*[@id=\"formResultados:tipoVivienda\"]");
	  By OpcionTipoVivienda1 = By.xpath("//*[@id=\"formResultados:tipoVivienda\"]/option[2]");
	  By DesplegableTipoVivienda2 = By.xpath("//*[@id=\"formResultados:tipoVivienda2\"]");
	  By OpcionTipoVivienda2 = By.xpath("//*[@id=\"formResultados:tipoVivienda2\"]/option[2]");
	  By DesplegableTipoContrato1 = By.xpath("//*[@id=\"formResultados:tipoContrato\"]");
	  By OpcionTipoContrato1 = By.xpath("//*[@id=\"formResultados:tipoContrato\"]/option[2]");
	  By DesplegableTipoContrato2 = By.xpath("//*[@id=\"formResultados:tipoContrato2\"]");
	  By OpcionTipoContrato2 = By.xpath("//*[@id=\"formResultados:tipoContrato2\"]/option[2]");
	  By Celular = By.xpath("//*[@id=\"formResultados:celular\"]");
	  By Telefono = By.xpath("//*[@id=\"formResultados:telefonoFijo\"]");
	  By Direccion = By.xpath("//*[@id=\"formResultados:direccionCorrespondencia\"]");
	  By DesplegableConstructor = By.xpath("//*[@id=\"formResultados:vendor\"]");  
	  By DesplegableProyecto = By.xpath("//*[@id=\"formResultados:project\"]");
	  By DesplegableDepartamento = By.xpath("//*[@id=\"formResultados:departamento\"]");
	  By DesplegableMunicipio = By.xpath("//*[@id=\"formResultados:municipio\"]");
	
	  //By BotonValidar = By.xpath("//*[@id=\"formResultados:_id187\"]");
	  By BotonValidar = By.xpath("//*[@id=\"formResultados:_id195\"]");
	  
	  
	 // By BotonMarcar = By.xpath("//*[@id=\"formResultados:panelid\"]");
	    By BotonMarcar = By.xpath("//*[@id=\"formResultados:marcar\"]");
	  
	  
	  
	   @Parameterized.Parameters   
	  
	   public static Collection<Object[]> listaTextos() {
			  
		   List<Object[]> args = new ArrayList<>();
		     
		   

		 	 try {
		 		  
		 		Conectar oracle=new Conectar();
		 	 	Connection con;
		 	 	con=oracle.conectar();
		 		  
		 	     
	 		  
	 		     
		 	 	
		 	 		//String sql1 = "select * from mcy_hogar where estado_actual = '1' and tipo_entidad = '999' and codigo_entidad = '1' and fec_consulta like '%20'and ROWNUM <= 2"; 
		 	 	 // String sql1 ="SELECT * FROM MCY_HOG_TERCERO MT, MCY_HOGAR MG  WHERE MG.ID_HOGAR = MT.ID_HOGAR AND MG.ESTADO_REGISTRO LIKE 'A%' AND MT.ESTADO_REGISTRO LIKE 'A%' AND MT.NUMERO_IDENTIFICACION = '1002297080'";
		 	 	  //String sql1= "Select mh.id_hogar from mcy_hog_tercero ht inner join mcy_hogar mh on mh.id_hogar = ht.id_hogar where ht.usuario_adicion='-1' and to_char(ht.fecha_adicion,'DD/MM/YYYY') like '21/02/2021%' and mh.estado_actual = '1' and mh.tipo_entidad='999' and mh.codigo_entidad='3'";  
		 	 	   String sql1= "Select mh.id_hogar from mcy_hog_tercero ht inner join mcy_hogar mh on mh.id_hogar = ht.id_hogar where mh.estado_actual = '1' and mh.tipo_entidad='999' and mh.codigo_entidad='1' and numero_identificacion between '1036687576' and '1036687895'";
		 	 	  // String sql1= "Select mh.id_hogar from mcy_hog_tercero ht inner join mcy_hogar mh on mh.id_hogar = ht.id_hogar where mh.estado_actual = '1' and mh.tipo_entidad='999' and mh.codigo_entidad='1' and rownum < = 3";
		 	 		
		 	 		
		 	 	
		 	 	
		 	 		Statement statement = con.createStatement();
		 		  
		 		  ResultSet result = statement.executeQuery(sql1);
		 		
		 		//Extraer Data
		 		   
		 		 
		 		  while (result.next()) {
		 			  
		 			  Object[] argumento = new Object[] { 
		 					  
		 					  result.getString(1),
		 					  //result.getString(2),
		 					  //result.getString(3),
		 					 // result.getString(4),
		 					
		 					 
		 			  };
		 		  
		 			  
		 			    args.add(argumento);
		 		
		 			
		 		
		 		  }
		 		}
		 	catch (Exception ex) {
//		 		  // Do nothing ... 
		 	}
//		 		  
		 	return args;
	//
		 	}
		 	   
	 
	   public static String Id_Hogar;
	   //public static String Nombre;
	   //public static String Municipio;
	
	  
	   
	   public MarcarHogarObligatorios(String Id_Hogar) {
		   this.Id_Hogar = Id_Hogar;
		   //this.Nombre = Nombre;
		   //this.Municipio = Municipio;
	
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
	 		    String rutaimagenes = propiedades.getProperty("rutaimagenesmarcarhogarobligatorios");
	 	
	 		Date d = new Date();
	  
	 		String FileName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
	  
	 		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	             
	 		
	 		FileHandler.copy(screenshot, new File(rutaimagenes + FileName));
	 		
	 		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				entrada = new FileInputStream("datos.properties");
	 			propiedades.load(entrada); 
	 		    String rutaerror = propiedades.getProperty("rutaimagenesmarcarhogarobligatorios");
	 	
	 		Date d = new Date();
	  
	 		String FileName = "Error" + d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
	  
	 		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	  
	 		
	 		FileHandler.copy(screenshot, new File(rutaerror + FileName));
					
			}
	 		
	 		
	 	}
	  
	  @After
	  public void tearDown() {
	   driver.quit();
	  }
	  @Test
	  
	  public void testRegistro() throws InterruptedException, IOException {
		  
		  try {
				entrada = new FileInputStream("datos.properties");
				 propiedades.load(entrada); 
				 
				
				 String url = propiedades.getProperty("url");
				 String usuario2 = propiedades.getProperty("usuario1");	 
				 String password1 = propiedades.getProperty("password1");
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
				 String departamento = propiedades.getProperty("departamento");
				 String municipio= propiedades.getProperty("municipio");
				 String constructor= propiedades.getProperty("constructor");
				 String proyecto= propiedades.getProperty("proyecto");
				 String tipovivienda = propiedades.getProperty("tipovivienda");
						 
				 
				 System.out.println(Id_Hogar);
				 
				//  driver.get("http://10.1.104.75:9090/sso-auth-server/login");
				    //driver.get("https://aplicaciones.cifin.co/sso-auth-server/login");
				  
				  
	  	    
	     driver.get(url);
	   //  driver.get("http://10.1.104.75:9090/sso-auth-server/login");
	   //  System.out.println("LA URL ES: " +url);
	     driver.findElement(usuario).click();
	     driver.findElement(usuario).sendKeys(usuario2);
	     driver.findElement(clave).click();
	     driver.findElement(clave).sendKeys(password1);
	     driver.findElement(BotonIngresar).click();
	     Thread.sleep(3000);
	    
	     //Forzar login
		  
	     Boolean b1 = driver.equals(CheckForzar);
	     System.out.println("CheckForzar :" + b1);
	     
	    // Boolean isPresent = driver.findElements(By.yourLocator).size() > 0
	    // Boolean isPresent = CheckForzar.size() > 0;
	     
	     Boolean isPresent = driver.findElements(By.xpath("//*[@id=\"forcelogin\"]")).size() > 0;
	     
	     System.out.println("is Present es:" +isPresent);
	        // if (isPresent = false) {
	       if (b1==true) {
	       //if (driver.findElement(CheckForzar).isSelected()) {  
	      //  if (driver.findElement(CheckForzar).isEnabled()) {
	    // if (driver.findElement(CheckForzar).isDisplayed()){

	        driver.findElement(CheckForzar).click();
	        Thread.sleep(2000); 
	        driver.findElement(usuario).click();
	        driver.findElement(usuario).sendKeys(usuario2);
	        driver.findElement(clave).click();
	        driver.findElement(clave).sendKeys(password1);
	        driver.findElement(BotonIngresar).click();
	        
	      }
	    
	       else {

	    //3er Formulario /menu escoger proyecto 
	    driver.findElement(CampoBusqueda).click();
	    driver.findElement(CampoBusqueda).sendKeys("Mi Casa ");
	    Thread.sleep(2000);
	    //driver.findElement(MiCasaYa).click();
	    driver.get("https://miportafoliodev.cifin.co/cifin/MiCasaYa/faces/menu?destino=Menu");
	    Thread.sleep(2000);
	    
	    //4to Formulario /Escoger Opcion Mi Casa Ya.
	    driver.findElement(MarcarHogar).click();
	    Thread.sleep(2000);
	    System.out.println("Ingreso a modulo Marcar Hogar Obligatorios:TRUE");
	    
	  //5to Formulario //Formulario Marcar Hogar
	    driver.findElement(CampoIdentificadorHogar).click();
	    Thread.sleep(2000);
	    driver.findElement(CampoIdentificadorHogar).sendKeys(Id_Hogar);
	  //  driver.findElement(CampoIdentificadorHogar).sendKeys("7237");
	  
	     Thread.sleep(2000);
	     driver.findElement(BotonConsultar).click();
	     Thread.sleep(5000);
	     js.executeScript("window.scrollBy(0,500)");
	     Thread.sleep(3000);
	    
//	    driver.findElement(DesplegableRango1).click();
//	    Thread.sleep(2000);
//	   // driver.findElement (OpcionRango1).click();
//	     driver.findElement(DesplegableRango1).sendKeys("3-Hasta 2 smmlv");
//	    Thread.sleep(1000);
	    driver.findElement(DesplegableRango2).click();
	    Thread.sleep(2000);
	   // driver.findElement (OpcionRango2).click();
	    driver.findElement(DesplegableRango2).sendKeys("3-Hasta 2 smmlv");
	    driver.findElement(DesplegableRango2).click();
	    Thread.sleep(2000);
	   
	 
	    driver.findElement(DesplegableTipoVivienda1).click();
//	    Thread.sleep(2000);
	    driver.findElement (DesplegableTipoVivienda1).sendKeys(tipovivienda);
//	    Thread.sleep(1000);
	    driver.findElement(DesplegableTipoVivienda2).click();
	    Thread.sleep(2000);
	    //driver.findElement (OpcionTipoVivienda2).click();
	      driver.findElement (DesplegableTipoVivienda2).sendKeys(tipovivienda);
	    Thread.sleep(2000);
        
       
	    driver.findElement(Telefono).clear();
		 Thread.sleep(2000);
        driver.findElement(Telefono).sendKeys("2500174");
        Thread.sleep(2000);
	    driver.findElement(Celular).clear();
	    Thread.sleep(2000);
        driver.findElement(Celular).sendKeys("3194371546");
        Thread.sleep(2000);
        
        driver.findElement(Direccion).clear();
        Thread.sleep(2000);
        driver.findElement(Direccion).sendKeys(direccion);

        driver.findElement(DesplegableDepartamento).click();
	    Thread.sleep(2000);
	    //driver.findElement(DesplegableDepartamento).sendKeys("BOGOTA DISTRITO C.A");
	     driver.findElement(DesplegableDepartamento).sendKeys(departamento);
	    //driver.findElement(DesplegableDepartamento).sendKeys("ANTIOQUIA");
	    Thread.sleep(2000);
	    driver.findElement(DesplegableMunicipio).click();
	    //Thread.sleep(2000);
	    driver.findElement(DesplegableMunicipio).sendKeys(municipio);
	    //driver.findElement(DesplegableMunicipio).sendKeys("BOGOTA DISTRITO C.A");
	    Thread.sleep(2000);
	    driver.findElement(DesplegableMunicipio).click();
	    Thread.sleep(2000);
	  // driver.findElement(DesplegableMunicipio).sendKeys("MEDELLIN");
	   // Thread.sleep(2000);
	    driver.findElement(DesplegableMunicipio).sendKeys(municipio);
	    //Thread.sleep(2000);
	   //driver.findElement(DesplegableMunicipio).click();
	    //Thread.sleep(2000);
	    driver.findElement(DesplegableMunicipio).sendKeys(Keys.RETURN);
	    Thread.sleep(2000);
	    
	    
	    
	    driver.findElement(DesplegableConstructor).click();
	    Thread.sleep(2000);
	    driver.findElement(DesplegableConstructor).sendKeys(constructor);
	    Thread.sleep(2000);
	    driver.findElement(DesplegableConstructor).click();
	    Thread.sleep(2000);
	    driver.findElement(DesplegableProyecto).click();
	    Thread.sleep(2000);
	    driver.findElement(DesplegableProyecto).sendKeys(proyecto);
	    Thread.sleep(2000);
	    driver.findElement(DesplegableProyecto).click();
	    
	    Thread.sleep(2000);
        driver.findElement(DesplegableTipoContrato1).click();
	    Thread.sleep(2000);
	    //driver.findElement (OpcionTipoContrato2).click();
	    driver.findElement (DesplegableTipoContrato1).sendKeys(contrato);
	    //driver.findElement(DesplegableTipoContrato2).click();
	    Thread.sleep(4000);
	    driver.findElement(DesplegableTipoContrato2).click();
	    Thread.sleep(2000);
	    //driver.findElement (OpcionTipoContrato2).click();
	    driver.findElement (DesplegableTipoContrato2).sendKeys(contrato);
	    Thread.sleep(2000);
	    driver.findElement(DesplegableTipoContrato2).click();
	    Thread.sleep(3000);
	        
	    
	    driver.findElement(BotonValidar).click(); 
	    Thread.sleep(10000);
	    captureScreenshot();
	    driver.findElement(BotonMarcar).click();
	    Thread.sleep(5000);
	    
	    URL url1 = Loader.getResource("log4j.properties");  // Esto carga el fichero como recurso si está en el CLASSPATH
        PropertyConfigurator.configure(url1);
        log.info("Log4j, Marcar Hogar Exitoso.");
        System.out.println("Automatizacion Marcar Hogar Obligatorios: True");
	    
	      }
	    
		  } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("Log4j, Automatizacion Fallida.");
				captureScreenshot();
				System.out.println("Automatizacion Marcar Hogar Obligatorios: False");
				driver.quit();
			}
		  
	  }
	}