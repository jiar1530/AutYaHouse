package com.selenium.test;

import java.io.File;
import java.io.FileInputStream;
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
public class MarcacionPagado {

	private static Logger log = Logger.getLogger(ActosAdministrativos.class);
	Properties propiedades = new Properties();
    InputStream entrada = null;	

private WebDriver driver;
private Map<String, Object> vars;
JavascriptExecutor js;

//Ubicacion de Elementos


By usuario = By.xpath("//*[@id=\"username\"]");
By clave = By.id("password");
By BotonIngresar = By.id("sId");
By CheckForzar = By.xpath("//*[@id=\"forcelogin\"]");

//elementos 2do Formular
//By BotonConfiguracionAvanzada = By.id("details-button");
By BotonConfiguracionAvanzada = By.xpath("//*[@id=\"details-button\"]");
By LinkContinuar = By.id("proceed-link");

//elementos 3r Formulario

By CampoBusqueda = By.id("search-query");
//ambiente dev
 By MiCasaYa = By.xpath("//*[@id=\"id_70\"]/div/a"); 
//By MiCasaYa = By.xpath("//*[@id=\"id_46\"]/div/a");

// elementos 4to Formulario
   //Se llama en el menu Cobro al Subsidio
By MarcacionPagado = By.xpath("//*[@id=\'terceros:tbody_element\']/tr[4]/td/a"); 
// By MarcacionPagado = By.xpath("//*[@id=\"terceros:tbody_element\"]/tr[14]/td/a");

//Elementos 5 formulario Registro
By IdentificadorHogar = By.xpath("//*[@id=\'idFamilia\']");
By Fecha = By.xpath("//*[@id=\'FechaActo\']"); 
By BotonLimpiar = By.xpath("//*[@id=\'content\']/input[2]");
By BotonMarcar = By.xpath("//*[@id=\'content\']/input[1]");	 
By BotonNuevo  = By.xpath("//*[@id=\'btnNuevo\']");



//arreglos de la base de datos 
@Parameterized.Parameters   

public static Collection<Object[]> listaTextos() {

	List<Object[]> args = new ArrayList<>();



	try {

		Conectar oracle=new Conectar();
		Connection con;
		con=oracle.conectar();

		
		// String sql = "select Numero_identificacion,nombre from terceros WHERE ROWNUM <= 10";
		String sql = "select id_hogar from mcy_hogar WHERE estado_actual=10 and ROWNUM <= 3 order by id_hogar asc";

		Statement statement = con.createStatement();

		ResultSet result = statement.executeQuery(sql);

		//Extraer Data


		while (result.next()) {

			Object[] argumento = new Object[] { 

					result.getString(1),
					//result.getString(2),
					


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


public static String id_hogar;





public MarcacionPagado(String id_hogar) {
	this.id_hogar = id_hogar;
	//this.FECHA = FECHA;
	

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
			 String url2 = propiedades.getProperty("url2");
			 String fecha_marcacion_pagado = propiedades.getProperty("fecha_marcacion_pagado");
	         String usuario1=propiedades.getProperty("usuario1");
	         String password1=propiedades.getProperty("password1");
	
	
	System.out.println(id_hogar);
	
	driver.get(url);


	driver.findElement(usuario).click();
	driver.findElement(usuario).sendKeys(usuario1);
	driver.findElement(clave).click();
	driver.findElement(clave).sendKeys(password1);
	driver.findElement(BotonIngresar).click();
	Thread.sleep(2000);

		
		//Forzar login
//		driver.findElement(CheckForzar).click();
//		Thread.sleep(2000); 
//		driver.findElement(usuario).click();
//		driver.findElement(usuario).sendKeys(nombre_usuario);
//		driver.findElement(clave).click();
//		driver.findElement(clave).sendKeys(password);
//		driver.findElement(BotonIngresar).click();
//		Thread.sleep(3000);
		
		

		//2do Formulario/ Conexion no es privada
	//	driver.findElement(BotonConfiguracionAvanzada).click();
	//	driver.findElement(LinkContinuar).click();
		Thread.sleep(2000);
		
		//driver.get("https://localhost:8443/cifin/MiCasaYa/MarcacionPagado");
//		driver.get(url1);
//		Thread.sleep(2000);

		//3er Formulario /menu escoger proyecto 
		driver.findElement(CampoBusqueda).click();
		driver.findElement(CampoBusqueda).sendKeys("Mi Casa Ya");
		//driver.findElement(MiCasaYa).click();
		driver.get("https://miportafoliodev.cifin.co/cifin/MiCasaYa/MarcacionPagado/faces/marcacionpagado?destino=generacionPagado");
		Thread.sleep(8000);
		System.out.println("Ingreso a modulo Marcacion Pagado:TRUE");
		driver.findElement(IdentificadorHogar).click();
		//  driver.findElement(CampoCedula).sendKeys("1037479977");
		//  driver.findElement(CampoCedula).sendKeys(Integer.toString(id_hogar));
		driver.findElement(IdentificadorHogar).sendKeys(id_hogar);
		//driver.findElement(Fecha).sendKeys("18/11/2020");
		driver.findElement(Fecha).sendKeys(fecha_marcacion_pagado);
		
		driver.findElement(BotonMarcar).click();
		Thread.sleep(10000);
		
//		driver.findElement(BotonNuevo).click();
//		Thread.sleep(10000);
		 System.out.println("Marcacion Pagado:TRUE");
			
		 URL url1 = Loader.getResource("log4j.properties");  // Esto carga el fichero como recurso si está en el CLASSPATH
	     PropertyConfigurator.configure(url1);
	     log.info("Log4j, Marcacion Pagado Exitoso.");
		
		
	 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			driver.quit();
		}
	 }	
	
	
}
