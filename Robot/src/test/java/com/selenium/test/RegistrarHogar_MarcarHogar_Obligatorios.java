package com.selenium.test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
//import org.testng.reporters.Files;


import com.selenium.database.Conectar;


@RunWith(Parameterized.class)
public class RegistrarHogar_MarcarHogar_Obligatorios {
	
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
  
  
//  String nombre_usuario = "90681";
//  String password = "H33std";
//  
  //elementos 2do Formulario
  
  By BotonConfiguracionAvanzada = By.id("details-button");
  By LinkContinuar = By.id("proceed-link");

  //elementos 3r Formulario

  By CampoBusqueda = By.id("search-query");
  //Dev                     
   By MiCasaYa = By.xpath("//*[@id=\"id_69\"]/div/a");
 
 // By MiCasaYa = By.xpath("//*[@id=\"id_1\"]/div/a");
  
  //  By MiCasaYa = By.xpath("//*[@id=\"id_18\"]/div/a");

   //QA
   //By MiCasaYa = By.xpath("//*[@id=\"id_4\"]/div/a");
   
  
  //  By MiCasaYa = By.xpath("//*[@id=\"id_2\"]/div/a");
  
  // elementos 4to Formulario
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
  By DesplegableTipodecontrato = By.xpath("//*[@id=\"form1:tipoContrato\"]");
  
  By BotonNuevo = By.xpath("//*[@id=\"form1:botonNuevo\"]");
  By BotonValidar = By.xpath("//*[@id=\"validarButton\"]");		 

  
  
   @Parameterized.Parameters   
  
   public static Collection<Object[]> listaTextos() {
		  
	   List<Object[]> args = new ArrayList<>();
	     
	   

	 	 try {
	 		  
	 		Conectar oracle=new Conectar();
	 	 	Connection con;
	 	 	con=oracle.conectar();
	 		  
	
 		    //  String sql = "Select * from mcy_hogar";
 		    // String sql = "select Numero_identificacion,nombre from terceros WHERE ROWNUM <= 10";
	 	 	//String sql = "select Numero_identificacion,nombre, lugar_expedicion from terceros WHERE ROWNUM <= 10";
	 	 	//String sql = "select a.Numero_identificacion,a.nombre,a.lugar_expedicion,b.nombre_departamento,a.fecha_nacimiento from terceros a,departamentos b where b.codigo_departamento = a.departamento_expedicion and ROWNUM <= 2"; 
	 	 	String sql = "select distinct numero_identificacion from mcy_excepcion where numero_identificacion between '1002320874' and '1002323207'";
	 	 	String sql1= "Select mh.id_hogar from mcy_hog_tercero ht inner join mcy_hogar mh on mh.id_hogar = ht.id_hogar where ht.usuario_adicion='-1' and to_char(ht.fecha_adicion,'DD/MM/YYYY') like '21/12/2020%' and mh.estado_actual = '1' and mh.tipo_entidad='1' and mh.codigo_entidad='30'";
	 	 	
	 	 	
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
	 	   
 
//   public static String Cedula;
//   public static String Tipo_Identificacion;
//   public static String Municipio;
////   public static String Nombre;
   public static String Cedula;
   public static String ID;
   //public static String Lugar_expedicion;
   //public static String Nombre_Departamento;
   //public static String Fecha_Nacimiento1;
   
   
   //public RegistrarHogar_1(String Secuencia, String Tipo_Identificacion,String Cedula,String Nombre) {
   public RegistrarHogar_MarcarHogar_Obligatorios (String Cedula, String ID) {
	   this.Cedula = Cedula;
	   this.ID = ID;
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
	    
	    
	    entrada = new FileInputStream("datos.properties");
		 propiedades.load(entrada); 
		 
		 String url = propiedades.getProperty("url");
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
	  
	     //driver.findElement(CheckForzar).isEnabled();
	     
	      if (b1==true) {
	 	  
		//if (driver.findElement(CheckForzar).isSelected() == false)  
	   // if (driver.findElement(CheckForzar).isEnabled() == true)
	     // {
	        driver.findElement(CheckForzar).click();
	        Thread.sleep(2000); 
	        driver.findElement(usuario).click();
	        driver.findElement(usuario).sendKeys(usuario1);
	        driver.findElement(clave).click();
	        driver.findElement(clave).sendKeys(password);
	        driver.findElement(BotonIngresar).click();
	    	  
	        
	      }
	      
	     // else {
	      
	     
	     
		
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
			 
  //		 Map<String,String> env = System.getenv();
//			 String java_home = env.get("JAVA_HOME");
  //		 String Servidor = env.get("OneDrive");
 //			 System.out.println("DriverDATA: "+Servidor);
//			 System.out.println("java_home"+java_home);
			 
	//		 File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	  
			 System.out.println(Cedula);
			// System.out.println(Nombre);
			// System.out.println(Lugar_expedicion);
		    // System.out.println(Nombre_Departamento); 
		    // System.out.println(Fecha_Nacimiento1);
  
		  // Metemos en una lista de cedulas  del 80000000 al 90000000.
		     List<Integer> numbers = new ArrayList<>(10);
		     for (int i=80000000;i<80027213;i++){
		        numbers.add(i);
		     }

		     // Instanciamos la clase Random
		     Random random = new Random();

		     // Mientras queden numeros de cedula
		     while (numbers.size()>1){
		        // Elegimos un índice al azar, entre numero de Cedula incial y el número de cedulas por asignar
		          int randomIndex = random.nextInt(numbers.size());
                  String randomIndex1 = Integer.toString(randomIndex);
		    	 
		        // Imprimir numero de Cedula
		        System.out.println("Numero de Cedula no repetido "+numbers.get(randomIndex));

		        // Y eliminamos la carta del mazo (la borramos de la lista)
		        numbers.remove(randomIndex);
		     }	     
		     
		     //3er Formulario /menu escoger proyecto 
			    driver.findElement(CampoBusqueda).click();
			    driver.findElement(CampoBusqueda).sendKeys("Mi Casa Ya");
			    driver.findElement(MiCasaYa).click();
			    Thread.sleep(2000);
		    
		    //4to Formulario /Escoger Opcion Mi Casa Ya.
		    driver.findElement(RegistrarHogar).click();
		    Thread.sleep(2000);
		    
		    
   
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
    // driver.findElement(BotonNuevo).click();
    // MarcarHogarObligatorios nuevo = new MarcarHogarObligatorios("1");
	    
	 // }
	}
	catch (Exception ex) {
//		  // Do nothing ... 
	}
  } 
 
     @RunWith(Parameterized.class)
     public static class MarcarHogarObligatorios {
     	  
     	//static Properties propiedades1 = new Properties();
        // static InputStream entrada1 = null;	
     	
     //	  private static WebDriver driver;
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
     	 By MiCasaYa = By.xpath("//*[@id=\"id_69\"]/div/a");
     	 //By MiCasaYa = By.xpath("//*[@id='id_69']/div/a");
     	  
     	  
     	  
     	
     	 
     	  //En Uat
     	//  By MiCasaYa = By.xpath("//*[@id=\"id_18\"]/div/a");
     	//  By MiCasaYa = By.xpath("//*[@id=\"id_20\"]/div/a");
     	  
     	  //By MiCasaYa = By.xpath("//*[@id=\"id_2\"]/div/a");

     	  // elementos 4to Formulario
     	  By MarcarHogar = By.xpath("//*[@id=\'terceros:tbody_element\']/tr[3]/td/a");
     	
     	  
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
     	
     	  By BotonValidar = By.xpath("//*[@id=\"formResultados:_id187\"]");

     	
     	 // By BotonMarcar = By.xpath("//*[@id=\"formResultados:panelid\"]");
     	    By BotonMarcar = By.xpath("//*[@id=\"formResultados:marcar\"]");
     	  
     	  
     			
     	 
     	  
     	   @Parameterized.Parameters   
     	  
     	   public Collection<Object[]> listaTextos() {
     			  
     		   List<Object[]> args = new ArrayList<>();
     		     
     		   

     		 	 try {
     		 		  
     		 		Conectar oracle=new Conectar();
     		 	 	Connection con;
     		 	 	con=oracle.conectar();
     		 		  
     		 	     
     	 		  
     	 		     //String sql = "select Numero_identificacion,nombre from terceros WHERE ROWNUM <= 10";
     		 	 	//String sql = "select Numero_identificacion,nombre, lugar_expedicion from terceros WHERE ROWNUM <= 10";
     		 	 	//	String sql = "select * from mcy_hogar where estado_actual = '1' and tipo_entidad = '999' and codigo_entidad = '1' and fec_consulta like '%20'and ROWNUM <= 2"; 
     		 	 	//String sql ="SELECT * FROM MCY_HOG_TERCERO MT, MCY_HOGAR MG  WHERE MG.ID_HOGAR = MT.ID_HOGAR AND MG.ESTADO_REGISTRO LIKE 'A%' AND MT.ESTADO_REGISTRO LIKE 'A%' AND MT.NUMERO_IDENTIFICACION = '1002297080'";
     		 	 	 String sql1= "Select mh.id_hogar from mcy_hog_tercero ht inner join mcy_hogar mh on mh.id_hogar = ht.id_hogar where ht.usuario_adicion='-1' and to_char(ht.fecha_adicion,'DD/MM/YYYY') like '21/12/2020%' and mh.estado_actual = '1' and mh.tipo_entidad='1' and mh.codigo_entidad='30'";  
     		 	 	  	 
     		 	 	
     		 	 		
     		 	 		
     		 	 	
     		 	 	
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
//     		 		  // Do nothing ... 
     		 	}
//     		 		  
     		 	return args;
     	//
     		 	}
     		 	   
     	 
     	   public  String Id_Hogar;
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
     	     

     	        driver.findElement(CheckForzar).click();
     	        Thread.sleep(2000); 
     	        driver.findElement(usuario).click();
     	        driver.findElement(usuario).sendKeys(usuario2);
     	        driver.findElement(clave).click();
     	        driver.findElement(clave).sendKeys(password1);
     	        driver.findElement(BotonIngresar).click();
     	        
     	      }
     	    
     	      // else {

     	    //3er Formulario /menu escoger proyecto 
     	    driver.findElement(CampoBusqueda).click();
     	    driver.findElement(CampoBusqueda).sendKeys("Mi Casa ");
     	    Thread.sleep(2000);
     	    driver.findElement(MiCasaYa).click();
     	    Thread.sleep(2000);
     	    
     	    //4to Formulario /Escoger Opcion Mi Casa Ya.
     	    driver.findElement(MarcarHogar).click();
     	    Thread.sleep(2000);
     	    
     	  //5to Formulario //Formulario Marcar Hogar
     	    driver.findElement(CampoIdentificadorHogar).click(); 
     	    driver.findElement(CampoIdentificadorHogar).sendKeys(Id_Hogar);
     	  //  driver.findElement(CampoIdentificadorHogar).sendKeys("7237");
     	  
     	     Thread.sleep(2000);
     	     driver.findElement(BotonConsultar).click();
     	     Thread.sleep(5000);
     	     js.executeScript("window.scrollBy(0,500)");
     	     Thread.sleep(3000);
     	    
//     	    driver.findElement(DesplegableRango1).click();
//     	    Thread.sleep(2000);
//     	   // driver.findElement (OpcionRango1).click();
//     	     driver.findElement(DesplegableRango1).sendKeys("3-Hasta 2 smmlv");
//     	    Thread.sleep(1000);
     	    driver.findElement(DesplegableRango2).click();
     	    Thread.sleep(2000);
     	   // driver.findElement (OpcionRango2).click();
     	    driver.findElement(DesplegableRango2).sendKeys("3-Hasta 2 smmlv");
     	    driver.findElement(DesplegableRango2).click();
     	    Thread.sleep(2000);
     	   
     	 
     	    driver.findElement(DesplegableTipoVivienda1).click();
//     	    Thread.sleep(2000);
     	    driver.findElement (DesplegableTipoVivienda1).sendKeys(tipovivienda);
//     	    Thread.sleep(1000);
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
     	    //driver.findElement(DesplegableDepartamento).sendKeys(departamento);
     	    driver.findElement(DesplegableDepartamento).sendKeys("ANTIOQUIA");
     	    Thread.sleep(2000);
     	    driver.findElement(DesplegableMunicipio).click();
     	    Thread.sleep(2000);
     	    //driver.findElement(DesplegableMunicipio).sendKeys("Medellin");
     	    //driver.findElement(DesplegableMunicipio).sendKeys(municipio);
     	    driver.findElement(DesplegableMunicipio).sendKeys("MEDELLIN");
     	    Thread.sleep(2000);
     	    driver.findElement(DesplegableMunicipio).click();
     	    //Thread.sleep(2000);
     	   // driver.findElement(DesplegableMunicipio).sendKeys("MEDELLIN");
     	   // Thread.sleep(2000);
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
     	    Thread.sleep(5000);
     	    driver.findElement(BotonMarcar).click();
     	    Thread.sleep(5000);
     	    captureScreenshot();
     	    //  }
     	    
     		  } catch (IOException e) {
     				// TODO Auto-generated catch block
     				e.printStackTrace();
     				captureScreenshot();
     				driver.quit();
     			}
     		  
     	  }
     	}
    
  }
