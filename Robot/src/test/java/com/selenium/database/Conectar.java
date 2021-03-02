package com.selenium.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Conectar {
	
	private Connection conexion;

	public Connection getConexion(){
		return conexion;
	}
	public void setConexion(Connection conexion){
		this.conexion=conexion;
	}	
	public Connection conectar(){
		Properties propiedades = new Properties();
        InputStream entrada = null;
		
		try {
		
			  entrada = new FileInputStream("datos.properties");
		 	  propiedades.load(entrada); 
		 	 
			 
			  Class.forName("oracle.jdbc.OracleDriver");
			  
			  Map<String,String> env = System.getenv();
			  String servidor = env.get("servidorbd");
			  String usuario = env.get("usuariobd"); 
			  String password = env.get("passwordbd");
			  
			  //Desde Variables servidor
			//   conexion = DriverManager.getConnection(servidor,usuario,password);
			
			//Desde Parametros 
			  conexion = DriverManager.getConnection(propiedades.getProperty("servidor"),propiedades.getProperty("usuario"),propiedades.getProperty("password"));
			  
			  
				if(conexion!=null){
					System.out.println("Conexion establecida");
				}else{
					System.out.println("Error al conectar");
				}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
	 		e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return conexion;
	}
}