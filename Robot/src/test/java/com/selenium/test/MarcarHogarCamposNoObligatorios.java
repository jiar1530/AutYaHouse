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
public class MarcarHogarCamposNoObligatorios {
	static Properties propiedades = new Properties();
    static InputStream entrada = null;	
	
    private static Logger log = Logger.getLogger(MarcarHogarCamposNoObligatorios.class);
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
	  
	     By CampoBusqueda = By.id("search-query");
	   // By CampoBusqueda = By.xpath("//*[@id=\'search-query\']");
	  
	  
	       By MiCasaYa = By.xpath("//*[@id='id_70']/div/a");
	    //  By MiCasaYa = By.xpath("//*[@id=\"id_69\"]/div/a");
	     
	       //QA
	     //  By MiCasaYa = By.xpath("//*[@id=\"id_4\"]/div/a");
	    
	   //Ambiente uat 
	   //  By MiCasaYa = By.xpath("//*[@id=\'id_18\']/div/a");
	     

	  // elementos 4to Formulario
	  By MarcarHogar = By.xpath("//*[@id=\'terceros:tbody_element\']/tr[3]/td/a");
	//*[@id="terceros:tbody_element"]/tr[2]/td/a
	//*[@id="terceros:tbody_element"]/tr[3]/td/a
	
	  //Elementos 5 formulario Marcar Hogar
	  

	  By CampoIdentificadorHogar = By.xpath("//*[@id=\"formConsulta:idFamilia\"]");
	  By BotonConsultar = By.xpath("//*[@id=\'formConsulta:_id35\']"); 
	  By DesplegableDepartamento = By.xpath("//*[@id=\"formResultados:departamento\"]");
	  By DesplegableMunicipio = By.xpath("//*[@id=\"formResultados:municipio\"]");
	  By DesplegableConstructor = By.xpath("//*[@id=\"formResultados:vendor\"]");
	  By DesplegableProyecto = By.xpath("//*[@id=\"formResultados:project\"]");
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
	  By BotonValidar = By.xpath("//*[@id=\"formResultados:_id187\"]");
	  By BotonMarcar = By.xpath("//*[@id=\"formResultados:panelid\"]");
	  
	//Datos Opcionales


		By DesplegableGenero = By.xpath("//*[@id=\"tercerosLista:0:genero\"]");
		By DesplegableOrientacion = By.xpath("//*[@id=\"tercerosLista:0:orientacionSexual\"]");
		By DesplegableEstadoCivil = By.xpath("//*[@id=\"tercerosLista:0:estadoCivil\"]");
		By DesplegableCondicionEspecial = By.xpath("//*[@id=\"tercerosLista:0:estadoCivil\"]");
		By DesplegableEtnica = By.xpath("//*[@id=\"tercerosLista:0:pertenenciaEtnica\"]");
		By Celular = By.xpath("//*[@id=\"form1:celular\"]");
		By Telefono = By.xpath("//*[@id=\"form1:telefonoFijo\"]");
		By CorreoElectronico = By.xpath("//*[@id=\"form1:email\"]");
		By Direccion = By.xpath("//*[@id=\"form1:direccionCorrespondencia\"]");
		By DesplegableContrato = By.xpath("//*[@id=\"form1:tipoContrato\"]");
		By OpcionConstructor1 = By.xpath("//*[@id=\"form1:vendor\"]/option[1]");
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
	 		     //String sql = "select Numero_identificacion,nombre from terceros WHERE ROWNUM <= 10";
		 	 	//String sql = "select Numero_identificacion,nombre, lugar_expedicion from terceros WHERE ROWNUM <= 10";
		 	 		
		 	   //String sql = "select * from mcy_hogar where estado_actual = '1' and tipo_entidad = '999' and codigo_entidad = '3' and fec_consulta like '%20'";
		 	   // Crear lista con los querys
		 	 	//String sql = "select a.Numero_identificacion,a.nombre,a.lugar_expedicion,b.nombre_departamento,a.fecha_nacimiento from terceros a,departamentos b where b.codigo_departamento = a.departamento_expedicion and ROWNUM <= 2";
		 	 	String sql= "Select mh.id_hogar from mcy_hog_tercero ht inner join mcy_hogar mh on mh.id_hogar = ht.id_hogar where mh.estado_actual = '1' and mh.tipo_entidad='999' and mh.codigo_entidad='3' and rownum < = 3";
		 	 	
		 	 			Statement statement = con.createStatement();
		 		  // 
		 		  ResultSet result = statement.executeQuery(sql);
		 		
		 		//Extraer Data
		 		   
		 		 
		 		  while (result.next()) {
		 			  
		 			  Object[] argumento = new Object[] { 
		 					  
		 					  result.getString(1),
		 					//  result.getString(2),
		 					//  result.getString(3),
		 					//  result.getString(4),
		 					//  result.getString(5),
		 					 
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

//	   public static String Cedula;
//	   public static String Nombre;
//	   public static String Lugar_expedicion;
//	   public static String Nombre_Departamento;
//	   public static String Fecha_Nacimiento1;
//	
	  
	   
	   public MarcarHogarCamposNoObligatorios(String Id_Hogar) {
		   this.Id_Hogar = Id_Hogar;
		   //this.Nombre = Nombre;
		   //this.Municipio = Municipio;
	
	   }
	   

//	   public MarcarHogarCamposNoObligatorios(String Cedula, String Nombre, String Lugar_expedicion,String Nombre_Departamento, String Fecha_Nacimiento1) {
//		   this.Cedula = Cedula;
//		   this.Nombre = Nombre;
//		   this.Lugar_expedicion = Lugar_expedicion;
//		   this.Nombre_Departamento = Nombre_Departamento;
//		   this.Fecha_Nacimiento1 = Fecha_Nacimiento1;		   
//   }
	   
	   
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
	 		    String rutaimagenes = propiedades.getProperty("rutaimagenesmarcarhogarnoobligatorio");
	 	
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
				 String constructor= propiedades.getProperty("constructor");
				 String proyecto= propiedades.getProperty("proyecto");
						 
				 
				 System.out.println(Id_Hogar);
				 
//				 System.out.println(Cedula);
//				 System.out.println(Nombre);
//				 System.out.println(Lugar_expedicion);
//			     System.out.println(Nombre_Departamento); 
//			     System.out.println(Fecha_Nacimiento1);
//				 
				//  driver.get("http://10.1.104.75:9090/sso-auth-server/login");
				    //driver.get("https://aplicaciones.cifin.co/sso-auth-server/login");
				  
				  
	  	    
	     driver.get(url);
	     driver.findElement(usuario).click();
	     driver.findElement(usuario).sendKeys(usuario2);
	     driver.findElement(clave).click();
	     driver.findElement(clave).sendKeys(password1);
	     driver.findElement(BotonIngresar).click();
	     Thread.sleep(2000);
	    
	     //Forzar login
		  
	     Boolean b1 = driver.equals(CheckForzar);
	     System.out.println("CheckForzar :" + b1);
	  
	      if (b1==true) {
	    	//  if (driver.findElement(CheckForzar).isDisplayed()){

	        driver.findElement(CheckForzar).click();
	        Thread.sleep(2000); 
	        driver.findElement(usuario).click();
	        driver.findElement(usuario).sendKeys(usuario2);
	        driver.findElement(clave).click();
	        driver.findElement(clave).sendKeys(password1);
	        driver.findElement(BotonIngresar).click();
	        
	      }
	    


	    //3er Formulario /menu escoger proyecto 
	    driver.findElement(CampoBusqueda).click();
	    driver.findElement(CampoBusqueda).sendKeys("Mi Casa Ya");
	    //driver.findElement(MiCasaYa).click();
	    driver.get("https://miportafoliodev.cifin.co/cifin/MiCasaYa/faces/menu?destino=Menu");
	    Thread.sleep(2000);
	    
	    //4to Formulario /Escoger Opcion Mi Casa Ya. 
	    driver.findElement(MarcarHogar).click();
	    Thread.sleep(2000);
	    
	  //5to Formulario //Formulario Marcar Hogar
		driver.findElement(CampoIdentificadorHogar).click(); 
	    driver.findElement(CampoIdentificadorHogar).sendKeys(Id_Hogar);
	    //driver.findElement(CampoIdentificadorHogar).sendKeys("7237");
	    driver.findElement(BotonConsultar).click();
	    Thread.sleep(2000);
//	    driver.findElement(DesplegableDepartamento).sendKeys("BOGOTA DISTRITO C.A");
//	   // driver.findElement(DesplegableDepartamento).sendKeys(Nombre_Departamento);
//	    Thread.sleep(2000);
//	    driver.findElement(DesplegableDepartamento).click();
//	    Thread.sleep(2000);
//	    driver.findElement(DesplegableMunicipio).sendKeys("BOGOTA DISTRITO C.A");
//	   // driver.findElement(DesplegableMunicipio).sendKeys(Lugar_expedicion);
//	    Thread.sleep(2000);
//	    driver.findElement(DesplegableMunicipio).click();
//	    Thread.sleep(2000);
//	    driver.findElement(DesplegableConstructor).sendKeys("136- PRUEBAS MCY");
//	    Thread.sleep(2000);
	    
	       driver.findElement(DesplegableDepartamento).click();
		    Thread.sleep(2000);
		    driver.findElement(DesplegableDepartamento).sendKeys("BOGOTA DISTRITO C.A");
		    //driver.findElement(DesplegableDepartamento).sendKeys(departamento);
		    //driver.findElement(DesplegableDepartamento).sendKeys("ANTIOQUIA");
		    Thread.sleep(2000);
		    driver.findElement(DesplegableMunicipio).click();
		    //Thread.sleep(2000);
		    //driver.findElement(DesplegableMunicipio).sendKeys(municipio);
		    driver.findElement(DesplegableMunicipio).sendKeys("BOGOTA DISTRITO C.A");
		   // Thread.sleep(2000);
		  //  driver.findElement(DesplegableMunicipio).click();
		    Thread.sleep(2000);
		  // driver.findElement(DesplegableMunicipio).sendKeys("MEDELLIN");
		   // Thread.sleep(2000);
		    //Thread.sleep(2000);
		   driver.findElement(DesplegableMunicipio).click();
		    Thread.sleep(2000);
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
	    
	    
	    
	    
//	    driver.findElement(OpcionConstructor1).click();
//    	Thread.sleep(2000);
//    	driver.findElement(DesplegableProyecto).click();
//        driver.findElement(OpcionProyecto1).click();
	    
	    
	 //   driver.findElement(DesplegableConstructor).click ();
	 //   Thread.sleep(2000);
	 //   driver.findElement(DesplegableConstructor).sendKeys("313- CONCURRENTE PRUEBA");
	 //   Thread.sleep(2000);
	 //   driver.findElement(DesplegableProyecto).click ();
	    Thread.sleep(2000);
	    driver.findElement(DesplegableRango1).click();
	    Thread.sleep(2000);
		driver.findElement(Celular).sendKeys(celular);
		Thread.sleep(2000);
		driver.findElement(Telefono).sendKeys(telefono);
		Thread.sleep(2000);
		driver.findElement(CorreoElectronico).sendKeys(correo_electronico);
		Thread.sleep(2000);
		driver.findElement(Direccion).sendKeys(direccion);
		Thread.sleep(2000);
//		driver.findElement(DesplegableContrato).click();
//		Thread.sleep(2000);
//		driver.findElement(DesplegableContrato).sendKeys(contrato);
//		Thread.sleep(2000);
//	    driver.findElement (OpcionRango1).click();
//	    Thread.sleep(1000);
	    driver.findElement(DesplegableRango2).click();
	    Thread.sleep(2000);
	    driver.findElement (OpcionRango2).click();
	    Thread.sleep(1000);
	    driver.findElement(DesplegableTipoVivienda1).click();
	    Thread.sleep(2000);
	    driver.findElement (OpcionTipoVivienda1).click();
	    Thread.sleep(1000);
	    driver.findElement(DesplegableTipoVivienda2).click();
	    Thread.sleep(2000);
	    driver.findElement (OpcionTipoVivienda2).click();
	    Thread.sleep(1000);
	    driver.findElement(DesplegableTipoContrato1).click();
	    Thread.sleep(2000);
	    driver.findElement (OpcionTipoContrato1).click();
	    Thread.sleep(1000);
	    driver.findElement(DesplegableTipoContrato2).click();
	    Thread.sleep(2000);
	    driver.findElement (OpcionTipoContrato2).click();
	    Thread.sleep(2000);
//	    driver.findElement(BotonValidar).click();
//	    Thread.sleep(2000);
//	    driver.findElement(BotonMarcar).click();
//	    Thread.sleep(2000);
	    
		//Datos Opcionales

		driver.findElement(DesplegableGenero).click();
		driver.findElement(DesplegableGenero).sendKeys(genero);
		driver.findElement(DesplegableOrientacion).click();
		driver.findElement(DesplegableOrientacion).sendKeys(orientacion);
		driver.findElement(DesplegableEstadoCivil).sendKeys(estado_civil);
		driver.findElement(DesplegableCondicionEspecial).sendKeys(condicion_especial);
		driver.findElement(DesplegableEtnica).sendKeys(etnia);
		
	    driver.findElement(BotonValidar).click();
	    Thread.sleep(5000);
	    captureScreenshot();
	    
//	    driver.findElement(BotonMarcar).click();
//	    Thread.sleep(2000);
	    URL url1 = Loader.getResource("log4j.properties");  // Esto carga el fichero como recurso si está en el CLASSPATH
        PropertyConfigurator.configure(url1);
        log.info("Log4j, Marcar Hogar Exitoso.");
		
	     
		  } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("Log4j, Automatizacion Fallida.");
				driver.quit();
			}
	  }
}
